package Afficheur;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


import domaine.PatioControleur;
import domaine.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class AffichagePatio {


    private final PatioControleur controller;
    private  static int nbrPlanExporter = 0 ;


    public double conversionDimensionsY(Piece piece, Vues2d.TypeVues2d vue2dActive) {
        double ValeurDimensionY;

        if(controller.getVueActivePatio() == Vues2d.TypeVues2d.vuePlan) {
            double[] valeurDimension = vue2dActive.vuePlan(piece);
            ValeurDimensionY = valeurDimension[0];
            return ValeurDimensionY;
        }
        else if(controller.getVueActivePatio() == Vues2d.TypeVues2d.vueCote) {
            double[] valeurDimension =  vue2dActive.vueCote(piece);
            ValeurDimensionY = valeurDimension[0];
            return ValeurDimensionY;
        }
        else {
            double[] valeurDimension = vue2dActive.vueFace(piece);
            ValeurDimensionY = valeurDimension[0];
            return ValeurDimensionY;
        }

    }
    public double conversionDimensionsX(Piece piece, Vues2d.TypeVues2d vue2dActive) {
        double ValeurDimensionX;

        if (controller.getVueActivePatio() == Vues2d.TypeVues2d.vuePlan) {
            double[] valeurDimension = vue2dActive.vuePlan(piece);
            ValeurDimensionX= valeurDimension[1];
            return ValeurDimensionX;

        } else if (controller.getVueActivePatio()  == Vues2d.TypeVues2d.vueCote) {
            double[] valeurDimension = vue2dActive.vueCote(piece);
           ValeurDimensionX = valeurDimension[1];
            return ValeurDimensionX;

        } else {
            double[] valeurDimension = vue2dActive.vueFace(piece);
            ValeurDimensionX = valeurDimension[1];
            return ValeurDimensionX;
        }
    }
    public AffichagePatio(PatioControleur controller)
    {
        this.controller = controller;
    }

    public void dessine(Graphics g, double coefficient, double decalageOrigineX, double decalageOrigineY)
    {
        dessinePiece(g, controller.getVueActivePatio(), coefficient, decalageOrigineX, decalageOrigineY);
    }
    private void dessinePiece(Graphics g, Vues2d.TypeVues2d vue2dActive, double coefficient, double decalageOrigineX,
                              double decalageOrigineY)
    {
        List<Piece> pieces = controller.getListPiece();
        for (Piece piece: pieces) {
            if (piece.getvisiblitee()) {
                if (controller.getVueActivePatio() == Vues2d.TypeVues2d.vuePlan) {
                    double PieceEmplacementX2D = (piece.getEmplacement().getX() * coefficient) + decalageOrigineX;
                    double PieceEmplacementY2D = (piece.getEmplacement().getY() * coefficient) + decalageOrigineY;

                    double valeurDimensionY = conversionDimensionsY(piece, vue2dActive) * coefficient; //Souvent la longueur
                    double valeurDimensionX = conversionDimensionsX(piece, vue2dActive) * coefficient;  //Épaisseur ou largeur
                    Graphics2D g2D = (Graphics2D) g;


                    if (!piece.getTransparence()) {
                        Color color = piece.getColor();
                        g.setColor(color);
                        AlphaComposite facteurTransparence = AlphaComposite.getInstance(
                                AlphaComposite.SRC_OVER, 1f);
                        g2D.setComposite(facteurTransparence);

                        Rectangle2D rect = new Rectangle2D.Double(PieceEmplacementX2D, PieceEmplacementY2D,
                                valeurDimensionX, valeurDimensionY );
                        g2D.fill(rect);
                        g.setColor(Color.BLACK);
                        g2D.draw(rect);
                    } else {
                        Color color = piece.getColor();
                        AlphaComposite facteurTransparence = AlphaComposite.getInstance(
                                AlphaComposite.SRC_OVER, 0.1f);
                        g2D.setComposite(facteurTransparence);
                        g2D.setColor(color);


                        Rectangle2D rect = new Rectangle2D.Double(PieceEmplacementX2D, PieceEmplacementY2D,
                                valeurDimensionX , valeurDimensionY  );
                        g2D.fill(rect);
                        g.setColor(Color.BLACK);
                        g2D.draw(rect);
                    }
                } else if (controller.getVueActivePatio() == Vues2d.TypeVues2d.vueFace) {
                    double PieceEmplacementX2D = (piece.getEmplacement().getY() * coefficient)+decalageOrigineX;
                    double PieceEmplacementY2D = (piece.getEmplacement().getZ() * coefficient)+decalageOrigineY;

                    double valeurDimensionY = conversionDimensionsY(piece, vue2dActive) * coefficient; //Souvent la longueur
                    double valeurDimensionX = conversionDimensionsX(piece, vue2dActive) * coefficient;  //Épaisseur ou largeur
                    Graphics2D g2D = (Graphics2D) g;

                    if (!piece.getTransparence()) {
                        Color color = piece.getColor();
                        g.setColor(color);
                        AlphaComposite facteurTransparence = AlphaComposite.getInstance(
                                AlphaComposite.SRC_OVER, 1f);
                        g2D.setComposite(facteurTransparence);

                        Rectangle2D rect = new Rectangle2D.Double(PieceEmplacementX2D, PieceEmplacementY2D,
                                valeurDimensionX, valeurDimensionY );
                        g2D.fill(rect);
                        g.setColor(Color.BLACK);
                        g2D.draw(rect);
                    } else {
                        Color color = piece.getColor();
                        AlphaComposite facteurTransparence = AlphaComposite.getInstance(
                                AlphaComposite.SRC_OVER, 0.01f);
                        g2D.setComposite(facteurTransparence);
                        g2D.setColor(color);

                        Rectangle2D rect = new Rectangle2D.Double(PieceEmplacementX2D, PieceEmplacementY2D,
                                valeurDimensionX, valeurDimensionY );
                        g2D.fill(rect);
                        g.setColor(Color.BLACK);
                        g2D.draw(rect);
                    }
                } else if (controller.getVueActivePatio() == Vues2d.TypeVues2d.vueCote) {
                    double PieceEmplacementX2D = (piece.getEmplacement().getX() * coefficient) + decalageOrigineX;
                    double PieceEmplacementY2D = (piece.getEmplacement().getZ() * coefficient) + decalageOrigineY;

                    double valeurDimensionY = conversionDimensionsY(piece, vue2dActive) * coefficient; //Souvent la longueur
                    double valeurDimensionX = conversionDimensionsX(piece, vue2dActive) * coefficient;  //Épaisseur ou largeur
                    Graphics2D g2D = (Graphics2D) g;

                    if (!piece.getTransparence()) {
                        Color color = piece.getColor();
                        g.setColor(color);
                        AlphaComposite facteurTransparence = AlphaComposite.getInstance(
                                AlphaComposite.SRC_OVER, 1f);
                        g2D.setComposite(facteurTransparence);
                        Rectangle2D rect = new Rectangle2D.Double(PieceEmplacementX2D, PieceEmplacementY2D,
                                valeurDimensionX, valeurDimensionY );
                        g2D.fill(rect);
                        g.setColor(Color.BLACK);
                        g2D.draw(rect);
                    } else {
                        Color color = piece.getColor();
                        AlphaComposite facteurTransparence = AlphaComposite.getInstance(
                                AlphaComposite.SRC_OVER, 0.01f);
                        g2D.setComposite(facteurTransparence);
                        g2D.setColor(color);

                        Rectangle2D rect = new Rectangle2D.Double(PieceEmplacementX2D, PieceEmplacementY2D,
                                valeurDimensionX, valeurDimensionY );
                        g2D.fill(rect);
                        g.setColor(Color.BLACK);
                        g2D.draw(rect);
                    }
                    Color color = piece.getColor();
                    g.setColor(color);

                    Rectangle2D rect = new Rectangle2D.Double(PieceEmplacementX2D, PieceEmplacementY2D,
                            valeurDimensionX, valeurDimensionY );
                    g2D.fill(rect);
                    g.setColor(Color.BLACK);
                    g2D.draw(rect);
                }
            }
        }
    }

    public void exportation(double coefficient, double decalageOrigineX, double decalageOrigineY) throws IOException {

        String vueActive;

        if(controller.getVueActivePatio() == Vues2d.TypeVues2d.vueCote){
            vueActive = "VueCote";
        }
        else if(controller.getVueActivePatio() == Vues2d.TypeVues2d.vueFace){
            vueActive = "VueFace";
        }
        else{
            vueActive = "VuePlan";
        }
        final BufferedImage plan = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = plan.createGraphics();
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, 1000, 1000);
        dessine(graphics2D, coefficient, decalageOrigineX, decalageOrigineY);
        graphics2D.dispose();
        File image = new File("Plan" + vueActive +  nbrPlanExporter +".jpg");
        ImageIO.write(plan, "jpeg", image);

        JFileChooser enregistrer = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        enregistrer.setDialogTitle("Choisissez où enregistrer votre .jpg");
        enregistrer.setApproveButtonText("Enregistrer");
        enregistrer.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        //ouvre la fenetre de sauvegarde et renomme le fichier s'il existe déjà
        int retourEnregistrer = enregistrer.showOpenDialog(null);
        if (retourEnregistrer == JFileChooser.APPROVE_OPTION){
            if( enregistrer.getSelectedFile().isDirectory()){
                File nouvelleDestination = new File(enregistrer.getSelectedFile().toString() + "\\" + image.getName());

                while(nouvelleDestination.exists()){
                    nbrPlanExporter++;
                    image = new File("Plan" + vueActive + nbrPlanExporter+".jpg");
                    ImageIO.write(plan, "jpeg", image);
                    nouvelleDestination = new File(enregistrer.getSelectedFile().toString() + "\\" + image.getName());
                }
                image.renameTo(nouvelleDestination);
            }

        }
        nbrPlanExporter++;

    }

}

