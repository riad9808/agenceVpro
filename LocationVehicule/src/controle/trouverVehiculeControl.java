package controle;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

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


@WebServlet("/trouverVehiculeControl")
public class trouverVehiculeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public trouverVehiculeControl() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "application/json" );
	    response.setCharacterEncoding( "UTF-8" );
		try {
			if(request.getSession().getAttribute("client") == null) {
				 response.getWriter().write("notlogged");
			}else {
				
				EspaceClient e=new EspaceClient();
				ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>();
				Location l=new Location();
				String type=request.getParameter("type");

				int codeDepot=Integer.parseInt(request.getParameter("codeDepot"));
				Float prixJMin=Float.parseFloat(request.getParameter("prixDmin"));
				Float prixHMin=Float.parseFloat(request.getParameter("prixhmin"));
				Float prixJMax=Float.parseFloat(request.getParameter("prixDmax"));
				Float prixHMax=Float.parseFloat(request.getParameter("prixhmax"));
				
				String typerech=request.getParameter("typerech");
				
				String dateD=request.getParameter("dateD");
				String dateF=request.getParameter("dateF");
				String heureD=request.getParameter("heureD");
				String heureF=request.getParameter("heuref");
				
				if( (typerech.equals("date")) && ((dateD.trim().isEmpty()) || (dateF.trim().isEmpty()) ) ){
					response.getWriter().print(new Gson().toJson("entrez une periode S.V.P completer les champs date de debut et de fin"));

				}else {
				if( (typerech.equals("heure")) && ( (dateD.trim().isEmpty()) || (heureD.trim().isEmpty()) || (heureF.trim().isEmpty()) )) {
					response.getWriter().print(new Gson().toJson("entrez une periode S.V.P completer les champs date et heure de debut et de fin"));

				}else {
					if(dateD.trim().isEmpty()) {
						l.setDateDebut(null);

					}else {
						l.setDateDebut(Date.valueOf(dateD));

					}
				
					if(dateF.trim().isEmpty()) {
						l.setDateFin(Date.valueOf(dateD));

					}else {
						l.setDateFin(Date.valueOf(dateF));

					}
					if( (typerech.equals("date")) && (!e.validiteDateLocation(l)) ) {
						response.getWriter().print(new Gson().toJson("date errone"));
					
					}else {
					
					if(typerech.equals("heure")) {	
						heureD=heureD+":00";
						heureF=heureF+":00";
						l.setHeureDebut(Time.valueOf(heureD));	
						l.setHeureFin(Time.valueOf(heureF));
						l.setHeure(true);

					}else {

						l.setHeure(false);
						l.setHeureDebut(null);		
						l.setHeureFin(null);
							
					}
					
					if( (typerech.equals("heure")) && (e.periodeLocation(l)==false ) ) {
						response.getWriter().print(new Gson().toJson("entrez une periode valide S.V.P"));
					}else {
				request.getSession().setAttribute("location", l);	
				request.getSession().setAttribute("typeVehicule", type);	
				
				switch (type) {
				case "voiture":
					Voiture v=new Voiture();
					Voiture vmax=new Voiture();
					v.setType(type);
					v.setCategorie(request.getParameter("typeV"));
					if(v.getCategorie()=="" || v.getCategorie().equals("tout")) {
						v.setCategorie(null);
					}
					String marquev=request.getParameter("marqueV");
					String modelev=request.getParameter("modeleV");
					System.out.println(modelev);
					if(marquev=="" ) {
						marquev ="";
						modelev ="";
					}
					if(modelev=="" ||modelev==null) {
						modelev ="";
					}
					v.setMarque(marquev);
					v.setModele(modelev);
					v.setTarifJour(prixJMin);
					v.setTarifHeure(prixHMin);
					v.setCodeDepot(codeDepot);
					vmax.setTarifJour(prixJMax);
					vmax.setTarifHeure(prixHMax);
					System.out.println(prixJMax);
					vehicules=e.TrouverVehicule(v,vmax,l);
					
					break;

				case "bus":
					Bus b=new Bus();
					Bus bmax=new Bus();
					b.setType(type);
					b.setNombrePlace(Integer.parseInt(request.getParameter("nbplace")));
					String marqueb=request.getParameter("marqueB");
					String modeleb=request.getParameter("modeleB");
					if(marqueb=="") {
						marqueb ="";
						modeleb ="";
					}
					if(modeleb=="" ) {
						modeleb ="";
					}
					b.setMarque(marqueb);
					b.setModele(modeleb);
					b.setTarifJour(prixJMin);
					b.setTarifHeure(prixHMin);
					b.setCodeDepot(codeDepot);
					bmax.setTarifJour(prixJMax);
					bmax.setTarifHeure(prixHMax);
					vehicules=e.TrouverVehicule(b,bmax,l);

					break;
					
				case "moto":
					Moto m =new Moto();
					Moto mmax=new Moto();
					m.setType(type);
					m.setCategorie(request.getParameter("typeM"));
					if(m.getCategorie()=="" || m.getCategorie().equals("tout")) {
						m.setCategorie(null);
					}
					String marquem=request.getParameter("marqueM");
					String modelem=request.getParameter("modeleM");
					if(marquem.trim().isEmpty() ) {
						marquem ="";
						modelem="";

					}
					if(modelem.trim().isEmpty() ) {
						modelem ="";
					}
					m.setMarque(marquem);
					m.setModele(modelem);
					m.setTarifJour(prixJMin);
					m.setTarifHeure(prixHMin);
					m.setCodeDepot(codeDepot);
					mmax.setTarifJour(prixJMax);
					mmax.setTarifHeure(prixHMax);
					vehicules=e.TrouverVehicule(m,mmax,l);

					break;
				}
					
					if(vehicules.isEmpty()) {
						response.getWriter().println(new Gson().toJson("les vehicules avec vos caracteristique sont indisponible"));
					}else {
						Gson g=new Gson();
						response.getWriter().print( g.toJson(vehicules));
					}
					}
					 }				
				}
			}
			}
			
		}catch (Exception e) {
			
		}
		

	}

}
