import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/remind")
public class ReminderServlet extends HttpServlet
{
    private static final String ACCOUNT_SID = "AC02637f9b63fbf1fecf3897a8bc1c2620";
    private static final String AUTH_TOKEN = "196cc659c47c04b439c5a93d6836ec7c";

    private static final long serialVersionUID = 1L;

    private final Map<String,String> phoneNumbers;

    public ReminderServlet() {
        super();

        phoneNumbers = new HashMap<>();
        phoneNumbers.put("Eric Wolfe", "+18054900758");
        phoneNumbers.put("Jeff Stanton", "+18058076413");

        com.twilio.Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String remindeeName = request.getParameter("remindee").replace("+"," ");
        String reminderName = request.getParameter("reminder").replace("+", " ");
        String className = request.getParameter("class");
        String parentOrStudent = request.getParameter("parentOrStudent");

        if(!phoneNumbers.containsKey(remindeeName)) {
            response.setStatus(400);
        }
        else {
            String phoneNumber = phoneNumbers.get(remindeeName);

            String url = null;
            if("parent".equalsIgnoreCase(parentOrStudent))
                url = DocuSign.INSTANCE.getParentSigningUrl(remindeeName);
            else
                url = DocuSign.INSTANCE.getChildSigningUrl(remindeeName);

            String message = "Signature Requested: "+url;

            Message messageObj = Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber("+14243720569"), message)
                    .create();

            System.out.print("Sent a message to the number "+phoneNumber+". SID = "+messageObj.getSid());
            response.setStatus(200);
            JSONObject jobj = new JSONObject();
            jobj.put("data", "success");
            response.getWriter().write(jobj.toString());
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }
}
