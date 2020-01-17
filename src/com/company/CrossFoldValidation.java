package com.company;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

public class CrossFoldValidation {

    private List<HouseVote> houseVotes;
    private int numPartitions;
    private List<Double> matchAccuracies;

    public CrossFoldValidation(List<HouseVote> houseVotes, int numPartitions) {
        this.houseVotes = houseVotes;
        this.numPartitions = numPartitions;
    }


    public void validate() {
        Map<Integer, Integer> intervals = getPartitionIntervals();
        matchAccuracies = new ArrayList<>();
        intervals.forEach((k, v) -> {
            List<HouseVote> testData = new ArrayList<>(houseVotes.subList(k, v));
            List<HouseVote> trainData = new ArrayList<>(houseVotes);
            trainData.removeAll(testData);

            int positiveMatch = 0;
            NaiveBayesClassifier bayesClassifier = new NaiveBayesClassifier(trainData);
            try {
                bayesClassifier.makeTraining();
                for (HouseVote houseVote : testData) {
                    if (bayesClassifier.checkNewRecordAccuracy(houseVote)) {
                        positiveMatch++;
                    }
                }
                double percent = calculatePercent(positiveMatch, testData.size());
                matchAccuracies.add(percent);
                System.out.println("Current match accuracy: " + percent);
            } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.getMessage());
            }
        });

        OptionalDouble averageAccuracy = matchAccuracies.stream()
                .mapToDouble(a -> a)
                .average();

        System.out.println(averageAccuracy.isPresent()
                ? "Average accuracy: " + averageAccuracy.getAsDouble()
                : "There is no average accuracy");
    }

    private double calculatePercent(int positiveMatches, int size) {
        return ((double) positiveMatches / (double) size) * 100;
    }

    /**
     * @return Map<Integer, Integer> - the intervals for each partition - leftSide and rightSide
     */
    private Map<Integer, Integer> getPartitionIntervals() {
        int sizeOfData = houseVotes.size();
        Map<Integer, Integer> intervals = new HashMap<>();
        int sizeOfSinglePartition = sizeOfData / numPartitions;

        int leftInterval = 0;
        int rightInterval = leftInterval + sizeOfSinglePartition;
        while (rightInterval < sizeOfData) {
            intervals.put(leftInterval, rightInterval);
            leftInterval += sizeOfSinglePartition;
            rightInterval = leftInterval + sizeOfSinglePartition;

            if (rightInterval > sizeOfData) {
                rightInterval = sizeOfData;
            }
        }
        return intervals;
    }
}
