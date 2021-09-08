package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import context.*;
import dao.*;
import entity.*;

@WebServlet("/order-now")
public class OrderNowControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            Account auth = (Account) request.getSession().getAttribute("acc");

            if (auth != null) {
                String productId = request.getParameter("id");
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                if (productQuantity <= 0) {
                	productQuantity = 1;
                }
                Cart orderModel = new Cart();
                orderModel.setId(Integer.parseInt(productId));
                orderModel.setUserID(auth.getID());
                orderModel.setQuantity(productQuantity);
                orderModel.setDateCreate(formatter.format(date));

                OrderDAO orderDao = new OrderDAO(DBContext.getConnection());
                boolean result = orderDao.insertOrder(orderModel);
                if (result) {
                    ArrayList<Item> cart_list = (ArrayList<Item>) request.getSession().getAttribute("cart-list");
                    if (cart_list != null) {
                        for (Item c : cart_list) {
                            if (c.getId() == Integer.parseInt(productId)) {
                                cart_list.remove(cart_list.indexOf(c));
                                break;
                            }
                        }
                    }
                    response.sendRedirect("orders.jsp");
                } else {
                    out.println("order failed");
                }
            } else {
                response.sendRedirect("Login.jsp");
            }

        } catch (ClassNotFoundException|SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
