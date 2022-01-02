package gui;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import Afficheur.AffichagePatio;
import Afficheur.Vues2d;

public class PanelDessin extends JPanel implements Serializable{

    private App app;

    public double tauxZoom = 1;
    public double prevTauxZoom = 1;
    public boolean actionZoom;

    public boolean actionDrag;
    public boolean sourisRelachee;
    public double xDifference;
    public double yDifference;
    public Point startPoint;
    public double DecalageX = 0;
    public double DecalageY = 0;


    public PanelDessin(App app)
    {
        this.app = app;
    }
    // Les méthodes de zoom/dézoom et de drag sont inspirées de code trouvé sur stackoverflow
    // https://stackoverflow.com/questions/6543453/zooming-in-and-zooming-out-within-a-panel
    // avec cette méthode le zoom/dézoom affecte pas directement les dimensions des rectangles des pièces.
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        if (actionZoom == true) {
            AffineTransform modifZoom = new AffineTransform();

            double zoomDiv = tauxZoom / prevTauxZoom;
            double PositionX = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double PositionY = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            DecalageX = (zoomDiv) * (DecalageX) + (1 - zoomDiv) * PositionX;
            DecalageY = (zoomDiv) * (DecalageY) + (1 - zoomDiv) * PositionY;

            modifZoom.translate(DecalageX, DecalageY);
            modifZoom.scale(tauxZoom, tauxZoom);
            prevTauxZoom = tauxZoom;
            g2D.transform(modifZoom);
            actionZoom = false;
        }
        if (actionDrag) {
            AffineTransform modifDrag = new AffineTransform();
            modifDrag.translate(DecalageX + xDifference, DecalageY + yDifference);
            modifDrag.scale(tauxZoom, tauxZoom);
            g2D.transform(modifDrag);

            if (sourisRelachee) {
                DecalageX += xDifference;
                DecalageY += yDifference;
                actionDrag = false;
            }

        }
            AffichagePatio mainDrawer = new AffichagePatio(app.controleur);
            mainDrawer.dessine(g2D, 2.5, 50,150);
    }

}
