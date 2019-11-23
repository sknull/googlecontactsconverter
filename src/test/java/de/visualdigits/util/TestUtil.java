package de.visualdigits.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;

public class TestUtil {

    public static InputStream getInputStreamForResource(String resource) {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(resource);
    }

    public static String readResourceFile(String resource) {
        final InputStream ins = getInputStreamForResource(resource);
        try {
            return StringUtils.join(IOUtils.readLines(ins), "\n");
        } catch (IOException e) {
            throw new IllegalStateException("Could not read resource: "+ resource, e);
        }
    }
}
