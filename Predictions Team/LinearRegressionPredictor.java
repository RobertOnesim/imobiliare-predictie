package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class LinearRegressionPredictor {

    public static void main(String[] args) throws Exception {

        String trainPath, testPath;
        String option = "";
        if (args.length < 2 || args.length > 3) {
            System.out
                    .println("Usage: java LinearRegressionPredictor <train-data> <test-data> <option string>");
            //
            trainPath =  "apartamente_vandute.arff";
            testPath = "apartamente_vandute.arff";
        } else {
            trainPath = args[0];
            testPath = args[1];
        }

        Instances trainData = DataSource.read(trainPath), testData = DataSource
                .read(testPath);

        LinearRegression predictor = new LinearRegression();

        if (args.length == 3) {
            predictor.setOptions(args[2].split(" "));
        }

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

            for (int i = 0; i < n; ++i) {
                Instance t = testData.instance(i);
                double pred = predictor.classifyInstance(t), act = t
                        .value(m - 1);
                out.write(i+","+act+","+pred);
                out.newLine();

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

        /*Root Mean Squared Logarithmic Error*/
        System.out.println("LinearRegressionPredictor option: " + option);
        System.out.println("# of training data: " + trainData.numInstances());
        System.out.println("# of testing data: " + testData.numInstances());
        System.out.println("RMSLE on testing data: " + rmsle);
        System.out.println("RMSLE on testing data Zero: " + rmsleZero);
    }
}