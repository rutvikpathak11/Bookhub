import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.*;
import java.util.*;
import java.util.Date;

public class ViewOrders extends HttpServlet 
{	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
{
	PrintWriter out = response.getWriter();
	response.setContentType("text/html;charset=UTF-8");
	out.println("<html><head>");
	out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
	out.println("<title>Book Hub</title>");
	out.println("<link rel='shortcut icon' href='cart/icon.jpg'/>");
	out.println("<link rel='stylesheet' href='styles.css' type='text/css' />");
	out.println("</head>");
	out.println("<body>");
	out.println("<div id='container'>");
	out.println("<header>");
	out.println("<h1><a href='/'>Book <span> Hub</span></a></h1>");
	out.println("</header>");
	out.println("<nav>");
	out.println("<ul>");
	out.println("<div align='right'>");
	HttpSession session = request.getSession();
	String fname=(String)session.getAttribute("fname");
	
	if (fname == null)
	{
	out.println("<li class=''><a href='register'>Register</a></li>");
	out.println("<li class='' ><a href='login'>Sign in</a></li>");
	}
	else if(fname.equals("SalesMngr"))
	{
		  out.println("<li class=''><a href='register'>Register Customer</a></li>");
		  out.println("<li class=''><a href='#'>Hello  "+fname+"</a></li>");  
		  out.println("<li class='' ><a href='signout'>Sign Out</a></li>");
	}
	else
	{
		out.println("<li class=''><a href='#'>Hello  "+fname+"</a></li>");
		out.println("<li class='' ><a href='signout'>Sign Out</a></li>");
	}
	
	out.println("<li class='' ><a href='vieworders'>View Orders</a></li>");
	out.println("<form action='viewcart'>");
	out.println("<button type='submit' style='background-color:transparent'><img src='images/cart.png' width = '60px' height = '63px'></button>");
	out.println("</form>");
	out.println("</div>");
	out.println("</ul>");
	out.println("</nav>");
	
	out.println("<nav>");
	out.println("<ul>");
	out.println("<li  class=''><a href='home'>Home</a></li>");
	out.println("<li class=''><a href='Technology'>Technology</a></li>");
	out.println("<li class=''><a href='Comics'>Comics</a></li>");
	out.println("<li class=''><a href='Autobiography'>Autobiography</a></li>");
	out.println("<li class=''><a href='Sports'>Sports</a></li>");
	out.println("<li class=''><a href='Education'>Education</a></li>");
	out.println("<li class=''><a href='Religion'>Religion</a></li>");
	out.println("</ul>");
	out.println("</nav>");
	
	if(fname==null)
		{
			out.println("<h1>Please Login to View Orders!!!!!!!!!!!!!!!</h1>");
			
		}
		 
	else
	{
		String userid=(String)session.getAttribute("userid");	
		MySQLDataStoreUtilities dbObject = new MySQLDataStoreUtilities();
		List<OrderDetails> orderList = dbObject.getUserOrders(userid);
		OrderDetails order = null;
				
		/* FileInputStream fis = new FileInputStream("C:\\apache-tomcat-7.0.34\\webapps\\csj1\\OrderDetails.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		
		try
		{
			boolean flag=true;
			//orderList = (ArrayList<OrderDetails>)ois.readObject();
			//out.println("<Div>" +orderList+ "</div>");
			 while(flag)
			{
					order = (OrderDetails)ois.readObject();
					//out.println("<Div>" +order+ "</div>");
					if((order != null && order.getUserId().equalsIgnoreCase(userid)) || (order != null && order.getUserId().equalsIgnoreCase("SalesMngr")))
						orderList.add(order);
					else
						flag=false;
			} 
			
			if(orderList != null)
			session.setAttribute("orderList",orderList);
			ois.close();
			fis.close();
					
			/* while((order = (OrderDetails)ois.readObject()) != null)
			{
				if(order.getUserId().equalsIgnoreCase(fname.trim()))
					orderList.add(order);
			} 
		//out.println(orderList);
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
		}
		 */
		if(orderList.size() < 1)
		{
			out.println("<h3>There are no Orders for this Userid<h3>");
		}
		else
		{
			String Ordernum="",productname="",price="",quantity="",shipping="",delivery;
							
							out.println("<table>");
							//out.println("orderlist: "+orderList.size()+" "+orderList);
							out.println("<tr><td></td><td>User Id</td><td>Order Num</td><td>Product Name</td><td>Price</td><td>Quantity</td><td>Delivery Date</td><td>Shipping Address</td></tr>");
								for(int i=0 ; i<orderList.size(); i++) 
								{
									order = orderList.get(i);
									out.println("<form action='cancelorder'>");
									out.println("<input type='hidden' name='userid' value='"+userid+"'>");
									out.println("<input type='hidden' name='product' value='"+order.getModelName()+"'>");
									out.println("<input type='hidden' name='ordernum' value='"+order.getOrderNo()+"'>");
									out.println("<input type='hidden' name='delivery' value='"+order.getDeliveryDate()+"'>");
									out.println("<td></td>");
									out.println("<td>"+order.getUserId()+"</td>");
									out.println("<td>"+order.getOrderNo()+"</td>");
									out.println("<td>"+order.getModelName()+"</td>");
									out.println("<td>"+order.getPrice()+"</td>");
									out.println("<td>"+1+"</td>");
									out.println("<td>"+order.getDeliveryDate()+"</td>");
									out.println("<td>"+order.getShippingAddress()+"</td>");
									out.println("<td><input type='submit'  value='Cancel Order'></td></tr>");
									out.println("</form>");		
								}
							out.println("</table>");	
		}
		 
	}
}
}