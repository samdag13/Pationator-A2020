package domaine;

import java.awt.*;

public abstract class Piece {

    public enum TypePiece
    {
        Plis,Poteau,Planche,Solive;
    }

    public enum Orientation
    {
        HautBas,NordSud,EstOuest;
    }

    public enum PartieVersLeSol
    {
        FaceAuSol,CoteAuSol,NonApplicable;
    }


    private double longueur;
    private Emplacement emplacement;
    private PartieVersLeSol partieVersLeSol;
    private TypeBois typeBois;
    private TypePiece typePiece;
    private Orientation orientation;
    private boolean transparence;
    private boolean visibilitee;
    private Color couleurPiece;

    Piece(){
        typeBois = null;
        longueur = 0;
        emplacement = new Emplacement();
        typePiece = null;
        orientation = null;
        transparence=false;
        visibilitee=false;
        couleurPiece=Color.red;
    }

    Piece(TypeBois inTypeBois, double inLongueur, Emplacement inEmplacement,TypePiece inTypePiece,Orientation inOrientation,
          PartieVersLeSol inPartieVersLeSol, boolean inTransparence, boolean inVisibilitee, Color inCouleurPiece){
        typeBois = inTypeBois;
        longueur=inLongueur;
        emplacement=inEmplacement;
        typePiece=inTypePiece;
        orientation=inOrientation;
        transparence = inTransparence;
        visibilitee = inVisibilitee;
        partieVersLeSol = inPartieVersLeSol;
        couleurPiece = inCouleurPiece;

    }
    abstract boolean Valider();

    public Emplacement getEmplacement(){
        return emplacement;
    }

    public void setEmplacement(double inX, double inY, double inZ){
        emplacement.setX(inX);
        emplacement.setY(inY);
        emplacement.setZ(inZ);
    }

    public TypePiece getTypePiece(){
        return typePiece;
    }

    public double getLongueur(){
        return longueur;
    }

    public boolean getTransparence() {
        return transparence;
    }

    public boolean getvisiblitee(){
        return visibilitee;
    }

    public Color getColor(){
        return couleurPiece;
    }

    public void setColor(Color inCouleurPiece){
       couleurPiece = inCouleurPiece;
    }

    public void setLongueur(double inLongueur){
        longueur = inLongueur;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation inOrientation){
        orientation = inOrientation;
    }
    public TypeBois getTypeBois(){
        return typeBois;
    }
}
