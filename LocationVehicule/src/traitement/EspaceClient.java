package traitement;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import beans.Bus;
import beans.Client;
import beans.Depot;
import beans.Location;
import beans.Moto;
import beans.Vehicule;
import beans.Voiture;
import traitement.connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;

public class EspaceClient {
	
	public Boolean inscription(Client x) {
		Connection c=null;
		Boolean a=false;
		try {
		c = connexion.con();
		Statement mystat=c.createStatement();
		int myres=mystat.executeUpdate("insert into client (idClient,user,password,nom,prenom,dateNaissance,telephone,mail,adresse,dateInscription) values ("+x.getIdClient()+",'"+x.getUser()+"','"+x.getPassword()+"','"+x.getNom()+"','"+x.getPrenom()+"','"+x.getDateNaissance()+"','"+x.getTelephone()+"','"+x.getMail()+"','"+x.getAdresse()+"','"+x.getDateInscription()+"') ;");
		
		if(myres !=0){
			a= true;
		}else {
			a= false;
		}

		
		c.close();
		mystat.close();
		
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a;
	}
	
	public ArrayList<Client> listClient() {
		Connection c=null;
		ArrayList<Client> clients =new ArrayList<Client>();
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet res=mystat.executeQuery("select * from client ;");
			
			while(res.next()) {
				Client x=new Client();
				
				x.setIdClient(res.getInt("idClient"));
				x.setNom(res.getString("nom"));
				x.setPrenom(res.getString("prenom"));
				x.setDateNaissance(res.getDate("dateNaissance"));
				x.setTelephone(res.getString("telephone"));
				x.setMail(res.getString("mail"));
				x.setAdresse(res.getString("adresse"));
				x.setUser(res.getString("user"));
				x.setPassword(res.getString("password"));
				
				x.setEstBloque(res.getBoolean("estBloque"));
				x.setDateInscription(res.getDate("dateInscription"));
				
				clients.add(x);
			}
			res.close();
			mystat.close();
			c.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clients;
		
	}
	
	
	public boolean existeUser(Client x) {
			for(Client c:this.listClient()) {
					if(c.getUser().equals(x.getUser())) {
						return true;
					}
			}
		
		return false;
	}
	
	public boolean existePermis(Client x) {
		ArrayList<Client> l=this.listClient();
		for(Client c:l) {
				if(c.getIdClient().equals(x.getIdClient())) {
					return true;
				}
		}
	
	return false;
}

	
	public boolean existeMail(Client x) {
		ArrayList<Client> l=this.listClient();
		for(Client c:l) {
				if(c.getMail().equals(x.getMail())) {
					return true;
				}
		}
	
	return false;
}
	
	public boolean existeTelephone(Client x) {
		ArrayList<Client> l=this.listClient();
		for(Client c:l) {
				if(c.getTelephone().equals(x.getTelephone())) {
					return true;
				}
		}
	
	return false;
}
	
	
	public boolean Identification(Client x) {
		for(Client c:this.listClient()) {
			if(c.getUser().equals(x.getUser())) {
				if(c.getPassword().equals(x.getPassword())){
					return true;
				}else {
					return false;
				}
			}
		}
		return false;
	}
	
	public boolean estBloque(Client x) {
		for(Client c:this.listClient()) {
			if(c.getUser().equals(x.getUser())) {
				if(c.isEstBloque()==true) {
					return true;
				}else {
					return false;
				}
			}
		}
		return false;
	}
	
	public Client profilClient(Client x) {
		Client a=new Client();
		for(Client c:this.listClient()) {
			if(c.getUser().equals(x.getUser())) {
				a= c;

			}
		}
return a;
	}
	
	public Client profilClientId(Client x) {
		Client a=new Client();
		for(Client c:this.listClient()) {
			if(c.getIdClient().equals(x.getIdClient())) {
				a= c;

			}
		}
return a;
	}
	
	public Boolean ConfirmerMotDePasse(Client a,Client b) {
		if(a.getPassword()==null) {
			if(b.getPassword()==null) {
				return true;
			}else {
				return false;
			}
		
		}else {
			if(b.getPassword()==null) {
				return false;
			}
		}
		if(a.getPassword().equals(b.getPassword())) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean modifierProfil(Client x) {
		boolean a=false;
		if(x.getTelephone() !=null) {
			a=true;
			this.ModifierTelephone(x);
		}
		if(x.getMail() != null) {
			this.ModifierMail(x);
			a=true;
		}
		if(x.getAdresse() != null) {
			this.ModifierAdresse(x);
			a=true;
		}
		if(x.getUser() != null) {
			this.ModifierUserName(x);
			a=true;
		}
		if(x.getPassword() != null) {
			this.ModifierPassword(x);
			a=true;
		}
		return a;
	}
	
	public void ModifierTelephone(Client x) {
		Connection c=null;
		try {
		c = connexion.con();
		Statement mystat=c.createStatement();
		int myres=mystat.executeUpdate("update client set telephone ='"+x.getTelephone() +"' where idClient ="+x.getIdClient()+";");
		
		c.close();
		mystat.close();
		
		}catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	public void ModifierMail(Client x) {
		Connection c=null;
		try {
		c = connexion.con();
		Statement mystat=c.createStatement();
		int myres=mystat.executeUpdate("update client set mail ='"+x.getMail() +"' where idClient ="+x.getIdClient()+";");
		
		c.close();
		mystat.close();
		
		}catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	public void ModifierAdresse(Client x) {
		Connection c=null;
		try {
		c = connexion.con();
		Statement mystat=c.createStatement();
		int myres=mystat.executeUpdate("update client set adresse ='"+x.getAdresse() +"' where idClient ="+x.getIdClient()+";");
		
		c.close();
		mystat.close();
		
		}catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	public void ModifierUserName(Client x) {
		Connection c=null;
		try {
		c = connexion.con();
		Statement mystat=c.createStatement();
		int myres=mystat.executeUpdate("update client set user ='"+x.getUser() +"' where idClient ="+x.getIdClient()+";");
		
		c.close();
		mystat.close();
		
		}catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	public void ModifierPassword(Client x) {
		Connection c=null;
		try {
		c = connexion.con();
		Statement mystat=c.createStatement();
		int myres=mystat.executeUpdate("update client set password ='"+x.getPassword() +"' where idClient ="+x.getIdClient()+";");
		
		c.close();
		mystat.close();
		
		}catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	

	
	public String EstBloque(Client x) {
		if(x.isEstBloque()==true) {
        	return "Compte bloqué";
        }else
        	return "Compte Actif";
	}
	
	
	
	public Date Date_aujourdhui() {
		LocalDate d;
		d=LocalDate.now();
		Date t=java.sql.Date.valueOf(d);
		return t;
	
		
	}
	
	public ArrayList<Vehicule> TrouverVoiture(Voiture v) {
		Connection c=null;
		ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>();
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet res;
			if(v.getCategorie()!=null) {
				 res=mystat.executeQuery("select * from vehicule where type ='voiture' and categorie ='"+v.getCategorie()+"';");

			}else {
			 res=mystat.executeQuery("select * from vehicule where type ='voiture' ;");
			}
				while(res.next()) {
					Voiture voiture=new Voiture();
					voiture.setMatricule(res.getString("matricule"));
					voiture.setMarque(res.getString("marque"));
					voiture.setModele(res.getString("modele"));
					voiture.setTarifHeure(res.getFloat("tarifHeure"));
					voiture.setTarifJour(res.getFloat("tarifJour"));
					voiture.setType(res.getString("type"));
					voiture.setCodeDepot(res.getInt("codeDepot"));
					voiture.setCategorie(res.getString("categorie"));
					voiture.setEtat(res.getInt("etat"));
					
					vehicules.add(voiture);
				}
					res.close();
					mystat.close();
					c.close();
					
				}catch (SQLException e) {
					e.printStackTrace();
				}
						return vehicules;
				}
	
	public ArrayList<Vehicule> TrouverBus(Bus b) {
		Connection c=null;
		ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>();
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet res;
			if(b.getNombrePlace() !=0) {
				 res=mystat.executeQuery("select * from vehicule where type ='bus' and nombrePlace ='"+b.getNombrePlace()+"';");

			}else {
			 res=mystat.executeQuery("select * from vehicule where type ='bus' ;");
			}	
				while(res.next()) {
					Bus bus=new Bus();
					bus.setMatricule(res.getString("matricule"));
					bus.setMarque(res.getString("marque"));
					bus.setModele(res.getString("modele"));
					bus.setTarifHeure(res.getFloat("tarifHeure"));
					bus.setTarifJour(res.getFloat("tarifJour"));
					bus.setType(res.getString("type"));
					bus.setCodeDepot(res.getInt("codeDepot"));
					bus.setNombrePlace(res.getInt("nombrePlace"));
					bus.setEtat(res.getInt("etat"));
					
					vehicules.add(bus);
				}
				res.close();
				mystat.close();
				c.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
					return vehicules;
			}

	public ArrayList<Vehicule> TrouverMoto(Moto m) {
		Connection c=null;
		ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>();
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet res;
			if(m.getCategorie()!=null) {
				 res=mystat.executeQuery("select * from vehicule where type ='moto' and categorie ='"+m.getCategorie()+"';");

			}else {
			 res=mystat.executeQuery("select * from vehicule where type ='moto' ;");
			}		
					
					while(res.next()) {
					Moto moto=new Moto();
					moto.setMatricule(res.getString("matricule"));
					moto.setMarque(res.getString("marque"));
					moto.setModele(res.getString("modele"));
					moto.setTarifHeure(res.getFloat("tarifHeure"));
					moto.setTarifJour(res.getFloat("tarifJour"));
					moto.setType(res.getString("type"));
					moto.setCodeDepot(res.getInt("codeDepot"));
					moto.setCategorie(res.getString("categorie"));
					moto.setEtat(res.getInt("etat"));
					
					vehicules.add(moto);
				}
							
		res.close();
		mystat.close();
		c.close();
		
	}catch (SQLException e) {
		e.printStackTrace();
	}
			return vehicules;
	}
	
	
	public ArrayList<Vehicule> TrouverParMarque(Vehicule v,ArrayList<Vehicule> l){
		ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>();
			for(Vehicule ve:l) {
				if(ve.getMarque().equals(v.getMarque())) {
					vehicules.add(ve);
				}
			}
		return vehicules;

	}
	
	public ArrayList<Vehicule> TrouverParModele(Vehicule v,ArrayList<Vehicule> l){
		ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>();
			for(Vehicule ve:l) {
				if(ve.getModele().equals(v.getModele())) {
					vehicules.add(ve);
				}
			}
		return vehicules;

	}
	
	public ArrayList<Vehicule> TrouverParDepot(Vehicule v,ArrayList<Vehicule> l){
		ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>();
			for(Vehicule ve:l) {
				if(ve.getCodeDepot()==(v.getCodeDepot())) {
					vehicules.add(ve);
				}
			}
		return vehicules;

	}
	
	public ArrayList<Vehicule> TrouverParprixJ(Vehicule v,Vehicule vmax,ArrayList<Vehicule> l){
		ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>();
		ArrayList<Vehicule> veh =new ArrayList<Vehicule>();

		if(v.getTarifJour()!=0) {
			for(Vehicule ve:l) {
					
				if((ve.getTarifJour()) > (v.getTarifJour())) {
					vehicules.add(ve);
				}
					}
			
			}else {
				vehicules.addAll(l);
			}

		if(vmax.getTarifJour()!=0) {

			for(Vehicule vm:vehicules) {
				if( (vm.getTarifJour()) < (vmax.getTarifJour())){
					veh.add(vm);
				}
			}
		}else {
			veh.addAll(vehicules);
		}
		return veh;

	}
	
	public ArrayList<Vehicule> TrouverParprixH(Vehicule v,Vehicule vmax,ArrayList<Vehicule> l){
		ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>();
		ArrayList<Vehicule> veh =new ArrayList<Vehicule>();

		if(v.getTarifHeure()!=0) {
			for(Vehicule ve:l) {
				if( (ve.getTarifHeure()) > (v.getTarifHeure()) ) {
					vehicules.add(ve);
				}
			}
		}else {
			vehicules.addAll(l);
		}
		
		if(vmax.getTarifHeure() !=0) {
			for(Vehicule vh:vehicules) {
				if( (vh.getTarifHeure()) < (vmax.getTarifHeure())){
					veh.add(vh);
				}
			}
		}else {
			veh.addAll(vehicules);
		}
		return veh;

	}
	
	public ArrayList<Vehicule> TrouverLibre(ArrayList<Vehicule> l){
		ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>();
			for(Vehicule ve:l) {
				if(ve.getEtat()==0) {
					vehicules.add(ve);
				}
			}
		return vehicules;

	}
	
	public ArrayList<Location> vehiculeLouer(){
		Connection c=null;
		ArrayList<Location> locations = new ArrayList<Location>();
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet res=mystat.executeQuery("select * from location ;");
			while(res.next()) {
				Location l=new Location();
				l.setMatricule(res.getString("matricule"));
				l.setDateDebut(res.getDate("dateDebut"));
				l.setDateFin(res.getDate("dateFin"));
				l.setHeureDebut(res.getTime("heureDebut"));
				l.setHeureDebut(res.getTime("heureFin"));
				l.setHeure(res.getBoolean("heure"));

				locations.add(l);
			}
			res.close();
			mystat.close();
			c.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return locations;
	}
	
	public ArrayList<Vehicule> TrouverParDate(Location loc,ArrayList<Vehicule> liste){
		ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>(liste);
		ArrayList<Location> listeLocation =new ArrayList<Location>(this.vehiculeLouer());

			for(Vehicule ve:liste) {
					for(Location l1:listeLocation) {
						if(ve.getMatricule().equals(l1.getMatricule())) {
							if( ( (loc.getDateDebut().after(l1.getDateDebut()) || (loc.getDateDebut().equals(l1.getDateDebut())) )) && ( (loc.getDateDebut().before(l1.getDateFin()) || (loc.getDateDebut().equals(l1.getDateFin())))) ) {
								vehicules.remove(ve);
							}else {
								if( ( (loc.getDateFin().after(l1.getDateDebut()) || (loc.getDateFin().equals(l1.getDateDebut())) ) ) && ( (loc.getDateFin().before(l1.getDateFin())) || (loc.getDateFin().equals(l1.getDateFin())))) {
								vehicules.remove(ve);	
								
								}
							}
						}
					}
				
			}
		return vehicules;

	}
	
	public ArrayList<Vehicule> TrouverParHeure(Location loc,ArrayList<Vehicule> liste){
		ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>(liste);
		ArrayList<Location> listeLocation =new ArrayList<Location>(this.vehiculeLouer());

			for(Vehicule ve:liste) {
					for(Location l1:listeLocation) {
						if(ve.getMatricule().equals(l1.getMatricule())) {
							if(l1.isHeure()==true) {
								if(loc.getDateDebut().equals(l1.getDateDebut())) {
									if( ( (loc.getHeureDebut().equals(l1.getHeureDebut())) || (loc.getHeureDebut().after(l1.getHeureDebut())) ) && ( (loc.getHeureDebut().equals(l1.getHeureFin())) || (loc.getHeureDebut().before(l1.getHeureFin()))  ) ) {
										vehicules.remove(ve);
									}else {
										if( ( (loc.getHeureFin().equals(l1.getHeureDebut())) || (loc.getHeureFin().after(l1.getHeureDebut())) ) && ( (loc.getHeureFin().equals(l1.getHeureFin())) || (loc.getHeureFin().before(l1.getHeureFin())) ) ) {
											vehicules.remove(ve);
										}
									}
								}
								
							}else {
								if( ( (loc.getDateDebut().equals(l1.getDateDebut())) || (loc.getDateDebut().after(l1.getDateDebut())) ) && ( (loc.getDateDebut().equals(l1.getDateFin())) || (loc.getDateDebut().before(l1.getDateFin())) ) ) {
									vehicules.remove(ve);
								}
								
							}
						}
					}
			}	
		return vehicules;

	}
	public ArrayList<Vehicule> Filtrer(Vehicule v,Vehicule vmax,Location loc,ArrayList<Vehicule> liste){
		
		ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>(liste);
		
		if(loc.isHeure()) {
			vehicules=TrouverParHeure(loc, vehicules);
		}else {
			vehicules=TrouverParDate(loc, vehicules);
		}
		
			
			
		
		if(!v.getMarque().trim().isEmpty()) {
			vehicules=TrouverParMarque(v, vehicules);
		}
		if(!v.getModele().trim().isEmpty()) {
			vehicules=TrouverParModele(v, vehicules);
		}
		if(v.getCodeDepot()!=0) {
			vehicules=TrouverParDepot(v, vehicules);
		}
		if( (v.getTarifJour() !=0) ||(vmax.getTarifJour()!=0) ) {
			vehicules=TrouverParprixJ(v,vmax, vehicules);
		}
		if( (v.getTarifHeure() !=0) || (vmax.getTarifHeure()!=0) ) {
			vehicules=TrouverParprixH(v,vmax, vehicules);
		}
	return vehicules;
	}
	
	
	
	public ArrayList<Vehicule> TrouverVehicule(Voiture v,Voiture vmax,Location loc){
		
		ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>(this.TrouverVoiture(v));
		vehicules=this.Filtrer(v,vmax, loc, vehicules);
		
		return vehicules;

	}
	
		public ArrayList<Vehicule> TrouverVehicule(Bus b,Bus bmax,Location loc){
		
			ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>(this.TrouverBus(b));
			vehicules=this.Filtrer(b,bmax, loc, vehicules);

				return vehicules;

		}

		public ArrayList<Vehicule> TrouverVehicule(Moto m,Moto mmax,Location loc){
	
			ArrayList<Vehicule> vehicules =new ArrayList<Vehicule>(this.TrouverMoto(m));
			vehicules=this.Filtrer(m,mmax, loc, vehicules);
			return vehicules;
		}
	
		public ArrayList<Location> locationClient(Client client){
			Connection c=null;
			ArrayList<Location> locations = new ArrayList<Location>();
			try {
				c=connexion.con();
				Statement mystat=c.createStatement();
				ResultSet res=mystat.executeQuery("select * from location where idClient ="+client.getIdClient()+";");
				while(res.next()) {
					Location l=new Location();
					l.setIdLocation(res.getString("idLocation"));
					l.setMatricule(res.getString("matricule"));
					l.setDateDebut(res.getDate("dateDebut"));
					l.setDateFin(res.getDate("dateFin"));
					l.setHeureDebut(res.getTime("heureDebut"));
					l.setHeureFin(res.getTime("heureFin"));
					l.setEtatLocation(res.getBoolean("etatLocation"));
					l.setHeure(res.getBoolean("heure"));
					locations.add(l);
				}
				res.close();
				mystat.close();
				c.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
				
			return locations;
		}
		
		public ArrayList<Location> location(){
			Connection c=null;
			ArrayList<Location> locations = new ArrayList<Location>();
			try {
				c=connexion.con();
				Statement mystat=c.createStatement();
				ResultSet res=mystat.executeQuery("select * from location ;");
				while(res.next()) {
					Location l=new Location();
					l.setIdLocation(res.getString("idLocation"));
					l.setMatricule(res.getString("matricule"));
					l.setDateDebut(res.getDate("dateDebut"));
					l.setDateFin(res.getDate("dateFin"));
					l.setHeureDebut(res.getTime("heureDebut"));
					l.setHeureFin(res.getTime("heureFin"));
					l.setEtatLocation(res.getBoolean("etatLocation"));
					l.setHeure(res.getBoolean("heure"));
					locations.add(l);
				}
				res.close();
				mystat.close();
				c.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
				
			return locations;
		}
	
		public ArrayList<Vehicule> vehiculeLouerClient(ArrayList<Location> l){
			ArrayList<Vehicule> vehicule = new ArrayList<Vehicule>();
			
			for(Location loc:l) {
				for(Vehicule veh:this.voiture()) {
					if(loc.getMatricule().equals(veh.getMatricule())) {
						vehicule.add(veh);
					}
				}

				for(Vehicule veh:this.bus()) {
					if(loc.getMatricule().equals(veh.getMatricule())) {
						vehicule.add(veh);
					}
				}
				for(Vehicule veh:this.moto()) {
					if(loc.getMatricule().equals(veh.getMatricule())) {
						vehicule.add(veh);
					}
				}
			}
			
			for(int i=0;i<vehicule.size()-1;i++) {
				for(int j=i+1;j<vehicule.size();j++) {
					if(vehicule.get(i).getMatricule().equals(vehicule.get(j).getMatricule())){
						vehicule.remove(j);
					}
				}
			}
			
			return vehicule;

		}
		
		
		
		public Boolean validiteDateLocation(Location l) {
			if(l.getDateDebut()!=null) {
				if(l.getDateDebut().before(this.Date_aujourdhui())) {
					return false;
				}
			}
			if(l.getDateFin()!=null) {
				if(l.getDateFin().before(this.Date_aujourdhui())) {
					return false;
				}
			}
			if((l.getDateDebut()!=null)&&(l.getDateFin()!=null)) {
				if(l.getDateFin().before(l.getDateDebut())) {
					return false;
				}
			}
			return true;
		}
		
	
		public Boolean legalAge(Client x) {
			long ajd=this.Date_aujourdhui().getTime();
			long c=x.getDateNaissance().getTime();
			long r=(ajd-c)/86400000;
			if(r<6579) {
				return false;
			}else {
				return true;

			}
		}

		public ArrayList<Voiture> voiture(){
			Connection c=null;
			ArrayList<Voiture> vehicule = new ArrayList<Voiture>();
			try {
				c=connexion.con();
				Statement mystat=c.createStatement();
				ResultSet res=mystat.executeQuery("select * from vehicule where type='voiture' ;");
				while(res.next()) {
					
						Voiture v=new Voiture();
						v.setMatricule(res.getString("matricule"));
						v.setMarque(res.getString("marque"));
						v.setModele(res.getString("modele"));
						v.setType(res.getString("type"));
						v.setCategorie(res.getString("categorie"));
						v.setTarifHeure(res.getFloat("tarifHeure"));
						v.setTarifJour(res.getFloat("tarifJour"));
						v.setEtat(res.getInt("etat"));
						v.setCodeDepot(res.getInt("codeDepot"));
						vehicule.add(v);
				}	
				res.close();
				mystat.close();
				c.close();
			
			}catch(SQLException e) {
				e.printStackTrace();

			}
			return vehicule;

		}
		
		
		
		
		public ArrayList<Bus> bus(){
			Connection c=null;
			ArrayList<Bus> vehicule = new ArrayList<Bus>();
			try {
				c=connexion.con();
				Statement mystat=c.createStatement();
				ResultSet res=mystat.executeQuery("select * from vehicule where type='bus' ;");
				while(res.next()) {
					
						Bus b=new Bus();
						b.setMatricule(res.getString("matricule"));
						b.setMarque(res.getString("marque"));
						b.setModele(res.getString("modele"));
						b.setType(res.getString("type"));
						b.setNombrePlace(res.getInt("nombrePlace"));
						b.setTarifHeure(res.getFloat("tarifHeure"));
						b.setTarifJour(res.getFloat("tarifJour"));
						b.setEtat(res.getInt("etat"));
						b.setCodeDepot(res.getInt("codeDepot"));
						vehicule.add(b);
				}	
				res.close();
				mystat.close();
				c.close();
			
			}catch(SQLException e) {
				e.printStackTrace();

			}
			return vehicule;

		}
		
		public ArrayList<Moto> moto(){
			Connection c=null;
			ArrayList<Moto> vehicule = new ArrayList<Moto>();
			try {
				c=connexion.con();
				Statement mystat=c.createStatement();
				ResultSet res=mystat.executeQuery("select * from vehicule where type='moto' ;");
				while(res.next()) {
					
						Moto m=new Moto();
						m.setMatricule(res.getString("matricule"));
						m.setMarque(res.getString("marque"));
						m.setModele(res.getString("modele"));
						m.setType(res.getString("type"));
						m.setCategorie(res.getString("categorie"));
						m.setTarifHeure(res.getFloat("tarifHeure"));
						m.setTarifJour(res.getFloat("tarifJour"));
						m.setEtat(res.getInt("etat"));
						m.setCodeDepot(res.getInt("codeDepot"));
						vehicule.add(m);
				}	
				res.close();
				mystat.close();
				c.close();
			
			}catch(SQLException e) {
				e.printStackTrace();

			}
			return vehicule;

		}	
		
		public Voiture getVoiture(Voiture v) {
			for(Voiture v1:this.voiture()) {
				if(v1.getMatricule()==v.getMatricule()) {
					return v1;
				}
			}
			return null;
		}
		public Bus getBus(Bus b) {
			for(Bus b1:this.bus()) {
				if(b1.getMatricule()==b.getMatricule()) {
					return b1;
				}
			}
			return null;
		}
		
		public Moto getMoto(Moto m) {
			for(Moto m1:this.moto()) {
				if(m1.getMatricule()==m.getMatricule()) {
					return m1;
				}
			}
			return null;
		}
		
		public Vehicule getVehicule(Vehicule v) {
			for(Vehicule v1:this.vehicule()) {
				if(v1.getMatricule().equals(v.getMatricule())) {
					return v1;
				}
			}
			return null;
		}
		public ArrayList<Vehicule> vehicule(){
			 ArrayList<Vehicule> vehicule=new ArrayList<Vehicule>();
			
			 for(Voiture v:this.voiture()) {
				 vehicule.add(v);
			 }
			 for(Bus b:this.bus()) {
				 vehicule.add(b);
			 }
			 for(Moto m:this.moto()) {
				 vehicule.add(m);
			 }
			 return vehicule;
		}
	
		
		public Boolean louer(Location l) {
			int etat=1;
			Vehicule v=new Voiture();
			v.setMatricule(l.getMatricule());
			v=this.getVehicule(v);
			if(v.getEtat()==2) {
			etat =2;	
			}
			Connection c=null;
			Boolean a=false;
			int t=0;
			try {
			c = connexion.con();
			Statement mystat=c.createStatement();
				if(l.isHeure()==false) {
			int myres=mystat.executeUpdate("insert into Location (idLocation,matricule,idClient,dateDebut,dateFin) values ('"+l.getIdLocation()+"','"+l.getMatricule()+"',"+l.getIdClient()+",'"+l.getDateDebut()+"','"+l.getDateFin()+"');");
			int myres2=mystat.executeUpdate("update vehicule set etat="+ etat + " where matricule='"+l.getMatricule()+"';");
			t=myres+myres2;
				}else {
				int myres=mystat.executeUpdate("insert into Location (idLocation,matricule,idClient,dateDebut,dateFin,heureDebut,heureFin,heure) values ('"+l.getIdLocation()+"','"+l.getMatricule()+"',"+l.getIdClient()+",'"+l.getDateDebut()+"','"+l.getDateFin()+"','"+l.getHeureDebut()+"','"+l.getHeureFin()+"',"+l.isHeure()+");");
				int myres2=mystat.executeUpdate("update vehicule set etat="+ etat + " where matricule='"+l.getMatricule()+"';");
				t=myres+myres2;
				}
			if(t ==2){
				a= true;
			}else {
				a= false;
			}

			
			c.close();
			mystat.close();
			
			
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return a;
		}
		
	public boolean disponibiliteLocation(Location l) {
		for(Location loc:this.location()) {
			if(loc.getMatricule().equals(l.getMatricule())) {
				if(l.isHeure()) {
					if(loc.isHeure()) {
						if( (loc.getDateDebut().equals(l.getDateDebut()))  ) {
							if( ((l.getHeureDebut().equals(loc.getHeureDebut())) || (l.getHeureDebut().after(loc.getHeureDebut()))) && ( (l.getHeureDebut().equals(loc.getHeureFin()) ) || (l.getHeureDebut().before(loc.getHeureFin())) ) ) {
								return false;
							}else {
								if ( ( (l.getHeureFin().equals(loc.getHeureDebut())) || (l.getHeureFin().after(loc.getHeureDebut())) ) && ( (l.getHeureFin().equals(loc.getHeureFin())) || (l.getHeureFin().before(loc.getHeureFin())) ) ) {
									return false;
								}
							}
						}
					}else {
						if( ((l.getDateDebut().equals(loc.getDateDebut())) || (l.getDateDebut().after(loc.getDateDebut()))) && ( (l.getDateDebut().equals(loc.getDateFin())) || (l.getDateDebut().before(loc.getDateFin())) )   ) {
							return false;
						}
					}
				}else {
				if( ( (l.getDateDebut().equals(loc.getDateDebut())) || (l.getDateDebut().after(loc.getDateDebut())) ) && ( (l.getDateDebut().equals(loc.getDateFin())) || (l.getDateDebut().before(loc.getDateFin()))) 	) {
					return false;
				}else {
					if( ( (l.getDateFin().equals(loc.getDateDebut())) || (l.getDateFin().after(loc.getDateDebut()))) && ( (l.getDateFin().equals(loc.getDateFin())) || (l.getDateFin().before(loc.getDateFin()))))  {
						return false;
					}
				}
			}
			}
		}
				return true;
	}
	
	public boolean existeLocation(Location l) {
		for(Location loc:this.location()) {
			if(loc.getIdLocation().equals(l.getIdLocation())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean verifierAnnulationLocation(Location l) {
		Client c=new Client();
		c.setIdClient(l.getIdClient());
		for(Location loc:this.locationClient(c)) {
			if(loc.getIdLocation().equals(l.getIdLocation())) {
				if(loc.isEtatLocation()==false) {
					if(loc.getDateDebut().after(this.Date_aujourdhui())) {
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
			}
		}
		return false;
	}
	
	public boolean annulerLocation(Location l) {
		Connection c=null;
		boolean a=false;
		try {
		c = connexion.con();
		Statement mystat=c.createStatement();
		int myres=mystat.executeUpdate("delete from location where idLocation='"+l.getIdLocation()+"';");
		if(myres!=0) {
			a=true;
		}
		c.close();
		mystat.close();
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	public ArrayList<Depot> Depot(){
		Connection c=null;
		ArrayList<Depot> depot = new ArrayList<Depot>();
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet res=mystat.executeQuery("select * from depot ;");
			while(res.next()) {
				Depot d=new Depot();
				d.setCode(res.getInt("code"));
				d.setAdresse(res.getString("adresse"));
				d.setCapacite(res.getInt("capacite"));
				d.setNombreVehicule(res.getInt("nombreVehicule"));
				d.setRegion(res.getString("region"));
			depot.add(d);
			}
		}catch(SQLException e) {
			e.printStackTrace();

		}
		return depot;
	}
	
	
	public int getPeriode(Location l) {
		int p=0;
		if(l.isHeure()) {
				long a=l.getHeureFin().getTime()-l.getHeureDebut().getTime();
				int aa=(int)a/60000;
				long aaa=aa*60;
				if ((aaa==aa)){
					p=(int)a/3600000;

				}else {
					p=(int)(a/3600000)+1;

				}

						
		}else {
			long a=l.getDateFin().getTime()-l.getDateDebut().getTime();
			p=(int) (a/86400000);
		}
		return p;
	}
	
	public boolean periodeLocation(Location l) {
		if(l.getDateDebut().before(this.Date_aujourdhui())) {
			return false;
		}else {
			if( (l.getHeureDebut().equals(l.getHeureFin())) || (l.getHeureFin().before(l.getHeureDebut()))  ){
				return false;
			}
		}

		return true;
	}
	public ArrayList<Vehicule> libreParDepot(Depot d) {
		ArrayList<Vehicule> res=new ArrayList<Vehicule>();
		for(Vehicule v:this.vehicule()) {
			if(v.getCodeDepot()==d.getCode()) {
				if(v.getEtat()==0) {
				res.add(v);
				}
			}
		}
		return res;
	}

	
}
