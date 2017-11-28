import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


public class GoogleVisualization extends HttpServlet 
{
	 private static final long serialVersionUID = 1L;
	 
	 public GoogleVisualization() 
	 {
	  super();
	 }

	 protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	 {
		MySQLDataStoreUtilities obj = new MySQLDataStoreUtilities();
		List<Products> productDetailsList = obj.getProductList("");
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!googlevisualization");
		  Gson gson = new Gson();
		  String jsonString = gson.toJson(productDetailsList);
		  response.setContentType("application/json");
		  response.getWriter().write(jsonString);
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!inventoty"+jsonString);
	 }
	 
	  protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	 {
		 MySQLDataStoreUtilities obj = new MySQLDataStoreUtilities();
		 Map<OrderDetails,String> map = obj.getSoldProductQuantity();
		 
		 List<forJsonSalesBarChart> obj1 = new ArrayList<forJsonSalesBarChart>();
		 
		 Iterator it = map.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry entry = (Map.Entry) it.next();
			OrderDetails key = (OrderDetails) entry.getKey();
			String value = (String) entry.getValue();
			
			forJsonSalesBarChart forBarChartObj = new forJsonSalesBarChart();
			forBarChartObj.setModelName(key.getModelName());
			Float totalSales = Float.parseFloat(value) * key.getPrice();
			forBarChartObj.setTotalSales(totalSales + "");
			obj1.add(forBarChartObj);
		}
		
		  Gson gson = new Gson();
		  String jsonString = gson.toJson(obj1);
		  response.setContentType("application/json");
		  response.getWriter().write(jsonString);
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!inventoty"+jsonString);
	 }
}

class forJsonSalesBarChart
{
	String modelName, totalSales;
	
	public void setModelName(String modelName)
	{
		this.modelName = modelName;
	}
	public void setTotalSales(String totalSales)
	{
		this.totalSales = totalSales;
	}
	
	public String getModelName()
	{
		return this.modelName;
	}
	public String getTotalSales()
	{
		return this.totalSales;
	}
}