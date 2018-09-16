package com.crud.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

	static public Connection getConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

		DriverManager.registerDriver((Driver) Class.forName(PropertyReader.getProperties("driver.class")).newInstance());

		String url = PropertyReader.getProperties("connection.url") + "?user=" +
				PropertyReader.getProperties("username") + "&password=" +
				PropertyReader.getProperties("password") + "&autoReconnect=true&useSSL=false";

		return DriverManager.getConnection(url);

	}
}
