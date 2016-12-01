/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 *
 * Copyright 2005-2016 Kuali, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.s2sgen.impl.generate.support.stylesheet;

import com.lowagie.text.pdf.PdfReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.fop.apps.*;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.xsd2inst.SampleXmlUtil;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.kuali.coeus.s2sgen.impl.generate.DynamicNamespace;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.S2SFormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.support.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * If you would like to save the results of this test run set the following SystemProperties:
 *
 * {@value ConfigurationConstants#PRINT_LOGGING_DIRECTORY}
 * {@value ConfigurationConstants#PRINT_LOGGING_ENABLE}
 * {@value ConfigurationConstants#PRINT_PDF_LOGGING_ENABLE}
 */
public class StylesheetValidationTest {

    private static final List<String> BROKEN_GENERATORS = Stream.of(RRSF424V1_0Generator.class.getName(),
            SF424V1_0Generator.class.getName(), ED524BudgetV1_0Generator.class.getName(), RRFedNonFedBudgetV1_0Generator.class.getName(),
            RRFedNonFedSubAwardBudget10_10V1_2Generator.class.getName(), RRFedNonFedSubAwardBudget10_30V1_2Generator.class.getName(),
            RRSubAwardBudgetV1_0Generator.class.getName(), RRSubAwardBudgetV1_1Generator.class.getName(),
            SFLLLV1_0Generator.class.getName()).collect(Collectors.toList());

    /**
     * This test inspects all available generators to do the following:
     * 1) generates sample XML that conforms to the grants.gov schema
     * 2) retrieves the xslt stylesheet from the generator
     * 3) attempts to transform the xml into a pdf
     * 4) makes sure a non-null, non-empty pdf is generated
     * 5) reads the pdf to make sure it is a valid pdf using a PdfReader
     */
    @Test
    public void test_stylesheets_validate() throws Exception {
        getGeneratorsToTest().forEach(generatorClass -> {
            final String sampleXml = getSampleXml(getSchemaType(generatorClass));
            try (final ByteArrayOutputStream byteStream = new ByteArrayOutputStream(); final OutputStream out = new BufferedOutputStream(byteStream)) {
                final FopFactory fopFactory = FopFactory.newInstance();
                final Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
                final TransformerFactory factory = TransformerFactory.newInstance();
                final Source xslt = new StreamSource(getXsltStylesheet(generatorClass).getInputStream());
                final Transformer transformer = factory.newTransformer(xslt);
                final Source src = new StreamSource(new ByteArrayInputStream(sampleXml.getBytes()));
                final Result res = new SAXResult(fop.getDefaultHandler());
                transformer.transform(src, res);
                out.flush();
                final byte[] bytes = byteStream.toByteArray();
                saveData(sampleXml, bytes, generatorClass);
                Assert.assertTrue(getFailMessage(generatorClass, sampleXml), ArrayUtils.isNotEmpty(bytes));

                new PdfReader(bytes);
            } catch (AssertionError|RuntimeException|FOPException|IOException|TransformerException e) {
                throw new RuntimeException(getFailMessage(generatorClass, sampleXml), e);
            }
        });
    }

    private void saveData(String xml, byte[] pdf, Class<? extends S2SFormGenerator> clazz) {
        final String printLogging = System.getProperty(ConfigurationConstants.PRINT_LOGGING_ENABLE);
        final String printPdfLogging = System.getProperty(ConfigurationConstants.PRINT_PDF_LOGGING_ENABLE);
        final String printDirectory = System.getProperty(ConfigurationConstants.PRINT_LOGGING_DIRECTORY);

        if (StringUtils.isNotBlank(printDirectory)) {
            try {
                if ("true".equalsIgnoreCase(printLogging)) {
                    File xmlFile = new File(new File(printDirectory), clazz.getSimpleName() + LocalDateTime.now().toString() + ".xml");
                    FileUtils.write(xmlFile, xml);
                }

                if ("true".equalsIgnoreCase(printPdfLogging) && pdf != null) {
                    File pdfFile = new File(new File(printDirectory), clazz.getSimpleName() + LocalDateTime.now().toString() + ".pdf");
                    FileUtils.writeByteArrayToFile(pdfFile, pdf);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String getFailMessage(Class<? extends S2SFormGenerator> generatorClass, String sampleXml) {
        return generatorClass.getName() + " has an invalid stylesheet or generated an invalid pdf\nSample XML:\n" + sampleXml;
    }

    private String getSampleXml(SchemaType type) {
        return SampleXmlUtil.createSampleForType(type);
    }

    private SchemaType getSchemaType(Class<? extends S2SFormGenerator> generatorClass) {
        final Class<?> returnType;
        try {
            returnType = generatorClass.getDeclaredMethod("getFormObject", ProposalDevelopmentDocumentContract.class).getReturnType();
            return (SchemaType) returnType.getDeclaredField("type").get(null);
        } catch (NoSuchMethodException|IllegalAccessException|NoSuchFieldException e) {
            throw new RuntimeException(generatorClass.getName() + " cannot find SchemaType", e);
        }
    }

    private Resource getXsltStylesheet(Class<? extends S2SFormGenerator> generatorClass) {
        try {
            final Field stylesheet = generatorClass.getDeclaredField("stylesheet");
            stylesheet.setAccessible(true);
            final Value value = stylesheet.getAnnotation(Value.class);
            final ResourceLoader resourceLoader = new DefaultResourceLoader(this.getClass().getClassLoader());
            return resourceLoader.getResource(value.value());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(generatorClass.getName() + " cannot find stylesheet", e);
        }
    }

    private Stream<Class<? extends S2SFormGenerator>> getGeneratorsToTest() {
        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);

        final TypeFilter testableFilter = (metadataReader, metadataReaderFactory) -> new AnnotationTypeFilter(FormGenerator.class).match(metadataReader, metadataReaderFactory)
                && new AssignableTypeFilter(S2SFormGenerator.class).match(metadataReader, metadataReaderFactory)
                && !metadataReader.getClassMetadata().isAbstract()
                && !BROKEN_GENERATORS.contains(metadataReader.getClassMetadata().getClassName());

        provider.addIncludeFilter(testableFilter);
        provider.addExcludeFilter(new AssignableTypeFilter(DynamicNamespace.class));
        provider.setResourceLoader(new PathMatchingResourcePatternResolver(this.getClass().getClassLoader()));
        final Set<BeanDefinition> generators = provider.findCandidateComponents("org.kuali.coeus.s2sgen.impl.generate.support");
        return generators.stream().map(generator -> {
            try {
                @SuppressWarnings("unchecked")
                final Class<? extends S2SFormGenerator> clazz = (Class<? extends S2SFormGenerator> ) Class.forName(generator.getBeanClassName());
                return clazz;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
