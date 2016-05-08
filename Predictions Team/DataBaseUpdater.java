package com.company;

/**
 * Created by Diana on 07.05.2016.
 */
public class DataBaseUpdater {
    private LinearRegressionPredictor linearRegressionPredictor;
    private ConectareBD conectareBD;

    public DataBaseUpdater() {
        try {
            linearRegressionPredictor = new LinearRegressionPredictor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conectareBD = new ConectareBD();
    }

    public void main() {
        conectareBD.main(linearRegressionPredictor);

        /*for (int i = 0; i < linearRegressionPredictor.getCoefficients().length; ++i) {
            System.out.println("Coeficient " + i + "   " + linearRegressionPredictor.getCoefficients()[i]);
        }*/
    }


}
