package gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import domaine.MesureImperiale;
import domaine.PatioControleur;
import domaine.TypeBois;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListePrix extends JFrame {
    private JPanel panelPrixMateriaux;
    private JPanel ongletMateriaux;
    private JTextField txtCout2X4;
    private JTextField txtCout2X8;
    private JTextField txtCout2X10;
    private JTextField txtCout2X6;
    private JTextField txtCout2X12;
    public JTextField txtCout4X4;
    public JTextField txtCout6X6;
    public JTextField txtCout54X6;
    public JButton Enregistrer;
    public PatioControleur controleur;
    public App app;
    private JFrame frameMessage;

    public ListePrix(String title, App app) {
        super(title);
        this.app = app;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(panelPrixMateriaux);
        this.pack();
        controleur = app.getControleur();
        setListener();
        setPrixListe();

    }

    public void setPrixListe() {
        double prix54X6 = controleur.getGabaritPC().getPrixFormatCinqQuartParSix();
        String strPrix54X6 = Double.toString(prix54X6);
        txtCout54X6.setText(strPrix54X6);

        double prix2X4 = controleur.getGabaritPC().getPrixFormatDeuxParQuatre();
        String strPrix2X4 = Double.toString(prix2X4);
        txtCout2X4.setText(strPrix2X4);

        double prix2X6 = controleur.getGabaritPC().getPrixFormatDeuxParSix();
        String strPrix2X6 = Double.toString(prix2X6);
        txtCout2X6.setText(strPrix2X6);

        double prix2X8 = controleur.getGabaritPC().getPrixFormatDeuxParHuit();
        String strPrix2X8 = Double.toString(prix2X8);
        txtCout2X8.setText(strPrix2X8);

        double prix2X10 = controleur.getGabaritPC().getPrixFormatDeuxParDix();
        String strPrix2X10 = Double.toString(prix2X10);
        txtCout2X10.setText(strPrix2X10);

        double prix2X12 = controleur.getGabaritPC().getPrixFormatDeuxParDouze();
        String strPrix2X12 = Double.toString(prix2X12);
        txtCout2X12.setText(strPrix2X12);

        double prix4X4 = controleur.getGabaritPC().getPrixFormatQuatreParQuatre();
        String strPrix4X4 = Double.toString(prix4X4);
        txtCout4X4.setText(strPrix4X4);

        double prix6X6 = controleur.getGabaritPC().getPrixFormatSixParSix();
        String strPrix6X6 = Double.toString(prix6X6);
        txtCout6X6.setText(strPrix6X6);

    }

    public void setPrixFormat54X6() {
        String donnee = txtCout54X6.getText();
        double prixFormat54X6 = Double.parseDouble(donnee);
        controleur.setPrixFormat54X6Gabarit(prixFormat54X6);
        app.setListePrix();
    }

    public void setPrixFormat2X4() {
        String donnee = txtCout2X4.getText();
        double prixFormat2X4 = Double.parseDouble(donnee);
        controleur.setPrixFormat2X4Gabarit(prixFormat2X4);
        app.setListePrix();

    }

    public void setPrixFormat2X6() {
        String donnee = txtCout2X6.getText();
        double prixFormat2X6 = Double.parseDouble(donnee);
        controleur.setPrixFormat2X6Gabarit(prixFormat2X6);
        app.setListePrix();


    }

    public void setPrixFormat2X8() {
        String donnee = txtCout2X8.getText();
        double prixFormat2X8 = Double.parseDouble(donnee);
        controleur.setPrixFormat2X8Gabarit(prixFormat2X8);
        app.setListePrix();

    }

    public void setPrixFormat2X10() {
        String donnee = txtCout2X10.getText();
        double prixFormat2X10 = Double.parseDouble(donnee);
        controleur.setPrixFormat2X10Gabarit(prixFormat2X10);
        app.setListePrix();

    }

    public void setPrixFormat2X12() {
        String donnee = txtCout2X12.getText();
        double prixFormat2X12 = Double.parseDouble(donnee);
        controleur.setPrixFormat2X12Gabarit(prixFormat2X12);
        app.setListePrix();

    }

    public void setPrixFormat4X4() {
        String donnee = txtCout4X4.getText();
        double prixFormat4X4 = Double.parseDouble(donnee);
        controleur.setPrixFormat4X4Gabarit(prixFormat4X4);
        app.setListePrix();

    }

    public void setPrixFormat6X6() {
        String donnee = txtCout6X6.getText();
        double prixFormat6X6 = Double.parseDouble(donnee);
        controleur.setPrixFormat6X6Gabarit(prixFormat6X6);
        app.setListePrix();

    }

    public void setListener() {
        txtCout54X6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPrixFormat54X6();
            }
        });
        txtCout2X4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPrixFormat2X4();
            }
        });
        txtCout2X6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPrixFormat2X6();

            }
        });
        txtCout2X8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPrixFormat2X8();

            }
        });
        txtCout2X10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPrixFormat2X10();
            }
        });
        txtCout2X12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPrixFormat2X12();
            }
        });
        txtCout4X4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPrixFormat4X4();
            }
        });
        txtCout6X6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPrixFormat6X6();
            }
        });

        Enregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPrixFormat54X6();
                setPrixFormat2X6();
                setPrixFormat2X4();
                setPrixFormat2X8();
                setPrixFormat2X10();
                setPrixFormat2X12();
                setPrixFormat6X6();
                setPrixFormat4X4();
                controleur.setPrixFormatsStack();
                JOptionPane.showMessageDialog(frameMessage, "Les nouveaux prix ont été enregistrés");
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelPrixMateriaux = new JPanel();
        panelPrixMateriaux.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        ongletMateriaux = new JPanel();
        ongletMateriaux.setLayout(new GridLayoutManager(10, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelPrixMateriaux.add(ongletMateriaux, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Matériaux");
        ongletMateriaux.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setHorizontalAlignment(0);
        label2.setHorizontalTextPosition(0);
        label2.setText("2 X 4");
        ongletMateriaux.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("2 X 6");
        ongletMateriaux.add(label3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("2 X 10");
        ongletMateriaux.add(label4, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setHorizontalAlignment(0);
        label5.setHorizontalTextPosition(0);
        label5.setText("Coût linéaire");
        ongletMateriaux.add(label5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtCout2X4 = new JTextField();
        txtCout2X4.setEditable(true);
        ongletMateriaux.add(txtCout2X4, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtCout2X8 = new JTextField();
        txtCout2X8.setEditable(true);
        ongletMateriaux.add(txtCout2X8, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtCout2X10 = new JTextField();
        txtCout2X10.setEditable(true);
        ongletMateriaux.add(txtCout2X10, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtCout2X6 = new JTextField();
        txtCout2X6.setEditable(true);
        ongletMateriaux.add(txtCout2X6, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("2 X 12");
        ongletMateriaux.add(label6, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtCout2X12 = new JTextField();
        txtCout2X12.setEditable(true);
        ongletMateriaux.add(txtCout2X12, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("2 X 8");
        ongletMateriaux.add(label7, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("4 X 4");
        ongletMateriaux.add(label8, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("6 X 6");
        ongletMateriaux.add(label9, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtCout4X4 = new JTextField();
        ongletMateriaux.add(txtCout4X4, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtCout6X6 = new JTextField();
        txtCout6X6.setText("");
        ongletMateriaux.add(txtCout6X6, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("5/4 X 6");
        ongletMateriaux.add(label10, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtCout54X6 = new JTextField();
        ongletMateriaux.add(txtCout54X6, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelPrixMateriaux.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Enregistrer = new JButton();
        Enregistrer.setText("Enregistrer");
        panelPrixMateriaux.add(Enregistrer, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelPrixMateriaux;
    }

}
