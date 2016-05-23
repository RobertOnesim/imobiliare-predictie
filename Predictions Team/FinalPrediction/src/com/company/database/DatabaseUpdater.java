package com.company.database;

import com.company.prediction.Properties;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alfa on 04/05/16.
 */
public class DatabaseUpdater {

    static private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static private final String DB_URL = "jdbc:mysql://85.122.23.50/stefania.baincescu";

    static private final String USER = "stefania.baincescu";
    static private final String PASS = "Alex0974";

    static private Connection connection;

    private String[] attributes = {"numar_camere","suprafata","centrala_termica","geam_termopan","link", "link", "link","link","pret","vandut","link","aer_conditionat","geam_baie","negociabil","suprafata_teren","evaluare"};

    public DatabaseUpdater() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Class loading error: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateEstimatedPriceTable(Map<String, Float> coefficients) throws SQLException {
        System.out.println(coefficients.keySet());
        String query = com.company.database.ProprietatiQueryBuilder.buildSelectQuery(coefficients.keySet());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        Integer columnCount = rs.getMetaData().getColumnCount();
        while(rs.next()) {
            // Map should be sent to the function that calculates the estimated price
            Map<String, Object> databaseValues = new HashMap<>();
            // getColumnName starts counting from 1
            for(Integer count = 1; count <= columnCount; ++count) {
                String attribute = rs.getMetaData().getColumnName(count);
                if(!attribute.equals(com.company.database.ProprietatiQueryBuilder.getPk())) {
                    databaseValues.put(attribute, rs.getObject(attribute));
                }
            }

            List<Double> coefficientsProperties = new ArrayList<>();

            for (String attribute : attributes) {
                coefficientsProperties.add(Double.valueOf(coefficients.get(attribute)));
            }

            Properties properties = new Properties(coefficientsProperties);
            setAllProperties(properties, rs);

            double estimatedPrice = properties.calculeazaPret();
            double priceDifference = estimatedPrice - properties.getPret();

            System.out.println("Id proprietate: "+ properties.getIdProprietate());
            System.out.println("Pretul actual: " + estimatedPrice);
            System.out.println("Diferenta pret: " + priceDifference);

            /*if(PriceDifference < 0 ) {
                 estimatedPrice = (double) (properties.getPret() - (((double) properties.getPret() * Math.abs(PriceDifference)) / 100));
            }
            else {
                 estimatedPrice = (double) (properties.getPret() + (((double) properties.getPret() * Math.abs(PriceDifference)) / 100));
            }*/

//            System.out.println("*****************************************");
            //DONE -Robert


            Integer primaryKey = rs.getInt(com.company.database.ProprietatiQueryBuilder.getPk());
            // Float estimatedProce = something.getEstimatedPrime(databaseValues);
            updateEstimatedPriceRow(primaryKey, Float.valueOf((float) estimatedPrice));
        }

    }

    public void updateEstimatedPriceRow(Integer pk, Float estimatedPrice) throws SQLException {
        String query = com.company.database.ProprietatiQueryBuilder.buildUpdateQuery(pk, estimatedPrice);
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    private void setAllProperties(Properties properties,ResultSet rs) throws SQLException {

        properties.setIdProprietate(Integer.toString(rs.getInt("id_proprietate")));
        properties.setLink(rs.getString("link"));
        properties.setTitlu(rs.getString("titlu"));
        properties.setTip(rs.getString("tip"));

        properties.setPret(rs.getFloat("pret"));
        properties.setMoneda(rs.getString("moneda"));
        properties.setPretEstimat(rs.getFloat("pret_estimat"));

        properties.setSuprafata(rs.getString("suprafata"));
        properties.setSuprafataTeren(rs.getString("suprafata_teren"));
        properties.setSuprafataConstruita(rs.getString("suprafata_construita"));

        properties.setNumarCamere(rs.getInt("numar_camere"));
        properties.setAerConditionat(rs.getInt("aer_conditionat"));
        properties.setCentralaTermica(rs.getInt("centrala_termica"));
        properties.setFrontStradal(rs.getInt("front_stradal"));
        properties.setGeamBaie(rs.getInt("geam_baie"));
        properties.setGeamTermopan(rs.getInt("geam_termopan"));
        properties.setNegociabil(rs.getInt("negociabil"));

        properties.setDescriere(rs.getString("descriere"));
        properties.setEvaluare(rs.getInt("evaluare"));
        properties.setAnConstructie(rs.getString("an_constructie"));

        properties.setAdresa(rs.getString("adresa"));
        properties.setVandut(rs.getInt("vandut"));

    }
    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            //
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
