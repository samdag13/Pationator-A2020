package domaine;
import java.awt.*;
import java.util.ArrayList;

public class Poutre {

    private int m_nbrPlis;
    private int m_nbrPoteaux;

    private boolean m_transparencePoutres;
    private boolean m_visibilitePoutres;
    private boolean m_transparencePoteaux;
    private boolean m_visibilitePoteaux;
    private boolean m_porteeDouble = false;
    private boolean m_ancrerMaison;

    private Color m_colorPoutres;
    private Color m_colorPoteaux;

    private double m_longueurPoteaux;
    private double m_longueurPoutre;
    private double m_nbrPoutreLargeur;
    private int m_nbrPoteauxParPoutre;

    private Emplacement m_emplacementPoutre;

    private  ArrayList<Piece> m_listePlis = new ArrayList<>();
    private  ArrayList<Piece> m_listePoteaux = new ArrayList<>();

    private TypeBois m_typePlanche;
    private TypeBois m_typeSolive;
    private TypeBois m_typePlis;
    private TypeBois m_typePoteaux;
    // Constructeur

    public Poutre(){}

    public Poutre(int nbrPlis, TypeBois typePlis, TypeBois typeSolive, TypeBois typePoteaux, double longueurPoteaux, double longueurPoutre,
                  boolean ancrageMaison,boolean visibilitePoutre, boolean transparencePoutre, boolean visibilitePoteaux,
                  boolean transparencePoteaux, TypeBois typePlanche, Color colorPoutres, Color colorPoteaux,double nbrPoutreLargeur,int nbrPoteauxParPoutre ) {

        m_nbrPlis = nbrPlis;
        m_ancrerMaison = ancrageMaison;
        m_longueurPoteaux = longueurPoteaux;
        m_typePlis = typePlis;
        m_longueurPoutre = longueurPoutre;
        m_typePoteaux = typePoteaux;
        m_visibilitePoutres = visibilitePoutre;
        m_transparencePoutres = transparencePoutre;
        m_visibilitePoteaux = visibilitePoteaux;
        m_transparencePoteaux = transparencePoteaux;
        m_colorPoutres = colorPoutres;
        m_colorPoteaux = colorPoteaux;
        m_typePlanche = typePlanche;
        m_typeSolive = typeSolive;
        m_nbrPoutreLargeur = nbrPoutreLargeur;
        m_nbrPoteauxParPoutre = nbrPoteauxParPoutre;
    }



    // Création des poteaux supportant la poutre et méthodes nécessaire à la création
    public void creerPoteaux(int nbrPoteauxParPoutre) {

        if(!m_ancrerMaison & !m_porteeDouble && nbrPoteauxParPoutre ==0){
                Poteau poteau1 = new Poteau(m_typePoteaux, m_longueurPoteaux, calculEmplacementPoteau1(),
                        Piece.TypePiece.Poteau, Piece.Orientation.HautBas, m_transparencePoteaux, m_visibilitePoteaux, m_colorPoteaux);
                m_listePoteaux.add(poteau1);

        }else if (!m_ancrerMaison & !m_porteeDouble){
            Poteau poteau2 = new Poteau(m_typePoteaux, m_longueurPoteaux, calculEmplacementPoteau2(nbrPoteauxParPoutre),
                    Piece.TypePiece.Poteau, Piece.Orientation.HautBas, m_transparencePoteaux, m_visibilitePoteaux, m_colorPoteaux);
            m_listePoteaux.add(poteau2);

/*
                if (m_nbrPoteaux > 2){
                    for(int i = m_nbrPoteaux-2; i <= m_nbrPoteaux; i++){
                        Poteau poteauNonExtremite = new Poteau(m_typePoteaux, m_longueurPoteaux, calculPoteauxNonExtremite(i),
                                Piece.TypePiece.Poteau, Piece.Orientation.HautBas, m_transparencePoteaux, m_visibilitePoteaux, m_colorPoteaux);
                        m_listePoteaux.add(poteauNonExtremite);
                    }

                }

 */
        }


    }

    public void creerPoteauxPlusUnePoutreLargeur(int nbrPoteauxParPoutre) {

        if(!m_ancrerMaison & !m_porteeDouble){
            Poteau poteau3 = new Poteau(m_typePoteaux, m_longueurPoteaux, calculEmplacementPoteau3(nbrPoteauxParPoutre),
                    Piece.TypePiece.Poteau, Piece.Orientation.HautBas, m_transparencePoteaux, m_visibilitePoteaux, m_colorPoteaux);
            m_listePoteaux.add(poteau3);
        }
    }

    private Emplacement calculEmplacementPoteau1(){

        double x,y,z;
        Emplacement nouveauPoteau1 = new Emplacement();
        double demiDifferencePoteauPoutre = (m_typePoteaux.getLargeurReelle() - (m_nbrPlis*m_typePlis.getEpaisseurReelle()))/2.0;
        x =  m_emplacementPoutre.getX() - demiDifferencePoteauPoutre;
        y =  0;
        double epaisseurStructureSurPoteau = m_typePlanche.getEpaisseurReelle() + m_typeSolive.getLargeurReelle()
                + m_typePlis.getLargeurReelle();
        z = epaisseurStructureSurPoteau;
        nouveauPoteau1.setXYZ(x,y,z);
        return nouveauPoteau1;
    }

    private Emplacement calculEmplacementPoteau2(int nbrPoteauxParPoutre){

        double x,y,z;
        Emplacement nouveauPoteau2 = new Emplacement();
        double demiDifferencePoteauPoutre = (m_typePoteaux.getLargeurReelle() - (m_nbrPlis*m_typePlis.getEpaisseurReelle()))/2.0;
        x =  m_emplacementPoutre.getX() - demiDifferencePoteauPoutre;
        y = (m_longueurPoutre/(m_nbrPoteauxParPoutre-1))*nbrPoteauxParPoutre;
        double epaisseurStructureSurPoteau = m_typePlanche.getEpaisseurReelle() + m_typeSolive.getLargeurReelle()
                + m_typePlis.getLargeurReelle();
        z = epaisseurStructureSurPoteau;
        nouveauPoteau2.setXYZ(x,y,z);
        return nouveauPoteau2;
    }

    private Emplacement calculEmplacementPoteau3(int nbrPoteauxParPoutre){

        double x,y,z;
        Emplacement nouveauPoteau2 = new Emplacement();
        double coordY = (m_longueurPoutre*(m_nbrPoutreLargeur+1));
        double demiDifferencePoteauPoutre = (m_typePoteaux.getLargeurReelle() - (m_nbrPlis*m_typePlis.getEpaisseurReelle()))/2.0;
        x =  m_emplacementPoutre.getX() - demiDifferencePoteauPoutre;
        y = coordY-(m_longueurPoutre/(m_nbrPoteauxParPoutre-1))*nbrPoteauxParPoutre;
        double epaisseurStructureSurPoteau = m_typePlanche.getEpaisseurReelle() + m_typeSolive.getLargeurReelle()
                + m_typePlis.getLargeurReelle();
        z = epaisseurStructureSurPoteau;
        nouveauPoteau2.setXYZ(x,y,z);
        return nouveauPoteau2;
    }


    // Création des plis de la poutre et méthodes nécessaire à la création
    public void creerPlis(){

        for (int i=0; i<m_nbrPlis; i++){
            Plis nouveauPlis = new Plis(m_typePlis, m_longueurPoutre, calculEmplacementPlis(i), Piece.TypePiece.Plis,
                    Piece.Orientation.NordSud, m_transparencePoutres, m_visibilitePoutres, m_colorPoutres);
            m_listePlis.add(nouveauPlis);
        }
    }

    public Emplacement calculEmplacementPlis(int indexePlis){;
        Emplacement nouveauPlis = new Emplacement();
        double x = m_emplacementPoutre.getX() + (indexePlis*m_typePlis.getEpaisseurReelle());
        if(indexePlis==1){
            x = m_emplacementPoutre.getX() + m_typePlis.getEpaisseurReelle();
        }
        else if(indexePlis == 2){
            x = m_emplacementPoutre.getX() + (m_typePlis.getEpaisseurReelle()+m_typePlis.getEpaisseurReelle());
        }
        nouveauPlis.setXYZ(x, m_emplacementPoutre.getY(), m_emplacementPoutre.getZ());
        return nouveauPlis;
    }



    // Getter


    public boolean getPorteeDouble(){ return m_porteeDouble; }


    public Emplacement getEmplacementPoutre() {
        return m_emplacementPoutre;
    }

    public TypeBois getTypePlis() {
        return m_typePlis;
    }

    public ArrayList<Piece> getListePlis(){
        return m_listePlis;
    }

    public ArrayList<Piece> getListePoteaux() {
        return m_listePoteaux;
    }

    //Setter
    public void setPorteeDouble(){

        if (!m_porteeDouble){
            m_porteeDouble = true;
        }
    }

    public void setEmplacementPoutre(Emplacement emplacementPoutre){m_emplacementPoutre = emplacementPoutre;}


    // Méthodes Utilitaires
    public boolean validerPoutre(){
        if (m_ancrerMaison & m_listePoteaux.size() == 0 & m_listePlis.size()>0){
            return true;
        }
        else if(!m_ancrerMaison & m_listePoteaux.size()>0 & m_listePlis.size()>0){
            return true;
        }

    return false;
    }
}
