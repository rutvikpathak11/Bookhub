import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class Education extends HttpServlet 
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  {  
	SAXParserDataStore fetchProductList = new SAXParserDataStore();
	List<Products> speakerList = fetchProductList.getEducationList();
	
	
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
	out.println("<li class=''><a href='Autobiography'>Autobiography</a></li>");
	out.println("<li class=''><a href='Religion'>Religion</a></li>");
	out.println("<li class=''><a href='Sports'>Sports</a></li>");
	out.println("<li class=''><a href='Comics'>Comics</a></li>");
	out.println("<li class=''><a href='Education'>Education</a></li>");
	out.println("</ul>");
	out.println("</nav>");
	
	out.println("<img class='header-image' src='images/img_index1.jpg' width = '100%' height = '100%' alt='Index Page Image' />");
	
	out.println("<div id='body'>");
	out.println("<section id='content'>");
	out.println("<article>");
	out.println("<h2>Educational Books</h2>" +
				"<table>");
	
	/* out.println("Test 4");
		out.println(speakerList);
		out.println(SAXParserDataStore.productMap); */
	
	Products p,p2;
	 for(int i = 0;i<speakerList.size();i++)
	{
		p = speakerList.get(i);
		session.setAttribute("addtocart",p);
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
	
	out.println("</table>");
	out.println("</article>");
	out.println("</section>");
	out.println("<aside class='sidebar'>");
	out.println("<ul>");	
	out.println("<li>");
	out.println("<h4>Products</h4>");
	out.println("<ul>");
	out.println("<li><a href='Technology'>Technology</a></li>");
	out.println("<li><a href='Autobiography'>Autobiography</a></li>");
	out.println("<li><a href='Religion'>Religion</a></li>");
	out.println("<li><a href='Sports'>Sports</a></li>");
	out.println("<li><a href='Comics'>Comics</a></li>");
	out.println("<li><a href='Education'>Education</a></li>");
	out.println("</ul>");
	out.println("</li>");	
	out.println("<li>");
	out.println("<h4>About us</h4>");
	out.println("<ul>");
	out.println("<li class='text'>");
	out.println("<p style='margin: 0;'>This is a sample website created to demonstrate a standard enterprise web page.</p>");
	out.println(" </li>");
	out.println("</ul>");
	out.println("</li>");	
	
	//Start of search feature
	out.println("<li>");
	out.println("<h4>Search site</h4>");
	out.println("<ul>");
	out.println("<li class='text'>");
	out.println("<form method='get' class='searchform' action='search'>");
	out.println("<p>");
	out.println("<input type='text' size='25' name='searchterm' class='s' placeholder='Search...'>");
	out.println("</p>");	                     

	out.println("<input type='checkbox' name='categories' value='Technology' checked> <strong>Technology</strong><br>");
        out.print("<input type='checkbox' name='categories' value='Comics' checked> <strong>Comics</strong><br>");
        out.print("<input type='checkbox' name='categories' value='Autobiography' checked> <strong>Autobiography</strong><br>");
        out.print("<input type='checkbox' name='categories' value='Sports' checked> <strong>Sports</strong><br>");
        out.print("<input type='checkbox' name='categories' value='Education' checked> <strong>Education</strong><br>");
        out.print("<input type='checkbox' name='categories' value='Religion' checked> <strong>Religion</strong><br>");         
        out.println("  </form>");
        out.println("</li></ul></li>");
	//End of search feature

	out.println("<li>");	
	out.println("<h4>Helpful Links</h4>");	
	out.println("<ul>");	
	out.println("<li><a href='http://www.w3schools.com/html/default.asp' title='premium templates'>Learn HTML here</a></li>");	
	out.println("<li><a href='http://www.w3schools.com/css/default.asp' title='web hosting'>Learn CSS here</a></li>");	
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
