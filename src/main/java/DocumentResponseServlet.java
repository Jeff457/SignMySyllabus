import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

@WebServlet("/response")
public class DocumentResponseServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Response endpoint called");

        String name = request.getParameter("name").replace("+", " ");
        String eventResponse = request.getParameter("event");

        Object obj = request.getAttribute("signedSet");
        HashSet<String> set;
        if(obj == null) {
            set = new HashSet<>();
            request.setAttribute("signedSet", set);
        }
        else set = (HashSet<String>)obj;

        set.add(name);

        if(eventResponse.equalsIgnoreCase("cancel")
                || eventResponse.equalsIgnoreCase("decline")
                || eventResponse.equalsIgnoreCase("viewing_complete")) {
            response.sendRedirect("signingFailed.html");
        }
        else if(eventResponse.equalsIgnoreCase("session_timeout")) {
            response.sendRedirect("signingTimeout.html");
        }
        else if(eventResponse.equalsIgnoreCase("signing_complete")) {
            response.sendRedirect("signingComplete.html");
        }
        else if(eventResponse.equalsIgnoreCase("exception")
                || eventResponse.equalsIgnoreCase("fax_pending")) {
            response.sendRedirect("signingError.html");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}
