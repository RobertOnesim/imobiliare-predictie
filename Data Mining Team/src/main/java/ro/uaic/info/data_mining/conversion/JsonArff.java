package ro.uaic.info.data_mining.conversion; /**
 * Created by Alexandru Ciubotariu on 4/20/2016.
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import javafx.util.Pair;
import ro.uaic.info.data_mining.conversion.exceptions.UnconsistentFormatException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;


public class JsonArff {
    private File file;
    private List<List<String>> objectsData = new ArrayList<>();
    private Map<String, Pair<Integer, ArffTypes>> attributes = new HashMap<>();

    ;
    private int numberOfAttributes;
    private Map<Integer, ArffTypes> attributeType = new HashMap<>();
    JsonArff(File Jsonfile) {
        this.file = Jsonfile;
    }

    public static void main(String[] argv) {
        JsonArff jsonArff = new JsonArff(new File("case_vandute.json"));
        try {
            jsonArff.writeArff(new File("case_vandute.arff"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnconsistentFormatException e) {
            e.printStackTrace();
        }
    }

    public void writeArff(File outputFile) throws IOException, UnconsistentFormatException {
        numberOfAttributes = 1;
        attributes.put("URL", new Pair<Integer, ArffTypes>(0, ArffTypes.STRING));


        ObjectMapper mapper = new ObjectMapper();
        try {
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
            Set<Map.Entry<String, Pair<Integer, ArffTypes>>> set = attributes.entrySet();
            List<Map.Entry<String, Pair<Integer, ArffTypes>>> list = new ArrayList<Map.Entry<String, Pair<Integer, ArffTypes>>>(set);
            Collections.sort(list, new Comparator<Map.Entry<String, Pair<Integer, ArffTypes>>>() {
                @Override
                public int compare(Map.Entry<String, Pair<Integer, ArffTypes>> o1, Map.Entry<String, Pair<Integer, ArffTypes>> o2) {
                    return -(o2.getValue().getKey()).compareTo(o1.getValue().getKey());
                }

            });
            for (int i = 0; i < objectsData.size(); i++) {
                System.out.println(objectsData.get(i).get(4));
            }
            PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
            writer.println("@RELATION random");
            for (Map.Entry<String, Pair<Integer, ArffTypes>> entry : list) {
                if (entry.getValue().getValue() != ArffTypes.NUMERIC)
                    continue;
                ;
                String attributeLine = "@ATTRIBUTE " + entry.getKey().replace(' ', '-') + "  " + (entry.getValue().getValue() == ArffTypes.NUMERIC ? "NUMERIC" : "string");
                writer.println(attributeLine);
            }
            writer.println("@DATA");
            for (int i = 0; i < objectsData.size(); i++) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < objectsData.get(i).size(); j++) {
                    if (attributeType.get(j) != ArffTypes.NUMERIC)
                        continue;
                    stringBuilder.append(objectsData.get(i).get(j) + " ");
                }
                String dataLine = stringBuilder.toString();
                writer.println(dataLine);
            }
            writer.close();


        } catch (IOException e) {
            throw e;
        }

    }

    private Pair<Integer, ArffTypes> getAttributePosition(ArffTypes type, String attribute) throws UnconsistentFormatException {
        if (attributes.containsKey(attribute)) {

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
