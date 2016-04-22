package com.catalina;

import java.sql.*;

public class Main {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://85.122.23.50/stefania.baincescu";

    //  Database credentials
    static final String USER = "stefania.baincescu";
    static final String PASS = "Alex0974";


    public static void main(String[] args) {

        Main app = new Main();
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;

            /*sql = "SELECT id_proprietate, titlu, tip, pret, moneda, suprafata, numar_camere, descriere, \" +\n" +
                    "                \"evaluare, an_constructie, taguri, adresa, vandut FROM Proprietati Where vandut=1";*/

            /*sql = "SELECT id_proprietate, titlu, tip, pret, moneda, suprafata, numar_camere, descriere, \" +\n" +
                    "                \"evaluare, an_constructie, taguri, adresa, vandut FROM Proprietati where tip like 'Garsoniere'";*/

            /*sql = "SELECT id_proprietate, titlu, tip, pret, moneda, suprafata, numar_camere, descriere, \" +\n" +
                    "                \"evaluare, an_constructie, taguri, adresa, vandut FROM Proprietati where tip like 'Case/Vile'";*/

            sql = "SELECT id_proprietate, titlu, tip, pret, moneda, suprafata, numar_camere, descriere, \" +\n" +
                    "                \"evaluare, an_constructie, taguri, adresa, vandut FROM Proprietati where tip like 'Apartamente Decomandat'";

            /*sql = "SELECT id_proprietate, titlu, tip, pret, moneda, suprafata, numar_camere, descriere, \" +\n" +
                    "                \"evaluare, an_constructie, taguri, adresa, vandut FROM Proprietati";*/
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id_proprietate");
                String titlu = rs.getString("titlu");
                String tip = rs.getString("tip");
                float pret = rs.getFloat("pret");
                String moneda = rs.getString("moneda");
                String suprafata = rs.getString("suprafata");
                int nrCamere = rs.getInt("numar_camere");
                String descriere = rs.getString("descriere");
                String evaluare = rs.getString("evaluare");
                String an = rs.getString("an_constructie");
                String taguri = rs.getString("taguri");
                String adresa = rs.getString("adresa");
                int vandut = rs.getInt("vandut");

                Proprietati prop = new Proprietati(Integer.toString(id), titlu, tip, Float.toString(pret), moneda,
                        suprafata, Integer.toString(nrCamere), an, descriere, evaluare, taguri, adresa, vandut);
                System.out.println(prop.toString());
                System.out.println("*****************************************");
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

    }

}
