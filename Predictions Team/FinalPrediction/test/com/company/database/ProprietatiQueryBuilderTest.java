package com.company.database;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Robert Onesim on 5/16/2016.
 */
public class ProprietatiQueryBuilderTest extends TestCase {

    public void testBuildSelectQuery() throws Exception {
        Set<String>attribute = new HashSet<>();
        ProprietatiQueryBuilder proprietatiQueryBuilder = new ProprietatiQueryBuilder();
        attribute.add("pret_estimat");
        String result = ProprietatiQueryBuilder.buildSelectQuery(attribute);
        assertEquals("SELECT id_proprietate, pret_estimat FROM Proprietati;",result);
    }

    public void testBuildUpdateQuery() throws Exception {
        double ans = 0.5;
        ProprietatiQueryBuilder proprietatiQueryBuilder = new ProprietatiQueryBuilder();
        String result = ProprietatiQueryBuilder.buildUpdateQuery(12, (float) ans);
        assertEquals("UPDATE Proprietati SET pret_estimat = 0.5 WHERE id_proprietate = 12;",result);
    }
}