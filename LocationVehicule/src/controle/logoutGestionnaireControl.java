package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Gestionnaire;


@WebServlet("/logoutGestionnaireControl")
public class logoutGestionnaireControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public logoutGestionnaireControl() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			if(request.getSession().getAttribute("Gestionnaire") !=null) {
				HttpSession session=request.getSession(false);
				Gestionnaire g=(Gestionnaire) session.getAttribute("Gestionnaire");
				session.invalidate();
				this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);		
				
				
			}else {
				
				this.getServletContext().getRequestDispatcher("/connexion.html").forward(request, response);

}
		} catch (Exception e) {

		}

		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
