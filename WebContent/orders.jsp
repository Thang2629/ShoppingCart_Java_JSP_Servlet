<%@page import="java.text.DecimalFormat"%>
<%@page import="dao.*"%>
<%@page import="context.DBContext"%>
<%@page import="entity.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%
	DecimalFormat dcf = new DecimalFormat("#.##");
	request.setAttribute("dcf", dcf);
	Account auth = (Account) request.getSession().getAttribute("acc");
	List<Cart> orders = null;
	if (auth != null) {
	    request.setAttribute("person", auth);
	    OrderDAO orderDao  = new OrderDAO(DBContext.getConnection());
		orders = orderDao.userOrders(auth.getID());
	}else{
		response.sendRedirect("Login.jsp");
	}
	ArrayList<Item> cart_list = (ArrayList<Item>) session.getAttribute("cart-list");
	if (cart_list != null) {
		request.setAttribute("cart_list", cart_list);
	}
	
	%>
<!DOCTYPE html>
<html>
<head><meta name="viewport" content="width=device-width,initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>E-Commerce Cart</title>
</head>
<body>
	<jsp:include page="Menu.jsp"></jsp:include>

	<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			
			<%
			if(orders != null){
				for(Cart o:orders){%>
					<tr>
						<td><%=o.getDateCreate() %></td>
						<td><%=o.getName() %></td>
						<td><%=o.getQuantity() %></td>
						<td><%=dcf.format(o.getPrice()) %></td>
						<td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=o.getOrderID()%>">Cancel Order</a></td>
					</tr>
				<%}
			}
			%>
			
			</tbody>
		</table>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>

</body>
</html>