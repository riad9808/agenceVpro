package controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Bus;
import beans.Depot;
import beans.Gestionnaire;
import beans.Location;
import beans.Moto;
import beans.Vehicule;
import beans.Voiture;
import traitement.EspaceGestionnaire;


@WebServlet("/etatLocationAgenceControl")
public class etatLocationAgenceControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public etatLocationAgenceControl() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			if(request.getSession().getAttribute("Gestionnaire") !=null) {
				response.setContentType( "application/json" );
			    response.setCharacterEncoding( "UTF-8" );
				Gestionnaire g=(Gestionnaire) request.getSession().getAttribute("Gestionnaire");
				EspaceGestionnaire eg=new EspaceGestionnaire();
				Gson gson=new Gson();
				if(eg.Grade(g).equals("Gérant Principal")) {
					
				
				String type=request.getParameter("type");
				switch (type) {
				
				case "agence": {
					String typeV=request.getParameter("typeV");
					
					switch (typeV) {
					case "voiture":{

						ArrayList<Voiture> voitures=new ArrayList<Voiture>(eg.voiture());
						response.getWriter().write(gson.toJson(voitures));
					};break;

						

					case "bus": {
						ArrayList<Bus> bus=new ArrayList<Bus>(eg.bus());
						response.getWriter().write(gson.toJson(bus));
					};break;
					

						
					
					case "moto":{
						ArrayList<Moto> moto=new ArrayList<Moto>(eg.moto());
						response.getWriter().write(gson.toJson(moto));
					};break;	
						

						
					}
				};break;

				case "depot":  {
					Integer codeDepot=Integer.parseInt(request.getParameter("depot"));
					Depot d=new Depot();
					d.setCode(codeDepot);
					ArrayList<Vehicule> vehicules=new ArrayList<Vehicule>(eg.vehiculeDepot(d));		
				
					response.getWriter().write(gson.toJson(vehicules));
				};break;
				
					
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType( "application/json" );
		response.setCharacterEncoding( "UTF-8" );
		try {
			if(request.getSession().getAttribute("Gestionnaire") !=null) {
				Gestionnaire g=(Gestionnaire) request.getSession().getAttribute("Gestionnaire");
				EspaceGestionnaire eg=new EspaceGestionnaire();

				if(eg.Grade(g).equals("Gérant Principal")) {
			String mat=request.getParameter("matricule");
			Location l=new Location();
			l.setMatricule(mat);
			ArrayList<Location> etatLocationVehicule=new ArrayList<Location>(eg.etatLocationVehicule(l));
			if(etatLocationVehicule.isEmpty()) {
				response.getWriter().write("aucune location pour ce vehicule");
			}else {
				System.out.println(etatLocationVehicule.get(0).getIdClient());
				System.out.println(etatLocationVehicule.get(0).isHeure());

				Gson gg=new Gson();
				response.getWriter().println(gg.toJson(etatLocationVehicule));
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
