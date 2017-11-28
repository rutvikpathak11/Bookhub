import java.io.*;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.*;


public class ForgotPassword extends HttpServlet {
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
	
	//FORGOT PASSWORD STARTS FROM HERE...RUTVIK	
	
	out.println("<form method='post' action='ForgotPassword'>");
	out.println("<table>");
	out.println("<tr>");
	out.println("<td>");
	out.println("User Id &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp :");
	out.println("<input type='text' name='userid' required/></td>");
	out.println("</tr>");
	out.println("<tr>");
	out.println("<td>");
	out.println("New Password &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp :");
	out.println("<input type='password' name='newpassword' required/></td>");
	out.println("</tr>");
	/*
	out.println("<tr>");
	out.println("<td>");
	out.println("Password&nbsp&nbsp&nbsp:");
	out.println("<input type='password' name='password' required/></td>");
	out.println("</tr>");
	*/
	out.println("<tr><td ><input type='submit' value='Change Password'></td></tr>");
	out.println("</table/>");
	out.println("</form>");
	
	//FORGOT PASSWORD ENDS FROM HERE...RUTVIK
	
}


public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
  {
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
	
	//FORGOT PASSWORD CODE STARTS FROM HERE...RUTVIK
	
	String userid = request.getParameter("userid");
	String newpassword = request.getParameter("newpassword");
	
	//SQL code to change password in user details from table
	MySQLDataStoreUtilities obj = new MySQLDataStoreUtilities();
	int exist=obj.changePassword(userid,newpassword);
	
	if(exist==1)
	{
	out.println("<h3>Your password has been changed successfully !</h3>");
	out.println("<br>");
	out.println("<a href='login'>Click here to login again !</a>");
	}
	
	//FORGOT PASSWORD CODE ENDS HERE...RUTVIK
}


}
	