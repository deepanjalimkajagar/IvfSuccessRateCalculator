package com.example.IvfSuccessApplication.service;

import com.example.IvfSuccessApplication.model.IvfFormula;
import com.example.IvfSuccessApplication.model.IvfUserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IvfCalculationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IvfCalculationService.class);
    private final List<IvfFormula> formulas;


    public IvfCalculationService(CsvParserService csvParserService) {
        this.formulas = csvParserService.parseCsv("src/main/resources/ivf_success_formulas.csv");;
    }

    public double calculateSuccessRate(int userAge, double userBmi, String formulaId, IvfUserInput ivfUserInput ) {
        LOGGER.info("Entering IvfCalculationService.calculateSuccessRate");
        //Extracting the Formula required to perform calculations by filtering on the formula id (cdc_formula)
        IvfFormula selectedFormula = formulas.stream()
                .filter(f -> f.getFormulaId().equals(formulaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Formula not found"));

        double scoreRate = getScoreRate(userAge, userBmi, ivfUserInput, selectedFormula);

        double finalIvfScoreInPercentage = Math.exp(scoreRate) / (1 + Math.exp(scoreRate));

        LOGGER.info("Exiting IvfCalculationService.calculateSuccessRate");
        return finalIvfScoreInPercentage * 100;
    }

    private static double getScoreRate(int userAge, double userBmi, IvfUserInput ivfUserInput, IvfFormula selectedFormula) {
        LOGGER.info("Entering IvfCalculationService.getScoreRate");
        double scoreRate = 0;

        //Adding formula_intercept to the score rate
        scoreRate += selectedFormula.getIntercept();


        //Adding age with linear and polynomial component to the score rate
        double ageWithCoefficient = getAgeWithCoefficient(userAge, selectedFormula);
        scoreRate += ageWithCoefficient;

        //Adding BMI with linear and polynomial component to the score rate
        double bmiWithCoefficient = getBmiWithCoefficient(userBmi, selectedFormula);
        scoreRate += bmiWithCoefficient;

        //Adding Valid Tubal Factor Value to the Score Rate based on User Input
        scoreRate = addTubalFactorValueToScoreRate(ivfUserInput, selectedFormula, scoreRate);

        //Adding Valid Male Factor Infertility Value to the Score Rate based on User Input
        scoreRate = addMaleFactorInfertilityValueToScoreRate(ivfUserInput, selectedFormula, scoreRate);

        //Adding Valid Endometriosis Value to the Score Rate based on User Input
        scoreRate = addEndometriosisValueToScoreRate(ivfUserInput, selectedFormula, scoreRate);

        //Adding Valid Ovulatory Disorder Value to the Score Rate based on User Input
        scoreRate = addOvulatoryDisorderValueToScoreRate(ivfUserInput, selectedFormula, scoreRate);

        //Adding Valid Diminished Ovarian Reserve Value to the Score Rate based on User Input
        scoreRate = addDiminishedOvarianReserveValueToScoreRate(ivfUserInput, selectedFormula, scoreRate);

        //Adding Valid Uterine Factor Value to the Score Rate based on User Input
        scoreRate = addUterineFactorValueToScoreRate(ivfUserInput, selectedFormula, scoreRate);

        //Adding Valid Other Reason Value to the Score Rate based on User Input
        scoreRate = addOtherReasonValueToScoreRate(ivfUserInput, selectedFormula, scoreRate);

        //Adding Valid Unexplained Infertility Value to the Score Rate based on User Input
        scoreRate = addUnexplainedFertilityValueToScoreRate(ivfUserInput, selectedFormula, scoreRate);

        //Adding Valid Prior Pregnancies Value to the Score Rate based on User Input
        scoreRate = addPriorPregnanciesValueToScoreRate(ivfUserInput, selectedFormula, scoreRate);

        //Adding Valid Live Births Value to the Score Rate based on User Input
        scoreRate = addLiveBirthsValueToScoreRate(ivfUserInput, selectedFormula, scoreRate);

        LOGGER.info("Exiting IvfCalculationService.getScoreRate");
        return scoreRate;
    }

    private static double addTubalFactorValueToScoreRate(IvfUserInput ivfUserInput, IvfFormula selectedFormula, double scoreRate) {
        LOGGER.info("Entering IvfCalculationService.addTubalFactorValueToScoreRate");
        if(ivfUserInput.isTubalFactor()){
            scoreRate += selectedFormula.getTubalFactorTrueValue();
        }else{
            scoreRate += selectedFormula.getTubalFactorFalseValue();
        }
        LOGGER.info("Exiting IvfCalculationService.addTubalFactorValueToScoreRate");
        return scoreRate;
    }

    private static double addMaleFactorInfertilityValueToScoreRate(IvfUserInput ivfUserInput, IvfFormula selectedFormula, double scoreRate) {
        LOGGER.info("Entering IvfCalculationService.addMaleFactorInfertilityValueToScoreRate");
        if(ivfUserInput.isMaleFactorInfertility()){
            scoreRate += selectedFormula.getMaleFactorInfertilityTrueValue();
        }else{
            scoreRate += selectedFormula.getMaleFactorInfertilityFalseValue();
        }
        LOGGER.info("Exiting IvfCalculationService.addMaleFactorInfertilityValueToScoreRate");
        return scoreRate;
    }

    private static double addEndometriosisValueToScoreRate(IvfUserInput ivfUserInput, IvfFormula selectedFormula, double scoreRate) {
        LOGGER.info("Entering IvfCalculationService.addEndometriosisValueToScoreRate");
        if(ivfUserInput.isEndometriosis()){
            scoreRate += selectedFormula.getEndometriosisTrueValue();
        }else{
            scoreRate += selectedFormula.getEndometriosisFalseValue();
        }
        LOGGER.info("Exiting IvfCalculationService.addEndometriosisValueToScoreRate");
        return scoreRate;
    }

    private static double addOvulatoryDisorderValueToScoreRate(IvfUserInput ivfUserInput, IvfFormula selectedFormula, double scoreRate) {
        LOGGER.info("Entering IvfCalculationService.addOvulatoryDisorderValueToScoreRate");
        if(ivfUserInput.isOvulatoryDisorder()){
            scoreRate += selectedFormula.getOvulatoryDisorderTrueValue();
        }else{
            scoreRate += selectedFormula.getOvulatoryDisorderFalseValue();
        }
        LOGGER.info("Exiting IvfCalculationService.addOvulatoryDisorderValueToScoreRate");
        return scoreRate;
    }

    private static double addDiminishedOvarianReserveValueToScoreRate(IvfUserInput ivfUserInput, IvfFormula selectedFormula, double scoreRate) {
        LOGGER.info("Entering IvfCalculationService.addDiminishedOvarianReserveValueToScoreRate");
        if(ivfUserInput.isDiminishedOvarianReserve()){
            scoreRate += selectedFormula.getDiminishedOvarianReserveTrueValue();
        }else{
            scoreRate += selectedFormula.getDiminishedOvarianReserveFalseValue();
        }
        LOGGER.info("Exiting IvfCalculationService.addDiminishedOvarianReserveValueToScoreRate");
        return scoreRate;
    }

    private static double addUterineFactorValueToScoreRate(IvfUserInput ivfUserInput, IvfFormula selectedFormula, double scoreRate) {
        LOGGER.info("Entering IvfCalculationService.addUterineFactorValueToScoreRate");
        if(ivfUserInput.isUterineFactor()){
            scoreRate += selectedFormula.getUterineFactorTrueValue();
        }else{
            scoreRate += selectedFormula.getUterineFactorFalseValue();
        }
        LOGGER.info("Exiting IvfCalculationService.addUterineFactorValueToScoreRate");
        return scoreRate;
    }

    private static double addOtherReasonValueToScoreRate(IvfUserInput ivfUserInput, IvfFormula selectedFormula, double scoreRate) {
        LOGGER.info("Entering IvfCalculationService.addOtherReasonValueToScoreRate");
        if(ivfUserInput.isOtherReason()){
            scoreRate += selectedFormula.getOtherReasonTrueValue();
        }else{
            scoreRate += selectedFormula.getOtherReasonFalseValue();
        }
        LOGGER.info("Exiting IvfCalculationService.addOtherReasonValueToScoreRate");
        return scoreRate;
    }

    private static double addUnexplainedFertilityValueToScoreRate(IvfUserInput ivfUserInput, IvfFormula selectedFormula, double scoreRate) {
        LOGGER.info("Entering IvfCalculationService.addUnexplainedFertilityValueToScoreRate");
        if(ivfUserInput.isUnexplainedInfertility()){
            scoreRate += selectedFormula.getUnexplainedInfertilityTrueValue();
        }else{
            scoreRate += selectedFormula.getUnexplainedInfertilityFalseValue();
        }
        LOGGER.info("Exiting IvfCalculationService.addUnexplainedFertilityValueToScoreRate");
        return scoreRate;
    }

    private static double addLiveBirthsValueToScoreRate(IvfUserInput ivfUserInput, IvfFormula selectedFormula, double scoreRate) {
        LOGGER.info("Entering IvfCalculationService.addLiveBirthsValueToScoreRate");
        switch (ivfUserInput.getLiveBirths()){
            case 0:
                scoreRate += selectedFormula.getLiveBirths0Value();
                break;
            case 1:
                scoreRate += selectedFormula.getLiveBirths1Value();
                break;
            case 2:
                scoreRate += selectedFormula.getLiveBirths2PlusValue();
                break;
        }
        LOGGER.info("Exiting IvfCalculationService.addLiveBirthsValueToScoreRate");
        return scoreRate;
    }

    private static double addPriorPregnanciesValueToScoreRate(IvfUserInput ivfUserInput, IvfFormula selectedFormula, double scoreRate) {
        LOGGER.info("Entering IvfCalculationService.addPriorPregnanciesValueToScoreRate");
        switch (ivfUserInput.getPriorPregnancies()){
            case 0:
                scoreRate += selectedFormula.getPriorPregnancies0Value();
                break;
            case 1:
                scoreRate += selectedFormula.getPriorPregnancies1Value();
                break;
            case 2:
                scoreRate += selectedFormula.getPriorPregnancies2PlusValue();
                break;
        }
        LOGGER.info("Exiting IvfCalculationService.addPriorPregnanciesValueToScoreRate");
        return scoreRate;
    }

    private static double getBmiWithCoefficient(double userBmi, IvfFormula selectedFormula) {
        LOGGER.info("Entering IvfCalculationService.getBmiWithCoefficient");
        double bmiWithCoefficient = selectedFormula.getBmiLinearCoefficient() * userBmi + selectedFormula.getBmiPowerCoefficient() * (Math.pow(userBmi, selectedFormula.getBmiPowerFactor()));
        LOGGER.info("Exiting IvfCalculationService.getBmiWithCoefficient");
        return bmiWithCoefficient;
    }

    private static double getAgeWithCoefficient(int userAge, IvfFormula selectedFormula) {
        LOGGER.info("Entering IvfCalculationService.getAgeWithCoefficient");
        double ageWithCoefficient = selectedFormula.getAgeLinearCoefficient() * userAge + selectedFormula.getAgePowerCoefficient() * (Math.pow(userAge, selectedFormula.getAgePowerFactor()));
        LOGGER.info("Exiting IvfCalculationService.getAgeWithCoefficient");
        return ageWithCoefficient;
    }

}
