package com.company;

public enum ClassType {
    DEMOCRAT("democrat"),
    REPUBLICAN("republican");

    private String classType;

    ClassType(String classType) {
        this.classType = classType;
    }

    public String getClassType() {
        return classType;
    }
}
