package controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Client;
import beans.Location;
import beans.Vehicule;
import traitement.EspaceClient;

@WebServlet("/gererLocationClientControl")
public class gererLocationClientControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public gererLocationClientControl() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			EspaceClient e=new EspaceClient();
			Client c=(Client)request.getSession().getAttribute("client");
			ArrayList<Location> location=new ArrayList<Location>(e.locationClient(c));
			if(location.isEmpty()){
				request.setAttribute("message","vous n'avez aucune location");
				this.getServletContext().getRequestDispatcher("/gererMesLocation.jsp").forward(request, response);

			}else {
				ArrayList<Vehicule> vehiculeClient=new ArrayList<Vehicule>(e.vehiculeLouerClient(location));
				request.setAttribute("location", location);
				request.setAttribute("vehicule", vehiculeClient);
				
				this.getServletContext().getRequestDispatcher("/gererMesLocation.jsp").forward(request, response);
			}
		} catch (Exception e) {

		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getSession().getAttribute("client") !=null) {
				EspaceClient e=new EspaceClient();
			String idLocation =request.getParameter("annuler");
			System.out.println(idLocation);
			
			Location l=new Location();
			l.setIdLocation(idLocation);
			Client c=(Client)request.getSession().getAttribute("client");
			l.setIdClient(c.getIdClient());
			if(e.existeLocation(l)) {
				if(e.verifierAnnulationLocation(l)) {
					if(e.annulerLocation(l)) {
						response.getWriter().write("bon");
					}else {
						response.getWriter().println("erreur survenue");
					}
				}else {
					response.getWriter().println("vous ne pouvez pas annuler cette location");
				}
			}else {
				response.getWriter().println("la location avec cet id n'existe pas");
			}
			
			
			}else {
				this.getServletContext().getRequestDispatcher("/identification.html").forward(request, response);
			}
		}catch (Exception e) {

		}	
	}

}
