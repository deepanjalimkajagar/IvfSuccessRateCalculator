package com.example.IvfSuccessApplication.service;

import com.example.IvfSuccessApplication.model.IvfFormula;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvParserService {


    private static final Logger LOGGER = LoggerFactory.getLogger(CsvParserService.class);

    public List<IvfFormula> parseCsv(String filePath) {
        LOGGER.info("Entering CsvParserService.parseCsv");

        List<IvfFormula> formulas = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] headers = reader.readNext(); // Read headers
            String[] line;
            while ((line = reader.readNext()) != null) {
                IvfFormula formula = new IvfFormula();

                // Parse formula selection parameters
                formula.setUsingOwnEggs(Boolean.parseBoolean(line[0]));
                formula.setAttemptedIvfPreviously(line[1]);
                formula.setReasonForInfertilityKnown(Boolean.parseBoolean(line[2]));
                formula.setFormulaId(line[3]);

                // Parse formula components
                formula.setIntercept(Double.parseDouble(line[4]));
                formula.setAgeLinearCoefficient(Double.parseDouble(line[5]));
                formula.setAgePowerCoefficient(Double.parseDouble(line[6]));
                formula.setAgePowerFactor(Double.parseDouble(line[7]));

                formula.setBmiLinearCoefficient(Double.parseDouble(line[8]));
                formula.setBmiPowerCoefficient(Double.parseDouble(line[9]));
                formula.setBmiPowerFactor(Double.parseDouble(line[10]));

                // Parse infertility reason values
                formula.setTubalFactorTrueValue(Double.parseDouble(line[11]));
                formula.setTubalFactorFalseValue(Double.parseDouble(line[12]));
                formula.setMaleFactorInfertilityTrueValue(Double.parseDouble(line[13]));
                formula.setMaleFactorInfertilityFalseValue(Double.parseDouble(line[14]));
                formula.setEndometriosisTrueValue(Double.parseDouble(line[15]));
                formula.setEndometriosisFalseValue(Double.parseDouble(line[16]));
                formula.setOvulatoryDisorderTrueValue(Double.parseDouble(line[17]));
                formula.setOvulatoryDisorderFalseValue(Double.parseDouble(line[18]));
                formula.setDiminishedOvarianReserveTrueValue(Double.parseDouble(line[19]));
                formula.setDiminishedOvarianReserveFalseValue(Double.parseDouble(line[20]));
                formula.setUterineFactorTrueValue(Double.parseDouble(line[21]));
                formula.setUterineFactorFalseValue(Double.parseDouble(line[22]));
                formula.setOtherReasonTrueValue(Double.parseDouble(line[23]));
                formula.setOtherReasonFalseValue(Double.parseDouble(line[24]));
                formula.setUnexplainedInfertilityTrueValue(Double.parseDouble(line[25]));
                formula.setUnexplainedInfertilityFalseValue(Double.parseDouble(line[26]));

                // Parse prior pregnancies
                formula.setPriorPregnancies0Value(Double.parseDouble(line[27]));
                formula.setPriorPregnancies1Value(Double.parseDouble(line[28]));
                formula.setPriorPregnancies2PlusValue(Double.parseDouble(line[29]));

                // Parse live births
                formula.setLiveBirths0Value(Double.parseDouble(line[30]));
                formula.setLiveBirths1Value(Double.parseDouble(line[31]));
                formula.setLiveBirths2PlusValue(Double.parseDouble(line[32]));


                formulas.add(formula);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.info("Exiting CsvParserService.parseCsv");
        return formulas;
    }
}
