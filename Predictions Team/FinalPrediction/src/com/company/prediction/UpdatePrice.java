package com.company.prediction;

import com.company.database.DatabaseUpdater;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class UpdatePrice {

    public Map<String, Float> generateCoefficientsAndPrice(LinearRegressionPredictor linearRegressionPredictor, DatabaseUpdater databaseUpdater) {

        Map<String, Float> returnStatement = new HashMap<>();
        Statement stmt = null;
        try {

            stmt = DatabaseUpdater.getConnection().createStatement();
            String sql;

            sql = "SELECT * FROM Proprietati where tip like 'Apartamente Decomandat'";

            ResultSet rs = stmt.executeQuery(sql);

            double [] coefficients = linearRegressionPredictor.getCoefficients();

            while (rs.next()) {
                //Retrieve by column name
                returnStatement.put("link",(float) 0.0);
                returnStatement.put("titlu",(float) 0.0);
                returnStatement.put("tip",(float) 0.0);
                returnStatement.put("pret",(float) coefficients[8]);
                returnStatement.put("moneda",(float) 0.0);
                returnStatement.put("pret_estimat",(float) 0.0);
                returnStatement.put("suprafata",(float) coefficients[1]);
                returnStatement.put("suprafata_construita",(float) 0.0);
                returnStatement.put("suprafata_teren",(float) coefficients[14]);
                returnStatement.put("numar_camere",(float) coefficients[0]);
                returnStatement.put("aer_conditionat",(float) coefficients[11]);
                returnStatement.put("centrala_termica",(float) coefficients[2]);
                returnStatement.put("front_stradal",(float) 0.0);
                returnStatement.put("geam_baie",(float) 0.0);
                returnStatement.put("geam_termopan",(float) coefficients[3]);
                returnStatement.put("negociabil",(float) coefficients[13]);
                returnStatement.put("descriere",(float) 0.0);
                returnStatement.put("evaluare",(float) 0.0);
                returnStatement.put("an_constructie",(float) 0.0);
                returnStatement.put("adresa", (float) 0.0);
                returnStatement.put("vandut", (float) coefficients[9]);

            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ignored) {
            }// nothing we can do
        }//end try

        return returnStatement;

    }
}
