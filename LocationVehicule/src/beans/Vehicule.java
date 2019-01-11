package beans;


public abstract class Vehicule {
    protected String matricule;
    protected String marque;
    protected String modele;
    protected float tarifHeure;
    protected float tarifJour;
    protected int etat;
    protected int codeDepot;
    protected String type;
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public float getTarifHeure() {
        return tarifHeure;
    }

    public void setTarifHeure(float tarifHeure) {
        this.tarifHeure = tarifHeure;
    }

    public float getTarifJour() {
        return tarifJour;
    }

    public void setTarifJour(float tarifJour) {
        this.tarifJour = tarifJour;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

	public int getCodeDepot() {
		return codeDepot;
	}

	public void setCodeDepot(int codeDepot) {
		this.codeDepot = codeDepot;
	}
    
}
