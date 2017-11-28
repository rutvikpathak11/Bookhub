import java.io.*;
import javax.servlet.*;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.*;



public class Login extends HttpServlet {
	
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

	out.println("<form  method='Post' action='login'>");
	out.println("<table>");
	out.println("<tr>");
	out.println("<td>");
	out.println("Login as&nbsp&nbsp");
	out.println("<input type='radio' name='usertype' value='customer' checked='checked'>&nbspCustomer&nbsp&nbsp&nbsp <input type='radio' name='usertype' value='storemanager'>&nbspStore Manager&nbsp&nbsp&nbsp <input type='radio' name='usertype' value='salesmanager'>&nbspSales Manager ");
	out.println("</td>");
	out.println("</tr>");
	out.println("<td>");
	out.println("User Id &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp :");
	out.println("<input type='text' name='userid' required/></td>");
	out.println("</tr>");
	out.println("<tr>");
	out.println("<td>");
	out.println("Password&nbsp&nbsp&nbsp:");
	out.println("<input type='password' name='password' required/></td>");
	out.println("</tr>");
	out.println("<tr><td ><input type='submit' value='Login'></td></tr>");
	
	//FORGOT PASSWORD STARTS FROM HERE...RUTVIK
	out.println("<br><br>");
	out.println("<tr><td><a href='ForgotPassword'><h3>Forgot Password !</h3></td></tr>");
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
	
	String customertype = request.getParameter("usertype");
	String userid1 = request.getParameter("userid");
	String password1 = request.getParameter("password");
	
	
	if(customertype.equals("storemanager") && userid1.equals("admin") && password1.equals("admin"))
	{
		session.setAttribute("fname","StoreMngr");
		session.setAttribute("userid","StoreMngr");
		RequestDispatcher rd = request.getRequestDispatcher("managestore");
		rd.forward(request,response);
	}
	else if(customertype.equalsIgnoreCase("salesmanager") && userid1.equals("admin") && password1.equals("admin"))
	{
		session.setAttribute("fname","SalesMngr");
		session.setAttribute("userid","SalesMngr");
		RequestDispatcher rd = request.getRequestDispatcher("home");
		rd.forward(request,response);
		
	}
	
	else
	{
		String userId = request.getParameter("userid");
		String password = request.getParameter("password");
		HashMap<String,User> userHashMap = new HashMap<String,User>();
		List<User> userList = new ArrayList<User>();
		User user = new User();
		
		/* FileInputStream fis = new FileInputStream(new File("C:\\apache-tomcat-7.0.34\\webapps\\csj1\\UserDetails.txt"));
		ObjectInputStream ois = null;
		
		try
		{
			ois = new ObjectInputStream(fis);
			while((user = (User)ois.readObject()) != null)
			{
				//ois = new ObjectInputStream(fis);
				userList.add(user);
				//out.println(userList);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		} */
		
	//SQL code to validate user details from table
	MySQLDataStoreUtilities dbObject = new MySQLDataStoreUtilities();
	if(dbObject.checkUserExist(userId))
	{
		if(dbObject.validateUser(userId,password))
		{
			session.setAttribute("fname",userId);
			session.setAttribute("userid",userId);
			RequestDispatcher rd = request.getRequestDispatcher("home");
			rd.forward(request,response);
		}
		else
		{
			out.println("<h3>Password is Incorrect / Please select appropriate User Type and try again  <h3>");
			//FORGOT PASSWORD STARTS FROM HERE...RUTVIK
	
			out.println("<br>");
			out.println("<a href='ForgotPassword'><h3>Forgot Password !</h3>");
		
	//FORGOT PASSWORD ENDS FROM HERE...RUTVIK
		}	
	}
	else
		out.println("<h1>User Id does not exist </h1>");
		
		/* boolean userExist = false;
		for(User u:userList)
		{
			if(userId.equalsIgnoreCase(u.getUserId().trim()))
			{
				userExist =  true;
				if(password.equalsIgnoreCase(u.getPassword())  && customertype.equalsIgnoreCase(user.getUserType()))
				{
					session.setAttribute("fname",userId);
					session.setAttribute("userid",userId);
					RequestDispatcher rd = request.getRequestDispatcher("home");
					rd.forward(request,response);
				}
				else
				{
					out.println("<h3>Password is Incorrect / Please select appropriate User Type and try again  <h3>");
				}	
			}
		}
			if(!userExist)
				out.println("<h1>User Id does not exist </h1>");	 */
		
		/* if(!userHashMap.containsKey(userId))
		{
			out.println("<h1>User Id does not exist </h1>");
		} */
		/* else
		{
			User user = userHashMap.get(userId);
			if(password.equalsIgnoreCase(user.getPassword())  && customertype.equalsIgnoreCase(user.getUserType()))
			{
				session.setAttribute("fname",userId);
				session.setAttribute("userid",userId);
				RequestDispatcher rd = request.getRequestDispatcher("home");
				rd.forward(request,response);
			}
			else
			{
				out.println("<h3>Password is Incorrect / Please select appropriate User Type and try again  <h3>");
			}	
		} */
		
	}
}


}
