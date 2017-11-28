import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class UpdateProduct extends HttpServlet 
{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException 
	{
    response.setContentType("text/html;charset=UTF-8");
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
	out.println("<h1><a href='/'>Book <span> hub</span></a></h1>");
	out.println("</header>");
	out.println("<nav>");
	out.println("<ul>");
	out.println("<li  class='start selected'><a href='managestore'>Add Products</a></li>");
	out.println("<li class=''><a href='modifyproduct'>Delete Product</a></li>");
	out.println("<li class=''><a href='modifyproduct'>Update Product</a></li>");
	out.println("<li class=''><a href='dataanalytics'>Data Analytics</a></li>");
	out.println("<li class=''><a href='inventory'>Inventory</a></li>");
	out.println("<li class=''><a href='salesreport'>Sales Report</a></li>");
	
	HttpSession session = request.getSession();
	String fname=(String)session.getAttribute("fname");
	if (fname == null)
	{
	out.println("<li class='' ><a href='login'>Sign in</a></li>");
	}
	else
	{
		out.println("<li class=''><a href='#'>Hello  "+fname+"</a></li>");
		out.println("<li class='' ><a href='signout'>Sign Out</a></li>");
	}
	
	out.println("</ul>");
	out.println("</nav>");
	out.println("<form method='POST' action='updateproduct'>");
	  
      out.println("<table>");				
				out.println("<tr>");
				out.println("<td> Product Model Name :-</td>");
				out.println("<td> <input type='text' name='productname' </td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> Product Price :- </td>");
				out.println("<td> $<input type='text' name='price' ></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td> Discount:-</td>");
				out.println("<td><input type='text' name='discount' ></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td> Manufacture Rebate:-</td>");
				out.println("<td><select name='rebate'><option value='yes' >Yes</option><option value='No'>No</option></select></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td> Product Condition:-</td>");
				out.println("<td><select name='condition'><option value='New' >New</option><option value='Old'>Old</option></select></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td colspan='2' align='center'>");
				out.println("<input class = 'submit-button' type = 'submit'  value = 'Update Product'></form>");
				out.println("</td>");
				out.println("</tr></table>");
			
			
            out.println("</body>");
            out.println("</html>");
}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
	  
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
		out.println("<li  class='start selected'><a href='managestore'>Add Products</a></li>");
		out.println("<li class=''><a href='modifyproduct'>Delete Product</a></li>");
		out.println("<li class=''><a href='updateproduct'>Update Product</a></li>");
		out.println("<li class=''><a href='dataanalytics'>Data Analytics</a></li>");
		out.println("<li class=''><a href='inventory'>Inventory</a></li>");
		out.println("<li class=''><a href='salesreport'>Sales Report</a></li>");
			
		HttpSession session = request.getSession();
		String fname=(String)session.getAttribute("fname");
		if (fname == null)
		{
		out.println("<li class='' ><a href='login'>Sign in</a></li>");
		}
		else
		{
			out.println("<li class=''><a href='#'>Hello  "+fname+"</a></li>");
			out.println("<li class='' ><a href='signout'>Sign Out</a></li>");
		}
		
		out.println("</ul>");
		out.println("</nav>");
		
		String productPrice =request.getParameter("price");
		String productName=request.getParameter("productname");
		String productCondition=request.getParameter("condition");
		String productDiscount=request.getParameter("discount");
		String rebate=request.getParameter("rebate");
			
			MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
			int exist = dbObj.updateProductDetailTable(productName,productPrice,productCondition,productDiscount,rebate);
				if(exist == 0)
				{
					out.println("<h3>Product doesn't exist!!!<h3>");	
				}
				else
				{
					out.println("<h3>Product has been updated!!!<h3>");
				}	
	}
}


