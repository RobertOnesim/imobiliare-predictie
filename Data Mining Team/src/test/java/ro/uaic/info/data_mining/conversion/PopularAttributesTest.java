package ro.uaic.info.data_mining.conversion;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Ciubi on 28/04/16.
 */
public class PopularAttributesTest {

    @Test
    public void testGetNumericAttributesSorted() throws Exception {
        List<File> listFiles= new ArrayList<>();
        listFiles.add(new File("apartamente.json"));
        listFiles.add(new File("apartamente_vandute.json"));
        listFiles.add(new File("case_vandute.json"));
        listFiles.add(new File("case.json"));
        listFiles.add(new File("garsoniere.json"));
        listFiles.add(new File("garsoniere_vandute.json"));
        PopularAttributes.getNumericAttributesSorted(listFiles);

    }
}