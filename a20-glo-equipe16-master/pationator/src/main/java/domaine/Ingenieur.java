package domaine;

public class Ingenieur {
    Gabarit catalogue = new Gabarit();

    public Ingenieur() {
    }

    //Tableau 2B
    public double longueurPAFauxMax(TypeBois typeSolives){
        double mesurePAF=0;

        if(typeSolives.equal(catalogue.getTypeDeuxParQuatre())){
            mesurePAF = 8;
        }
        else if(typeSolives.equal(catalogue.getTypeDeuxParSix()) | typeSolives.equal(catalogue.getTypeDeuxParHuit())){
            mesurePAF = 16;
        }

        else if(typeSolives.equal(catalogue.getTypeDeuxParDix()) | typeSolives.equal(catalogue.getTypeDeuxParDouze())){
            mesurePAF = 24;
        }
        return mesurePAF;
    }


    public double choixLongueurSolives(TypeBois typeSolives, double espacementSolives){
        //section du tableau pour l'espacement de 8 po
        double longueurSolive = 0;

        if(espacementSolives <= 8){
            if (typeSolives.equal(catalogue.getTypeDeuxParQuatre())){
                longueurSolive = 88;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParSix())){
                longueurSolive = 138;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParHuit())){
                longueurSolive = 181;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParDix())){
                longueurSolive = 231;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParDouze())){
                longueurSolive = 281;
            }
        }

        // Section du tableau pour un espacement de 12 po
        else if(espacementSolives > 8 & espacementSolives <= 12){
            if (typeSolives.equal(catalogue.getTypeDeuxParQuatre())){
                longueurSolive = 77;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParSix())){
                longueurSolive = 120;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParHuit())){
                longueurSolive = 158;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParDix())){
                longueurSolive = 202;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParDouze())){
                longueurSolive = 244;
            }
        }

        //Section du tableau pour un espacement de 16 po
        else if(espacementSolives > 12 & espacementSolives <= 16){
            if (typeSolives.equal(catalogue.getTypeDeuxParQuatre())){
                longueurSolive = 70;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParSix())){
                longueurSolive = 109;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParHuit())){
                longueurSolive = 144;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParDix())){
                longueurSolive = 182;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParDouze())){
                longueurSolive = 211;
            }
        }

        //Section du tableau pour un espacement de 24 po

        else if(espacementSolives > 16 & espacementSolives <= 24){
            if (typeSolives.equal(catalogue.getTypeDeuxParQuatre())){
                longueurSolive = 61;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParSix())){
                longueurSolive = 96;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParHuit())){
                longueurSolive = 122;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParDix())){
                longueurSolive = 149;
            }
            if (typeSolives.equal(catalogue.getTypeDeuxParDouze())){
                longueurSolive = 172;
            }
        }
        return longueurSolive;
    }


    public double espacementSolivesPossible(double longueurSolives, TypeBois typeSolive){

        double espacementSolives = 0;
        // espacement possible pour le format 2x4
        if(typeSolive.equal(catalogue.getTypeDeuxParQuatre())){
            if(longueurSolives > 77 & longueurSolives<=88){
                espacementSolives = 8;
            }
            else if(longueurSolives > 70 & longueurSolives<=77){
                espacementSolives = 12;
            }
            else if(longueurSolives > 61 & longueurSolives<=70){
                espacementSolives = 16;
            }
            else if(longueurSolives<=61){
                espacementSolives = 24;
            }
        }

        // espacement possible pour le format 2x6 selon la longueur
        else if(typeSolive.equal(catalogue.getTypeDeuxParSix())){
            if(longueurSolives > 120 & longueurSolives<=138){
                espacementSolives = 8;
            }
            else if(longueurSolives > 109 & longueurSolives<=120){
                espacementSolives = 12;
            }
            else if(longueurSolives > 96 & longueurSolives<=109){
                espacementSolives = 16;
            }
            else if(longueurSolives<=96){
                espacementSolives = 24;
            }
        }

        // espacement possible pour le format 2x8 selon la longueur
        else if(typeSolive.equal(catalogue.getTypeDeuxParHuit())){
            if(longueurSolives > 158 & longueurSolives<=181){
                espacementSolives = 8;
            }
            else if(longueurSolives > 144 & longueurSolives<=158){
                espacementSolives = 12;
            }
            else if(longueurSolives > 122 & longueurSolives<=144){
                espacementSolives = 16;
            }
            else if(longueurSolives<=122){
                espacementSolives = 24;
            }
        }

        // espacement possible pour le format 2x4 selon la longueur
        else if(typeSolive.equal(catalogue.getTypeDeuxParDix())){
            if(longueurSolives > 202 & longueurSolives<=231){
                espacementSolives = 8;
            }
            else if(longueurSolives > 182 & longueurSolives<=202){
                espacementSolives = 12;
            }
            else if(longueurSolives > 149 & longueurSolives<=182){
                espacementSolives = 16;
            }
            else if(longueurSolives<=149){
                // Selon le tableau on peut aller jusqu'à 24p mais selon le recouvrment, on ne peut pas dépaser 18p
                espacementSolives = 18;
            }
        }

        // espacement possible pour le format 2x4 selon la longueur
        else if(typeSolive.equal(catalogue.getTypeDeuxParDouze())){
            if(longueurSolives > 241 & longueurSolives<=281){
                espacementSolives = 8;
            }
            else if(longueurSolives > 211 & longueurSolives<=241){
                espacementSolives = 12;
            }
            else if(longueurSolives > 172 & longueurSolives<=211){
                espacementSolives = 16;
            }
            else if(longueurSolives<=172){
                espacementSolives = 24;
            }
        }
        return  espacementSolives;
    }


    public TypeBois choixTypeSolives(double espacementSolives, double longueurSolives){
        TypeBois typeSolive = new TypeBois();

        //Type de solive pour 8 po d'espacement selon la longueur
        if(espacementSolives <= 8){
            if(longueurSolives<=88){
                typeSolive = catalogue.getTypeDeuxParQuatre();
            }
            else if(longueurSolives > 88 & longueurSolives<=138){
                typeSolive = catalogue.getTypeDeuxParSix();
            }
            else if(longueurSolives > 138 & longueurSolives<=181){
                typeSolive = catalogue.getTypeDeuxParHuit();
            }
            else if(longueurSolives > 181 & longueurSolives<=231){
                typeSolive = catalogue.getTypeDeuxParDix();
            }
            else if(longueurSolives > 231 & longueurSolives<=281){
                typeSolive = catalogue.getTypeDeuxParDouze();
            }
        }

        //Type de solive pour 12 po d'espacement selon la longueur
        else if(espacementSolives > 8 & espacementSolives <= 12){
            if(longueurSolives<=77){
                typeSolive = catalogue.getTypeDeuxParQuatre();
            }
            else if(longueurSolives > 77 & longueurSolives<=120){
                typeSolive = catalogue.getTypeDeuxParSix();
            }
            else if(longueurSolives > 120 & longueurSolives<=158){
                typeSolive = catalogue.getTypeDeuxParHuit();
            }
            else if(longueurSolives > 158 & longueurSolives<=202){
                typeSolive = catalogue.getTypeDeuxParDix();
            }
            else if(longueurSolives > 202 & longueurSolives<=244){
                typeSolive = catalogue.getTypeDeuxParDouze();
            }
        }

        //Type de solive pour 16 po d'espacement selon la longueur
        else if(espacementSolives > 12 & espacementSolives <= 16){
            if(longueurSolives<=70){
                typeSolive = catalogue.getTypeDeuxParQuatre();
            }
            else if(longueurSolives > 70 & longueurSolives<=109){
                typeSolive = catalogue.getTypeDeuxParSix();
            }
            else if(longueurSolives > 109 & longueurSolives<=144){
                typeSolive = catalogue.getTypeDeuxParHuit();
            }
            else if(longueurSolives > 144 & longueurSolives<=182){
                typeSolive = catalogue.getTypeDeuxParDix();
            }
            else if(longueurSolives > 182 & longueurSolives<=211){
                typeSolive = catalogue.getTypeDeuxParDouze();
            }
        }

        //Type de solive pour 24 po d'espacement selon la longueur
        else if(espacementSolives > 16 & espacementSolives <= 24){
            if(longueurSolives<=61){
                typeSolive = catalogue.getTypeDeuxParQuatre();
            }
            else if(longueurSolives > 61 & longueurSolives<=96){
                typeSolive = catalogue.getTypeDeuxParSix();
            }
            else if(longueurSolives > 96 & longueurSolives<=122){
                typeSolive = catalogue.getTypeDeuxParHuit();
            }
            else if(longueurSolives > 122 & longueurSolives<=149){
                typeSolive = catalogue.getTypeDeuxParDix();
            }
            else if(longueurSolives > 149 & longueurSolives<=172){
                typeSolive = catalogue.getTypeDeuxParDouze();
            }
        }
        return typeSolive;
    }

    //Tableau 4B 
    // espacementPoteaux donne la longueur de la poutre.
	public int nbrPlisPoutreSimple(double longueurSolive, double espacementPoteaux) {

        int nbrPlis = 1;
        //nombre de plis pour les poutres de portée simple pour 4 pi d'espacement selon la longueur des solives
        if(espacementPoteaux<=48) {
            if (longueurSolive == 192) {
                nbrPlis = 2;
            }
        }

        //nombre de plis pour les poutres de portée simple pour 6 pi d'espacement selon la longueur des solives
        else if(espacementPoteaux > 48 & espacementPoteaux<=72){
            if( longueurSolive >= 96){
                nbrPlis=2;
            }
        }

        //nombre de plis pour les poutres de portée simple pour 8 pi d'espacement selon la longueur des solives
        else if(espacementPoteaux > 72 & espacementPoteaux<=96){
            nbrPlis = 2;
        }

        else if(espacementPoteaux > 96){
            return 4;
        }
        return nbrPlis;
    }

    public TypeBois formatPlisPorteeSimple(double longueurSolives, double espacementPoteaux){
        // format "par défaut" de 2x6 car on a que des 2x6 pour un espacement de 4 pi
        TypeBois typePlis = catalogue.getTypeDeuxParSix();

        //format du plis pour les poutres de portée simple pour 6 pi d'espacement selon la longueur des solives
        if(espacementPoteaux > 48 & espacementPoteaux<=72 & longueurSolives>=180 & longueurSolives<=192){
            typePlis = catalogue.getTypeDeuxParHuit();
        }

        //format du plis pour les poutres de portée simple pour 8 pi d'espacement selon la longueur des solives
        else if(espacementPoteaux<=96 && espacementPoteaux > 72){
            if (longueurSolives>=96 & longueurSolives<144){
                typePlis = catalogue.getTypeDeuxParDix();
            }
            else if (longueurSolives>=144 & longueurSolives<=192){
                typePlis = catalogue.getTypeDeuxParDouze();
            }
        }

        else if(espacementPoteaux > 96) {
            typePlis = catalogue.getTypePlusGrandQueTous();
        }
        return typePlis;
    }

    // Tableau 6B
    public int nbrPlisPoutreDouble(double longueurSolive, double espacementPoteaux){

        //nombre de plis pour les poutres de portée double pour 4 pi d'espacement selon la longueur des solives
        int nbrPlis = 1;
        if(espacementPoteaux<=48){
            if(longueurSolive>=96){
                nbrPlis=2;
            }
        }

        //nombre de plis pour les poutres de portée double pour 6 pi d'espacement selon la longueur des solives
        else if(espacementPoteaux<=72 && espacementPoteaux > 48){
            nbrPlis=2;
        }

        //nombre de plis pour les poutres de portée double pour 8 pi d'espacement selon la longueur des solives
        else if(espacementPoteaux<=96 && espacementPoteaux > 72){
            nbrPlis = 2;
            if(longueurSolive>=144){
                nbrPlis=3;
            }
        }

        else {
            return 4;
        }

        return nbrPlis;
    }


    public TypeBois formatPlisPorteeDouble(double longueurSolives, double espacementPoteaux){
        //2x6 valeur "par défaut" car on a pas un format plus petit que 2x6 dans le tableau
        TypeBois typePlis = catalogue.getTypeDeuxParSix();

        //format du plis pour les poutres de portée double pour 4 pi d'espacement selon la longueur des solives
        if(espacementPoteaux<=48 & longueurSolives == 192){
            typePlis = catalogue.getTypeDeuxParHuit();
        }

        //format du plis pour les poutres de portée double pour 6 pi d'espacement selon la longueur des solives
        else if(espacementPoteaux > 48 & espacementPoteaux<=72){
            if( longueurSolives>=96 & longueurSolives<132){
                typePlis = catalogue.getTypeDeuxParHuit();
            }
            else if(longueurSolives>=132 & longueurSolives<192){
                typePlis = catalogue.getTypeDeuxParDix();
            }
            else if (longueurSolives == 192){
                typePlis = catalogue.getTypeDeuxParDouze();
            }
        }

        //format du plis pour les poutres de portée double pour 8 pi d'espacement selon la longueur des solives
        else if(espacementPoteaux > 72 & espacementPoteaux<=96){
            if(longueurSolives < 72){
                typePlis = catalogue.getTypeDeuxParHuit();
            }
            else if((longueurSolives>=72 & longueurSolives<132) | (longueurSolives>=132 & longueurSolives<180)){
                typePlis = catalogue.getTypeDeuxParDix();
            }
            else if((longueurSolives>=108 & longueurSolives<144) | (longueurSolives<=180 & longueurSolives<=192)){
                typePlis = catalogue.getTypeDeuxParDouze();
            }
        }
        else if(espacementPoteaux>96) {
            typePlis = catalogue.getTypePlusGrandQueTous();
        }
        return typePlis;
    }


    //Spécifications supplémentaires

    public TypeBois formatPoteaux(double longueurPoteaux){
        TypeBois typePoteaux = catalogue.getTypeQuatreParQuatre();

        if(longueurPoteaux >= 78){
            typePoteaux = catalogue.getTypeSixParSix();
        }
        return typePoteaux;
    }

    public boolean recouvrementEtEspaceSoliveLegal(TypeBois typePlanche, double espacementSolives){

        if ((typePlanche.equal(catalogue.getTypeDeuxParSix()) | typePlanche.equal(catalogue.getTypeCinqQuartParSix()))
                & espacementSolives <= 12){
            return true;
        }
        else if (typePlanche.equal(catalogue.getTypeDeuxParSix()) & ( espacementSolives >12 & espacementSolives <=18)){
            return true;
        }
        return false;

    }

    public boolean espacementSoliveLargeurPatio(int nbrSolives, TypeBois typeSolives, double espacementSolives, double largeurPatio, int nbrPortee){
        boolean validitee = true;
        double lSolives = 0;
        if(nbrPortee > 1) {
            lSolives = (typeSolives.getEpaisseurReelle() * nbrSolives) + typeSolives.getEpaisseurReelle();
        }
        else {
            lSolives = (typeSolives.getEpaisseurReelle() * nbrSolives);
        }
        double lEspacement = (nbrSolives-1)*espacementSolives;

        double divisionLargeurPatio = largeurPatio/(lSolives + lEspacement);
        if(divisionLargeurPatio < 1){
            validitee = false;
        }

        return validitee;
    }

    public boolean espacementPlancheLongueurPatio(int nbrPlanches, double espacementPlanches, double longueurPatio, TypeBois typePlanche){
        boolean validitee = false;
        double divisionLongueurPatio = (longueurPatio/
                ((typePlanche.getLargeurReelle()+espacementPlanches)*(nbrPlanches-1))) ;
        double divisionLongueurPatioAdmissible = ((longueurPatio + typePlanche.getLargeurReelle()/2)/
                (((typePlanche.getLargeurReelle() * nbrPlanches)  + (espacementPlanches * (nbrPlanches-1)))));
        if ( 1 < divisionLongueurPatio & divisionLongueurPatio > divisionLongueurPatioAdmissible){
            validitee = true;
        }
        return  validitee;
    }



}
