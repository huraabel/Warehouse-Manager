package businessLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProductController {
	private presentation.View view;

	/**
	 * Creates Client Controller
	 * 
	 * @param view
	 */
	public ProductController(presentation.View view) {
		this.view = view;
		this.view.addAddProductButtonListener(new AddProductListener());
		this.view.addEditProductButtonListener(new UpdateProductListener());
		this.view.addDeleteProductButtonListener(new DeleteProductListener());

	}
	/**
	 * Listens for add button
	 * @author Abel Jonathan
	 *
	 */
	public class AddProductListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {

				testFields();
				testLogic();
				int id = Integer.parseInt(view.getIdProdusTextField());
				String denumire = view.getDenumireTextField();
				int stoc = Integer.parseInt(view.getStocTextField());
				double pret = Double.parseDouble(view.getPretTextField());
				model.Product product = new model.Product(id, denumire, stoc, pret);
				dataAccessLayer.ProductDAO.insertIntoProduct(product);
				makeProductTable(view.getProductTable());
				view.setProductTableModel();
				view.setSelectProductForOrderTableModel();
				view.getProductTableDataModel().fireTableDataChanged();

			} catch (Exception e1) {

				e1.printStackTrace();
			}
		}

	}
	/**
	 * Listens for edit button
	 * @author Abel Jonathan
	 *
	 */
	public class UpdateProductListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {

				testFields();
				testLogic();
				int id = Integer.parseInt(view.getIdProdusTextField());
				String denumire = view.getDenumireTextField();
				int stoc = Integer.parseInt(view.getStocTextField());
				double pret = Double.parseDouble(view.getPretTextField());
				model.Product product = new model.Product(id, denumire, stoc, pret);
				dataAccessLayer.ProductDAO.updateProduct(product);
				makeProductTable(view.getProductTable());
				view.setProductTableModel();
				view.setSelectProductForOrderTableModel();
				view.getProductTableDataModel().fireTableDataChanged();

			} catch (Exception e1) {

				e1.printStackTrace();
			}

		}

	}
/**
 * Listens for delete button
 * @author Abel Jonathan
 *
 */
	public class DeleteProductListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {

				testFields();
				int id = Integer.parseInt(view.getIdProdusTextField());
				String denumire = view.getDenumireTextField();
				int stoc = Integer.parseInt(view.getStocTextField());
				double pret = Double.parseDouble(view.getPretTextField());
				model.Product product = new model.Product(id, denumire, stoc, pret);
				dataAccessLayer.ProductDAO.deleteProduct(product);
				makeProductTable(view.getProductTable());
				view.setProductTableModel();
				view.setSelectProductForOrderTableModel();
				view.getProductTableDataModel().fireTableDataChanged();

			} catch (Exception e1) {

				e1.printStackTrace();
			}

		}

	}
	/**
	 * Gets data from Product Table and sets up the JTable
	 * @param t
	 * @return
	 */
	public static JTable makeProductTable(JTable t) {
		dataAccessLayer.ProductDAO.selectEverythingFromProduct();
		ResultSet rs = dataAccessLayer.ProductDAO.getResultSet();

		List objects = new ArrayList<Object>();

		try {
			int ok = 0;
			while (rs.next()) {
				ok = 1;
				int idP = rs.getInt("idProduct");
				String denumire = rs.getString("denumire");
				int stoc = rs.getInt("stoc");
				double pret = rs.getDouble("pret");
				objects.add(new model.Product(idP, denumire, stoc, pret));

			}
			if (ok == 1)
				t = Utility.createTable(objects);
			else
				t = new JTable();

			presentation.View.setProductTableDataModel((DefaultTableModel) t.getModel());
			return t;
		} catch (SQLException e1) {
			return null;
		}

	}
/**
 * Test if fields are empty
 * @throws Exception
 */
	public void testFields() throws Exception {
		if (view.getIdProdusTextField().isEmpty() || view.getDenumireTextField().isEmpty()
				|| view.getStocTextField().isEmpty() || view.getPretTextField().isEmpty()) {
			JOptionPane.showMessageDialog(new JFrame(), "Completati campurile!", "ERROR", JOptionPane.ERROR_MESSAGE);
			throw new Exception();
		}
	}
/**
 * Test logic of data given by user
 * @throws Exception
 */
	public void testLogic() throws Exception {
		if (Integer.parseInt(view.getIdProdusTextField()) <= 0 || Double.parseDouble(view.getPretTextField()) <= 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Completati campurile corect!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			throw new Exception();
		}
	}

}
