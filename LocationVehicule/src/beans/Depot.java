package beans;

public class Depot {
    private int code;
    private int capacite;
    private String adresse;
    private int nombreVehicule;
    private String region;

    public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNombreVehicule() {
        return nombreVehicule;
    }

    public void setNombreVehicule(int nombreVehicule) {
        this.nombreVehicule = nombreVehicule;
    }
}
