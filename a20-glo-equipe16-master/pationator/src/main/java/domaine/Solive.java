package domaine;

import java.awt.*;

public class Solive extends Piece{

    public Solive(TypeBois inTypeBois, double inLongueur,Emplacement inEmplacement,TypePiece inTypePiece, Orientation inOrientation,
                 boolean inTransparence, boolean inVisibilitee, Color inCouleurPiece){
        super(inTypeBois, inLongueur,inEmplacement,inTypePiece,inOrientation, PartieVersLeSol.CoteAuSol, inTransparence,
                inVisibilitee,inCouleurPiece);
    }

    @Override
    boolean Valider() {
        return false;
    }
}
