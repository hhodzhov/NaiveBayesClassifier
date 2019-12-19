package com.company;

public enum AttributeType {
    Y("y"),
    N("n");

    private String type;

    AttributeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
