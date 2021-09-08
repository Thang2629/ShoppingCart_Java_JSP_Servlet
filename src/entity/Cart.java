package entity;

public class Cart extends Product {
	private int orderID;
	private int userID;
	private int quantity;
	private String dateCreate;
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}
	public Cart(int orderID, int userID, int quantity, String dateCreate) {
		super();
		this.orderID = orderID;
		this.userID = userID;
		this.quantity = quantity;
		this.dateCreate = dateCreate;
	}
	public Cart( int userID, int quantity, String dateCreate) {
		super();
		this.userID = userID;
		this.quantity = quantity;
		this.dateCreate = dateCreate;
	}
	public Cart() {
		
	}
	
}
