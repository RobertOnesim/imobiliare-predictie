package ro.uaic.info.data_mining.database_controller;

import javafx.util.Pair;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ro.uaic.info.data_mining.database_controller.DatabaseController;

import java.util.List;

/**
 * Created by dryflo on 4/24/2016.
 */
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("file:src\\main\\resources\\database_controller\\Beans.xml");

        DatabaseController obj = (DatabaseController) context.getBean("testing"); // TODO denumire mai sugestiva, in loc de testing

        List<Pair> results = obj.getPriceAndZone(new Statements().getStatementsById().get("priceAndZone")); // TODO mai sugestiv
        for (Pair current : results) { // TODO o denumire mai sugestiva decat current
            System.out.println("Price=" + current.getKey() + "   adress:" + current.getValue());
        }

    }
}
