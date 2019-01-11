package traitement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.sun.org.apache.xpath.internal.operations.Bool;

import beans.AvisFinLocation;
import beans.Bus;
import beans.Client;
import beans.Depot;
import beans.Facture;
import beans.Gestionnaire;
import beans.Location;
import beans.Moto;
import beans.Vehicule;
import beans.Voiture;



public class EspaceGestionnaire {
	
	public ArrayList<Gestionnaire> listGestionnaire(){
		Connection c=null;
		ArrayList<Gestionnaire> g=new ArrayList<Gestionnaire>();
	try {
		c=connexion.con();
		Statement mystat=c.createStatement();
			ResultSet res=mystat.executeQuery("select * from gestionnaire ;");
		while(res.next()) {
			Gestionnaire x=new Gestionnaire();
			x.setUser(res.getString("user"));
			x.setPassword(res.getString("password"));
			x.setGrade(res.getInt("grade"));
			
			g.add(x);
		}
		res.close();
		mystat.close();
		c.close();
			
	}catch (SQLException e) {
			e.printStackTrace();
	}
		
		return g;
	}
	
	public Boolean ConnexionGestionnaire(Gestionnaire x) {
		for(Gestionnaire g:this.listGestionnaire()) {
			if(g.getUser().equals(x.getUser())) {
				if(g.getPassword().equals(x.getPassword())) {
					return true;
				}else {
					return false;
				}
			}
		}
		return false;
	}
	
	public Boolean existeGestionnaire(Gestionnaire x) {
		for(Gestionnaire g:this.listGestionnaire()) {
			if(g.getUser().equals(x.getUser())) {
				return true;
			}
		}
	return false;
	}
	
	public Gestionnaire profilGestionnaire(Gestionnaire x) {
		for(Gestionnaire g:this.listGestionnaire()) {
			if(g.getUser().equals(x.getUser())) {
				return g;
			}
		}
		return null;
	}
	
	public Facture RecetteJour(Location l) {
		Facture f=new Facture();
		Connection c=null;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet res=mystat.executeQuery("select * from facture f,location l where f.idLocation=l.idLocation and  l.dateDebut ='"+l.getDateDebut()+"';");
			while(res.next()) {
				f.setMontant(res.getFloat("montant"));
			}
			
			c.close();
			mystat.close();
			res.close();
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return f;
	}
	
	
	public Facture RecettePeriode(Location loc) {
		Connection c=null;
		Facture f=new Facture();
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet res=mystat.executeQuery("select sum(montant) as x from facture f,location l where l.dateDebut between '"+loc.getDateDebut()+"' and '"+loc.getDateFin() +"';");
			while(res.next()) {
				f.setMontant(res.getFloat("x"));
			}
			
			c.close();
			mystat.close();
			res.close();
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return f;
	}
	
	
	public String Grade(Gestionnaire g) {
		if(g.getGrade()==1) {
        	return "Gérant Principal";
        }else {
        if(g.getGrade()==2) {
        	return "Employé";
        }else {
        	return "Garagiste";
        }
        }
	}
	
	
	public void ajouterBus(Bus b) {
		Connection c=null;
		Depot d=new Depot();
		d.setCode(b.getCodeDepot());
		d=this.getDepot(d);
		try {
		c = connexion.con();
		Statement mystat=c.createStatement();
		int myres=mystat.executeUpdate("insert into vehicule (matricule,type,nombrePlace,marque,modele,tarifHeure,tarifJour,codeDepot) values ('"+b.getMatricule()+"','"+b.getType()+"',"+b.getNombrePlace()+",'"+b.getMarque()+"','"+b.getModele()+"',"+b.getTarifHeure()+","+b.getTarifJour()+","+b.getCodeDepot()+");");
		d.setNombreVehicule(d.getNombreVehicule()+1);
		int myres2=mystat.executeUpdate("update depot set nombreVehicule="+d.getNombreVehicule() +" where code="+b.getCodeDepot()+";");	
		c.close();
		mystat.close();
		
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void ajouterVoiture(Voiture v) {
		Connection c=null;
		Depot d=new Depot();
		d.setCode(v.getCodeDepot());
		d=this.getDepot(d);
		try {
		c = connexion.con();
		Statement mystat=c.createStatement();
		int myres=mystat.executeUpdate("insert into vehicule (matricule,type,categorie,marque,modele,tarifHeure,tarifJour,codeDepot) values ('"+v.getMatricule()+"','"+v.getType()+"','"+v.getCategorie()+"','"+v.getMarque()+"','"+v.getModele()+"',"+v.getTarifHeure()+","+v.getTarifJour()+","+v.getCodeDepot()+");");
		d.setNombreVehicule(d.getNombreVehicule()+1);
		int myres2=mystat.executeUpdate("update depot set nombreVehicule="+d.getNombreVehicule() +" where code="+v.getCodeDepot()+";");	
		c.close();
		mystat.close();
		
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void ajouterMoto (Moto m) {
		Connection c=null;
		Depot d=new Depot();
		d.setCode(m.getCodeDepot());
		d=this.getDepot(d);
		try {
		c = connexion.con();
		Statement mystat=c.createStatement();
		int myres=mystat.executeUpdate("insert into vehicule (matricule,type,categorie,marque,modele,tarifHeure,tarifJour,codeDepot) values ('"+m.getMatricule()+"','"+m.getType()+"','"+m.getCategorie()+"','"+m.getMarque()+"','"+m.getModele()+"',"+m.getTarifHeure()+","+m.getTarifJour()+","+m.getCodeDepot()+");");
		d.setNombreVehicule(d.getNombreVehicule()+1);
		int myres2=mystat.executeUpdate("update depot set nombreVehicule="+d.getNombreVehicule() +" where code="+m.getCodeDepot()+";");	
		c.close();
		mystat.close();
		
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Client> listeClient(){
		ArrayList<Client> listeClient=new ArrayList<Client>();
		Connection c=null;
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
				
				listeClient.add(x);
			}
			res.close();
			mystat.close();
			c.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listeClient;
	}
	
	public Boolean validiteDateLocation(Location l) {
		if(l.getDateDebut()!=null) {
			if(l.getDateDebut().after(this.Date_aujourdhui())) {
				return false;
			}
		}
		if(l.getDateFin()!=null) {
			if(l.getDateFin().after(this.Date_aujourdhui())) {
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
	
	
	public Date Date_aujourdhui() {
		LocalDate d;
		d=LocalDate.now();
		Date t=java.sql.Date.valueOf(d);
		return t;
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
				l.setIdClient(res.getInt("idClient"));
				l.setMatricule(res.getString("matricule"));
				l.setDateDebut(res.getDate("dateDebut"));
				l.setDateFin(res.getDate("dateFin"));
				l.setHeureDebut(res.getTime("heureDebut"));
				l.setHeureFin(res.getTime("heureFin"));
				l.setEtatLocation(res.getBoolean("etatLocation"));
				l.setHeure(res.getBoolean("heure"));
				l.setModePayement(res.getBoolean("modePayement"));
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
	
	
	public Boolean depotPlein(Depot d) {
		for(Depot dep:this.Depot()) {
			if(dep.getCode()==d.getCode()) {
				if(dep.getCapacite()==dep.getNombreVehicule()) {
					return true;
				}else {
					return false;
				}
			}
		}
		return true;
	}
	
	public Boolean existeDepot(Depot d) {
		for(Depot dep:this.Depot()) {
			if(dep.getCode()==d.getCode()) {
				return true;
			}
		}
		return false;
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
	
	public Boolean existematricule(Vehicule v) {
		for(Voiture veh:this.voiture()) {
			if(veh.getMatricule().equals(v.getMatricule())) {
				return true;
			}
		}
		for(Bus veh:this.bus()) {
			if(veh.getMatricule().equals(v.getMatricule())) {
				return true;
			}
		}
		for(Moto veh:this.moto()) {
			if(veh.getMatricule().equals(v.getMatricule())) {
				return true;
			}
		}
		return false;
	}
	
	public Depot getDepot(Depot d) {
		for(Depot dep:this.Depot()) {
			if(dep.getCode()==d.getCode()) {
				return dep;
			}
		}
		return null;
	}

	public Boolean DebloquerClient(Client cl) {
		Connection c=null;
		Boolean res=false;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			int myres=mystat.executeUpdate("update client set estBloque=0 where idClient="+cl.getIdClient()+";");
			if(myres!=0) {
				res=true;
			}
			
		}catch (SQLException e) {
		}
		return res;
	}
	
	public Boolean bloqueClient(Client x,Boolean definitive) {
		Connection c=null;
		Boolean res=false;
		try {
			c=connexion.con();
		Statement mystat=c.createStatement();
		if(definitive ==true) {
			int myres=mystat.executeUpdate("delete from client where idClient="+x.getIdClient()+";");
			if(myres!=0) {
				res=true;
			}
		}else {
			int myres1=mystat.executeUpdate("update client set estbloque=1 where idClient="+x.getIdClient()+";");
			if(myres1 !=0) {
				res=true;
			}
		}
		}catch (SQLException e) {

		}
		return res;
	}
	
	
	
	public Boolean existeClientBloque(Client c) {
		for(Client x:this.listeClient()) {
			if(x.getIdClient().equals(c.getIdClient())) {
				if(x.isEstBloque()==true) {
					return true;
				}
			}
		}
		return false;
	}
	
	public ArrayList<Location> etatLocationClient(Client client){
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
	
	public Client getClient(Client c) {
		for(Client cl:this.listeClient()) {
			if(cl.getIdClient().equals(c.getIdClient())) {
				return cl;
			}
		}
		return null;
	}
	
	public Boolean possibleModifierPrix(Vehicule v) {
		for(Vehicule vh:this.vehicule()) {
			if(vh.getMatricule().equals(v.getMatricule())) {
				if(vh.getEtat()==0) {
					return true;
				}else {
					return false;
				}
			}
		}
		return false;
	}
	
	public Boolean conditionPrixHeureJour(Vehicule v) {
		if( (v.getTarifHeure()*24) > v.getTarifJour()) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public Boolean modifierPrix(Vehicule v) {
		Connection c=null;
		Boolean res=false;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			int myres=mystat.executeUpdate("update vehicule set tarifHeure="+v.getTarifHeure()+"where matricule='"+v.getMatricule()+"';");
			int myres2=mystat.executeUpdate("update vehicule set tarifJour ="+v.getTarifJour()+"where matricule='"+v.getMatricule()+"';");
			
			if(myres!=0) {
				res=true;
			}
			
		}catch (SQLException e) {
		}
		return res;
	}
	
public ArrayList<Vehicule> vehiculeDepot(Depot d) {
	ArrayList<Vehicule> vehicules=new ArrayList<Vehicule>();
	for(Vehicule v:this.vehicule()) {
		if(v.getCodeDepot()==d.getCode()) {
			vehicules.add(v);
		}
	}
	return vehicules;
}
	
	public ArrayList<Location> etatLocationVehicule(Location l){
		Connection c=null;
		ArrayList<Location> locations = new ArrayList<Location>();
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet res=mystat.executeQuery("select * from location where matricule ='"+l.getMatricule()+"';");
			while(res.next()) {
				Location loc=new Location();
				l.setIdClient(res.getInt("idClient"));

				loc.setIdLocation(res.getString("idLocation"));
				loc.setIdClient(res.getInt("idClient"));
				loc.setMatricule(res.getString("matricule"));
				loc.setDateDebut(res.getDate("dateDebut"));
				loc.setDateFin(res.getDate("dateFin"));
				loc.setHeureDebut(res.getTime("heureDebut"));
				loc.setHeureFin(res.getTime("heureFin"));
				loc.setEtatLocation(res.getBoolean("etatLocation"));
				loc.setHeure(res.getBoolean("heure"));
				locations.add(loc);
			}
			res.close();
			mystat.close();
			c.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return locations;
	}
	
	public boolean appartenirDepot(Vehicule v) {
		for(Vehicule veh:this.vehicule()) {
			if(v.getMatricule().equals(veh.getMatricule())) {
				if(v.getCodeDepot()==veh.getCodeDepot()) {
					return true;
				}else {
					return false;
				}
			}
		}
		return false;
	}
		
	
	public int existeVehicule(Vehicule v) {
		for(Vehicule veh:this.vehicule()) {
			if(veh.getMatricule().equals(v.getMatricule())) {
				if(veh.getCodeDepot()==v.getCodeDepot()) {
					return veh.getEtat();

				}
			}
			
		}
		
		return 4;
	}
	
	public void rendreVehicule(Vehicule v) {
		int etat=0;
		if(this.existeLocationAVenir(v).size()!=0) {
			etat=1;
		}
		Connection c=null;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			int myres=mystat.executeUpdate("update vehicule set etat="+ etat +" where matricule='"+v.getMatricule()+"';");
			
		}catch (SQLException e) {
		}
		
		
	}
	
	public boolean rendreVehicule(AvisFinLocation a,Vehicule v) {
		Boolean res=false;

		if(this.existanceLocation(a, v)==0) {
			Connection c=null;
			try {
				String idAvis=a.getIdLocation()+"AFL";
				c=connexion.con();
				Statement mystat=c.createStatement();
				int myres=mystat.executeUpdate("insert into avisFinLocation(idAvis,idLocation,etatRetour,description,dateRetour,heurRetour) values('"+idAvis+"','"+a.getIdLocation()+"',"+a.getEtatRetour()+",'"+a.getDescription()+"','"+a.getDateRetour()+"','"+a.getHeureRetour()+"');"  );
				if(myres!=0) {
					res=true;
				}
				
			}catch (SQLException e) {
			}
		}
		return res;

	}

	public int existanceLocation(AvisFinLocation a,Vehicule v) {
		for(Location l:this.location()) {
			if(l.getIdLocation().equals(a.getIdLocation())) {
				if(l.isEtatLocation()) {
					if(l.getMatricule().equals(v.getMatricule())) {
						return 0;
					}else {
						return 1;
					}
				}else {
					return 2;
				}
			}
		}
		return 3;
	}
	
	public boolean apresReparation(Vehicule v) {
		int etat=0;
		if(this.existeLocationAVenir(v).size()!=0) {
			etat=1;
		}
		Connection c=null;
		Boolean res=false;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			int myres=mystat.executeUpdate("update vehicule set etat="+ etat +" where matricule='"+v.getMatricule()+"';");

			if(myres!=0) {
				res=true;
			}
			
		}catch (SQLException e) {
		}
		return res;
		
	}
	
	public ArrayList<Location> existeLocationAVenir(Vehicule v) {
		ArrayList<Location> loc=new ArrayList<Location>();
		for(Location l:this.location()) {
			if(l.getMatricule().equals(v.getMatricule())) {
				if( (l.getDateDebut().equals(this.Date_aujourdhui())) || (l.getDateDebut().after(this.Date_aujourdhui())) ) {
					loc.add(l);
				}
			}
		}
		return loc;
	}
	
	public ArrayList<Location> annulerLocation(Location l) {
		ArrayList<Location> loc=new ArrayList<Location>();

		if(l.getIdClient()!=null) {
			for(Location location:this.location()) {
				if(location.getIdClient().equals(l.getIdClient())) {
					if(location.getDateDebut().after(this.Date_aujourdhui())) {
						loc.add(location);
					}
				
				}
			}
			
			
		}else {
		for(Location location:this.location()) {
			if(location.getMatricule().equals(l.getMatricule())) {
				if(location.getDateDebut().equals(l.getDateDebut()) || (location.getDateDebut().after(l.getDateDebut())) ) {
					if(l.isEtatLocation()==true) {
						loc.add(location);
						Client c=new Client();
						c.setIdClient(location.getIdClient());
						c=this.getClient(c);
						mail email=new mail();
						email.locationAnnuler(c, location);
					}else {
						if(location.getDateDebut().equals(l.getDateFin()) || (location.getDateDebut().before(l.getDateFin()))) {
							loc.add(location);
							Client c=new Client();
							c.setIdClient(location.getIdClient());
							c=this.getClient(c);
							mail email=new mail();
							email.locationAnnuler(c, location);
						}
					}
				}
				
			}
		}
		}
		if(loc.isEmpty()) {
			return loc;
		}else {
		for(Location loca:loc) {
			Connection c=null;
			
			try {
				c=connexion.con();
				Statement mystat=c.createStatement();
				int myres1=mystat.executeUpdate("delete from facture where idLocation='"+loca.getIdLocation()+"';");
				int myres=mystat.executeUpdate("delete from location where idLocation='"+loca.getIdLocation()+"';");
				c.close();
				mystat.close();
			}catch (SQLException e) {
			}
			
		}
		}
		
		return loc;
	}
	
	public boolean retirerVehicule(Vehicule v,String type) {
		int etat=2;
		if(type.equals("location")) {
			etat=2;
		}else {
			etat=3;
		}
		Connection c=null;
		Boolean res=false;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			int myres=mystat.executeUpdate("update vehicule set etat="+ etat +" where matricule='"+v.getMatricule()+"';");

			if(myres!=0) {
				res=true;
			}
			
		}catch (SQLException e) {
		}
		return res;
		
	}
	
	public boolean supprimerVehicule(Vehicule v) {
		Depot d=new Depot();
		d.setCode(v.getCodeDepot());
		d=this.getDepot(d);
		int nb=d.getNombreVehicule()-1;
		Connection c=null;
		Boolean res=false;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			int myres=mystat.executeUpdate("delete from vehicule where matricule='"+v.getMatricule()+"';");
			int myres1=mystat.executeUpdate("update depot set nombreVehicule="+nb+" where code ="+d.getCode()+";");
			if(myres!=0) {
				res=true;
			}
			
		}catch (SQLException e) {
		}
		return res;
	}
	
	public ArrayList<Location> listeLocation(Location l){
		ArrayList<Location> location=new ArrayList<Location>();
		
		Connection c=null;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet res;
			if(l.getDateDebut()!=null) {
				if(l.getHeureDebut()!=null) {
			 res=mystat.executeQuery("select * from location where idClient ="+l.getIdClient()+" and dateDebut ='"+l.getDateDebut()+"'and heureDebut ='"+l.getHeureDebut()+"' and etatLocation=0 ;");

				}else {
			 res=mystat.executeQuery("select * from location where idClient ="+l.getIdClient()+" and dateDebut ='"+l.getDateDebut()+"' and etatLocation=0;");

				}
			}else {
			res=mystat.executeQuery("select * from location where idClient ="+l.getIdClient()+" and etatLocation=0;");
			}
			while(res.next()) {
				Location loc=new Location();
				loc.setIdClient(res.getInt("idClient"));
				loc.setIdLocation(res.getString("idLocation"));
				loc.setMatricule(res.getString("matricule"));
				loc.setDateDebut(res.getDate("dateDebut"));
				loc.setDateFin(res.getDate("dateFin"));
				loc.setHeureDebut(res.getTime("heureDebut"));
				loc.setHeureFin(res.getTime("heureFin"));
				loc.setEtatLocation(res.getBoolean("etatLocation"));
				loc.setHeure(res.getBoolean("heure"));
				location.add(loc);
			}
			res.close();
			mystat.close();
			c.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return location;
	}
	
	public Location existeContrat(Location l) {
		for(Location loc:this.location()) {
			if(loc.getIdLocation().equals(l.getIdLocation())) {
				if(loc.isEtatLocation()==true) {
					return loc;
				}else {
					return null;
				}
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
	public float calculerMontantTotal(Float a) {
		float res=a;
		res=a+((a*25)/100);
		return res;
	}
	public float calculerMontantTotal(Location l) {
		float a=00;
		int p=0;
		Vehicule v=new Voiture();

		if(l.isEtatLocation()) {
			for (Location loc:this.location()){
				if(loc.getIdLocation().equals(l.getIdLocation())) {
					 p=this.getPeriode(loc);
						v.setMatricule(loc.getMatricule());
							if(loc.isHeure()) {
								 a=(this.getVehicule(v).getTarifHeure()*p);
								 a=a+((a*18)/100);
							}else {
								 a=(this.getVehicule(v).getTarifJour()*p);
								 a=a+((a*18)/100);
							}
			}
			}
		}else {
			 a=Float.valueOf(l.getMatricule());
			a=a+((a*25)/100);
		}
		return a;
	}
	
	public float calculerMontantBrut(Location l) {
		float a=00;
		int p=0;
		Vehicule v=new Voiture();

		
			for (Location loc:this.location()){
				if(loc.getIdLocation().equals(l.getIdLocation())) {
					 p=this.getPeriode(loc);
						v.setMatricule(loc.getMatricule());
							if(loc.isHeure()) {
								 a=(this.getVehicule(v).getTarifHeure()*p);
								
							}else {
								 a=(this.getVehicule(v).getTarifJour()*p);
								
							}
			}
			}
		
		return a;
	}
	
	public AvisFinLocation recupererAvis(Location l) {
		AvisFinLocation avis=new AvisFinLocation();

		Connection c=null;
		try {

			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet res;
		
			res=mystat.executeQuery("select * from avisfinlocation where idLocation ='"+l.getIdLocation()+"';");
			
			while(res.next()) {
			avis.setIdAvis(res.getString("idAvis"));
			avis.setIdLocation(res.getString("idLocation"));
			avis.setDateRetour(res.getDate("dateRetour"));
			avis.setHeureRetour(res.getTime("heurRetour"));
			avis.setEtatRetour(res.getInt("etatRetour"));
			avis.setDescription(res.getString("description"));
			}
			res.close();
			mystat.close();
			c.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return avis;
	}
	
	public Location recupererInfoLocation(Location l){
			for(Location loc:this.location()) {
				if(loc.getIdLocation().equals(l.getIdLocation())) {
			return loc;
				}
			}
		return null;
	}
	
	public String modalitePayement(Location l) {
		if(l.isModePayement()) {
			return "en liquide";
		}else {
			return "par chèque";
		}
	}
	
	public boolean etablirContrat(Location l) {
		boolean res=false;
		Connection c=null;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			int myres=mystat.executeUpdate("update location set etatLocation=1 where idLocation='"+l.getIdLocation()+"';");
			int myres1=mystat.executeUpdate("update location set modePayement="+l.isModePayement()+" where idLocation='"+l.getIdLocation()+"';");

			if(myres!=0) {
				res=true;
			}
			
		}catch (SQLException e) {
		}
		return res;
	}
	
	public int getPeriode(Location l) {
		int p=0;
		if(l.isHeure()) {
				long a=l.getHeureFin().getTime()-l.getHeureDebut().getTime();
				int aa=(int)a/60000;
				long aaa=aa*60;
				if (aaa==aa){
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
	public boolean etablirFacture(Facture f) {
		boolean res=false;
		Connection c=null;
		
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			int myres=mystat.executeUpdate("insert into facture values('"+f.getCodeFacture()+"','"+f.getIdLocation()+"',"+f.getMontant()+","+f.isFactureDePenalite()+");"  );
			if(myres!=0) {
				res=true;
			}
			
		}catch (SQLException e) {
		}
	
	return res;
	}
	
	public float recetteBus() {
		Float res=(float) 0;
		Connection c=null;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet myres=mystat.executeQuery("select sum(montant) as x from facture f,location l,vehicule v where v.type='bus' and v.matricule=l.matricule and f.idlocation=l.idlocation ;");
			while(myres.next()) {
				res=myres.getFloat("x");
			}
			
			c.close();
			mystat.close();
			myres.close();
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return res;
	}
	public float recetteMoto() {
		Float res=(float) 0;
		Connection c=null;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet myres=mystat.executeQuery("select sum(montant) as x from facture f,location l,vehicule v where v.type='moto' and v.matricule=l.matricule and f.idlocation=l.idlocation ;");
			while(myres.next()) {
				res=myres.getFloat("x");
			}
			
			c.close();
			mystat.close();
			myres.close();
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return res;
	}
	
	public float recetteVoiture() {
		Float res=(float) 0;
		Connection c=null;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet myres=mystat.executeQuery("select sum(montant) as x from facture f,location l,vehicule v where v.type='voiture' and v.matricule=l.matricule and f.idlocation=l.idlocation ;");
			while(myres.next()) {
				res=myres.getFloat("x");
			}
			
			c.close();
			mystat.close();
			myres.close();
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return res;
	}
	
	public int nombreLocationDepot(Depot d) {
		int res=0;
		Connection c=null;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet myres=mystat.executeQuery("select count(idLocation) as x from location l,vehicule v where v.codeDepot="+d.getCode()+" and v.matricule=l.matricule ;");
			while(myres.next()) {
				res=myres.getInt("x");
			}
			
			c.close();
			mystat.close();
			myres.close();
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return res;
	}
	
	public float recetteDepot(Depot d) {
		Float res=(float) 0;
		Connection c=null;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet myres=mystat.executeQuery("select sum(montant) as x from facture f,location l,vehicule v where v.codeDepot="+d.getCode()+" and v.matricule=l.matricule and f.idlocation=l.idlocation ;");
			while(myres.next()) {
				res=myres.getFloat("x");
			}
			
			c.close();
			mystat.close();
			myres.close();
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return res;
	}
	
	public float recetteVoitureDepot(Depot d) {
		Float res=(float) 0;
		Connection c=null;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet myres=mystat.executeQuery("select sum(montant) as x from facture f,location l,vehicule v where v.type='voiture' and codedepot="+d.getCode() +" and v.matricule=l.matricule and f.idlocation=l.idlocation ;");
			while(myres.next()) {
				res=myres.getFloat("x");
			}
			
			c.close();
			mystat.close();
			myres.close();
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return res;
	}

	public float recetteBusDepot(Depot d) {
		Float res=(float) 0;
		Connection c=null;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet myres=mystat.executeQuery("select sum(montant) as x from facture f,location l,vehicule v where v.type='bus' and codedepot="+d.getCode() +" and v.matricule=l.matricule and f.idlocation=l.idlocation ;");
			while(myres.next()) {
				res=myres.getFloat("x");
			}
			
			c.close();
			mystat.close();
			myres.close();
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return res;
	}
	
	
	
	public float recetteMotoDepot(Depot d) {
		Float res=(float) 0;
		Connection c=null;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet myres=mystat.executeQuery("select sum(montant) as x from facture f,location l,vehicule v where v.type='moto' and codedepot="+d.getCode() +" and v.matricule=l.matricule and f.idlocation=l.idlocation ;");
			while(myres.next()) {
				res=myres.getFloat("x");
			}
			
			c.close();
			mystat.close();
			myres.close();
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return res;
	}
	
	public int nombreClient() {
		int res=0;
		Connection c=null;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet myres=mystat.executeQuery("select count(idClient) as x from client ;");
			while(myres.next()) {
				res=myres.getInt("x");
			}
			
			c.close();
			mystat.close();
			myres.close();
		}catch (SQLException e) {
				e.printStackTrace();
		}
		
		return res;
	}
	public ArrayList<Facture> facture(){
		Connection c=null;
		ArrayList<Facture> fac=new ArrayList<Facture>();
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet res=mystat.executeQuery("select * from facture ;");
			while(res.next()) {
				
					Facture f = new Facture();
					f.setCodeFacture(res.getString("codeFacture"));
					f.setIdLocation(res.getString("idLocation"));
					f.setMontant(res.getFloat("montant"));
					f.setFactureDePenalite(res.getBoolean("factureDePenalite"));
					fac.add(f);
			}	
			res.close();
			mystat.close();
			c.close();
		
		}catch(SQLException e) {
			e.printStackTrace();

		}
		return fac;
	}
	
	
	public boolean existeFacture(Facture f) {
		for(Facture ff:this.facture()) {
			if(ff.getCodeFacture().equals(f.getCodeFacture())) {
				return true;
			}
		}
		return false;
	}
	
	public int nombreClientBloquer() {
		int res=0;
		for(Client c:this.listeClient()) {
			if(c.isEstBloque()) {
				res=res+1;
			}
		}
		return res;
	}
	
	public int nombreLocationToDay() {
		int res=0;
		Connection c=null;
		try {
			c=connexion.con();
			Statement mystat=c.createStatement();
			ResultSet myres=mystat.executeQuery("select count(idLocation) as x from location wherer dateDebut='"+this.Date_aujourdhui()+"';");
			while(myres.next()) {
				res=myres.getInt("x");
			}
			
			c.close();
			mystat.close();
			myres.close();
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return res;
	}
}
