import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.List.*;


class MyComparator implements Comparator<String>
    {
        public int compare(String o1,String o2)
        {
           return (o1.compareTo(o2));
        }
    }
	
public class SalesReport extends HttpServlet {
	
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
	out.println("<h1>Sales Report</h1>");
	out.println("<form  method='Post' action='salesreport'>");
	out.println("<table>"+
	"<tr>"+
	"<td></td><td><select name='inventoryType'><option value='productSold' >Products Sold</option><option value='graphicalRepresenation' >Graphical Representation</option></option><option value='dailySales'>Daily Sales</option>/select></td>"+
	"<td></td><td></td><td></td><td></td></tr>"+
	"</table>"+
	"<input type='submit' value='Find Data'>");
	out.println("</form>");
	
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
		out.println("<h1>Sales Report</h1>");
		out.println("<form  method='Post' action='salesreport'>");
		out.println("<table>"+
		"<tr>"+
		"<td></td><td><select name='inventoryType'><option value='productSold' >Products Sold</option><option value='graphicalRepresenation' >Graphical Representation</option></option><option value='dailySales'>Daily Sales</option>/select></td>"+
		"<td></td><td></td><td></td><td></td></tr>"+
		"</table>"+
		"<input type='submit' value='Find Data'><br>");
		out.println("</form>");
		
		String viewType = request.getParameter("inventoryType");
		
			//Results Tab
			MySQLDataStoreUtilities obj = new MySQLDataStoreUtilities();
			List<Products> productDetailsList = obj.getProductList("");
			Map<OrderDetails,String> map = new HashMap<OrderDetails,String>();
			
			out.println("<section id='content'>");
			out.println("<article>");
			
			int i=0;
			if(viewType.equalsIgnoreCase("productSold"))
			{
				//Product Availability
				out.println("<h2>Product Sales Report</h2>");
				out.println("<table border='2'>"+
					"<tr><th width='10%'>Sr. No.</th><th width='30%'>Product Name </th><th width='30%'>Price</th><th width='30%'>Sales Quantity</th><th width='30%'>Total Sales</th></tr>");
					
				map = obj.getSoldProductQuantity();	
				Iterator it = map.entrySet().iterator();
				while(it.hasNext())
				{
					Map.Entry entry = (Map.Entry) it.next();
					OrderDetails key = (OrderDetails) entry.getKey();
					String value = (String) entry.getValue();
					out.println("<tr><td> "+(++i)+" </td><td>" +key.getModelName()+ " </td><td>$" +key.getPrice()+ "</td><td>" +value+ "</td><td>$" + key.getPrice() * Float.parseFloat(value) + "</td></tr>");
				}
				out.println("</table>");
				//end of product availability
			}
			
			//Daily Sales Details
			TreeMap<String, Float> salesDayWise = new TreeMap<String , Float>(new MyComparator());
			if(viewType.equalsIgnoreCase("dailySales"))
			{
				salesDayWise = obj.getDayWiseSales();
				out.println("<h2>Daily Sales Report</h2>");
				out.println("<table border='2'>"+
					"<tr><th width='10%'>Sr. No.</th><th width='30%'>Date </th><th width='30%'>Total Sales</th></tr>");
				Iterator it = salesDayWise.entrySet().iterator();
				while(it.hasNext())
				{
					Map.Entry entry = (Map.Entry) it.next();
					String key = (String) entry.getKey();
					String value = (String) entry.getValue();
					out.println("<tr><td> "+(++i)+" </td><td>" +key+ " </td><td>$" +value+ "</td></tr>");
				}
				out.println("</table>");
			}
			//end of Daily Sales Details
			out.println("</article>");
			out.println("</section>");
			
			//Graphical Representation
			if(viewType.equalsIgnoreCase("graphicalRepresenation"))
			{
				out.println("<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js'></script>"+
							"<script type='text/javascript' src='https://www.google.com/jsapi'></script>");
				out.println("<script type='text/javascript' src='SalesBarChart.js'></script>");
				//bar chart code
				out.println("<div id='product-bar-chart'>Here it is</div>");
			}
			//End of Graphical Representation

		out.println("</body>");
		out.println("</html>");
	}

}


