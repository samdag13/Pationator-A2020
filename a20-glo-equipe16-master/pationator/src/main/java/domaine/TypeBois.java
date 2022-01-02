package domaine;

import java.io.Serializable;

public class TypeBois implements Serializable {

    public enum Format{
        DeuxParQuatre, DeuxParSix, DeuxParHuit, DeuxParDix, DeuxParDouze, SixParSix, QuatreParQuatre,
        CinqQuartParSix, PasUnType
    }
    private Format formatBois;
    private String nomBois;

    private double epaisseurNominale;
    private double largeurNominale;
    private double epaisseurReelle;
    private double largeurReelle;
    private double coutLineaire;
    private int ordreComparaison;

    public TypeBois() {
        this.formatBois = null;
        this.epaisseurNominale = 0;
        this.largeurNominale = 0;
        this.epaisseurReelle = 0;
        this.largeurReelle = 0;
        this.coutLineaire = 0;
    }

    public String getNomBois() {
        return nomBois;
    }

    public TypeBois(String nomBois, Format formatBois ,double epaisseurNominale, double largeurNominale, double epaisseurReelle,
                    double largeurReelle, double coutLineaire, int ordreComparaison) {
        this.nomBois = nomBois;
        this.formatBois = formatBois;
        this.epaisseurNominale = epaisseurNominale;
        this.largeurNominale = largeurNominale;
        this.epaisseurReelle = epaisseurReelle;
        this.largeurReelle = largeurReelle;
        this.coutLineaire = coutLineaire;
        this.ordreComparaison = ordreComparaison;
    }
    public void setLargeurReelle(double inLargeur){
        largeurReelle = inLargeur;
    }
    public Format getFormatBois() {
        return formatBois;
    }

    public int getOrdreComparaison() {
        return ordreComparaison;
    }

    public void setOrdreComparaison(int ordreComparaison) {
        this.ordreComparaison = ordreComparaison;
    }

    public double getEpaisseurNominale() {
        return epaisseurNominale;
    }

    public double getLargeurNominale() {
        return largeurNominale;
    }

    public double getEpaisseurReelle() {
        return epaisseurReelle;
    }

    public double getLargeurReelle() {
        return largeurReelle;
    }

    public double getCoutLineaire() {
        return coutLineaire;
    }

    public void setCoutLineaire(double coutLineaire) {
        this.coutLineaire = coutLineaire;
    }

    public boolean equal(TypeBois typeBois) {
        boolean resultat = false;
        if (this.formatBois == typeBois.getFormatBois()&
                this.getLargeurReelle() == typeBois.getLargeurReelle() &
                this.getEpaisseurReelle() == typeBois.getEpaisseurReelle() &
                this.getLargeurNominale() == typeBois.getLargeurNominale() &
                this.getEpaisseurNominale() == typeBois.getEpaisseurNominale())
        {
            resultat = true;
        }
        return resultat;
    }
}
