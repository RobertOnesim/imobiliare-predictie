package ro.uaic.info.data_mining.database_controller;

import javafx.util.Pair;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Suhani on 27\04\16.
 */
public class DatabaseControllerTest {
    @Test
    public void getPriceAndZone() throws Exception {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("file:src\\main\\resources\\database_controller\\Beans.xml");

        DatabaseController databaseController = (DatabaseController) context.getBean("databaseController");

        List<Pair> resultedRowsFromQuery = databaseController.getPriceAndZone(new Statements().getStatementsByCategory().get("priceAndZone"));
        assertNotEquals(resultedRowsFromQuery,null);
    }

}