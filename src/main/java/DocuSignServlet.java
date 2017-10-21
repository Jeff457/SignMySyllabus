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
		docuSign = new DocuSign();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String name = request.getParameter("name");
		String action = request.getParameter("action");
		if (name != null)
			System.out.println(name + " suck my duck");
		if (action != null && name != null)
		{
			if (action.equalsIgnoreCase("sign"))
			{
				String signingUrl = docuSign.getSigningUrl(name);
				RequestDispatcher dispatcher = request.getRequestDispatcher(signingUrl);
				dispatcher.forward(request, response);
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