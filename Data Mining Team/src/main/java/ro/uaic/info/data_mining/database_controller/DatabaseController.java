package ro.uaic.info.data_mining.database_controller;

import javafx.util.Pair;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dryflo on 4/24/2016.
 */
@Controller
public class DatabaseController {

    public List<Pair> getPriceAndZone(String queryStatement) {

        Statement statement;
        List<Pair> resultedRowsFromQuery = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();

            statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(queryStatement);

            String lastKnownCurrency = "";

            while (queryResult.next()) {
                double price = queryResult.getDouble("pret");
                String zone = queryResult.getString("adresa");
                String currency = queryResult.getString("moneda");

                if (lastKnownCurrency.equals(currency)) {
                    resultedRowsFromQuery.add(new Pair<>(price, zone));
                } else if (lastKnownCurrency.isEmpty()) {
                    lastKnownCurrency = currency;
                } else {
                    break;
                }
            }

            queryResult.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultedRowsFromQuery;
    }

}
