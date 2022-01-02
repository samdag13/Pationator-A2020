package domaine;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Gabarit implements Serializable {



    //private double espacementPoteaux ou private double largeurPatio -> est la même chose que la longueurPoutres
    private double largeurPatio = 72;
    private double LongueurSolives = 119.25;
    private double LongueurSolivesPAF= 0.00;
    private double coutPatio = 0;
    private double coutPatioAvecPoteaux = 0;
    private double hauteurPatio = 80;// hauteurPatio = hateurPoteau + largeurTypePlis, + largeurTypeSolive, + épaisseurTypePlanche
    private double longueurPatio = 235.5; // longueurPatio = (longueurSolive * nbrPortée) - ((nbrPortée-1)*(nbrPLis*EpaisseurPlis))
    private double espacementPlanches = 2;
    private double espacementSolives = 12;
    private double longueurPoteaux = 67.5;
    private double longueurPAFaux = 0;
    private double longueurPoutre = 72;

    String msgErreur = " Test ";
    private int nbrPortee = 2;
    private int nbrPoutres = 3;
    private int nbrSolives = 6;
    private int nbrSolivesPAF = 6;
    private int nbrPlis = 2;
    private int nbrPoutreLargeur = 1;
    private int nbrPlanches = 32;
    private int nbrPoteauxParPoutre = 3;

    private boolean transparenceSolives = false;
    private boolean visibiliteSolives = true;
    private boolean transparencePlanches = false;
    private boolean visibilitePlanches = true;
    private boolean visibilitePoutre = true;
    private boolean transparencePoutre = false;
    private boolean visibilitePoteaux = true;
    private boolean transparencePoteaux = false;
    private boolean validiteePatio;

    private Color colorPlanches = Color.GRAY;
    private Color colorSolives = Color.red;
    private Color colorPoutres= Color.YELLOW;
    private Color colorPoteaux = Color.BLUE;

    private double prixFormatCinqQuartParSix = 2.0;
    private double prixFormatSixParSix;
    private double prixFormatQuatreParQuatre = 1.0;
    private double prixFormatDeuxParDouze = 1.0;
    private double prixFormatDeuxParDix = 1.0;
    private double prixFormatDeuxParHuit = 1.0;
    private double prixFormatDeuxParSix = 1;
    private double prixFormatDeuxParQuatre = 1.0;

    private final TypeBois typeDeuxParQuatre = new TypeBois("2x4",TypeBois.Format.DeuxParQuatre, 2,4,
            1.5,3.5, prixFormatDeuxParQuatre, 1);
    private final TypeBois typeDeuxParSix = new TypeBois("2x6",TypeBois.Format.DeuxParSix, 2,6,
            1.5,5.5,prixFormatDeuxParSix, 2);
    private final TypeBois typeDeuxParHuit = new TypeBois("2x8",TypeBois.Format.DeuxParHuit, 2, 8,
            1.5, 7.25, prixFormatDeuxParHuit, 3);
    private final TypeBois typeDeuxParDix = new TypeBois("2x10",TypeBois.Format.DeuxParDix, 2,10,
            1.5,9.25,prixFormatDeuxParDix, 4);
    private final TypeBois typeDeuxParDouze = new TypeBois("2x12",TypeBois.Format.DeuxParDouze, 2,12,
            1.5,11.25, prixFormatDeuxParDouze, 5);
    private final TypeBois typeQuatreParQuatre = new TypeBois("4x4", TypeBois.Format.QuatreParQuatre, 4,
            4,3.5,3.5, prixFormatQuatreParQuatre,6);
    private final TypeBois typeSixParSix = new TypeBois("6x6",TypeBois.Format.SixParSix, 6,6,
            5.5,5.5,prixFormatSixParSix,7);
    private final TypeBois typeCinqQuartParSix = new TypeBois("5/4x6", TypeBois.Format.CinqQuartParSix, 1.25,
            6, 1, 5.5, prixFormatCinqQuartParSix,0);
    private final TypeBois typePlusGrandQueTous= new TypeBois("NATB", TypeBois.Format.PasUnType, 0,
            0, 0, 0, prixFormatCinqQuartParSix,8);
    private TypeBois typeSolives = typeDeuxParSix;
    private TypeBois typePlis = typeDeuxParHuit;
    private TypeBois typePoteaux = typeQuatreParQuatre;
    private TypeBois typePlanches = typeDeuxParSix;

    private double coutSolives;
    private double coutSolivesPAF;
    private double coutPoutres;
    private double coutPoteaux;
    private double coutPlanches;

    private ArrayList<TypeBois> catalogue = new ArrayList<>();

    public Gabarit(){

        //On initialise notre catalogue de type de bois
        catalogue.add(typeDeuxParQuatre);
        catalogue.add(typeDeuxParSix);
        catalogue.add(typeDeuxParHuit);
        catalogue.add(typeDeuxParDix);
        catalogue.add(typeDeuxParDouze);
        catalogue.add(typeSixParSix);
        catalogue.add(typeQuatreParQuatre);
        catalogue.add(typeCinqQuartParSix);
        catalogue.add(typePlusGrandQueTous);
    }


    public Gabarit copieGabarit() {
        Gabarit newGabarit = new Gabarit();
        newGabarit.setColorPlanches(getColorPlanches());
        newGabarit.setColorPoteaux(getColorPoteaux());
        newGabarit.setColorPoutres(getColorPoutres());
        newGabarit.setColorSolives(getColorSolives());

        newGabarit.setEspacementPlanches(getEspacementPlanches());
        newGabarit.setEspacementSolives(getEspacementSolives());

        newGabarit.setHauteurPatio(getHauteurPatio());
        newGabarit.setLargeurPatio(getLargeurPatio());
        newGabarit.setLongueurPAFaux(getLongueurPAFaux());
        newGabarit.setLongueurPatio(getLongueurPatio());

        newGabarit.setLongueurPoteaux(getLongueurPoteaux());
        newGabarit.setLongueurPoutre(getLongueurPoutre());
        newGabarit.setLongueurSolives(getLongueurSolives());

        newGabarit.setMsgErreur(getMsgErreur());

        newGabarit.setNbrPoteauxParPoutre(getNbrPoteauxParPoutre());
        newGabarit.setNbrPlanches(getNbrPlanches());
        newGabarit.setNbrPlis(getNbrPlis());
        newGabarit.setNbrPortee(getNbrPortee());
        newGabarit.setNbrPoutreLargeur(getNbrPoutreLargeur());
        newGabarit.setNbrSolives(getNbrSolives());

        newGabarit.setTransparencePlanches(getTransparencePlanche());
        newGabarit.setTransparencePoteaux(getTransparencePoteau());
        newGabarit.setTransparencePoutre(getTransparencePoutre());
        newGabarit.setTransparenceSolives(getTransparenceSolive());

        newGabarit.setTypePlanches(getTypePlanches());
        newGabarit.setTypePlis(getTypePlis());
        newGabarit.setTypePoteaux(getTypePoteaux());
        newGabarit.setTypeSolives(getTypeSolives());

        newGabarit.setValiditeePatio(getValiditeePatio());

        newGabarit.setVisibilitePlanches(getVisibilitePlanches());
        newGabarit.setVisibilitePoteaux(getVisibilitePoteaux());
        newGabarit.setVisibilitePoutre(getVisibilitePoutres());
        newGabarit.setVisibiliteSolives(getVisibiliteSolive());

        newGabarit.setCoutSolives(getCoutSolives());
        newGabarit.setCoutSolivesPAF(getCoutSolivesPAF());
        newGabarit.setCoutPoutres(getCoutPoutres());
        newGabarit.setCoutPoteaux(getCoutPoteaux());
        newGabarit.setCoutPlanches(getCoutPlanches());

        newGabarit.setPrixFormatCinqQuartParSix(getPrixFormatCinqQuartParSix());
        newGabarit.setPrixFormatSixParSix(getPrixFormatSixParSix());
        newGabarit.setPrixFormatQuatreParQuatre(getPrixFormatQuatreParQuatre());
        newGabarit.setPrixFormatDeuxParDouze(getPrixFormatDeuxParDouze());
        newGabarit.setPrixFormatDeuxParDix(getPrixFormatDeuxParDix());
        newGabarit.setPrixFormatDeuxParHuit(getPrixFormatDeuxParHuit());
        newGabarit.setPrixFormatDeuxParSix(getPrixFormatDeuxParSix());
        newGabarit.setPrixFormatDeuxParQuatre(getPrixFormatDeuxParQuatre());

        return newGabarit;
    }

    public boolean equals(Gabarit g){
        if(getColorPlanches() != g.getColorPlanches()){
            return false;
        }
        else if(getColorPoteaux() != g.getColorPoteaux()){
            return false;
        }
        else if(getColorPoutres() != g.getColorPoutres()){
            return false;
        }
        else if(getColorSolives() != g.getColorSolives()){
            return false;
        }
        else if(getEspacementPlanches() != g.getEspacementPlanches()){
            return false;
        }
        else if(getEspacementSolives() != g.getEspacementSolives()){
            return false;
        }
        else if(getHauteurPatio() != g.getHauteurPatio()){
            return false;
        }
        else if(getLargeurPatio() != g.getLargeurPatio()){
            return false;
        }
        else if(getLongueurPAFaux() != g.getLongueurPAFaux()){
            return  false;
        }
        else if(getLongueurPatio() != g.getLongueurPatio()){
            return false;
        }
        else if(getLongueurPoteaux() != g.getLongueurPoteaux()){
            return false;
        }
        else if(getLongueurPoutre() != g.getLongueurPoutre()){
            return false;
        }
        else if(getLongueurSolives() != g.getLongueurSolives()){
            return  false;
        }
        else if(!getMsgErreur().equals(g.getMsgErreur())){
            return false;
        }
        else if(getNbrPoteauxParPoutre() != g.getNbrPoteauxParPoutre()){
            return  false;
        }
        else if(getNbrPlanches() != g.getNbrPlanches()){
            return false;
        }
        else if(getNbrPlis() != g.getNbrPlis()){
            return  false;
        }
        else if(getNbrPortee() != g.getNbrPortee()){
            return false;
        }
        else if(getNbrPoutreLargeur() != g.getNbrPoutreLargeur()){
            return false;
        }
        else if(getTransparencePlanche() != g.getTransparencePlanche()){
            return false;
        }
        else if(getTransparencePoteau() != g.getTransparencePoteau()){
            return false;
        }
        else if(getTransparencePoutre() != g.getTransparencePoutre()){
            return false;
        }
        else if(getTransparenceSolive() != g.getTransparenceSolive()){
            return false;
        }
        else if(!getTypePlanches().equal(g.getTypePlanches())){
            return false;
        }
        else if(!getTypePlis().equal(g.getTypePlis())){
            return false;
        }
        else if(!getTypePoteaux().equal(g.getTypePoteaux())){
            return false;
        }
        else if(!getTypeSolives().equal(g.getTypeSolives())){
            return false;
        }
        else if(getValiditeePatio() != g.getValiditeePatio()){
            return false;
        }
        else if(getVisibilitePlanches() != g.getVisibilitePlanches()){
            return false;
        }
        else if(getVisibilitePoteaux() != g.getVisibilitePoteaux()){
            return false;
        }
        else if(getVisibilitePoutres() != g.getVisibilitePoutres()){
            return false;
        }
        else if(getVisibiliteSolive() != g.getVisibiliteSolive()){
            return false;
        }
        return true;
    }

    public TypeBois getTypePlusGrandQueTous() {
        return typePlusGrandQueTous;
    }

    public boolean getTransparencePlanche(){
        return this.transparencePlanches;
    }

    public boolean getTransparencePoteau(){
        return this.transparencePoteaux;
    }

    public boolean getTransparencePoutre(){
        return this.transparencePoutre;
    }

    public boolean getTransparenceSolive(){
        return this.transparenceSolives;
    }

    public boolean getValiditeePatio(){
        return this.validiteePatio;
    }

    public boolean getVisibilitePlanches(){
        return this.visibilitePlanches;
    }

    public boolean getVisibilitePoteaux(){
        return this.visibilitePoteaux;
    }

    public boolean getVisibilitePoutres(){
        return this.visibilitePoutre;
    }

    public boolean getVisibiliteSolive(){
        return this.validiteePatio;
    }


    public String getMsgErreur() {
        return msgErreur;
    }

    public void setMsgErreur(String msgErreur) {
        this.msgErreur = msgErreur;
    }

    public boolean isValiditeePatio() {
        return validiteePatio;
    }

    public void setValiditeePatio(boolean validiteePatio) {
        this.validiteePatio = validiteePatio;
    }

    public double getLargeurPatio() {
        return largeurPatio;
    }

    public double getCoutPatio() {
        return coutPatio;
    }

    public TypeBois getTypeDeuxParQuatre() {
        return typeDeuxParQuatre;
    }

    public TypeBois getTypeDeuxParSix() {
        return typeDeuxParSix;
    }

    public TypeBois getTypeDeuxParHuit() {
        return typeDeuxParHuit;
    }

    public TypeBois getTypeDeuxParDix() {
        return typeDeuxParDix;
    }

    public TypeBois getTypeDeuxParDouze() {
        return typeDeuxParDouze;
    }

    public TypeBois getTypeQuatreParQuatre() {
        return typeQuatreParQuatre;
    }

    public TypeBois getTypeSixParSix() {
        return typeSixParSix;
    }

    public TypeBois getTypeCinqQuartParSix() {
        return typeCinqQuartParSix;
    }

    public ArrayList<TypeBois> getCatalogue() {
        return catalogue;
    }

    public boolean isTransparencePlanches() {
        return transparencePlanches;
    }

    public void setTransparencePlanches(boolean transparencePlanches) {
        this.transparencePlanches = transparencePlanches;
    }

    public boolean isVisibilitePlanches() {
        return visibilitePlanches;
    }

    public void setVisibilitePlanches(boolean visibilitePlanches) {
        this.visibilitePlanches = visibilitePlanches;
    }

    public TypeBois getTypePlanches() {
        return typePlanches;
    }

    public void setTypePlanches(TypeBois typePlanches) {
        this.typePlanches = typePlanches;
    }

    public double getLongueurPoutre() {
        return longueurPoutre;
    }

    public void setLongueurPoutre(double longueurPoutre) {
        this.longueurPoutre = longueurPoutre;
    }

    public double getLongueurPAFaux() {
        return longueurPAFaux;
    }

    public void setLongueurPAFaux(double longueurPAFaux) {
        this.longueurPAFaux = longueurPAFaux;
    }

    public TypeBois getTypeSolives() {
        return typeSolives;
    }

    public void setTypeSolives(TypeBois typeSolives) {
        this.typeSolives = typeSolives;
    }

    public boolean isTransparenceSolives() {
        return transparenceSolives;
    }

    public void setTransparenceSolives(boolean transparenceSolives) {
        this.transparenceSolives = transparenceSolives;
    }

    public boolean isVisibiliteSolives() {
        return visibiliteSolives;
    }

    public void setVisibiliteSolives(boolean visibiliteSolives) {
        this.visibiliteSolives = visibiliteSolives;
    }

    public int getNbrPoutres() { return nbrPoutres; }

    public void setNbrPoutres(int nbrPoutres) {
        this.nbrPoutres = nbrPoutres;
    }

    public int getNbrSolives() {
        return nbrSolives;
    }

    public void setNbrSolives(int nbrSolives) {
        this.nbrSolives = nbrSolives;
    }

    public double getLongueurSolives() {
        return LongueurSolives;
    }

    public void setLongueurSolives(double longueurSolives) {
        LongueurSolives = longueurSolives;
    }

    public double getLongueurPatio() {
        return longueurPatio;
    }

    public void setLongueurPatio(double longueurPatio) {
        this.longueurPatio = longueurPatio;
    }

    public double getHauteurPatio() { return hauteurPatio; }

    public void setHauteurPatio(double hauteurPatio) { this.hauteurPatio = hauteurPatio; }

    public double getEspacementPlanches() { return espacementPlanches; }

    public void setEspacementPlanches(double espacementPlanches) { this.espacementPlanches = espacementPlanches; }

    public double getEspacementSolives() { return espacementSolives; }

    public void setEspacementSolives(double espacementSolives) { this.espacementSolives = espacementSolives; }

    public void setLargeurPatio(double largeurPatio) {
        this.largeurPatio = largeurPatio;
    }

    public int getNbrPlis() { return nbrPlis; }

    public void setNbrPlis(int nbrPlis) { this.nbrPlis = nbrPlis; }

    public TypeBois getTypePlis(){ return typePlis; }

    public void setTypePlis(TypeBois typePlis){ this.typePlis = typePlis; }

    public TypeBois getTypePoteaux() { return typePoteaux; }

    public void setTypePoteaux(TypeBois typePoteaux) { this.typePoteaux = typePoteaux; }

    public double getLongueurPoteaux() { return longueurPoteaux; }

    public void setLongueurPoteaux(double longueurPoteaux) { this.longueurPoteaux = longueurPoteaux; }

    public boolean isVisibilitePoutre() { return visibilitePoutre; }

    public void setVisibilitePoutre(boolean visibilitePoutre) { this.visibilitePoutre = visibilitePoutre; }

    public boolean isTransparencePoutre() { return transparencePoutre; }

    public void setTransparencePoutre(boolean transparencePoutre) { this.transparencePoutre = transparencePoutre; }

    public boolean isVisibilitePoteaux() { return visibilitePoteaux; }

    public void setVisibilitePoteaux(boolean visibilitePoteaux) { this.visibilitePoteaux = visibilitePoteaux; }

    public boolean isTransparencePoteaux() { return transparencePoteaux; }

    public void setTransparencePoteaux(boolean transparencePoteaux) { this.transparencePoteaux = transparencePoteaux; }

    public Color getColorPoteaux() {
        return colorPoteaux;
    }

    public Color getColorPoutres() {
        return colorPoutres;
    }

    public Color getColorSolives() {
        return colorSolives;
    }

    public Color getColorPlanches() {
        return colorPlanches;
    }

    public void setColorPoteaux(Color colorPoteaux) {
        this.colorPoteaux = colorPoteaux;
    }

    public void setColorPoutres(Color colorPoutres) {
        this.colorPoutres = colorPoutres;
    }

    public void setColorSolives(Color colorSolives) {
        this.colorSolives = colorSolives;
    }

    public void setColorPlanches(Color colorPlanches) {
        this.colorPlanches = colorPlanches;
    }

    public int getNbrPlanches() { return nbrPlanches;}

    public void setNbrPlanches(int nbrPlanches) { this.nbrPlanches = nbrPlanches; }

    public int getNbrPortee() { return nbrPortee; }

    public void setNbrPortee(int nbrPortee) {this.nbrPortee = nbrPortee; }

    public int getNbrPoutreLargeur() { return nbrPoutreLargeur; }

    public void setNbrPoutreLargeur(int nbrPoutreLargeur) { this.nbrPoutreLargeur = nbrPoutreLargeur; }

    public void setCoutPatio(double coutPatio) { this.coutPatio = coutPatio; }

    public double getPrixFormatCinqQuartParSix() { return prixFormatCinqQuartParSix; }

    public void setPrixFormatCinqQuartParSix(double prixFormatCinqQuartParSix) { this.prixFormatCinqQuartParSix = prixFormatCinqQuartParSix; }

    public double getPrixFormatSixParSix() { return prixFormatSixParSix; }

    public void setPrixFormatSixParSix(double prixFormatSixParSix) { this.prixFormatSixParSix = prixFormatSixParSix; }

    public double getPrixFormatQuatreParQuatre() { return prixFormatQuatreParQuatre; }

    public void setPrixFormatQuatreParQuatre(double prixFormatQuatreParQuatre) { this.prixFormatQuatreParQuatre = prixFormatQuatreParQuatre; }

    public double getPrixFormatDeuxParDouze() { return prixFormatDeuxParDouze; }

    public void setPrixFormatDeuxParDouze(double prixFormatDeuxParDouze) { this.prixFormatDeuxParDouze = prixFormatDeuxParDouze; }

    public double getPrixFormatDeuxParDix() { return prixFormatDeuxParDix; }

    public void setPrixFormatDeuxParDix(double prixFormatDeuxParDix) { this.prixFormatDeuxParDix = prixFormatDeuxParDix; }

    public double getPrixFormatDeuxParHuit() { return prixFormatDeuxParHuit; }

    public void setPrixFormatDeuxParHuit(double prixFormatDeuxParHuit) { this.prixFormatDeuxParHuit = prixFormatDeuxParHuit; }

    public double getPrixFormatDeuxParSix() { return prixFormatDeuxParSix; }

    public void setPrixFormatDeuxParSix(double prixFormatDeuxParSix) { this.prixFormatDeuxParSix = prixFormatDeuxParSix; }

    public double getPrixFormatDeuxParQuatre() { return prixFormatDeuxParQuatre; }

    public void setPrixFormatDeuxParQuatre(double prixFormatDeuxParQuatre) { this.prixFormatDeuxParQuatre = prixFormatDeuxParQuatre; }

    public double getCoutPatioAvecPoteaux() {
        return coutPatioAvecPoteaux;
    }

    public void setCoutPatioAvecPoteaux(double coutPatioAvecPoteaux) {
        this.coutPatioAvecPoteaux = coutPatioAvecPoteaux;
    }

    public int getNbrPoteauxParPoutre() { return nbrPoteauxParPoutre; }

    public void setNbrPoteauxParPoutre(int nbrPoteauxParPoutre) { this.nbrPoteauxParPoutre = nbrPoteauxParPoutre; }

    public double getCoutSolives() {
        return coutSolives;
    }

    public void setCoutSolives(double coutSolives) {
        this.coutSolives = coutSolives;
    }

    public double getCoutSolivesPAF() {
        return coutSolivesPAF;
    }

    public void setCoutSolivesPAF(double coutSolivesPAF) {
        this.coutSolivesPAF = coutSolivesPAF;
    }

    public double getCoutPoutres() {
        return coutPoutres;
    }

    public void setCoutPoutres(double coutPoutres) {
        this.coutPoutres = coutPoutres;
    }

    public double getCoutPoteaux() {
        return coutPoteaux;
    }

    public void setCoutPoteaux(double coutPoteaux) {
        this.coutPoteaux = coutPoteaux;
    }

    public double getCoutPlanches() {
        return coutPlanches;
    }

    public void setCoutPlanches(double coutPlanches) {
        this.coutPlanches = coutPlanches;
    }

    public int getNbrSolivesPAF() {
        return nbrSolivesPAF;
    }

    public void setNbrSolivesPAF(int nbrSolivesPAF) {
        this.nbrSolivesPAF = nbrSolivesPAF;
    }

    public double getLongueurSolivesPAF() {
        return LongueurSolivesPAF;
    }

    public void setLongueurSolivesPAF(double longueurSolivesPAF) {
        LongueurSolivesPAF = longueurSolivesPAF;
    }
}



