import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.HashMap;
import java.text.SimpleDateFormat;


public class DeleteItem extends HttpServlet {public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
	
			 String action = request.getParameter("action1");
			
			
		if(action.equals("RemoveItem"))
		{
					
						
			String name = request.getParameter("name");
			Cart ekart;
			out.println("Item deleted:"+ name);
			ekart = (Cart) session.getAttribute("cart");
			ekart.deleteFromCart(name);
			session.setAttribute("cart", ekart);
			ekart = (Cart) session.getAttribute("cart");
			 List<Products> productList = ekart.getProductList();
			 {
			  
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Servlet deleteItem</title>");            
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Items Currently in  cart </h1>");
				out.println("<hr>");
				out.println("<h2>Cart</h2>");
				Map<String, Float> items = ekart.getCartItems();
			   out.println("<table  frame='box' rules='rows'>");
			   out.println("<tr><td></td><td>Product</td><td>Price&nbsp&nbsp&nbsp&nbsp</td><td>Quantity</td><td></td>");
				for(Products p : productList)
				{
					out.println("<form action='deleteitem'><input type='hidden' name='name' value='"+p.getName()+"'>");
					out.println("<tr><td><img src ='" +p.getImage()+ "' width = '100' height = '100'></td><td>"+p.getName()+"  </td><td>"+"$"+p.getPrice()+"</td>");
					out.println("<td><select name='"+p.getName()+"'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td>");
					out.println("<td><input type='submit' name='action1' value='RemoveItem'></td></tr>");
				}
			   out.println("<tr ><td align='center' colspan='4'><input type='submit' name='action1' value='CheckOut'></td></tr></form>");				  
			   
				out.println("<tr>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
			   
				out.println("<table>");
				out.println("</body>");
				out.println("</html>");
			  
			}   
					
					
		}

		if(action.equals("CheckOut"))		
		{
			Cart ekart;
			ekart = (Cart) session.getAttribute("cart");
			Float amount = ekart.getCartTotal();
			/* HashMap<String, Float> items = ekart.getCartItems();
		  for(Map.Entry<String, Float> entry : items.entrySet()){
					String key = entry.getKey();
					Float values = entry.getValue();
				int quantity = Integer.parseInt(request.getParameter(key));
					//ekart.addToCart(key,values.get(0),quantity);
						}
						
						int amount=0;
						Float price=0.0f, quantity=0.0f;
						 for(Map.Entry<String, Float> entry : items.entrySet()){
					String key = entry.getKey();
					Float values = entry.getValue();
							price=values;
							//quantity=values;
							amount=amount + price*quantity;
						} */
						
						
						
						String userid=(String)session.getAttribute("fname");
						fname=(String)session.getAttribute("fname");
							if(fname==null)
							{
								out.println("<h1>Please Login to Checkout!!!!!!!!!!!!!!!</h1>");
							}
							
							else
							{
								
								out.println("<!DOCTYPE html>");
								out.println("<html>");
								out.println("<body>");
								out.println("<form method = 'get' action = 'checkoutprocess'>");
			
				out.println("<table><tr>");
				if(fname.equals("SalesMngr"))
							{
								out.println("<td> User Id</td>");
					out.println("<td><input type='text' name='userid'  required></td>");
					out.println("</tr>");
								
							}
							
							else
							{
					out.println("<td> User Id</td>");
					out.println("<td><input type='text' name='userid' value="+userid+" required></td>");
					out.println("</tr>");
							}
					out.println("<tr>");
					out.println("<td> Full Name</td>");
					out.println("<td><input type='text' name='fullname' required></td>");
					out.println("</tr>");
				
					
						out.println("<tr>");
					out.println("<td> Address:</td>");
					out.println("<td><input type='text' name='addr' required></td>");
					out.println("</tr>");
					
						out.println("<tr>");
					out.println("<td> City </td>");
					out.println("<td><input type='text' name='city' required></td>");
					out.println("</tr>");
					
						out.println("<tr>");
					out.println("<td> State </td>");
					out.println("<td><input type='text' name='state' required></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Zip </td>");
					out.println("<td><input type='text' name='zip' required></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Country </td>");
					out.println("<td><input type='text' name='country' required></td>");
					out.println("</tr>");
				
				
					out.println("<tr>");
					out.println("<td> Amount Payable </td>");
					out.println("<td>$"+amount+"</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Credit Card Details:-</td>");
					out.println("<td><input type='text'  maxlength='4' size='2' required><input type='text'  maxlength='4' size='2' required><input type='text'  maxlength='4' size='2' required><input type='text' maxlength='4' size='2' required>&nbsp&nbspValid Till Month<input type='text'  maxlength='2' size='2'>&nbsp&nbspYear<input type='text'  maxlength='4' size='4' required>&nbsp&nbspCVV<input type='password'  maxlength='3' size='4' required></td>");
					out.println("</tr>");
		
		
		
					out.println("<tr><td align='center' colspan='2'><input type='submit' value='Make Payment'></td></tr>"); 
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
					
								
								
							}
						
					
		}
				
				
				
			
}
}