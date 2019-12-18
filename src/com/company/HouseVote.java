package com.company;

public class HouseVote {
    private String className;
    private String handicappedInfants;
    private String waterProjectCostSharing;
    private String adoptionOfTheBudgetResolution;
    private String physicianFeeFreeze;
    private String elSalvadorAid;
    private String religiousGroupsInSchools;
    private String antiSateliteTestBan;
    private String aidToNicaraguanContras;
    private String mxMissile;
    private String immigration;
    private String synfuels;
    private String educationSpending;
    private String superFundRightToSue;
    private String crime;
    private String dutyFreeExports;
    private String exportAdminActSouthAfrica;

    public HouseVote(HouseVoteBuilder houseVoteBuilder) {
        this.className = houseVoteBuilder.className;
        this.handicappedInfants = houseVoteBuilder.handicappedInfants;
        this.waterProjectCostSharing = houseVoteBuilder.waterProjectCostSharing;
        this.adoptionOfTheBudgetResolution = houseVoteBuilder.adoptionOfTheBudgetResolution;
        this.physicianFeeFreeze = houseVoteBuilder.physicianFeeFreeze;
        this.elSalvadorAid = houseVoteBuilder.elSalvadorAid;
        this.religiousGroupsInSchools = houseVoteBuilder.religiousGroupsInSchools;
        this.antiSateliteTestBan = houseVoteBuilder.antiSateliteTestBan;
        this.aidToNicaraguanContras = houseVoteBuilder.aidToNicaraguanContras;
        this.mxMissile = houseVoteBuilder.mxMissile;
        this.immigration = houseVoteBuilder.immigration;
        this.synfuels = houseVoteBuilder.synfuels;
        this.educationSpending = houseVoteBuilder.educationSpending;
        this.superFundRightToSue = houseVoteBuilder.superFundRightToSue;
        this.crime = houseVoteBuilder.crime;
        this.dutyFreeExports = houseVoteBuilder.dutyFreeExports;
        this.exportAdminActSouthAfrica = houseVoteBuilder.exportAdminActSouthAfrica;
    }

    public static class HouseVoteBuilder {
        private String className;
        private String handicappedInfants;
        private String waterProjectCostSharing;
        private String adoptionOfTheBudgetResolution;
        private String physicianFeeFreeze;
        private String elSalvadorAid;
        private String religiousGroupsInSchools;
        private String antiSateliteTestBan;
        private String aidToNicaraguanContras;
        private String mxMissile;
        private String immigration;
        private String synfuels;
        private String educationSpending;
        private String superFundRightToSue;
        private String crime;
        private String dutyFreeExports;
        private String exportAdminActSouthAfrica;

        public HouseVoteBuilder setClassName(String className) {
            this.className = className;
            return this;
        }

        public HouseVoteBuilder setHandicappedInfants(String handicappedInfants) {
            this.handicappedInfants = handicappedInfants;
            return this;
        }

        public HouseVoteBuilder setWaterProjectCostSharing(String waterProjectCostSharing) {
            this.waterProjectCostSharing = waterProjectCostSharing;
            return this;
        }

        public HouseVoteBuilder setAdoptionOfTheBudgetResolution(String adoptionOfTheBudgetResolution) {
            this.adoptionOfTheBudgetResolution = adoptionOfTheBudgetResolution;
            return this;
        }

        public HouseVoteBuilder setPhysicianFeeFreeze(String physicianFeeFreeze) {
            this.physicianFeeFreeze = physicianFeeFreeze;
            return this;
        }

        public HouseVoteBuilder setElSalvadorAid(String elSalvadorAid) {
            this.elSalvadorAid = elSalvadorAid;
            return this;
        }

        public HouseVoteBuilder setReligiousGroupsInSchools(String religiousGroupsInSchools) {
            this.religiousGroupsInSchools = religiousGroupsInSchools;
            return this;
        }

        public HouseVoteBuilder setAntiSateliteTestBan(String antiSateliteTestBan) {
            this.antiSateliteTestBan = antiSateliteTestBan;
            return this;
        }

        public HouseVoteBuilder setAidToNicaraguanContras(String aidToNicaraguanContras) {
            this.aidToNicaraguanContras = aidToNicaraguanContras;
            return this;
        }

        public HouseVoteBuilder setMxMissile(String mxMissile) {
            this.mxMissile = mxMissile;
            return this;
        }

        public HouseVoteBuilder setImmigration(String immigration) {
            this.immigration = immigration;
            return this;
        }

        public HouseVoteBuilder setSynfuels(String synfuels) {
            this.synfuels = synfuels;
            return this;
        }

        public HouseVoteBuilder setEducationSpending(String educationSpending) {
            this.educationSpending = educationSpending;
            return this;
        }

        public HouseVoteBuilder setSuperFundRightToSue(String superFundRightToSue) {
            this.superFundRightToSue = superFundRightToSue;
            return this;
        }

        public HouseVoteBuilder setCrime(String crime) {
            this.crime = crime;
            return this;
        }

        public HouseVoteBuilder setDutyFreeExports(String dutyFreeExports) {
            this.dutyFreeExports = dutyFreeExports;
            return this;
        }

        public HouseVoteBuilder setExportAdminActSouthAfrica(String exportAdminActSouthAfrica) {
            this.exportAdminActSouthAfrica = exportAdminActSouthAfrica;
            return this;
        }

        public HouseVote build() {
            return new HouseVote(this);
        }
    }

    @Override
    public String toString() {
        return "HouseVote{" +
                "className='" + className + '\'' +
                ", handicappedInfants='" + handicappedInfants + '\'' +
                ", waterProjectCostSharing='" + waterProjectCostSharing + '\'' +
                ", adoptionOfTheBudgetResolution='" + adoptionOfTheBudgetResolution + '\'' +
                ", physicianFeeFreeze='" + physicianFeeFreeze + '\'' +
                ", elSalvadorAid='" + elSalvadorAid + '\'' +
                ", religiousGroupsInSchools='" + religiousGroupsInSchools + '\'' +
                ", antiSateliteTestBan='" + antiSateliteTestBan + '\'' +
                ", aidToNicaraguanContras='" + aidToNicaraguanContras + '\'' +
                ", mxMissile='" + mxMissile + '\'' +
                ", immigration='" + immigration + '\'' +
                ", synfuels='" + synfuels + '\'' +
                ", educationSpending='" + educationSpending + '\'' +
                ", superFundRightToSue='" + superFundRightToSue + '\'' +
                ", crime='" + crime + '\'' +
                ", dutyFreeExports='" + dutyFreeExports + '\'' +
                ", exportAdminActSouthAfrica='" + exportAdminActSouthAfrica + '\'' +
                '}';
    }
}
