package businessLayer;


import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



public class Utility {
	/**
	 * Creates a JTable using reflection
	 * @param objects
	 * @return
	 */
	public static JTable createTable(List<Object> objects) {

		DefaultTableModel model = new DefaultTableModel();
		model.setRowCount(0);// plus
		
		for (Field field : objects.get(0).getClass().getDeclaredFields()) {
			field.setAccessible(true);
			model.addColumn(field.getName());
		}
		model = Utility.addRows(model, objects);

		JTable table = new JTable();
		table.setModel(model);
		model.fireTableDataChanged();//plus
		return table;

	}
	
	/**
	 * Adds rows into a table model using reflection
	 * @param model
	 * @param objects
	 * @return
	 */
	public static DefaultTableModel addRows(DefaultTableModel model, List<Object> objects) {
		
		for (int i = 0; i < objects.size(); i++) {
			Object[] ob = new Object[objects.get(i).getClass().getDeclaredFields().length];
			int j = 0;
			for (Field field : objects.get(i).getClass().getDeclaredFields()) {
				field.setAccessible(true); // set modifier to public
				try { ob[j] = field.get(objects.get(i)); } 
				catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				
				j++;
				
			}
			model.addRow(ob);
			
		}
		return model;
	}
	
	/**
	 * Creates bill for an order
	 * @param order
	 */
	public static void createBill(model.Order order, int productId)
	{	
		double pret=0;
		Document doc = new Document();
		try {
			dataAccessLayer.ProductDAO.selectPretFromProduct(productId);
			ResultSet r = dataAccessLayer.ProductDAO.getResultSet();
			r.next();
			pret = r.getDouble("pret");
			PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Abel Jonathan\\Desktop\\TP\\tema3\\"+"Order"+ order.getIdOrder()+ ".pdf"));
			doc.open();
			PdfPTable t = new PdfPTable(6);
			t.addCell("Order ID"); t.addCell("idCLient"); t.addCell("idProduct"); t.addCell("cantitate"); t.addCell("pret"); t.addCell("Total");
			t.addCell(order.getIdOrder());
			t.addCell( ((Integer)order.getIdClient()).toString() );
			t.addCell(((Integer)order.getIdProduct()).toString() );
			t.addCell(((Integer)order.getCantitate()).toString() );
			t.addCell(((Double)pret).toString());
			t.addCell( ( (Double)(order.getCantitate()*pret) ).toString() );
			doc.add(t);
			doc.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
}
	
	