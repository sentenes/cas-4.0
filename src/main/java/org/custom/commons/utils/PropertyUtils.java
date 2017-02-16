package org.custom.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class PropertyUtils {
    private static Properties properties = new Properties();

    static {
        init();
    }

    private static void init() {
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath:*.properties");
            for (Resource resource : resources) {
                InputStream inputStream = resource.getInputStream();
                properties.load(inputStream);
            }
        } catch (IOException e) {
            LoggerUtils.error(PropertyUtils.class, "初始化读取属性文件错误", e);
        }
    }

    public static String getString(String key) {
        return properties.getProperty(key, "");
    }
}
