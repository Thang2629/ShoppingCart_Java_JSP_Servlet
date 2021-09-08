/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


//import context.DBContext;
//import entity.Category;
import entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {

    private Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ProductDAO(Connection con ) {
    	this.conn = con;
    }
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Products";
        try {
           
            ps = this.conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

//    public List<Category> getAllCategory() {
//        List<Category> list = new ArrayList<>();
//        String query = "select * from Category";
//        try {
//            new ConnectionUtils();
//			conn = ConnectionUtils.getConnection();//mo ket noi voi sql
//            ps = conn.prepareStatement(query);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new Category(rs.getInt(1),
//                        rs.getString(2)));
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }

    public Product getLast() {
        String query = "select * from Products Order by Id DESC LIMIT 1";
        try {
           
            ps = this.conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public Product getProductByID(String id) {
        String query = "select * from Products where Id = ?";
                
        try {
           
            ps = this.conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }
    public List<Item> getCartProducts(ArrayList<Item> cartList) {
    	List<Item> clothes = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (Item item : cartList) {
                	String query = "select * from Products where Id = ?";
                	ps = this.conn.prepareStatement(query);
                	ps.setInt(1, item.getId());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Item row = new Item();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setPrice(rs.getDouble("price")*item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        clothes.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return clothes;
    	
    }
    public double getTotalCartPrice(ArrayList<Item> cartList) {
        double sum = 0;
        try {
            if (cartList.size() > 0) {
                for (Item item : cartList) {
                    String query = "select Price from Products where Id=?";
                    ps = this.conn.prepareStatement(query);
                    ps.setInt(1, item.getId());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        sum+=rs.getDouble("Price")*item.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }
    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Products where Name like ?";
        try {
          
            ps = this.conn.prepareStatement(query);
            ps.setString(1,"%"+ txtSearch+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public void deleteProduct(String id) {
    	String query = "delete from Products where Id = ?";
        try {
           
            ps = this.conn.prepareStatement(query);
            ps.setString(1,id);
           ps.executeUpdate();
        }catch(Exception e) {
        	
        }
    }
    public void insertProduct(String name, String image, String price,
            String title, String description) {
        String query = "INSERT Products \n"
                + "(Name, Image, Price, Title, Description)\n"
                + "VALUES(?,?,?,?,?)";
        try {
           
            ps = this.conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void editProduct(String name, String image, String price,
            String title, String description,String pid) {
        String query = "UPDATE Products SET Name = ?, Image = ?, Price = ?, Title = ?, Description = ? WHERE Id = ?";
        try {
          
            ps = this.conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
