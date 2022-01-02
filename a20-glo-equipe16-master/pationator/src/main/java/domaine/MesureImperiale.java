package domaine;

public class MesureImperiale {
    private int entier;
    private int numerateur = 1;
    private int denominateur = 1;

    public MesureImperiale() {
        entier = numerateur = denominateur = 0;
    }

    public MesureImperiale(int in_Entier, int in_Numerateur, int in_Denominateur) {
        entier = in_Entier;
        numerateur = in_Numerateur;
        denominateur = in_Denominateur;
    }
    public MesureImperiale(String fraction){
        final String separateurEntier = " ";
        final String separateurFraction = "/";

        String donneSepareeEntier[] = fraction.split(separateurEntier);
        if (donneSepareeEntier.length == 1 && donneSepareeEntier[0].indexOf(separateurFraction) == -1) {
            double entier = Double.parseDouble(donneSepareeEntier[0]);
            this.decimaleVersFraction(entier);

        }
        else {
            String donneSepareeFraction[] = donneSepareeEntier[1].split(separateurFraction);
            entier = Integer.parseInt(donneSepareeEntier[0]);
            numerateur = Integer.parseInt(donneSepareeFraction[0]);
            denominateur = Integer.parseInt(donneSepareeFraction[1]);
        }
    }

    // code de PGCD et decimaleVersFraction inspiré des exemples sur : https://www.geeksforgeeks.org/convert-given-decimal-number-into-an-irreducible-fraction/

    private long PGCD(long fraction, long precision){

        if (fraction == 0)
            return precision;
        else if (precision == 0)
            return fraction;
        if (fraction < precision)
            return PGCD(fraction, precision % fraction);
        else
            return PGCD(precision, fraction % precision);

    }

    private void decimaleVersFraction(double nombre){
        // Fetch integral value of the decimal
        int valeurEntier = (int)(nombre);
        double valeurDecimal = nombre - valeurEntier;;
        if(valeurDecimal != 0){
            final int valeurPrecision = 100;
            int pgcd = (int)(PGCD(Math.round(
                    valeurDecimal * valeurPrecision), valeurPrecision));

            numerateur = (int)(Math.round(valeurDecimal * valeurPrecision)) / pgcd;
            denominateur = (int)(valeurPrecision / pgcd);
        }
        entier = valeurEntier;
    }

    /*
    public int getEntier() {
        return entier;
    }

    public void setEntier(int entier) {
        this.entier = entier;
    }

    public int getNumerateur() {
        return numerateur;
    }

    public void setNumerateur(int numerateur) {
        this.numerateur = numerateur;
    }

    public int getDenominateur() {
        return denominateur;
    }

    public void setDenominateur(int denominateur) {
        this.denominateur = denominateur;
    }
     */

    public String getAsString() {

        String fraction = Integer.toString(entier) + " " + Integer.toString(numerateur) + "/" + Integer.toString(denominateur);
        if(numerateur == 1 && denominateur == 1) {
            fraction = Integer.toString(entier);
            return fraction;
        }
        else if (numerateur == 0 | denominateur == 0){

            fraction = Integer.toString(entier);
        }
        return fraction;
    }



    public double getFractionAsDouble(){
        double addition = entier + ((double)(numerateur)/(double)(denominateur));
        if (numerateur == 1 && denominateur == 1){
           addition = entier;
        }
        return addition;
    }

    @Override
    public boolean equals(Object in_Mesure) {
        if(in_Mesure instanceof MesureImperiale) {
            if(((MesureImperiale) in_Mesure).entier == this.entier && ((MesureImperiale) in_Mesure).numerateur == this.numerateur && ((MesureImperiale) in_Mesure).denominateur == this.denominateur )
            {
                return true;
            }
        }
        return  false;
    }
}
