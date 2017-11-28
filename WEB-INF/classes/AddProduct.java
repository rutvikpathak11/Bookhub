import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AddProduct extends HttpServlet
{
	public void init()
	{
		String xmlFileName = "C:\\apache-tomcat-7.0.34\\webapps\\final project\\BookCatalog.xml";
		SAXParserDataStore parser = new SAXParserDataStore();
		parser.parse(xmlFileName);
		
		//Add products to DB
		//Clean existing products from DB.
		MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
		dbObj.emptyProductDetailTable();
		
		//Add all products from xml file to ProductDetail table
		Map<String,List<Products>> productMap = parser.getProductMap();
		int i =0;
		for (String key : productMap.keySet())
		{
			//System.out.print("\n 2222222222222222222222222222222222"+key);
			
		}
			
		for(String key : productMap.keySet())
		{
			List<Products> productList = productMap.get(key);
			//System.out.print("\n "+key + " !!!!!!!!!!!!!!!!!!!!!!!" + productList + "!!!!!!!!!!!!!!");
			String category = key;
			if(	(key.trim()).equalsIgnoreCase("technologyList"))
				category = "Technology";
			else if(key.equalsIgnoreCase("comicsList"))
				category = "Comics";
			else if(key.equalsIgnoreCase("autobiographyList"))
				category = "Autobiography";
			else if(key.equalsIgnoreCase("sportsList"))
				category = "Sports";
			else if(key.equalsIgnoreCase("educationList"))
				category = "Education";
			else if(key.equalsIgnoreCase("religionList"))
				category = "Religion";
			else if(key.equalsIgnoreCase("accessoriesList"))
				category = "AccessoriesList";
			
			for(Products p:productList)
			{
				String productName = p.getName();
				String productPrice = p.getPrice();
				String productImage = p.getImage();
				String productManufacturer = p.getManufacturer();
				String productCondition = p.getCondition();
				String productDiscount = p.getDiscount();
				String productAccessoryList = "";
				
				if(p.getAccessoryList() != null)
					for(String p1:p.getAccessoryList())
						productAccessoryList += p1 +",";
				
				dbObj.insertIntoProductDetailTable(category,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,productAccessoryList);
			}  
		}
		//End of add products to db.
		
	}  
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	response.setContentType("text/html;charset=UTF-8");
	out.println("<html><head>");
	out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
	out.println("<title>Smart Portables</title>");
	out.println("<link rel='shortcut icon' href='cart/icon.jpg'/>");
	out.println("<link rel='stylesheet' href='styles.css' type='text/css' />");
	out.println("</head>");
	out.println("<body>");
	out.println("<div id='container'>");
	out.println("<header>");
	out.println("<h1><a href='/'>Smart <span> Portables</span></a></h1>");
	out.println("</header>");
	out.println("<nav>");
	out.println("<ul>");
	out.println("<li  class='start selected'><a href='managestore'>Add Products</a></li>");
	out.println("<li class=''><a href='modifyproduct'>Delete Product</a></li>");
	
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
		String productPrice=request.getParameter("price");
		String productImage=request.getParameter("productImage");
		String retailername=request.getParameter("retailername");
		String retailerzip=request.getParameter("retailerzip");
		String retailercity=request.getParameter("retailercity");
		String retailerstate=request.getParameter("retailerstate");
		String sale=request.getParameter("sale");
		String productManufacturer=request.getParameter("manufacture");
		String rebate=request.getParameter("rebate");
		
		MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
		dbObj.insertIntoProductDetailTable(category,productName,productPrice,productImage,productManufacturer,"New","25","");
		
		out.println("<h3><br><br>The Product has been added succesfully </h3><br><br>");	
	}
}
