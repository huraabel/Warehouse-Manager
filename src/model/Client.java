package model;

public class Client {
	
	private int idClient;
	private String nume;
	private String prenume;
	private String email;
	private String password;
	
	/**
	 * Creates new Client
	 * @param id
	 * @param nume
	 * @param prenume
	 * @param email
	 * @param password
	 */
	public Client(int id,String nume, String prenume,String email,String password)
	{
		this.idClient = id;
		this.nume= nume;
		this.prenume =prenume;
		this.email= email;
		this.password = password;
	}
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int id) {
		this.idClient = id;
	}
	
	
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume= nume;
	}
	
	
	public String getPrenume() {
		return prenume;
	}
	public void setPrenume(String prenume) {
		this.prenume= prenume;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email= email;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String passw) {
		this.password= passw;
	}
	
	
	public String toString() {
		return idClient+ " "+ nume+ " "+ prenume + " "+ email+ " "+ password + "\n";
		
	}
	
}
