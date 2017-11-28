import java.util.*;
import java.io.*;

import javax.servlet.http.*;

import javax.servlet.ServletContextEvent;  
import javax.servlet.ServletContextListener;

//A general class for managing data in memory
public class StoreData implements ServletContextListener {

    //Hold details on the books
    private static HashMap<String, Book> books;

    //Genres the store holds
    private static String[] availableGenres;

    //This is called when Tomcat is started up
    public void contextInitialized(ServletContextEvent event) {
        books = new HashMap<String, Book>();
        //Store the genres available
        availableGenres = new String[] {"romance", "sci-fi", "action", "thriller", "adventure", "drama", "crime",
                                        "mystery", "horror", "comedy", "fantasy"};
        Arrays.sort(availableGenres);

        //Make books and store them for testing
        Book af = new Book("1");
        af.setTitle("Artemis Fowl");
        af.setAuthor("Eoin Colfer");
        af.setCost(9.99);
        af.setDateFromString("04/26/2001");
        af.addGenre("thriller");
        af.addGenre("fantasy");
        books.put(af.getISBN(), af);

        Book h2 = new Book("2");
        h2.setTitle("The Hitchhiker's Guide to the Galaxy");
        h2.setAuthor("Douglas Adams");
        h2.setCost(9.99);
        h2.setDateFromString("10/12/1979");
        h2.addGenre("sci-fi");
        h2.addGenre("comedy");
        books.put(h2.getISBN(), h2);

        Book befSun = new Book("3");
        befSun.setTitle("Before Sunrise");
        befSun.setAuthor("Richard Linklater");
        befSun.setCost(4.99);
        befSun.setDateFromString("01/27/1995");
        befSun.addGenre("romance");
        befSun.addGenre("drama");
        books.put(befSun.getISBN(), befSun);

        Book max = new Book("4");
        max.setTitle("Mad Max: Fury Road");
        max.setAuthor("George Miller");
        max.setCost(11.99);
        max.setDateFromString("05/07/2015");
        max.addGenre("action");
        max.addGenre("sci-fi");
        books.put(max.getISBN(), max);

        Book conf = new Book("5");
        conf.setTitle("L.A. Confidential");
        conf.setAuthor("James Ellroy");
        conf.setCost(3.99);
        conf.setDateFromString("06/01/1990");
        conf.addGenre("mystery");
        conf.addGenre("crime");
        conf.addGenre("drama");
        books.put(conf.getISBN(), conf);
    }

    //This code is called when Tomcat is shut down
    public void contextDestroyed(ServletContextEvent event) {
        //Stub to let code compile; must be implemented by a ServletContextListener
    }

    //Given an array of genres, return a list of the books that are in those genres
    public static ArrayList<Book> getBooksByGenres(String[] genres) {
        //Make a list to hold the books
        ArrayList<Book> bookList = new ArrayList<Book>();

        //For each book in the store...
        for (Book book : books.values()) {
            //...and each genre in the list...
            for(String genre : genres) {
                //...check if the book is in that genre
                if(book.isThisGenre(genre)) {
                    //If it is, add it to the list and move on to the next book
                    bookList.add(book);
                    break;
                }
            }
        }

        //After going through all the books, return the list
        return bookList;
    }

    //Return an array of all the available genres
    public static String[] getAvailableGenres() {
        return availableGenres;
    }

}
