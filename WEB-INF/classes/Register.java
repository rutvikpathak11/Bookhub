import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;




public class Register extends HttpServlet 
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
	
	out.println("<h3>Please fill form to register Customer</h3>");
	out.println("<form  method='Post' action='registerdata'>");
	out.println("<table>");
	out.println("<tr>");
	out.println("<td>");
	out.println("Name :");
	out.println("<input type='text' name='fname' required/></td>");
	out.println("</tr>");
	out.println("<tr>");
	out.println("<td>");
	out.println("User Type  :");
	out.println("<select name='userType'> "
				 +" <option value='Customer' >Customer</option> "
				 +" <option value='StoreManager' >Store Manager</option> "
				 +" <option value='Salesman' >Salesman</option> "
				 +" </select>");
	out.println("</tr>");
	out.println("<tr>");
	
	out.println("<tr>");
	out.println("<td>");
	out.println("User Id &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp :");
	out.println("<input type='text' name='userid' required/></td>");
	out.println("</tr>");
	out.println("<tr>");
	out.println("<td>");
	out.println("Email Id&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp :");
	out.println("<input type='text' name='email' required/></td>");
	out.println("</tr>");
	out.println("<tr>");
	out.println("<td>");
	out.println("Password&nbsp&nbsp&nbsp:");
	out.println("<input type='password' name='password' required/></td>");
	out.println("</tr>");
	out.println("<tr><td ><input type='submit' value='Register'></td></tr>");
	out.println("</table/>");
	out.println("</form>");
	
	
}
}
