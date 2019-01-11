package controle;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.AvisFinLocation;
import beans.Bus;
import beans.Client;
import beans.Facture;
import beans.Gestionnaire;
import beans.Location;
import beans.Moto;
import beans.Vehicule;
import beans.Voiture;
import traitement.EspaceGestionnaire;
import traitement.pdfAvis;
import traitement.pdfContrat;
import traitement.pdfFacture;


@WebServlet("/gererLocationControl")
public class gererLocationControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public gererLocationControl() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson=new Gson();
		response.setCharacterEncoding( "UTF-8" );
		response.setContentType( "application/json" );
		try {
			EspaceGestionnaire e=new EspaceGestionnaire();
			String idloc=request.getParameter("loc");
			Location l=new Location();
			l.setIdLocation(idloc);
			Location ll=e.recupererInfoLocation(l);
			Client c=new Client();
			c.setIdClient(ll.getIdClient());
			Client cc = e.getClient(c);
			Vehicule x=null;
			String  tt="";
			for(Bus v:e.bus()) {
				if(v.getMatricule().equals(ll.getMatricule())) {
					 x=v;
					 tt="bus";
					break;
				}
			}
			for(Voiture v:e.voiture()) {
				if(v.getMatricule().equals(ll.getMatricule())) {
					 x=v;
					 tt="voiture";
					break;
				}
			}
			for(Moto v:e.moto()) {
				if(v.getMatricule().equals(ll.getMatricule())) {
					 x=v;
					 tt="moto";
					break;
				}
			}
			HashMap<String, String> publisher = new HashMap<String, String>();
			publisher.put("idlocation", ll.getIdLocation());
			publisher.put("idclient", ""+cc.getIdClient());
			publisher.put("nom", cc.getNom());
			publisher.put("prenom", cc.getPrenom());
			publisher.put("marque", x.getMarque());
			publisher.put("modele", x.getModele());
			
			publisher.put("matricule", x.getMatricule());
			
			publisher.put("type", tt);
			response.getWriter().write(gson.toJson(publisher));
		} catch (Exception e) {

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson=new Gson();
		response.setCharacterEncoding( "UTF-8" );
		try {
			if(request.getSession().getAttribute("Gestionnaire") !=null) {
				EspaceGestionnaire e=new EspaceGestionnaire();
					
				Gestionnaire g=(Gestionnaire) request.getSession().getAttribute("Gestionnaire");
				if(e.Grade(g).equals("Employé")) {
				String type=request.getParameter("typeO");
				switch (type) {
				case "contrat":
					response.setContentType( "application/json" );

					String page=request.getParameter("contrat");
					switch (page) {
					case "getContrat":
				
				String idClient=request.getParameter("idclient");
				String dateDebut=(String)request.getParameter("dateD");
				String heureDebut=(String)request.getParameter("heurD");
				if( (idClient.trim().isEmpty()) ) {
					response.getWriter().println(gson.toJson("vous devez saissir un idClient valide"));
				}else {
					Location l=new Location();
					l.setIdClient(Integer.parseInt(idClient));
					if(!dateDebut.trim().isEmpty()) {
						l.setDateDebut(Date.valueOf(dateDebut));
						}
					if(!heureDebut.trim().isEmpty()) {
						l.setHeureDebut(Time.valueOf(heureDebut+":00"));
					}
					l.setModePayement(false);
					Client c=new Client();
					c.setIdClient(Integer.parseInt(idClient));
					if(e.getClient(c)==null){
						response.getWriter().write(gson.toJson("client inexistant"));
					}else {
						if(e.listeLocation(l).isEmpty()) {
							response.getWriter().write(gson.toJson("aucune location trouver"));
						}else {
							
							response.getWriter().write(gson.toJson(e.listeLocation(l)));
							//request.getSession().setAttribute("location", e.listeLocation(l));
							//this.getServletContext().getRequestDispatcher("/listelocationclient.jsp").forward(request, response);
						
						}
					}
				}	;break;
				
					case "choisirContrat":
						String modalitePayement=request.getParameter("modep");
						System.out.println(modalitePayement);
						String idLocation=request.getParameter("idLocation");
						System.out.println(idLocation);
						Location l=new Location();
						l.setIdLocation(idLocation);
						pdfContrat pdfcontrat=new pdfContrat();
						Location ttt=e.recupererInfoLocation(l);
						if(modalitePayement.equals("cheque")) {
							ttt.setModePayement(false);
							}else {
								ttt.setModePayement(true);
							}
						if(e.etablirContrat(ttt)) {
						Client cc=new Client();
						cc.setIdClient(ttt.getIdClient());
						Client ccc=e.getClient(cc);
						Vehicule x=null;
						String  tt="";

						for(Bus v:e.bus()) {
							if(v.getMatricule().equals(ttt.getMatricule())) {
								 x=v;
								 tt="bus";
								break;
							}
						}
						for(Voiture v:e.voiture()) {
							if(v.getMatricule().equals(ttt.getMatricule())) {
								 x=v;
								 tt="voiture";
								break;
							}
						}
						for(Moto v:e.moto()) {
							if(v.getMatricule().equals(ttt.getMatricule())) {
								 x=v;
								 tt="moto";
								break;
							}
						}
						
						
						pdfcontrat.Contratpdf(ttt, x, ccc,tt);
						response.getWriter().write("sucess");}
						
						;break;
					}
					;break;

					
				case "facture":
					float montantTotal=00;
					Client c=new Client();
					Vehicule v=new Voiture();
					Facture f=new Facture();

					String typeFacture=request.getParameter("type");
					
						switch (typeFacture) {
						
						case "simple":
							
						String idContrat=request.getParameter("idContrat");
						if(idContrat.trim().isEmpty()) {
							response.getWriter().println("completez vos champs S.V.P");
						}else {
							idContrat=idContrat.trim();
							Location l=new Location();
							l.setIdLocation(idContrat);
							if(e.existeContrat(l)==null){
								
								response.getWriter().println("vous devez etablir un contrat de location d'abord");
							}else {
								
								c.setIdClient(e.recupererInfoLocation(l).getIdClient());
								l.setEtatLocation(true);
							 
							f.setFactureDePenalite(false);
							String codeFacture=l.getIdLocation()+"FACT";
							f.setCodeFacture(codeFacture);
							if(e.existeFacture(f)) {
								response.getWriter().println("cette facture est déja etablit !");
							}else {
							f.setIdLocation(idContrat);
							
							montantTotal=e.calculerMontantTotal(l);
							f.setMontant(e.calculerMontantBrut(l));
							Client c2=e.getClient(c);
							Location l2=e.recupererInfoLocation(l);
							Vehicule x=null;
							String  tt="";

							for(Bus vv:e.bus()) {
								if(vv.getMatricule().equals(l2.getMatricule())) {
									 x=vv;
									 tt="bus";
									break;
								}
							}
							for(Voiture vv:e.voiture()) {
								if(vv.getMatricule().equals(l2.getMatricule())) {
									 x=vv;
									 tt="voiture";
									break;
								}
							}
							for(Moto vv:e.moto()) {
								if(vv.getMatricule().equals(l2.getMatricule())) {
									 x=vv;
									 tt="moto";
									break;
								}
							}
							if(e.etablirFacture(f)) {
								 response.getWriter().println("succes");

							pdfFacture pdfFacture=new pdfFacture();
							pdfFacture.Facturepdf(f, x, c2,montantTotal,tt,l2);
							}else {
								 response.getWriter().println("une erreur survenu, verifier vos information !!");

							}
							}
							}
						}
							;break;

						case "penalite":
							Facture fp=new Facture();
							String montant=(String)request.getParameter("montant");
							String idC=request.getParameter("idContrat");
							
							if( (montant.trim().isEmpty()) || (idC.trim().isEmpty() )){
								response.getWriter().println("completez vos champs S.V.P");
							}else {
								Location l=new Location();
								l.setIdLocation(idC);
								Location ll=new Location();
								ll.setIdLocation(idC);
								if(e.existeContrat(l)==null){
									response.getWriter().println("vous devez etablir un contrat de location d'abord");
								}else {
								
								float montantp=Float.parseFloat(montant);
								
								l.setEtatLocation(false);
								
							 montantTotal=e.calculerMontantTotal(montantp);
							 
							int x=(int) (Math.random()*1000);
							String codeFacture=l.getIdLocation()+x+"FP";
							fp.setCodeFacture(codeFacture);
							fp.setIdLocation(idC);	
							fp.setMontant(montantp);
							fp.setFactureDePenalite(true);
							
							Location l2=e.recupererInfoLocation(ll);
							

							c.setIdClient(l2.getIdClient());
							Client c2=e.getClient(c);
							Vehicule xx=null;
							String  tt="";

							for(Bus vv:e.bus()) {
								if(vv.getMatricule().equals(l2.getMatricule())) {
									 xx=vv;
									 tt="bus";
									break;
								}
							}
							for(Voiture vv:e.voiture()) {
								if(vv.getMatricule().equals(l2.getMatricule())) {
									 xx=vv;
									 tt="voiture";
									break;
								}
							}
							for(Moto vv:e.moto()) {
								if(vv.getMatricule().equals(l2.getMatricule())) {
									 xx=vv;
									 tt="moto";
									break;
								}
							}
							
						if(	e.etablirFacture(fp)) {
							response.getWriter().println("succes");

							pdfFacture pdfFacture=new pdfFacture();
							pdfFacture.Facturepdf(fp, xx, c2,montantTotal,tt,l2);
								
						}else {
									response.getWriter().println("une erreur survenu, verifier vos information !!");

								}
								
							}
							}
							;break;
							
						}
						
					;break;
					
					
				case "avis":
					String idContrat=request.getParameter("idContrat");
					if(idContrat.trim().isEmpty()) {
						response.getWriter().println("completez vos champs S.V.P");
					}else {
						Location l=new Location();
						l.setIdLocation(idContrat);
						AvisFinLocation a=new AvisFinLocation();
						a=e.recupererAvis(l);
						if(a.equals(null)) {
							response.getWriter().println("vehicule non rendu ou location inexistante !");
						}else {
							
							//response.getWriter().println(a.getIdAvis()+ " " + a.getIdLocation() + " " +a.getDateRetour() +" " + a.getHeureRetour()+ " "+a.getDescription() + a.getEtatRetour());
							pdfAvis pdfavis=new pdfAvis();
							Location t=e.recupererInfoLocation(l);
							Client cc=new Client();
							cc.setIdClient(t.getIdClient());
							Client ccc=e.getClient(cc);
							pdfavis.Avispdf(a, t.getMatricule(),ccc);
							response.getWriter().write("sucess");
						
						}
					}
					;break;
				}
				
				
				
				}else {
					response.getWriter().write("vous n'etes pas autorisé a gerer des locations");
				}

				
			}else {
				this.getServletContext().getRequestDispatcher("/connexion.html").forward(request, response);
			}
			
		}catch(Exception e){
			
			}


	}

}
