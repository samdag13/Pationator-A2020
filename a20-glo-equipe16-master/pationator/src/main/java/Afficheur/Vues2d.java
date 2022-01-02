package Afficheur;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import domaine.PatioControleur;
import domaine.Piece;
import domaine.TypeBois;

import javax.imageio.ImageIO;

public class Vues2d {

    public enum TypeVues2d {
        vuePlan, vueCote, vueFace;

        public double[] vuePlan(Piece piece) {
            double DimensionY;
            double DimensionX;
            if (piece.getTypePiece() == Piece.TypePiece.Planche) {
                DimensionY = piece.getLongueur();
                DimensionX = piece.getTypeBois().getLargeurReelle();
                return new double[]{DimensionY, DimensionX};
            } else if (piece.getTypePiece() == Piece.TypePiece.Solive & piece.getOrientation() == Piece.Orientation.EstOuest) {
                DimensionX = piece.getLongueur();
                DimensionY = piece.getTypeBois().getEpaisseurReelle();
                return new double[]{DimensionY, DimensionX};
            } else if(piece.getTypePiece() == Piece.TypePiece.Solive & piece.getOrientation() == Piece.Orientation.NordSud){
                DimensionX = piece.getTypeBois().getEpaisseurReelle();
                DimensionY = piece.getLongueur();
                return new double[]{DimensionY, DimensionX};
            } else if (piece.getTypePiece() == Piece.TypePiece.Poteau) {
                DimensionY = piece.getTypeBois().getEpaisseurReelle();
                DimensionX = piece.getTypeBois().getLargeurReelle();
                return new double[]{DimensionY, DimensionX};
            } else {//Poutre
                DimensionY = piece.getLongueur();
                DimensionX = piece.getTypeBois().getEpaisseurReelle();
                return new double[]{DimensionY, DimensionX};
            }

        }

        public double[] vueCote(Piece piece) {
            double DimensionY;
            double DimensionX;
            if (piece.getTypePiece() == Piece.TypePiece.Planche) {
                DimensionY = piece.getTypeBois().getEpaisseurReelle();
                DimensionX = piece.getTypeBois().getLargeurReelle();
                return new double[]{DimensionY, DimensionX};
            } else if (piece.getTypePiece() == Piece.TypePiece.Solive & piece.getOrientation() == Piece.Orientation.EstOuest) {
                DimensionX = piece.getLongueur();
                DimensionY = piece.getTypeBois().getLargeurReelle();
                return new double[]{DimensionY, DimensionX};
            }else if(piece.getTypePiece() == Piece.TypePiece.Solive & piece.getOrientation() == Piece.Orientation.NordSud){
                DimensionX = piece.getTypeBois().getEpaisseurReelle();
                DimensionY = piece.getTypeBois().getLargeurReelle();
                return new double[]{DimensionY, DimensionX};
            }
            else if (piece.getTypePiece() == Piece.TypePiece.Poteau) {
                DimensionY = piece.getLongueur();
                DimensionX = piece.getTypeBois().getLargeurReelle();
                return new double[]{DimensionY, DimensionX};
            } else {//Poutre
                DimensionX = piece.getTypeBois().getEpaisseurReelle();
                DimensionY = piece.getTypeBois().getLargeurReelle();
                return new double[]{DimensionY, DimensionX};
            }

        }

        public double[] vueFace(Piece piece) {
            double DimensionY;
            double DimensionX;
            if (piece.getTypePiece() == Piece.TypePiece.Planche) {
                DimensionY = piece.getTypeBois().getEpaisseurReelle();
                DimensionX = piece.getLongueur();
                return new double[]{DimensionY, DimensionX};
            } else if (piece.getTypePiece() == Piece.TypePiece.Solive & piece.getOrientation() == Piece.Orientation.EstOuest) {
                DimensionY = piece.getTypeBois().getLargeurReelle();
                DimensionX = piece.getTypeBois().getEpaisseurReelle();
                return new double[]{DimensionY, DimensionX};
            } else if(piece.getTypePiece() == Piece.TypePiece.Solive & piece.getOrientation() == Piece.Orientation.NordSud){
                DimensionY = piece.getTypeBois().getLargeurReelle();
                DimensionX = piece.getLongueur();
                return new double[]{DimensionY, DimensionX};
            }
            else if (piece.getTypePiece() == Piece.TypePiece.Poteau) {
                DimensionY = piece.getLongueur();
                DimensionX = piece.getTypeBois().getLargeurReelle();
                return new double[]{DimensionY, DimensionX};
            } else {//Poutre
                DimensionX = piece.getLongueur();
                DimensionY = piece.getTypeBois().getLargeurReelle();
                return new double[]{DimensionY, DimensionX};
            }

        }
    }


}