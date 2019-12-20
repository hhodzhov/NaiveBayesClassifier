package com.company;

public class Attribute {
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

    private double positiveAttributeProbability;
    private double negativeAttributeProbability;

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
        democratProbabilityForPositiveAttribute = (double) numberOfDemocratsForPostiveAttribute / (double) allDemocrats;
    }

    public void setRepublicanProbabilityForPositiveAttribute() {
        republicanProbabilityForPositiveAttribute = (double) numberOfRepublicansForPositiveAttribute / (double) allRepublicans;
    }

    public double getDemocratProbabilityForPositiveAttribute() {
        return democratProbabilityForPositiveAttribute;
    }

    public double getRepublicanProbabilityForPositiveAttribute() {
        return republicanProbabilityForPositiveAttribute;
    }

    public void setPositiveAttributeProbability(double positiveAttributeProbability) {
        this.positiveAttributeProbability = positiveAttributeProbability;
    }

    public void setNegativeAttributeProbability(double negativeAttributeProbability) {
        this.negativeAttributeProbability = negativeAttributeProbability;
    }

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
        this.democratProbabilityForNegativeAttribute = (double) numberOfDemocratsForNegativeAttribute / (double) allDemocrats;
    }

    public double getRepublicanProbabilityForNegativeAttribute() {
        return republicanProbabilityForNegativeAttribute;
    }

    public void setRepublicanProbabilityForNegativeAttribute() {
        this.republicanProbabilityForNegativeAttribute = (double) numberOfRepublicansForNegativeAttribute / (double) allRepublicans;
    }

    public void setNumberOfDemocratsForNegativeAttribute(long numberOfDemocratsForNegativeAttribute) {
        this.numberOfDemocratsForNegativeAttribute = numberOfDemocratsForNegativeAttribute;
    }

    public void setNumberOfRepublicansForNegativeAttribute(long numberOfRepublicansForNegativeAttribute) {
        this.numberOfRepublicansForNegativeAttribute = numberOfRepublicansForNegativeAttribute;
    }
}
