package controle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Client;
import traitement.EspaceClient;
import traitement.mail;



@WebServlet("/inscriptionClientControl")
public class inscriptionClientControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public inscriptionClientControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		long y=Long.parseLong(request.getParameter("code"));
		Client c=(Client)request.getSession().getAttribute("client");
		EspaceClient e=new EspaceClient(); 
		long t=(long) request.getSession().getAttribute("codec");
		if(y==t) {
			if(e.inscription(c)) {
				
				
				out.print("ok");
				}else {
					out.print("Verifier vos informations !!");

				}
		}else {
			out.print("code incorect!!");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		try {
			Client c=new Client();
			Client c1=new Client();
			EspaceClient e=new EspaceClient();
			
			String nom=request.getParameter("nom");
			String prenom=request.getParameter("prenom");
			java.sql.Date date=java.sql.Date.valueOf(request.getParameter("dateNaissence"));
			Integer numPermis=Integer.parseInt(request.getParameter("numPermisConduire"));
			String telephone =request.getParameter("NumeroDeTelephone");
			String mail=request.getParameter("mail");
			String adresse=request.getParameter("adresse");
			String user=request.getParameter("nomUser");
			String password=request.getParameter("password");
			String password1=request.getParameter("password1");
			
			c.setPassword(password);
			c1.setPassword(password1);
			
			if(nom.trim().isEmpty()||prenom.trim().isEmpty()||(numPermis==null)||telephone.trim().isEmpty()||mail.trim().isEmpty()||adresse.trim().isEmpty()||user.trim().isEmpty()||password.trim().isEmpty()||password1.trim().isEmpty()) {
				out.println("remplissez tous le formulaire");
			}else {
			
				if(!(e.ConfirmerMotDePasse(c, c1))) {
					out.println("mot de passe de confirmation incohrent");
				}else {
				user=user.trim();
				
				c.setNom(nom);
				c.setPrenom(prenom);
				c.setDateNaissance(date);
				c.setIdClient(numPermis);
				c.setTelephone(telephone);
				c.setMail(mail);
				c.setAdresse(adresse);
				c.setUser(user);
				c.setDateInscription(e.Date_aujourdhui());	
				if(!(e.legalAge(c))) {
					out.println("vous devez avoir au moins 18 ans");

				}else {
			if(e.existePermis(c)==true) {
				out.println("permis existe");
				
			}	else {
					if(e.existeMail(c)) {
						out.println("mail existe");

					}else {
						if(e.existeUser(c)==true) {
							out.println("user existe");
						}	else {
							
							if(e.existeTelephone(c)) {
								out.println("numero de telephone existe");

							}else {
								HttpSession session=null;
								session =request.getSession(true);
								session.setAttribute("client", c);
								mail email = new mail();
								long t=email.email(c);
								session.setAttribute("codec", t);
								out.print("yes");
						}
									}
					}
			}	
			}
				}}
			}catch (Exception e) {

	}
	}

}
