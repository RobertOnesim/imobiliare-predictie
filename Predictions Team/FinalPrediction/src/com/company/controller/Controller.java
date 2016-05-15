package com.company.controller;

import com.company.database.DatabaseUpdater;
import com.company.prediction.LinearRegressionPredictor;
import com.company.prediction.UpdatePrice;

import java.sql.SQLException;

/**
 * Created by Diana on 07.05.2016.
 */
public class Controller {
    private LinearRegressionPredictor linearRegressionPredictor;
    private UpdatePrice updatePrice;
    private DatabaseUpdater databaseUpdater;

    public Controller(DatabaseUpdater databaseUpdater) {
        try {
            linearRegressionPredictor = new LinearRegressionPredictor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        updatePrice = new UpdatePrice();
        this.databaseUpdater=databaseUpdater;
    }

    public void startApplication() throws SQLException {
        databaseUpdater.updateEstimatedPriceTable(updatePrice.generateCoefficientsAndPrice(linearRegressionPredictor,databaseUpdater));
    }


}
