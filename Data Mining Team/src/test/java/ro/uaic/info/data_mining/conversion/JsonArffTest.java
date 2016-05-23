package ro.uaic.info.data_mining.conversion;

import com.fasterxml.jackson.core.JsonFactory;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * TODO Add Documentation. Creation date: 5/23/2016.
 */
public class JsonArffTest {

    @Test
    public void testGenerateArff() throws Exception {
        //File file = new File("apartamente_vechi.arff");
        BufferedReader br = new BufferedReader(new FileReader("garsoniere_vandute_vechi.arff"));
        List<String> listToIgnore= new ArrayList<String>();
        listToIgnore.add("foto");
        JsonArff jsonToArff = new JsonArff(new File("garsoniere_vandute.json"),listToIgnore);
        jsonToArff.writeArff(new File("garsoniere_vandute.arff"));
        BufferedReader newArff = new BufferedReader(new FileReader("garsoniere_vandute.arff"));
        String oldLine = "";
        String newLine = "";
        while ((newLine = newArff.readLine()) != null || (oldLine = br.readLine()) != null) {
            if ((newLine == null && oldLine != null) || (oldLine == null && newLine != null) || !newLine.equals(oldLine)) {
                throw new Exception("not equal arff");
            }

        }
    }
}