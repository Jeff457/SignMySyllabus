import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/response")
public class DocumentResponseServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");
        String eventResponse = request.getParameter("event");

        //TODO---Store it somewhere if they successfully signed the document

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


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setStatus(200);
    }
}
