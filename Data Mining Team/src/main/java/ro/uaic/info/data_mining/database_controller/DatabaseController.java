package ro.uaic.info.data_mining.database_controller;

import javafx.util.Pair;
import org.springframework.stereotype.Controller;
import ro.uaic.info.data_mining.database_controller.DatabaseConnection;

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
        List<Pair> resultedQuery = new ArrayList<>(); // TODO denumire mai sugestiva pt resultedQuery

        /* TODO: Pentru a face codul mai citibil, rule of thumb-ul este
        TODO sa separi prin newline bucatile de cod care nu se leaga la fel de strans, dpdv. logic
        TODO Ai un exemplu mai jos
         */

        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            // TODO aici merge o linie noua
            statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(queryStatement);
            // TODO si aici
            String lastKnownCurrency = "";
            // TODO si aici
            while (queryResult.next()) {
                double price = queryResult.getDouble("pret");
                String zone = queryResult.getString("adresa");
                String currency = queryResult.getString("moneda");
                //TODO si aici
                if (lastKnownCurrency.equals(currency)) {
                    resultedQuery.add(new Pair<>(price, zone));
                } else if (lastKnownCurrency.isEmpty()) {
                    lastKnownCurrency = currency;
                } else {
                    break;
                }
            }
            //TODO si aici
            queryResult.close();
            statement.close();
            connection.close();
            //TODO si aici - doar pentru a nu parea impins in partea de mai jos
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // TODO si aici
        return resultedQuery;
    }

}
