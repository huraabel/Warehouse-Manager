package businessLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.UID;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class OrderController {

	private presentation.View view ;
	
	/**
	 * Creates the OrderController that operates on the Order Tab
	 * @param view
	 */
	public OrderController(presentation.View view) {
		super();
		this.view = view;
		this.view.addPlaceOrderButtonListener(new PlaceOrderListener());
	}

	/**
	 * Listens for place order button, test the fields and the logic behind them and insert data accordingly
	 * Creates Bill for the order
	 * @author Abel Jonathan
	 *
	 */
	public class PlaceOrderListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				testFields(); testLogic(); testStoc();
				UID id = new UID();
				String idOrder = id.toString(); idOrder=idOrder.replace(':', ' ');
				model.Order order = new model.Order(idOrder, Integer.parseInt(view.getOrderClientIdTextField()),
						Integer.parseInt( view.getOrderProductIdTextField()),
						Integer.parseInt(view.getOrderCantitateTextField()));
				dataAccessLayer.OrderDAO.insertIntoOrder(order);
				makeOrderTable(view.getOrderListTable());
				view.setOrderListTableModel();
				view.getOrderListTableDataModel().fireTableDataChanged();
				dataAccessLayer.ProductDAO.updateStoc(Integer.parseInt( view.getOrderProductIdTextField()),
						Integer.parseInt(view.getOrderCantitateTextField())	);
				ProductController.makeProductTable(view.getProductTable());
				view.setProductTableModel();
				view.setSelectProductForOrderTableModel();
				view.getProductTableDataModel().fireTableDataChanged();
				Utility.createBill(order,Integer.parseInt( view.getOrderProductIdTextField()));
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}		
		}	
	}
	/**
	 * Test if fields are empty
	 * @throws Exception
	 */
	public void testFields() throws Exception{
		if( view.getOrderClientIdTextField().isEmpty() || view.getOrderProductIdTextField().isEmpty() 
				|| view.getOrderCantitateTextField().isEmpty()
				) {
			JOptionPane.showMessageDialog(new JFrame(), "Completati campurile!", "ERROR", JOptionPane.ERROR_MESSAGE);
			throw new Exception();
		}
	}
	
	/**
	 * Test if logic given by the user is valid
	 * @throws Exception
	 */
	public void testLogic() throws Exception{
		if( Integer.parseInt(view.getOrderClientIdTextField()) <=0 || Integer.parseInt( view.getOrderProductIdTextField()) <=0
				|| Integer.parseInt(view.getOrderCantitateTextField()) <= 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Completati campurile corect!", "ERROR", JOptionPane.ERROR_MESSAGE);
			throw new Exception();
		}
	}
	
	/**
	 * Test if it is Under Stock case for a product
	 * @throws Exception
	 */
	public void testStoc() throws Exception{
		int i = view.getSelectProductForOrderTable().getSelectedRow();
        TableModel model =view.getSelectProductForOrderTable().getModel();
        if(Integer.parseInt(view.getOrderCantitateTextField()) > Integer.parseInt(model.getValueAt(i,2).toString()) )
        {
        	JOptionPane.showMessageDialog(new JFrame(), "Cantitate ceruta prea mare!", "UNDER STOCK", JOptionPane.ERROR_MESSAGE);
			throw new Exception();
        }
       
	}
	
	
	/**
	 * Gets data from Order Table and sets up the JTable
	 * @param t
	 * @return
	 */
	public static JTable makeOrderTable(JTable t)
	{
		dataAccessLayer.OrderDAO.selectEverythingFromOrder();
		ResultSet rs =  dataAccessLayer.OrderDAO.getResultSet();
		List objects = new ArrayList<Object>();
		
		try {
			int ok=0;
			while(rs.next())
			 {
				ok=1;
				objects.add(new model.Order( rs.getString("idOrder"),rs.getInt("idClient"),
						rs.getInt("idProduct"),rs.getInt("cantitate")));	
			 }
			
			if(ok==1)
				t =Utility.createTable(objects);
			else
				t =new JTable();
			
			dataAccessLayer.ConnectionFactory.close(rs);
			presentation.View.setOrderListTableDataModel((DefaultTableModel)t.getModel());
			 return t;
		} catch (SQLException e1) {
			return null;
		}
	}

}
