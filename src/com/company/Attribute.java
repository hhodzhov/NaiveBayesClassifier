package com.company;

public class Attribute {
    private String name;
    private double democratProbabilityForPositiveAttribute;
    private double republicanProbabilityForPositiveAttribute;

    private long numberOfDemocratsForPostiveAttribute;
    private long numberOfRepublicansForPositiveAttribute;

    private double democratProbabilityForNegativeAttribute;
    private double republicanProbabilityForNegativeAttribute;

    private long numberOfDemocratsForNegativeAttribute;
    private long numberOfRepublicansForNegativeAttribute;

    private long allDemocrats;
    private long allRepublicans;

    private double democratProbability;
    private double republicanProbability;

    private double positiveAttributeProbability;
    private double negativeAttributeProbability;

    public Attribute(String name) {
        this.name = name;
    }

    public void setNumberOfDemocratsForPostiveAttribute(long numberOfDemocratsForPostiveAttribute) {
        this.numberOfDemocratsForPostiveAttribute = numberOfDemocratsForPostiveAttribute;
    }

    public void setNumberOfRepublicansForPositiveAttribute(long numberOfRepublicansForPositiveAttribute) {
        this.numberOfRepublicansForPositiveAttribute = numberOfRepublicansForPositiveAttribute;
    }

    public void setAllDemocrats(long allDemocrats) {
        this.allDemocrats = allDemocrats;
    }

    public void setAllRepublicans(long allRepublicans) {
        this.allRepublicans = allRepublicans;
    }

    public void setDemocratProbabilityForPositiveAttribute() {
        democratProbabilityForPositiveAttribute = numberOfDemocratsForPostiveAttribute / allDemocrats;
    }

    public void setRepublicanProbabilityForPositiveAttribute() {
        republicanProbabilityForPositiveAttribute = numberOfRepublicansForPositiveAttribute / allRepublicans;
    }

    public double getDemocratProbabilityForPositiveAttribute() {
        return democratProbabilityForPositiveAttribute;
    }

    public double getRepublicanProbabilityForPositiveAttribute() {
        return republicanProbabilityForPositiveAttribute;
    }

//    public void setDemocratProbability(double democratProbability) {
//        this.democratProbability = democratProbability;
//    }

//    public void setRepublicanProbability(double republicanProbability) {
//        this.republicanProbability = republicanProbability;
//    }

    public void setPositiveAttributeProbability(double positiveAttributeProbability) {
        this.positiveAttributeProbability = positiveAttributeProbability;
    }

    public void setNegativeAttributeProbability(double negativeAttributeProbability) {
        this.negativeAttributeProbability = negativeAttributeProbability;
    }

//    public double getDemocratProbability() {
//        return democratProbability;
//    }
//
//    public double getRepublicanProbability() {
//        return republicanProbability;
//    }

    public double getPositiveAttributeProbability() {
        return positiveAttributeProbability;
    }

    public double getNegativeAttributeProbability() {
        return negativeAttributeProbability;
    }

    public double getDemocratProbabilityForNegativeAttribute() {
        return democratProbabilityForNegativeAttribute;
    }

    public void setDemocratProbabilityForNegativeAttribute() {
        this.democratProbabilityForNegativeAttribute = numberOfDemocratsForNegativeAttribute / allDemocrats;
    }

    public double getRepublicanProbabilityForNegativeAttribute() {
        return republicanProbabilityForNegativeAttribute;
    }

    public void setRepublicanProbabilityForNegativeAttribute() {
        this.republicanProbabilityForNegativeAttribute = numberOfRepublicansForNegativeAttribute / allRepublicans;
    }

    public long getNumberOfDemocratsForNegativeAttribute() {
        return numberOfDemocratsForNegativeAttribute;
    }

    public void setNumberOfDemocratsForNegativeAttribute(long numberOfDemocratsForNegativeAttribute) {
        this.numberOfDemocratsForNegativeAttribute = numberOfDemocratsForNegativeAttribute;
    }

    public long getNumberOfRepublicansForNegativeAttribute() {
        return numberOfRepublicansForNegativeAttribute;
    }

    public void setNumberOfRepublicansForNegativeAttribute(long numberOfRepublicansForNegativeAttribute) {
        this.numberOfRepublicansForNegativeAttribute = numberOfRepublicansForNegativeAttribute;
    }
}
