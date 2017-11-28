import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;



public class MySQLDataStoreUtilities
{
	static Connection con;
	PreparedStatement pst;
	ResultSet rs;
	static
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookHub","root","Mysql@1991");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	void emptyProductDetailTable()
	{
		String query = "DELETE FROM ProductDetail;";
		try
		{
			con.setAutoCommit(false);
			pst = con.prepareStatement(query);
			pst.executeUpdate();
			con.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//Inserting products into product table
	void insertIntoProductDetailTable(String category,String productName,String productPrice,String productImage,String productManufacturer,String productCondition,String productDiscount,String productAccessoryList)
	{
		String query = "INSERT INTO ProductDetail values (?,?,?,?,?,?,?,?,?,?);";
		Random rn = new Random();
		int productQuantity = rn.nextInt(1000) + 1;
		String manufacturerRebate = "No";
		if(productQuantity % 5 ==0)
			manufacturerRebate = "Yes";
		try
		{
			con.setAutoCommit(false);
			pst = con.prepareStatement(query);
			
			pst.setString(1,category);
			pst.setString(2,productName);
			pst.setString(3,productPrice);
			pst.setString(4,productImage);
			pst.setString(5,productManufacturer);
			pst.setString(6,productCondition);
			pst.setString(7,productDiscount);
			if(productAccessoryList != null)
				pst.setString(8,productAccessoryList);
			pst.setString(9,productQuantity + "");
			pst.setString(10,manufacturerRebate);
			pst.executeUpdate();
			con.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//Updating products into product table
	int updateProductDetailTable(String productName,String productPrice,String productCondition,String productDiscount, String rebate)
	{
		String query = "UPDATE productdetail set productPrice = ?, productDiscount = ?, manufacturerRebate = ?, productCondition = ? where productName = ? ;";
		int exist = 0;
		try
		{
			con.setAutoCommit(false);
			pst = con.prepareStatement(query);
			
			pst.setString(1,productPrice);
			pst.setString(2,productDiscount);
			pst.setString(3,rebate);
			pst.setString(4,productCondition);
			pst.setString(5,productName);

			exist = pst.executeUpdate();
			con.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return exist;
	}
	
	//Fetch products based on productName
	Products getProduct(String productName)
	{
		Products product = new Products();
		String query;
		try
		{
			query = "SELECT * FROM ProductDetail where productName = ? ;";
			pst = con.prepareStatement(query);
			pst.setString(1,productName);
			
			rs = pst.executeQuery();
			while(rs.next())
			{
				List<String> accessoryList = new ArrayList<String>();
				product.setName(rs.getString("productName"));
				product.setPrice(rs.getString("productPrice"));
				product.setImage(rs.getString("productImage"));
				product.setManufacturer(rs.getString("productManufacturer"));
				product.setCondition(rs.getString("productCondition"));
				product.setDiscount(rs.getString("productDiscount"));
				product.setQuantity(rs.getString("productQuantity"));
				product.setManufacturerRebate(rs.getString("manufacturerRebate"));
				
				//accessory list conversion.
				String s[] = (rs.getString("productAccessoryList")).split(",");
				for(String s1:s)
					accessoryList.add(s1);
				product.setAccessoryList(accessoryList);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return product;
	}
	
	//fetching products from product table based on category
	List<Products> getProductList(String category)
	{
		List<Products> productList = new ArrayList<Products>();
		String query;
		try
		{
			if(category.equalsIgnoreCase(""))
			{
				query = "SELECT * FROM ProductDetail order by productName ASC;";
				pst = con.prepareStatement(query);
			}
			else
			{
				query = "SELECT * FROM ProductDetail where category = ? order by productName ASC ;";
				pst = con.prepareStatement(query);
				pst.setString(1,category);
			}
			
			rs = pst.executeQuery();
			Products product=null;
			while(rs.next())
			{
				List<String> accessoryList = new ArrayList<String>();
				product = new Products();
				product.setName(rs.getString("productName"));
				product.setPrice(rs.getString("productPrice"));
				product.setImage(rs.getString("productImage"));
				product.setManufacturer(rs.getString("productManufacturer"));
				product.setCondition(rs.getString("productCondition"));
				product.setDiscount(rs.getString("productDiscount"));
				product.setQuantity(rs.getString("productQuantity"));
				product.setManufacturerRebate(rs.getString("manufacturerRebate"));
				
				//accessory list conversion.
				String s[] = (rs.getString("productAccessoryList")).split(",");
				for(String s1:s)
					accessoryList.add(s1);
				product.setAccessoryList(accessoryList);
				
				productList.add(product);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return productList;
	}
	
	//get discounted products
	List<Products> getDiscountedProducts()
	{
		List<Products> productList = new ArrayList<Products>();
		String query;
		try
		{
			query = "SELECT * FROM ProductDetail where productDiscount NOT like '0.0' order by productName ASC ;";
			pst = con.prepareStatement(query);
			//pst.setString(1,searchValue);
			
			rs = pst.executeQuery();
			Products product=null;
			while(rs.next())
			{
				List<String> accessoryList = new ArrayList<String>();
				product = new Products();
				product.setName(rs.getString("productName"));
				product.setPrice(rs.getString("productPrice"));
				product.setImage(rs.getString("productImage"));
				product.setManufacturer(rs.getString("productManufacturer"));
				product.setCondition(rs.getString("productCondition"));
				product.setDiscount(rs.getString("productDiscount"));
				product.setQuantity(rs.getString("productQuantity"));
				product.setManufacturerRebate(rs.getString("manufacturerRebate"));
				
				//accessory list conversion.
				String s[] = (rs.getString("productAccessoryList")).split(",");
				for(String s1:s)
					accessoryList.add(s1);
				product.setAccessoryList(accessoryList);
				
				productList.add(product);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return productList;
	}
	//get manufacturer rebate products
	List<Products> getManufacturerRebateProducts()
	{
		List<Products> productList = new ArrayList<Products>();
		String query;
		try
		{
			query = "SELECT * FROM ProductDetail where manufacturerRebate LIKE 'Yes' order by productName ASC ;";
			pst = con.prepareStatement(query);
			//pst.setString(1,searchValue);
			
			rs = pst.executeQuery();
			Products product=null;
			while(rs.next())
			{
				List<String> accessoryList = new ArrayList<String>();
				product = new Products();
				product.setName(rs.getString("productName"));
				product.setPrice(rs.getString("productPrice"));
				product.setImage(rs.getString("productImage"));
				product.setManufacturer(rs.getString("productManufacturer"));
				product.setCondition(rs.getString("productCondition"));
				product.setDiscount(rs.getString("productDiscount"));
				product.setQuantity(rs.getString("productQuantity"));
				product.setManufacturerRebate(rs.getString("manufacturerRebate"));
				
				//accessory list conversion.
				String s[] = (rs.getString("productAccessoryList")).split(",");
				for(String s1:s)
					accessoryList.add(s1);
				product.setAccessoryList(accessoryList);
				
				productList.add(product);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return productList;
	}
	
	//Delete Products from table
	//Cancel customer orders.
	int deleteProduct(String category, String productName)
	{
		String query = " DELETE FROM ProductDetail where category = ? and productName = ? ;";
		int isDeleted=0;
		try
		{
			con.setAutoCommit(false);
			pst = con.prepareStatement(query);
			pst.setString(1,category);
			pst.setString(2,productName);
			isDeleted = pst.executeUpdate();
			
			con.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return isDeleted;
	}
	
	void insertIntoUserDetails(String userId, String pwd, String emailId, String userType)
	{
		
		String query = "INSERT INTO UserDetails values (?,?,?,?);";
		try
		{
			con.setAutoCommit(false);
			pst = con.prepareStatement(query);
			pst.setString(1,userId);
			pst.setString(2,pwd);
			pst.setString(3,emailId);
			pst.setString(4,userType);
			
			pst.executeUpdate();
			con.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	boolean checkUserExist(String userId)
	{
		String query = "SELECT * FROM UserDetails where userId = ?;";
		try
		{
			pst = con.prepareStatement(query);
			pst.setString(1,userId);
			rs = pst.executeQuery();
			int rowCount = 0;
			if (rs.last()) 
			{
			  rowCount = rs.getRow();
			}
			if (rowCount > 0)
				return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	
	boolean validateUser(String userId, String pwd)
	{
		String query = "SELECT * FROM UserDetails where userId = ? and pwd = ?;";
		try
		{
			pst = con.prepareStatement(query);
			pst.setString(1,userId);
			pst.setString(2,pwd);
			rs = pst.executeQuery();
			int rowCount = 0;
			if (rs.last()) 
			{
			  rowCount = rs.getRow();
			}
			if (rowCount > 0)
				return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	
	void insertOrderDetails(OrderDetails order)
	{
		String query = "INSERT INTO CustomerOrderDetails values (?,?,?,?,?,?,?);";
		try
		{
			con.setAutoCommit(false);
			pst = con.prepareStatement(query);
			pst.setString(1,order.getOrderNo());
			pst.setString(2,order.getUserId());
			pst.setString(3,order.getModelName());
			pst.setString(4,order.getShippingAddress());
			pst.setFloat(5,order.getPrice());
			pst.setString(6,order.getDeliveryDate());
			pst.setString(7,order.getZipCode());
			pst.executeUpdate();
			
			con.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//Method to fetch all orders for a particular user.
	List getUserOrders(String userId)
	{
		List<OrderDetails> orderList = new ArrayList<OrderDetails>();
		String query = "SELECT * FROM CustomerOrderDetails where userId = ? ;";
		try
		{
			if(userId.equalsIgnoreCase("SalesMngr"))
			{
				query = "SELECT * FROM CustomerOrderDetails ;";
				pst = con.prepareStatement(query);
			}
			else
			{
				pst = con.prepareStatement(query);
				pst.setString(1,userId);
			}
			
			
			rs = pst.executeQuery();
			OrderDetails order=null;
			while(rs.next())
			{
				order = new OrderDetails();
				order.setOrderNo(rs.getString("orderNo"));
				order.setUserId(rs.getString("userId"));
				order.setModelName(rs.getString("modelName"));
				order.setShippingAddress(rs.getString("shippingAddress"));
				order.setPrice(rs.getFloat("price"));
				order.setDeliveryDate(rs.getString("deliveryDate"));
				
				orderList.add(order);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return orderList;
	}
	
	//Cancel customer orders.
	void deleteOrder(String orderNo, String modelName)
	{
		String query = " DELETE FROM CustomerOrderDetails where orderNo = ? and modelName = ? ;";
		try
		{
			con.setAutoCommit(false);
			pst = con.prepareStatement(query);
			pst.setString(1,orderNo);
			pst.setString(2,modelName);
			pst.executeUpdate();
			
			con.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//Trending Link
	// Top 5 products sold
	public List<OrderDetails> getTopFiveSoldProducts()
	{
		List<OrderDetails> orderList = new ArrayList<OrderDetails>();
		String query = "select * from CustomerOrderDetails GROUP BY modelName order by count(modelName) DESC LIMIT 5;";
		try
		{
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			OrderDetails order=null;
			int productCount=0;
			while(rs.next())
			{
				++productCount;
				if(productCount > 5)
					break;
				order = new OrderDetails();
				order.setOrderNo(rs.getString("orderNo"));
				order.setUserId(rs.getString("userId"));
				order.setModelName(rs.getString("modelName"));
				order.setShippingAddress(rs.getString("shippingAddress"));
				order.setPrice(rs.getFloat("price"));
				order.setDeliveryDate(rs.getString("deliveryDate"));
				orderList.add(order);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return orderList;
	}
	
	// Top 5 zip codes where maximum products are sold
	public List<OrderDetails> getTopFiveZipCodes()
	{
		List<OrderDetails> orderList = new ArrayList<OrderDetails>();
		String query = "select * from CustomerOrderDetails"+
						" GROUP BY (zipCode)"+
						" order by count(zipCode) DESC LIMIT 5;";
		try
		{
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			OrderDetails order=null;
			int productCount=0;
			while(rs.next())
			{
				++productCount;
				if(productCount > 5)
					break;
				order = new OrderDetails();
				order.setOrderNo(rs.getString("orderNo"));
				order.setUserId(rs.getString("userId"));
				order.setModelName(rs.getString("modelName"));
				order.setShippingAddress(rs.getString("shippingAddress"));
				order.setPrice(rs.getFloat("price"));
				order.setDeliveryDate(rs.getString("deliveryDate"));
				order.setZipCode(rs.getString("zipCode"));
				orderList.add(order);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return orderList;
	}
	
	//Number of products sold 
	public Map getSoldProductQuantity()
	{
		//List<OrderDetails> orderList = new ArrayList<OrderDetails>();
		Map<OrderDetails,String> map= new HashMap<OrderDetails,String>();
		String query = "select *,count(*) AS quantity FROM CustomerOrderDetails GROUP BY modelName ORDER BY modelName ASC ;";
		try
		{
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			OrderDetails order=null;
			while(rs.next())
			{
				order = new OrderDetails();
				order.setOrderNo(rs.getString("orderNo"));
				order.setUserId(rs.getString("userId"));
				order.setModelName(rs.getString("modelName"));
				order.setShippingAddress(rs.getString("shippingAddress"));
				order.setPrice(rs.getFloat("price"));
				order.setDeliveryDate(rs.getString("deliveryDate"));
				order.setZipCode(rs.getString("zipCode"));
				//orderList.add(order);
				map.put(order,rs.getString("quantity"));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return map;
	}
	
	//Number of products sold per day 
	public TreeMap getDayWiseSales()
	{
		//List<OrderDetails> orderList = new ArrayList<OrderDetails>();
		TreeMap<String,String> map= new TreeMap<String,String>();
		String query = "select deliveryDate,SUM(price) AS quantity from CustomerOrderDetails group by deliveryDate ORDER BY CAST(deliveryDate As DATETIME);";
		try
		{
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			while(rs.next())
			{
				//date conversion.
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.DAY_OF_MONTH, 14);
				//Date date = cal.getTime();
				String DATE_FORMAT = "MM/dd/yyyy"; 
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);				
				//String deliverydate = sdf.format(date);
				
				java.util.Date deliveryDate = sdf.parse(rs.getString("deliveryDate"));
				cal.setTime(deliveryDate);
				cal.add(Calendar.DATE, -14);
				java.util.Date date = cal.getTime();
				String purchaseDate = sdf.format(date);
				
				map.put(purchaseDate,rs.getString("quantity"));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return map;
	}
	
	//Updating password into userdetails
	int changePassword(String userid,String newpassword)
	{
			String query = "UPDATE UserDetails set pwd = ? where userId = ? ;";
			int exist = 0;
			try
			{
			con.setAutoCommit(false);
			pst = con.prepareStatement(query);
			pst.setString(1,newpassword);
			pst.setString(2,userid);
			exist = pst.executeUpdate();
			con.commit();
			}
			catch(Exception ex)
			{
			ex.printStackTrace();
			}
		return exist;
	}
	
	//Getting email from userdetails
String getEmail(String userid)
{
String email=null;
String query = "select email from UserDetails where userId=?;";
try
{
con.setAutoCommit(false);
pst = con.prepareStatement(query);
pst.setString(1,userid);
rs = pst.executeQuery();
while (rs.next()) 
{
  email=rs.getString("email");;
}
con.commit();
}
catch(Exception ex)
{
ex.printStackTrace();
}
return email;
}


}


