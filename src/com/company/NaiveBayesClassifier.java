package com.company;

import static com.company.AttributeType.N;
import static com.company.AttributeType.Y;
import static com.company.ClassType.DEMOCRAT;
import static com.company.ClassType.REPUBLICAN;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NaiveBayesClassifier {
    private List<HouseVote> testData;
    private List<HouseVote> trainData;
    private List<HouseVote> allData;
    private Map<String, Attribute> attributeMap;

    public NaiveBayesClassifier(List<HouseVote> testData, List<HouseVote> trainData, List<HouseVote> allData) {
        this.testData = testData;
        this.trainData = trainData;
        this.allData = allData;
    }

    public void makeTraining() throws IntrospectionException {
        attributeMap = new HashMap<>();

        Class clazz = HouseVote.class;
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            Attribute attribute = new Attribute(field.getName());
            calculateProbabilitiesOfAttribute(trainData, field.getName(), attribute);

            attributeMap.put(field.getName(), attribute);
        }
    }

    private void calculateProbabilitiesOfAttribute(List<HouseVote> trainData, String name, Attribute attribute)
            throws IntrospectionException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name, HouseVote.class);

        calculateProbabilityOfDemocrat(trainData, propertyDescriptor, attribute);
        calculateProbabilityOfRepublican(trainData, propertyDescriptor, attribute);
        calculateProbabilityOfAttributeType(trainData, propertyDescriptor, attribute, Y);
        calculateProbabilityOfAttributeType(trainData, propertyDescriptor, attribute, N);

//        trainData.stream()
    }

    private void calculateProbabilityOfAttributeType(List<HouseVote> trainData, PropertyDescriptor propertyDescriptor,
                                                     Attribute attribute, AttributeType attributeType) {
        long numberOfAttributeType = trainData.stream()
                .filter(houseVote -> {
                    try {
                        return attributeType.getType().equals(propertyDescriptor.getReadMethod().invoke(houseVote));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .count();
        switch (attributeType) {
            case Y:
                attribute.setPositiveAttributeProbability((double) numberOfAttributeType / trainData.size());
                break;
            case N:
                attribute.setNegativeAttributeProbability((double) numberOfAttributeType / trainData.size());
                break;
        }
    }

    private void calculateProbabilityOfDemocrat(List<HouseVote> trainData, PropertyDescriptor propertyDescriptor,
                                                Attribute attribute) {
        long numberOfAllDemocrats = trainData.stream()
                .filter(houseVote -> DEMOCRAT.getClassType().equals(houseVote.getClassName()))
                .count();
        attribute.setAllDemocrats(numberOfAllDemocrats);

        calculateProbabilityForAttributeValue(trainData, propertyDescriptor, attribute, Y, DEMOCRAT);
        calculateProbabilityForAttributeValue(trainData, propertyDescriptor, attribute, N, DEMOCRAT);

        attribute.setDemocratProbability((double) numberOfAllDemocrats / trainData.size());
    }

    private void calculateProbabilityOfRepublican(List<HouseVote> trainData, PropertyDescriptor propertyDescriptor,
                                                  Attribute attribute) {
        long numberOfAllRepublicans = trainData.stream()
                .filter(houseVote -> REPUBLICAN.getClassType().equals(houseVote.getClassName()))
                .count();
        attribute.setAllRepublicans(numberOfAllRepublicans);

        calculateProbabilityForAttributeValue(trainData, propertyDescriptor, attribute, Y, REPUBLICAN);
        calculateProbabilityForAttributeValue(trainData, propertyDescriptor, attribute, N, REPUBLICAN);

        attribute.setRepublicanProbability((double) numberOfAllRepublicans / trainData.size());
    }

    private void calculateProbabilityForAttributeValue(List<HouseVote> trainData, PropertyDescriptor propertyDescriptor,
                                                       Attribute attribute, AttributeType attributeType,
                                                       ClassType classType) {
        long numberOfClassForAttributeValue = trainData.stream()
                .filter(houseVote -> classType.getClassType().equals(houseVote.getClassName()))
                .filter(houseVote -> {
                    try {
                        return attributeType.getType().equals(propertyDescriptor.getReadMethod().invoke(houseVote));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        return false;
                    }
                })
                .count();

        if (DEMOCRAT.equals(classType)) {
            if (Y.equals(attributeType)) {
                attribute.setNumberOfDemocratsForPostiveAttribute(numberOfClassForAttributeValue);
                attribute.setDemocratProbabilityForPositiveAttribute();
            } else if (N.equals(attributeType)) {
                attribute.setNumberOfDemocratsForNegativeAttribute(numberOfClassForAttributeValue);
                attribute.setDemocratProbabilityForNegativeAttribute();
            }
        } else {
            if (Y.equals(attributeType)) {
                attribute.setNumberOfRepublicansForPositiveAttribute(numberOfClassForAttributeValue);
                attribute.setRepublicanProbabilityForNegativeAttribute();
            } else if (N.equals(attributeType)) {
                attribute.setNumberOfRepublicansForNegativeAttribute(numberOfClassForAttributeValue);
                attribute.setRepublicanProbabilityForNegativeAttribute();
            }
        }
    }
}
