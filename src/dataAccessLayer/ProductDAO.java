package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ProductDAO {
	private static ResultSet resultSet =null ;
	private static PreparedStatement prepStatement = null;
	
	/**
	 * SQL instruction that selects everything from Product
	 */
	public static void selectEverythingFromProduct() {
		Connection con = ConnectionFactory.getConnection();
		try {
			prepStatement = con.prepareStatement("SELECT * FROM PRODUCT");
			resultSet = prepStatement.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void selectPretFromProduct(int idP) {
		Connection con = ConnectionFactory.getConnection();
		try {
			prepStatement = con.prepareStatement("SELECT pret FROM PRODUCT where idProduct=?");
			prepStatement.setInt(1, idP);
			resultSet = prepStatement.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * SQL instruction that inserts data into Product
	 * @param product
	 */
	public static void insertIntoProduct(model.Product product) {
		Connection con = ConnectionFactory.getConnection();
		try {
			prepStatement = con.prepareStatement("INSERT INTO PRODUCT VALUES(?,?,?,?)");
			prepStatement.setInt(1, product.getIdProduct());
			prepStatement.setString(2, product.getDenumire());
			prepStatement.setInt(3, product.getStoc());
			prepStatement.setDouble(4, product.getPret());
		
			
			prepStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Product Added");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to Add Product");
			e.printStackTrace();
		}
		
	}
	
	/**
	 *  SQL instruction that updates data in Product
	 * @param product
	 */
	public static void updateProduct(model.Product product) {
		Connection con = ConnectionFactory.getConnection();
		try {
			prepStatement = con.prepareStatement("UPDATE PRODUCT SET denumire=?,stoc=? ,pret=? WHERE idProduct=?");
			
			prepStatement.setString(1, product.getDenumire());
			prepStatement.setInt(2, product.getStoc());
			prepStatement.setDouble(3, product.getPret());
			prepStatement.setInt(4, product.getIdProduct());
			
			prepStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Product Updated");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to Update Product");
			e.printStackTrace();
		}
		
	}
	
	/**
	 *  SQL instruction that deletes data from Product
	 * @param product
	 */
	public static void deleteProduct(model.Product product) {
		Connection con = ConnectionFactory.getConnection();
		try {
			prepStatement = con.prepareStatement("DELETE FROM PRODUCT  WHERE idProduct=?");
			prepStatement.setInt(1, product.getIdProduct());
			prepStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Product Deleted");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to delete Product");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * SQL instruction that updates (decrements) stock based on an order
	 * @param idP
	 * @param cant
	 */
	public static void updateStoc(int idP, int cant)
	{
		Connection con = ConnectionFactory.getConnection();
		try {
			
			PreparedStatement st = con.prepareStatement("Select stoc from Product where idProduct=?");
			st.setInt(1, idP);
			ResultSet r = st.executeQuery();
			r.next();
			int stoc = r.getInt("stoc");
			
			prepStatement = con.prepareStatement("UPDATE PRODUCT SET stoc=?  WHERE idProduct=?");
			prepStatement.setInt(1,stoc-cant );
			prepStatement.setInt(2, idP );
			prepStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Product Stock Updated");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to update Product");
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
