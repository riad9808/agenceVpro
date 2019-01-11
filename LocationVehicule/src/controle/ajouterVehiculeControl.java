package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Bus;
import beans.Depot;
import beans.Gestionnaire;
import beans.Moto;
import beans.Vehicule;
import beans.Voiture;
import traitement.EspaceGestionnaire;




@WebServlet("/ajouterVehiculeControl")
public class ajouterVehiculeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ajouterVehiculeControl() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setCharacterEncoding( "UTF-8" );

		try {
			if(request.getSession().getAttribute("Gestionnaire") !=null) {
				EspaceGestionnaire e=new EspaceGestionnaire();

				Gestionnaire g=(Gestionnaire) request.getSession().getAttribute("Gestionnaire");
				if(e.Grade(g).equals("Garagiste")) {

					String type=request.getParameter("type");
					String matricule=request.getParameter("matricule");
					String marque=request.getParameter("marque");
					String modele=request.getParameter("modele");
					String tarifHeure=request.getParameter("tarifHeure");
					String tarifJour=request.getParameter("tarifJour");
					String codeD=request.getParameter("codeDepot");
					if( (type.trim().isEmpty()) ||(matricule.trim().isEmpty()) || (marque.trim().isEmpty()) || (modele.trim().isEmpty()) || (tarifHeure.trim().isEmpty()) || (tarifJour.trim().isEmpty()) || (codeD.trim().isEmpty()) ) {
						
						response.getWriter().write("remplissez tout le formulaire S.V.P");
					}else {
						Voiture vtest=new Voiture();
						vtest.setMatricule(matricule);
						if(e.existematricule(vtest)==true) {
							response.getWriter().write("matricule existe déja");
						}else {
						int codeDepot=Integer.parseInt(codeD);
						Depot d=new Depot();
						d.setCode(codeDepot);
							
						if(e.existeDepot(d)) {
							if(e.depotPlein(d)) {
								response.getWriter().write("ce depot est pelin");
							}else {
								float tarifH=Float.parseFloat(tarifHeure);
								float tarifJ=Float.parseFloat(tarifJour);	
								Vehicule veh=new Voiture();
								veh.setTarifHeure(tarifH);
								veh.setTarifJour(tarifJ);
								if(e.conditionPrixHeureJour(veh)) {
																	
								
								switch(type) {
								case "bus" : 
									Bus b=new Bus();
									b.setMatricule(matricule);
									b.setMarque(marque);
									b.setModele(modele);
									b.setTarifHeure(tarifH);
									b.setTarifJour(tarifJ);
									b.setCodeDepot(codeDepot);
									b.setType(type);

									String nombrePlace=request.getParameter("nbplace");
									if(nombrePlace.trim().isEmpty()) {
										response.getWriter().write("remplissez tous le formulaire S.V.p");

									}else {
									b.setNombrePlace(Integer.parseInt(nombrePlace));
									
									e.ajouterBus(b);

									response.getWriter().write( "Votre Bus a été Ajouter Avec Succes");
									}
									;break;
								case "voiture" : 
									Voiture v=new Voiture();
									v.setMatricule(matricule);
									v.setMarque(marque);
									v.setModele(modele);
									v.setTarifHeure(tarifH);
									v.setTarifJour(tarifJ);
									v.setCodeDepot(codeDepot);
									v.setType(type);
									
									String categorie=request.getParameter("categorieVoiture");
									if(categorie.trim().isEmpty()) {
										response.getWriter().write("remplissez tous le formulaire S.V.p");
									}else {
									v.setCategorie(categorie);
									
									e.ajouterVoiture(v);
									response.getWriter().write("voiture ajouter avec succes");
									}
									;break;
								case "moto":
									Moto m=new Moto();
									m.setMatricule(matricule);
									m.setMarque(marque);
									m.setModele(modele);
									m.setTarifHeure(tarifH);
									m.setTarifJour(tarifJ);
									m.setCodeDepot(codeDepot);
									m.setType(type);

									String cat=request.getParameter("categorieMoto");
									if(cat.trim().isEmpty()) {
										response.getWriter().write("remplissez tous le formulaire S.V.p");

									}else {
									m.setCategorie(cat);
									e.ajouterMoto(m);
									response.getWriter().write("Moto ajouter avec succes");
									}
									;break;
								}
								}else {
									response.getWriter().write("tarif de location/heure *24 doit etre superieur a tarif de location/jour");
								}
							}

						}else {
							response.getWriter().write("cet depot n'existe pas !!");
						}

					}
					
					}
				
				}else {
					response.getWriter().write("vous n'etes pas autorisé a ajouter des vehicules");
				}

				
			}else {
				this.getServletContext().getRequestDispatcher("/connexion.html").forward(request, response);
			}
			
		}catch(Exception e){
			
			
			}
		
	}
		
		
	}
