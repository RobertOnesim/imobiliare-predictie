package com.company;

import java.sql.*;
import java.util.HashMap;
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
        String query = ProprietatiQueryBuilder.buildSelectQuery(coefficients.keySet());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        Integer columnCount = rs.getMetaData().getColumnCount();
        while(rs.next()) {
            // Map should be sent to the function that calculates the estimated price
            Map<String, Object> databaseValues = new HashMap<>();
            // getColumnName starts counting from 1
            for(Integer count = 1; count <= columnCount; ++count) {
                String attribute = rs.getMetaData().getColumnName(count);
                if(!attribute.equals(ProprietatiQueryBuilder.getPk())) {
                    databaseValues.put(attribute, rs.getObject(attribute));
                }
            }

            /*TODO replace here with the function that calculates the estimate price
            the function should be able to calculate the price given a map of attributes and their values from the
            database
            */
            Integer primaryKey = rs.getInt(ProprietatiQueryBuilder.getPk());
            // Float estimatedProce = something.getEstimatedPrime(databaseValues);
            Float estimatedPrice = (float) 4;
            updateEstimatedPriceRow(primaryKey,estimatedPrice);
        }

    }

    public void updateEstimatedPriceRow(Integer pk, Float estimatedPrice) throws SQLException {
        String query = ProprietatiQueryBuilder.buildUpdateQuery(pk, estimatedPrice);
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            //
        }
    }

}
