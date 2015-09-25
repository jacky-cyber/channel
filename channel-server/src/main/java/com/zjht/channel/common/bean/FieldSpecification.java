package com.zjht.channel.common.bean;

import java.util.List;

/**
 * TODO
 * 
 * @author jun
 * @since JDK 1.8
 * @date Sep 23, 2015 9:42:00 PM
 */
public class FieldSpecification {
    private String name;
    private String info;
    private boolean required;
    private Class<?> type;
    private List<?> range;

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public boolean isRequired() {
        return required;
    }

    public Class<?> getType() {
        return type;
    }

    public List<?> getRange() {
        return range;
    }

    public FieldSpecification setName(String name) {
        this.name = name;
        return this;
    }

    public FieldSpecification setInfo(String info) {
        this.info = info;
        return this;
    }

    public FieldSpecification setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public FieldSpecification setType(Class<?> type) {
        this.type = type;
        return this;
    }

    public FieldSpecification setRange(List<?> range) {
        this.range = range;
        return this;
    }
}
