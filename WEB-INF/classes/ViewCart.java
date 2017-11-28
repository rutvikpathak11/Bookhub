import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.Iterator;



public class ViewCart extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
	  
	 PrintWriter out = response.getWriter();
	response.setContentType("text/html;charset=UTF-8");
	out.println("<html><head>");
	out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
	out.println("<title>Book Hub</title>");
	out.println("<link rel='shortcut icon' href='cart/icon.jpg'/>");
	out.println("<link rel='stylesheet' href='styles.css' type='text/css' />");
	//carousel bootstrap
	out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
	out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>");
	out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
	out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>");
	
	out.println("</head>");
	out.println("<body>");
	out.println("<div id='container'>");
	out.println("<header>");
	out.println("<h1><a href='home'>Book <span> Hub</span></a></h1>");
	out.println("</header>");
	out.println("<nav>");
	out.println("<ul>");
	out.println("<li  class='start selected'><a href='home'>Home</a></li>");
	//out.println("<li class=''><a href='smartwatches'>Smart Watches</a></li>");
	//out.println("<li class=''><a href='speakers'>speakers</a></li>");
	//out.println("<li class=''><a href='headphones'>headphones</a></li>");
	//out.println("<li class=''><a href='phones'>Phones</a></li>");
	//out.println("<li class=''><a href='laptops'>Laptops</a></li>");
	//out.println("<li class=''><a href='externalstorage'>External Storage</a></li>");
	
	HttpSession session = request.getSession();
	String fname=(String)session.getAttribute("fname");
	
	if (fname == null)
	{
		out.println("<li class=''><a href='register'>Register</a></li>");
		out.println("<li class='' ><a href='login'>Sign in</a></li>");
	}
	else
	{
		out.println("<li class=''><a href='#'>Hello  "+fname+"</a></li>");
		out.println("<li class='' ><a href='signout'>Sign Out</a></li>");
	}

	
	out.println("<li class='' ><a href='vieworders'>View Orders</a></li>");
	
	
	out.println("<div align='right'>");
	out.println("<form action='viewcart'>");
	out.println("<button type='submit' style='background-color:transparent'><img src='images/cart.png' width = '60px' height = '63px'></button>");
	out.println("</form>");
	out.println("</div>");
	out.println("</ul>");
	out.println("</nav>");
	  
        Cart ekart;
        ekart = (Cart) session.getAttribute("cart");
			
			if(ekart==null )
				
				{
			out.println("<h1>Cart is Empty </h1>");
			out.println("<tr>");
			out.println("<td>");
			out.println("</td>");
			out.println("</tr>");
					
				}
					
else
{
        
            
            
            //HashMap<String, List<Integer>> items = ekart.getCartItems();
			HashMap<String,Float> items = ekart.getCartItems();
			List<Products> productList = ekart.getProductList();
			List<String> accessoryList = null;
			if(productList != null)
				accessoryList = (productList.get(productList.size()-1)).getAccessoryList();
			
			SAXParserDataStore fetchProductList = new SAXParserDataStore();
			List<Products> accessoryProductList = fetchProductList.getAccessoriesList();
						
						if(items.isEmpty())
						{
							
							out.println("<h1>Cart is Empty </h1>");
							out.println("<tr>");
							out.println("<td>");
							out.println("</td>");
							out.println("</tr>");
							
						}
			else
				{
            
			
			out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>result</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Items Currently in  cart </h1>");
            out.println("<hr>");
			out.println("<table  frame='box' rules='rows'>");
           out.println("<tr><td></td><td>Product</td><td>Price&nbsp&nbsp&nbsp&nbsp</td><td>Quantity</td><td></td>");

			for(Products p : productList)
		   {
				out.println("<form action='deleteitem'><input type='hidden' name='name' value='"+p.getName()+"'>");
                out.println("<tr><td><img src ='" +p.getImage()+ "' width = '100' height = '100'></td><td>"+p.getName()+"  </td><td>"+"$"+p.getPrice()+"</td>");
				out.println("<td><select name='"+p.getName()+"'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td>");
				out.println("<td><input type='submit' name='action1' value='RemoveItem'></td></tr>");
			}
			
			
			out.println("<tr ><td align='center' colspan='5'>Cart Total:" +ekart.getCartTotal()+ "</td></tr>");	
			out.println("<tr ><td align='center' colspan='5'><input type='submit' name='action1' value='CheckOut'></td></tr></form>");				  
			out.println("</table>");
			
			//Start of Carousel code
			out.println("<tr><div id='myCarousel' class='carousel slide' data-ride='carousel'> "+
						
						
						"<div class='carousel-inner'>");

					   //Wrapper for slides
					   int i=0;
						if(accessoryList != null)
					   for(String s : accessoryList)
					   {
						   for(Products p:accessoryProductList)
						   {
							if(s.equalsIgnoreCase(p.getName()))
							{
								//out.println("Test here");
								if(i==0)
								{
								out.println("<div class='item active'>"+
											"<div style='text-align: center'>"+
												  "<img class='product-image' src='" +p.getImage()+ "'>"+
											"</div>"+
											"<div style='text-align: center'>"+
													p.getName()+
													"<br/>$" +p.getPrice()+
														"<form  method = 'get' action = 'addtocart' style='text-align: center'>" +
															"<input class = 'submit-button' type = 'submit' value='Buy Now'>" +
															"<input type='hidden' name='product' value='"+p+"'>" +
														"</form>"+
												"</div>"+
											  "</div>");
											
											++i;
								}
								else
								{
									out.println("<div class='item'>"+
											"<div style='text-align: center'>"+
												  "<img class='product-image' src='" +p.getImage()+ "'>"+
											"</div>"+
											"<div style='text-align: center'>"+
													p.getName()+
													"<br/>$" +p.getPrice()+
														"<form  method = 'get' action = 'addtocart' style='text-align: center'>" +
															"<input class = 'submit-button' type = 'submit' value='Buy Now'>" +
															"<input type='hidden' name='product' value='"+p+"'>" +
														"</form>"+
												"</div>"+
											  "</div>");
								}
							}
						   }
					   }
						
						/*
						 "<div class='col-xs-12 col-sm-4 col-md-2'>"+
						 "<div class='col-xs-12 col-sm-4 col-md-2 cloneditem-1'>"+
						out.println("<input class = 'submit-button' type = 'label' value='$" +p.getPrice()+ "' align='center'>" +
														"<form class = 'submit-button' method = 'get' action = 'addtocart'>" +
													"<input class = 'submit-button' type = 'submit' value='Buy Now' align='center'>" +
													"<input type='hidden' name='product' value='"+p+"'>" +
												"</form>");
						"<img class='product-image' src='" +p.getImage()+ "' alt='" +p.getImage()+ "'>" +
						<div class='item active'>"+
												"<img src='" +p.getImage()+ "' alt='Los Angeles'>" +
											"</div>");
						
"<div class='item'>"+
												"<img src='" +p.getImage()+ "' alt='Chicago'>"+
											"</div>"
						"<div class='item'>"+
						  "<img src='images/chicago.jpg' alt='Chicago'>"+
						"</div>"+ */

					  //Left and right controls
					  out.println("<a class='left carousel-control' href='#myCarousel' data-slide='prev'>"+
						"<span class='glyphicon glyphicon-chevron-left'></span>"+
						"<span class='sr-only'>Previous</span>"+
					  "</a>"+
					  "<a class='right carousel-control' href='#myCarousel' data-slide='next'>"+
						"<span class='glyphicon glyphicon-chevron-right'></span>"+
						"<span class='sr-only'>Next</span>"+
					  "</a>"+
					"</div>");
					
			
			
            out.println("</body>");
            out.println("</html>");
			

}
			
			
	  }}

}



