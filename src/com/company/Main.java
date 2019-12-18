package com.company;

import static com.company.Constants.PATH_TO_FILE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<HouseVote> houseVotes = getHouseVotesFromFile(PATH_TO_FILE);
        houseVotes.forEach(System.out::println);

    }

    private static List<HouseVote> getHouseVotesFromFile(String pathToFile) throws IOException {

        InputStream in;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(pathToFile)));

        List<HouseVote> houseVotes = new ArrayList<>();
        String currentLine = bufferedReader.readLine();

        while (currentLine != null) {
            HouseVote houseVote = convertLineToHouseVote(currentLine);
            houseVotes.add(houseVote);
            currentLine = bufferedReader.readLine();
        }

        return houseVotes;
    }

    private static HouseVote convertLineToHouseVote(String currentLine) {
        String[] attribute = currentLine.split(",");

        return new HouseVote.HouseVoteBuilder()
                .setClassName(attribute[0])
                .setHandicappedInfants(attribute[1])
                .setWaterProjectCostSharing(attribute[2])
                .setAdoptionOfTheBudgetResolution(attribute[3])
                .setPhysicianFeeFreeze(attribute[4])
                .setElSalvadorAid(attribute[5])
                .setReligiousGroupsInSchools(attribute[6])
                .setAntiSateliteTestBan(attribute[7])
                .setAidToNicaraguanContras(attribute[8])
                .setMxMissile(attribute[9])
                .setImmigration(attribute[10])
                .setSynfuels(attribute[11])
                .setEducationSpending(attribute[12])
                .setSuperFundRightToSue(attribute[13])
                .setCrime(attribute[14])
                .setDutyFreeExports(attribute[15])
                .setExportAdminActSouthAfrica(attribute[16])
                .build();
    }
}
