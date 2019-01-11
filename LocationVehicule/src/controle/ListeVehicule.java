package controle;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Bus;
import beans.Location;
import beans.Moto;
import beans.Vehicule;
import beans.Voiture;
import traitement.EspaceClient;


@WebServlet("/ListeVehicule")
public class ListeVehicule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "application/json" );
	    response.setCharacterEncoding( "UTF-8" );
		EspaceClient e=new EspaceClient();
		String t=request.getParameter("louer");
		request.getSession().setAttribute("louer", t);
		Vehicule x=null;
		for(Bus v:e.bus()) {
			if(v.getMatricule().equals(t)) {
				 x=v;
				break;
			}
		}
		for(Voiture v:e.voiture()) {
			if(v.getMatricule().equals(t)) {
				 x=v;
				break;
			}
		}
		for(Moto v:e.moto()) {
			if(v.getMatricule().equals(t)) {
				 x=v;
				break;
			}
		}
		HashMap<String, String> publisher = new HashMap<String, String>();
		int hours=0;
		String hd="";
		String hf="";
		Location l=(Location)request.getSession().getAttribute("location");
		Date dd=l.getDateDebut();
		Date ff=l.getDateFin();
		float tarif=e.getPeriode(l)*x.getTarifJour();
		publisher.put("tarif",""+ tarif);

		publisher.put("periode",""+ e.getPeriode(l));
		if(l.getHeureDebut()!=null) {
				hd=l.getHeureDebut().toString();
				hf=l.getHeureFin().toString();			 
			 float tarif2=x.getTarifHeure()*e.getPeriode(l);
				publisher.put("periode",""+ e.getPeriode(l));
				publisher.put("tarif",""+ tarif2);

		}else {
			 hd="00-00";
			 hf="00-00";
			 
		}
		
		

		
		String d=dd.toString();
		String f=ff.toString();
		
		
		String marque=x.getMarque();
		String modele=x.getModele();
		String matricule=x.getMatricule();
		String type=x.getType();
		int codeDepot=x.getCodeDepot();
		
			publisher.put("adresse", e.Depot().get(codeDepot-1).getAdresse());
		
		
		
			publisher.put("dateD", d);
			publisher.put("dateF", f);
			publisher.put("heurD", hd);
			publisher.put("heurF", hf);
			publisher.put("marque", marque);
			publisher.put("modele", modele);
			
			publisher.put("matricule", matricule);
			publisher.put("type", type);
			publisher.put("heure", ""+l.isHeure());

	
		
		

			
		
		System.out.println(publisher);
		
		response.getWriter().print(new Gson().toJson(publisher));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
