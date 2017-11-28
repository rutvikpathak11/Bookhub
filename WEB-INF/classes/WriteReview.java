import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class WriteReview extends HttpServlet 
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
	out.println("<h1>Write Review</h1>");
	out.println("</article>");
	out.println("</section>");
	
	//review data
	//Parsing param values.
	if(fname==null)
	{
		out.println("<section id='content'>");
		out.println("<article>");
		out.println("<h2>Please Login to Write Reviews!!!!!!!!!!!!!!!</h2>");
		out.println("</article>");
		out.println("</section>");
		out.println("");
	}
	else
	{
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
		
		/* String retailerName="SmartPortables", retailerCity="Chicago", retailerState="Illinois", retailerZip="60616";
		String productOnSale="Yes", manufacturerRebate="No"; */
		
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String DATE_FORMAT = "MM/dd/yyyy"; 
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);				
		reviewDate = sdf.format(date);
		String productCategory = request.getParameter("productCategory");
		
		out.println("<section id='content'>");
		out.println("<article>");
		out.println("<form  method='Post' action='writereview'>");
		out.println("<table>"+
		"<tr><td>Product Name: </td><td>" +p1.getName()+ "</td></tr>"+
		"<input type='hidden' name='productName' value='"+p1.getName()+"'>" +
		"<tr><td>Product Category: </td> <td>" +productCategory+ "</td></tr>"+
		"<input type='hidden' name='productCategory' value='" +productCategory+ "'>" +
		"<tr><td>Product Price: </td> <td>$" +p1.getPrice()+ "</td></tr>"+
		"<input type='hidden' name='price' value='"+p1.getPrice()+"'>" +
		"<tr><td>Retailer Name: </td> <td>" +retailerName+ "</td></tr>"+
		"<input type='hidden' name='retailerName' value='"+retailerName+"'>" +
		"<tr><td>Retailer City: </td> <td>" +retailerCity+ "</td></tr>"+
		"<input type='hidden' name='retailerCity' value='"+retailerCity+"'>" +
		"<tr><td>Retailer State: </td> <td>" +retailerState+ "</td></tr>"+
		"<input type='hidden' name='retailerState' value='"+retailerState+"'>" +
		"<tr><td>Retailer Zip: </td> <td>" +retailerZip+ "</td></tr>"+
		"<input type='hidden' name='retailerZip' value='"+retailerZip+"'>" +
		"<tr><td>ProductOnSale: </td> <td>" +productOnSale+ "</td></tr>"+
		"<input type='hidden' name='productOnSale' value='"+productOnSale+"'>" +
		"<tr><td>Manufacturer Name: </td> <td>" +p1.getManufacturer()+ "</td></tr>"+
		"<input type='hidden' name='manufacturerName' value='"+p1.getManufacturer()+"'>" +
		"<tr><td>Manufacturer Rebate: </td> <td>" +manufacturerRebate+ "</td></tr>"+
		"<input type='hidden' name='manufacturerRebate' value='"+manufacturerRebate+"'>" +
		"<tr><td>User Id: </td> <td>" +fname+ "</td></tr>"+
		"<input type='hidden' name='fname' value='"+fname+"'>" +
		"<tr><td>User Age: </td> <td><input type='text' name='userAge' required/></td></tr>"+
		"<tr><td>User Gender: </td> <td><select name='userGender'> "
					 +" <option value='Male' >Male</option> "
					 +" <option value='Female' >Female</option> "
					 +" </select></td></tr>"+
		"<tr><td>User Occupation: </td> <td><input type='text' name='userOccupation' /></td></tr>"+	
		"<tr><td>Review Rating: </td><td><select name='reviewRating'> "
					 +" <option value='1' >1(Very Bad)</option> "
					 +" <option value='2' >2</option> "
					 +" <option value='3' >3</option> "
					 +" <option value='4' >4</option> "
					 +" <option value='5' >5(Very Good)</option> "
					 +" </select></td></tr>"+
		"<tr><td>Review Date: </td> <td>" +reviewDate+ "</td></tr>"+
		"<input type='hidden' name='reviewDate' value='"+reviewDate+"'>" +
		"<tr><td>Review Text: </td> <td><textarea name='reviewText' cols='40' rows='5'></textarea></td></tr>"+
		"<tr><td align='center' ><input type='submit' value='Submit Review'></td></tr>"+
		"</table>");
		out.println("</form>");
		out.println("</article>");
		out.println("</section>");	
	}
	//end of review part.
	
	out.println("<aside class='sidebar'>");
	out.println("<ul>");	
	out.println("<li>");
	out.println("<h4>Products</h4>");
	out.println("<ul>");
	out.println("<li><a href='smartwatches'>Smart Watches</a></li>");
	out.println("<li><a href='speakers'>Speakers</a></li>");
	out.println("<li><a href='headphones'>Headphones</a></li>");
	out.println("<li><a href='phones'>Phones</a></li>");
	out.println("<li><a href='laptops'>Laptops</a></li>");
	out.println("<li><a href='externalstorage'>External Storage</a></li>");
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
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
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
	out.println("<h1>Write Review</h1>");
	out.println("</article>");
	out.println("</section>");
	
	//review data
	//Parsing param values.
	String productName = request.getParameter("productName");
	String productCategory = request.getParameter("productCategory");
	String price = request.getParameter("price");
	String retailerName = request.getParameter("retailerName");
	String retailerCity = request.getParameter("retailerCity");
	String retailerState = request.getParameter("retailerState");
	String retailerZip = request.getParameter("retailerZip");
	String productOnSale = request.getParameter("productOnSale");
	String manufacturerName = request.getParameter("manufacturerName");
	String manufacturerRebate = request.getParameter("manufacturerRebate");
	String userId = request.getParameter("fname");
	String userAge = request.getParameter("userAge");
	String userGender = request.getParameter("userGender");
	String userOccupation = request.getParameter("userOccupation");
	String reviewRating = request.getParameter("reviewRating");
	String reviewDate = request.getParameter("reviewDate");
	String reviewText = request.getParameter("reviewText");
	
	MongoDBDataStoreUtilities obj = new MongoDBDataStoreUtilities();
	obj.insertReview(productName, productCategory,  price,  retailerName,  retailerCity, 
					retailerState,  retailerZip,  productOnSale, manufacturerName, manufacturerRebate, userId, userAge,
					userGender,  userOccupation,  reviewRating,  reviewDate,  reviewText);
	
	out.println("<section id='content'>");
	out.println("<article>");
	out.println("<h2>Review successfully stored. </h2>");
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
