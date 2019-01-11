package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Facture;
import beans.Location;
import traitement.EspaceGestionnaire;




@WebServlet("/calculerRecetteControl")
public class calculerRecetteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public calculerRecetteControl() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	
			EspaceGestionnaire g=new EspaceGestionnaire();
			String type=request.getParameter("type");
			Location l=new Location();
			Facture f=new Facture();
			switch(type) {
				case "jour" :{
					String date=request.getParameter("date");
					
					if(date.trim().isEmpty()) {
						response.getWriter().write("entrez une date s.v.p");
					}else {
						l.setDateDebut(java.sql.Date.valueOf(date));
					if(g.validiteDateLocation(l)==false) {
						response.getWriter().write("entrez une date valide");
					}else {
						f=g.RecetteJour(l);
						response.getWriter().write(""+f.getMontant());
					}
					}
				}break;
			case "periode" :{
					String dateDebut=request.getParameter("dateDebut");
					String dateFin=request.getParameter("dateFin");
				if((dateDebut.trim().isEmpty()) || (dateFin.trim().isEmpty())) {
					response.getWriter().write("entrez une periode svp");
				}else {
					l.setDateDebut(java.sql.Date.valueOf(dateDebut));
					l.setDateFin(java.sql.Date.valueOf(dateFin));

				if(g.validiteDateLocation(l)==false) {
					response.getWriter().write("entrez une periode valide");
				}else {
				f=g.RecettePeriode(l);
				response.getWriter().write(""+f.getMontant());
				}
				}
			}break;
			
		}
	}catch (NumberFormatException e) {
		
	}

	}

}
