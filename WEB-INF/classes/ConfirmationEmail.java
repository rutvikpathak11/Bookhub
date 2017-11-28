import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Map.Entry;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ConfirmationEmail extends HttpServlet {

    //Shows if any fields are blank
    private boolean blankFields;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
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
        out.println("<link rel=\"stylesheet\" href=\"/csj/navbar-fixed-side.css\" type=\"text/css\">");
        out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
        out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");

        out.println("</head>");
        out.println("<body>");

        out.println("<div class=\"container\">");
        out.println("<hr>");

        //Message for you
        out.println("<p>This is a test page to show you how to send confirmation emails. It should be integrated "+
                    "into the rest of the site, not used as-is.</p>");
        out.println("<hr>");

        //Begin sending form
        out.println("<form action=\""+response.encodeURL("ConfirmationEmail")+"\" method=POST>");

        //Notify user if any fields were blank
        if(blankFields)
            out.println("<p>Error. Some fields were blank.</p>");
        blankFields = false;

        //Data to put in email
        out.println("Order number: <input type=\"text\" name=\"orderNum\"><br>");
        out.println("Email address: <input type=\"text\" name=\"address\"><br>");

        out.println("<input type=\"submit\" value=\"Send email\">");
        out.println("</form>");

        //Finish up the body
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        //Get the parameters
        String orderNum = request.getParameter("orderNum");
        String address = request.getParameter("address");

        //If fields were blank, send an error message saying so in GET page
        if(orderNum.equals("") || address.equals("")) {
            blankFields = true;
            doGet(request, response);
            return;
        }

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

        //You need to put in your own username and password
        final String username = "username@hawk.iit.edu";
        final String password = "password";

        //These properties assume you're using Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
            Message message = new MimeMessage(session);

            //The address doesn't actually matter; if you use Gmail, the "from" address in the email will
            //always be from the account you used to send it (Gmail does this do prevent account spoofing);
            //however, changing "BookHub" will change the name of the sender
            message.setFrom(new InternetAddress("do-not-reply@bookhub.org", "BookHub"));

            //Set the email address of the recipient here
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(address));

            //Set the subject of the email
            message.setSubject("BookHub order confirmation");

            //Set the content of the email
            message.setText("Thank you for ordering from BookHub! Your order number is "+orderNum+".");

            Transport.send(message);
            out.println("<p>Your message was sent successfully.</p>");
        } catch (MessagingException | UnsupportedEncodingException e) {
            out.println("<p>Error. Your message could not be sent.</p>");
        }

        //Finish up the body
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }

}