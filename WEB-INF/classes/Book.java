import java.util.HashSet;
import java.util.Date;
import java.text.SimpleDateFormat;

//A general class for storing book data
public class Book {

    //Details on the book
    private String ISBN;
    private String title;
    private String author;
    private double cost;
    private Date releaseDate;
    //Having the genres in a set prevents doubles
    private HashSet<String> genres;

    public Book(String ISBN) {
        this.ISBN = ISBN;
        title = "";
        author = "";
        cost = 0;
        releaseDate = new Date();
        genres = new HashSet<String>();
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String newAuthor) {
        author = newAuthor;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double newCost) {
        cost = newCost;
    }

    public void setDateFromString(String dateString) {
        try {
            releaseDate = new SimpleDateFormat("MM/dd/yyyy").parse(dateString);
        } catch (Exception e) {
            System.out.println("Error parsing "+title+" release date");
        }
    }

    public Date getDate() {
        return releaseDate;
    }

    public String getDateString() {
        return new SimpleDateFormat("MM/dd/yyyy").format(releaseDate);
    }

    public HashSet<String> getGenres() {
        return genres;
    }

    //Remove all genres in case we want to modify the book
    public void resetGenres() {
        genres = new HashSet<String>();
    }

    //Add a genre to the list
    public void addGenre(String newGenre) {
        genres.add(newGenre.toLowerCase());
    }

    //Check if a book is in this particular genre
    public boolean isThisGenre(String testGenre) {
        return genres.contains(testGenre.toLowerCase());
    }

}
