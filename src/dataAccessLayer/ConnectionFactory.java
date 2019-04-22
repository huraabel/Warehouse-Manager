package dataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;




public class ConnectionFactory {

	
	private static final Logger MYLOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String MYDRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String MYDBURL = "jdbc:mysql://localhost:3306/wh";
	private static final String MYUSERNAME = "root";
	private static final String MYPASSWORD = "root";
	
	private static ConnectionFactory connection = new ConnectionFactory();
	
	/**
	 * Creates the connection with reflection
	 */
	private ConnectionFactory() {
		try {
			Class.forName(MYDRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Connects to the database
	 * @return
	 */
	private Connection connect() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(MYDBURL, MYUSERNAME, MYPASSWORD);
		} catch (SQLException e) {
			MYLOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Returns the connection
	 * @return
	 */
	public static Connection getConnection() {
		return connection.connect();
	}
	
	/**
	 * Closes the connection
	 * @param connection
	 */
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				MYLOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
			}
		}
	}

	
	/**
	 * Closes the statement
	 * @param statement
	 */
	
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				MYLOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
		}
	}
	
	/**
	 * Closes the resultset
	 * @param resultSet
	 */
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				MYLOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
			}
		}
	}
	
}
