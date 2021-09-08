/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AccountDAO {

    private Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public AccountDAO(Connection con) {
		this.conn = con;
	}
    
   public Account login(String user, String pass) {
	   String query = "select * from Account where User = ? and Pass = ?";
	   try {
           ps = this.conn.prepareStatement(query);
           ps.setString(1, user);
           ps.setString(2, pass);
           rs = ps.executeQuery();
           while(rs.next())
           {
        	   return new Account(
        			  rs.getInt(1),
        			  rs.getString(2),
        			  rs.getString(3),
        			  rs.getInt(4));
           }
           
	   }catch(Exception e) {
		   
	   }
	   return null;
   }
   
   public Account checkAccountExist(String user) {
	   String query = "select * from Account where User = ?";
	   try {
           ps = this.conn.prepareStatement(query);
           ps.setString(1, user);
           rs = ps.executeQuery();
           while(rs.next())
           {
        	   return new Account(
        			  rs.getInt(1),
        			  rs.getString(2),
        			  rs.getString(3),
        			  rs.getInt(4));
           }
           
	   }catch(Exception e) {
		   
	   }
	   return null;
   }
   
   public void signUp(String user, String pass) {
	   String query = "insert into Account values(?,?,0)";
	   try {
          ps = this.conn.prepareStatement(query);
          ps.setString(1, user);
          ps.setString(2, pass);
          ps.executeUpdate();
	} catch (Exception e) {
		// TODO: handle exception
	}
   }
}
