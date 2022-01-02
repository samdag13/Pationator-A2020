package domaine;

import java.awt.*;

public class Poteau extends Piece{

    public Poteau(TypeBois inTypeBois, double inLongueur,Emplacement inEmplacement,TypePiece inTypePiece, Orientation inOrientation,boolean inTransparence, boolean inVisibilitee,
                  Color inCouleurPiece){
        super(inTypeBois, inLongueur,inEmplacement,inTypePiece,inOrientation,
                PartieVersLeSol.NonApplicable, inTransparence,inVisibilitee, inCouleurPiece);
    }

    @Override
    boolean Valider() {
        return false;
    }
}