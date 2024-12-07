package com.example.IvfSuccessApplication.controller;

import com.example.IvfSuccessApplication.model.IvfUserInput;
import com.example.IvfSuccessApplication.service.IvfCalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IvfController {


    private static final Logger LOGGER = LoggerFactory.getLogger(IvfController.class);
    @Autowired
    private IvfCalculationService calculationService;

    @GetMapping("/")
    public String showForm() {
        // Load the HTML form
        return "ivf_form";
    }

    @PostMapping("/calculate")
    public String calculateSuccessRate(
            @RequestParam(name = "age") int age,
            @RequestParam(name = "weightInLbs") double weightInLbs,
            @RequestParam(name = "heightFeet") double heightFeet,
            @RequestParam(name = "heightInches") double heightInches,
            @RequestParam(name = "attemptedIvf") String attemptedIvf,
            @RequestParam(name = "usingOwnEggs") boolean usingOwnEggs,
            @RequestParam(name = "reasonForInfertilityKnown") boolean reasonForInfertilityKnown,
            @RequestParam(name = "tubalFactor", required = false, defaultValue = "false") boolean tubalFactor,
            @RequestParam(name = "maleFactorInfertility", required = false, defaultValue = "false") boolean maleFactorInfertility,
            @RequestParam(name = "endometriosis", required = false, defaultValue = "false") boolean endometriosis,
            @RequestParam(name = "ovulatoryDisorder", required = false, defaultValue = "false") boolean ovulatoryDisorder,
            @RequestParam(name = "diminishedOvarianReserve", required = false, defaultValue = "false") boolean diminishedOvarianReserve,
            @RequestParam(name = "uterineFactor", required = false, defaultValue = "false") boolean uterineFactor,
            @RequestParam(name = "otherReason", required = false, defaultValue = "false") boolean otherReason,
            @RequestParam(name = "unexplainedInfertility", required = false, defaultValue = "false") boolean unexplainedInfertility,
            @RequestParam(name = "priorPregnancies") int priorPregnancies,
            @RequestParam(name = "liveBirths") int liveBirths,
            Model model) {

        LOGGER.info("Entering IvfController.calculateSuccessRate");
        //Populating User Input values in an object for easier access
        IvfUserInput ivfUserInput = new IvfUserInput();
        ivfUserInput.setUserAge(age);
        ivfUserInput.setWeightInLbs(weightInLbs);
        ivfUserInput.setHeightFeet(heightFeet);
        ivfUserInput.setHeightInches(heightInches);
        ivfUserInput.setAttemptedIvf(attemptedIvf);
        ivfUserInput.setUsingOwnEggs(usingOwnEggs);
        ivfUserInput.setReasonForInfertilityKnown(reasonForInfertilityKnown);
        ivfUserInput.setTubalFactor(tubalFactor);
        ivfUserInput.setMaleFactorInfertility(maleFactorInfertility);
        ivfUserInput.setEndometriosis(endometriosis);
        ivfUserInput.setOvulatoryDisorder(ovulatoryDisorder);
        ivfUserInput.setDiminishedOvarianReserve(diminishedOvarianReserve);
        ivfUserInput.setUterineFactor(uterineFactor);
        ivfUserInput.setOtherReason(otherReason);
        ivfUserInput.setUnexplainedInfertility(unexplainedInfertility);
        ivfUserInput.setPriorPregnancies(priorPregnancies);
        ivfUserInput.setLiveBirths(liveBirths);

        try {
            // Calculate BMI
            double bmi = getBmi(weightInLbs, heightFeet, heightInches);

            String formulaId = getFormulaId(attemptedIvf, usingOwnEggs, reasonForInfertilityKnown);

            double successRate = calculationService.calculateSuccessRate(age, bmi, formulaId, ivfUserInput);

            // Add success rate to the model
            model.addAttribute("successRate", String.format("%.2f", successRate));

        } catch (Exception e) {
            // Handle errors
            model.addAttribute("error", "Error: " + e.getMessage());
        }

        LOGGER.info("Exiting IvfController.calculateSuccessRate");
        return "ivf_form";
    }

    private static double getBmi(double weightInLbs, double heightFeet, double heightInches) {
        LOGGER.info("Entering IvfController.getBmi");
        double bmi = weightInLbs / Math.pow((heightFeet * 12 + heightInches), 2) * 703;
        LOGGER.info("Exiting IvfController.getBmi");
        return bmi;
    }

    private static String getFormulaId(String attemptedIvf, boolean usingOwnEggs, boolean reasonForInfertilityKnown) {
        LOGGER.info("Entering IvfController.getFormulaId");
        //Formula Selection
        String formulaId = "";
        if(usingOwnEggs){
            formulaId = getFormulaIdForUsingOwnEggs(attemptedIvf,reasonForInfertilityKnown);
        }else{
            formulaId = getFormulaIdForUsingDonorEggs(reasonForInfertilityKnown);
        }
        LOGGER.info("Exiting IvfController.getFormulaId");
        return formulaId;
    }

    private static String getFormulaIdForUsingOwnEggs(String attemptedIvf, boolean reasonForInfertilityKnown) {
        LOGGER.info("IvfController.getFormulaIdForUsingOwnEggs");
        if ("TRUE".equals(attemptedIvf)) {
            return reasonForInfertilityKnown ? "7-8" : "9-10";
        } else {
            return reasonForInfertilityKnown ? "1-3" : "4-6";
        }
    }

    private static String getFormulaIdForUsingDonorEggs(boolean reasonForInfertilityKnown) {
        LOGGER.info("IvfController.getFormulaIdForUsingDonorEggs");
        return reasonForInfertilityKnown ? "11-13" : "14-16";
    }

}
