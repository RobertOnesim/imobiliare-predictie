package ro.uaic.info.data_mining.conversion; /**
 * Created by Alex on 4/20/2016.
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import javafx.util.Pair;
import ro.uaic.info.data_mining.conversion.exceptions.UnconsistentFormatException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class JsonArff {
    private File file;
    private List<List<String>> objectsData = new ArrayList<>();
    private Map<String, Pair<Integer, ArffTypes>> attributes = new HashMap<>();
    private Map<Integer, ArffTypes> attributeType = new HashMap<>();
    private int numberOfAttributes;

    JsonArff(File jsonFile) {
        this.file = jsonFile;
    }

    public static void main(String[] argv) {
        JsonArff jsonArff = new JsonArff(new File("C:\\Users\\Ovidiu\\Documents\\Facultate\\imobiliare\\imobiliare-predictie\\Data Mining Team\\src\\main\\resources\\conversions\\apartamente_vandute.json"));
        try {
            jsonArff.writeArff(new File("C:\\Users\\Ovidiu\\Documents\\Facultate\\imobiliare\\imobiliare-predictie\\Data Mining Team\\src\\main\\resources\\conversions\\apartamente_vandute.arff"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnconsistentFormatException e) {
            e.printStackTrace();
        }
    }

    public void writeArff(File outputFile) throws IOException, UnconsistentFormatException {
        numberOfAttributes = 1;
        attributes.put("URL", new Pair<>(0, ArffTypes.STRING));

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
            fillUntilPosition(strings, this.numberOfAttributes);

        }
        Set<Map.Entry<String, Pair<Integer, ArffTypes>>> set = attributes.entrySet();
        List<Map.Entry<String, Pair<Integer, ArffTypes>>> list = new ArrayList<>(set);
        Collections.sort(list, new Comparator<Map.Entry<String, Pair<Integer, ArffTypes>>>() {
            @Override
            public int compare(Map.Entry<String, Pair<Integer, ArffTypes>> o1, Map.Entry<String, Pair<Integer, ArffTypes>> o2) {
                return -(o2.getValue().getKey()).compareTo(o1.getValue().getKey());
            }

        });
        PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
        writer.println("@RELATION random");
        for (Map.Entry<String, Pair<Integer, ArffTypes>> entry : list) {
            String attributeLine = "@ATTRIBUTE " + entry.getKey() + "  " + (entry.getValue().getValue() == ArffTypes.NUMERIC ? "NUMERIC" : "string");
            writer.println(attributeLine);
        }
        writer.println("@DATA");
        for (int i = 0; i < objectsData.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < objectsData.get(i).size(); j++) {
                stringBuilder.append(objectsData.get(i).get(j)).append(" ");
            }
            String dataLine = stringBuilder.toString();
            writer.println(dataLine);
        }
        writer.close();


    }

    private Pair<Integer, ArffTypes> getAttributePosition(ArffTypes type, String attribute) throws UnconsistentFormatException {
        if (attributes.containsKey(attribute)) {

            Pair<Integer, ArffTypes> attributeValue = attributes.get(attribute);
            if (attributeValue.getValue() != type)
                throw new UnconsistentFormatException("Unconsistent types");
            return attributeValue;

        }
        Pair<Integer, ArffTypes> attributePosition = new Pair<Integer, ArffTypes>(numberOfAttributes++, type);
        attributes.put(attribute, attributePosition);
        attributeType.put(numberOfAttributes - 1, type);
        return attributePosition;
    }

    private List<String> fillUntilPosition(List<String> objectData, int position) {
        for (int i = objectData.size(); i < position; i++) {
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
                    objectData.add("1");
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
                Pair<Integer, ArffTypes> positionAttribute = getAttributePosition(ArffTypes.STRING, nameOfObject);
                objectData = fillUntilPosition(objectData, positionAttribute.getKey());
                objectData.add("\"" + node.asText() + "\"");
                break;
            }
            case NUMBER: {
                Pair<Integer, ArffTypes> positionAttribute = getAttributePosition(ArffTypes.NUMERIC, nameOfObject);
                objectData = fillUntilPosition(objectData, positionAttribute.getKey());
                objectData.add(node.asText());
                break;
            }


        }


        return objectData;
    }

    enum ArffTypes {
        NUMERIC,
        STRING
    }
}
