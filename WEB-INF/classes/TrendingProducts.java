import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.SimpleDateFormat;


public class TrendingProducts extends HttpServlet 
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
	out.println("<h1>Trending Products</h1>");
	out.println("</article>");
	out.println("</section>");
	
		// Top 5 sold products
		MySQLDataStoreUtilities obj = new MySQLDataStoreUtilities();
		List<OrderDetails> topFiveSoldProductList = obj.getTopFiveSoldProducts();
		
		out.println("<section id='content'>");
		out.println("<article>");
		out.println("<h2>Top 5 sold products</h2>");
		out.println("<table border='2'>"+
			"<tr><th width='30%'>Product Name </th><th width='30%'>Price</th></tr>");
		for(OrderDetails order:topFiveSoldProductList)
		{
			out.println("<tr><td>" +order.getModelName()+ " </td><td>" +order.getPrice()+ "</td></tr>");
		}
		out.println("</table>");
		
		//Top 5 zip codes where maximum products are sold
		topFiveSoldProductList = obj.getTopFiveZipCodes();
		out.println("<h2>Top 5 zip codes with maximum sold products</h2>");
		out.println("<table border='2'>"+
			"<tr><th width='30%'>Zip Code</th></tr>");
		for(OrderDetails order:topFiveSoldProductList)
		{
			out.println("<tr><td>" +order.getZipCode()+ "</td></tr>");
		}
			out.println("</table>");
		
		//Top 5 most liked products.
	MongoDBDataStoreUtilities dbObject = new MongoDBDataStoreUtilities();
	Map<String,Double> productMap = dbObject.getTopFiveLikedProducts();
	
	
	
	//Sorting based on map values.
	productMap = sortByValue(productMap);
	
	out.println("<h2>Top 5 most liked products</h2>");
	out.println("<table border='2'>"+
				"<tr><th width='30%'>Product Name </th><th width='30%'>Average Rating</th></tr>");
				int i=0;
			for(String key:productMap.keySet())
			{
				++i;
				if(i>5)
					break;
				Double value = productMap.get(key);
				out.println("<tr><td>" +key+ " </td><td>" +value+ "</td></tr>");
			}
			out.println("</table>");
	//end of 5 most liked products.
		
		out.println("</article>");
		out.println("</section>");
	//end of review part.
	
	
	out.println("<aside class='sidebar'>");
	out.println("<ul>");	
	out.println("<li>");
	out.println("<h4>Products</h4>");
	out.println("<ul>");
	out.println("<li class=''><a href='Technology'>Technology</a></li>");
	out.println("<li class=''><a href='Comics'>Comics</a></li>");
	out.println("<li class=''><a href='Autobiography'>Autobiography</a></li>");
	out.println("<li class=''><a href='Sports'>Sports</a></li>");
	out.println("<li class=''><a href='Education'>Education</a></li>");
	out.println("<li class=''><a href='Religion'>Religion</a></li>");
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
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map)
{
    List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
    Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
        @Override
        public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
            return (o2.getValue()).compareTo(o1.getValue());
        }
    });

    Map<K, V> result = new LinkedHashMap<>();
    for (Map.Entry<K, V> entry : list) {
        result.put(entry.getKey(), entry.getValue());
    }
    return result;
}
}
