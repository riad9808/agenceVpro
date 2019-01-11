package controle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import beans.Client;
import traitement.EspaceClient;
import traitement.mail;



@WebServlet("/identificationControl")
public class identificationControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public identificationControl() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text" );
	    response.setCharacterEncoding( "UTF-8" );
		
		String user=((String)(request.getParameter("user")));
		if(user.trim().isEmpty()) {
			response.getWriter().println("entrez un nom d'utilisateur valide ");
		}else {
			user=user.trim();
		Client c=new Client();
		c.setUser(user);
		EspaceClient e=new EspaceClient();
		if(e.existeUser(c)) {
		Client cc =e.profilClient(c);
		mail m=new mail();
		long newpass=m.mdpoublier(cc);
		cc.setTelephone(null);
		cc.setMail(null);
		cc.setAdresse(null);
		cc.setUser(null);
		cc.setPassword(newpass+"");
		e.modifierProfil(cc);

		response.getWriter().write("votre nouveau mot de passe vous a été  envoyé par mail");
		}else {
			response.getWriter().write("cet nom d'utilisateur n'existe pas !");

		}
	}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType( "text" );
		    response.setCharacterEncoding( "UTF-8" );
			Client c=new Client();
			EspaceClient e=new EspaceClient();
			String user=((String)(request.getParameter("user")));
			String password=((String)(request.getParameter("password")));
			
			
	          
			String s="";
			
			if(user.trim().isEmpty()||password.trim().isEmpty()) {
				
				int x=0;
				
				if(user.trim().isEmpty()) {
					x=x+1;
				}
				if(password.trim().isEmpty()){
					x=x+2;
				}
				
				switch(x) {
				case 1 : s="entrez votre user";break;
				case 2 : s="entrez votre mot de passe";break;
				case 3 : s="entrez votre user et votre mot de passe";break;
				}
				response.getWriter().write(s);
			}
			else {
				user=user.trim();
			c.setUser(user);
				
				c.setPassword(password);
			
			if(e.Identification(c)) {
					if(e.estBloque(c)) {
							
						response.getWriter().write("votre compte est bloque veuillez vous rapprocher de notre agence ");
					}else {
						HttpSession session=null;
						session =request.getSession(true);
						
						session.setAttribute("client", e.profilClient(c));
						response.getWriter().write("sucess" );
						//this.getServletContext().getRequestDispatcher("/MenuClient.jsp").forward(request, response);
						
					}
						
				
			}else {
			
					if(e.existeUser(c)) {
						
						response.getWriter().write(" mot de passe incorrect si vous l'avez oublier entrez votre nom d'utilisateur et cliquer sur mot de passe oublier");
						
				
					}else {
						response.getWriter().write("profil client n'existe pas ");


						}
					}
			}
		}catch (NumberFormatException e) {

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
