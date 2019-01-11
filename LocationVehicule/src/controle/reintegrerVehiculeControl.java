package controle;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AvisFinLocation;
import beans.Gestionnaire;
import beans.Vehicule;
import beans.Voiture;
import traitement.EspaceGestionnaire;


@WebServlet("/reintegrerVehiculeControl")
public class reintegrerVehiculeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public reintegrerVehiculeControl() {
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
					String matricule=request.getParameter("matricule");
					String typeR=request.getParameter("typeR");
					String codeDepot=(String)(request.getParameter("depot"));
					int etat=Integer.parseInt(request.getParameter("etat"));
					String description=request.getParameter("description");
					String dateR=(String)request.getParameter("dateR");
					String heureR=(String)request.getParameter("heurR");
				String idLocation=request.getParameter("idcontrat");
					

					
			if( (matricule.trim().isEmpty()) || (codeDepot.trim().isEmpty()) || (typeR.trim().isEmpty()) ) {
				response.getWriter().println("completez vos champ S.V.P!!");

			}else {
				
				if( (typeR.equals("retour")) && ( (dateR.trim().isEmpty()) || (heureR.trim().isEmpty())) || (idLocation.trim().isEmpty()) ) {
					response.getWriter().println("completez vos champ S.V.P!!");
				}else {
			Vehicule v=new Voiture();
			v.setMatricule(matricule);
			int code=Integer.parseInt(codeDepot);
			v.setCodeDepot(code);
			if(e.appartenirDepot(v)) {
				int existeVehicule=e.existeVehicule(v);
				if( (existeVehicule == 2 ) || (existeVehicule == 3)) {
					if( (existeVehicule ==2) && (typeR.equals("hors")) ) {
						response.getWriter().println("ce véhicule est en cours de location !! verifier vos information");
					}else {
					 if( (existeVehicule ==3) && (typeR.equals("retour")) ){
						 response.getWriter().println("ce vehicule est hors service !! verifier vos information");
					 }else {
					
				
					
			switch (typeR) {
					case "retour":
						AvisFinLocation a=new AvisFinLocation();
						
						a.setIdLocation(idLocation);
						a.setDateRetour(Date.valueOf(dateR));
						heureR=heureR+":00";
						a.setHeureRetour(Time.valueOf(heureR));
						a.setEtatRetour(etat);
						if(description.trim().isEmpty()) {
							a.setDescription("Rien a Signaler");
						}else {
							a.setDescription(description);
						}
						
						
						if(e.rendreVehicule(a,v)) {
							e.rendreVehicule(v);
							response.getWriter().println("véhicule réintegrer avec succes ");
						}else {
							response.getWriter().println("une erreur est survenu réessayer !!");
						}
						
						;break;

					case "hors":
						
						if(e.apresReparation(v)) {
							response.getWriter().println("vehicule réintegrer avec succes");
						}else {
							response.getWriter().println("une erreur survenu réessayer !");
						}
						;break;
											
					}
					 }
					}	
				}else {
					response.getWriter().print("ce véhicule est déjà dans le depot !");
				}
					
			}else {
				response.getWriter().println("ce vehicule n'appartient pas a ce depot !!");
			}
					
				}	
				}	
					
				}else {
					response.getWriter().write("vous n'etes pas autorisé a retirer ou supprimer des vehicules");
				}

				
			}else {
				this.getServletContext().getRequestDispatcher("/connexion.html").forward(request, response);
			}
			
		}catch(Exception e){
			response.getWriter().println("erreur");
			
			}

	}

}
