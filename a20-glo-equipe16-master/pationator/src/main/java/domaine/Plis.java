package domaine;

import java.awt.*;

public class Plis extends Piece{

    public Plis(TypeBois inTypeBois, double inLongueur,Emplacement inEmplacement,TypePiece inTypePiece, Orientation inOrientation,
                boolean inTransparence, boolean inVisibilitee, Color inCouleurPiece){
        super(inTypeBois, inLongueur,inEmplacement,inTypePiece,inOrientation, PartieVersLeSol.CoteAuSol, inTransparence,inVisibilitee,inCouleurPiece);
    }

    @Override
    boolean Valider() {
        return false;
    }
}
