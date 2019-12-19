package com.company;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrossFoldValidation {

    private List<HouseVote> houseVotes;
    private int numPartitions;

    public CrossFoldValidation(List<HouseVote> houseVotes, int numPartitions) {
        this.houseVotes = houseVotes;
        this.numPartitions = numPartitions;
    }


    public void validate() {
        Map<Integer, Integer> intervals = getPartitionIntervals();
        intervals.forEach((k, v) -> {
            List<HouseVote> testData = new ArrayList<>(houseVotes.subList(k, v));
            List<HouseVote> trainData = new ArrayList<>(houseVotes);
            trainData.removeAll(testData);

            NaiveBayesClassifier bayesClassifier = new NaiveBayesClassifier(testData, trainData, houseVotes);
            try {
                bayesClassifier.makeTraining();
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
        });
    }

    private Map<Integer, Integer> getPartitionIntervals() {
        int sizeOfData = houseVotes.size();
        Map<Integer, Integer> intervals = new HashMap<>();
        int sizeOfSinglePartition = sizeOfData / numPartitions;

        int leftInterval = 0;
        int rightInterval = leftInterval + sizeOfSinglePartition;
        while (rightInterval <= sizeOfData) {
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
