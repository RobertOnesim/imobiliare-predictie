package com.company.database;


import java.util.Set;

/**
 * Created by Alfa on 04/05/16.
 */
public class ProprietatiQueryBuilder {

    static private final String tableName = "Proprietati";
    static private final String pk = "id_proprietate";
    static private final String estimatedPriceColumn = "pret_estimat";

    public static String buildSelectQuery(Set<String> attributes) {
        String query = "SELECT " + pk + ",";
        for(String attr : attributes) {
            query = query + " " + attr + ",";
        }
        // String trailing coma
        query = query.substring(0, query.length()-1);
        query = query + " FROM " + tableName + ";";
        return query;
    }

    public static String buildUpdateQuery(Integer pkValue, Float estimatedPrice) {
        String query = "UPDATE " + tableName + " SET ";
        query = query + estimatedPriceColumn + " = " + estimatedPrice;
        query = query + " WHERE " + pk + " = " + pkValue + ";";
        return query;
    }

    public static String getPk() {
        return pk;
    }
}
