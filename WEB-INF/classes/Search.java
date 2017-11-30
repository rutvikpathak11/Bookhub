import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.Map.Entry;


public class Search extends HttpServlet 
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  {  
	String[] categoryArr = request.getParameterValues("categories");
	if(categoryArr == null)
            categoryArr = new String[] {};
        ArrayList<String> categories = new ArrayList<String>(Arrays.asList(categoryArr));

       String searchterm = request.getParameter("searchterm");
       if(searchterm == null)
           searchterm = "";

       ArrayList<Products> productList = new ArrayList<Products>();
       MySQLDataStoreUtilities sqlUtil = new MySQLDataStoreUtilities();

       for(String category : categories) {
           productList.addAll(sqlUtil.getProductList(category));
       }

       if(!searchterm.equals(""))
           productList = sort(productList, searchterm);
	
    PrintWriter out = response.getWriter();
	response.setContentType("text/html;charset=UTF-8");
	out.println("<html><head>");
	out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
	out.println("<title>Book Hub</title>");
	out.println("<link rel='shortcut icon' href='cart/icon.jpg'/>");
	out.println("<link rel='stylesheet' href='styles.css' type='text/css' />");

	//search  tab js
	out.println("<script type='text/javascript' src='javascript.js'></script>");

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
	out.println("<li class=''><a href='Autobiography'>Autobiography</a></li>");
	out.println("<li class=''><a href='Religion'>Religion</a></li>");
	out.println("<li class=''><a href='Sports'>Sports</a></li>");
	out.println("<li class=''><a href='Comics'>Comics</a></li>");
	out.println("<li class=''><a href='Education'>Education</a></li>");
	out.println("</ul>");
	out.println("</nav>");
	
	out.println("<img class='header-image' src='images/img_index1.jpg' width = '100%' height = '100%' alt='Index Page Image' />");

	out.println("<div id='body'>");
	out.println("<section id='content'>");
	out.println("<article>");
	out.println("<h2>Your search found the following books:</h2>" +
				"<table>");
        //out.println("<p>Whup! Still testing.</p>");
	
	
	if(productList.size() != 0) {
            Products p,p2;
	    for(int i = 0;i<productList.size();i++)
	    {
		    p = productList.get(i);
		    session.setAttribute("addtocart",p);
		    //out.println("p name: "+p.getName());
		    out.println("<tr>"+
					"	<td><b>" + p.getName() + "</b>  </td>" +
					"	<td><img class='product-image' src='" +p.getImage()+ "'></td>" +
					"	<td>$" + p.getPrice() + "</td>" +
					"<form class = 'submit-button' method = 'get' action = 'addtocart'>" +
					"<td><input class = 'submit-button' type = 'submit' value='Buy Now'></td>" +
					"<input type='hidden' name='product' value='"+p+"'>" +
					"</form>" +
					//Write Review form
					"<form class = 'submit-button' method = 'get' action = 'writereview'>" +
					"<td><input class = 'submit-button' type = 'submit' value='Write Review'></td>" +
					"<input type='hidden' name='product' value='"+p+"'>" +
					"<input type='hidden' name='productCategory' value='Speakers'>" +
					"</form>" +
					
					//View Review form
					"<form class = 'submit-button' method = 'get' action = 'viewreview'>" +
					"<td><input class = 'submit-button' type = 'submit' value='View Review'></td>" +
					"<input type='hidden' name='product' value='"+p+"'>" +
					"</form>" +
					"</tr>");
		}
        } else {
            out.println("<tr><br>We're sorry, but we couldn't find any products of sufficient relevance.</tr>");
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
	out.println("<li><a href='Autobiography'>Autobiography</a></li>");
	out.println("<li><a href='Religion'>Religion</a></li>");
	out.println("<li><a href='Sports'>Sports</a></li>");
	out.println("<li><a href='Comics'>Comics</a></li>");
	out.println("<li><a href='Education'>Education</a></li>");
	out.println("</ul>");
	out.println("</li>");	
	out.println("<li>");
	out.println("<h4>About us</h4>");
	out.println("<ul>");
	out.println("<li class='text'>");
	out.println("<p style='margin: 0;'>This is a sample website created to demonstrate a standard enterprise web page.</p>");
	out.println(" </li>");
	out.println("</ul>");
	out.println("</li>");

       //Start of search feature
	out.println("<li>");
	out.println("<h4>Search site</h4>");
	out.println("<ul>");
	out.println("<li class='text'>");
	out.println("<form method='get' class='searchform' action='search'>");
	out.println("<p>");
	out.println("<input type='text' size='25' name='searchterm' class='s' value='"+searchterm+"' placeholder='Search...'>");
	out.println("</p>");	                     

	out.print("<input type='checkbox' name='categories' value='Technology'");
        if(categories.contains("Technology"))
            out.print(" checked");
        out.println("> <strong>Technology</strong><br>");
        out.print("<input type='checkbox' name='categories' value='Comics'");
        if(categories.contains("Comics"))
            out.print(" checked");
        out.println("> <strong>Comics</strong><br>");
        out.print("<input type='checkbox' name='categories' value='Autobiography'");
        if(categories.contains("Autobiography"))
            out.print(" checked");
        out.println("> <strong>Autobiography</strong><br>");
        out.print("<input type='checkbox' name='categories' value='Sports'");
        if(categories.contains("Sports"))
            out.print(" checked");
        out.println("> <strong>Sports</strong><br>");
        out.print("<input type='checkbox' name='categories' value='Education'");
        if(categories.contains("Education"))
            out.print(" checked");
        out.println("> <strong>Education</strong><br>");
        out.print("<input type='checkbox' name='categories' value='Religion'");
        if(categories.contains("Religion"))
            out.print(" checked");
        out.println("> <strong>Religion</strong><br>");         
        out.println("  </form>");
        out.println("</li></ul></li>");
	//End of search feature

	out.println("<li>");	
	out.println("<h4>Helpful Links</h4>");	
	out.println("<ul>");	
	out.println("<li><a href='http://www.w3schools.com/html/default.asp' title='premium templates'>Learn HTML here</a></li>");	
	out.println("<li><a href='http://www.w3schools.com/css/default.asp' title='web hosting'>Learn CSS here</a></li>");	
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

    //Sorts products by relevance to the search term
    private ArrayList<Products> sort(ArrayList<Products> products, String keywords) {
        //Create a map to store distance values for each product
        HashMap<Products, Double> similarityScores = new HashMap<Products, Double>();

        //Calculate similarity score for each product name based on keywords
        for(Products product : products) {
            //Only put score if above a threshold
            double score = similarityScore(keywords, product.getName());
            if(score >= 0.05)
                similarityScores.put(product, score);
        }

        //Make a list of the entries in the map
        List<Entry<Products, Double>> list = new LinkedList<Entry<Products, Double>>(similarityScores.entrySet());

        //Sorting the list based on scores w/ a Comparator
        Collections.sort(list, new Comparator<Entry<Products, Double>>()
        {
            public int compare(Entry<Products, Double> o1,
                    Entry<Products, Double> o2)
            {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        //Put sorted values into array list, return
        ArrayList<Products> returnedProducts = new ArrayList<Products>();
        for (Entry<Products, Double> entry : list)
        {
            returnedProducts.add(entry.getKey());
        }

        return returnedProducts;
    }

    //Calculate the similarity score of two strings based on Levenshtein distance
    private double similarityScore(String first, String second) {
        int maxLength = Math.max(first.length(), second.length());
        //Can't divide by 0
        if (maxLength == 0)
            return 1.0d;

        first = first.toLowerCase();
        second = second.toLowerCase();

        int[] costs = new int[second.length() + 1];
        for (int i = 0; i <= first.length(); i++) {
            int previousValue = i;
            for (int j = 0; j <= second.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                }
                else if (j > 0) {
                    int useValue = costs[j - 1];
                    if (first.charAt(i - 1) != second.charAt(j - 1)) {
                        useValue = Math.min(Math.min(useValue, previousValue), costs[j]) + 1;
                    }
                    costs[j - 1] = previousValue;
                    previousValue = useValue;

                }
            }
            if (i > 0) {
                costs[second.length()] = previousValue;
            }
        }

        int editDistance = costs[second.length()];

        return ((double) (maxLength - editDistance)) / (double) maxLength;
    }
}
