package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JOptionPane;

public class ClientDAO {

	
	private static ResultSet resultSet =null ;
	private static PreparedStatement prepStatement = null;
	
	/**
	 * SQL instruction that selects everything from Client
	 */
	public static void selectEverythingFromClient() {
		Connection con = ConnectionFactory.getConnection();
		try {
			prepStatement = con.prepareStatement("SELECT * FROM CLIENT");
			resultSet = prepStatement.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * SQL instruction that inserts data into Client
	 * @param client
	 */
	public static void insertIntoClient(model.Client client) {
		Connection con = ConnectionFactory.getConnection();
		try {
			prepStatement = con.prepareStatement("INSERT INTO CLIENT VALUES(?,?,?,?,?)");
			prepStatement.setInt(1, client.getIdClient());
			prepStatement.setString(2, client.getNume());
			prepStatement.setString(3, client.getPrenume());
			prepStatement.setString(4, client.getEmail());
			prepStatement.setString(5, client.getPassword());
			
			prepStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Client Added");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to Add Client");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * SQL instruction that updates data in Client
	 * @param client
	 */
	public static void updateClient(model.Client client) {
		Connection con = ConnectionFactory.getConnection();
		try {
			prepStatement = con.prepareStatement("UPDATE CLIENT SET nume=?,prenume=? ,email=?, password=? WHERE idClient=?");
			
			prepStatement.setString(1, client.getNume());
			prepStatement.setString(2, client.getPrenume());
			prepStatement.setString(3, client.getEmail());
			prepStatement.setString(4, client.getPassword());
			prepStatement.setInt(5, client.getIdClient());
			
			prepStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Client Updated");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to Update Client");
			e.printStackTrace();
		}
		
	}
	/**
	 * SQL instruction that deletes data from Client
	 * @param client
	 */
	public static void deleteClient(model.Client client) {
		Connection con = ConnectionFactory.getConnection();
		try {
			prepStatement = con.prepareStatement("DELETE FROM CLIENT  WHERE idClient=?");
			prepStatement.setInt(1, client.getIdClient());
			prepStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Client Deleted");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to delete Client");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Returns the static resultset
	 * @return
	 */
	public static ResultSet getResultSet() {
		return resultSet;
	}
	
	
}
