import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Map.Entry;
import javax.servlet.*;
import javax.servlet.http.*;

public class Catalog extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        //GETting is basically the same as POSTing, but the URL looks nicer in POSTing,
        //so I'm handling GETs in POST
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        //Get the parameter sent of all the genres to look at; if there
        //is no parameter (from GETting the page), just get all genres
        //from the store
        String[] genres = request.getParameterValues("genres");
        if(genres == null)
            genres = StoreData.getAvailableGenres();

        //Get parameter of keywords and type to search under; if there
        //are no parameters, don't search
        String type = request.getParameter("type");
        if(type == null)
            type = "title";
        String keywords = request.getParameter("keywords");
        if(keywords == null)
            keywords = "";

        //Get parameter of sorting by; sort by relevance by default
        String sortBy = request.getParameter("sortBy");
        if(sortBy == null)
            sortBy = "relevance";

        response.setContentType("text/html");		
	PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");

        out.println("<html>");
        out.println("<head>");

	out.println("<title> BookHub </title>");

        //Access to Bootstrap for carousel and CSS file
        out.println("<meta charset=\"utf-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
        out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
        out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");

        out.println("</head>");
        out.println("<body>");

        out.println("<div class=\"container\">");
        out.println("<hr>");

        out.println("<form action=\""+response.encodeURL("Catalog")+"\" method=POST>");

        //Text box for keywords; just-searched keywords are in box
        out.println("Keywords: <input type=\"text\" name=\"keywords\" value=\""+keywords+"\"><br>");

        //Radio buttons for categories; just-searched category is checked
        out.println("Category to search:");
        out.print("<input type=\"radio\" name=\"type\" value=\"title\"");
        if(type.equals("title"))
            out.print(" checked");
        out.println("> Title ");
        out.print("<input type=\"radio\" name=\"type\" value=\"author\"");
        if(type.equals("author"))
            out.print(" checked");
        out.println("> Author ");
        out.print("<input type=\"radio\" name=\"type\" value=\"ISBN\"");
        if(type.equals("ISBN"))
            out.print(" checked");
        out.println("> ISBN <br>");

        //Radio buttons for sorting by
        out.println("Sort by:");
        out.print("<input type=\"radio\" name=\"sortBy\" value=\"relevance\"");
        if(sortBy.equals("relevance"))
            out.print(" checked");
        out.println("> Relevance ");
        out.print("<input type=\"radio\" name=\"sortBy\" value=\"date\"");
        if(sortBy.equals("date"))
            out.print(" checked");
        out.println("> Date <br><br>");

        //Make a table storing the checkboxes for the genres
        out.println("Narrow your search by genre:<br>");

        //This will be used to make new rows on the table when needed (see below)
        int colNum = 0;

        //Start the table
        out.println("<table style=\"width:100%\">");

        //Start the first entry
        out.println("<tr>");

        //For each genre in the store...
        for(String genre : StoreData.getAvailableGenres()) {
            //In a table entry, print out a checkbox for it in a table entry
            out.print("<th><input type=\"checkbox\" name=\"genres\" value=\""+genre+"\"");

            //If this genre is in the genres parameter, mark it as checked
            if(Arrays.asList(genres).contains(genre))
                out.print(" checked");

            //Conclude the printing of the genre checkbox, with the genre capitalized
            out.println("> "+ genre.substring(0, 1).toUpperCase() + genre.substring(1)+"</th>");

            //Increase the number of columns printed
            colNum += 1;

            //If the row has three columns already (or some other number, if you change three), start a new row
            if(colNum%3 == 0)
                out.println("</tr>\n<tr>");
        }
        //Finish off the table
        out.println("</tr>");
        out.println("</table>");

        //Make a submit button to search with the genres
        out.println("<input type=\"submit\" value=\"Search\">");
        out.println("</form>");

        //A horizontal rule keeps things separate
        out.println("<hr>");

        //Get books in available genres
        ArrayList<Book> books = StoreData.getBooksByGenres(genres);

        //If searching for something, further narrow search and sort
        if(!keywords.equals(""))
            books = searchByType(books, keywords, type);

        //If sorting by date, do so
        if(sortBy.equals("date"))
            sortByDate(books);

        //If no books, print error message
        if(books.size() == 0) {
            out.println("<ul>");
            out.println("<li>We're sorry. We couldn't find any books matching your specifications.</li>");
            out.println("</ul>");
            out.println("<hr>");
        }

        //Otherwise, for each book, print out title, author, and cost in unordered list
        else {
            for(Book book : books) {
                out.println("<ul>");
                out.println("<li>Title: <i>"+book.getTitle()+"</i></li>");
                out.println("<li>Author: "+book.getAuthor()+"</li>");
                out.println("<li>Price: $"+String.format("%.2f", book.getCost())+"</li>");
                out.println("<li>Release date: "+book.getDateString()+"</li>");
                out.println("</ul>");
                out.println("<hr>");
            }
        }

        //Finish up the body
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }

    //Returns a list of books, sorted by relevance to the search term
    private ArrayList<Book> searchByType(ArrayList<Book> books, String keywords, String type) {
        //Create a map to store distance values for each book
        HashMap<Book, Double> similarityScores = new HashMap<Book, Double>();

        //Calculate similarity score for each book based on keywords; choose right field to search
        for(Book book : books) {
            String bookField;
            if(type.equals("author"))
                bookField = book.getAuthor();
            else if (type.equals("title"))
                bookField = book.getTitle();
            else
                bookField = book.getISBN();

            //Only put score if above a threshold
            double score = similarityScore(keywords, bookField);
            if(score >= 0.1)
                similarityScores.put(book, score);
        }

        //Make a list of the entries in the map
        List<Entry<Book, Double>> list = new LinkedList<Entry<Book, Double>>(similarityScores.entrySet());

        //Sorting the list based on scores w/ a Comparator
        Collections.sort(list, new Comparator<Entry<Book, Double>>()
        {
            public int compare(Entry<Book, Double> o1,
                    Entry<Book, Double> o2)
            {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        //Put sorted values into array list, return
        ArrayList<Book> returnedBooks = new ArrayList<Book>();
        for (Entry<Book, Double> entry : list)
        {
            returnedBooks.add(entry.getKey());
        }

        return returnedBooks;
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

    private void sortByDate(ArrayList<Book> books) {
        //Sorting books based on date w/ a Comparator
        Collections.sort(books, new Comparator<Book>()
        {
            public int compare(Book b1, Book b2)
            {
                return b2.getDate().compareTo(b1.getDate());
            }
        });
    }

}