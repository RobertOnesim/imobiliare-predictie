package ro.uaic.info.data_mining.database_controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dryflo on 4/24/2016.
 */
public class Statements {
    private Map<String, String> statementsByCategory = new HashMap<>();

    public Statements() {
        parseXML();
    }

    private void parseXML() {
        try {

            File statements = new File("src\\main\\resources\\database_controller", "Statements.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(statements);
            doc.getDocumentElement().normalize();

            NodeList statementListFromXML = doc.getElementsByTagName("statement");


            for (int temp = 0; temp < statementListFromXML.getLength(); temp++) {
                Node statementFromXML = statementListFromXML.item(temp);

                if (statementFromXML.getNodeType() == Node.ELEMENT_NODE) {
                    Element statement = (Element) statementFromXML;
                    this.statementsByCategory.put(statement.getAttribute("category"), statement.getElementsByTagName("query").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getStatementsByCategory() {
        return this.statementsByCategory;
    }
}
