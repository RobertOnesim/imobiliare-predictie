package ro.uaic.info.data_mining.conversion;

import javafx.util.Pair;
import ro.uaic.info.data_mining.conversion.exceptions.UnconsistentFormatException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Ciubi on 28/04/16.
 */
public class PopularAttributes {

    public static List<String> getNumericAttributesSorted(List<File> jsonFiles) throws IOException, UnconsistentFormatException {

         JsonArff[] interpretedJsons = new JsonArff[jsonFiles.size()];
        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < jsonFiles.size(); ++i) {
            JsonArff converted = new JsonArff(jsonFiles.get(i),null);
            interpretedJsons[i] = converted;
        }

        for (int i = 0; i < interpretedJsons.length; i++) {

            List<Pair<String, Integer>> list = interpretedJsons[i].getNumericAttributesWithPopularity();
            for (int j = 0; j < list.size(); j++) {
                Integer popularity = list.get(j).getValue();
                if (map.containsKey(list.get(j).getKey())) {
                    popularity = map.get(list.get(j).getKey());
                    popularity += list.get(j).getValue();

                }
                map.put(list.get(j).getKey(), popularity);
            }


        }

        for (String s : map.keySet()) {
            System.out.println(s + " " + map.get(s));

        }


        return null;

    }







}
