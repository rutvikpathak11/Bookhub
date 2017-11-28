import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class ViewReview extends HttpServlet 
{
	
	String retailerName="BookHub", retailerCity="Chicago", retailerState="Illinois", retailerZip="60616";
	String productOnSale="Yes", manufacturerRebate="No";
	String reviewDate = null;
		
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
	
	out.println("<img class='header-image' src='images/img_index1.jpg' width = '100%' height = '100%' alt='Index Page Image' />");
	out.println("<div id='body'>");
	out.println("<section id='content'>");
	out.println("<article>");
	out.println("<h1>View Review</h1>");
	out.println("</article>");
	out.println("</section>");
	
	//View Reviews tab
	//Parsing param values.
		String p =  request.getParameter("product");
		 p=p.replaceAll("\\[","");
		 p=p.replaceAll("\\]","");
		  p=p.replaceAll("\\,","");
		List<String> accessoryList = new ArrayList<String>(); 
		String str[] = p.split(" ");
		Products p1 = new Products();
		
		p1.setName(str[0]);
		p1.setPrice(str[1]);
		p1.setImage(str[2]);
		p1.setManufacturer(str[3]);
		p1.setCondition(str[4]);
		p1.setDiscount(str[5]);
		
		for(int i=6 ; i<str.length; i++)
			accessoryList.add(str[i]);
		p1.setAccessoryList(accessoryList);
		
		MongoDBDataStoreUtilities obj = new MongoDBDataStoreUtilities();
		List<ProductReview> reviewList = obj.getReviews("productName", p1.getName());
		
		out.println("<section id='content'>");
		out.println("<article>");
		for(ProductReview review:reviewList)
		{
			
			out.println("<table border='2'>"+
			"<tr><td width='30%'>Product Name: </td><td>" +review.getProductModelName()+ "</td></tr>"+
			"<tr><td>Product Category: </td> <td>" +review.getProductCategory()+ "</td></tr>"+
			"<tr><td>Product Price: </td> <td>$" +review.getProductPrice()+ "</td></tr>"+
			"<tr><td>Retailer Name: </td> <td>" +review.getRetailerName()+ "</td></tr>"+
			"<tr><td>Retailer City: </td> <td>" +review.getRetailerCity()+ "</td></tr>"+
			"<tr><td>Retailer State: </td> <td>" +review.getRetailerState()+ "</td></tr>"+
			"<tr><td>Retailer Zip: </td> <td>" +review.getRetailerZip()+ "</td></tr>"+
			"<tr><td>ProductOnSale: </td> <td>" +review.getProductOnSale()+ "</td></tr>"+
			"<tr><td>Manufacturer Name: </td> <td>" +review.getManufacturerName()+ "</td></tr>"+
			"<tr><td>Manufacturer Rebate: </td> <td>" +review.getManufacturerRebate()+ "</td></tr>"+
			"<tr><td>User Id: </td> <td>" +review.getUserId()+ "</td></tr>"+
			"<tr><td>User Age: </td> <td>" +review.getUserAge()+ "</td></tr>"+
			"<tr><td>User Gender: </td> <td>" +review.getUserGender()+ "</td></tr>"+
			"<tr><td>User Occupation: </td> <td>" +review.getUserOccupation()+ "</td></tr>"+	
			"<tr><td>Review Rating: </td><td>" +review.getReviewRating()+ "</td></tr>"+
			"<tr><td>Review Date: </td> <td>" +review.getReviewDate()+ "</td></tr>"+
			"<tr><td>Review Text: </td> <td>" +review.getReviewText()+ "</td></tr>"+
			"</table>");
		}
		out.println("</article>");
		out.println("</section>");
	//end of review part.
	
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
