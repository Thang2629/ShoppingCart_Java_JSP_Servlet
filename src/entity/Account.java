package entity;

public class Account {
	private int ID;
	private String user;
	private String pass;
	private int isAdmin;
	
	public Account(int iD, String user, String pass, int isAdmin) {
		super();
		ID = iD;
		this.user = user;
		this.pass = pass;
		this.isAdmin = isAdmin;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	@Override
	public String toString() {
		return "Account [ID=" + ID + ", user=" + user + ", pass=" + pass + ", isAdmin=" + isAdmin + "]";
	}
	
	
}
