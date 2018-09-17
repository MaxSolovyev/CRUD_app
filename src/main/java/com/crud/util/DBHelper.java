package com.crud.util;

import com.crud.model.User;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static DBHelper instance;
    private Connection connection;
    private Configuration configuration;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

	public Connection getConnection() throws DBException {
        if (connection == null) {
            try {
                DriverManager.registerDriver((Driver) Class.forName(PropertyReader.getProperties("driver.class")).newInstance());

                String url = PropertyReader.getProperties("connection.url") + "?user=" +
                        PropertyReader.getProperties("username") + "&password=" +
                        PropertyReader.getProperties("password") + "&autoReconnect=true&useSSL=false";
                connection = DriverManager.getConnection(url);
            }
            catch (SQLException|ClassNotFoundException|IllegalAccessException|InstantiationException ex) {
                throw new DBException(ex);
            }
        }
		return connection;
	}

    public Configuration getConfiguration() {

        if (configuration == null) {

            configuration = new org.hibernate.cfg.Configuration();
            configuration.addAnnotatedClass(User.class);

            configuration.setProperty("hibernate.dialect", PropertyReader.getProperties("hibernate.dialect"));
            configuration.setProperty("hibernate.connection.driver_class", PropertyReader.getProperties("hibernate.connection.driver_class"));
            configuration.setProperty("hibernate.connection.url", PropertyReader.getProperties("hibernate.connection.url"));
            configuration.setProperty("hibernate.connection.username", PropertyReader.getProperties("hibernate.connection.username"));
            configuration.setProperty("hibernate.connection.password", PropertyReader.getProperties("hibernate.connection.password"));
            configuration.setProperty("hibernate.show_sql", PropertyReader.getProperties("hibernate.show_sql"));
            configuration.setProperty("hibernate.hbm2ddl.auto", PropertyReader.getProperties("hibernate.hbm2ddl.auto"));
//        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        }
        return configuration;
    }

}
