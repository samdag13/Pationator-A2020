package Afficheur;

import java.awt.Point;

public class Conversion_PixelPouceMetre {

    private double tauxConversion = 39.37;
    private double tauxZoom;
    private double valeurEnMetre;
    private double valeurEnPouce;


    public double convertirPouceEnMetre(int valeurEnPouce)
    {
        valeurEnMetre = this.valeurEnPouce / tauxConversion;
        return valeurEnMetre;
    }
        public double convertirMetreEnPouce(int valeurEnMetre)
    {
        valeurEnPouce = this.valeurEnMetre * tauxConversion;
        return valeurEnPouce;
    }

}
