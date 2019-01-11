package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Gestionnaire;
import beans.Vehicule;
import beans.Voiture;
import traitement.EspaceGestionnaire;


@WebServlet("/modifierPrixControl")
public class modifierPrixControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public modifierPrixControl() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			if(request.getSession().getAttribute("Gestionnaire") !=null) {
				Gestionnaire g=(Gestionnaire) request.getSession().getAttribute("Gestionnaire");
				EspaceGestionnaire eg=new EspaceGestionnaire();

				if(eg.Grade(g).equals("Employé")) {
					
					Vehicule v=new Voiture(); 
					v.setMatricule(request.getParameter("matricule"));
					String tarifh=(String)request.getParameter("tarifHeure");
					String tarifJ=(String)request.getParameter("tarifJour");
				
					if(tarifh.trim().isEmpty() || tarifJ.trim().isEmpty()) {
						response.getWriter().write("completez vos champs !");
					}else {
				v.setTarifHeure(Float.parseFloat(tarifh));
				v.setTarifJour(Float.parseFloat(tarifJ));
				if(eg.possibleModifierPrix(v)) {
					if(eg.conditionPrixHeureJour(v)) {
						if(eg.modifierPrix(v)) {
							response.getWriter().write("prix de location modifier avec succes");
						}else {
							response.getWriter().write("une erreur survenu,réessayer !!");
						}
					}else {
						response.getWriter().write("le prix de location/heure*24 doit etre superieur au prix de location/jour");
					}
					
				}else {
					response.getWriter().write("vous ne pouvez pas modifier le prix de ce véhicule pour le momoent");
				}
				
					}				
			}else {
				response.getWriter().write("vous n'etes pas autorisé a effectuer cette tache");
			}
			
}else {
			this.getServletContext().getRequestDispatcher("/connexion.html").forward(request, response);
}
		} catch (Exception e) {


		}
	}

}
