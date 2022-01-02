package domaine;

import java.awt.*;
import java.util.ArrayList;

public class Portee {

    private double m_longueurPAFaux;
    private double m_espacementSolives;
    private double m_longueurSolives;
    private double m_longueurPoutre;
    private double m_nbrPoutreLargeur;

    private Color m_colorSolives;

    private int m_nombreSolives;
    private int m_idPortee;
    private int  m_nbrPoteauxParPoutre;

    private boolean m_visibiliteSolives;
    private boolean m_transparenceSolives;

    private ArrayList<Piece> m_listeSolives = new ArrayList<>();

    private Poutre m_poutre1 = null; // est la poutre la plus près de la maison parmis les 2 poutres de la portée.
    private Poutre m_poutre2 = null; // est la poutre la plus éloigné de la maison parmis les 2 poutres de la portée

    private TypeBois m_typeSolives;
    private TypeBois m_typePlanche;
    private TypeBois m_typePlis;
    private TypeBois m_typePoteaux;

    // Constructeur avec le nombre de solives spécifié
    public Portee( TypeBois typePlis, TypeBois typePoteaux, double longueurPoutre, double espacementSolives, int nombreSolives,
                   double longueurPortee, TypeBois typeSolives, boolean visibiliteSolives, boolean transparenceSolives,
                   int idPortee, TypeBois typePlanche, Color colorSolives, boolean porteeAncrer, double longueurPAFaux,
                   double nbrPoutreLargeur, int nbrPoteauxParPoutre){

        m_espacementSolives = espacementSolives;
        m_longueurSolives = longueurPortee;
        m_typeSolives = typeSolives;
        m_visibiliteSolives = visibiliteSolives;
        m_transparenceSolives = transparenceSolives;
        m_nombreSolives = nombreSolives;
        m_idPortee = idPortee;
        m_longueurPoutre = longueurPoutre;
        m_typePlanche = typePlanche;
        m_colorSolives = colorSolives;
        m_typePlis = typePlis;
        m_typePoteaux = typePoteaux;
        m_longueurPAFaux = longueurPAFaux;
        m_nbrPoutreLargeur = nbrPoutreLargeur;
        m_nbrPoteauxParPoutre = nbrPoteauxParPoutre;

    }
/*
    //Constructeur avec le nombre de solives non spécifié
    public Portee( TypeBois typePlis, TypeBois typePoteaux, double longueurPoutre, double espacementSolives, double longueurPortee, TypeBois typeSolives,
                  boolean visibiliteSolives, boolean transparenceSolives, int idPortee, TypeBois typePlanche, Color colorSolives, boolean porteeAncrer, double longueurPAFaux, double nbrPoutreLargeur){

        m_espacementSolives = espacementSolives;
        m_longueurSolives = longueurPortee;
        m_typeSolives = typeSolives;
        m_visibiliteSolives = visibiliteSolives;
        m_transparenceSolives = transparenceSolives;
        m_idPortee = idPortee;
        m_longueurPoutre = longueurPoutre;
        m_typePlanche = typePlanche;
        m_colorSolives = colorSolives;
        m_typePlis = typePlis;
        m_porteeAncrer = porteeAncrer;
        m_typePoteaux = typePoteaux;
        m_longueurPAFaux = longueurPAFaux;
        m_nbrPoutreLargeur = nbrPoutreLargeur;
        this.setNombreSolivesParPortee();
    }
*/



    // Création des poutres et méthodes nécessaire à la création;
    public void creerPoutre1(int nbrPlis, double longueurPoteaux, boolean visibilitePoutre,
                             boolean transparencePoutre, boolean visibilitePoteaux,
                             boolean transparencePoteaux, Color colorPoutres, Color colorPoteaux,int nbrPoutreLargeur){



        m_poutre1 = new Poutre(nbrPlis, m_typePlis, m_typeSolives, m_typePoteaux, longueurPoteaux, m_longueurPoutre, true,
                visibilitePoutre, transparencePoutre, visibilitePoteaux, transparencePoteaux, m_typePlanche,
                colorPoutres, colorPoteaux,nbrPoutreLargeur,m_nbrPoteauxParPoutre);
        m_poutre1.setEmplacementPoutre(this.calculEmplacementPoutre1(m_typePoteaux, nbrPlis, nbrPoutreLargeur));
        m_poutre1.creerPlis();

    }
    public void creerPoutre1(Poutre poutre1PorteeDouble, int nbrPlis,int nbrPoutreLargeur){
            m_poutre1 = poutre1PorteeDouble;

            m_poutre1.setEmplacementPoutre(this.calculEmplacementPoutre1PorteeDouble(m_typePoteaux, nbrPlis, nbrPoutreLargeur));

    }


    public void creerPoutre2(int nbrPlis, double longueurPoteaux, boolean visibilitePoutre,
                             boolean transparencePoutre, boolean visibilitePoteaux,
                             boolean transparencePoteaux, Color colorPoutres, Color colorPoteaux, int nbrPoutreLargeur){

            m_poutre2 = new Poutre(nbrPlis, m_typePlis, m_typeSolives, m_typePoteaux, longueurPoteaux, m_longueurPoutre, false,
                    visibilitePoutre, transparencePoutre, visibilitePoteaux, transparencePoteaux, m_typePlanche,
                    colorPoutres, colorPoteaux, nbrPoutreLargeur, m_nbrPoteauxParPoutre);
            m_poutre2.setEmplacementPoutre(this.calculEmplacementPoutre2(m_typePoteaux, nbrPlis, nbrPoutreLargeur));
            if (nbrPoutreLargeur > 0){
                m_poutre2.creerPlis();
                for (int  i=0; i<m_nbrPoteauxParPoutre-1; i++){
                    m_poutre2.creerPoteauxPlusUnePoutreLargeur(i);
                }
            }else{
                m_poutre2.creerPlis();
                for (int  i=0; i<m_nbrPoteauxParPoutre; i++){
                    m_poutre2.creerPoteaux(i);
                }
            }
    }

    public Emplacement calculEmplacementPoutre1(TypeBois typePoteau,int nbrPlis, int nbrPoutreLargeur){
        Emplacement nouvellePoutre = new Emplacement();
        double x,y,z;
        x = 0;
        z = m_typeSolives.getLargeurReelle() + m_typePlanche.getEpaisseurReelle();
        y = typePoteau.getEpaisseurReelle()/2 + (nbrPoutreLargeur*m_longueurPoutre);

        nouvellePoutre.setXYZ(x,y,z);

        return nouvellePoutre;
    }

    public Emplacement calculEmplacementPoutre2(TypeBois typePoteau,int nbrPlis, int nbrPoutreLargeur){
        Emplacement nouvellePoutre = new Emplacement();
        double x,y,z;
        x = (m_idPortee+1) * ((m_longueurSolives) - (nbrPlis*m_typePlis.getEpaisseurReelle()));
        z = m_typeSolives.getLargeurReelle() + m_typePlanche.getEpaisseurReelle();
        y = typePoteau.getEpaisseurReelle()/2 + (nbrPoutreLargeur*m_longueurPoutre);
        nouvellePoutre.setXYZ(x,y,z);

        return nouvellePoutre;
    }

    public Emplacement calculEmplacementPoutre1PorteeDouble(TypeBois typePoteau,int nbrPlis, int nbrPoutreLargeur){
        Emplacement nouvellePoutre = new Emplacement();
        double x,y,z;
        x = ((m_longueurSolives)-(nbrPlis*m_typePlis.getEpaisseurReelle()));
        z = m_typeSolives.getLargeurReelle() + m_typePlanche.getEpaisseurReelle();
        y = typePoteau.getEpaisseurReelle()/2 * (nbrPoutreLargeur*m_longueurPoutre);

        nouvellePoutre.setXYZ(x,y,z);

        return nouvellePoutre;
    }




    //Création des Solives et méthodes nécessaire à la création
    public void creerSolives(){
        for (int i = 0 ; i < m_nombreSolives; i++){
            Solive nouvelleSolive = new Solive(m_typeSolives, m_longueurSolives + m_longueurPAFaux, calculEmplacementSolive(),
                    Piece.TypePiece.Solive, Piece.Orientation.EstOuest, m_transparenceSolives, m_visibiliteSolives, m_colorSolives);
            m_listeSolives.add(nouvelleSolive);
        }
        /*Emplacement derniereSolive = new Emplacement();
        double x,y,z;


        if(m_idPortee%2 == 0) {
            x = m_poutre1.getEmplacementPoutre().getX()* m_idPortee;
            y = m_longueurPoutre + m_typePoteaux.getEpaisseurReelle()/2 - (2*m_typeSolives.getEpaisseurReelle()) ;
            z = m_typePlanche.getEpaisseurReelle();
            derniereSolive.setXYZ(x,y,z);
            Solive nouvelleSolive = new Solive(m_typeSolives, m_longueurSolives, derniereSolive,
                    Piece.TypePiece.Solive, Piece.Orientation.EstOuest, m_transparenceSolives, m_visibiliteSolives, m_colorSolives);
            m_listeSolives.add(nouvelleSolive);
        }
        else{
            x = (m_poutre1.getEmplacementPoutre().getX()) * m_idPortee;
            y = m_longueurPoutre + m_typePoteaux.getEpaisseurReelle()/2 - m_typeSolives.getEpaisseurReelle();
            z = m_typePlanche.getEpaisseurReelle();
            derniereSolive.setXYZ(x,y,z);
            Solive nouvelleSolive = new Solive(m_typeSolives, m_longueurSolives, derniereSolive,
                    Piece.TypePiece.Solive, Piece.Orientation.EstOuest, m_transparenceSolives, m_visibiliteSolives, m_colorSolives);
            m_listeSolives.add(nouvelleSolive);
        }*/
    }

    public Emplacement calculEmplacementSolive(){

        Emplacement nouvelleSolive = new Emplacement();
        double y;
        double x;
        double z = m_typePlanche.getEpaisseurReelle();

        if((m_idPortee%2) == 0){
            y = m_typePoteaux.getEpaisseurReelle()/2.0 + ((m_espacementSolives+m_typeSolives.getEpaisseurReelle()) * m_listeSolives.size());
            x = (m_poutre1.getEmplacementPoutre().getX())*m_idPortee;
        }
        else{
            y = m_typePoteaux.getEpaisseurReelle()/2.0 + m_typeSolives.getEpaisseurReelle()
                    + ((m_espacementSolives+m_typeSolives.getEpaisseurReelle()) * m_listeSolives.size());
            x = (m_poutre1.getEmplacementPoutre().getX())*m_idPortee;
        }

        nouvelleSolive.setXYZ(x,y,z);
        return nouvelleSolive;
    }


    //Setter

    public void setLongueurPAFaux(double longueurPAFaux){
        m_longueurPAFaux = longueurPAFaux;
    }

    //Getter

    public Poutre getPoutre2() {
        return m_poutre2;
    }



    // On veut la liste de Piece de Poutre1, Poutre2 et listeSolives.

    public ArrayList<Piece> getListePoteaux(){
        ArrayList<Piece> listePiece = new ArrayList<>();
        listePiece.addAll(m_poutre1.getListePoteaux());
        listePiece.addAll(m_poutre2.getListePoteaux());
       return listePiece;
    }

    public ArrayList<Piece> getListePlis(){
        ArrayList<Piece> listePiece = new ArrayList<>();
        listePiece.addAll(m_poutre1.getListePlis());
        listePiece.addAll(m_poutre2.getListePlis());
        return listePiece;
    }

    public ArrayList<Piece> getListeSolives(){
        return m_listeSolives;
    }

    public Emplacement getEmplacementPourPlanches(){
        Emplacement debutPlanches = m_poutre1.getEmplacementPoutre();
        double x,y, z;
        x = m_poutre1.getEmplacementPoutre().getX();
        y = m_poutre1.getEmplacementPoutre().getY();
        z = 0;
        debutPlanches.setXYZ(x,y,z);
        return debutPlanches;
    }


    //Méthodes utilitaires
    public boolean valider(){

        if(m_poutre1.validerPoutre() & m_poutre2.validerPoutre()){
            if (m_listeSolives.size()>0){
                return true;
            }
        }
        return false;
    }

    public void creerPieceAuBoutDuPatio(){
        Emplacement position = new Emplacement();
        double longueurPiece = ((m_nombreSolives-1)*m_espacementSolives) + (m_nombreSolives*m_typeSolives.getEpaisseurReelle());
        Solive plancheDuBout = new Solive(m_typeSolives,longueurPiece, position, Piece.TypePiece.Solive,
                Piece.Orientation.NordSud, m_transparenceSolives, m_visibiliteSolives, m_colorSolives);
        int finDeListe = m_listeSolives.size()-1;
        m_listeSolives.add(plancheDuBout);

        double x,y,z;
        if(m_idPortee%2==0 && m_idPortee>0){
            x = (m_poutre1.getEmplacementPoutre().getX()*(m_idPortee) + m_longueurSolives + m_longueurPAFaux);
            y = m_poutre1.getEmplacementPoutre().getY()+ m_typeSolives.getEpaisseurReelle();
            //toutes les solives on le même z
            z = m_listeSolives.get(0).getEmplacement().getZ();

        } else if(m_idPortee == 0 && m_idPortee%2==0){
            x = (m_poutre1.getEmplacementPoutre().getX() + m_longueurSolives)*(m_idPortee+1)+ m_longueurPAFaux;
            y = m_poutre1.getEmplacementPoutre().getY();
            //toutes les solives on le même z
            z = m_listeSolives.get(0).getEmplacement().getZ();
        }
        else{
            x = (m_poutre1.getEmplacementPoutre().getX()*(m_idPortee) + m_longueurSolives + m_longueurPAFaux);
            y = m_poutre1.getEmplacementPoutre().getY()+(2.15*m_typeSolives.getEpaisseurReelle());
            //toutes les solives on le même z
            z = m_listeSolives.get(0).getEmplacement().getZ();
        }

        plancheDuBout.setEmplacement(x,y,z);
    }
}

