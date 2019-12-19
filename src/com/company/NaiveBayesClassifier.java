package com.company;

import static com.company.Constants.DEMOCRAT;
import static com.company.Constants.N;
import static com.company.Constants.REPUBLICAN;
import static com.company.Constants.Y;

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

//        trainData.stream()
    }

    private void calculateProbabilityOfDemocrat(List<HouseVote> trainData, PropertyDescriptor propertyDescriptor,
                                                Attribute attribute) {
        long numberOfAllDemocrats = trainData.stream()
                .filter(houseVote -> DEMOCRAT.equals(houseVote.getClassName()))
                .count();
        attribute.setAllDemocrats(numberOfAllDemocrats);

        calculateProbabilityForAttributeValue(trainData, propertyDescriptor, attribute, Y, DEMOCRAT);
        calculateProbabilityForAttributeValue(trainData, propertyDescriptor, attribute, N, DEMOCRAT);

        attribute.setDemocratProbability((double) numberOfAllDemocrats / trainData.size());
    }

    private void calculateProbabilityOfRepublican(List<HouseVote> trainData, PropertyDescriptor propertyDescriptor,
                                                  Attribute attribute) {
        long numberOfAllRepublicans = trainData.stream()
                .filter(houseVote -> REPUBLICAN.equals(houseVote.getClassName()))
                .count();
        attribute.setAllRepublicans(numberOfAllRepublicans);

        calculateProbabilityForAttributeValue(trainData, propertyDescriptor, attribute, Y, REPUBLICAN);
        calculateProbabilityForAttributeValue(trainData, propertyDescriptor, attribute, N, REPUBLICAN);

        attribute.setRepublicanProbability((double) numberOfAllRepublicans / trainData.size());
    }

    private void calculateProbabilityForAttributeValue(List<HouseVote> trainData, PropertyDescriptor propertyDescriptor,
                                                       Attribute attribute, String value, String klass) {
        long numberOfClassForAttributeValue = trainData.stream()
                .filter(houseVote -> klass.equals(houseVote.getClassName()))
                .filter(houseVote -> {
                    try {
                        return value.equals(propertyDescriptor.getReadMethod().invoke(houseVote));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        return false;
                    }
                })
                .count();

        if (DEMOCRAT.equals(klass)) {
            if (Y.equals(value)) {
                attribute.setNumberOfDemocratsForPostiveAttribute(numberOfClassForAttributeValue);
                attribute.setDemocratProbabilityForPositiveAttribute();
            } else if (N.equals(value)) {
                attribute.setNumberOfDemocratsForNegativeAttribute(numberOfClassForAttributeValue);
                attribute.setDemocratProbabilityForNegativeAttribute();
            }
        } else {
            if (Y.equals(value)) {
                attribute.setNumberOfRepublicansForPositiveAttribute(numberOfClassForAttributeValue);
                attribute.setRepublicanProbabilityForNegativeAttribute();
            } else if (N.equals(value)) {
                attribute.setNumberOfRepublicansForNegativeAttribute(numberOfClassForAttributeValue);
                attribute.setRepublicanProbabilityForNegativeAttribute();
            }
        }
    }
}
