package ro.uaic.info.data_mining.conversion; /**
 * Created by Alexandru Ciubotariu on 4/20/2016.
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import javafx.util.Pair;
import ro.uaic.info.data_mining.aggregation.Construction;
import ro.uaic.info.data_mining.aggregation.LocationCoefficientCalculator;
import ro.uaic.info.data_mining.aggregation.LocationCoefficientRequest;
import ro.uaic.info.data_mining.conversion.exceptions.UnconsistentFormatException;
import ro.uaic.info.data_mining.database_controller.DatabaseConnection;
import ro.uaic.info.data_mining.database_controller.Statements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;


public class JsonArff {
    List<Map.Entry<String, Pair<Integer, ArffTypes>>> listAttributes;
    private File file;
    private List<List<String>> objectsData = new ArrayList<>();
    private Map<String, Pair<Integer, ArffTypes>> attributes;
    ;
    private int numberOfAttributes;
    private Map<Integer, ArffTypes> attributeType;
    private Map<String, Integer> attributePopularity;

    JsonArff(File Jsonfile) throws IOException, UnconsistentFormatException {
        this.file = Jsonfile;
        attributes = new HashMap<>();
        attributeType = new HashMap<>();
        objectsData = new ArrayList<>();
        attributePopularity = new HashMap<>();
        numberOfAttributes = 1;


        attributes.put("URL", new Pair<Integer, ArffTypes>(0, ArffTypes.STRING));
        attributePopularity.put("URL", 1);


        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(this.file);
        Iterator<String> iteratorURLObject = rootNode.fieldNames();
        while (iteratorURLObject.hasNext()) {
            String URLPath = iteratorURLObject.next();
            JsonNode imobilObject = rootNode.get(URLPath);
            List<String> objectData = new ArrayList<>();
            objectData.add(URLPath);
            try {
                objectData = parseObject(imobilObject, objectData, "");
                objectsData.add(objectData);
            } catch (UnconsistentFormatException e) {
                throw e;
            }
        }
        for (List<String> strings : objectsData) {
            fillUntilPosition(strings, this.numberOfAttributes - 1);

        }

        attributes.put("evaluare", new Pair<Integer, ArffTypes>(attributes.size(), ArffTypes.NUMERIC));
        attributeType.put(attributes.size()-1, ArffTypes.NUMERIC);
        attributePopularity.put("evaluare", 1);


        int pozitieId = attributes.get("detalii-ID proprietate:").getKey();
        for (List<String> strings : objectsData) {
            int number = Integer.parseInt(strings.get(pozitieId).substring(2, strings.get(pozitieId).length() - 1));
            DatabaseConnection connection = DatabaseConnection.getInstance();

            try {
                CallableStatement cStatement = connection.getConnection().prepareCall(
                        new Statements().getStatementsByCategory().get("TextMiningEval"));

                cStatement.setInt(1, number);
                ResultSet queryResult = cStatement.executeQuery();

                if (!queryResult.next())
                    throw new UnconsistentFormatException("Eval not found");
                strings.add(String.valueOf(queryResult.getInt(1)));


            } catch (SQLException e) {
                throw new UnconsistentFormatException(e.getMessage());
            }

        }


        Set<Map.Entry<String, Pair<Integer, ArffTypes>>> set = attributes.entrySet();
        listAttributes = new ArrayList<Map.Entry<String, Pair<Integer, ArffTypes>>>(set);
        Collections.sort(listAttributes, new Comparator<Map.Entry<String, Pair<Integer, ArffTypes>>>() {
            @Override
            public int compare(Map.Entry<String, Pair<Integer, ArffTypes>> o1, Map.Entry<String, Pair<Integer, ArffTypes>> o2) {
                return -(o2.getValue().getKey()).compareTo(o1.getValue().getKey());
            }

        });
    }

    public static void main(String[] argv) {

        try {
            JsonArff jsonArff = new JsonArff(new File("garsoniere_vandute.json"));
            jsonArff.writeArff(new File("garsoniere_vandute.arff"));
            System.out.println("am terminat cu garsonierele vandute ");
            System.out.flush();

            jsonArff = new JsonArff(new File("case.json"));
            jsonArff.writeArff(new File("case.arff"));
            System.out.println("am terminat cu casele");

            jsonArff = new JsonArff(new File("apartamente.json"));
            jsonArff.writeArff(new File("apartamente.arff"));
            System.out.println("am terminat cu apartamentele");

            jsonArff = new JsonArff(new File("apartamente_vandute.json"));
            jsonArff.writeArff(new File("apartamente_vandute.arff"));
            System.out.println("am terminat cu apartamentele vandute");

            jsonArff = new JsonArff(new File("case_vandute.json"));
            jsonArff.writeArff(new File("case_vandute.arff"));
            System.out.println("am terminat cu casele vandute");

            jsonArff = new JsonArff(new File("garsoniere.json"));
            jsonArff.writeArff(new File("garsoniere.arff"));
            System.out.println("am terminat cu garsonierele");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnconsistentFormatException e) {
            e.printStackTrace();
        }
    }

    public List<Pair<String, Integer>> getNumericAttributesWithPopularity() {
        List<Pair<String, Integer>> list = new ArrayList<>();
        for (Map.Entry<String, Pair<Integer, ArffTypes>> listAttribute : listAttributes) {
            if (listAttribute.getValue().getValue() == ArffTypes.NUMERIC) {
                ;
                list.add(new Pair<>(listAttribute.getKey(), attributePopularity.get(listAttribute.getKey())));
            }
        }
        return list;

    }

    public void writeArff(File outputFile) throws IOException, UnconsistentFormatException {


        PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
        writer.println("@RELATION random");
        int posPret = -1;
        int poz = -1;
        for (Map.Entry<String, Pair<Integer, ArffTypes>> entry : listAttributes) {
            poz++;
            if (entry.getValue().getValue() != ArffTypes.NUMERIC)
                continue;
            if (entry.getKey().startsWith("pret")) {
                posPret = poz;
                continue;
            }
            String attributeLine = "@ATTRIBUTE " + entry.getKey().replace(' ', '-') + "  " + (entry.getValue().getValue() == ArffTypes.NUMERIC ? "NUMERIC" : "string");
            writer.println(attributeLine);

        }
        int indexZona = -1;
        int pozitieZona = 0;
        for (Map.Entry<String, Pair<Integer, ArffTypes>> entry : listAttributes) {

            if (entry.getKey().startsWith("detalii-Zona")) {
                indexZona = pozitieZona;
                break;
            }
            pozitieZona++;


        }
        List<Construction> constuctionList;
        LocationCoefficientCalculator coefficientCalculator = null;
        if (indexZona != -1) {
            writer.println("@ATTRIBUTE coefficient");
            constuctionList = new ArrayList<>();
            for (List<String> strings : objectsData) {
                double price = Double.parseDouble(strings.get(posPret));
                String zone = strings.get(indexZona);
                Construction constructie = new Construction();
                constructie.setPrice(price).setZone(zone);
                constuctionList.add(constructie);


            }
            coefficientCalculator = new LocationCoefficientCalculator(constuctionList, Construction.Parameter.Zone);


        }


        if (posPret != -1) {
            Map.Entry<String, Pair<Integer, ArffTypes>> entry = listAttributes.get(posPret);
            String attributeLine = "@ATTRIBUTE " + entry.getKey().replace(' ', '-') + "  " + (entry.getValue().getValue() == ArffTypes.NUMERIC ? "NUMERIC" : "string");
            writer.println(attributeLine);
        }


        writer.println("@DATA");
        for (int i = 0; i < objectsData.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < objectsData.get(i).size(); j++) {
                if (attributeType.get(j) != ArffTypes.NUMERIC || j == posPret)
                    continue;
                stringBuilder.append(objectsData.get(i).get(j) + " ");
            }

            if (coefficientCalculator != null) {
                double coefficient = coefficientCalculator.getZoneCoefficient(new LocationCoefficientRequest().withLocation(objectsData.get(i).get(indexZona)));
                stringBuilder.append(coefficient + " ");
            }
            if (posPret != -1)
                stringBuilder.append(objectsData.get(i).get(posPret) + " ");
            String dataLine = stringBuilder.toString();
            writer.println(dataLine);
        }
        writer.close();


    }


    private Pair<Integer, ArffTypes> getAttributePosition(ArffTypes type, String attribute) throws UnconsistentFormatException {
        if (attributes.containsKey(attribute)) {

            Integer popularity = attributePopularity.get(attribute);
            attributePopularity.put(attribute, ++popularity);
            Pair<Integer, ArffTypes> attributeValue = attributes.get(attribute);
            if (attributeValue.getValue() != type)
                if (type == ArffTypes.NUMERIC) {// transform from Numeric to string{
                    attributes.put(attribute, new Pair<Integer, ArffTypes>(attributeValue.getKey(), ArffTypes.STRING));
                    attributeType.put(attributeValue.getKey(), ArffTypes.STRING);
                    for (int i = 0; i < this.objectsData.size(); i++) {
                        if (attributeValue.getKey() < objectsData.get(i).size()) {
                            String strToString = "\"" + objectsData.get(i).get(attributeValue.getKey()) + "\"";
                            objectsData.get(i).set(attributeValue.getKey(), strToString);
                        }
                    }
                } else {

                    attributes.put(attribute, new Pair<Integer, ArffTypes>(attributeValue.getKey(), ArffTypes.STRING));
                    attributeType.put(attributeValue.getKey(), ArffTypes.STRING);
                }
            return attributeValue;

        }
        Pair<Integer, ArffTypes> attributePosition = new Pair<Integer, ArffTypes>(numberOfAttributes++, type);
        attributes.put(attribute, attributePosition);
        attributeType.put(numberOfAttributes - 1, type);
        attributePopularity.put(attribute, 1);
        return attributePosition;
    }


    private List<String> fillUntilPosition(List<String> objectData, int position) {
        for (int i = objectData.size(); i <= position; i++) {
            if (attributeType.get(i) == ArffTypes.NUMERIC) {
                objectData.add("0");
            } else { //empty string
                objectData.add("\"\"");
            }
        }
        return objectData;
    }

    private List<String> parseObject(JsonNode node, List<String> objectData, String nameOfObject) throws UnconsistentFormatException {
        JsonNodeType type = node.getNodeType();
        switch (node.getNodeType()) {
            case ARRAY:
                for (JsonNode jsonNode : node) {
                    if (jsonNode.getNodeType() != JsonNodeType.STRING)
                        throw new UnconsistentFormatException("Arrays can only be arrays of strings");
                    Pair<Integer, ArffTypes> positionAttribute = getAttributePosition(ArffTypes.NUMERIC, nameOfObject + (nameOfObject == "" ? "" : " ") + jsonNode.asText());
                    objectData = fillUntilPosition(objectData, positionAttribute.getKey());
                    objectData.set(positionAttribute.getKey(), "1");
                }
                break;
            case OBJECT:
                Iterator<String> iteratorURLObject = node.fieldNames();
                while (iteratorURLObject.hasNext()) {
                    String nameOfField = iteratorURLObject.next();
                    objectData = parseObject(node.get(nameOfField), objectData, nameOfObject + (nameOfObject == "" ? "" : "-") + nameOfField);
                }
                break;
            case STRING: {

                ArffTypes typeOfString;
                String toAdd;
                if (node.asText().compareToIgnoreCase("Da") == 0 || node.asText().compareToIgnoreCase("Nu") == 0) {
                    typeOfString = ArffTypes.NUMERIC;
                    if (node.asText().compareToIgnoreCase("Da") == 0) {
                        toAdd = "1";
                    } else
                        toAdd = "0";
                    Pair<Integer, ArffTypes> positionAttribute = getAttributePosition(ArffTypes.NUMERIC, nameOfObject);
                    objectData = fillUntilPosition(objectData, positionAttribute.getKey());
                    objectData.set(positionAttribute.getKey(), toAdd);
                } else {

                    try {
                        Number number = NumberFormat.getNumberInstance(Locale.forLanguageTag("ro")).parse(node.asText());
                        typeOfString = ArffTypes.NUMERIC;
                        Pair<Integer, ArffTypes> positionAttribute = getAttributePosition(ArffTypes.NUMERIC, nameOfObject);
                        objectData = fillUntilPosition(objectData, positionAttribute.getKey());
                        objectData.set(positionAttribute.getKey(), number.toString());
                    } catch (ParseException e) {
                        Pair<Integer, ArffTypes> positionAttribute = getAttributePosition(ArffTypes.STRING, nameOfObject);
                        objectData = fillUntilPosition(objectData, positionAttribute.getKey());
                        objectData.set(positionAttribute.getKey(), "\"" + node.asText() + "\"");
                        objectData.set(positionAttribute.getKey(), "\"" + node.asText() + "\"");
                    }
                }
                break;
            }
            case NUMBER: {
                Pair<Integer, ArffTypes> positionAttribute = getAttributePosition(ArffTypes.NUMERIC, nameOfObject);
                objectData = fillUntilPosition(objectData, positionAttribute.getKey());
                objectData.set(positionAttribute.getKey(), node.asText());
                break;
            }


        }


        return objectData;
    }

    enum ArffTypes {NUMERIC, STRING}
}