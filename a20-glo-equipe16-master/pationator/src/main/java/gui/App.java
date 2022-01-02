package gui;

import Afficheur.AffichagePatio;
import Afficheur.Vues2d;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import domaine.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.*;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class App extends JFrame {

    private JPanel panelMain;
    public PatioControleur controleur;
    private JTabbedPane onglets;
    private JPanel ongletSolives;
    private JPanel ongletPoutres;
    private JPanel ongletRecouvrement;
    private JPanel ongletMateriaux;
    private JTextField txtFormatPoteau;
    private JTextField txtQuantitePoteau;
    private JTextField txtCoutPoteau;
    private JTextField txtFormatPoutre;
    private JTextField txtQuantitePoutre;
    private JTextField txtCoutPoutre;
    private JTextField txtFormatSolive;
    private JTextField txtQuantiteSolive;
    private JTextField txtCoutSolive;
    private JTextField txtFormatPlanche;
    private JTextField txtQuantitePlanche;
    private JTextField txtCoutPlanche;
    private JTextField txtCoutTotal;
    private JTextField txtLongueurSolive;
    private JComboBox<Object> txtFormatOngletSolive;
    private JTextField txtEspacementSolive;
    private JTextField txtQuantiteOngletSolive;
    private JRadioButton radSoliveTransparence;
    private JRadioButton radSoliveCache;
    private JRadioButton radPoutreTransparence;
    private JRadioButton radPoutreCache;
    private JTextField txtLongueurPoutre;
    private JComboBox<Object> txtFormatOngletPoutre;
    private JTextField txtQuantiteOngletPoutre;
    private JRadioButton radioTransparenceRecouvrement;
    private JRadioButton radRecouvrementCache;
    private JComboBox<Object> txtFormatRecouvrement;
    private JTextField txtEspacementRecouvrement;
    private JTextField txtQuantiteRecouvrement;
    private JMenu menuFichier;
    private JMenuBar barreMenu;
    private JMenuItem sousMenuSauvegarder;
    private JMenuItem sousMenuCharger;
    private JMenuItem sousMenuExporter2d;
    private JMenuItem sousMenuExporter3d;
    private JMenu menuPrixMateriaux;
    private JMenu menuVue;
    private JMenuItem sousMenuVuePlan;
    private JMenuItem sousMenuVueCote;
    private JMenuItem sousMenuVueFace;
    private JTextField txtLongueurPatio;
    private JTextField txtLargeurPatio;
    private JTextField txtHauteurPatio;
    private JButton boutonMagique;
    private JButton boutonUndo;
    private JButton boutonRedo;
    private JPanel panelPatio;
    public PanelDessin panelDessin;
    public JTextField txtPorteAFaux;
    public JComboBox txtChoixCouleurRecouvrement;
    public JComboBox txtChoixFormatRecouvrement;
    public JLabel Choixcouleur;
    public JLabel choixCouleurRecouvrement;
    public JComboBox txtChoixCouleurPoutres;
    public JComboBox txtChoixFormatPoutres;
    public JComboBox txtChoixEspacementPoutres;
    public JComboBox txtChoixCouleurSolives;
    public JComboBox txtChoixFormatSolives;
    public JLabel choixCouleurSolives1;
    public JComboBox txtFormatPoteaux;
    public JRadioButton transparencePoteaux;
    public JRadioButton visibiliteePoteaux;
    public JComboBox txtChoixCouleurPoteaux;
    public JCheckBox validitePatio;
    public JTextField txtNbrSection;
    private JPanel panelOnglet;
    private JPanel panelInfoPiece;
    private JLabel lblTitrePanelInfo;
    private JLabel lblTypePiece;
    private JLabel lblFormatPiece;
    private JLabel lblLongueurPiece;
    private JLabel lblPrixInfoPiece;
    private JLabel lblInfoSupp;
    public JTextField txtHauteurPoteaux;
    public JPanel ongletPoteaux;
    public JLabel lblNbrPlis;
    public JComboBox txtNbrPlis;
    public JLabel txtMsgErreur;
    public JPanel panelErreur;
    public JTextField txtNbrPoteauxParPoutre;
    public JMenuItem sousMenuListePieces;
    public JTextField txtLongueurPoutresListe;
    public JTextField txtLongueurSolivesListe;
    public JTextField txtLongueurPlanches;
    public JTextField txt;
    public JTextField txtLongueurSolivesListePAF;
    public JTextField txtQuantiteSolivePAF;
    public JTextField txtCoutSolivePAF;
    public JTextField txtFormatSolivePAF;
    public JTextField txtLongueurPoteauxListe;
    public JComboBox txtChoixFormatPoteaux;
    public JComboBox txtChoixEspacementPoteaux;
    public Gabarit gabarit;
    public MesureImperiale mesureImperiale;
    public ListePrix listePrix;
    public App app = this;


    public App(String title) throws IOException {
        super(title);
        panelDessin = new PanelDessin(this);
        controleur = new PatioControleur();
        $$$setupUI$$$();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelMain);
        this.pack();
        remplirLesComboBox();
        preSelectionnerValeurSauvegarde();
        ajouterLesListeners();
        setBoutons();
        setDimensionsPatio();
        setListePrix();


    }

    //txtNbrSection
    public PatioControleur getControleur() {
        return controleur;
    }


    public void checkValiditePatio() {
        boolean variableValiditee = controleur.getGabaritPC().isValiditeePatio();
        if (controleur.getGabaritPC().isValiditeePatio()) {
            validitePatio.setSelected(true);
            panelDessin.repaint();
            refreshListeMateriaux();
            //controleur.saveGabaritValide();
        } else {
            validitePatio.setSelected(false);
            panelDessin.repaint();
            refreshListeMateriaux();
        }
    }

    public void refreshListeMateriaux() {
        setDimensionsPatio();
        setListePrix();
    }


    public void setBoutons() {
        Color couleurPlanches = controleur.getGabaritPC().getColorPlanches();
        if (couleurPlanches == Color.black) {
            txtChoixCouleurRecouvrement.setSelectedIndex(3);
        } else if (couleurPlanches == Color.red) {
            txtChoixCouleurRecouvrement.setSelectedIndex(0);
        } else if (couleurPlanches == Color.yellow) {
            txtChoixCouleurRecouvrement.setSelectedIndex(2);
        } else if (couleurPlanches == Color.green) {
            txtChoixCouleurRecouvrement.setSelectedIndex(5);
        } else if (couleurPlanches == Color.gray) {
            txtChoixCouleurRecouvrement.setSelectedIndex(4);
        } else if (couleurPlanches == Color.blue) {
            txtChoixCouleurRecouvrement.setSelectedIndex(1);
        }

        Color couleurPoteaux = controleur.getGabaritPC().getColorPoteaux();
        if (couleurPoteaux == Color.black) {
            txtChoixCouleurPoteaux.setSelectedIndex(3);
        } else if (couleurPoteaux == Color.red) {
            txtChoixCouleurPoteaux.setSelectedIndex(0);
        } else if (couleurPoteaux == Color.yellow) {
            txtChoixCouleurPoteaux.setSelectedIndex(2);
        } else if (couleurPoteaux == Color.green) {
            txtChoixCouleurPoteaux.setSelectedIndex(5);
        } else if (couleurPoteaux == Color.gray) {
            txtChoixCouleurPoteaux.setSelectedIndex(4);
        } else if (couleurPoteaux == Color.blue) {
            txtChoixCouleurPoteaux.setSelectedIndex(1);
        }

        Color couleurPoutres = controleur.getGabaritPC().getColorPoutres();
        if (couleurPoutres == Color.black) {
            txtChoixCouleurPoutres.setSelectedIndex(3);
        } else if (couleurPoutres == Color.red) {
            txtChoixCouleurPoutres.setSelectedIndex(0);
        } else if (couleurPoutres == Color.yellow) {
            txtChoixCouleurPoutres.setSelectedIndex(2);
        } else if (couleurPoutres == Color.green) {
            txtChoixCouleurPoutres.setSelectedIndex(5);
        } else if (couleurPoutres == Color.gray) {
            txtChoixCouleurPoutres.setSelectedIndex(4);
        } else if (couleurPoutres == Color.blue) {
            txtChoixCouleurPoutres.setSelectedIndex(1);
        }

        Color couleurSolives = controleur.getGabaritPC().getColorSolives();
        if (couleurSolives == Color.black) {
            txtChoixCouleurSolives.setSelectedIndex(3);
        } else if (couleurSolives == Color.red) {
            txtChoixCouleurSolives.setSelectedIndex(0);
        } else if (couleurSolives == Color.yellow) {
            txtChoixCouleurSolives.setSelectedIndex(2);
        } else if (couleurSolives == Color.green) {
            txtChoixCouleurSolives.setSelectedIndex(5);
        } else if (couleurSolives == Color.gray) {
            txtChoixCouleurSolives.setSelectedIndex(4);
        } else if (couleurSolives == Color.blue) {
            txtChoixCouleurSolives.setSelectedIndex(1);
        }

        TypeBois formatPlanches = controleur.getGabaritPC().getTypePlanches();
        if (formatPlanches == controleur.getGabaritPC().getTypeCinqQuartParSix()) {
            txtFormatRecouvrement.setSelectedIndex(0);
        } else if (formatPlanches == controleur.getGabaritPC().getTypeDeuxParSix()) {
            txtFormatRecouvrement.setSelectedIndex(1);
        }
        //2x4,2x6,2x8,2x10,2x12
        TypeBois formatPoutres = controleur.getGabaritPC().getTypePlis();
        if (formatPoutres == controleur.getGabaritPC().getTypeDeuxParQuatre()) {
            txtFormatOngletPoutre.setSelectedIndex(0);
        } else if (formatPoutres == controleur.getGabaritPC().getTypeDeuxParSix()) {
            txtFormatOngletPoutre.setSelectedIndex(1);
        } else if (formatPoutres == controleur.getGabaritPC().getTypeDeuxParHuit()) {
            txtFormatOngletPoutre.setSelectedIndex(2);
        } else if (formatPoutres == controleur.getGabaritPC().getTypeDeuxParDix()) {
            txtFormatOngletPoutre.setSelectedIndex(3);
        } else if (formatPoutres == controleur.getGabaritPC().getTypeDeuxParDouze()) {
            txtFormatOngletPoutre.setSelectedIndex(4);
        }

        TypeBois formatSolives = controleur.getGabaritPC().getTypeSolives();
        if (formatSolives == controleur.getGabaritPC().getTypeDeuxParQuatre()) {
            txtFormatOngletSolive.setSelectedIndex(0);
        } else if (formatSolives == controleur.getGabaritPC().getTypeDeuxParSix()) {
            txtFormatOngletSolive.setSelectedIndex(1);
        } else if (formatSolives == controleur.getGabaritPC().getTypeDeuxParHuit()) {
            txtFormatOngletSolive.setSelectedIndex(2);
        } else if (formatSolives == controleur.getGabaritPC().getTypeDeuxParDix()) {
            txtFormatOngletSolive.setSelectedIndex(3);
        } else if (formatSolives == controleur.getGabaritPC().getTypeDeuxParDouze()) {
            txtFormatOngletSolive.setSelectedIndex(4);
        }
        TypeBois formatPoteaux = controleur.getGabaritPC().getTypePoteaux();
        if (formatPoteaux == controleur.getGabaritPC().getTypeQuatreParQuatre()) {
            txtFormatPoteaux.setSelectedIndex(0);
        } else if (formatPoteaux == controleur.getGabaritPC().getTypeSixParSix()) {
            txtFormatPoteaux.setSelectedIndex(1);
        }

        if ((txtNbrPlis.getSelectedIndex() + 1) != controleur.getGabaritPC().getNbrPlis()) {
            txtNbrPlis.setSelectedIndex(controleur.getGabaritPC().getNbrPlis() - 1);
        }

        //Set du bouton plan comme "Enfoncé au début de l'application"
        sousMenuVuePlan.setBackground(new Color(190, 190, 190));
        sousMenuVuePlan.setEnabled(false);
    }


    public void setListePrix() {
        controleur.getCoutQuantiteLongueurSolives();
        controleur.getCoutQuantiteLongueurPlanches();
        controleur.getCoutQuantiteLongueurPoutres();
        controleur.getCoutQuantiteLongueurPoteaux();


        //Set des longueurs
        double longueurPoteaux = controleur.getGabaritPC().getLongueurPoteaux();
        double longueurPoutres = controleur.getGabaritPC().getLongueurPoutre();
        double longueurSolives = controleur.getGabaritPC().getLongueurSolives();
        double longueurPlanches = controleur.getGabaritPC().getLargeurPatio();
        double longueurSolivesPAF = controleur.getGabaritPC().getLongueurSolivesPAF();

        String longueurPoteauxStr = Double.toString(longueurPoteaux);
        String longueurSolivesStr = Double.toString(longueurSolives);
        String longueurSolivesPAFStr = Double.toString(longueurSolivesPAF);
        String longueurPlanchesStr = Double.toString(longueurPlanches);
        String longueurPoutreStr = Double.toString(longueurPoutres);

        txtLongueurPoutresListe.setText(longueurPoutreStr);
        txtLongueurSolivesListePAF.setText(longueurSolivesPAFStr);
        txtLongueurPoteauxListe.setText(longueurPoteauxStr);
        txtLongueurSolivesListe.setText(longueurSolivesStr);
        txtLongueurPlanches.setText(longueurPlanchesStr);

        //Set des couts

        double coutSolives = controleur.getGabaritPC().getCoutSolives();
        double coutSolivesPAF = controleur.getGabaritPC().getCoutSolivesPAF();
        double coutPlanches = controleur.getGabaritPC().getCoutPlanches();
        double coutPoteau = controleur.getGabaritPC().getCoutPoteaux();
        double coutPoutre = controleur.getGabaritPC().getCoutPoutres();


        String strCoutSolives = Double.toString(coutSolives);
        String strCoutSolivesPAF = Double.toString(coutSolivesPAF);
        txtCoutSolive.setText(strCoutSolives);
        txtCoutSolivePAF.setText(strCoutSolivesPAF);

        String strCoutPlanches = Double.toString(coutPlanches);
        txtCoutPlanche.setText(strCoutPlanches);

        String strCoutPoutre = Double.toString(coutPoutre);
        txtCoutPoutre.setText(strCoutPoutre);

        String strCoutPoteau = Double.toString(coutPoteau);
        txtCoutPoteau.setText(strCoutPoteau);

        controleur.getCoutTotal();
        double coutTotalAvecPoteau = controleur.getGabaritPC().getCoutPatioAvecPoteaux();
        controleur.setCoutPatioAvecPoteaux(coutTotalAvecPoteau);
        String strCoutTotalAvecPoteau = Double.toString(coutTotalAvecPoteau);
        txtCoutTotal.setText(strCoutTotalAvecPoteau);

        // Set des formats
        txtFormatSolive.setText(controleur.getGabaritPC().getTypeSolives().getNomBois());
        txtFormatSolivePAF.setText(controleur.getGabaritPC().getTypeSolives().getNomBois());
        txtFormatPlanche.setText(controleur.getGabaritPC().getTypePlanches().getNomBois());
        txtFormatPoutre.setText(controleur.getGabaritPC().getTypePlis().getNomBois());
        txtFormatPoteau.setText(controleur.getGabaritPC().getTypePoteaux().getNomBois());

        //Set des quantités
        int nbSolives = controleur.getGabaritPC().getNbrSolives() * controleur.getGabaritPC().getNbrPortee() - controleur.getGabaritPC().getNbrSolivesPAF();
        String strNbrSolives = Integer.toString(nbSolives);
        txtQuantiteSolive.setText(strNbrSolives);

        int nbSolivesPorterFaux = controleur.getGabaritPC().getNbrSolivesPAF();
        String strNbrSolivesPorteFaux = Integer.toString(nbSolivesPorterFaux);
        txtQuantiteSolivePAF.setText(strNbrSolivesPorteFaux);

        int nbPoutres = controleur.getGabaritPC().getNbrPoutres() * controleur.getGabaritPC().getNbrPoutreLargeur();
        String strNbrPoutres = Integer.toString(nbPoutres);
        txtQuantitePoutre.setText(strNbrPoutres);

        int nbPlanches = controleur.getGabaritPC().getNbrPlanches();
        String strNbrPlanches = Integer.toString(nbPlanches);
        txtQuantitePlanche.setText(strNbrPlanches);

        int nbPoteaux = controleur.getGabaritPC().getNbrPortee() *
                controleur.getGabaritPC().getNbrPoteauxParPoutre();
        String strNbrPoteaux = Integer.toString(nbPoteaux);
        txtQuantitePoteau.setText(strNbrPoteaux);

    }

    public void setDimensionsPatio() {
        double hauteurPoteaux = controleur.getGabaritPC().getLongueurPoteaux();
        String strHauteurPoteaux = Double.toString(hauteurPoteaux);
        MesureImperiale poteauxImperial = new MesureImperiale(strHauteurPoteaux);
        txtHauteurPoteaux.setText(poteauxImperial.getAsString());

        double longueurPatio = controleur.getGabaritPC().getLongueurPatio();
        String strLongueurPatio = Double.toString(longueurPatio);
        MesureImperiale longueurImperiale = new MesureImperiale(strLongueurPatio);
        txtLongueurPatio.setText(longueurImperiale.getAsString());

        double largeurPatio = controleur.getGabaritPC().getLargeurPatio();
        String strLargeurPatio = Double.toString(largeurPatio);
        MesureImperiale largeurImperiale = new MesureImperiale(strLargeurPatio);
        txtLargeurPatio.setText(largeurImperiale.getAsString());

        double hauteurPatio = controleur.getGabaritPC().getHauteurPatio();
        String strHauteurPatio = Double.toString(hauteurPatio);
        MesureImperiale hauteurImperiale = new MesureImperiale(strHauteurPatio);
        txtHauteurPatio.setText(hauteurImperiale.getAsString());

        int nbrSection = controleur.getGabaritPC().getNbrPortee();
        String strNbrSection = Integer.toString(nbrSection);
        txtNbrSection.setText(strNbrSection);

        int nbrPoutre = controleur.getGabaritPC().getNbrPoutres();
        String strNbrPoutre = Integer.toString(nbrPoutre);
        txtQuantiteOngletPoutre.setText(strNbrPoutre);

        /*
        int nbrPlanches = controleur.getGabaritPC().getNbrPlanches();
        String strNbrPlanches = Integer.toString(nbrPlanches);
        txtQuantiteRecouvrement.setText(strNbrPlanches);

         */


        double longueurPAFaux = controleur.getGabaritPC().getLongueurPAFaux();
        String strLongueurPAFaux = Double.toString(longueurPAFaux);
        MesureImperiale pAFauxImperial = new MesureImperiale(strLongueurPAFaux);
        txtPorteAFaux.setText(pAFauxImperial.getAsString());

        double espacementPlanches = controleur.getGabaritPC().getEspacementPlanches();
        String strEspacementPlanches = Double.toString(espacementPlanches);
        MesureImperiale espacementPlanchesImperial = new MesureImperiale(strEspacementPlanches);
        txtEspacementRecouvrement.setText(espacementPlanchesImperial.getAsString());

        double longueurPoutre = controleur.getGabaritPC().getLongueurPoutre();
        String strLongueurPoutre = Double.toString(longueurPoutre);
        MesureImperiale poutreImperiale = new MesureImperiale(strLongueurPoutre);
        txtLongueurPoutre.setText(poutreImperiale.getAsString());

        double longueurSolives = controleur.getGabaritPC().getLongueurSolives();
        String strLongueurSolives = Double.toString(longueurSolives);
        MesureImperiale solivesImperiale = new MesureImperiale(strLongueurSolives);
        txtLongueurSolive.setText(solivesImperiale.getAsString());

        double espacementSolives = controleur.getGabaritPC().getEspacementSolives();
        String strEspacmentSolives = Double.toString(espacementSolives);
        MesureImperiale espacementSolivesImpériale = new MesureImperiale(strEspacmentSolives);
        txtEspacementSolive.setText(espacementSolivesImpériale.getAsString());

        txtMsgErreur.setText(controleur.getGabaritPC().getMsgErreur());
        validitePatio.setSelected(controleur.getGabaritPC().isValiditeePatio());
    }


    public void resetZoomPosition() {
        panelDessin.tauxZoom = 1;
        panelDessin.DecalageX = 0;
        panelDessin.DecalageY = 0;

    }


    public static void main(String[] args) throws IOException {
        App app = new App("Pationator");
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //JFrame frame = new App("Pationator");
        //frame.setVisible(true);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void setGabarit() {
        double longueurPatio = controleur.getGabaritPC().getLongueurPatio();
        String strLongueurPatio = Double.toString(longueurPatio);
        txtLongueurPatio.setText(strLongueurPatio);

        double largeurPatio = controleur.getGabaritPC().getLargeurPatio();
        String strLargeurPatio = Double.toString(largeurPatio);
        txtLargeurPatio.setText(strLargeurPatio);
    }


    private void changerCouleurPoteaux() {
        int selection = txtChoixCouleurPoteaux.getSelectedIndex();
        switch (selection) {
            case 0:
                controleur.getGabaritPC().setColorPoteaux(Color.red);
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
                break;
            case 1:
                controleur.getGabaritPC().setColorPoteaux(Color.blue);
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();

                break;
            case 2:
                controleur.getGabaritPC().setColorPoteaux(Color.yellow);
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
                break;
            case 3:
                controleur.getGabaritPC().setColorPoteaux(Color.black);
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
                break;
            case 4:
                controleur.getGabaritPC().setColorPoteaux(Color.gray);
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
                break;
            case 5:
                controleur.getGabaritPC().setColorPoteaux(Color.green);
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
                break;
        }
    }

    //Ajoute les valeurs disponnibles pour les combo box
    private void remplirLesComboBox() {

        //Format
        ArrayList<Object> formatPiecesRecouvrementArray = new ArrayList<>();
        formatPiecesRecouvrementArray.add("5/4x6");
        formatPiecesRecouvrementArray.add("2x6");
        ArrayList<Object> formatPiecesSoliveArray = new ArrayList<>();
        formatPiecesSoliveArray.add("2x4");
        formatPiecesSoliveArray.add("2x6");
        formatPiecesSoliveArray.add("2x8");
        formatPiecesSoliveArray.add("2x10");
        formatPiecesSoliveArray.add("2x12");
        ArrayList<Object> formatPiecesPoutreArray = new ArrayList<>();
        formatPiecesPoutreArray.add("2x6");
        formatPiecesPoutreArray.add("2x8");
        formatPiecesPoutreArray.add("2x10");
        formatPiecesPoutreArray.add("2x12");
        ArrayList<Object> formatPiecesPoteauxArray = new ArrayList<>();
        formatPiecesPoteauxArray.add("4x4");
        formatPiecesPoteauxArray.add("6x6");

        for (Object itemToAdd : formatPiecesRecouvrementArray/*controlleur.getGabarit().getFormatRecouvrement()*/) {
            txtFormatRecouvrement.addItem(itemToAdd);
        }
        for (Object itemToAdd : formatPiecesPoutreArray/*controlleur.getGabarit().getFormatPoutre()*/) {
            txtFormatOngletPoutre.addItem(itemToAdd);
        }
        for (Object itemToAdd : formatPiecesSoliveArray/*controlleur.getGabarit().getFormatSolive()*/) {
            txtFormatOngletSolive.addItem(itemToAdd);
        }
        for (Object itemToAdd : formatPiecesPoteauxArray/*controlleur.getGabarit().getFormatSolive()*/) {
            txtFormatPoteaux.addItem(itemToAdd);
        }

        //Couleur
        ArrayList<Object> colorArray = new ArrayList<>();
        colorArray.add("Rouge");
        colorArray.add("Bleu");
        colorArray.add("Jaune");
        colorArray.add("Noir");
        colorArray.add("Gris");
        colorArray.add("Vert");
        for (Object itemToAdd : colorArray) {
            txtChoixCouleurPoutres.addItem(itemToAdd);
        }
        for (Object itemToAdd : colorArray) {
            txtChoixCouleurRecouvrement.addItem(itemToAdd);
        }
        for (Object itemToAdd : colorArray) {
            txtChoixCouleurSolives.addItem(itemToAdd);
        }
        for (Object itemToAdd : colorArray) {
            txtChoixCouleurPoteaux.addItem(itemToAdd);
        }
        for (int i = 0; i < 3; i++) {
            txtNbrPlis.addItem(Integer.toString(i + 1));
        }


    }

    public static void pressEnter() {
        try {
            Robot robot = new Robot();

            // Simulate a key press
            robot.mousePress(KeyEvent.VK_ENTER);
            robot.mouseRelease(KeyEvent.VK_ENTER);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    //ajoute les listeners au controles de la fenêtre
    private void ajouterLesListeners() {
        onglets.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                refreshListeMateriaux();
            }
        });


        ActionListener scannePiece = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double mouseX = MouseInfo.getPointerInfo().getLocation().getX() - panelDessin.getX() - 8;
                double mouseY = MouseInfo.getPointerInfo().getLocation().getY() - panelDessin.getY() - 31;
                AffineTransform souris = new AffineTransform();
                souris.translate(mouseX - panelDessin.DecalageX, mouseY - panelDessin.DecalageY);
                Piece infoAAfficher = controleur.getPiecePointer(new Point2D.Double(souris.getTranslateX(), souris.getTranslateY()), panelDessin.tauxZoom);
                if (infoAAfficher != null) {
                    lblTypePiece.setText("Type de la pièce:" + infoAAfficher.getTypePiece().toString());
                    lblFormatPiece.setText("Format de la pièce: " + infoAAfficher.getTypeBois().getNomBois());
                    MesureImperiale mesureAAfficher = new MesureImperiale(Double.toString(infoAAfficher.getLongueur()));
                    lblLongueurPiece.setText("Longueur de la pièce (en pouce): " + mesureAAfficher.getAsString());
                    DecimalFormat formatArgent = new DecimalFormat("###0.00");
                    lblPrixInfoPiece.setText("Coût de la pièce: " + formatArgent.format(infoAAfficher.getTypeBois().getCoutLineaire()) + " $");
                    /*System.out.println(" Info " + infoAAfficher.getTypePiece() + ",X " + infoAAfficher.getEmplacement().getX() + ",Y " + infoAAfficher.getEmplacement().getY());
                    System.out.print("X " + mouseX);
                    System.out.println(" Y " + mouseY);
                    System.out.println(" Zoom "+ panelDessin.tauxZoom);*/

                } else {
                    lblTypePiece.setText("Type de la piece:");
                    lblFormatPiece.setText("Format de la pièce: ");
                    lblLongueurPiece.setText("Longueur de la pièce (en pouce): ");
                    lblPrixInfoPiece.setText("Coût de la pièce: ");
                    /*System.out.print("X " + mouseX);
                    System.out.println(" Y " + mouseY);
                    System.out.println(" Zoom "+ panelDessin.tauxZoom);*/
                }
            }
        };
        final Timer timer = new Timer(100, scannePiece);
//            txtChoixFormatRecouvrement.addItemListener(new ItemListener() {
//                @Override
//                public void itemStateChanged(ItemEvent e) {
//                    changerFormatRecouvrement();
//
//                }
//            });

        txtFormatPoteaux.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //JOptionPane.showMessageDialog(frameMessage, "Format poteaux");
                controleur.changeGabaritStack();
                int selection = txtFormatPoteaux.getSelectedIndex();
                switch (selection) {
                    case 0:
                        controleur.getGabaritPC().setTypePoteaux(controleur.getGabaritPC().getTypeQuatreParQuatre());
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 1:
                        controleur.getGabaritPC().setTypePoteaux(controleur.getGabaritPC().getTypeSixParSix());
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                }
            }
        });
        txtChoixCouleurRecouvrement.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                controleur.changeGabaritStack();
                int selection = txtChoixCouleurRecouvrement.getSelectedIndex();
                switch (selection) {
                    case 0:
                        controleur.getGabaritPC().setColorPlanches(Color.red);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 1:
                        controleur.getGabaritPC().setColorPlanches(Color.blue);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 2:
                        controleur.getGabaritPC().setColorPlanches(Color.yellow);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 3:
                        controleur.getGabaritPC().setColorPlanches(Color.black);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 4:
                        controleur.getGabaritPC().setColorPlanches(Color.gray);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 5:
                        controleur.getGabaritPC().setColorPlanches(Color.green);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                }
            }
        });

        txtChoixCouleurSolives.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                controleur.changeGabaritStack();
                int selection = txtChoixCouleurSolives.getSelectedIndex();
                switch (selection) {
                    case 0:
                        controleur.getGabaritPC().setColorSolives(Color.red);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 1:
                        controleur.getGabaritPC().setColorSolives(Color.blue);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();

                        break;
                    case 2:
                        controleur.getGabaritPC().setColorSolives(Color.yellow);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 3:
                        controleur.getGabaritPC().setColorSolives(Color.black);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 4:
                        controleur.getGabaritPC().setColorSolives(Color.gray);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 5:
                        controleur.getGabaritPC().setColorSolives(Color.green);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                }
            }
        });

        txtChoixCouleurPoutres.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                controleur.getGabaritPC();
                int selection = txtChoixCouleurPoutres.getSelectedIndex();
                switch (selection) {
                    case 0:
                        controleur.getGabaritPC().setColorPoutres(Color.red);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 1:
                        controleur.getGabaritPC().setColorPoutres(Color.blue);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();

                        break;
                    case 2:
                        controleur.getGabaritPC().setColorPoutres(Color.yellow);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 3:
                        controleur.getGabaritPC().setColorPoutres(Color.black);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 4:
                        controleur.getGabaritPC().setColorPoutres(Color.gray);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 5:
                        controleur.getGabaritPC().setColorPoutres(Color.green);
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                }
            }
        });
        txtChoixCouleurPoteaux.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                changerCouleurPoteaux();
            }
        });

        // Les méthodes de zoom/dézoom et de drag sont inspirées de code trouvé sur stackoverflow
        // https://stackoverflow.com/questions/6543453/zooming-in-and-zooming-out-within-a-panel
        // avec cette méthode le zoom/dézoom affecte pas directement les dimensions des rectangles des pièces.
        panelDessin.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                panelDessin.actionZoom = true;
                //Quand tu zoom
                if (e.getWheelRotation() < 0) {
                    panelDessin.tauxZoom *= 1.1;
                    panelDessin.repaint();
                }
                //Quand tu dézoom
                if (e.getWheelRotation() > 0) {
                    panelDessin.tauxZoom /= 1.1;
                    panelDessin.repaint();
                }
            }
        });

        panelDessin.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                Point currentPoint = e.getLocationOnScreen();
                panelDessin.xDifference = currentPoint.x - panelDessin.startPoint.x;
                panelDessin.yDifference = currentPoint.y - panelDessin.startPoint.y;
                panelDessin.actionDrag = true;
                panelDessin.repaint();
            }
        });

        panelDessin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                panelDessin.sourisRelachee = false;
                panelDessin.startPoint = MouseInfo.getPointerInfo().getLocation();
            }
        });
        panelDessin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                panelDessin.sourisRelachee = true;
                if (panelDessin.actionDrag) {
                    panelDessin.repaint();
                }
            }
        });
        panelDessin.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                timer.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                timer.stop();
            }
        });

        //region DimensionPatio
        txtLongueurPatio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.changeGabaritStack();
                String donnee = txtLongueurPatio.getText();
                MesureImperiale nouvelleMesure = new MesureImperiale(donnee);
                double valeurLongueurPatio = nouvelleMesure.getFractionAsDouble();
                controleur.setLongueurPatio(valeurLongueurPatio);
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });

        txtNbrSection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.changeGabaritStack();
                String donnee = txtNbrSection.getText();
                String[] donneSeparee = donnee.split(" ");
                controleur.setNbrSections(Integer.parseInt(donneSeparee[0]));
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        txtHauteurPoteaux.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.changeGabaritStack();
                String donne = txtHauteurPoteaux.getText();
                MesureImperiale nouvelleMesure = new MesureImperiale(donne);
                controleur.setHauteurPoteaux(nouvelleMesure.getFractionAsDouble());
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        txtLongueurPatio.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //txtLongueurPatio.postActionEvent();
            }
        });
        txtLargeurPatio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.changeGabaritStack();
                String donnee = txtLargeurPatio.getText();
                MesureImperiale nouvelleFraction = new MesureImperiale(donnee);
                double valeurLargeurPatio = nouvelleFraction.getFractionAsDouble();
                controleur.setLargeurPatio(valeurLargeurPatio);
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        txtLargeurPatio.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //setLargeurPatio();
                //txtLargeurPatio.postActionEvent();
            }
        });
        txtHauteurPatio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.changeGabaritStack();
                String donnee = txtHauteurPatio.getText();
                MesureImperiale nouvelleMesure = new MesureImperiale(donnee);
                double valeurHauteurPatio = nouvelleMesure.getFractionAsDouble();
                controleur.setHauteurPatio(valeurHauteurPatio);
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        txtHauteurPatio.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //txtHauteurPatio.postActionEvent();
            }
        });

        txtPorteAFaux.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.changeGabaritStack();
                String donnee = txtPorteAFaux.getText();
                MesureImperiale nouvelleMesure = new MesureImperiale(donnee);
                if (nouvelleMesure.getFractionAsDouble() != controleur.getGabaritPC().getLongueurPAFaux()) {
                    controleur.setPAFaux(nouvelleMesure.getFractionAsDouble());
                }
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        txtPorteAFaux.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //txtPorteAFaux.postActionEvent();
            }
        });
        boutonMagique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.changeGabaritStack();
                controleur.BoutonMagic();
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();

            }
        });
        boutonUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.changeGabaritUndo();
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();

            }
        });

        boutonRedo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.changeGabaritRedo();
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();

            }
        });
        //endregion
        //region Menus
        menuPrixMateriaux.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame frame = new ListePrix("Liste des prix des matériaux", app);
                frame.setVisible(true);
            }
        });
        sousMenuSauvegarder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frameMessage, "Sauvegarde");
                try {
                    int nbrSauvegarde = 0;
                    File fichierASauvegarder = new File("plan" + nbrSauvegarde + ".txt");


                    JFileChooser sauvegarder = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                    sauvegarder.setDialogTitle("Choisissez le dossier de sauvegarde");
                    sauvegarder.setApproveButtonText("Sauvegarder");
                    sauvegarder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                    int retourSauvegarde = sauvegarder.showOpenDialog(null);
                    //Files.write(fichierASauvegarder.toPath(), controleur.getGabaritPC());

                    if (retourSauvegarde == JFileChooser.APPROVE_OPTION) {
                        if (sauvegarder.getSelectedFile().isDirectory()) {
                            File nouvelleDestination = new File(sauvegarder.getSelectedFile().toString() + "\\" + fichierASauvegarder.getName());
                            FileOutputStream os = new FileOutputStream(nouvelleDestination);
                            ObjectOutputStream oos = new ObjectOutputStream(os);
                            oos.writeObject(controleur.getGabaritPC());
                            nbrSauvegarde++;

                            while (nouvelleDestination.exists() & nbrSauvegarde < 10) {
                                nbrSauvegarde++;
                                nouvelleDestination = new File(sauvegarder.getSelectedFile().toString() + "\\" + "Projet" + nbrSauvegarde + ".txt");
                            }
                            fichierASauvegarder.renameTo(nouvelleDestination);
                        }

                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        sousMenuCharger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // JOptionPane.showMessageDialog(frameMessage, "Charger un projet");


                JFileChooser importProjet = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                importProjet.setDialogTitle("Charger un projet");
                importProjet.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filtreTxt = new FileNameExtensionFilter("Fichier texte", "txt");
                importProjet.addChoosableFileFilter(filtreTxt);

                int retourImport = importProjet.showOpenDialog(null);
                if (retourImport == JFileChooser.APPROVE_OPTION) {
                    File fichierSelectionne = importProjet.getSelectedFile();
                    try {
                        FileInputStream iS = new FileInputStream(fichierSelectionne);
                        ObjectInputStream oIS = new ObjectInputStream(iS);
                        controleur.setPatioControleur((Gabarit) oIS.readObject());
                    } catch (IOException | ClassNotFoundException ioException) {
                        ioException.printStackTrace();
                    }
                }

                resetZoomPosition();
                checkValiditePatio();

            }
        });

        sousMenuExporter3d.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String patio3d = controleur.export3DFileToSTL();
                int nbrExport3D = 0;

                File geoASauvegarder = new File("Patio3D" + nbrExport3D + ".stl");


                JFileChooser sauvegardeListe = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                sauvegardeListe.setDialogTitle("Choisissez le dossier de sauvegarde");
                sauvegardeListe.setApproveButtonText("Sauvegarder");
                sauvegardeListe.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int retourSauvegarde = sauvegardeListe.showOpenDialog(null);


                try {
                    Files.write(geoASauvegarder.toPath(), patio3d.getBytes());

                    if (retourSauvegarde == JFileChooser.APPROVE_OPTION) {
                        if (sauvegardeListe.getSelectedFile().isDirectory()) {
                            File nouvelleDestination = new File(sauvegardeListe.getSelectedFile().toString() + "\\" + geoASauvegarder.getName());

                            while (nouvelleDestination.exists()) {
                                nbrExport3D++;
                                geoASauvegarder = new File("Patio3D" + nbrExport3D + ".stl");
                                Files.write(geoASauvegarder.toPath(), patio3d.getBytes());
                                nouvelleDestination = new File(sauvegardeListe.getSelectedFile().toString() + "\\" + geoASauvegarder.getName());
                            }
                            geoASauvegarder.renameTo(nouvelleDestination);
                        }

                    }

                } catch (IOException exception) {
                    exception.printStackTrace();
                }

            }
        });
        sousMenuVuePlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frameMessage, "le plan devient en vue plan");
                sousMenuVuePlan.setBackground(new Color(190, 190, 190));
                sousMenuVuePlan.setEnabled(false);
                sousMenuVueCote.setBackground(new Color(238, 238, 238));
                sousMenuVueCote.setEnabled(true);
                sousMenuVueFace.setBackground(new Color(238, 238, 238));
                sousMenuVueFace.setEnabled(true);
                controleur.setVueActivePatio(Vues2d.TypeVues2d.vuePlan);
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        sousMenuVueCote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frameMessage, "le plan devient en vue cote");
                sousMenuVuePlan.setBackground(new Color(238, 238, 238));
                sousMenuVuePlan.setEnabled(true);
                sousMenuVueCote.setBackground(new Color(190, 190, 190));
                sousMenuVueCote.setEnabled(false);
                sousMenuVueFace.setBackground(new Color(238, 238, 238));
                sousMenuVueFace.setEnabled(true);
                controleur.setVueActivePatio(Vues2d.TypeVues2d.vueCote);
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        sousMenuVueFace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frameMessage, "le plan devient en vue face");
                sousMenuVuePlan.setBackground(new Color(238, 238, 238));
                sousMenuVuePlan.setEnabled(true);
                sousMenuVueCote.setBackground(new Color(238, 238, 238));
                sousMenuVueCote.setEnabled(true);
                sousMenuVueFace.setBackground(new Color(190, 190, 190));
                sousMenuVueFace.setEnabled(false);
                controleur.setVueActivePatio(Vues2d.TypeVues2d.vueFace);
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        //endregion
        //region Onglets
        onglets.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                requestFocus();

            }
        });

        onglets.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (onglets.getSelectedIndex() == 4) // 4 car c'est l'indice de l'onglet de matériaux
                {
                    //JOptionPane.showMessageDialog(frameMessage, "Faire liste prix");
                }
            }
        });

        //endregion
        //region Recouvrement
        txtFormatRecouvrement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frameMessage, "Format recouvrement");
                controleur.changeGabaritStack();
                int selection = txtFormatRecouvrement.getSelectedIndex();
                switch (selection) {
                    case 0:
                        controleur.getGabaritPC().setTypePlanches(controleur.getGabaritPC().getTypeCinqQuartParSix());
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 1:
                        controleur.getGabaritPC().setTypePlanches(controleur.getGabaritPC().getTypeDeuxParSix());
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                }
            }
        });
        txtEspacementRecouvrement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frameMessage, "Espacement recouvrement");
                controleur.changeGabaritStack();
                String donnee = txtEspacementRecouvrement.getText();
                MesureImperiale nouvelleMesure = new MesureImperiale(donnee);
                controleur.getGabaritPC().setEspacementPlanches(nouvelleMesure.getFractionAsDouble());
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });

        radioTransparenceRecouvrement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frameMessage, "Transparence recouvrement");
                controleur.changeGabaritStack();
                controleur.getGabaritPC().setTransparencePlanches(!controleur.getGabaritPC().isTransparencePlanches());
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        radRecouvrementCache.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frameMessage, "Cache recouvrement");
                controleur.changeGabaritStack();
                controleur.getGabaritPC().setVisibilitePlanches(!controleur.getGabaritPC().isVisibilitePlanches());
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        //endregion
        //region Poutre
        txtLongueurPoutre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.changeGabaritStack();
                String donne = txtLongueurPoutre.getText();
                MesureImperiale nouvelleMesure = new MesureImperiale(donne);
                controleur.setLargeurPatio(nouvelleMesure.getFractionAsDouble());
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });

        txtLongueurPoutre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                txtLongueurPoutre.postActionEvent();
            }
        });


        txtFormatOngletPoutre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // JOptionPane.showMessageDialog(frameMessage, "Format poutre");
                controleur.changeGabaritStack();
                int selection = txtFormatOngletPoutre.getSelectedIndex();
                switch (selection) {
                    case 0:
                        controleur.getGabaritPC().setTypePlis(controleur.getGabaritPC().getTypeDeuxParQuatre());
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 1:
                        controleur.getGabaritPC().setTypePlis(controleur.getGabaritPC().getTypeDeuxParSix());
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 2:
                        controleur.getGabaritPC().setTypePlis(controleur.getGabaritPC().getTypeDeuxParHuit());
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 3:
                        controleur.getGabaritPC().setTypePlis(controleur.getGabaritPC().getTypeDeuxParDix());
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 4:
                        controleur.getGabaritPC().setTypePlis(controleur.getGabaritPC().getTypeDeuxParDouze());
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                }
            }
        });
        txtQuantiteOngletPoutre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.changeGabaritStack();
                String donne = txtQuantiteOngletPoutre.getText();
                String[] donneSeparee = donne.split(" ");
                controleur.setNbrPoutre(Integer.parseInt(donneSeparee[0]));
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();

            }
        });
        txtQuantiteOngletPoutre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //txtQuantiteOngletPoutre.postActionEvent();
            }
        });
        radPoutreTransparence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.changeGabaritStack();
                controleur.getGabaritPC().setTransparencePoutre(!controleur.getGabaritPC().isTransparencePoutre());
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        radPoutreCache.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frameMessage, "Cache poutre");
                controleur.changeGabaritStack();
                controleur.getGabaritPC().setVisibilitePoutre(!controleur.getGabaritPC().isVisibilitePoutre());
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        //endregion
        //region Solives
        txtLongueurSolive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.changeGabaritStack();
                String donne = txtLongueurSolive.getText();
                MesureImperiale nouvelleMesure = new MesureImperiale(donne);
                controleur.setLongueurSolive(nouvelleMesure.getFractionAsDouble());
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        txtLongueurSolive.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //txtLongueurSolive.postActionEvent();
            }
        });
        txtFormatOngletSolive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frameMessage, "Format solive");
                controleur.changeGabaritStack();
                int selection = txtFormatOngletSolive.getSelectedIndex();
                switch (selection) {
                    case 0:
                        controleur.getGabaritPC().setTypeSolives(controleur.getGabaritPC().getTypeDeuxParQuatre());
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 1:
                        controleur.getGabaritPC().setTypeSolives(controleur.getGabaritPC().getTypeDeuxParSix());
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 2:
                        controleur.getGabaritPC().setTypeSolives(controleur.getGabaritPC().getTypeDeuxParHuit());
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 3:
                        controleur.getGabaritPC().setTypeSolives(controleur.getGabaritPC().getTypeDeuxParDix());
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                    case 4:
                        controleur.getGabaritPC().setTypeSolives(controleur.getGabaritPC().getTypeDeuxParDouze());
                        controleur.setPatioControleur(controleur.getGabaritPC());
                        resetZoomPosition();
                        checkValiditePatio();
                        break;
                }
            }
        });
        txtEspacementSolive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frameMessage, "Espacement solive");
                controleur.changeGabaritStack();
                String donne = txtEspacementSolive.getText();
                MesureImperiale nouvelleMesure = new MesureImperiale(donne);
                controleur.getGabaritPC().setEspacementSolives(nouvelleMesure.getFractionAsDouble());
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();

            }
        });

        radSoliveTransparence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frameMessage, "Transparence solive");
                controleur.changeGabaritStack();
                controleur.getGabaritPC().setTransparenceSolives(!controleur.getGabaritPC().isTransparenceSolives());
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        radSoliveCache.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frameMessage, "Cache solive");
                controleur.changeGabaritStack();
                controleur.getGabaritPC().setVisibiliteSolives(!controleur.getGabaritPC().isVisibiliteSolives());
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });

        transparencePoteaux.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.changeGabaritStack();
                controleur.getGabaritPC().setTransparencePoteaux(!controleur.getGabaritPC().isTransparencePoteaux());
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        visibiliteePoteaux.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.changeGabaritStack();
                controleur.getGabaritPC().setVisibilitePoteaux(!controleur.getGabaritPC().isVisibilitePoteaux());
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();


            }
        });


        sousMenuListePieces.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nbSolives = controleur.getGabaritPC().getNbrSolives() * controleur.getGabaritPC().getNbrPortee();

                int nbPoutres = controleur.getGabaritPC().getNbrPoutres() * controleur.getGabaritPC().getNbrPoutreLargeur();

                int nbPlis = nbPoutres * controleur.getGabaritPC().getNbrPlis();

                int nbPlanches = controleur.getGabaritPC().getNbrPlanches();

                int nbPoteaux = controleur.getGabaritPC().getNbrPortee() + 1 * controleur.getGabaritPC().getNbrPoutreLargeur() * controleur.getGabaritPC().getNbrPortee();


                String formatSolive = controleur.getGabaritPC().getTypeSolives().getNomBois();
                String formatPlis = controleur.getGabaritPC().getTypePlis().getNomBois();
                String formatPlanches = controleur.getGabaritPC().getTypePlanches().getNomBois();
                String formatPoteaux = controleur.getGabaritPC().getTypePoteaux().getNomBois();

                String separateur = "\n=======================================================================\n";

                String listePiecesExporter = "Liste de pièce pour la construction de votre patio" + separateur
                        + nbPoteaux + " Poteaux de format : " + formatPoteaux + separateur
                        + nbPlis + " Plis de format : " + formatPlis + ", pour un total de " + nbPoutres + " Poutres" + separateur
                        + nbSolives + " Solives de format : " + formatSolive + separateur
                        + nbPlanches + " Planches de recouvrement de format : " + formatPlanches + separateur
                        + "Pour un coût total de : " + controleur.getGabaritPC().getCoutPatioAvecPoteaux() + "$";

                int nbrListes = 0;

                File listeASauvegarder = new File("Liste" + nbrListes + ".txt");


                JFileChooser sauvegardeListe = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                sauvegardeListe.setDialogTitle("Choisissez le dossier de sauvegarde");
                sauvegardeListe.setApproveButtonText("Sauvegarder");
                sauvegardeListe.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int retourSauvegarde = sauvegardeListe.showOpenDialog(null);


                try {
                    Files.write(listeASauvegarder.toPath(), listePiecesExporter.getBytes());

                    if (retourSauvegarde == JFileChooser.APPROVE_OPTION) {
                        if (sauvegardeListe.getSelectedFile().isDirectory()) {
                            File nouvelleDestination = new File(sauvegardeListe.getSelectedFile().toString() + "\\" + listeASauvegarder.getName());

                            while (nouvelleDestination.exists()) {
                                nbrListes++;
                                listeASauvegarder = new File("Liste" + nbrListes + ".txt");
                                Files.write(listeASauvegarder.toPath(), listePiecesExporter.getBytes());
                                nouvelleDestination = new File(sauvegardeListe.getSelectedFile().toString() + "\\" + listeASauvegarder.getName());
                            }
                            listeASauvegarder.renameTo(nouvelleDestination);
                        }

                    }

                } catch (IOException exception) {
                    exception.printStackTrace();
                }


            }
        });

        txtNbrPlis.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                controleur.changeGabaritStack();
                for (int i = 0; i < 3; i++) {
                    if (txtNbrPlis.getSelectedIndex() == i) {
                        controleur.setNbrPlis(i + 1);
                    }
                }
                controleur.setPatioControleur(controleur.getGabaritPC());
                resetZoomPosition();
                checkValiditePatio();
            }
        });
        sousMenuExporter2d.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double coefficient = 3;
                if (controleur.getGabaritPC().getLargeurPatio() >= 300 && controleur.getGabaritPC().getLargeurPatio() <= 400
                        && controleur.getGabaritPC().getLongueurPatio() <= 400 && controleur.getGabaritPC().getLongueurPatio() >= 300) {
                    coefficient = 2;
                } else if (controleur.getGabaritPC().getLargeurPatio() > 400 && controleur.getGabaritPC().getLongueurPatio() > 400) {
                    coefficient = 1.75;
                }

                try {
                    new AffichagePatio(controleur).exportation(coefficient, 25, 100);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        txtNbrPoteauxParPoutre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int donne = Integer.parseInt(txtNbrPoteauxParPoutre.getText());
                controleur.setNbrPlis(donne);
                controleur.changeGabaritStack();
            }
        });

        //endregion
    }


    //Sélectionne les valeurs de la sauvegarde
    private void preSelectionnerValeurSauvegarde() {

    }//

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.setBackground(new Color(-16744320));
        panelMain.setForeground(new Color(-4503731));
        panelPatio = new JPanel();
        panelPatio.setLayout(new GridLayoutManager(8, 4, new Insets(0, 0, 0, 0), -1, -1));
        panelPatio.setBackground(new Color(-3447241));
        panelMain.add(panelPatio, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(800, -1), null, 2, false));
        final JLabel label1 = new JLabel();
        label1.setText("Longueur");
        panelPatio.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelPatio.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelPatio.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Hauteur");
        panelPatio.add(label2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtLongueurPatio = new JTextField();
        panelPatio.add(txtLongueurPatio, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Largeur");
        panelPatio.add(label3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtHauteurPatio = new JTextField();
        panelPatio.add(txtHauteurPatio, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        boutonMagique = new JButton();
        boutonMagique.setText("Bouton magique");
        panelPatio.add(boutonMagique, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boutonUndo = new JButton();
        boutonUndo.setText("Undo");
        panelPatio.add(boutonUndo, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boutonRedo = new JButton();
        boutonRedo.setText("Redo");
        panelPatio.add(boutonRedo, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panelPatio.add(spacer3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Longueur du porte-à-faux");
        panelPatio.add(label4, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtPorteAFaux = new JTextField();
        panelPatio.add(txtPorteAFaux, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer4 = new Spacer();
        panelPatio.add(spacer4, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Nombre de Sections");
        panelPatio.add(label5, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtNbrSection = new JTextField();
        panelPatio.add(txtNbrSection, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtLargeurPatio = new JTextField();
        panelPatio.add(txtLargeurPatio, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        barreMenu = new JMenuBar();
        barreMenu.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(barreMenu, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        menuFichier = new JMenu();
        menuFichier.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        menuFichier.setText("Fichier");
        barreMenu.add(menuFichier, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        sousMenuSauvegarder = new JMenuItem();
        sousMenuSauvegarder.setText("Sauvegarder");
        menuFichier.add(sousMenuSauvegarder);
        sousMenuCharger = new JMenuItem();
        sousMenuCharger.setText("Charger un projet");
        menuFichier.add(sousMenuCharger);
        sousMenuExporter2d = new JMenuItem();
        sousMenuExporter2d.setText("Exportation 2d");
        menuFichier.add(sousMenuExporter2d);
        sousMenuExporter3d = new JMenuItem();
        sousMenuExporter3d.setText("Exportation 3d");
        menuFichier.add(sousMenuExporter3d);
        sousMenuListePieces = new JMenuItem();
        sousMenuListePieces.setText("Sauvegarder la liste de Pièces");
        menuFichier.add(sousMenuListePieces);
        menuPrixMateriaux = new JMenu();
        menuPrixMateriaux.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        menuPrixMateriaux.setText("Prix des matériaux");
        barreMenu.add(menuPrixMateriaux, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        menuVue = new JMenu();
        menuVue.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        menuVue.setSelected(false);
        menuVue.setText("Changer de vue");
        barreMenu.add(menuVue, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        sousMenuVuePlan = new JMenuItem();
        sousMenuVuePlan.setText("Vue en plan");
        menuVue.add(sousMenuVuePlan);
        sousMenuVueCote = new JMenuItem();
        sousMenuVueCote.setText("Vue de côté");
        menuVue.add(sousMenuVueCote);
        sousMenuVueFace = new JMenuItem();
        sousMenuVueFace.setText("Vue de face");
        menuVue.add(sousMenuVueFace);
        final Spacer spacer5 = new Spacer();
        menuVue.add(spacer5);
        final Spacer spacer6 = new Spacer();
        barreMenu.add(spacer6, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        panelOnglet = new JPanel();
        panelOnglet.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelOnglet.setBackground(new Color(-3289651));
        panelOnglet.setForeground(new Color(-3289651));
        panelMain.add(panelOnglet, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        onglets = new JTabbedPane();
        panelOnglet.add(onglets, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(400, 600), null, 0, false));
        ongletRecouvrement = new JPanel();
        ongletRecouvrement.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        ongletRecouvrement.setBackground(new Color(-6579301));
        ongletRecouvrement.setForeground(new Color(-6579301));
        onglets.addTab("Recouvrement", ongletRecouvrement);
        final JLabel label6 = new JLabel();
        label6.setForeground(new Color(-12828863));
        label6.setText("Format");
        ongletRecouvrement.add(label6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        ongletRecouvrement.add(spacer7, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setForeground(new Color(-12828863));
        label7.setText("Espacement");
        ongletRecouvrement.add(label7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtFormatRecouvrement = new JComboBox();
        ongletRecouvrement.add(txtFormatRecouvrement, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtEspacementRecouvrement = new JTextField();
        ongletRecouvrement.add(txtEspacementRecouvrement, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        choixCouleurRecouvrement = new JLabel();
        choixCouleurRecouvrement.setForeground(new Color(-12828863));
        choixCouleurRecouvrement.setText("Choix couleur");
        ongletRecouvrement.add(choixCouleurRecouvrement, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioTransparenceRecouvrement = new JRadioButton();
        radioTransparenceRecouvrement.setBackground(new Color(-6579301));
        radioTransparenceRecouvrement.setForeground(new Color(-12828863));
        radioTransparenceRecouvrement.setText("Transparent");
        ongletRecouvrement.add(radioTransparenceRecouvrement, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radRecouvrementCache = new JRadioButton();
        radRecouvrementCache.setBackground(new Color(-6579301));
        radRecouvrementCache.setForeground(new Color(-12828863));
        radRecouvrementCache.setText("Caché");
        ongletRecouvrement.add(radRecouvrementCache, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtChoixCouleurRecouvrement = new JComboBox();
        ongletRecouvrement.add(txtChoixCouleurRecouvrement, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ongletPoutres = new JPanel();
        ongletPoutres.setLayout(new GridLayoutManager(6, 4, new Insets(0, 0, 0, 0), -1, -1));
        ongletPoutres.setBackground(new Color(-6579301));
        ongletPoutres.setForeground(new Color(-6579301));
        onglets.addTab("Poutres", ongletPoutres);
        final JLabel label8 = new JLabel();
        label8.setForeground(new Color(-12828863));
        label8.setText("Longueur");
        ongletPoutres.add(label8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        ongletPoutres.add(spacer8, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setForeground(new Color(-12828863));
        label9.setText("Quantité");
        ongletPoutres.add(label9, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setForeground(new Color(-12828863));
        label10.setText("Format");
        ongletPoutres.add(label10, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtFormatOngletPoutre = new JComboBox();
        ongletPoutres.add(txtFormatOngletPoutre, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtQuantiteOngletPoutre = new JTextField();
        ongletPoutres.add(txtQuantiteOngletPoutre, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtLongueurPoutre = new JTextField();
        ongletPoutres.add(txtLongueurPoutre, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Choixcouleur = new JLabel();
        Choixcouleur.setForeground(new Color(-12828863));
        Choixcouleur.setText("Choix couleur");
        ongletPoutres.add(Choixcouleur, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radPoutreTransparence = new JRadioButton();
        radPoutreTransparence.setBackground(new Color(-6579301));
        radPoutreTransparence.setForeground(new Color(-12828863));
        radPoutreTransparence.setText("Transparent");
        ongletPoutres.add(radPoutreTransparence, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtChoixCouleurPoutres = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        txtChoixCouleurPoutres.setModel(defaultComboBoxModel1);
        ongletPoutres.add(txtChoixCouleurPoutres, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radPoutreCache = new JRadioButton();
        radPoutreCache.setBackground(new Color(-6579301));
        radPoutreCache.setForeground(new Color(-12828863));
        radPoutreCache.setText("Caché");
        ongletPoutres.add(radPoutreCache, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblNbrPlis = new JLabel();
        lblNbrPlis.setForeground(new Color(-12828863));
        lblNbrPlis.setText("Nombre de plis");
        ongletPoutres.add(lblNbrPlis, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtNbrPlis = new JComboBox();
        ongletPoutres.add(txtNbrPlis, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ongletSolives = new JPanel();
        ongletSolives.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        ongletSolives.setBackground(new Color(-6579301));
        ongletSolives.setForeground(new Color(-6579301));
        onglets.addTab("Solives", ongletSolives);
        final JLabel label11 = new JLabel();
        label11.setForeground(new Color(-12828863));
        label11.setText("Longueur");
        ongletSolives.add(label11, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        ongletSolives.add(spacer9, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setForeground(new Color(-12828863));
        label12.setText("Espacement");
        ongletSolives.add(label12, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtLongueurSolive = new JTextField();
        ongletSolives.add(txtLongueurSolive, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtFormatOngletSolive = new JComboBox();
        ongletSolives.add(txtFormatOngletSolive, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        radSoliveTransparence = new JRadioButton();
        radSoliveTransparence.setBackground(new Color(-6579301));
        radSoliveTransparence.setForeground(new Color(-12828863));
        radSoliveTransparence.setText("Transparent");
        ongletSolives.add(radSoliveTransparence, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtEspacementSolive = new JTextField();
        ongletSolives.add(txtEspacementSolive, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        choixCouleurSolives1 = new JLabel();
        choixCouleurSolives1.setForeground(new Color(-12828863));
        choixCouleurSolives1.setText("Choix couleur");
        ongletSolives.add(choixCouleurSolives1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtChoixCouleurSolives = new JComboBox();
        ongletSolives.add(txtChoixCouleurSolives, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setForeground(new Color(-12828863));
        label13.setText("Format");
        ongletSolives.add(label13, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radSoliveCache = new JRadioButton();
        radSoliveCache.setBackground(new Color(-6579301));
        radSoliveCache.setForeground(new Color(-12828863));
        radSoliveCache.setText("Caché");
        ongletSolives.add(radSoliveCache, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ongletPoteaux = new JPanel();
        ongletPoteaux.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        ongletPoteaux.setBackground(new Color(-6579301));
        ongletPoteaux.setForeground(new Color(-6579301));
        onglets.addTab("Poteaux", ongletPoteaux);
        final Spacer spacer10 = new Spacer();
        ongletPoteaux.add(spacer10, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setForeground(new Color(-12828863));
        label14.setText("Format");
        ongletPoteaux.add(label14, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        visibiliteePoteaux = new JRadioButton();
        visibiliteePoteaux.setBackground(new Color(-6579301));
        visibiliteePoteaux.setForeground(new Color(-12828863));
        visibiliteePoteaux.setText("Caché");
        ongletPoteaux.add(visibiliteePoteaux, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(219, 20), null, 0, false));
        final JLabel label15 = new JLabel();
        label15.setForeground(new Color(-12828863));
        label15.setText("Hauteur");
        ongletPoteaux.add(label15, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        transparencePoteaux = new JRadioButton();
        transparencePoteaux.setBackground(new Color(-6579301));
        transparencePoteaux.setForeground(new Color(-12828863));
        transparencePoteaux.setText("Transparence");
        ongletPoteaux.add(transparencePoteaux, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label16 = new JLabel();
        label16.setForeground(new Color(-12828863));
        label16.setText("Choix couleurs");
        ongletPoteaux.add(label16, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtHauteurPoteaux = new JTextField();
        ongletPoteaux.add(txtHauteurPoteaux, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(219, 30), null, 0, false));
        final JLabel label17 = new JLabel();
        label17.setForeground(new Color(-12828863));
        label17.setText("Nombre de poteaux par poutre");
        ongletPoteaux.add(label17, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtNbrPoteauxParPoutre = new JTextField();
        ongletPoteaux.add(txtNbrPoteauxParPoutre, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(219, 30), null, 0, false));
        txtChoixCouleurPoteaux = new JComboBox();
        ongletPoteaux.add(txtChoixCouleurPoteaux, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(219, 30), null, 0, false));
        txtFormatPoteaux = new JComboBox();
        ongletPoteaux.add(txtFormatPoteaux, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(219, 30), null, 0, false));
        ongletMateriaux = new JPanel();
        ongletMateriaux.setLayout(new GridLayoutManager(7, 6, new Insets(0, 0, 0, 0), -1, -1));
        ongletMateriaux.setBackground(new Color(-6579301));
        ongletMateriaux.setForeground(new Color(-6579301));
        onglets.addTab("Liste des matériaux", ongletMateriaux);
        final JLabel label18 = new JLabel();
        label18.setForeground(new Color(-12828863));
        label18.setText("Matériaux");
        ongletMateriaux.add(label18, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        ongletMateriaux.add(spacer11, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label19 = new JLabel();
        label19.setForeground(new Color(-12828863));
        label19.setText("Poteaux");
        ongletMateriaux.add(label19, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label20 = new JLabel();
        label20.setForeground(new Color(-12828863));
        label20.setText("Poutres");
        ongletMateriaux.add(label20, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label21 = new JLabel();
        label21.setForeground(new Color(-12828863));
        label21.setText("Solives");
        ongletMateriaux.add(label21, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label22 = new JLabel();
        label22.setForeground(new Color(-12828863));
        label22.setText("Planches");
        ongletMateriaux.add(label22, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label23 = new JLabel();
        label23.setForeground(new Color(-12828863));
        label23.setText("Format");
        ongletMateriaux.add(label23, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label24 = new JLabel();
        label24.setForeground(new Color(-12828863));
        label24.setText("Quantité");
        ongletMateriaux.add(label24, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label25 = new JLabel();
        label25.setForeground(new Color(-12828863));
        label25.setText("Coût");
        ongletMateriaux.add(label25, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtFormatPoteau = new JTextField();
        txtFormatPoteau.setEditable(false);
        txtFormatPoteau.setText("");
        ongletMateriaux.add(txtFormatPoteau, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtQuantitePoteau = new JTextField();
        txtQuantitePoteau.setEditable(false);
        ongletMateriaux.add(txtQuantitePoteau, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtCoutPoteau = new JTextField();
        txtCoutPoteau.setEditable(false);
        ongletMateriaux.add(txtCoutPoteau, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtFormatPoutre = new JTextField();
        txtFormatPoutre.setEditable(false);
        ongletMateriaux.add(txtFormatPoutre, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtQuantitePoutre = new JTextField();
        txtQuantitePoutre.setEditable(false);
        ongletMateriaux.add(txtQuantitePoutre, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtFormatSolive = new JTextField();
        txtFormatSolive.setEditable(false);
        ongletMateriaux.add(txtFormatSolive, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtQuantiteSolive = new JTextField();
        txtQuantiteSolive.setEditable(false);
        ongletMateriaux.add(txtQuantiteSolive, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtCoutSolive = new JTextField();
        txtCoutSolive.setEditable(false);
        ongletMateriaux.add(txtCoutSolive, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtFormatPlanche = new JTextField();
        txtFormatPlanche.setEditable(false);
        ongletMateriaux.add(txtFormatPlanche, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtQuantitePlanche = new JTextField();
        txtQuantitePlanche.setEditable(false);
        ongletMateriaux.add(txtQuantitePlanche, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtCoutPlanche = new JTextField();
        txtCoutPlanche.setEditable(false);
        ongletMateriaux.add(txtCoutPlanche, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtCoutPoutre = new JTextField();
        txtCoutPoutre.setEditable(false);
        ongletMateriaux.add(txtCoutPoutre, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtCoutTotal = new JTextField();
        txtCoutTotal.setEditable(false);
        ongletMateriaux.add(txtCoutTotal, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer12 = new Spacer();
        ongletMateriaux.add(spacer12, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label26 = new JLabel();
        label26.setForeground(new Color(-12828863));
        label26.setText("Total");
        ongletMateriaux.add(label26, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label27 = new JLabel();
        label27.setText("Longueur");
        ongletMateriaux.add(label27, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtLongueurPoutresListe = new JTextField();
        txtLongueurPoutresListe.setEditable(false);
        txtLongueurPoutresListe.setText("");
        ongletMateriaux.add(txtLongueurPoutresListe, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtLongueurSolivesListe = new JTextField();
        txtLongueurSolivesListe.setEditable(false);
        ongletMateriaux.add(txtLongueurSolivesListe, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtLongueurPlanches = new JTextField();
        txtLongueurPlanches.setEditable(false);
        ongletMateriaux.add(txtLongueurPlanches, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label28 = new JLabel();
        label28.setText("Solives du porte à faux");
        ongletMateriaux.add(label28, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtLongueurSolivesListePAF = new JTextField();
        txtLongueurSolivesListePAF.setEditable(false);
        ongletMateriaux.add(txtLongueurSolivesListePAF, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtQuantiteSolivePAF = new JTextField();
        txtQuantiteSolivePAF.setEditable(false);
        txtQuantiteSolivePAF.setText("");
        ongletMateriaux.add(txtQuantiteSolivePAF, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtCoutSolivePAF = new JTextField();
        txtCoutSolivePAF.setEditable(false);
        ongletMateriaux.add(txtCoutSolivePAF, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtFormatSolivePAF = new JTextField();
        txtFormatSolivePAF.setEditable(false);
        ongletMateriaux.add(txtFormatSolivePAF, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtLongueurPoteauxListe = new JTextField();
        txtLongueurPoteauxListe.setEditable(false);
        ongletMateriaux.add(txtLongueurPoteauxListe, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelErreur = new JPanel();
        panelErreur.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelErreur.setBackground(new Color(-6579301));
        panelErreur.setForeground(new Color(-6579301));
        panelOnglet.add(panelErreur, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, 1, 1, null, null, null, 0, false));
        final JLabel label29 = new JLabel();
        label29.setForeground(new Color(-12828863));
        label29.setText("Message d'erreur sur");
        panelErreur.add(label29, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        panelErreur.add(spacer13, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer14 = new Spacer();
        panelErreur.add(spacer14, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label30 = new JLabel();
        label30.setBackground(new Color(-6579301));
        label30.setForeground(new Color(-12828863));
        label30.setText("la validité de votre patio:");
        panelErreur.add(label30, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        validitePatio = new JCheckBox();
        validitePatio.setBackground(new Color(-6579301));
        validitePatio.setEnabled(true);
        validitePatio.setForeground(new Color(-12828863));
        validitePatio.setSelected(false);
        validitePatio.setText("Validité du patio");
        panelErreur.add(validitePatio, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtMsgErreur = new JLabel();
        txtMsgErreur.setBackground(new Color(-12828863));
        txtMsgErreur.setForeground(new Color(-4503731));
        txtMsgErreur.setText("");
        panelErreur.add(txtMsgErreur, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelInfoPiece = new JPanel();
        panelInfoPiece.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelInfoPiece.setBackground(new Color(-6579301));
        panelInfoPiece.setForeground(new Color(-6579301));
        panelErreur.add(panelInfoPiece, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        panelInfoPiece.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16645630)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        lblTitrePanelInfo = new JLabel();
        Font lblTitrePanelInfoFont = this.$$$getFont$$$(null, Font.BOLD, -1, lblTitrePanelInfo.getFont());
        if (lblTitrePanelInfoFont != null) lblTitrePanelInfo.setFont(lblTitrePanelInfoFont);
        lblTitrePanelInfo.setForeground(new Color(-12828863));
        lblTitrePanelInfo.setText("Info pièce");
        panelInfoPiece.add(lblTitrePanelInfo, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblTypePiece = new JLabel();
        lblTypePiece.setForeground(new Color(-12828863));
        lblTypePiece.setText("Type de la pièce: ");
        panelInfoPiece.add(lblTypePiece, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblFormatPiece = new JLabel();
        lblFormatPiece.setForeground(new Color(-12828863));
        lblFormatPiece.setText("Format de la pièce: ");
        panelInfoPiece.add(lblFormatPiece, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblLongueurPiece = new JLabel();
        lblLongueurPiece.setForeground(new Color(-12828863));
        lblLongueurPiece.setText("Longueur de la pièce (en pouce): ");
        panelInfoPiece.add(lblLongueurPiece, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblPrixInfoPiece = new JLabel();
        lblPrixInfoPiece.setForeground(new Color(-12828863));
        lblPrixInfoPiece.setText("Coût de la pièce:: ");
        panelInfoPiece.add(lblPrixInfoPiece, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblInfoSupp = new JLabel();
        lblInfoSupp.setText("");
        panelInfoPiece.add(lblInfoSupp, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelDessin.setForeground(new Color(-12828863));
        panelMain.add(panelDessin, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(800, 500), new Dimension(800, 500), 0, false));
        final Spacer spacer15 = new Spacer();
        panelMain.add(spacer15, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}


