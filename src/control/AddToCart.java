package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Item;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet(name = "AddToCartControl", urlPatterns = "/add-to-cart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()) {
			ArrayList<Item> cartList = new ArrayList<>();
			
			int id = Integer.parseInt(request.getParameter("id"));
			Item i = new Item();
			i.setId(id);
			i.setQuantity(1);
			
			HttpSession session = request.getSession();
			ArrayList<Item> item_list = (ArrayList<Item>) session.getAttribute("cart-list");
			if (item_list == null) {
                cartList.add(i);
                session.setAttribute("cart-list", cartList);
                response.sendRedirect("home");
            } else {
                cartList = item_list;

                boolean exist = false;
                for (Item it : item_list) {
                    if (it.getId() == id) {
                        exist = true;
                        out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>GO to Cart Page</a></h3>");
                    }
                }

                if (!exist) {
                    cartList.add(i);
                    response.sendRedirect("home");
                }
            }
		}catch(Exception e) {
			
		}
	}

}
