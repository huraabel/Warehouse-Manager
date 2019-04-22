package presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dataAccessLayer.ConnectionFactory;


/**
 * Creates the GUI
 */
public class View2 extends JFrame {

	private static JPanel contentPane;

	public static void repaintContent() {
		contentPane.repaint();
	}

	private JTextField idTextField;
	private JTextField numeTextField;
	private JTextField prenumeTextField;
	private JTextField passTextField;
	private JTextField emailTextField;
	private JTable clientTable;
	private JButton addClientButton;
	private JButton updateClientButton;
	private JButton deleteClientButton;

	private static DefaultTableModel dtm;
	private static DefaultTableModel odtm;

	private JTextField idProdusTextField;
	private JTextField denumireTextField;
	private JTextField stocTextField;
	private JTextField pretTextField;
	private JTable productTable;
	private JButton btnAddProduct;
	private JButton btnEditProduct;
	private JButton btnDeleteProduct;
	
	private static DefaultTableModel pdtm;

	private JTable selectClientForOrderTable;
	private JTable selectProductForOrderTable;
	private JTextField orderCantitateTextField;
	private JButton btnPlaceOrder;
	private JTextField orderClientIdTextField;
	private JTextField orderProductIdTextField;
	private JTable orderListTable;

	/**
	 * Create the frame.
	 */
	public View2() {
		setTitle("WareHouse Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 774, 505);
		contentPane.add(tabbedPane);

		JPanel clientPanel = new JPanel();
		tabbedPane.addTab("Client", null, clientPanel, null);
		clientPanel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GREEN));
		panel_1.setBounds(229, 11, 530, 466);
		clientPanel.add(panel_1);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE).addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(13)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		clientTable = businessLayer.ClientController.makeClientTable(clientTable); // new JTable();
		clientTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	clientTableMouseClicked(evt);
            }});
		scrollPane.setViewportView(clientTable);
		panel_1.setLayout(gl_panel_1);

		JLabel label = new JLabel("id:");
		label.setBounds(15, 48, 72, 14);
		clientPanel.add(label);

		idTextField = new JTextField();
		idTextField.setColumns(10);
		idTextField.setBounds(97, 45, 86, 20);
		clientPanel.add(idTextField);

		JLabel label_1 = new JLabel("nume:");
		label_1.setBounds(15, 95, 72, 14);
		clientPanel.add(label_1);

		numeTextField = new JTextField();
		numeTextField.setColumns(10);
		numeTextField.setBounds(97, 92, 86, 20);
		clientPanel.add(numeTextField);

		JLabel label_2 = new JLabel("prenume:");
		label_2.setBounds(15, 143, 72, 14);
		clientPanel.add(label_2);

		prenumeTextField = new JTextField();
		prenumeTextField.setColumns(10);
		prenumeTextField.setBounds(97, 140, 86, 20);
		clientPanel.add(prenumeTextField);

		JLabel label_3 = new JLabel("email:");
		label_3.setBounds(15, 187, 72, 14);
		clientPanel.add(label_3);

		JLabel label_4 = new JLabel("password:");
		label_4.setBounds(15, 233, 72, 14);
		clientPanel.add(label_4);

		passTextField = new JTextField();
		passTextField.setText("");
		passTextField.setColumns(10);
		passTextField.setBounds(97, 230, 86, 20);
		clientPanel.add(passTextField);

		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(97, 184, 86, 20);
		clientPanel.add(emailTextField);

		addClientButton = new JButton("Add Client");
		addClientButton.setBounds(28, 298, 118, 23);
		clientPanel.add(addClientButton);

		updateClientButton = new JButton("Update Client");
		updateClientButton.setBounds(28, 345, 118, 23);
		clientPanel.add(updateClientButton);

		deleteClientButton = new JButton("Delete Client");
		deleteClientButton.setBounds(28, 393, 118, 23);
		clientPanel.add(deleteClientButton);

		JPanel productPanel = new JPanel();
		tabbedPane.addTab("Product", null, productPanel, null);
		productPanel.setLayout(null);

		JLabel idProductLabel = new JLabel("idProduct:");
		idProductLabel.setBounds(10, 40, 87, 14);
		productPanel.add(idProductLabel);

		idProdusTextField = new JTextField();
		idProdusTextField.setBounds(117, 37, 86, 20);
		productPanel.add(idProdusTextField);
		idProdusTextField.setColumns(10);

		JLabel denumireLabel = new JLabel("denumire:");
		denumireLabel.setBounds(10, 78, 87, 14);
		productPanel.add(denumireLabel);

		denumireTextField = new JTextField();
		denumireTextField.setBounds(117, 75, 86, 20);
		productPanel.add(denumireTextField);
		denumireTextField.setColumns(10);

		JLabel stocLabel = new JLabel("stoc:");
		stocLabel.setBounds(10, 113, 46, 14);
		productPanel.add(stocLabel);

		stocTextField = new JTextField();
		stocTextField.setBounds(117, 110, 86, 20);
		productPanel.add(stocTextField);
		stocTextField.setColumns(10);

		JLabel pretLabel = new JLabel("pret:");
		pretLabel.setBounds(10, 155, 46, 14);
		productPanel.add(pretLabel);

		pretTextField = new JTextField();
		pretTextField.setBounds(117, 152, 86, 20);
		productPanel.add(pretTextField);
		pretTextField.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.RED));
		panel.setBounds(246, 11, 513, 455);
		productPanel.add(panel);

		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE).addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		productTable = businessLayer.ProductController.makeProductTable(productTable);
		productTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	productTableMouseClicked(evt);
            }});
		scrollPane_1.setViewportView(productTable);
		panel.setLayout(gl_panel);

		btnAddProduct = new JButton("Add product");
		btnAddProduct.setBounds(39, 243, 120, 23);
		productPanel.add(btnAddProduct);

		btnEditProduct = new JButton("Edit product");
		btnEditProduct.setBounds(39, 288, 120, 23);
		productPanel.add(btnEditProduct);

		btnDeleteProduct = new JButton("Delete product");
		btnDeleteProduct.setBounds(38, 340, 120, 23);
		productPanel.add(btnDeleteProduct);

		JPanel orderPanel = new JPanel();
		tabbedPane.addTab("Order", null, orderPanel, null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(new LineBorder(Color.GREEN));

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setViewportBorder(new LineBorder(Color.RED));

		JLabel lblCantitate = new JLabel("Cantitate:");

		orderCantitateTextField = new JTextField();
		orderCantitateTextField.setColumns(10);

		btnPlaceOrder = new JButton("Place Order");
		
		orderClientIdTextField = new JTextField();
		orderClientIdTextField.setColumns(10);
		
		orderProductIdTextField = new JTextField();
		orderProductIdTextField.setColumns(10);
		
		JLabel oderClientLabel = new JLabel("Client ID:");
		
		JLabel productOrderLabel = new JLabel("Product ID:");
		GroupLayout gl_orderPanel = new GroupLayout(orderPanel);
		gl_orderPanel.setHorizontalGroup(
			gl_orderPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_orderPanel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_orderPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 749, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 749, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(15, Short.MAX_VALUE))
				.addGroup(gl_orderPanel.createSequentialGroup()
					.addGap(73)
					.addComponent(orderClientIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(68)
					.addComponent(orderProductIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
					.addComponent(orderCantitateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(btnPlaceOrder, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
				.addGroup(Alignment.LEADING, gl_orderPanel.createSequentialGroup()
					.addGap(104)
					.addComponent(oderClientLabel)
					.addGap(132)
					.addComponent(productOrderLabel)
					.addGap(158)
					.addComponent(lblCantitate, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(198, Short.MAX_VALUE))
		);
		gl_orderPanel.setVerticalGroup(
			gl_orderPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_orderPanel.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(gl_orderPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(oderClientLabel)
						.addComponent(lblCantitate)
						.addComponent(productOrderLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_orderPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPlaceOrder)
						.addComponent(orderCantitateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(orderClientIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(orderProductIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(56, Short.MAX_VALUE))
		);

		selectProductForOrderTable = businessLayer.ProductController.makeProductTable(selectProductForOrderTable);
		selectProductForOrderTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	selectProductForOrderTableMouseClicked(evt);
            }});
		scrollPane_3.setViewportView(selectProductForOrderTable);

		selectClientForOrderTable = businessLayer.ClientController.makeClientTable(selectClientForOrderTable);
		selectClientForOrderTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	selectClientForOrderTableMouseClicked(evt);
            }});
		scrollPane_2.setViewportView(selectClientForOrderTable);
		orderPanel.setLayout(gl_orderPanel);
		
		JPanel orderListPanel = new JPanel();
		tabbedPane.addTab("Order List", null, orderListPanel, null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setViewportBorder(new LineBorder(Color.YELLOW));
		GroupLayout gl_orderListPanel = new GroupLayout(orderListPanel);
		gl_orderListPanel.setHorizontalGroup(
			gl_orderListPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_orderListPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 743, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_orderListPanel.setVerticalGroup(
			gl_orderListPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_orderListPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		orderListTable = businessLayer.OrderController.makeOrderTable(orderListTable);
		scrollPane_4.setViewportView(orderListTable);
		orderListPanel.setLayout(gl_orderListPanel);
	}

	public String getIdProdusTextField() {
		return idProdusTextField.getText();
	}

	public void setIdProdusTextField(String text) {
		this.idProdusTextField.setText(text);
	}

	public String getDenumireTextField() {
		return denumireTextField.getText();
	}

	public void setDenumireTextField(String text) {
		this.denumireTextField.setText(text);
	}

	public String getStocTextField() {
		return stocTextField.getText();
	}

	public void setStocTextField(String text) {
		this.stocTextField.setText(text);
	}

	public String getPretTextField() {
		return pretTextField.getText();
	}

	public void setPretTextField(String text) {
		this.pretTextField.setText(text);
	}

	public String getIdTextField() {
		return idTextField.getText();
	}

	public void setIdTextField(String text) {
		this.idTextField.setText(text);
	}

	public String getNumeTextField() {
		return numeTextField.getText();
	}

	public void setNumeTextField(String text) {
		this.numeTextField.setText(text);
	}

	public String getPrenumeTextField() {
		return prenumeTextField.getText();
	}

	public void setPrenumeTextField(String text) {
		this.prenumeTextField.setText(text);
	}

	public String getPassTextField() {
		return passTextField.getText();
	}

	public void setPassTextField(String text) {
		this.passTextField.setText(text);
	}

	public String getEmailTextField() {
		return emailTextField.getText();
	}

	public void setEmailTextField(String text) {
		this.emailTextField.setText(text);
	}

	public String getOrderCantitateTextField() {
		return orderCantitateTextField.getText();
	}

	public void setOrderCantitateTextField(String text) {
		this.orderCantitateTextField.setText(text);
	}

	public void addAddClientButtonListener(ActionListener listenForAddButton) {
		addClientButton.addActionListener(listenForAddButton);
	}

	public void addUpdateClientButtonListener(ActionListener listenForUpdateButton) {
		updateClientButton.addActionListener(listenForUpdateButton);
	}

	public void addDeleteClientButtonListener(ActionListener listenForDeleteButton) {
		deleteClientButton.addActionListener(listenForDeleteButton);
	}

	/**
	 * When a row is clicked in clientTable, sets up the text fields accordingly
	 * @param evt
	 */
	public void clientTableMouseClicked(java.awt.event.MouseEvent evt) {
		int i = clientTable.getSelectedRow();
        TableModel model =clientTable.getModel();
        setIdTextField(model.getValueAt(i,0).toString());
        setNumeTextField(model.getValueAt(i,1).toString());
        setPrenumeTextField(model.getValueAt(i,2).toString());
        setEmailTextField(model.getValueAt(i,3).toString());
        setPassTextField(model.getValueAt(i,4).toString());
	}
	
	/**
	 * When a row is clicked in productTable, sets up the text fields accordingly
	 * @param evt
	 */
	public void productTableMouseClicked(java.awt.event.MouseEvent evt) {
		int i = productTable.getSelectedRow();
        TableModel model =productTable.getModel();
        setIdProdusTextField(model.getValueAt(i,0).toString());
        setDenumireTextField(model.getValueAt(i,1).toString());
        setStocTextField(model.getValueAt(i,2).toString());
        setPretTextField(model.getValueAt(i,3).toString());
        
	}
	
	/**
	 * When a row is clicked in selectProductForOrderTable, sets up the text fields accordingly
	 * @param evt
	 */
	public void selectProductForOrderTableMouseClicked(java.awt.event.MouseEvent evt) {
		int i = selectProductForOrderTable.getSelectedRow();
		TableModel model =selectProductForOrderTable.getModel();
		setOrderProductIdTextField(model.getValueAt(i,0).toString());
	}
	
	/**
	 * When a row is clicked in selectClientForOrderTable, sets up the text fields accordingly
	 * @param evt
	 */
	public void selectClientForOrderTableMouseClicked(java.awt.event.MouseEvent evt) {
		int i = selectClientForOrderTable.getSelectedRow();
		TableModel model =selectClientForOrderTable.getModel();
		setOrderClientIdTextField(model.getValueAt(i,0).toString());
	}
	
	
	public void addAddProductButtonListener(ActionListener listenForAddButton) {
		btnAddProduct.addActionListener(listenForAddButton);
	}

	public void addEditProductButtonListener(ActionListener listenForUpdateButton) {
		btnEditProduct.addActionListener(listenForUpdateButton);
	}

	public void addDeleteProductButtonListener(ActionListener listenForDeleteButton) {
		btnDeleteProduct.addActionListener(listenForDeleteButton);
	}

	public void addPlaceOrderButtonListener(ActionListener listenForPlaceOrderButton) {
		btnPlaceOrder.addActionListener(listenForPlaceOrderButton);
	}
	
	
	public JTable getOrderListTable() {
		return orderListTable;
	}
	
	public void setOrderListTableModel() {
		orderListTable.setModel(odtm);
	}
	public static DefaultTableModel getOrderListTableDataModel() {
		return odtm;
	}
	public static void setOrderListTableDataModel(DefaultTableModel dtm1) {
		odtm = dtm1;
	}
	

	public JTable getClientTable() {
		return clientTable;
	}

	public void setClientTable(JTable t) {

		clientTable = t;

	}

	public void setClientTableModel() {
		clientTable.setModel(dtm);
	}

	public static DefaultTableModel getClientTableDataModel() {
		return dtm;
	}

	public static void setClientTableDataModel(DefaultTableModel dtm1) {
		dtm = dtm1;
	}
	
	public JTable getSelectClientForOrderTable() {
		return selectClientForOrderTable;
	}
	public void setSelectClientForOrderTableModel() {
		selectClientForOrderTable.setModel(dtm);
	}
	
	public JTable getSelectProductForOrderTable() {
		return selectProductForOrderTable;
	}
	public void setSelectProductForOrderTableModel() {
		selectProductForOrderTable.setModel(pdtm);
	}
	
	public JTable getProductTable() {
		return productTable;
	}
	
	public void setProductTableModel() {
		productTable.setModel(pdtm);
	}

	public static DefaultTableModel getProductTableDataModel() {
		return pdtm;
	}

	public static void setProductTableDataModel(DefaultTableModel dtm1) {
		pdtm = dtm1;
	}
	
	public String getOrderClientIdTextField()
	{
		return orderClientIdTextField.getText();
	}
	
	public void setOrderClientIdTextField(String t)
	{
		 orderClientIdTextField.setText(t);
	}
	
	public String getOrderProductIdTextField()
	{
		return orderProductIdTextField.getText();
	}
	
	public void setOrderProductIdTextField(String t)
	{
		 orderProductIdTextField.setText(t);
	}
	
}
