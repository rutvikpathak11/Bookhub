import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class ModifyProduct extends HttpServlet {
	
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
	out.println("</head>");
	out.println("<body>");
	out.println("<div id='container'>");
	out.println("<header>");
	out.println("<h1><a href='/'>Book <span>Hub</span></a></h1>");
	out.println("</header>");
	out.println("<nav>");
	out.println("<ul>");
	out.println("<li  class=''><a href='managestore'>Add Products</a></li>");
	out.println("<li class='start selected'><a href='modifyproduct'>Delete Product</a></li>");
	
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
	out.println("<form method='POST' action='modifyproduct'>");
	  
      out.println("<table>");	
				out.println("<tr>");
				out.println("<td> Product Category:-</td>");
				out.println("<td><select name='category'><option value='Technology' >Technology</option></option><option value='Comics'>Comics</option><option value='Education'>Education</option><option value='Autobiography'>Autobiography</option><option value='Sports'>Sports</option><option value='Religion'>Religion</option></select></td>");
				out.println("</tr>");
				out.println("<tr>");				
				out.println("<tr>");
				out.println("<td> Product Model Name :-</td>");
				out.println("<td> <input type='text' name='productname' </td>");
				out.println("</tr>");
				out.println("<td colspan='2' align='center'>");
				
				out.println("<input class = 'submit-button' type = 'submit'  value = 'Delete Product'></form>");
				out.println("</td>");
				out.println("</tr>");
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
	out.println("<li  class=''><a href='managestore'>Add Products</a></li>");
	out.println("<li class='start selected'><a href='modifyproduct'>Delete Product</a></li>");
	
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
	
	String category =request.getParameter("category");
	String productName=request.getParameter("productname");
		
		MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
		int isDeleted = dbObj.deleteProduct(category,productName);
			if(isDeleted ==0)
			{
				out.println("<h3>Product doesn't exist!!!<h3>");	
			}
			else
			{
				out.println("<h3>Product has been deleted !!!<h3>");
			}
	}
}


