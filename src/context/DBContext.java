package context;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBContext {
    
	private static Connection connection = null;
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
        if(connection == null){
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com:3306/sql6431584","sql6431584","XSpJQtmJkk");
            System.out.print("connected");
        }
        return connection;
    }
	public static void main(String[] args) {
		try {
			DBContext.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}