package com.company;

import java.sql.*;

public class ConectareBD {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://85.122.23.50/stefania.baincescu";

    //  Database credentials
    static final String USER = "stefania.baincescu";
    static final String PASS = "Alex0974";


    public void main(LinearRegressionPredictor linearRegressionPredictor) {

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

            /*sql = "SELECT * FROM Proprietati Where vandut=1";*/

            /*sql = "SELECT * FROM Proprietati where tip like 'Garsoniere'";*/

            /*sql = "SELECT * FROM Proprietati where tip like 'Case/Vile'";*/

            sql = "SELECT * FROM Proprietati where tip like 'Apartamente Decomandat'";

            /*sql = "SELECT * FROM Proprietati";*/
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            double [] coeficienti = linearRegressionPredictor.getCoefficients();
            while (rs.next()) {
                //Retrieve by column name
                Proprietati prop = new Proprietati(coeficienti);

                prop.setIdProprietate(Integer.toString(rs.getInt("id_proprietate")));
                prop.setLink(rs.getString("link"));
                prop.setTitlu(rs.getString("titlu"));
                prop.setTip(rs.getString("tip"));

                prop.setPret(rs.getFloat("pret"));
                prop.setMoneda(rs.getString("moneda"));
                prop.setPretEstimat(rs.getFloat("pret_estimat"));

                prop.setSuprafata(rs.getString("suprafata"));
                prop.setSuprafataTeren(rs.getString("suprafata_teren"));
                prop.setSuprafataConstruita(rs.getString("suprafata_construita"));

                prop.setNumarCamere(rs.getInt("numar_camere"));
                prop.setAerConditionat(rs.getInt("aer_conditionat"));
                prop.setCentralaTermica(rs.getInt("centrala_termica"));
                prop.setFrontStradal(rs.getInt("front_stradal"));
                prop.setGeamBaie(rs.getInt("geam_baie"));
                prop.setGeamTermopan(rs.getInt("geam_termopan"));
                prop.setNegociabil(rs.getInt("negociabil"));

                prop.setDescriere(rs.getString("descriere"));
                prop.setEvaluare(rs.getInt("evaluare"));
                prop.setAnConstructie(rs.getString("an_constructie"));

                prop.setAdresa(rs.getString("adresa"));
                prop.setVandut(rs.getInt("vandut"));

                System.out.println(prop.calculeazaPret()); //// TODO: 08.05.2016 @Ioana Insereaza in bd
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
