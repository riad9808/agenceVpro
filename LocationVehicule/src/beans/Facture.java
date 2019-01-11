package beans;


public class Facture {
    private String codeFacture;
    private float montant;
    private boolean factureDePenalite;
    private String idLocation;

    public String getCodeFacture() {
        return codeFacture;
    }

    public void setCodeFacture(String codeFacture) {
        this.codeFacture = codeFacture;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public boolean isFactureDePenalite() {
        return factureDePenalite;
    }

    public void setFactureDePenalite(boolean factureDePenalite) {
        this.factureDePenalite = factureDePenalite;
    }

	public String getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(String idLocation) {
		this.idLocation = idLocation;
	}


    
}
