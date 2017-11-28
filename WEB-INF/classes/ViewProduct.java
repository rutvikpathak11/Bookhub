import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class ViewProduct extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  
    PrintWriter out = response.getWriter();
	response.setContentType("text/html;charset=UTF-8");
	out.println("<html><head>");
	out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
	out.println("<title>Book Hub</title>");
	out.println("<link rel='shortcut icon' href='cart/icon.jpg'/>");
	out.println("<script type='text/javascript' src='javascript.js'></script>");
	out.println("<link rel='stylesheet' href='styles.css' type='text/css' />");
	out.println("</head>");
	out.println("<body onload='init()'>");
	out.println("<div id='container'>");
	out.println("<header>");
	out.println("<h1><a href='home'>Book <span> Hub</span></a></h1>");
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
	
	//search feature
	out.println("  <form name='autofillform' action='autocomplete'>");
      out.println("<table border='0' cellpadding='5'> ");
      out.println("  <tbody> ");
          out.println("<tr>");
            out.println("<td><strong>Search :  </strong>");
                      
                       out.println(" <input type='text' size='40' id='complete-field' onkeyup='doCompletion()' autocomplete='off'></td>");
                     
        out.println("</tr>");
          out.println("<tr>");
             out.println("<td id='auto-row' colspan='2'>");
                out.println("<table id='complete-table' class='popupBox' />");
              out.println("</td>");
          out.println("</tr>");
        out.println("</tbody>");
     out.println(" </table>");
  out.println("  </form>");
	//End of search feature
	
	out.println("<img class='header-image' src='images/img_index1.jpg' width = '100%' height = '100%' alt='Index Page Image' />");
	out.println("<div id='body'>");
	out.println("<section id='content'>");
	out.println("<article>");
	
	//Start of product detail tab
	String product = request.getParameter("id");
	MySQLDataStoreUtilities DBObject = new MySQLDataStoreUtilities();
	List<Products> productList = DBObject.getProductList("");
	out.println("<table>");
	Products p,p2;

	//System.out.println("View Product 1: "+productList);
	for(int i = 0;i<productList.size();i++)
	{
		p = productList.get(i);
		//System.out.println("View Product 1: "+p);
		if(p.getName().equalsIgnoreCase(product))
		{
			session.setAttribute("addtocart",p);
			//System.out.println("View Product: "+p);
			//out.println("p name: "+p.getName());
			out.println("<tr>"+
					"	<td><b>" + p.getName() + "</b>  </td>" +
					"	<td><img class='product-image' src='" +p.getImage()+ "'></td>" +
					"	<td>$" + p.getPrice() + "</td>" +
					"<form class = 'submit-button' method = 'get' action = 'addtocart'>" +
					"<td><input class = 'submit-button' type = 'submit' value='Buy Now'></td>" +
					"<input type='hidden' name='product' value='"+p+"'>" +
					"</form>" +
					//Write Review form
					"<form class = 'submit-button' method = 'get' action = 'writereview'>" +
					"<td><input class = 'submit-button' type = 'submit' value='Write Review'></td>" +
					"<input type='hidden' name='product' value='"+p+"'>" +
					"<input type='hidden' name='productCategory' value='Speakers'>" +
					"</form>" +
					
					//View Review form
					"<form class = 'submit-button' method = 'get' action = 'viewreview'>" +
					"<td><input class = 'submit-button' type = 'submit' value='View Review'></td>" +
					"<input type='hidden' name='product' value='"+p+"'>" +
					"</form>" +
					"</tr>");
		}
	} 
	
	out.println("</table>");
	//End of product detail tab
	
	out.println("</article>");
	out.println("</section>");
	
	out.println("<aside class='sidebar'>");
	out.println("<ul>");	
	out.println("<li>");
	out.println("<h4>Products</h4>");
	out.println("<ul>");
	out.println("<li><a href='Technology'>Technology</a></li>");
	out.println("<li><a href='Comics'>Comics</a></li>");
	out.println("<li><a href='Autobiography'>Autobiography</a></li>");
	out.println("<li><a href='Sports'>Sports</a></li>");
	out.println("<li><a href='Education'>Education</a></li>");
	out.println("<li><a href='Religion'>Religion</a></li>");
	out.println("</ul>");
	out.println("</li>");

	//Start of Trending tab
	out.println("<li>");
	out.println("<a href='trendingproducts'><h4>Trending Products</h4></a>");
	out.println("</li>");
	//End of trending tab.
	
	out.println("<li>");
	out.println("<h4>About us</h4>");
	out.println("<ul>");
	out.println("<li class='text'>");
	out.println("<p style='margin: 0;'>This is a  website created to demonstrate a working of online commercial websites.</p>");
	out.println(" </li>");
	out.println("</ul>");
	out.println("</li>");	
	out.println("<li>");
	out.println("<h4>Search Products</h4>");
	out.println("<ul>");
	out.println("<li class='text'>");
	out.println("<form method='get' class='searchform' action='#'>");
	out.println("<p>");
	out.println("<input type='text' size='25' value='' name='s' class='s' />");
	out.println("</p>");	
	out.println("</form></li></ul></li>");	     	
	out.println("<li>");	
	out.println("<h4>Helpful Links</h4>");	
	out.println("<ul>");	
	out.println("<li><a href='mailto:rambani@hawk.iit.edu' title='premium templates'>Customer Care Support Email</a></li>");	
	out.println("<li>Phone: (123)-456-7890</li>");	
	out.println("</ul></li></ul></aside>");	
	out.println("<div class='clear'></div>");
	out.println("</div>");	
	out.println("<footer>");	
	out.println("<div class='footer-content'>");	
	out.println("<div class='clear'></div>");	
	out.println("</div>");	
	out.println("<div class='footer-bottom'>");	
	out.println("<p>Book Hub - Enterprise Web Application </p>");	
	out.println("</div>");	
	out.println("</footer>");	
	out.println("</div>");	
	out.println("</body>");	
	out.println("</html>");	

	} 
	
	//post method
	public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  
    PrintWriter out = response.getWriter();
	response.setContentType("text/html;charset=UTF-8");
	out.println("<html><head>");
	out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
	out.println("<title>Book Hub</title>");
	out.println("<link rel='shortcut icon' href='cart/icon.jpg'/>");
	out.println("<script type='text/javascript' src='javascript.js'></script>");
	out.println("<link rel='stylesheet' href='styles.css' type='text/css' />");
	out.println("</head>");
	out.println("<body onload='init()'>");
	out.println("<div id='container'>");
	out.println("<header>");
	out.println("<h1><a href='home'>Book <span> Hub</span></a></h1>");
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
	
	//search feature
	out.println("  <form name='autofillform' action='autocomplete'>");
      out.println("<table border='0' cellpadding='5'> ");
      out.println("  <tbody> ");
          out.println("<tr>");
            out.println("<td><strong>Search :  </strong>");
                      
                       out.println(" <input type='text' size='40' id='complete-field' onkeyup='doCompletion()' autocomplete='off'></td>");
                     
        out.println("</tr>");
          out.println("<tr>");
             out.println("<td id='auto-row' colspan='2'>");
                out.println("<table id='complete-table' class='popupBox' />");
              out.println("</td>");
          out.println("</tr>");
        out.println("</tbody>");
     out.println(" </table>");
  out.println("  </form>");
	//End of search feature
	
	out.println("<img class='header-image' src='images/img_index1.jpg' width = '100%' height = '100%' alt='Index Page Image' />");
	out.println("<div id='body'>");
	out.println("<section id='content'>");
	out.println("<article>");
	out.println("<h2>Welcome to Book Hub</h2>");
	out.println("<p>Book Hub offers variety of Electronic Devices</p>");
	out.println("<p>Shop at the best market rate</p>");
	
	//Start of product detail tab
	String Product = request.getParameter("id");
	MySQLDataStoreUtilities DBObject = new MySQLDataStoreUtilities();
	List<Products> productList = new ArrayList<Products>();
	out.println("<table>");
	Products p,p2;
	for(int i = 0;i<productList.size();i++)
	{
		p = productList.get(i);
		if(p.getName().equalsIgnoreCase("id"))
		{
			session.setAttribute("addtocart",p);
			System.out.println("View Product: "+p);
			out.println("p name: "+p.getName());
			out.println("<tr>"+
					"	<td><b>" + p.getName() + "</b>  </td>" +
					"	<td><img class='product-image' src='" +p.getImage()+ "'></td>" +
					"	<td>$" + p.getPrice() + "</td>" +
					"<form class = 'submit-button' method = 'get' action = 'addtocart'>" +
					"<td><input class = 'submit-button' type = 'submit' value='Buy Now'></td>" +
					"<input type='hidden' name='product' value='"+p+"'>" +
					"</form>" +
					//Write Review form
					"<form class = 'submit-button' method = 'get' action = 'writereview'>" +
					"<td><input class = 'submit-button' type = 'submit' value='Write Review'></td>" +
					"<input type='hidden' name='product' value='"+p+"'>" +
					"<input type='hidden' name='productCategory' value='Speakers'>" +
					"</form>" +
					
					//View Review form
					"<form class = 'submit-button' method = 'get' action = 'viewreview'>" +
					"<td><input class = 'submit-button' type = 'submit' value='View Review'></td>" +
					"<input type='hidden' name='product' value='"+p+"'>" +
					"</form>" +
					"</tr>");
		}
	} 
	
	out.println("</table>");
	//End of product detail tab
	
	out.println("</article>");
	out.println("</section>");
	
	out.println("<aside class='sidebar'>");
	out.println("<ul>");	
	out.println("<li>");
	out.println("<h4>Products</h4>");
	out.println("<ul>");
	out.println("<li><a href='Technology'>Technology</a></li>");
	out.println("<li><a href='Comics'>Comics</a></li>");
	out.println("<li><a href='Autobiography'>Autobiography</a></li>");
	out.println("<li><a href='Sports'>Sports</a></li>");
	out.println("<li><a href='Education'>Education</a></li>");
	out.println("<li><a href='Religion'>Religion</a></li>");
	out.println("</ul>");
	out.println("</li>");

	//Start of Trending tab
	out.println("<li>");
	out.println("<a href='trendingproducts'><h4>Trending Products</h4></a>");
	out.println("</li>");
	//End of trending tab.
	
	out.println("<li>");
	out.println("<h4>About us</h4>");
	out.println("<ul>");
	out.println("<li class='text'>");
	out.println("<p style='margin: 0;'>This is a  website created to demonstrate a working of online commercial websites.</p>");
	out.println(" </li>");
	out.println("</ul>");
	out.println("</li>");	
	out.println("<li>");
	out.println("<h4>Search Products</h4>");
	out.println("<ul>");
	out.println("<li class='text'>");
	out.println("<form method='get' class='searchform' action='#'>");
	out.println("<p>");
	out.println("<input type='text' size='25' value='' name='s' class='s' />");
	out.println("</p>");	
	out.println("</form></li></ul></li>");	     	
	out.println("<li>");	
	out.println("<h4>Helpful Links</h4>");	
	out.println("<ul>");	
	out.println("<li><a href='mailto:rambani@hawk.iit.edu' title='premium templates'>Customer Care Support Email</a></li>");	
	out.println("<li>Phone: (123)-456-7890</li>");	
	out.println("</ul></li></ul></aside>");	
	out.println("<div class='clear'></div>");
	out.println("</div>");	
	out.println("<footer>");	
	out.println("<div class='footer-content'>");	
	out.println("<div class='clear'></div>");	
	out.println("</div>");	
	out.println("<div class='footer-bottom'>");	
	out.println("<p>Book Hub - Enterprise Web Application </p>");	
	out.println("</div>");	
	out.println("</footer>");	
	out.println("</div>");	
	out.println("</body>");	
	out.println("</html>");	

	} 
}
