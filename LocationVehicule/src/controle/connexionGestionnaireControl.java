package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Gestionnaire;
import traitement.EspaceGestionnaire;



@WebServlet("/connexionGestionnaireControl")
public class connexionGestionnaireControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public connexionGestionnaireControl() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			EspaceGestionnaire eg=new EspaceGestionnaire();
			Gestionnaire g=new Gestionnaire();
			String user=request.getParameter("user");
			String password=request.getParameter("password");
		if( (user.trim().isEmpty()) || (password.trim().isEmpty())) {
			response.getWriter().write("completez vos champs S.V.P");
		}else {
			user=user.trim();
			password=password.trim();
			g.setUser(user);
			g.setPassword(password);
			if(eg.ConnexionGestionnaire(g)) {
				HttpSession session=null;
				session=request.getSession(true);
				session.setAttribute("Gestionnaire", eg.profilGestionnaire(g));
				Gestionnaire Gest=eg.profilGestionnaire(g);
				String test=eg.Grade(Gest);
				String url="";
				switch(test) {
				
				case "Gérant Principal":  url="menuGerantPrincipal.jsp";break;
				case "Employé":  url="menuEmploye.jsp";break;
				case "Garagiste": url="menuGaragiste.jsp";break;

				}
				response.getWriter().write(url);
			}else {
					if(eg.existeGestionnaire(g)) {
						response.getWriter().write("mot de passe incorrect");
					}else {
						response.getWriter().write("gestionnaire inexsitant");
					}
					
			}
				
		}	
	} catch(NumberFormatException e){
		
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
