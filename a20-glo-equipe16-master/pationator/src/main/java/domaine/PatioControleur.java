package domaine;

import Afficheur.Vues2d;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;

//import javax.media.j3d.SceneGraphObject;
//import javax.media.j3d.NodeComponent;
//import javax.media.j3d.ImageComponent;

import Afficheur.Vues2d.TypeVues2d;

import javax.swing.text.DefaultStyledDocument;

public class PatioControleur {
    private Patio patio;
    private List<Piece> listPiece;
    private Gabarit gabaritPC = new Gabarit();
    private Vues2d.TypeVues2d vueActive = Vues2d.TypeVues2d.vuePlan;
    private Deque<Gabarit> stackUndo = new ArrayDeque<>();
    private Deque<Gabarit> stackRedo = new ArrayDeque<>();
    final private double pouceParPied = 12;
    final private double decalageX = 50;
    final private double decalageY = 150;
    final private double coefficientDessin = 2.5;
    final private double MaxLongueurPoutre = 240;
    private final Ingenieur genie = new Ingenieur();

    public PatioControleur() throws IOException {
        this.patio = new Patio(gabaritPC);
        this.listPiece = patio.getListPieces();


    }

    public void setPatioControleur(Gabarit nouveauGabaritPC){
        Patio patio = new Patio(nouveauGabaritPC);
        listPiece = patio.getListPieces();

    }

    public TypeVues2d getVueActivePatio(){
        return vueActive;
    }

    public void setVueActivePatio(Vues2d.TypeVues2d vueSelectionnee){
        vueActive = vueSelectionnee;
    }

    public List<Piece> getListPiece() {
        return listPiece;
    }

    public Gabarit getGabaritPC(){
        return this.gabaritPC;
    }

    public Piece getPiecePointer(Point2D.Double locationSouris, double tauxZoom){
        ArrayList<Piece> pieceValide = new ArrayList<>();
        Piece pieceARetourner = null;
        if (getVueActivePatio() == TypeVues2d.vueCote){
            for(Piece lapiece : listPiece){
                if(lapiece.getEmplacement().getX()* coefficientDessin * tauxZoom + (decalageX * tauxZoom) <= locationSouris.x && lapiece.getEmplacement().getZ()* coefficientDessin * tauxZoom + (decalageY * tauxZoom) <= locationSouris.y){
                    if(lapiece.getTypePiece() == Piece.TypePiece.Planche) {
                        if(lapiece.getEmplacement().getX()* coefficientDessin * tauxZoom + (decalageX * tauxZoom) + (lapiece.getTypeBois().getLargeurReelle())*coefficientDessin*tauxZoom >= locationSouris.x
                                && ((lapiece.getEmplacement().getZ()*coefficientDessin)+decalageY) + (lapiece.getTypeBois().getEpaisseurReelle())*coefficientDessin >= locationSouris.y){
                            pieceValide.add(lapiece);
                        }
                    }
                    else if (lapiece.getTypePiece() == Piece.TypePiece.Solive) {
                        if(lapiece.getEmplacement().getX()* coefficientDessin * tauxZoom + (decalageX * tauxZoom) + (lapiece.getLongueur()*coefficientDessin*tauxZoom) >= locationSouris.x
                                && lapiece.getEmplacement().getZ()* coefficientDessin * tauxZoom + (decalageY * tauxZoom) + lapiece.getTypeBois().getLargeurReelle()*coefficientDessin*tauxZoom >= locationSouris.y){
                            pieceValide.add(lapiece);
                        }
                    }
                    else if (lapiece.getTypePiece() == Piece.TypePiece.Poteau){
                        if( lapiece.getEmplacement().getX()* coefficientDessin * tauxZoom + (decalageX * tauxZoom) + lapiece.getTypeBois().getLargeurReelle()*coefficientDessin*tauxZoom >= locationSouris.x
                                && lapiece.getEmplacement().getZ()* coefficientDessin * tauxZoom + (decalageY * tauxZoom) + lapiece.getLongueur()*coefficientDessin*tauxZoom >= locationSouris.y){
                            pieceValide.add(lapiece);
                        }
                    }
                    else {//poutre
                        if( lapiece.getEmplacement().getX()* coefficientDessin * tauxZoom + (decalageX * tauxZoom) + lapiece.getTypeBois().getEpaisseurReelle()*coefficientDessin*tauxZoom >= locationSouris.x
                                && lapiece.getEmplacement().getZ()* coefficientDessin * tauxZoom + (decalageY * tauxZoom) + lapiece.getTypeBois().getLargeurReelle()*coefficientDessin*tauxZoom >= locationSouris.y){
                            pieceValide.add(lapiece);
                        }
                    }
                }
            }
            for(Piece lapiece : pieceValide){
                if(lapiece.getvisiblitee()){
                    if(pieceARetourner == null){
                        pieceARetourner = lapiece;
                    }
                    else if((lapiece.getEmplacement().getZ()*coefficientDessin+decalageY) < (pieceARetourner.getEmplacement().getZ()*coefficientDessin+decalageY)) {
                        pieceARetourner = lapiece;
                    }
                }
            }
        }
        else if(getVueActivePatio() == TypeVues2d.vueFace) {
            for(Piece lapiece : listPiece){
                if((lapiece.getEmplacement().getY()* coefficientDessin * tauxZoom) + (decalageX * tauxZoom)<= locationSouris.x && (lapiece.getEmplacement().getZ()* coefficientDessin * tauxZoom) + (decalageY * tauxZoom) <= locationSouris.y){
                    if(lapiece.getTypePiece() == Piece.TypePiece.Planche) {
                        if(lapiece.getEmplacement().getY()* coefficientDessin * tauxZoom + (decalageX * tauxZoom) + lapiece.getLongueur()*coefficientDessin*tauxZoom >= locationSouris.x
                                && lapiece.getEmplacement().getZ()* coefficientDessin * tauxZoom + (decalageY * tauxZoom) + (lapiece.getTypeBois().getEpaisseurReelle())*coefficientDessin*tauxZoom >= locationSouris.y){
                            pieceValide.add(lapiece);
                        }
                    }
                    else if (lapiece.getTypePiece() == Piece.TypePiece.Solive) {
                        if(lapiece.getEmplacement().getY()* coefficientDessin * tauxZoom + (decalageX * tauxZoom) + (lapiece.getTypeBois().getEpaisseurReelle()*coefficientDessin*tauxZoom) >= locationSouris.x
                                && lapiece.getEmplacement().getZ()* coefficientDessin * tauxZoom + (decalageY * tauxZoom) + lapiece.getTypeBois().getLargeurReelle()*coefficientDessin*tauxZoom >= locationSouris.y){
                            pieceValide.add(lapiece);
                        }
                    }
                    else if (lapiece.getTypePiece() == Piece.TypePiece.Poteau){
                        if(lapiece.getEmplacement().getY()* coefficientDessin * tauxZoom + (decalageX * tauxZoom) + lapiece.getTypeBois().getLargeurReelle()*coefficientDessin*tauxZoom >= locationSouris.x
                                && lapiece.getEmplacement().getZ()* coefficientDessin * tauxZoom + (decalageY * tauxZoom) + lapiece.getLongueur()*coefficientDessin*tauxZoom >= locationSouris.y){
                            pieceValide.add(lapiece);
                        }
                    }
                    else {//poutre
                        if(lapiece.getEmplacement().getY()* coefficientDessin * tauxZoom + (decalageX * tauxZoom) + lapiece.getLongueur()*coefficientDessin*tauxZoom >= locationSouris.x
                                && lapiece.getEmplacement().getZ()* coefficientDessin * tauxZoom + (decalageY * tauxZoom) + lapiece.getTypeBois().getLargeurReelle()*coefficientDessin*tauxZoom >= locationSouris.y){
                            pieceValide.add(lapiece);
                        }
                    }
                }
            }
            for(Piece lapiece : pieceValide){
                if(lapiece.getvisiblitee()){
                    if(pieceARetourner == null){
                        pieceARetourner = lapiece;
                    }
                    else if((lapiece.getEmplacement().getZ()*coefficientDessin)+decalageY < (pieceARetourner.getEmplacement().getZ()*coefficientDessin+decalageY)) {
                        pieceARetourner = lapiece;
                    }
                }
            }
        }
        //On assume que nous avons la vue plan
        else{
            for(Piece lapiece : listPiece){
                if(lapiece.getEmplacement().getX() * coefficientDessin * tauxZoom + (decalageX * tauxZoom)  <= locationSouris.x && lapiece.getEmplacement().getY() * coefficientDessin * tauxZoom + (decalageY * tauxZoom)
                        <= locationSouris.y){
                    if(lapiece.getTypePiece() == Piece.TypePiece.Planche) {
                        if( lapiece.getEmplacement().getX() * coefficientDessin * tauxZoom + (decalageX * tauxZoom) + lapiece.getTypeBois().getLargeurReelle() * coefficientDessin * tauxZoom >= locationSouris.x
                                && lapiece.getEmplacement().getY() * coefficientDessin * tauxZoom + (decalageY * tauxZoom) + lapiece.getLongueur() * coefficientDessin * tauxZoom >= locationSouris.y){
                            pieceValide.add(lapiece);
                        }
                    }
                    else if (lapiece.getTypePiece() == Piece.TypePiece.Solive) {
                        if( lapiece.getEmplacement().getX() * coefficientDessin * tauxZoom + (decalageX * tauxZoom) + lapiece.getLongueur() * coefficientDessin * tauxZoom >= locationSouris.x
                                && lapiece.getEmplacement().getY() * coefficientDessin * tauxZoom + (decalageY * tauxZoom) + lapiece.getTypeBois().getEpaisseurReelle() * coefficientDessin * tauxZoom >= locationSouris.y){
                            pieceValide.add(lapiece);
                        }
                    }
                    else if (lapiece.getTypePiece() == Piece.TypePiece.Poteau){
                        if( lapiece.getEmplacement().getX() * coefficientDessin * tauxZoom + (decalageX * tauxZoom) + lapiece.getTypeBois().getLargeurReelle() * coefficientDessin * tauxZoom >= locationSouris.x
                                && lapiece.getEmplacement().getY() * coefficientDessin * tauxZoom + (decalageY * tauxZoom) + lapiece.getTypeBois().getEpaisseurReelle() * coefficientDessin * tauxZoom >= locationSouris.y){
                            pieceValide.add(lapiece);
                        }
                    }
                    else {
                        if( lapiece.getEmplacement().getX() * coefficientDessin * tauxZoom + (decalageX * tauxZoom) + lapiece.getTypeBois().getEpaisseurReelle() * coefficientDessin * tauxZoom >= locationSouris.x
                                && lapiece.getEmplacement().getY() * coefficientDessin * tauxZoom + (decalageY * tauxZoom) + lapiece.getLongueur()*coefficientDessin*tauxZoom >= locationSouris.y){
                            pieceValide.add(lapiece);
                        }
                    }
                }
            }
            for(Piece lapiece : pieceValide){
                if(lapiece.getvisiblitee()){
                    if(pieceARetourner == null){
                        pieceARetourner = lapiece;
                    }
                    else if(lapiece.getEmplacement().getZ()< pieceARetourner.getEmplacement().getZ()) {
                        pieceARetourner = lapiece;
                    }
                }
            }
        }

        return pieceARetourner;
    }

    public void setLargeurPatio(double largeurPatio){

        int roundUpNbrPoutreLargeur = (int)Math.ceil(largeurPatio/MaxLongueurPoutre);

        int nbrPoutreLargeur = gabaritPC.getNbrPoutreLargeur();

        if((gabaritPC.getLargeurPatio() != largeurPatio) & (gabaritPC.getLongueurPoutre() != largeurPatio)) {
            gabaritPC.setLargeurPatio(largeurPatio);
            gabaritPC.setLongueurPoutre(largeurPatio);
            gabaritPC.setNbrPlanches(gabaritPC.getNbrPlanches() / nbrPoutreLargeur);
            gabaritPC.setNbrPoutreLargeur(roundUpNbrPoutreLargeur);
            gabaritPC.setNbrPlanches(gabaritPC.getNbrPlanches() * roundUpNbrPoutreLargeur);
            setNbrSolives();
        }
    }

    public void setLongueurPatio(double longueurPatio){
        double longueurSolives = gabaritPC.getLongueurSolives();
        if(gabaritPC.getLongueurPAFaux() > 0) {
            longueurSolives = ((longueurPatio - gabaritPC.getLongueurPAFaux()) / gabaritPC.getNbrPortee()) +
                    ((gabaritPC.getNbrPlis() * gabaritPC.getTypePlis().getEpaisseurReelle()) * (gabaritPC.getNbrPortee() - 1))
                    - gabaritPC.getTypeSolives().getEpaisseurReelle();
        } else {
            longueurSolives = (longueurPatio / gabaritPC.getNbrPortee()) +
                    ((gabaritPC.getNbrPlis() * gabaritPC.getTypePlis().getEpaisseurReelle()) * (gabaritPC.getNbrPortee() - 1))
                    - gabaritPC.getTypeSolives().getEpaisseurReelle();
        }

        gabaritPC.setLongueurSolives(longueurSolives);
        gabaritPC.setLongueurPatio(longueurPatio);
        setNbrPlanches();
    }

    public void setLongueurPatioMagic(double longueurPatio){
        double longueurSolives = gabaritPC.getLongueurSolives();
        if(gabaritPC.getLongueurPAFaux() > 0) {
            longueurSolives = ((longueurPatio - gabaritPC.getLongueurPAFaux()) / gabaritPC.getNbrPortee()) +
                    ((gabaritPC.getNbrPlis() * gabaritPC.getTypePlis().getEpaisseurReelle()) * (gabaritPC.getNbrPortee() - 1))
                    - gabaritPC.getTypeSolives().getEpaisseurReelle();
        } else {
            longueurSolives = (longueurPatio / gabaritPC.getNbrPortee()) +
                    ((gabaritPC.getNbrPlis() * gabaritPC.getTypePlis().getEpaisseurReelle()) * (gabaritPC.getNbrPortee() - 1))
                    - gabaritPC.getTypeSolives().getEpaisseurReelle();
        }

        gabaritPC.setLongueurSolives(longueurSolives);
        gabaritPC.setLongueurPatio(longueurPatio);
    }

    public void setHauteurPatio(double hauteurPatio){
        gabaritPC.setHauteurPatio(hauteurPatio);
        double structure = gabaritPC.getTypePlanches().getEpaisseurReelle() + gabaritPC.getTypePlis().getLargeurReelle() +
                gabaritPC.getTypeSolives().getLargeurReelle();
        gabaritPC.setLongueurPoteaux(hauteurPatio - structure);
        if(hauteurPatio-structure < 0)
        gabaritPC.setLongueurPoteaux(0);
    }

    public void setNbrSections(int nbrSections){
        gabaritPC.setNbrPoutres(nbrSections+1);
        gabaritPC.setNbrPortee(nbrSections);
        setLongueurPatio(gabaritPC.getLongueurPatio());
    }
    public void setNbrSectionsMagic(int nbrSections){
        gabaritPC.setNbrPoutres(nbrSections+1);
        gabaritPC.setNbrPortee(nbrSections);
        setLongueurPatioMagic(gabaritPC.getLongueurPatio());
    }

    public void setNbrPoutre(int nbrPoutre){
        gabaritPC.setNbrPortee(nbrPoutre-1);
        gabaritPC.setNbrPoutres(nbrPoutre);

    }

    public void setLongueurSolive(double longueurSolive){
        double longueurPatio = (longueurSolive * gabaritPC.getNbrPortee()) - ((gabaritPC.getNbrPlis()) * gabaritPC.getTypePlis().getEpaisseurReelle()) * (gabaritPC.getNbrPortee()-1);
        gabaritPC.setLongueurPatio(longueurPatio);
        gabaritPC.setLongueurSolives(longueurSolive);
        this.setNbrPlanches();
    }

    public void setHauteurPoteaux(double hauteurPoteaux){

        double structure = gabaritPC.getTypePlanches().getEpaisseurReelle()
                + gabaritPC.getTypePlis().getLargeurReelle()
                + gabaritPC.getTypeSolives().getLargeurReelle();
        double hauteurPatio = hauteurPoteaux + structure;
        gabaritPC.setHauteurPatio(hauteurPatio);
        gabaritPC.setLongueurPoteaux(hauteurPoteaux);
    }

    public void setNbrPlis(int nbrPlis){
        gabaritPC.setNbrPlis(nbrPlis);
    }

    public void setPAFaux(double longueurPAF){
        double mesureAvantModif = gabaritPC.getLongueurPatio();
        gabaritPC.setLongueurPAFaux(longueurPAF);
        setLongueurPatio(gabaritPC.getLongueurPatio());
    }

    public void changeGabaritStack() {
        Gabarit ancienGabarit = gabaritPC.copieGabarit();
        stackUndo.push(ancienGabarit);

    }

    public void changeGabaritUndo() {
        if (stackUndo.size() > 0 && !gabaritPC.equals(stackUndo.peek())) {
            stackRedo.push(gabaritPC);
            boolean solivesVisible = false;
            if(gabaritPC.isVisibiliteSolives()){
                solivesVisible = true;
            }
            this.gabaritPC = stackUndo.pop();

            if(solivesVisible & !gabaritPC.isVisibiliteSolives()){
                 gabaritPC.setVisibiliteSolives(true);
            }
            System.out.println(gabaritPC.isVisibiliteSolives());
        }
    }

    public void changeGabaritRedo() {
        Gabarit temp = this.gabaritPC;
        if(stackRedo.size() > 0 && !gabaritPC.equals(stackRedo.peek())){
            this.gabaritPC = stackRedo.pop();
            stackUndo.push(temp);
        }

    }

    public void BoutonMagic() {
        //On commence avec une seule section et on construit différent patio à partir de là
        gabaritPC.setNbrPortee(1);
        gabaritPC.setNbrPoutres(2);

        //Il est requit d'avoir les mesures et type minimumes valide pour construire un patio valide
        double longueurPatio = gabaritPC.getLongueurPatio();
        double largeurPatio = gabaritPC.getLargeurPatio();
        double longueurPoutre = largeurPatio;
        double LONGUEURSOLIVEMAX = 240;
        double LONGUEURPOUTREMAX = 240;
        double longueurSolives = longueurPatio;
        double pied = 12;

        TypeBois typeSolives = gabaritPC.getTypeDeuxParQuatre();
        TypeBois typePlis = gabaritPC.getTypeDeuxParSix();
        TypeBois typePlanches = gabaritPC.getTypePlanches();

        ArrayList<Double> listeCoutSolives = new ArrayList<>();
        ArrayList<Double> listeCoutPoutres = new ArrayList<>();
        ArrayList<Double> listeCoutPlanches = new ArrayList<>();
        ArrayList<TypeBois>  listeFormatSolivesValide = new ArrayList<>();
        ArrayList<TypeBois> listeFormatPoutreValide = new ArrayList<>();

        int nbrSection = 1;
        int nbrPoutres = 2;

        //On doit avoir le moins de sections possible pour reduire les couts en frai de poutres
        // On doit avoir le plus grand espacement possible pour réduire le cout de solive (on doit décider de l'espacement max selon le format du recouvrement)
        setNbrSolives();
        setEspacementSolives();
        double espacementSolive = gabaritPC.getEspacementSolives();

        if (typePlanches.equal(gabaritPC.getTypeDeuxParSix())){
            gabaritPC.setEspacementSolives(18);
            setNbrSolives();
        }else{
            gabaritPC.setEspacementSolives(12);
            setNbrSolives();
        }
        while((espacementSolive > 12) & !typePlanches.equal(gabaritPC.getTypeDeuxParSix()) ){
            espacementSolive-=2;
            gabaritPC.setEspacementSolives(espacementSolive);
            setNbrSolives();
        }

        //On ajuste le nombre de section et la longueur des solives lorsque la longueur max est dépassé
        while(longueurSolives > LONGUEURSOLIVEMAX){
            nbrSection++;
            gabaritPC.setNbrPortee(nbrSection);
            nbrPoutres++;
            gabaritPC.setNbrPoutres(nbrPoutres);
            longueurSolives = longueurPatio/nbrSection + (gabaritPC.getNbrPlis()*typePlis.getEpaisseurReelle()) * (nbrSection-1);

        }


        /* Pour tous les types de bois on veut trouver celui qui nous donne un espacement valide
           Une fois qu'on trouve se type, on veut tout les types supérieur ou égal valdie
         */
        for(int i = typeSolives.getOrdreComparaison(); i < gabaritPC.getCatalogue().get(4).getOrdreComparaison(); i++){
            typeSolives = gabaritPC.getCatalogue().get(i);
            if( espacementSolive <= genie.espacementSolivesPossible(longueurSolives, typeSolives)) {
                if (typeSolives.getOrdreComparaison() >= genie.choixTypeSolives(espacementSolive, longueurSolives).getOrdreComparaison()) {
                    listeFormatSolivesValide.add(typeSolives);
                }
            }
        }

        // On calcul le prix total de chaque format de solives valide * la longueur * nbr
        for(TypeBois type: listeFormatSolivesValide){
            double prix = 0;
            switch (type.getOrdreComparaison()){
                case 1:
                    prix = gabaritPC.getNbrSolives() * longueurSolives/pied * gabaritPC.getPrixFormatDeuxParQuatre();
                    listeCoutSolives.add(prix);

                case 2:
                    prix = gabaritPC.getNbrSolives() * longueurSolives/pied * gabaritPC.getPrixFormatDeuxParSix();
                    listeCoutSolives.add(prix);

                case 3:
                    prix = gabaritPC.getNbrSolives() * longueurSolives/pied * gabaritPC.getPrixFormatDeuxParHuit();
                    listeCoutSolives.add(prix);

                case 4:
                    prix = gabaritPC.getNbrSolives() * longueurSolives/pied * gabaritPC.getPrixFormatDeuxParDix();
                    listeCoutSolives.add(prix);

                case 5:
                    prix = gabaritPC.getNbrSolives() * longueurSolives/pied * gabaritPC.getPrixFormatDeuxParDouze();
                    listeCoutSolives.add(prix);
            }
        }

        double espacementPoteaux = gabaritPC.getLongueurPoutre()/gabaritPC.getNbrPoteauxParPoutre();
        int nbPlis = 1;

        //Calcul des types de plis admissible et nombre de plis minimum
        if (nbrSection > 1) {
            for( int i = typePlis.getOrdreComparaison(); i < gabaritPC.getCatalogue().get(5).getOrdreComparaison(); i++){
                typePlis = gabaritPC.getCatalogue().get(i);
                if(typePlis.getOrdreComparaison() >= genie.formatPlisPorteeSimple(longueurSolives, espacementPoteaux).getOrdreComparaison()){
                    listeFormatPoutreValide.add(typePlis);
                }
            }
            nbPlis = genie.nbrPlisPoutreSimple(longueurSolives, espacementPoteaux);
            gabaritPC.setNbrPlis(nbPlis);
        } else {
            for( int i = typePlis.getOrdreComparaison(); i < gabaritPC.getCatalogue().get(5).getOrdreComparaison(); i++){
                typePlis = gabaritPC.getCatalogue().get(i);
                if(typePlis.getOrdreComparaison() >= genie.formatPlisPorteeDouble(longueurSolives, espacementPoteaux).getOrdreComparaison()){
                    listeFormatPoutreValide.add(typePlis);
                }
            }
            nbPlis = genie.nbrPlisPoutreDouble(longueurSolives, espacementPoteaux);
            gabaritPC.setNbrPlis(nbPlis);
        }

        // On calcul le prix de chaque format et on le met dans le tableau de prix. quand on va trouver le moins chère.
        // l'indexe dans le tableau de prix sera le même que celui du tableau de type
        for(TypeBois type: listeFormatPoutreValide){
            double prix = 0;
            switch (type.getOrdreComparaison()){

                case 2:
                    prix = nbPlis * nbrPoutres * longueurPoutre/pied * gabaritPC.getPrixFormatDeuxParSix();
                    listeCoutPoutres.add(prix);

                case 3:
                    prix = nbPlis * nbrPoutres * longueurPoutre/pied * gabaritPC.getPrixFormatDeuxParHuit();
                    listeCoutPoutres.add(prix);

                case 4:
                    prix = nbPlis * nbrPoutres * longueurPoutre/pied * gabaritPC.getPrixFormatDeuxParDix();
                    listeCoutPoutres.add(prix);

                case 5:
                    prix = nbPlis * nbrPoutres * longueurPoutre/pied * gabaritPC.getPrixFormatDeuxParDouze();
                    listeCoutPoutres.add(prix);
            }
        }
        int indexeSolivesCheap = retourCheapTypeIndexe(listeCoutSolives);
        if (listeFormatSolivesValide.size()>0){
            typeSolives = listeFormatSolivesValide.get(indexeSolivesCheap);
            gabaritPC.setTypeSolives(typeSolives);
        }

        int indexePoutresCheap = retourCheapTypeIndexe(listeCoutPoutres);
        typePlis = listeFormatPoutreValide.get(indexePoutresCheap);
        gabaritPC.setTypePlis(typePlis);
//        while (!genie.espacementSoliveLargeurPatio(gabaritPC.getNbrSolives(), typeSolives, espacementSolive, largeurPatio, nbrSection)){
//            int nbSolive = gabaritPC.getNbrSolives()-1;
//            gabaritPC.setNbrSolives(nbSolive);
//        }
        setNbrSections(nbrSection);
        setNbrPlanches();
    }

    public int retourCheapTypeIndexe(ArrayList<Double> tableauPrix){
        int indexe = 0;
        if(tableauPrix.size()>0) {
            double cheapest = tableauPrix.get(0);
            for (int i = 1; i < tableauPrix.size(); i++) {
                if (cheapest > tableauPrix.get(i)) {
                    cheapest = tableauPrix.get(i);
                    indexe = i;
                }
            }
        }
        return indexe;
    }

    public void setNbrPlanches() {

        double EspacementPlanches = gabaritPC.getEspacementPlanches();
        double longueurPlanches = gabaritPC.getTypePlanches().getLargeurReelle();
        double longueur = EspacementPlanches + longueurPlanches;
        double lPatio;
        if (gabaritPC.getLongueurPAFaux() > 0) {
            lPatio = gabaritPC.getLongueurPatio() + 1.5;
        } else {
            lPatio = gabaritPC.getLongueurPatio();
        }
        double nbPlanche = lPatio / longueur;
        int roundUpNbrPoutreLargeur = (int) Math.ceil(gabaritPC.getLargeurPatio() / MaxLongueurPoutre);
        int nbPlancheInt = (int) Math.round(nbPlanche);
        int nbPlancheIntTotal = (int) Math.round(nbPlanche) * roundUpNbrPoutreLargeur;
        double lPlanches = nbPlancheInt * 5.5;
        double espacementReste = (lPatio - lPlanches) / (nbPlancheInt - 1);

        if (genie.espacementPlancheLongueurPatio(nbPlancheIntTotal, espacementReste, gabaritPC.getLongueurPatio(),
                gabaritPC.getTypePlanches())) {
            gabaritPC.setNbrPlanches(nbPlancheIntTotal);
            gabaritPC.setEspacementPlanches(espacementReste);
        }

    }

    public void setNbrSolives() {

        double longueur = gabaritPC.getEspacementSolives() + gabaritPC.getTypeSolives().getEpaisseurReelle();
        double lPatio = gabaritPC.getLargeurPatio();
        double nbSolives = Math.round(lPatio / longueur);
        int nbSolivesInt = (int) (nbSolives);

        double lSolives = 0;
        if (gabaritPC.getNbrPortee() > 1) {
            lSolives = (nbSolivesInt * gabaritPC.getTypeSolives().getEpaisseurReelle()) + gabaritPC.getTypeSolives().getEpaisseurReelle();
        } else {
            lSolives = nbSolivesInt * gabaritPC.getTypeSolives().getEpaisseurReelle();
        }
        double espacementReste = (lPatio - lSolives) / (nbSolivesInt-1);

        if(genie.espacementSoliveLargeurPatio(nbSolivesInt, gabaritPC.getTypeSolives(), espacementReste,
                gabaritPC.getLargeurPatio(), gabaritPC.getNbrPortee())) {
            gabaritPC.setNbrSolives(nbSolivesInt);
            //gabaritPC.setEspacementSolives(espacementReste);
        }
    }

    public void setNbrSolivesMagic() {
        double lPatio = gabaritPC.getLargeurPatio();
        double longueur = gabaritPC.getEspacementSolives() + gabaritPC.getTypeSolives().getEpaisseurReelle();
        double nbSolives = Math.ceil(lPatio / longueur);
        int nbSolivesInt = (int) (nbSolives);

        double lSolives = 0;
            if (gabaritPC.getNbrPortee() > 1) {
                lSolives = (nbSolivesInt * gabaritPC.getTypeSolives().getEpaisseurReelle()) + gabaritPC.getTypeSolives().getEpaisseurReelle();
            } else {
                lSolives = nbSolivesInt * gabaritPC.getTypeSolives().getEpaisseurReelle();
            }
            double espacementReste = (lPatio - lSolives) / (nbSolivesInt - 1);
            double espacementSolives = gabaritPC.getEspacementSolives();
            double lEspacement = (nbSolives - 1) * espacementSolives;

            double divisionLargeurPatio = lPatio / (lSolives + lEspacement);
            while (divisionLargeurPatio > 1) {
                espacementSolives -=1;
                gabaritPC.setEspacementSolives(espacementSolives);
                longueur = gabaritPC.getEspacementSolives() + gabaritPC.getTypeSolives().getEpaisseurReelle();
                nbSolives = Math.ceil(lPatio / longueur);
                nbSolivesInt = (int) (nbSolives);

                lSolives = 0;
                if (gabaritPC.getNbrPortee() > 1) {
                    lSolives = (nbSolivesInt * gabaritPC.getTypeSolives().getEpaisseurReelle()) + gabaritPC.getTypeSolives().getEpaisseurReelle();
                } else {
                    lSolives = nbSolivesInt * gabaritPC.getTypeSolives().getEpaisseurReelle();
                }
                espacementReste = (lPatio - lSolives) / (nbSolivesInt - 1);
                espacementSolives = gabaritPC.getEspacementSolives();
                lEspacement = (nbSolives - 1) * espacementSolives;

               divisionLargeurPatio = lPatio / (lSolives + lEspacement);
            }
            if (genie.espacementSoliveLargeurPatio(nbSolivesInt, gabaritPC.getTypeSolives(), espacementReste,
                    gabaritPC.getLargeurPatio(), gabaritPC.getNbrPortee())) {
                gabaritPC.setNbrSolives(nbSolivesInt);
            }
        }

    public void setEspacementSolives() {
        double longueur = gabaritPC.getEspacementSolives() + gabaritPC.getTypeSolives().getEpaisseurReelle();
        double lPatio = gabaritPC.getLargeurPatio();
        double nbSolives = Math.round(lPatio / longueur);
        int nbSolivesInt = (int) (nbSolives);
        double lSolives = 0;
        if (gabaritPC.getNbrPortee() > 1) {
            lSolives = (nbSolivesInt * gabaritPC.getTypeSolives().getEpaisseurReelle()) + gabaritPC.getTypeSolives().getEpaisseurReelle();
        } else {
            lSolives = nbSolivesInt * gabaritPC.getTypeSolives().getEpaisseurReelle();
        }
        double espacementReste = (lPatio - lSolives) / (nbSolivesInt - 1);

        if(genie.espacementSoliveLargeurPatio(nbSolivesInt, gabaritPC.getTypeSolives(), espacementReste,
                gabaritPC.getLargeurPatio(), gabaritPC.getNbrPortee())) {
            //gabaritPC.setNbrSolives(nbSolivesInt);
            gabaritPC.setEspacementSolives(espacementReste);
        }
    }

    public void setCoutPatioAvecPoteaux(double cout){
        gabaritPC.setCoutPatioAvecPoteaux(cout);
    }

    public void setNbrPoteauxParPoutre(int nombrePoteauParPoutre){
        gabaritPC.setNbrPoteauxParPoutre(nombrePoteauParPoutre);
    }

    public void setPrixFormat54X6Gabarit(double nouveauPrix){
        TypeBois typeBois = gabaritPC.getCatalogue().get(7);
        typeBois.setCoutLineaire(nouveauPrix);
    }

    public void setPrixFormat2X4Gabarit(double nouveauPrix){
        TypeBois typeBois = gabaritPC.getCatalogue().get(0);
        typeBois.setCoutLineaire(nouveauPrix);
    }

    public void setPrixFormat2X6Gabarit(double nouveauPrix){
        TypeBois typeBois = gabaritPC.getCatalogue().get(1);
        typeBois.setCoutLineaire(nouveauPrix);

    }

    public void setPrixFormat2X8Gabarit(double nouveauPrix){
        TypeBois typeBois = gabaritPC.getCatalogue().get(2);
        typeBois.setCoutLineaire(nouveauPrix);
    }

    public void setPrixFormat2X10Gabarit(double nouveauPrix){
        TypeBois typeBois = gabaritPC.getCatalogue().get(3);
        typeBois.setCoutLineaire(nouveauPrix);
    }

    public void setPrixFormat2X12Gabarit(double nouveauPrix){
        TypeBois typeBois = gabaritPC.getCatalogue().get(4);
        typeBois.setCoutLineaire(nouveauPrix);

    }

    public void setPrixFormat4X4Gabarit(double nouveauPrix){
        TypeBois typeBois = gabaritPC.getCatalogue().get(6);
        typeBois.setCoutLineaire(nouveauPrix);
    }

    public void setPrixFormat6X6Gabarit(double nouveauPrix){
        TypeBois typeBois = gabaritPC.getCatalogue().get(5);
        typeBois.setCoutLineaire(nouveauPrix);
    }

    public void setPrixFormatsStack(){
        this.changeGabaritStack();
    }

    public void getCoutQuantiteLongueurSolives(){
        double valeurPorteFaux = gabaritPC.getLongueurPAFaux();
        double coutSolives;
        double coutSolivesPAF= 0.00;

        if (valeurPorteFaux==0){
            int nbSolives = gabaritPC.getNbrSolives() * gabaritPC.getNbrPortee();
            coutSolives = nbSolives * ((gabaritPC.getLongueurSolives()) /pouceParPied);

            coutSolives = coutSolives * gabaritPC.getTypeSolives().getCoutLineaire();
            gabaritPC.setCoutSolives(coutSolives);
            gabaritPC.setCoutSolivesPAF(0.00);
            gabaritPC.setNbrSolivesPAF(0);
            gabaritPC.setLongueurSolivesPAF(0.00);
        }else{
            int nbSolives = gabaritPC.getNbrSolives() * (gabaritPC.getNbrPortee()-1);
            coutSolives = nbSolives * ((gabaritPC.getLongueurSolives()) /pouceParPied);

            int nbSolivesPorterFaux = gabaritPC.getNbrSolives();
            coutSolivesPAF = nbSolivesPorterFaux * ((gabaritPC.getLongueurSolives()+ valeurPorteFaux) /pouceParPied);
            coutSolives = coutSolives * gabaritPC.getTypeSolives().getCoutLineaire();
            coutSolivesPAF = coutSolivesPAF * gabaritPC.getTypeSolives().getCoutLineaire();
            gabaritPC.setCoutSolives(coutSolives);
            gabaritPC.setCoutSolivesPAF(coutSolivesPAF);
            gabaritPC.setNbrSolivesPAF(nbSolivesPorterFaux);
            gabaritPC.setLongueurSolivesPAF(gabaritPC.getLongueurSolives()+valeurPorteFaux);
        }
    }

    public void getCoutQuantiteLongueurPlanches(){
        double coutPlanches = gabaritPC.getNbrPlanches() *
                ((gabaritPC.getLargeurPatio() / gabaritPC.getNbrPoutreLargeur()) /pouceParPied);
        coutPlanches = coutPlanches * gabaritPC.getTypePlanches().getCoutLineaire();
        gabaritPC.setCoutPlanches(coutPlanches);
    }

    public void getCoutQuantiteLongueurPoutres(){
        int nbPoutres = gabaritPC.getNbrPoutres();
        double coutPoutre = gabaritPC.getNbrPlis()*nbPoutres * (gabaritPC.getLongueurPoutre() /pouceParPied);
        coutPoutre = coutPoutre * gabaritPC.getTypePlis().getCoutLineaire() * gabaritPC.getNbrPlis();
        gabaritPC.setCoutPoutres(coutPoutre);
    }

    public void getCoutQuantiteLongueurPoteaux(){
        int nbPoteaux = gabaritPC.getNbrPortee() *
                gabaritPC.getNbrPoteauxParPoutre();
        double coutPoteaux = nbPoteaux * gabaritPC.getLongueurPoteaux()/pouceParPied;
        coutPoteaux = coutPoteaux * gabaritPC.getTypePoteaux().getCoutLineaire();
        gabaritPC.setCoutPoteaux(coutPoteaux);
    }

    public void getCoutTotal(){
        double coutTotal = gabaritPC.getCoutPlanches() + gabaritPC.getCoutPoutres() + gabaritPC.getCoutPoteaux() + gabaritPC.getCoutSolivesPAF()+ gabaritPC.getCoutSolives();
        gabaritPC.setCoutPatioAvecPoteaux(coutTotal);
    }

    public String export3DFileToSTL() {
        String bufferStlObj = "";
        int nombrePiece = listPiece.size();
        for (Piece piece: listPiece) {
            bufferStlObj = bufferStlObj + this.getFileToSTLString(piece,nombrePiece);
            nombrePiece--;
        }
        return bufferStlObj;
    }

    private String getBegeningFileToSTLString(int nombrePiece) {
        return "solid piece" + nombrePiece + "\n facet normal -1.0 -1.0 -1.0 0\n outer loop\n";
    }

    private String getEndFileToSTLString(int nombrePiece) {
        return  "endsolid\n";
    }

    public String getFileToSTLString(Piece piece,int nombrePiece) {
        String bufferStlObj = "";
        bufferStlObj = this.getBegeningFileToSTLString(nombrePiece);
        Emplacement emplacement = piece.getEmplacement();
        double epaisseurBois = piece.getTypeBois().getEpaisseurReelle();
        double longueurPiece = piece.getLongueur();
        double largeaurBois = piece.getTypeBois().getLargeurReelle();
        //tringle1 derrière 1
        double v1x = emplacement.getX();
        double v1y = emplacement.getY();
        double v1z = -emplacement.getZ();
        double v2x = v1x;
        double v2y = v1y;
        double v2z = 0.0;
        double v3x = 0.0;
        if( piece.getTypePiece() == Piece.TypePiece.Poteau){
            v2z = v1z - longueurPiece;
            v3x = v1x + epaisseurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Plis){
            v2z = v1z - largeaurBois;
            v3x = v1x + epaisseurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Solive){
            v2z = v1z - largeaurBois;
            v3x = v1x + longueurPiece;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Planche){
            v2z = v1z - epaisseurBois;
            v3x = v1x + largeaurBois;
        }
        double v3y = v1y;
        double v3z = v1z;
        bufferStlObj = bufferStlObj + this.getSTLString(v1x,v1y,v1z,v2x,v2y,v2z,v3x,v3y,v3z);
        //tringle2 derrière 2
        v1x = v3x;
        v1y = v3y;
        if( piece.getTypePiece() == Piece.TypePiece.Poteau){
            v1z = v3z - longueurPiece;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Plis){
            v1z = v3z - largeaurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Solive){
            v1z = v3z - largeaurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Planche){
            v1z = v3z - epaisseurBois;
        }
        bufferStlObj = bufferStlObj + this.getSTLString(v1x,v1y,v1z,v2x,v2y,v2z,v3x,v3y,v3z);

        //triangle1 coté droite 3
        v2x = v1x;
        if( piece.getTypePiece() == Piece.TypePiece.Poteau){
            v2y = v1y + epaisseurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Plis){
            v2y = v1y + longueurPiece;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Solive){
            v2y = v1y + epaisseurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Planche){
            v2y = v1y + longueurPiece;
        }
        v2z = v1z;
        bufferStlObj = bufferStlObj + this.getSTLString(v1x,v1y,v1z,v2x,v2y,v2z,v3x,v3y,v3z);

        //triangle2 coté droite 4
        v1x = v2x;
        v1y = v2y;
        if( piece.getTypePiece() == Piece.TypePiece.Poteau){
            v1z = v2z + longueurPiece;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Plis){
            v1z = v2z + largeaurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Solive){
            v1z = v2z + largeaurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Planche){
            v1z = v2z + epaisseurBois;
        }
        bufferStlObj = bufferStlObj + this.getSTLString(v1x,v1y,v1z,v2x,v2y,v2z,v3x,v3y,v3z);

        //triangle1 dessus 5
        if( piece.getTypePiece() == Piece.TypePiece.Poteau){
            v2x = v1x - epaisseurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Plis){
            v2x = v1x - epaisseurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Solive){
            v2x = v1x - longueurPiece;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Planche){
            v2x = v1x - largeaurBois;
        }
        v2y = v1y;
        v2z = v1z;
        bufferStlObj = bufferStlObj + this.getSTLString(v1x,v1y,v1z,v2x,v2y,v2z,v3x,v3y,v3z);

        //triangle2 dessus 6
        if( piece.getTypePiece() == Piece.TypePiece.Poteau){
            v1x = v3x - epaisseurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Plis){
            v1x = v3x - epaisseurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Solive){
            v1x = v3x - longueurPiece;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Planche){
            v1x = v3x - largeaurBois;
        }
        v1y = v3y;
        v1z = v3z;
        bufferStlObj = bufferStlObj + this.getSTLString(v1x,v1y,v1z,v2x,v2y,v2z,v3x,v3y,v3z);

        //triangle1 gauche 7
        v3x = v2x;
        v3y = v2y;
        if( piece.getTypePiece() == Piece.TypePiece.Poteau){
            v3z = v2z - longueurPiece;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Plis){
            v3z = v2z - largeaurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Solive){
            v3z = v2z - largeaurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Planche){
            v3z = v2z - epaisseurBois;
        }
        bufferStlObj = bufferStlObj + this.getSTLString(v1x,v1y,v1z,v2x,v2y,v2z,v3x,v3y,v3z);

        //triangle2 gauche 8
        v2x = v1x;
        v2y = v1y;
        if( piece.getTypePiece() == Piece.TypePiece.Poteau){
            v2z = v1z - longueurPiece;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Plis){
            v2z = v1z - largeaurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Solive){
            v2z = v1z - largeaurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Planche){
            v2z = v1z - epaisseurBois;
        }
        bufferStlObj = bufferStlObj + this.getSTLString(v1x,v1y,v1z,v2x,v2y,v2z,v3x,v3y,v3z);

        //triangle1 dessous 9
        if( piece.getTypePiece() == Piece.TypePiece.Poteau){
            v1x = v2x + epaisseurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Plis){
            v1x = v2x + epaisseurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Solive){
            v1x = v2x + longueurPiece;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Planche){
            v1x = v2x + largeaurBois;
        }
        v1y = v2y;
        v1z = v2z ;
        bufferStlObj = bufferStlObj + this.getSTLString(v1x,v1y,v1z,v2x,v2y,v2z,v3x,v3y,v3z);

        //triangle2 dessous 10
        if( piece.getTypePiece() == Piece.TypePiece.Poteau){
            v2x = v3x + epaisseurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Plis){
            v2x = v3x + epaisseurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Solive){
            v2x = v3x + longueurPiece;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Planche){
            v2x = v3x + largeaurBois;
        }
        v2y = v3y;
        v2z = v3z;
        bufferStlObj = bufferStlObj + this.getSTLString(v1x,v1y,v1z,v2x,v2y,v2z,v3x,v3y,v3z);

        //triangle1 devant 11
        v1x = v2x;
        v1y = v2y;
        if( piece.getTypePiece() == Piece.TypePiece.Poteau){
            v1z = v2z + longueurPiece;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Plis){
            v1z = v2z + largeaurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Solive){
            v1z = v2z + largeaurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Planche){
            v1z = v2z + epaisseurBois;
        }
        bufferStlObj = bufferStlObj + this.getSTLString(v1x,v1y,v1z,v2x,v2y,v2z,v3x,v3y,v3z);

        //triangle2 devant 12
        v2x = v3x;
        v2y = v3y;
        if( piece.getTypePiece() == Piece.TypePiece.Poteau){
            v2z = v3z + longueurPiece;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Plis){
            v2z = v3z + largeaurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Solive){
            v2z = v3z + largeaurBois;
        }
        if( piece.getTypePiece() == Piece.TypePiece.Planche){
            v2z = v3z + epaisseurBois;
        }
        bufferStlObj = bufferStlObj + this.getSTLString(v1x,v1y,v1z,v2x,v2y,v2z,v3x,v3y,v3z);
        bufferStlObj = bufferStlObj + getEndFileToSTLString(nombrePiece);
        return bufferStlObj;
    }

    private String getSTLString(Double v1x,Double v1y,Double v1z,Double v2x,Double v2y,Double v2z,Double v3x,Double v3y,Double v3z) {
        String bufferStlString = "  vertex " + v1x + " " + v1y + " " + v1z + " \n ";
        bufferStlString = bufferStlString + " vertex " + v2x + " " + v2y + " " + v2z + " \n ";
        bufferStlString = bufferStlString + " vertex " + v3x + " " + v3y + " " + v3z + " \n ";
        bufferStlString = bufferStlString + "endloop\n endfacet\n";

        return bufferStlString;
    }

}