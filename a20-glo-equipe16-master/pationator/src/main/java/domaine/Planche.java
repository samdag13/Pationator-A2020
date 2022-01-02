package domaine;

import java.awt.*;

public class Planche extends Piece{

    public Planche(TypeBois inTypeBois, double inLongueur, Emplacement inEmplacement, TypePiece inTypePiece, Orientation inOrientation,
                   boolean inTransparence, boolean inVisibilitee, Color inCouleurPiece){
        super(inTypeBois, inLongueur,inEmplacement,inTypePiece,inOrientation,
                PartieVersLeSol.FaceAuSol, inTransparence,inVisibilitee, inCouleurPiece);
    }

    @Override
    boolean Valider() {
        return false;
    }

    public void couperPlancheSurLaLongueur(double mesure){
        this.getTypeBois().setLargeurReelle(mesure);
    }
}
