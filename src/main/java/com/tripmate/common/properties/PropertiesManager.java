package com.tripmate.common.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String DEFAULT_SERVER_TYPE = "real";

    static Properties tripmateProperties = new Properties();

    private String choosePropertiesName(String propertiesFileName) {
        String serverType = null;
        try {
            serverType = System.getProperty("server.type");
            if (isEmpty(serverType)) {
                if (logger.isWarnEnabled()) {
                    logger.warn("System Property( -Dserver.type ) 값을 읽지 못했습니다. 기본값인 'real' 로 설정합니다.");
                }
                serverType = DEFAULT_SERVER_TYPE;
            }
        } catch (Exception ex) {
            if (logger.isWarnEnabled()) {
                logger.warn("System Property( -Dserver.type ) 값을 읽지 못했습니다. 기본값인 'real' 로 설정합니다.", ex);
            }
            serverType = DEFAULT_SERVER_TYPE;
        }

        String newPropertiesName = replace(propertiesFileName, "[$server_type]", serverType);

        if (logger.isInfoEnabled()) {
            logger.info("Property file : " + newPropertiesName);
        }

        return newPropertiesName;
    }

    /**
     * @param propertiesFileName
     */
    public void loadProperties(String propertiesFileName) {
        String newPropertiesFileName = choosePropertiesName(propertiesFileName);

        if (newPropertiesFileName == null || "".equals(newPropertiesFileName)) {
            if (logger.isErrorEnabled()) {
                logger.error("'" + newPropertiesFileName + "' File not found.");
            }
            return;
        }
        try {
            if (logger.isInfoEnabled()) {
                logger.info("Loading property from '" + newPropertiesFileName + "'.");
            }
            tripmateProperties.load(new FileInputStream(newPropertiesFileName));
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error("'" + newPropertiesFileName + "' File io error.", e);
            }
        }
    }

    public static String getProperty(String key) {
        return tripmateProperties.getProperty(key);
    }

    static void setProperty(String key, String value) {
        tripmateProperties.setProperty(key, value);
    }

    static void changeProperties(Properties boProperties) {
        PropertiesManager.tripmateProperties = boProperties;
    }

    public static void releaseProperties() {
        if (tripmateProperties != null) {
            tripmateProperties = null;
        }
    }

    static String replace(String srcStr, String oldStr, String newStr) {
        StringBuffer sb = new StringBuffer(srcStr);
        int start = 0;
        int end = 0;
        int pos = 0;

        while (true) {
            start = srcStr.indexOf(oldStr, pos);
            // 문자열에서 from이 더이상 발견되지 않으면 끝
            if (start == -1) {
                break;
            }
            end = start + oldStr.length();

            sb.replace(start, end, newStr);
            pos = start + newStr.length();
            srcStr = sb.toString();
        }
        return sb.toString();
    }

    static boolean isEmpty(String str) {
        return (str == null || str.isEmpty());
    }
}