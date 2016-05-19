/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mada
 */
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;
import java.util.Set;

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
            String sql= "Delete from Proprietati";
            //stmt.executeUpdate(sql);

            /////

            JSONParser parser = new JSONParser();
            try {

                Object obj = parser.parse(new FileReader("apartamente.json"));

                JSONObject jsObject = (JSONObject) obj;
                Set<String> keys = jsObject.keySet();
                Iterator<String> it = keys.iterator();
               /// int ok  =1;
                while (it.hasNext()) {
                  ///  ok=0;
                    String proprietate = it.next();
                    System.out.println(proprietate);

                    Proprietati p = new Proprietati();
                    p.setLink(proprietate);

                    JSONObject apartament = (JSONObject) jsObject.get(proprietate);
                    JSONObject detalii = (JSONObject) apartament.get("detalii");
                    JSONObject dotari = (JSONObject) apartament.get("dotari");
                    JSONObject pret = (JSONObject) apartament.get("pret");

                    p.setPret((String) pret.get("valoare"));
                    p.setMoneda((String) pret.get("moneda"));

                    if ( ((String)apartament.get("vandut")).equalsIgnoreCase("Da") ) p.setVandut(1);
                    else p.setVandut(0);

                    p.setTitlu((String) apartament.get("titlu"));
                    p.setDescriere((String) apartament.get("descriere"));
                    p.setIdProprietate( ((String) detalii.get("ID proprietate:")).substring(1) );

                    p.setNumarCamere((String) detalii.get("Nr camere:"));

                    p.setSuprafata((String) detalii.get("Suprafata:"));
                    p.setSuprafataConstruita((String) detalii.get("Suprafata construita:"));
                    p.setSuprafataTeren((String) detalii.get("Suprafata teren:"));


                    String t= (String)dotari.get("Tip apartament:");
                    if(t==null) p.setTip((String) detalii.get("Tip proprietate:"));
                    else p.setTip(detalii.get("Tip proprietate:") + " " + dotari.get("Tip apartament:"));

                    t=(String)detalii.get("Etaj:");
                    if(t==null) p.setAdresa((String) detalii.get("Zona:"));
                    else p.setAdresa(detalii.get("Zona:") + " " + detalii.get("Etaj:"));

                    p.setAnConstructie((String) dotari.get("An constructie:"));
                    p.setAerConditionat((String) dotari.get("Aer contidionat:"));
                    p.setCentralaTermica((String) dotari.get("Centrala termica:"));
                    if(p.getTip().contains("Case/Vile"))
                        p.setFrontStradal((String) dotari.get("Front stradal:"));

                    p.setGeamBaie((String) dotari.get("Geam la baie:"));
                    p.setGeamTermopan((String) dotari.get("Geam termopan:"));

                    sql=p.insert();
                    stmt.executeUpdate(sql);

                    if(p.updateSuprafata()!=null) stmt.execute(p.updateSuprafata());
                    if(p.updateSuprafataTeren()!=null) stmt.execute(p.updateSuprafataTeren());
                    if(p.updateSuprafataConstruita()!=null) stmt.execute(p.updateSuprafataConstruita());
                    if(p.updateNumarCamere()!=null) stmt.execute(p.updateNumarCamere());
                    if(p.updateAnConstuctie()!=null) stmt.execute(p.updateAnConstuctie());

                    if(p.updateAerConditionat()!=null) stmt.execute(p.updateAerConditionat());
                    if(p.updateCentralaTermica()!=null) stmt.execute(p.updateCentralaTermica());
                    if(p.updateGeamTermopan()!=null) stmt.execute(p.updateGeamTermopan());
                    if(p.updateGeamBaie()!=null) stmt.execute(p.updateGeamBaie());
                    if(p.updateNegociabil()!=null) stmt.execute(p.updateNegociabil());

                    if(p.getTip().contains("Case/Vile"))
                        if(p.updateFrontStradal()!=null) stmt.execute(p.updateFrontStradal());


                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

//////

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
