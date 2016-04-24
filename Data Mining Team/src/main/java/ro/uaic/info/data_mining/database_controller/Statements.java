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
    private Map<String, String> statementsById = new HashMap<>(); //TODO cine e id-ul statementului? vad doar category

    protected Statements() {
        parseXML();
    }

    private void parseXML() {
        try {

            //TODO fXmlFile si Statements.xml pot avea denumiri mai sugestiva
            File fXmlFile = new File("src\\main\\resources\\database_controller", "Statements.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("statement"); // TODO statement poate avea o denumire mai sugestiva

            // TODO felicitari pentru utilizarea spring. spring ajuta la dependency injection, un topic foarte important
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element statement = (Element) nNode;
                    this.statementsById.put(statement.getAttribute("category"), statement.getElementsByTagName("query").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Map<String, String> getStatementsById() {
        return this.statementsById;
    }
}
