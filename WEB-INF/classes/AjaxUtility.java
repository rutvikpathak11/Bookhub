import java.sql.*;
import java.util.*;

public class AjaxUtility
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
	
	public static HashMap<String,Products> getData()
	{ 
		HashMap<String,Products> hm=new HashMap<String,Products>();
		try
		{ 
			Statement stmt=con.createStatement();
			String selectCustomerQuery="select * from ProductDetail";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{
				Products p = new Products();
				p.setName(rs.getString("productName"));
				p.setCategory(rs.getString("category"));
				hm.put(rs.getString("productName"), p);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return hm;
	}
	
	public StringBuffer readdata(String searchId)
	{
		HashMap<String,Products> data;
		StringBuffer sb = new StringBuffer();
		data=getData();
		Iterator it = data.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry pi = (Map.Entry)it.next();
			Products p=(Products)pi.getValue();
			if (p.getName().toLowerCase().startsWith(searchId))
			{
				sb.append("<composer>");
				sb.append("<id>" + p.getCategory() + "</id>");
				sb.append("<ProductModel>" + p.getName() + "</ProductModel>");
				sb.append("</composer>");
			}
		}
		return sb;
	}
}