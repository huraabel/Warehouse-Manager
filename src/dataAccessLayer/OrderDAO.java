package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class OrderDAO {
	
	private static ResultSet resultSet =null ;
	private static PreparedStatement prepStatement = null;
	
	/**
	 * SQL instruction that selects everything from Order
	 */
	public static void selectEverythingFromOrder() {
		Connection con = ConnectionFactory.getConnection();
		try {
			prepStatement = con.prepareStatement("SELECT * FROM wh.ORDER");
			resultSet = prepStatement.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * SQL instruction that inserts data into Order
	 */
	public static void insertIntoOrder(model.Order order) {
		Connection con = ConnectionFactory.getConnection();
		try {
			prepStatement = con.prepareStatement("INSERT INTO wh.ORDER VALUES(?,?,?,?)");
			prepStatement.setString(1, order.getIdOrder());
			prepStatement.setInt(2, order.getIdClient());
			prepStatement.setInt(3, order.getIdProduct());
			prepStatement.setInt(4, order.getCantitate());
			prepStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Order Placed");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to Place Order");
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
