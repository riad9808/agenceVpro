package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Client;


@WebServlet("/logoutClientControl")
public class logoutClientControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public logoutClientControl() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			if(request.getSession().getAttribute("client") != null) {
				HttpSession session=request.getSession(false);
				Client c=(Client) session.getAttribute("client");
				session.invalidate();
				
				this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);		
			}else {
				this.getServletContext().getRequestDispatcher("/identification.html").forward(request, response);
			}
		} catch (Exception e) {

		}
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
