import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class AddToCart extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException 
{
      
	  response.setContentType("text/html;charset=UTF-8");
      HttpSession session = request.getSession();
      //Products p = (Products) session.getAttribute("addtocart");
	 
	 String p =  request.getParameter("product");
	 p=p.replaceAll("\\[","");
	 p=p.replaceAll("\\]","");
	  p=p.replaceAll("\\,","");
	List<String> accessoryList = new ArrayList<String>(); 
	PrintWriter out = response.getWriter();
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
	
	//out.println(p1);
	 
	 Cart cart;
	  if(null==session.getAttribute("cart"))
		 cart = new Cart();
	  else
		  cart = (Cart) session.getAttribute("cart");
	  
	  List<Products> productList = cart.getProductList();
	  if(productList == null)
		  productList = new ArrayList<Products>();
	  if(p1 != null)
		productList.add(p1);
	  cart.setProductList(productList);
	  session.setAttribute("cart", cart);
		
		RequestDispatcher rd = request.getRequestDispatcher("viewcart");
		rd.forward(request,response); 
		
	
	
}
}