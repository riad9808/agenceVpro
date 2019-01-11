package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Client;
import beans.Utilisateur;
import traitement.EspaceClient;



@WebServlet("/gererProfilClientControl")
public class gererProfilClientControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public gererProfilClientControl() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			if(request.getSession().getAttribute("client") !=null) {

	    response.setCharacterEncoding( "UTF-8" );
			Client c=(Client)request.getSession().getAttribute("client");
			String password=(String)request.getParameter("ancienpassword");
			c.setPassword(password);
			Client Nouveau=new Client();
			Client ConfirmerPassword=new Client();
			EspaceClient e=new EspaceClient();
			
			Nouveau.setTelephone(request.getParameter("telephone"));
			Nouveau.setMail(request.getParameter("mail"));
			Nouveau.setAdresse(request.getParameter("adresse"));
			Nouveau.setUser(request.getParameter("User"));
			Nouveau.setPassword(request.getParameter("password"));
			ConfirmerPassword.setPassword(request.getParameter("password1"));
			
			if(e.Identification(c)) {
			
					if((Nouveau.getPassword().trim().isEmpty())) {
						Nouveau.setPassword(null);
					}
					if((ConfirmerPassword.getPassword().trim().isEmpty())) {
						ConfirmerPassword.setPassword(null);
					}
					if(e.ConfirmerMotDePasse(Nouveau, ConfirmerPassword)== false) {
						response.getWriter().write("votre mot de passe n'est pas identique");
						}else {
							
							c=e.profilClient(c);
							Nouveau.setIdClient(c.getIdClient());		
								
								if((Nouveau.getTelephone().trim().isEmpty())) {
									Nouveau.setTelephone(null);
									}else {

										if(e.existeTelephone(Nouveau)) {
											Nouveau.setTelephone(null);
											 response.getWriter().write(" telephone Existe Déja ,Vos modification n'ont pas eté validé");
										}
										}
								if((Nouveau.getMail().trim().isEmpty())) {
									Nouveau.setMail(null);
									}else {

										if(e.existeMail(Nouveau)) {
											Nouveau.setMail(null);
											 response.getWriter().write( " mail Existe Déja ,Vos modification n'ont pas eté validé");
										}
									}
								if((Nouveau.getAdresse().trim().isEmpty())) {
									Nouveau.setAdresse(null);
									}
								if((Nouveau.getUser().trim().isEmpty())) {
									Nouveau.setUser(null);
									}else {
										if(e.existeUser(Nouveau)==true) {
											Nouveau.setUser(null);
											 response.getWriter().write( " Username Existe Déja ,Vos modification n'ont pas eté validé");
											}
									}
								
								if(e.modifierProfil(Nouveau)) {
									Client x=e.profilClientId(Nouveau);
									request.getSession().setAttribute("client", x);
									 response.getWriter().print( "sucess");
								}
								


								
						}
					
					
					
			}else {
					response.getWriter().write("votre mot de passe est incorrect");
						}
			
			
		}else {
			 this.getServletContext().getRequestDispatcher(new Gson().toJson("erreur")).forward(request, response);

			}
		}catch(Exception e){
				
			}
	}
}
		
		
		
		
		
		