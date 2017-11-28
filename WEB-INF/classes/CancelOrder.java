import java.io.*;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.servlet.http.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class CancelOrder extends HttpServlet {

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

	List<OrderDetails> orderList = (List<OrderDetails>) session.getAttribute("orderList");
	List<OrderDetails> tempList= new ArrayList<OrderDetails>();
	FileOutputStream fos;
	ObjectOutputStream oos;
	fos = new FileOutputStream(new File("C:\\apache-tomcat-7.0.34\\webapps\\csj1\\OrderDetails.txt"));
	oos = new ObjectOutputStream(fos);

	String userId = request.getParameter("userid");
 	String model= request.getParameter("product");
	String orderNum= request.getParameter("ordernum");
	String delivery= request.getParameter("delivery");
	
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DAY_OF_MONTH, 5);
	Date date = cal.getTime();
	String DATE_FORMAT = "MM/dd/yyyy"; 
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);				
	String checkdate = sdf.format(date);
	try{
	Date del = sdf.parse(delivery);
	Date chk = sdf.parse(checkdate);
	if(del.compareTo(chk)>0)
	{
		/* for(OrderDetails o:orderList)
			{
				if(o.getOrderNo().equalsIgnoreCase(orderNum) && o.getUserId().equalsIgnoreCase(userId) && o.getModelName().equalsIgnoreCase(model))
				{
					continue;
				}
				else
				{
					oos.writeObject(o);
					tempList.add(o);
				}
			} */
			
			MySQLDataStoreUtilities dbObject = new MySQLDataStoreUtilities();
			dbObject.deleteOrder(orderNum,model);

			out.println("<h3><br><br> Your Order for "+model+ " has been cancelled ! <br><br><h3>");
	}
	else
	{	
		out.println("<h3><br><br>The order has been shipped for delivery. We will not be able to process your request for cancellation!!<br><br><h3>");
	}
		
	
	}
	catch (Exception e)
	{
		
	}
}
}