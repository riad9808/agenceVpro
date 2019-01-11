package beans;

import java.sql.Time;
import java.util.Date;

public class AvisFinLocation {
    private String idAvis;
    private String idLocation;
    private Date dateRetour;
    private Time heureRetour;
    private int etatRetour;
    private String description;
    
    public String getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(String idAvis) {
        this.idAvis = idAvis;
    }

  

    public int getEtatRetour() {
		return etatRetour;
	}

	public void setEtatRetour(int etatRetour) {
		this.etatRetour = etatRetour;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Time getHeureRetour() {
        return heureRetour;
    }

    public void setHeureRetour(Time heureRetour) {
        this.heureRetour = heureRetour;
    }

	public String getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(String idLocation) {
		this.idLocation = idLocation;
	}
    
}
