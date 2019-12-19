package com.company;

import java.util.List;

public class NaiveBayesClassifier {
    private List<HouseVote> testData;
    private List<HouseVote> trainData;
    private List<HouseVote> allData;

    public NaiveBayesClassifier(List<HouseVote> testData, List<HouseVote> trainData, List<HouseVote> allData) {
        this.testData = testData;
        this.trainData = trainData;
        this.allData = allData;
    }


    public void makeTraining() {

    }
}
