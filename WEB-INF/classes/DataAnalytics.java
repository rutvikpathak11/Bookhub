import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.List.*;



public class DataAnalytics extends HttpServlet {
	
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
	out.println("<h1><a href='/'>Book <span> Hub</span></a></h1>");
	out.println("</header>");
	out.println("<nav>");
	out.println("<ul>");
	out.println("<li  class='start selected'><a href='managestore'>Add Products</a></li>");
	out.println("<li class=''><a href='modifyproduct'>Delete Product</a></li>");
	out.println("<li class=''><a href='dataanalytics'>Data Analytics</a></li>");
	
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
	out.println("<h1>Data Analytics on Customer Reviews</h1>");
	out.println("<form  method='Post' action='dataanalytics'>");
	out.println("<table>"+
	"<tr><td><input type='checkbox' name='analytics' value='productCategory'> Product Category</td>"+
	"<td><select name='category'><option value='allCategories' >All Categories</option><option value='Technology' >Technology</option></option><option value='Comics'>Comics</option><option value='Education'>Education</option><option value='Sports'>Sports</option><option value='Autobiography'>Autobiography</option><option value='Religion'>Religion</option></select></td>"+
	"<td></td><td></td><td></td></tr>"+
	
	"<tr><td><input type='checkbox' name='analytics' value='productPrice'> Product Price</td>"+
	"<td><input type='text' name='productPrice' /></td>"+
	"<td><input type='radio' name='productPriceRange' value='equals'>Equals<br>"+
	"<input type='radio' name='productPriceRange' value='greaterThan'>Greater than<br>"+
	"<input type='radio' name='productPriceRange' value='lessThan'>Less than<br>"+
	"</td><td></td><td></td></tr>"+
	
	"<tr><td><input type='checkbox' name='analytics' value='reviewRating'> Review Rating</td>"+
	"<td><select name='reviewRating'>"
					 +" <option value='1' >1(Very Bad)</option> "
					 +" <option value='2' >2</option> "
					 +" <option value='3' >3</option> "
					 +" <option value='4' >4</option> "
					 +" <option value='5' >5(Very Good)</option> "
					 +" </select></td>"+
	"<td><input type='radio' name='reviewRatingRadio' value='equals'>Equals<br>"+
	"<input type='radio' name='reviewRatingRadio' value='greaterThan'>Greater than<br>"+
	"</td><td></td><td></td></tr>"+
	
	"<tr><td><input type='checkbox' name='analytics' value='reviewRating'> Retailer City</td>"+
	"<td><input type='text' name='retailerCity' /></td>"+
	"<td></td><td></td><td></td></tr>"+
	
	"<tr><td><input type='checkbox' name='analytics' value='groupBy'> Group By</td>"+
	"<td><select name='groupBy'>"
					 +" <option value='city' >City</option> "
					 +" <option value='zipCode' >Zip Code</option> "
					 +" </select></td>"+
	"<td><input type='radio' name='groupByRadio' value='count'>Count<br>"+
	"<input type='radio' name='groupByRadio' value='details'>Details<br>"+
	"<td></td><td></td></tr>"+
	
	"</table> <input type='submit' value='Find Data'>");
	out.println("</form>");
	
	out.println("</body>");
    out.println("</html>");
	}
	
	public void doPost(HttpServletRequest request,
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
	out.println("<h1><a href='/'>Book <span> Hub</span></a></h1>");
	out.println("</header>");
	out.println("<nav>");
	out.println("<ul>");
	out.println("<li  class='start selected'><a href='managestore'>Add Products</a></li>");
	out.println("<li class=''><a href='modifyproduct'>Delete Product</a></li>");
	out.println("<li class=''><a href='dataanalytics'>Data Analytics</a></li>");
	
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
	out.println("<h1>Data Analytics on Customer Reviews</h1>");
	/* String[] names = request.getParameterValues("analytics");
	List list =  Arrays.asList(names); 
	
	if(list.contains("productCategory"))
	{
		String productCategory = request.getParameter("productCategory");
		if(productCategory.equalsIgnoreCase())
	}
	//System.out.println("!!!!!!!!!!!!!!!!!!!"+list); */
	
	String productCategory = request.getParameter("category");
	
	MongoDBDataStoreUtilities mongoObj = new MongoDBDataStoreUtilities();
	List<ProductReview> reviewList;
	if(productCategory.equalsIgnoreCase("allCategories"))
		 reviewList = mongoObj.getReviewList("","");
	else
		reviewList = mongoObj.getReviewList("productCategory",productCategory);
	
	out.println("<table border='2'>"+
				"<tr><th width='30%'>Product Name </th><th width='30%'>Price</th></tr>");
			for(int i=0;i<reviewList.size();i++)
			{
				ProductReview p = reviewList.get(i);
				out.println("<tr><td>" +p.getProductModelName()+ " </td><td>" +p.getProductPrice()+ "</td></tr>");
			}
			out.println("</table>");
	
	out.println("</body>");
    out.println("</html>");
	}

}


