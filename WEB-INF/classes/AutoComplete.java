import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AutoComplete extends HttpServlet
{ 	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String action = request.getParameter("action");
        String targetId = request.getParameter("id");
		StringBuffer sb = new StringBuffer();
		try
		{
			boolean namesAdded = false;
			if (targetId != null) {
				targetId = targetId.trim().toLowerCase();
			}
			if (action.equals("complete"))
			{
				if (!targetId.equals(""))
				{ 
					AjaxUtility a=new AjaxUtility();
					sb=a.readdata(targetId);
					if(sb!=null || !sb.equals(""))
					{
						namesAdded=true;
					}
					if (namesAdded)
					{
						response.setContentType("text/xml");
						//response.setHeader("Cache-Control", "no-cache");
						response.getWriter().write("<composers>" + sb.toString() + "</composers>");
						System.out.println("sb: "+sb.toString());
					}
					else 
					{
						response.setStatus(HttpServletResponse.SC_NO_CONTENT);
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
