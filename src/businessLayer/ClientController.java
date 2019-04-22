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

public class ClientController {
	
	private presentation.View view ;
	
	/**
	 * Creates Client Controller
	 * @param view
	 */
	public	ClientController(presentation.View view)
	{
		this.view = view;
		this.view.addAddClientButtonListener(new AddClientListener());
		this.view.addUpdateClientButtonListener(new UpdateClientListener());
		this.view.addDeleteClientButtonListener(new DeleteClientListener());
	}
	/**
	 * Listens for add button
	 * @author Abel Jonathan
	 *
	 */
	public class AddClientListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {				
				testFields();
				int id = Integer.parseInt(view.getIdTextField());
				String nume =view.getNumeTextField();
				String prenume = view.getPrenumeTextField();
				String email = view.getEmailTextField();
				String password = view.getPassTextField();
				model.Client client = new model.Client(id,nume,prenume,email,password);
				dataAccessLayer.ClientDAO.insertIntoClient(client);
				makeClientTable( view.getClientTable());
				view.setClientTableModel();
				view.setSelectClientForOrderTableModel();
				view.getClientTableDataModel().fireTableDataChanged();
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		}
		
		
		
	}
	
	/**
	 * Listens for update button
	 * @author Abel Jonathan
	 *
	 */
	public class UpdateClientListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {
				
				testFields();
				int id = Integer.parseInt(view.getIdTextField());
				String nume =view.getNumeTextField();
				String prenume = view.getPrenumeTextField();
				String email = view.getEmailTextField();
				String password = view.getPassTextField();
				model.Client client = new model.Client(id,nume,prenume,email,password);
				dataAccessLayer.ClientDAO.updateClient(client);
				makeClientTable( view.getClientTable());
				view.setClientTableModel();
				view.setSelectClientForOrderTableModel();
				view.getClientTableDataModel().fireTableDataChanged();
				
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
	public class DeleteClientListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {
				testFields();
				int id = Integer.parseInt(view.getIdTextField());
				String nume =view.getNumeTextField();
				String prenume = view.getPrenumeTextField();
				String email = view.getEmailTextField();
				String password = view.getPassTextField();
				model.Client client = new model.Client(id,nume,prenume,email,password);
				dataAccessLayer.ClientDAO.deleteClient(client);
				makeClientTable( view.getClientTable());
				view.setClientTableModel();
				view.setSelectClientForOrderTableModel();
				view.getClientTableDataModel().fireTableDataChanged();
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}	
		}	
	}
	
	
	
	/**
	 * Gets data from Client Table and sets up the JTable
	 * @param t
	 * @return
	 */
	public static JTable makeClientTable(JTable t)
	{
		dataAccessLayer.ClientDAO.selectEverythingFromClient();
		ResultSet rs =  dataAccessLayer.ClientDAO.getResultSet();
		List objects = new ArrayList<Object>();
		
		try {
			int ok=0;
			while(rs.next())
			 {
				ok=1;
				objects.add(new model.Client( rs.getInt("idClient"),rs.getString("nume"),
						rs.getString("prenume"),rs.getString("email"),rs.getString("password")));	
			 }
			
			if(ok==1)
				t =Utility.createTable(objects);
			else
				t =new JTable();
			
			dataAccessLayer.ConnectionFactory.close(rs);
			presentation.View.setClientTableDataModel((DefaultTableModel)t.getModel());
			 return t;
		} catch (SQLException e1) {
			return null;
		}
	}
	
	/**
	 * Test if fields are empty
	 * @throws Exception
	 */
	public void testFields() throws Exception
	{
		if(view.getIdTextField().isEmpty() || view.getNumeTextField().isEmpty()  ||
		   view.getPrenumeTextField().isEmpty()  || view.getEmailTextField().isEmpty() ||
		   view.getPassTextField().isEmpty() 		 )
		{
			 JOptionPane.showMessageDialog(new JFrame(), "Completati campurile!", "ERROR", JOptionPane.ERROR_MESSAGE);
			 throw new Exception();
		}
	}
	
	

	
	
	
	
	
}
