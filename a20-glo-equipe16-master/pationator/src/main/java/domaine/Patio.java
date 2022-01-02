package domaine;

import java.util.ArrayList;
import java.util.List;

public class Patio {

    private ArrayList<Planche> recouvrement = new ArrayList<Planche>();
    private List<Portee> listePortees = new ArrayList<>();
    private List<Piece> listePieces = new ArrayList<>();
    private Gabarit gabarit;
    private Ingenieur genie = new Ingenieur();

    public Patio() {
        gabarit = new Gabarit();
        creerPortees();
        creerPlanches();
        this.setListPiecePatio();
        gabarit.setValiditeePatio(this.valider());
    }

    public Patio(Gabarit gb) {
        gabarit = gb;
        creerPortees();
        creerPlanches();
        this.setListPiecePatio();
        gabarit.setValiditeePatio(this.valider());

    }

    public void creerPortees() {
        int nbrPortee = gabarit.getNbrPortee();

        int nbPoutreLargeur = gabarit.getNbrPoutreLargeur();

        double longueurPoutre = gabarit.getLargeurPatio() / nbPoutreLargeur;

        //Constructeur de portee avec nbrSolives
        if (gabarit.getNbrSolives() > 0) {

            //Si on a qu'une portée à construire
            if (nbrPortee == 1) {
                for (int j = 0; j < nbPoutreLargeur; j++) {
                    int idPortee = 0;
                    Portee nouvellePortee = new Portee(gabarit.getTypePlis(), gabarit.getTypePoteaux(),
                            longueurPoutre, gabarit.getEspacementSolives(),
                            gabarit.getNbrSolives(), gabarit.getLongueurSolives(),
                            gabarit.getTypeSolives(), gabarit.isVisibiliteSolives(), gabarit.isTransparenceSolives(),
                            idPortee, gabarit.getTypePlanches(), gabarit.getColorSolives(), true, gabarit.getLongueurPAFaux(),
                            nbPoutreLargeur - 1, gabarit.getNbrPoteauxParPoutre());

                    nouvellePortee.creerPoutre1(gabarit.getNbrPlis(), gabarit.getLongueurPoteaux(), gabarit.isVisibilitePoutre(),
                            gabarit.isTransparencePoutre(), gabarit.isVisibilitePoteaux(), gabarit.isTransparencePoteaux(),
                            gabarit.getColorPoutres(), gabarit.getColorPoteaux(), j);

                    nouvellePortee.creerPoutre2(gabarit.getNbrPlis(), gabarit.getLongueurPoteaux(), gabarit.isVisibilitePoutre(),
                            gabarit.isTransparencePoutre(), gabarit.isVisibilitePoteaux(), gabarit.isTransparencePoteaux(),
                            gabarit.getColorPoutres(), gabarit.getColorPoteaux(), j);
                    nouvellePortee.creerSolives();
                    if (gabarit.getLongueurPAFaux() > 0) {
                        nouvellePortee.creerPieceAuBoutDuPatio();
                    }
                    listePortees.add(nouvellePortee);

                }
            }

            // Si on a plusieurs portees a construire
            else {
                for (int j = 0; j < nbPoutreLargeur; j++) {
                    int idPortee = 0;
                    Portee nouvellePortee1 = new Portee(gabarit.getTypePlis(), gabarit.getTypePoteaux(),
                            longueurPoutre, gabarit.getEspacementSolives(),
                            gabarit.getNbrSolives(), gabarit.getLongueurSolives(),
                            gabarit.getTypeSolives(), gabarit.isVisibiliteSolives(), gabarit.isTransparenceSolives(),
                            idPortee, gabarit.getTypePlanches(), gabarit.getColorSolives(), true, 0,
                            nbPoutreLargeur - 1, gabarit.getNbrPoteauxParPoutre());
                    nouvellePortee1.creerPoutre1(gabarit.getNbrPlis(), gabarit.getLongueurPoteaux(), gabarit.isVisibilitePoutre(),
                            gabarit.isTransparencePoutre(), gabarit.isVisibilitePoteaux(), gabarit.isTransparencePoteaux(),
                            gabarit.getColorPoutres(), gabarit.getColorPoteaux(), j);
                    nouvellePortee1.creerPoutre2(gabarit.getNbrPlis(), gabarit.getLongueurPoteaux(), gabarit.isVisibilitePoutre(),
                            gabarit.isTransparencePoutre(), gabarit.isVisibilitePoteaux(), gabarit.isTransparencePoteaux(),
                            gabarit.getColorPoutres(), gabarit.getColorPoteaux(), j);
                    nouvellePortee1.creerSolives();
                    listePortees.add(nouvellePortee1);

                    for (int i = 1; i < nbrPortee; i++) {
                        int idPorteeSuite = i;
                        if (i + 1 == nbrPortee && gabarit.getLongueurPAFaux() > 0) {
                            Poutre poutrePorteeDouble = nouvellePortee1.getPoutre2();
                            poutrePorteeDouble.setPorteeDouble();
                            Portee nouvellePortee2 = new Portee(gabarit.getTypePlis(), gabarit.getTypePoteaux(),
                                    longueurPoutre, gabarit.getEspacementSolives(),
                                    gabarit.getNbrSolives(), gabarit.getLongueurSolives(),
                                    gabarit.getTypeSolives(), gabarit.isVisibiliteSolives(), gabarit.isTransparenceSolives(),
                                    idPorteeSuite, gabarit.getTypePlanches(), gabarit.getColorSolives(), false,
                                    gabarit.getLongueurPAFaux(), nbPoutreLargeur - 1, gabarit.getNbrPoteauxParPoutre());
                            nouvellePortee2.creerPoutre1(poutrePorteeDouble, gabarit.getNbrPlis(), j);
                            nouvellePortee2.creerPoutre2(gabarit.getNbrPlis(), gabarit.getLongueurPoteaux(), gabarit.isVisibilitePoutre(),
                                    gabarit.isTransparencePoutre(), gabarit.isVisibilitePoteaux(), gabarit.isTransparencePoteaux(),
                                    gabarit.getColorPoutres(), gabarit.getColorPoteaux(), j);
                            nouvellePortee2.creerSolives();
                            nouvellePortee2.creerPieceAuBoutDuPatio();
                            listePortees.add(nouvellePortee2);


                        } else {

                            Poutre poutrePorteeDouble = nouvellePortee1.getPoutre2();
                            poutrePorteeDouble.setPorteeDouble();
                            Portee nouvellePortee2 = new Portee(gabarit.getTypePlis(), gabarit.getTypePoteaux(),
                                    longueurPoutre, gabarit.getEspacementSolives(),
                                    gabarit.getNbrSolives(), gabarit.getLongueurSolives(),
                                    gabarit.getTypeSolives(), gabarit.isVisibiliteSolives(), gabarit.isTransparenceSolives(),
                                    idPorteeSuite, gabarit.getTypePlanches(), gabarit.getColorSolives(), false,
                                    0, nbPoutreLargeur - 1, gabarit.getNbrPoteauxParPoutre());
                            nouvellePortee2.creerPoutre1(poutrePorteeDouble, gabarit.getNbrPlis(), j);
                            nouvellePortee2.creerPoutre2(gabarit.getNbrPlis(), gabarit.getLongueurPoteaux(), gabarit.isVisibilitePoutre(),
                                    gabarit.isTransparencePoutre(), gabarit.isVisibilitePoteaux(), gabarit.isTransparencePoteaux(),
                                    gabarit.getColorPoutres(), gabarit.getColorPoteaux(), j);
                            nouvellePortee2.setLongueurPAFaux(gabarit.getLongueurPAFaux());
                            nouvellePortee2.creerSolives();
                            listePortees.add(nouvellePortee2);
                        }
                    }
                }
            }
        }
/*
            //Constructeur de portee sans nbrSolives
        else{

                //Si on a qu'une portée à construire
                if (nbrPortee == 1) {
                    for (int j = 0; j < nbPoutreLargeur; j++) {
                        int idPortee = 0;
                        Portee nouvellePortee = new Portee(gabarit.getTypePlis(), gabarit.getTypePoteaux(),
                                longueurPoutre, gabarit.getEspacementSolives(),
                                gabarit.getNbrSolives(), gabarit.getLongueurSolives(),
                                gabarit.getTypeSolives(), gabarit.isVisibiliteSolives(), gabarit.isTransparenceSolives(),
                                idPortee, gabarit.getTypePlanches(), gabarit.getColorSolives(), true, 0, nbPoutreLargeur - 1);
                        nouvellePortee.creerPoutre1(gabarit.getNbrPlis(), gabarit.getLongueurPoteaux(), gabarit.isVisibilitePoutre(),
                                gabarit.isTransparencePoutre(), gabarit.isVisibilitePoteaux(), gabarit.isTransparencePoteaux(),
                                gabarit.getColorPoutres(), gabarit.getColorPoteaux(), j);
                            nouvellePortee.creerPoutre2(gabarit.getNbrPlis(), gabarit.getLongueurPoteaux(), gabarit.isVisibilitePoutre(),
                                    gabarit.isTransparencePoutre(), gabarit.isVisibilitePoteaux(), gabarit.isTransparencePoteaux(),
                                    gabarit.getColorPoutres(), gabarit.getColorPoteaux(), j);
                        nouvellePortee.creerSolives();
                        listePortees.add(nouvellePortee);
                    }
                }

                // Si on a 2 portee a construire
                else if (nbrPortee == 2) {
                    int idPortee = 0;
                    for (int j = 0; j < nbPoutreLargeur; j++) {
                        Portee nouvellePortee1 = new Portee(gabarit.getTypePlis(), gabarit.getTypePoteaux(),
                                longueurPoutre, gabarit.getEspacementSolives(),
                                gabarit.getLongueurSolives(), gabarit.getTypeSolives(), gabarit.isVisibiliteSolives(), gabarit.isTransparenceSolives(),
                                idPortee, gabarit.getTypePlanches(), gabarit.getColorSolives(), true, 0, nbPoutreLargeur - 1);
                        nouvellePortee1.creerPoutre1(gabarit.getNbrPlis(), gabarit.getLongueurPoteaux(), gabarit.isVisibilitePoutre(),
                                gabarit.isTransparencePoutre(), gabarit.isVisibilitePoteaux(), gabarit.isTransparencePoteaux(),
                                gabarit.getColorPoutres(), gabarit.getColorPoteaux(), j);
                        nouvellePortee1.creerPoutre2(gabarit.getNbrPlis(), gabarit.getLongueurPoteaux(), gabarit.isVisibilitePoutre(),
                                gabarit.isTransparencePoutre(), gabarit.isVisibilitePoteaux(), gabarit.isTransparencePoteaux(),
                                gabarit.getColorPoutres(), gabarit.getColorPoteaux(), j);
                        nouvellePortee1.creerSolives();
                        listePortees.add(nouvellePortee1);

                        idPortee++;

                        Poutre poutrePorteeDouble = nouvellePortee1.getPoutre2();
                        poutrePorteeDouble.setPorteeDouble();
                        Portee nouvellePortee2 = new Portee(gabarit.getTypePlis(), gabarit.getTypePoteaux(),
                                longueurPoutre, gabarit.getEspacementSolives(),
                                gabarit.getLongueurSolives(), gabarit.getTypeSolives(), gabarit.isVisibiliteSolives(), gabarit.isTransparenceSolives(),
                                idPortee, gabarit.getTypePlanches(), gabarit.getColorSolives(), false, 0, nbPoutreLargeur - 1);
                        nouvellePortee2.creerPoutre1(poutrePorteeDouble, gabarit.getNbrPlis(), j);
                            nouvellePortee2.creerPoutre2(gabarit.getNbrPlis(), gabarit.getLongueurPoteaux(), gabarit.isVisibilitePoutre(),
                                    gabarit.isTransparencePoutre(), gabarit.isVisibilitePoteaux(), gabarit.isTransparencePoteaux(),
                                    gabarit.getColorPoutres(), gabarit.getColorPoteaux(), j);
                        nouvellePortee2.creerSolives();
                        listePortees.add(nouvellePortee2);
                    }
                }
            }*/
    }
    void creerPlanches() {
        //Emplacement debutPlanches = new Emplacement();

        int nbPoutreLargeur = gabarit.getNbrPoutreLargeur();
        int nbrPlanches = gabarit.getNbrPlanches();
        //if(gabarit.getNbrPlanches()*gabarit.getNbrPoutreLargeur() != nbrPlanches){
          // nbrPlanches = gabarit.getNbrPlanches()*nbPoutreLargeur;
        //}
        double longueurPoutre = gabarit.getLargeurPatio() / nbPoutreLargeur;

        for (int j = 0; j < nbPoutreLargeur; j++) {
            for (int i = 0; i < (nbrPlanches) / nbPoutreLargeur; i++) {
                double x, y, z;
                Emplacement positionNouvellePlanche = new Emplacement();
                x = i * (gabarit.getTypePlanches().getLargeurReelle() + gabarit.getEspacementPlanches());
                y = gabarit.getTypePoteaux().getEpaisseurReelle() / 2.0 + (j * longueurPoutre);
                z = 0;
                positionNouvellePlanche.setXYZ(x, y, z);
                Planche nouvellePlanche = new Planche(gabarit.getTypePlanches(), longueurPoutre,
                        positionNouvellePlanche, Piece.TypePiece.Planche, Piece.Orientation.NordSud,
                        gabarit.isTransparencePlanches(), gabarit.isVisibilitePlanches(), gabarit.getColorPlanches());
                recouvrement.add(nouvellePlanche);
            }
        }
    }

    public double getMesurePlancheDeFin() {
        double divisionDouble = (gabarit.getLongueurPoutre() / (gabarit.getTypePlanches().getLargeurReelle()
                + gabarit.getEspacementPlanches()));
        int divisionEntiere = (int) (gabarit.getLongueurPoutre() / (gabarit.getTypePlanches().getLargeurReelle()
                + gabarit.getEspacementPlanches()));

        return divisionDouble - divisionEntiere;
    }



    private int getNbrPlanches() {
        double longueurPatio = gabarit.getLongueurPatio();
        int nbrPlanches = (int) (longueurPatio / (gabarit.getTypePlanches().getLargeurReelle() + gabarit.getEspacementPlanches()));
        return nbrPlanches;  //+1
    }

    private void resetEspacementPlanches(){
        double epaisseurPlanches = gabarit.getTypePlanches().getLargeurReelle();
        double espacemenPlanches = gabarit.getEspacementPlanches();
        double longueurPatio = gabarit.getLongueurPatio();

        int nbrEspacement = getNbrPlanches()-1;
        double longueurPlanchesPlusEspacements = ((espacemenPlanches + epaisseurPlanches) * nbrEspacement) + epaisseurPlanches;
        double espaceRestant = longueurPatio - longueurPlanchesPlusEspacements;
        double ajoutParEspacement = espaceRestant/(double)(nbrEspacement);
        double nouvelleEspacement = epaisseurPlanches + ajoutParEspacement;
        gabarit.setEspacementPlanches(nouvelleEspacement);

    }

    private void setListPiecePatio() {
        for (Portee portee : listePortees) {
            listePieces.addAll(portee.getListePoteaux());
        }
        for (Portee portee : listePortees) {
            listePieces.addAll(portee.getListePlis());
        }
        for (Portee portee : listePortees) {
            listePieces.addAll(portee.getListeSolives());
        }
        listePieces.addAll(recouvrement);
    }

    public List<Piece> getListPieces() {

        return listePieces;
    }

    public double getCoutPatio() {
        return 0;
    }


    public Patio genererPatioOptimal(double longueur, double largeur, double hauteur, double espacementPlanches) {
        Patio newPatio = new Patio();

        return newPatio;
    }


    public boolean valider() {
        boolean validitee = true;
        String msgErreur = " ";
        double espacementPoteaux = gabarit.getLargeurPatio()/(gabarit.getNbrPoteauxParPoutre()-1);


        // Si on a une portées, on doit avoir un nombre de plis qui répond aux exigences de la portée double
        if (listePortees.size() > 1) {
            if (!(gabarit.getNbrPlis() >= genie.nbrPlisPoutreDouble(gabarit.getLongueurSolives(), espacementPoteaux))) {
                validitee = false;
                msgErreur = "Erreur nombre de plis portée double";
            } else if (!(gabarit.getTypePlis().getOrdreComparaison() >=
                    genie.formatPlisPorteeDouble(gabarit.getLongueurSolives(), espacementPoteaux).getOrdreComparaison())) {
                validitee = false;
                msgErreur = "Erreur Type de plis portée double";
            }
        }

        // Si on a 2 portées, on doit avoir un nombre de plis et un type de plis qui répond aux exigences de la portée double
        else if (listePortees.size() == 1) {
            if (!(gabarit.getNbrPlis() >= genie.nbrPlisPoutreSimple(gabarit.getLongueurSolives(), espacementPoteaux))) {
                validitee = false;
                msgErreur = "Erreur nombre de plis portée simple";
            } else if (!(gabarit.getTypePlis().getOrdreComparaison() >=
                    genie.formatPlisPorteeSimple(gabarit.getLongueurSolives(), espacementPoteaux).getOrdreComparaison())) {
                validitee = false;
                msgErreur = "Erreur Type de plis portée simple";
            }
        }
        if (!(gabarit.getLongueurPAFaux() <= genie.longueurPAFauxMax(gabarit.getTypeSolives()))) {
            validitee = false;
            msgErreur = "Erreur longueur Porte-à-faux";
        } else if (!(gabarit.getLongueurSolives() <= genie.choixLongueurSolives(gabarit.getTypeSolives(), gabarit.getEspacementSolives()))) {
            validitee = false;
            msgErreur = "Erreur longueur solives";
        } else if (!(gabarit.getEspacementSolives() <= genie.espacementSolivesPossible(gabarit.getLongueurSolives(), gabarit.getTypeSolives()))) {
            validitee = false;
            msgErreur = "Erreur espacement solives";
        } else if (!(gabarit.getTypeSolives().getOrdreComparaison() >= genie.choixTypeSolives(gabarit.getEspacementSolives(), gabarit.getLongueurSolives()).getOrdreComparaison())) {
            validitee = false;
            msgErreur = "Erreur Type Solives";
        } else if (!(gabarit.getTypePoteaux().getOrdreComparaison() >= genie.formatPoteaux(gabarit.getLongueurPoteaux()).getOrdreComparaison())) {
            validitee = false;
            msgErreur = "Erreur format des poteaux";
        } else if (!(genie.recouvrementEtEspaceSoliveLegal(gabarit.getTypePlanches(), gabarit.getEspacementSolives()))) {
            validitee = false;
            msgErreur = "Erreur espacement des solives et format du recouvrement invalide";
        } else if (!(genie.espacementSoliveLargeurPatio(gabarit.getNbrSolives(), gabarit.getTypeSolives(),
                gabarit.getEspacementSolives(), gabarit.getLargeurPatio(), gabarit.getNbrPortee()))) {
            validitee = false;
            msgErreur = "Nombre de solive + espacement dépasse la largeur du patio";

        } else if(!(genie.espacementPlancheLongueurPatio(gabarit.getNbrPlanches()/gabarit.getNbrPoutreLargeur(), gabarit.getEspacementPlanches(), gabarit.getLongueurPatio(),
                gabarit.getTypePlanches()))){
            validitee = false;
            msgErreur = "Le recouvrement n'est pas ajusté à la longueur du patio";

        } else if(gabarit.getNbrPoutreLargeur()>1){
            validitee = false;
            msgErreur = "<html>Plusieurs poutre de largeur: le guide du GCC <br/> ne permet pas de valider le patio</html>";
        }
        else{
            for (Portee portee : listePortees) {
                if (!portee.valider()) {
                    validitee = false;
                    msgErreur = "Erreur interne, construction invalide des composantes";
                    break;
                }
            }
        }
        if (validitee) {
            msgErreur = "Patio valide";
        }
        gabarit.setMsgErreur(msgErreur);
        return validitee;
    }


}

