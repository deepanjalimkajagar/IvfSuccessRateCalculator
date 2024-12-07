package com.example.IvfSuccessApplication.model;

public class IvfFormula {
    // Formula selection parameters
    private boolean usingOwnEggs;
    private String attemptedIvfPreviously; // TRUE, FALSE, or N/A
    private boolean reasonForInfertilityKnown;
    private String formulaId;

    // Formula components
    private double intercept;
    private double ageLinearCoefficient;
    private double agePowerCoefficient;
    private double agePowerFactor;

    private double bmiLinearCoefficient;
    private double bmiPowerCoefficient;
    private double bmiPowerFactor;

    // Infertility reason values
    private double tubalFactorTrueValue;
    private double tubalFactorFalseValue;
    private double maleFactorInfertilityTrueValue;
    private double maleFactorInfertilityFalseValue;
    private double endometriosisTrueValue;
    private double endometriosisFalseValue;
    private double ovulatoryDisorderTrueValue;
    private double ovulatoryDisorderFalseValue;
    private double diminishedOvarianReserveTrueValue;
    private double diminishedOvarianReserveFalseValue;
    private double uterineFactorTrueValue;
    private double uterineFactorFalseValue;
    private double otherReasonTrueValue;
    private double otherReasonFalseValue;
    private double unexplainedInfertilityTrueValue;
    private double unexplainedInfertilityFalseValue;

    // Prior pregnancies (Gravida)
    private double priorPregnancies0Value;
    private double priorPregnancies1Value;
    private double priorPregnancies2PlusValue;

    // Live births
    private double liveBirths0Value;
    private double liveBirths1Value;
    private double liveBirths2PlusValue;

    public double getUterineFactorTrueValue() {
        return uterineFactorTrueValue;
    }

    public void setUterineFactorTrueValue(double uterineFactorTrueValue) {
        this.uterineFactorTrueValue = uterineFactorTrueValue;
    }

    public double getUterineFactorFalseValue() {
        return uterineFactorFalseValue;
    }

    public void setUterineFactorFalseValue(double uterineFactorFalseValue) {
        this.uterineFactorFalseValue = uterineFactorFalseValue;
    }

    public double getOtherReasonTrueValue() {
        return otherReasonTrueValue;
    }

    public void setOtherReasonTrueValue(double otherReasonTrueValue) {
        this.otherReasonTrueValue = otherReasonTrueValue;
    }

    public double getOtherReasonFalseValue() {
        return otherReasonFalseValue;
    }

    public void setOtherReasonFalseValue(double otherReasonFalseValue) {
        this.otherReasonFalseValue = otherReasonFalseValue;
    }

    public double getUnexplainedInfertilityTrueValue() {
        return unexplainedInfertilityTrueValue;
    }

    public void setUnexplainedInfertilityTrueValue(double unexplainedInfertilityTrueValue) {
        this.unexplainedInfertilityTrueValue = unexplainedInfertilityTrueValue;
    }

    public double getUnexplainedInfertilityFalseValue() {
        return unexplainedInfertilityFalseValue;
    }

    public void setUnexplainedInfertilityFalseValue(double unexplainedInfertilityFalseValue) {
        this.unexplainedInfertilityFalseValue = unexplainedInfertilityFalseValue;
    }

    public double getDiminishedOvarianReserveFalseValue() {
        return diminishedOvarianReserveFalseValue;
    }

    public void setDiminishedOvarianReserveFalseValue(double diminishedOvarianReserveFalseValue) {
        this.diminishedOvarianReserveFalseValue = diminishedOvarianReserveFalseValue;
    }

    public double getDiminishedOvarianReserveTrueValue() {
        return diminishedOvarianReserveTrueValue;
    }

    public void setDiminishedOvarianReserveTrueValue(double diminishedOvarianReserveTrueValue) {
        this.diminishedOvarianReserveTrueValue = diminishedOvarianReserveTrueValue;
    }


    public double getPriorPregnancies0Value() {
        return priorPregnancies0Value;
    }

    public void setPriorPregnancies0Value(double priorPregnancies0Value) {
        this.priorPregnancies0Value = priorPregnancies0Value;
    }

    public double getPriorPregnancies1Value() {
        return priorPregnancies1Value;
    }

    public void setPriorPregnancies1Value(double priorPregnancies1Value) {
        this.priorPregnancies1Value = priorPregnancies1Value;
    }

    public double getPriorPregnancies2PlusValue() {
        return priorPregnancies2PlusValue;
    }

    public void setPriorPregnancies2PlusValue(double priorPregnancies2PlusValue) {
        this.priorPregnancies2PlusValue = priorPregnancies2PlusValue;
    }

    public double getLiveBirths0Value() {
        return liveBirths0Value;
    }

    public void setLiveBirths0Value(double liveBirths0Value) {
        this.liveBirths0Value = liveBirths0Value;
    }

    public double getLiveBirths1Value() {
        return liveBirths1Value;
    }

    public void setLiveBirths1Value(double liveBirths1Value) {
        this.liveBirths1Value = liveBirths1Value;
    }

    public double getLiveBirths2PlusValue() {
        return liveBirths2PlusValue;
    }

    public void setLiveBirths2PlusValue(double liveBirths2PlusValue) {
        this.liveBirths2PlusValue = liveBirths2PlusValue;
    }

    public double getTubalFactorTrueValue() {
        return tubalFactorTrueValue;
    }

    public void setTubalFactorTrueValue(double tubalFactorTrueValue) {
        this.tubalFactorTrueValue = tubalFactorTrueValue;
    }

    public double getTubalFactorFalseValue() {
        return tubalFactorFalseValue;
    }

    public void setTubalFactorFalseValue(double tubalFactorFalseValue) {
        this.tubalFactorFalseValue = tubalFactorFalseValue;
    }

    public double getMaleFactorInfertilityTrueValue() {
        return maleFactorInfertilityTrueValue;
    }

    public void setMaleFactorInfertilityTrueValue(double maleFactorInfertilityTrueValue) {
        this.maleFactorInfertilityTrueValue = maleFactorInfertilityTrueValue;
    }

    public double getMaleFactorInfertilityFalseValue() {
        return maleFactorInfertilityFalseValue;
    }

    public void setMaleFactorInfertilityFalseValue(double maleFactorInfertilityFalseValue) {
        this.maleFactorInfertilityFalseValue = maleFactorInfertilityFalseValue;
    }

    public double getEndometriosisTrueValue() {
        return endometriosisTrueValue;
    }

    public void setEndometriosisTrueValue(double endometriosisTrueValue) {
        this.endometriosisTrueValue = endometriosisTrueValue;
    }

    public double getEndometriosisFalseValue() {
        return endometriosisFalseValue;
    }

    public void setEndometriosisFalseValue(double endometriosisFalseValue) {
        this.endometriosisFalseValue = endometriosisFalseValue;
    }

    public double getOvulatoryDisorderTrueValue() {
        return ovulatoryDisorderTrueValue;
    }

    public void setOvulatoryDisorderTrueValue(double ovulatoryDisorderTrueValue) {
        this.ovulatoryDisorderTrueValue = ovulatoryDisorderTrueValue;
    }

    public double getOvulatoryDisorderFalseValue() {
        return ovulatoryDisorderFalseValue;
    }

    public void setOvulatoryDisorderFalseValue(double ovulatoryDisorderFalseValue) {
        this.ovulatoryDisorderFalseValue = ovulatoryDisorderFalseValue;
    }

    public boolean isUsingOwnEggs() {
        return usingOwnEggs;
    }

    public void setUsingOwnEggs(boolean usingOwnEggs) {
        this.usingOwnEggs = usingOwnEggs;
    }

    public String getAttemptedIvfPreviously() {
        return attemptedIvfPreviously;
    }

    public void setAttemptedIvfPreviously(String attemptedIvfPreviously) {
        this.attemptedIvfPreviously = attemptedIvfPreviously;
    }

    public boolean isReasonForInfertilityKnown() {
        return reasonForInfertilityKnown;
    }

    public void setReasonForInfertilityKnown(boolean reasonForInfertilityKnown) {
        this.reasonForInfertilityKnown = reasonForInfertilityKnown;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public double getIntercept() {
        return intercept;
    }

    public void setIntercept(double intercept) {
        this.intercept = intercept;
    }

    public double getAgeLinearCoefficient() {
        return ageLinearCoefficient;
    }

    public void setAgeLinearCoefficient(double ageLinearCoefficient) {
        this.ageLinearCoefficient = ageLinearCoefficient;
    }

    public double getAgePowerCoefficient() {
        return agePowerCoefficient;
    }

    public void setAgePowerCoefficient(double agePowerCoefficient) {
        this.agePowerCoefficient = agePowerCoefficient;
    }

    public double getAgePowerFactor() {
        return agePowerFactor;
    }

    public void setAgePowerFactor(double agePowerFactor) {
        this.agePowerFactor = agePowerFactor;
    }

    public double getBmiLinearCoefficient() {
        return bmiLinearCoefficient;
    }

    public void setBmiLinearCoefficient(double bmiLinearCoefficient) {
        this.bmiLinearCoefficient = bmiLinearCoefficient;
    }

    public double getBmiPowerCoefficient() {
        return bmiPowerCoefficient;
    }

    public void setBmiPowerCoefficient(double bmiPowerCoefficient) {
        this.bmiPowerCoefficient = bmiPowerCoefficient;
    }

    public double getBmiPowerFactor() {
        return bmiPowerFactor;
    }

    public void setBmiPowerFactor(double bmiPowerFactor) {
        this.bmiPowerFactor = bmiPowerFactor;
    }
}
