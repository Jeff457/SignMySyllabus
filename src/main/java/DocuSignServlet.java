import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/DocuSignServlet")
public class DocuSignServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private DocuSign docuSign;

	public DocuSignServlet()
	{
		super();
		docuSign = DocuSign.INSTANCE;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String name = request.getParameter("name");
		String action = request.getParameter("action");

		if (action != null && name != null)
		{
			if (action.equalsIgnoreCase("signParent"))
			{
				String signingUrl = docuSign.getParentSigningUrl(name);
				System.out.println(signingUrl);
				JSONObject jobj = new JSONObject();
				jobj.put("url", signingUrl);
				response.getWriter().write(jobj.toString());
			}
			else if(action.equalsIgnoreCase("signChild")) {
				String signingUrl = docuSign.getChildSigningUrl(name);
				System.out.println(signingUrl);
				JSONObject jobj = new JSONObject();
				jobj.put("url", signingUrl);
				response.getWriter().write(jobj.toString());
			}
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");
		if (action != null)
		{
			System.out.println("Suck my duck");
		}
	}
}