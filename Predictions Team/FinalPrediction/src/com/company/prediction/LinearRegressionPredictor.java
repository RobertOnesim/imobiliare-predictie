package com.company.prediction;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class LinearRegressionPredictor {

    private double[] coefficients;

    public LinearRegressionPredictor() throws Exception {

        String trainPath, testPath;
        String option = "";

        System.out.println("Usage: java LinearRegressionPredictor <train-data> <test-data> <option string>");
        trainPath = "apartamente_vandute.arff";
        testPath = "apartamente_vandute.arff";

        Instances trainData = ConverterUtils.DataSource.read(trainPath), testData = ConverterUtils.DataSource.read(testPath);

        LinearRegression predictor = new LinearRegression();

        if (trainData.numInstances() == 0) {
            return; // no training data for this operator, skip
        }

        if (trainData.classIndex() == -1) {
            trainData.setClassIndex(trainData.numAttributes() - 1); // the
            // target
            // attribute
        }

        predictor.buildClassifier(trainData);

        if (testData.classIndex() == -1) {
            testData.setClassIndex(testData.numAttributes() - 1);
        }

        int n = testData.numInstances(), m = testData.numAttributes();

        double rmsle = 0;
        double rmsleZero = 0;
        String outputPath = "response.csv";

        try {
            // Create file
            FileWriter fstream = new FileWriter(outputPath);
            BufferedWriter out = new BufferedWriter(fstream);
            coefficients = predictor.coefficients();

            for (int i = 0; i < n; ++i) {
                Instance t = testData.instance(i);
                double pred = predictor.classifyInstance(t), act = t.value(m - 1);
                out.write(i + " , " + act + " , " + pred );
                out.newLine();

                //System.out.println(calculate_price(predictor, t));

                if (pred < 1) {
                    if (pred != 0) {
                        System.out.println("member " + i + ":" + pred);
                    }
                    pred = 0;
                }

                if (pred > 15) {
                    if (pred != 15) {
                        System.out.println("member " + i + ":" + pred);
                    }
                    pred = 15;
                }

                rmsle += (Math.log(pred + 1) - Math.log(act + 1))
                        * (Math.log(pred + 1) - Math.log(act + 1));
                rmsleZero += Math.log(act + 1) * Math.log(act + 1);

            }
            out.close();
        } catch (Exception e) {// Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        rmsle = Math.sqrt(rmsle / n);
        rmsleZero = Math.sqrt(rmsleZero / n);

        System.out.println();

        /*Root Mean Squared Logarithmic Error*/
        System.out.println("LinearRegressionPredictor option: " + option);
        System.out.println("# of training data: " + trainData.numInstances());
        System.out.println("# of testing data: " + testData.numInstances());
        System.out.println("RMSLE on testing data: " + rmsle);
        System.out.println("RMSLE on testing data Zero: " + rmsleZero);
    }

    public double[] getCoefficients() {
        return coefficients;
    }
}