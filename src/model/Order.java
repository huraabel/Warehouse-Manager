package model;

public class Order {
	
	private String idOrder;
	private int idClient;
	private int idProduct;
	private int cantitate;
	
	/**
	 * Creates new Order
	 * @param idOrder
	 * @param idClient
	 * @param idProduct
	 * @param cant
	 */
	public Order(String idOrder, int idClient,int idProduct,int cant)
	{
		this.idOrder = idOrder;
		this.idClient= idClient;
		this.idProduct = idProduct;
		this.cantitate=cant;
	}
	
	public String getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(String idOrd) {
		this.idOrder = idOrd;
	}
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	
	public int getCantitate() {
		return cantitate;
	}
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	
	
}
