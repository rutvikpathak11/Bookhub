import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;




public class Home extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
     DealMatches.getDeals("C:\\apache-tomcat-7.0.34\\webapps\\Bookhub\\DealMatches.txt");
	  
    PrintWriter out = response.getWriter();
	response.setContentType("text/html;charset=UTF-8");
	out.println("<html><head>");
	out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
	out.println("<title>Book Hub</title>");
	out.println("<link rel='shortcut icon' href='cart/icon.jpg'/>");
	out.println("<link rel='stylesheet' href='styles.css' type='text/css' />");
	
	//carousel css
	out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
	out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>");
	out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
	out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>");
	//search  tab js
	out.println("<script type='text/javascript' src='javascript.js'></script>");
	
	out.println("</head>");
	out.println("<body onload='init()'>");
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

	
	/*//search tab
	out.println("<li style='text-align: center'>");
	out.println("<h4><b>Search Products</b></h4>");
	out.println("<ul>");
	out.println("<li class='text' style='text-align: center'>");
	out.println("<form method='get' class='searchform' action='#'>");
	out.println("<p>");
	out.println("<input type='text' size='70' value='' name='s' class='s' placeholder='Search by Title, Author, or ISBN' style='text-color: color: #555' />");
	out.println("</p> </form>");	
	out.println("</li></ul></li>");
	//End of search tab*/
	
	//Fetch Top 5 products sold.
	MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
	List<OrderDetails> orderList= dbObj.getTopFiveSoldProducts();
	
	//Start of carousel code
	out.println("<div style='width: 100%' style='height: 500px'>");
	out.println("<tr><div id='myCarousel' class='carousel slide' data-ride='carousel'>");
						
	//Indicators
	out.println("<ol class='carousel-indicators'>" +
    "<li data-target='#myCarousel' data-slide-to='0' class='active'></li>" +
    "<li data-target='#myCarousel' data-slide-to='1'></li>" +
    "<li data-target='#myCarousel' data-slide-to='2'></li>" +
  "</ol>");
						//Wrapper for slides
						int i=0;
						for(OrderDetails o:orderList)
						{
							//fetching product details.
							Products p = dbObj.getProduct(o.getModelName());
							if(i==0)
							{
								++i;
								out.println("<div class='carousel-inner'>");
								out.println("<div class='item active'>"+
											"<div style='text-align: center'>"+
												  "<img class='product-image' src='images/Homepage_Billboard_10-03_A3.jpg' style='width: 100%; height: 250px'>"+
											"</div>"+
											"<div style='text-align: center'>"+
													//p.getName()+
													//"<br/>$" +p.getPrice()+
														"<form  method = 'get' action = 'addtocart' style='text-align: center'>" +
															"<input class = 'submit-button' type = 'submit' value='Buy Now'>" +
															//"<input type='hidden' name='product' value='"+p+"'>" +
														"</form>"+
												"</div>"+
											  "</div>");
							}
							else
							{
								out.println("<div class='item'>"+
											"<div style='text-align: center'>"+
												  "<img class='product-image' src='images/img_index1.jpg' style='width: 100%; height: 250px'>"+
											"</div>"+
											"<div style='text-align: center'>"+
													//p.getName()+
													//"<br/>$" +p.getPrice()+
														"<form  method = 'get' action = 'addtocart' style='text-align: center'>" +
															"<input class = 'submit-button' type = 'submit' value='Buy Now'>" +
															//"<input type='hidden' name='product' value='"+p+"'>" +
														"</form>"+
												"</div>"+
											  "</div>");
							}
							
						}
											
					   
						
						/*
						 "<div class='col-xs-12 col-sm-4 col-md-2'>"+
						 "<div class='col-xs-12 col-sm-4 col-md-2 cloneditem-1'>"+
						out.println("<input class = 'submit-button' type = 'label' value='$" +p.getPrice()+ "' align='center'>" +
														"<form class = 'submit-button' method = 'get' action = 'addtocart'>" +
													"<input class = 'submit-button' type = 'submit' value='Buy Now' align='center'>" +
													"<input type='hidden' name='product' value='"+p+"'>" +
												"</form>");
						"<img class='product-image' src='" +p.getImage()+ "' alt='" +p.getImage()+ "'>" +
						<div class='item active'>"+
												"<img src='" +p.getImage()+ "' alt='Los Angeles'>" +
											"</div>");
						
"<div class='item'>"+
												"<img src='" +p.getImage()+ "' alt='Chicago'>"+
											"</div>"
						"<div class='item'>"+
						  "<img src='images/chicago.jpg' alt='Chicago'>"+
						"</div>"+ */

					  //Left and right controls
					  out.println("<a class='left carousel-control' href='#myCarousel' data-slide='prev'>"+
						"<span class='glyphicon glyphicon-chevron-left'></span>"+
						"<span class='sr-only'>Previous</span>"+
					  "</a>"+
					  "<a class='right carousel-control' href='#myCarousel' data-slide='next'>"+
						"<span class='glyphicon glyphicon-chevron-right'></span>"+
						"<span class='sr-only'>Next</span>"+
					  "</a>"+
					"</div>");
					out.println("</div>");
					// end of carousel code
					
	//out.println("<img class='header-image' src='images/img_index1.jpg' width = '100%' height = '100%' alt='Index Page Image' />");
	out.println("<div id='body'>");
	out.println("<section id='content'>");
	out.println("<article>");
	out.println("<h2>Welcome to Book Hub</h2>");
	out.println("<p>Book Hub offers variety of books.</p>");
	out.println("<p>Shop at the best market rate</p>");

        out.println("<table>");
        if(DealMatches.getTweetOne() == null) {
            out.println("<tr><td>We're sorry. We don't have any deals for today.</td></tr>");
        } else {
            out.println("<tr><td colspan='4'>Check out our deals!</td></tr>");

            out.println("<tr><td colspan='4'>"+DealMatches.getTweetOne()+"</td></tr>");
	    out.print("<tr>");

            Products p = dbObj.getProduct(DealMatches.getIdOne());
	    session.setAttribute("addtocart",p);
	    out.print("<tr><td rowspan='3'><b>" + p.getName() + "</b>  </td>");
	    out.print("<td rowspan='3'><img class='product-image' src='" +p.getImage()+ "'></td>");
	    out.print("<td rowspan='3'>   $" + p.getPrice() + "</td>");

	    out.print("<form class = 'submit-button' method = 'get' action = 'addtocart'>");
	    out.print("<td><input class = 'submit-button' type = 'submit' value='Buy Now'></td></tr>");
	    out.print("<input type='hidden' name='product' value='"+p+"'>");
	    out.print("</form>");

            //Write Review form
	    out.print("<form class = 'submit-button' method = 'get' action = 'writereview'>");
	    out.print("<tr><td><input class = 'submit-button' type = 'submit' value='Write Review'></td></tr>");
	    out.print("<input type='hidden' name='product' value='"+p+"'>");
	    out.print("<input type='hidden' name='productCategory' value='Speakers'>");
	    out.print("</form>");
	
	    //View Review form
	    out.print("<form class = 'submit-button' method = 'get' action = 'viewreview'>");
	    out.print("<tr><td><input class = 'submit-button' type = 'submit' value='View Review'></td></tr>");
	    out.print("<input type='hidden' name='product' value='"+p+"'>");
	    out.println("</form>");

            if(DealMatches.getTweetTwo() != null) {
        	out.println("<tr><td colspan='4'>"+DealMatches.getTweetTwo()+"</td></tr>");
		out.print("<tr>");

        	Products p2 = dbObj.getProduct(DealMatches.getIdTwo());
		session.setAttribute("addtocart",p2);
	        out.print("<td rowspan='3'><b>" + p2.getName() + "</b>  </td>");
	        out.print("<td rowspan='3'><img class='product-image' src='" +p2.getImage()+ "'></td>");
	        out.print("<td rowspan='3'>$" + p2.getPrice() + "</td>");

	        out.print("<form class = 'submit-button' method = 'get' action = 'addtocart'>");
	        out.print("<td><input class = 'submit-button' type = 'submit' value='Buy Now'></td></tr>");
	        out.print("<input type='hidden' name='product' value='"+p2+"'>");
	        out.print("</form>");

        	//Write Review form
		out.print("<form class = 'submit-button' method = 'get' action = 'writereview'>");
	        out.print("<tr><td><input class = 'submit-button' type = 'submit' value='Write Review'></td></tr>");
	        out.print("<input type='hidden' name='product' value='"+p2+"'>");
		out.print("<input type='hidden' name='productCategory' value='Speakers'>");
		out.print("</form>");
	
		//View Review form
		out.print("<form class = 'submit-button' method = 'get' action = 'viewreview'>");
		out.print("<tr><td><input class = 'submit-button' type = 'submit' value='View Review'></td>");
		out.print("<input type='hidden' name='product' value='"+p2+"'>");
		out.print("</form>");
		out.println("</tr>");
            }
        }
        out.println("</table>");

	out.println("</article>");
	out.println("</section>");
	
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

	//Start of Trending tab
	out.println("<li>");
	out.println("<a href='trendingproducts'><h4>Trending Products</h4></a>");
	out.println("</li>");
	//End of trending tab.
	
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
	//Start of search feature
	out.println("  <form name='autofillform' action='search'>");
      out.println("<table border='0' cellpadding='5'> ");
      out.println("  <tbody> ");
          out.println("<tr>");
            out.println("<td><strong>Search :  </strong>");
                      
                       out.println(" <input type='text' name='searchterm' size='25' id='complete-field' onkeyup='doCompletion()' autocomplete='off'></td>");
                     
        out.println("</tr>");
          out.println("<tr>");
             out.println("<td id='auto-row' colspan='2'>");
                out.println("<table id='complete-table' class='popupBox'></table>");
              out.println("</td>");
          out.println("</tr>");
          out.println("</td></tr>");
        out.println("</tbody>");
     out.println(" </table>");
	/*out.println("<form method='get' class='searchform' action='#'>");
	out.println("<p>");
	out.println("<input type='text' size='25' value='' name='s' class='s' />");
	out.println("</p>");	
	out.println("</form>*/
	out.println("</li>");
        out.println("<li>");
        out.println("<input type='checkbox' name='categories' value='Technology' checked> <strong>Technology</strong><br>");
        out.println("<input type='checkbox' name='categories' value='Comics' checked> <strong>Comics</strong><br>");
        out.println("<input type='checkbox' name='categories' value='Autobiography' checked> <strong>Autobiography</strong><br>");
        out.println("<input type='checkbox' name='categories' value='Sports' checked> <strong>Sports</strong><br>");
        out.println("<input type='checkbox' name='categories' value='Education' checked> <strong>Education</strong><br>");
        out.println("<input type='checkbox' name='categories' value='Religion' checked> <strong>Religion</strong><br>");         
        out.println("</li></ul></li>");
        out.println("  </form>");
	//End of search feature
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
	out.println("<p>©Book Hub - Enterprise Web Application </p>");
	out.println("</div>");	
	out.println("</footer>");	
	out.println("</div>");	
	out.println("</body>");	
	out.println("</html>");	

}




 public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	
	PrintWriter out = response.getWriter();
	response.setContentType("text/html;charset=UTF-8");
	out.println("<html><head>");
	out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
	out.println("<title>Book Hub</title>");
	out.println("<link rel='shortcut icon' href='cart/icon.jpg'/>");
	out.println("<link rel='stylesheet' href='styles.css' type='text/css' />");
	
	//carousel css
	out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
	out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>");
	out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
	out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>");
	//search  tab js
	out.println("<script type='text/javascript' src='javascript.js'></script>");
	
	out.println("</head>");
	out.println("<body onload='init()'>");
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

	
	/*//search tab
	out.println("<li style='text-align: center'>");
	out.println("<h4><b>Search Products</b></h4>");
	out.println("<ul>");
	out.println("<li class='text' style='text-align: center'>");
	out.println("<form method='get' class='searchform' action='#'>");
	out.println("<p>");
	out.println("<input type='text' size='70' value='' name='s' class='s' placeholder='Search by Title, Author, or ISBN' style='text-color: color: #555' />");
	out.println("</p> </form>");	
	out.println("</li></ul></li>");
	//End of search tab*/
	
	//Fetch Top 5 products sold.
	MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
	List<OrderDetails> orderList= dbObj.getTopFiveSoldProducts();
	
	//Start of carousel code
	out.println("<div style='width: 100%' style='height: 500px'>");
	out.println("<tr><div id='myCarousel' class='carousel slide' data-ride='carousel'>");
						
	//Indicators
	out.println("<ol class='carousel-indicators'>" +
    "<li data-target='#myCarousel' data-slide-to='0' class='active'></li>" +
    "<li data-target='#myCarousel' data-slide-to='1'></li>" +
    "<li data-target='#myCarousel' data-slide-to='2'></li>" +
  "</ol>");
						//Wrapper for slides
						int i=0;
						for(OrderDetails o:orderList)
						{
							//fetching product details.
							Products p = dbObj.getProduct(o.getModelName());
							if(i==0)
							{
								++i;
								out.println("<div class='carousel-inner'>");
								out.println("<div class='item active'>"+
											"<div style='text-align: center'>"+
												  "<img class='product-image' src='images/Homepage_Billboard_10-03_A3.jpg' style='width: 100%; height: 250px'>"+
											"</div>"+
											"<div style='text-align: center'>"+
													//p.getName()+
													//"<br/>$" +p.getPrice()+
														"<form  method = 'get' action = 'addtocart' style='text-align: center'>" +
															"<input class = 'submit-button' type = 'submit' value='Buy Now'>" +
															//"<input type='hidden' name='product' value='"+p+"'>" +
														"</form>"+
												"</div>"+
											  "</div>");
							}
							else
							{
								out.println("<div class='item'>"+
											"<div style='text-align: center'>"+
												  "<img class='product-image' src='images/img_index1.jpg' style='width: 100%; height: 250px'>"+
											"</div>"+
											"<div style='text-align: center'>"+
													//p.getName()+
													//"<br/>$" +p.getPrice()+
														"<form  method = 'get' action = 'addtocart' style='text-align: center'>" +
															"<input class = 'submit-button' type = 'submit' value='Buy Now'>" +
															//"<input type='hidden' name='product' value='"+p+"'>" +
														"</form>"+
												"</div>"+
											  "</div>");
							}
							
						}
											
					   
						
						/*
						 "<div class='col-xs-12 col-sm-4 col-md-2'>"+
						 "<div class='col-xs-12 col-sm-4 col-md-2 cloneditem-1'>"+
						out.println("<input class = 'submit-button' type = 'label' value='$" +p.getPrice()+ "' align='center'>" +
														"<form class = 'submit-button' method = 'get' action = 'addtocart'>" +
													"<input class = 'submit-button' type = 'submit' value='Buy Now' align='center'>" +
													"<input type='hidden' name='product' value='"+p+"'>" +
												"</form>");
						"<img class='product-image' src='" +p.getImage()+ "' alt='" +p.getImage()+ "'>" +
						<div class='item active'>"+
												"<img src='" +p.getImage()+ "' alt='Los Angeles'>" +
											"</div>");
						
"<div class='item'>"+
												"<img src='" +p.getImage()+ "' alt='Chicago'>"+
											"</div>"
						"<div class='item'>"+
						  "<img src='images/chicago.jpg' alt='Chicago'>"+
						"</div>"+ */

					  //Left and right controls
					  out.println("<a class='left carousel-control' href='#myCarousel' data-slide='prev'>"+
						"<span class='glyphicon glyphicon-chevron-left'></span>"+
						"<span class='sr-only'>Previous</span>"+
					  "</a>"+
					  "<a class='right carousel-control' href='#myCarousel' data-slide='next'>"+
						"<span class='glyphicon glyphicon-chevron-right'></span>"+
						"<span class='sr-only'>Next</span>"+
					  "</a>"+
					"</div>");
					out.println("</div>");
					// end of carousel code
					
	//out.println("<img class='header-image' src='images/img_index1.jpg' width = '100%' height = '100%' alt='Index Page Image' />");
	out.println("<div id='body'>");
	out.println("<section id='content'>");
	out.println("<article>");
	out.println("<h2>Welcome to Book Hub</h2>");
	out.println("<p>Book Hub offers variety of books.</p>");
	out.println("<p>Shop at the best market rate</p>");

        out.println("<table>");
        if(DealMatches.getTweetOne() == null) {
            out.println("<tr><td>We're sorry. We don't have any deals for today.</td></tr>");
        } else {
            out.println("<tr><td colspan='4'>Check out our deals!</td></tr>");

            out.println("<tr><td colspan='4'>"+DealMatches.getTweetOne()+"</td></tr>");
	    out.print("<tr>");

            Products p = dbObj.getProduct(DealMatches.getIdOne());
	    session.setAttribute("addtocart",p);
	    out.print("<tr><td rowspan='3'><b>" + p.getName() + "</b>  </td>");
	    out.print("<td rowspan='3'><img class='product-image' src='" +p.getImage()+ "'></td>");
	    out.print("<td rowspan='3'>   $" + p.getPrice() + "</td>");

	    out.print("<form class = 'submit-button' method = 'get' action = 'addtocart'>");
	    out.print("<td><input class = 'submit-button' type = 'submit' value='Buy Now'></td></tr>");
	    out.print("<input type='hidden' name='product' value='"+p+"'>");
	    out.print("</form>");

            //Write Review form
	    out.print("<form class = 'submit-button' method = 'get' action = 'writereview'>");
	    out.print("<tr><td><input class = 'submit-button' type = 'submit' value='Write Review'></td></tr>");
	    out.print("<input type='hidden' name='product' value='"+p+"'>");
	    out.print("<input type='hidden' name='productCategory' value='Speakers'>");
	    out.print("</form>");
	
	    //View Review form
	    out.print("<form class = 'submit-button' method = 'get' action = 'viewreview'>");
	    out.print("<tr><td><input class = 'submit-button' type = 'submit' value='View Review'></td></tr>");
	    out.print("<input type='hidden' name='product' value='"+p+"'>");
	    out.println("</form>");

            if(DealMatches.getTweetTwo() != null) {
        	out.println("<tr><td colspan='4'>"+DealMatches.getTweetTwo()+"</td></tr>");
		out.print("<tr>");

        	Products p2 = dbObj.getProduct(DealMatches.getIdTwo());
		session.setAttribute("addtocart",p2);
	        out.print("<td rowspan='3'><b>" + p2.getName() + "</b>  </td>");
	        out.print("<td rowspan='3'><img class='product-image' src='" +p2.getImage()+ "'></td>");
	        out.print("<td rowspan='3'>$" + p2.getPrice() + "</td>");

	        out.print("<form class = 'submit-button' method = 'get' action = 'addtocart'>");
	        out.print("<td><input class = 'submit-button' type = 'submit' value='Buy Now'></td></tr>");
	        out.print("<input type='hidden' name='product' value='"+p2+"'>");
	        out.print("</form>");

        	//Write Review form
		out.print("<form class = 'submit-button' method = 'get' action = 'writereview'>");
	        out.print("<tr><td><input class = 'submit-button' type = 'submit' value='Write Review'></td></tr>");
	        out.print("<input type='hidden' name='product' value='"+p2+"'>");
		out.print("<input type='hidden' name='productCategory' value='Speakers'>");
		out.print("</form>");
	
		//View Review form
		out.print("<form class = 'submit-button' method = 'get' action = 'viewreview'>");
		out.print("<tr><td><input class = 'submit-button' type = 'submit' value='View Review'></td>");
		out.print("<input type='hidden' name='product' value='"+p2+"'>");
		out.print("</form>");
		out.println("</tr>");
            }
        }
        out.println("</table>");

	out.println("</article>");
	out.println("</section>");
	
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

	//Start of Trending tab
	out.println("<li>");
	out.println("<a href='trendingproducts'><h4>Trending Products</h4></a>");
	out.println("</li>");
	//End of trending tab.
	
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
	//Start of search feature
	out.println("  <form name='autofillform' action='search'>");
      out.println("<table border='0' cellpadding='5'> ");
      out.println("  <tbody> ");
          out.println("<tr>");
            out.println("<td><strong>Search :  </strong>");
                      
                       out.println(" <input type='text' name='searchterm' size='25' id='complete-field' onkeyup='doCompletion()' autocomplete='off'></td>");
                     
        out.println("</tr>");
          out.println("<tr>");
             out.println("<td id='auto-row' colspan='2'>");
                out.println("<table id='complete-table' class='popupBox'></table>");
              out.println("</td>");
          out.println("</tr>");
          out.println("</td></tr>");
        out.println("</tbody>");
     out.println(" </table>");
	/*out.println("<form method='get' class='searchform' action='#'>");
	out.println("<p>");
	out.println("<input type='text' size='25' value='' name='s' class='s' />");
	out.println("</p>");	
	out.println("</form>*/
	out.println("</li>");
        out.println("<li>");
        out.println("<input type='checkbox' name='categories' value='Technology' checked> <strong>Technology</strong><br>");
        out.println("<input type='checkbox' name='categories' value='Comics' checked> <strong>Comics</strong><br>");
        out.println("<input type='checkbox' name='categories' value='Autobiography' checked> <strong>Autobiography</strong><br>");
        out.println("<input type='checkbox' name='categories' value='Sports' checked> <strong>Sports</strong><br>");
        out.println("<input type='checkbox' name='categories' value='Education' checked> <strong>Education</strong><br>");
        out.println("<input type='checkbox' name='categories' value='Religion' checked> <strong>Religion</strong><br>");         
        out.println("</li></ul></li>");
        out.println("  </form>");
	//End of search feature
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
	out.println("<p>©Book Hub - Enterprise Web Application </p>");
	out.println("</div>");	
	out.println("</footer>");	
	out.println("</div>");	
	out.println("</body>");	
	out.println("</html>");		

} 
}
