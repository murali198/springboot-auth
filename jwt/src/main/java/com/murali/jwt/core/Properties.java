package com.murali.jwt.core;

public class Properties {

    private java.util.Properties PROPERTIY_MAP;

    public Properties(java.util.Properties properties) {
        PROPERTIY_MAP = properties;
    }

    public String getValue(String key) {
        return PROPERTIY_MAP.getProperty(key);
    }
}
