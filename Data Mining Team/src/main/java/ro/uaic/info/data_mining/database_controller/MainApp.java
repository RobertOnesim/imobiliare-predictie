package ro.uaic.info.data_mining.database_controller;

import javafx.util.Pair;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by dryflo on 4/24/2016.
 */
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("file:src\\main\\resources\\database_controller\\Beans.xml");

        DatabaseController databaseController = (DatabaseController) context.getBean("databaseController");

        List<Pair> resultedRowsFromQuery = databaseController.getPriceAndZone(new Statements().getStatementsByCategory().get("priceAndZone"));
        for (Pair currentRowFromInterrogationResult : resultedRowsFromQuery) {
            System.out.println("Price=" + currentRowFromInterrogationResult.getKey() + "   adress:" + currentRowFromInterrogationResult.getValue());
        }

    }
}
