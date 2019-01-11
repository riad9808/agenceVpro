package controle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Client;
import beans.Gestionnaire;
import beans.Location;
import beans.Vehicule;
import beans.Voiture;
import traitement.EspaceGestionnaire;
import traitement.mail;


@WebServlet("/retirerVehiculeControl")
public class retirerVehiculeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public retirerVehiculeControl() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding( "UTF-8" );
		try {
			if(request.getSession().getAttribute("Gestionnaire") !=null) {
				EspaceGestionnaire e=new EspaceGestionnaire();

				Gestionnaire g=(Gestionnaire) request.getSession().getAttribute("Gestionnaire");
				if(e.Grade(g).equals("Garagiste")) {
					String typeR=request.getParameter("typeR");
					String matricule=request.getParameter("matricule");
					String codeDepot=(String)(request.getParameter("depot"));
					String nombreJour=(String)request.getParameter("nombre de jour");
					if( (matricule.trim().isEmpty()) || (typeR.trim().isEmpty()) || (codeDepot.trim().isEmpty()) ) {
						response.getWriter().println("completez vos champs S.V.P");
					}else {
						if( (typeR.equals("temporaire") && (nombreJour.trim().isEmpty())) ) {
							response.getWriter().println("completez vos champs S.V.P");
						}else {
							Vehicule v=new Voiture();
							v.setMatricule(matricule);
							v.setCodeDepot(Integer.parseInt(codeDepot));
							int existe=e.existeVehicule(v);
							if((existe==0) || (existe==1)) {
							
						Location l=new Location();
					switch (typeR) {
					case "location":
						e.retirerVehicule(v, "location");
						response.getWriter().println("vehicule retirer avec succes");
						;break;

					case "definitif":
						l.setMatricule(matricule);
						l.setDateDebut(e.Date_aujourdhui());
						l.setEtatLocation(true);

						ArrayList<Location> locationannuler=new ArrayList<Location>(e.annulerLocation(l));
						locationannuler.isEmpty(); 
						e.supprimerVehicule(v);
						response.getWriter().println("vehicule retirer avec succes");

						;break;
					case "temporaire":
						l.setMatricule(matricule);
						l.setEtatLocation(false);
						l.setDateDebut(e.Date_aujourdhui());
						
						LocalDate d=LocalDate.now();
						int nbj=Integer.parseInt(nombreJour);
						d=d.plusDays(nbj);
						l.setDateFin(java.sql.Date.valueOf(d));
					
						e.annulerLocation(l);

						e.retirerVehicule(v, "temporaire");
						response.getWriter().println("vehicule retirer avec succes");
						;break;
					}
					
					
					
					
							}else {
								response.getWriter().println("ce véhicule n'est pas dans le depot");
							}
					
						}
					
					}	
					
				}else {
					response.getWriter().write("vous n'etes pas autorisé a retirer ou supprimer des vehicules");
				}

				
			}else {
				this.getServletContext().getRequestDispatcher("/connexion.html").forward(request, response);
			}
			
		}catch(NumberFormatException e){
			
			}

	}

}
