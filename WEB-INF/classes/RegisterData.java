import java.io.*;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.*;
import java.util.*;





public class RegisterData extends HttpServlet 
{	
	static int userCount=0;
	static FileOutputStream fos;
	static ObjectOutputStream oos ;

  public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
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
	out.println("<h1><a href='/'>Book <span> Hub</span></a></h1>");
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
	
	String userType = request.getParameter("userType");
	//out.println(userType);
	String userId = request.getParameter("userid");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	
	
	//SQL code to insert user details into table
	MySQLDataStoreUtilities dbObject = new MySQLDataStoreUtilities();
	if(dbObject.checkUserExist(userId))
		out.println("<h1>User Id already exist. Please try again!</h1>");
	else
	{
		dbObject.insertIntoUserDetails(userId,password,email,userType);
		out.println("<h1>Registered Succesfully!</h1>");
	}	
	/* HashMap<String,User> userHashMap = new HashMap<String,User>();
	
	User user = new User(userId,password,userType);
	//userHashMap.put(userId,user);
	
	if(userCount==0)
	{
		fos = new FileOutputStream(new File("C:\\apache-tomcat-7.0.34\\webapps\\csj1\\UserDetails.txt"));
		oos = new ObjectOutputStream(fos);
		oos.writeObject(user);
		++userCount;
	}
	else
	{
		fos = new FileOutputStream(new File("C:\\apache-tomcat-7.0.34\\webapps\\csj1\\UserDetails.txt"),true);
		oos = new AppendableObjectOutputStream(fos);
		//out.println("<div>Here it is</div>");
		   oos = new ObjectOutputStream(fos) {
            protected void writeStreamHeader() throws IOException {
                reset();
            }
        }; 
		oos.writeObject(user);   
	} */
	
	//out.println("<h1>Registered Succesfully!</h1>");
	
	}

	private static class AppendableObjectOutputStream extends ObjectOutputStream 
	{

        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            // do not write a header
        }
    }	
}

