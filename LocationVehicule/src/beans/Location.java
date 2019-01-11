package beans;

import java.sql.Date;
import java.sql.Time;


public class Location {
    private String idLocation;
    private Date dateDebut;
    private Date dateFin;
    private Time heureDebut;
    private Time heureFin;
    private boolean modePayement;
    private boolean etatLocation;
    private String matricule;
    private Integer idClient;
    private boolean heure;
    
    public boolean isHeure() {
		return heure;
	}

	public void setHeure(boolean heure) {
		this.heure = heure;
	}

	public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    public boolean isModePayement() {
        return modePayement;
    }

    public void setModePayement(boolean modePayement) {
        this.modePayement = modePayement;
    }

    public boolean isEtatLocation() {
        return etatLocation;
    }

    public void setEtatLocation(boolean etatLocation) {
        this.etatLocation = etatLocation;
    }

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}
    
}
