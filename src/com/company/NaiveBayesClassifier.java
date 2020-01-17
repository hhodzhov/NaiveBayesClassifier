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
    private List<HouseVote> trainData;
    private Map<String, Attribute> attributeMap;
    private double democratProbability;
    private double republicanProbability;
    private static final String FIELD_CLASS_NAME = "className";

    public NaiveBayesClassifier(List<HouseVote> trainData) {
        this.trainData = trainData;
    }

    public void makeTraining() throws IntrospectionException {
        attributeMap = new HashMap<>();

        Class clazz = HouseVote.class;
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            Attribute attribute = new Attribute();
            if (!FIELD_CLASS_NAME.equals(field.getName())) {
                calculateProbabilitiesOfAttribute(trainData, field.getName(), attribute);
                attributeMap.put(field.getName(), attribute);
            }
        }
    }

    private void calculateProbabilitiesOfAttribute(List<HouseVote> trainData, String fieldName, Attribute attribute)
            throws IntrospectionException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, HouseVote.class);

        calculateProbabilityOfDemocrat(trainData, propertyDescriptor, attribute);
        calculateProbabilityOfRepublican(trainData, propertyDescriptor, attribute);
        calculateProbabilityOfAttributeType(trainData, propertyDescriptor, attribute, Y);
        calculateProbabilityOfAttributeType(trainData, propertyDescriptor, attribute, N);

    }

    private void calculateProbabilityOfAttributeType(List<HouseVote> trainData, PropertyDescriptor propertyDescriptor,
                                                     Attribute attribute, AttributeType attributeType) {
        long numberOfAttributeType = trainData.stream()
                .filter(houseVote -> {
                    try {
                        return attributeType.getType().equals(propertyDescriptor.getReadMethod().invoke(houseVote));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.out.println(e.getMessage());
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

        this.democratProbability = (double) numberOfAllDemocrats / trainData.size();
    }

    private void calculateProbabilityOfRepublican(List<HouseVote> trainData, PropertyDescriptor propertyDescriptor,
                                                  Attribute attribute) {
        long numberOfAllRepublicans = trainData.stream()
                .filter(houseVote -> REPUBLICAN.getClassType().equals(houseVote.getClassName()))
                .count();
        attribute.setAllRepublicans(numberOfAllRepublicans);

        calculateProbabilityForAttributeValue(trainData, propertyDescriptor, attribute, Y, REPUBLICAN);
        calculateProbabilityForAttributeValue(trainData, propertyDescriptor, attribute, N, REPUBLICAN);

        this.republicanProbability = (double) numberOfAllRepublicans / trainData.size();
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
                attribute.setRepublicanProbabilityForPositiveAttribute();
            } else if (N.equals(attributeType)) {
                attribute.setNumberOfRepublicansForNegativeAttribute(numberOfClassForAttributeValue);
                attribute.setRepublicanProbabilityForNegativeAttribute();
            }
        }
    }

    public boolean checkNewRecordAccuracy(HouseVote houseVote) throws IntrospectionException, InvocationTargetException,
            IllegalAccessException {
        Class clazz = HouseVote.class;
        Field[] fields = clazz.getDeclaredFields();

        double probabilityForDemocrats = 1.0;
        double probabilityForRepublicans = 1.0;

        double probabilityForAttributes = 1.0;

        for (Field field : fields) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), HouseVote.class);
            String value = (String) propertyDescriptor.getReadMethod().invoke(houseVote);
            Attribute attribute = attributeMap.get(field.getName());
            if (FIELD_CLASS_NAME.equals(field.getName())) {
                continue;
            }
            if (Y.getType().equals(value)) {
                probabilityForDemocrats *= attribute.getDemocratProbabilityForPositiveAttribute();
                probabilityForRepublicans *= attribute.getRepublicanProbabilityForPositiveAttribute();

                probabilityForAttributes *= attribute.getPositiveAttributeProbability();
            } else if (N.getType().equals(value)) {
                probabilityForDemocrats *= attribute.getDemocratProbabilityForNegativeAttribute();
                probabilityForRepublicans *= attribute.getRepublicanProbabilityForNegativeAttribute();

                probabilityForAttributes *= attribute.getNegativeAttributeProbability();
            }
        }

        probabilityForDemocrats *= democratProbability;
        probabilityForRepublicans *= republicanProbability;

        double resultDemocratProbability = probabilityForDemocrats / probabilityForAttributes;
        double resultRepublicanProbability = probabilityForRepublicans / probabilityForAttributes;

        String result = resultDemocratProbability > resultRepublicanProbability
                ? DEMOCRAT.getClassType()
                : REPUBLICAN.getClassType();

        return result != null && result.equals(houseVote.getClassName());
    }
}
