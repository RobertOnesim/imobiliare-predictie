package com.company;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws SQLException{
        DatabaseUpdater databaseUpdater = new DatabaseUpdater();

        Map<String, Float> coefficients = new HashMap<>();
        coefficients.put("numar_camere", (float) 14.0);
        coefficients.put("aer_conditionat", (float) 2);
        

        databaseUpdater.updateEstimatedPriceTable(coefficients);

        databaseUpdater.closeConnection();
    }
}
