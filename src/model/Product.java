package model;

public class Product {
	
	private int idProduct;
	private String denumire;
	private int stoc;
	private double pret;
	/**
	 * Creates new Product
	 * @param idP
	 * @param denum
	 * @param stoc
	 * @param pret
	 */
	public Product(int idP, String denum, int stoc, double pret)
	{
		this.idProduct = idP;
		this.denumire= denum;
		this.stoc = stoc;
		this.pret = pret;
	}
	
	
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	
	public String getDenumire() {
		return denumire;
	}
	public void setDenumire(String denumire) {
		this.denumire= denumire;
	}
	
	public int getStoc() {
		return stoc;
	}
	public void setStoc(int stoc) {
		this.stoc = stoc;
	}
	
	public double getPret() {
		return pret;
	}
	public void setPret(int pret) {
		this.pret = pret;
	}
	
}
