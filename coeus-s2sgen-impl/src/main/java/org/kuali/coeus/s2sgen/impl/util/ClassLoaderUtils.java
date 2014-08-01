package org.kuali.coeus.s2sgen.impl.util;

public final class ClassLoaderUtils {

    private ClassLoaderUtils() {
        throw new UnsupportedOperationException("do not call");
    }

    /**
     * Returns the default class loader within the current context.  If there is a context classloader
     * it is returned, otherwise the classloader which loaded the ClassLoaderUtil Class is returned.
     *
     * @return the appropriate default classloader which is guaranteed to be non-null
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoaderUtils.class.getClassLoader();
        }
        return classLoader;
    }
}
