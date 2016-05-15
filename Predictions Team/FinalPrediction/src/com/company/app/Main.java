package com.company.app;

import com.company.database.DatabaseUpdater;
import com.company.controller.Controller;

import java.sql.SQLException;

/**
 * Created by Robert on 08.05.2016.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseUpdater databaseUpdater = new DatabaseUpdater();

        Controller controller = new Controller(databaseUpdater);
        controller.startApplication();

        databaseUpdater.closeConnection();
    }
}
