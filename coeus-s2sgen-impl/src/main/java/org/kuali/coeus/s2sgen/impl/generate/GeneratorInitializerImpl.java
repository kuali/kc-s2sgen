package org.kuali.coeus.s2sgen.impl.generate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("generatorInitializer")
public class GeneratorInitializerImpl implements GeneratorInitializer, ApplicationContextAware, InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(GeneratorInitializerImpl.class);

    private ApplicationContext applicationContext;

    @Override
    public void initialize() {
        final Map<String, S2SFormGenerator> map = applicationContext.getBeansOfType(S2SFormGenerator.class);
        LOG.info("Found the following generators: " + map.keySet());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initialize();
    }
}
