package traitement;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import beans.Client;
import beans.Location;
public class mail {
	public long email(Client c) {
		long x=0;
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("companyvpro@gmail.com", "licencegl"));
		email.setSSLOnConnect(true);
		try {
			 x=(long)(Math.random()*1000000);
			 System.out.println(x);
			email.setFrom("companyvpro@gmail.com");
			email.setSubject( "Confirmer votre adresse mail");
			email.setMsg(
					" \n  M "+c.getNom()+" "+c.getPrenom()+ "\n votre code de confirmation : " +x
					);
			
			email.addTo(c.getMail());
			email.send();
			return x;
		} catch (EmailException e) {
			
			
		}
		return x;
	}
	public long emailbloque(Client c) {
		long x=0;
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("companyvpro@gmail.com", "licencegl"));
		email.setSSLOnConnect(true);
		try {
			 
			email.setFrom("companyvpro@gmail.com");
			email.setSubject( "bloquer");
			email.setMsg(
					" \nM "+c.getNom()+" "+c.getPrenom()+ "\n votre compte a été bloquer  " );
			
			email.addTo(c.getMail());
			email.send();
			return x;
		} catch (EmailException e) {
			
			
		}
		return x;
	}
	
	public long mdpoublier(Client c) {
		long x=0;
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("companyvpro@gmail.com", "licencegl"));
		email.setSSLOnConnect(true);
		try {
			 x=(long)(Math.random()*1000000);
			 System.out.println(x);
			email.setFrom("companyvpro@gmail.com");
			email.setSubject( "nouveau mot de passe");
			email.setMsg(
					" \n  M "+c.getNom()+" "+c.getPrenom()+ "\n votre nouveau mot de passe : " +x
					);
			
			email.addTo(c.getMail());
			email.send();
			return x;
		} catch (EmailException e) {
			
			
		}
		return x;
	}
	
	public long locationAnnuler(Client c,Location l) {
		long x=0;

		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("companyvpro@gmail.com", "licencegl"));
		email.setSSLOnConnect(true);
		try {
			email.setFrom("companyvpro@gmail.com");
			email.setSubject( "Location Annuler");
			email.setMsg(
					" \n  M."+c.getNom()+" "+c.getPrenom()+ "\n votre location num :"+l.getIdLocation()+" a été malheureusement annuler suite a un dysfonctionnement innatendu du véhicule immatriculé sous le numéro"+l.getMatricule()+" \n M."+c.getPrenom() +" nous tenons a vous informez a ce moment pour prendre vos précautions \n companyvPro vous présente ses sincère excuse \n \n cordialement ." 
					);
			
			email.addTo(c.getMail());
			email.send();
			
		} catch (EmailException e) {
			
			
		}
		return x;
	}
}
