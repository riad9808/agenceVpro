package controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Client;
import beans.Gestionnaire;
import beans.Location;
import traitement.EspaceGestionnaire;
import traitement.mail;


@WebServlet("/gererClientControl")
public class gererClientControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public gererClientControl() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			if(request.getSession().getAttribute("Gestionnaire") !=null) {
				Gestionnaire g=(Gestionnaire) request.getSession().getAttribute("Gestionnaire");
				EspaceGestionnaire eg=new EspaceGestionnaire();

				if(eg.Grade(g).equals("Employé")) {
					
				
			ArrayList<Client> listeClient=new ArrayList<Client>();
			listeClient=eg.listeClient();
			if(listeClient.isEmpty()) {
				response.getWriter().println("aucun client");
			}else {
				request.setAttribute("listeClient", listeClient);
				this.getServletContext().getRequestDispatcher("/gererClients.jsp").forward(request, response);
			}
				}else {
					response.getWriter().println("vous n'etes pas autorisé a effectuer cette tache");
				}
				
			}else {
				this.getServletContext().getRequestDispatcher("/connexion.html").forward(request, response);
			}
		} catch (Exception e) {


		}
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getSession().getAttribute("Gestionnaire") !=null) {
				Gestionnaire g=(Gestionnaire) request.getSession().getAttribute("Gestionnaire");
				EspaceGestionnaire eg=new EspaceGestionnaire();
				if(eg.Grade(g).equals("Employé")) {
					
					String etat=request.getParameter("action");
					
					String t=request.getParameter("bloque");
					char type='0';
					
					String id;
					String action; 
					Integer idcli;
					if(etat==null) {
						type=t.charAt(0);
						  
							if(type=='a') {
								action="debloquer";
							}else {
								
								
								action="bloquer";

							}
						StringBuilder idClient=new StringBuilder(t);
						idClient.deleteCharAt(0);
						 id=idClient.toString();
						idcli=Integer.parseInt(id);

					}else {
						id=etat;
						action="etat";
						idcli=Integer.parseInt(id);

					}

					
				Client c=new Client();
				c.setIdClient(idcli);
				switch (action) {
				case "etat": ;
					ArrayList<Location> etatClient=new ArrayList<Location>(eg.etatLocationClient(c));
					if(etatClient.isEmpty()) {
						
						response.getWriter().write("aucune location pour ce client");
					}else {
						 request.getSession().setAttribute("etat", eg.etatLocationClient(c));
						request.getSession().setAttribute("client", eg.getClient(c));
						response.getWriter().write(new Gson().toJson(eg.etatLocationClient(c)));
							//this.getServletContext().getRequestDispatcher("/gererClients.jsp").forward(request, response); 
						
					}
					break;

				case "bloquer": 
					if(eg.existeClientBloque(c)) {
						response.getWriter().write("ce client est déja bloqué !");
					}else { 
						Boolean def=false;
					
					
						if(type=='c') {
							def=true;
						} 
						Location l=new Location();
						l.setIdClient(c.getIdClient());
						eg.annulerLocation(l);
						mail mail = new mail();
						mail.emailbloque(eg.getClient(c));
						if(eg.bloqueClient(c, def)) {
							response.getWriter().write("bloquer avec succes");
							
						}else {
							response.getWriter().write("une erreur survenu réessayer !!");
						}
					}
					;break;
				
				case "debloquer":
					if(eg.existeClientBloque(c)) {
						
					if(eg.DebloquerClient(c)) {
						response.getWriter().write("succes");
					}else {
						response.getWriter().write("une erreur survenu réessayer !!");
					}
					}else {
						response.getWriter().write("ce client est déja debloquer ");
					}
					;break;	
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
