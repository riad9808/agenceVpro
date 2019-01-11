package controle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Bus;
import beans.Client;
import beans.Location;
import beans.Moto;
import beans.Vehicule;
import beans.Voiture;
import traitement.EspaceClient;


@WebServlet("/louerVehiculeControl")
public class louerVehiculeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public louerVehiculeControl() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding( "UTF-8" );

		try {
			if(request.getSession().getAttribute("client")!=null) {
			EspaceClient e=new EspaceClient();
			Location l=(Location)request.getSession().getAttribute("location");
			String type=(String)request.getSession().getAttribute("typeVehicule");
			String matricule=(String)request.getParameter("louer");
			String idLocat="";
			Client c=(Client)request.getSession().getAttribute("client");
			l.setMatricule(matricule);
			l.setIdClient(c.getIdClient());
			LocalDate ll=l.getDateDebut().toLocalDate();
			int randoma=(int)(Math.random()*1000);
			String ra=String.valueOf(randoma);
			int randomb=(int)(Math.random()*10000);
			String rb=String.valueOf(randomb);

			switch(type) {
			case "voiture":
				Voiture v=new Voiture();
				v.setMatricule(matricule);
				v=e.getVoiture(v);
				idLocat=ra+l.getIdClient()+"V";
				;break;
			case "bus":
				Bus b=new Bus();
				b.setMatricule(matricule);
				b=e.getBus(b);
				idLocat=ra+l.getIdClient()+"B";

				;break;
			case "moto":
				Moto m=new Moto();
				m.setMatricule(matricule);
				m=e.getMoto(m);
				idLocat=ra+l.getIdClient()+"M";

				;break;
				
			}
			
			
			 
			idLocat=idLocat+"J"+ll.getDayOfYear()+ll.getYear()+rb;
			if(l.isHeure()) {
			LocalTime lll=l.getHeureDebut().toLocalTime();
			idLocat=idLocat+"H"+lll.getHour();
			}
			System.out.println(idLocat);
			l.setIdLocation(idLocat);
				if(e.disponibiliteLocation(l)==true) {
		
			if(e.louer(l)==true) {
				response.getWriter().println("succes");
			}else {
				response.getWriter().println("ereur"+l.getMatricule());
				
			}
			
			}else {
				response.getWriter().println("ce vehicule est déja louer a cette periode");
			}
			}else {
				this.getServletContext().getRequestDispatcher("/identification.html").forward(request, response);

			}
			}catch(Exception e) {
				
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
