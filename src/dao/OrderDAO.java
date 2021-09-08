package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import entity.*;
public class OrderDAO {
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
    

	public OrderDAO(Connection con) {
		super();
		this.con = con;
	}

	public boolean insertOrder(Cart model) {
        boolean result = false;
        try {
            query = "insert into CART (UserID,ProductID,DateCreate,Quantity) values(?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, model.getUserID());
            pst.setInt(2, model.getId());
            pst.setString(3, model.getDateCreate());
            pst.setInt(4, model.getQuantity());
            pst.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
	

    public List<Cart> userOrders(int id) {
        List<Cart> list = new ArrayList<>();
        try {
            query = "select * from CART where UserID=? order by CART.ID desc";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
            	Cart order = new Cart();
                ProductDAO productDao = new ProductDAO(this.con);
                int pId = rs.getInt("ProductID");
                Product product = productDao.getProductByID(Integer.toString(pId));
                order.setOrderID(rs.getInt("ID"));
                order.setId(pId);
                order.setName(product.getName());
                order.setPrice(product.getPrice()*rs.getInt("Quantity"));
                order.setQuantity(rs.getInt("Quantity"));
                order.setDateCreate(rs.getString("DateCreate"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void cancelOrder(int id) {
        //boolean result = false;
        try {
            query = "delete from CART where ID=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            //result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        //return result;
    }
}
