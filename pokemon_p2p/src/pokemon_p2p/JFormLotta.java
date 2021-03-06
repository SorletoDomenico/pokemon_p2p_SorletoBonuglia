/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon_p2p;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author sorleto_domenico
 */
public class JFormLotta extends javax.swing.JFrame {

    /**
     * Creates new form JFormLotta
     */
    JDatiCondivisiConnessione dati;
    Integer pp1, pp2, pp3, pp4;
    public JPokemon pA;
    public JMoves mA;
    Integer listaAvversario = 6;

    public JFormLotta(JDatiCondivisiConnessione dati) throws IOException {
        this.dati = dati;
        initComponents();
        initIndiciPokemon();
        setPP();
        initHP(this.dati);
        initPokemonInCampo(this.dati);
        initColorButton(this.dati);
        //richiamiamo metodo manda pokemon che abbiamo in campo
        dati.manda("p;" + dati.getpNoi().getIpAndport() + ";" + dati.getListapokemonlotta().get(0).pokemon.id.toString());
        //ricevo il pokemon dell'avversario

        dati.ricevi();
        initPokemonAvversario(this.dati);
        initHPAvversario(this.dati);
//        dati.ChiInizia();

        if (dati.getTurno() == false) {
            attesaTurno(dati);
        }
    }

    private JFormLotta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void initIndiciPokemon() {
        jLabel17.setForeground(Color.GREEN);
        jLabel23.setForeground(Color.GREEN);
    }

    private void initResetPokemon(JDatiCondivisiConnessione dati) throws IOException {

        if (jProgressBar1.getValue() <= 0) {
            dati.getListapokemonlotta().remove(0);
            switch (dati.getListapokemonlotta().size()) {
                case 5:
                    //metto la label verde a rossa e quella dopo a verde
                    jLabel17.setForeground(Color.RED);
                    jLabel18.setForeground(Color.GREEN);
                    break;
                case 4:
                    //metto la label verde a rossa
                    jLabel18.setForeground(Color.RED);
                    jLabel19.setForeground(Color.GREEN);
                    break;
                case 3:
                    //metto la label verde a rossa
                    jLabel19.setForeground(Color.RED);
                    jLabel20.setForeground(Color.GREEN);
                    break;
                case 2:
                    //metto la label verde a rossa
                    jLabel20.setForeground(Color.RED);
                    jLabel21.setForeground(Color.GREEN);
                    break;
                case 1:
                    //metto la label verde a rossa
                    jLabel21.setForeground(Color.RED);
                    jLabel22.setForeground(Color.GREEN);
                    break;
                case 0:
                    //metto la label verde a rossa
                    jLabel22.setForeground(Color.RED);
                    // jLabel23.setForeground(Color.GREEN);
                    break;

            }

            if (dati.getListapokemonlotta().isEmpty()) {
                dati.manda("f;" + dati.getpNoi().getIpAndport() + ";" + "Hai vinto");
                jLabel10.setText("Hai perso");
                JOptionPane.showMessageDialog(null, "Hai perso! torna al menu principale");
                JFormMenu jfm = new JFormMenu(dati);
                jfm.setVisible(true);
                setVisible(false);
            } else {
                // jLabel10.setText("Hai Vinto");

                //mando il pokemon nuovo all'avversario
                dati.manda("p;" + dati.getpNoi().getIpAndport() + ";" + dati.getListapokemonlotta().get(0).pokemon.id.toString());
                setPP();
                initHP(dati);
                initPokemonInCampo(dati);
                initColorButton(dati);
            }
        }
        if (jProgressBar1.getValue() < ((50 * jProgressBar1.getMaximum()) / 100)) {
            jProgressBar1.setForeground(Color.YELLOW);

        }
        if (jProgressBar1.getValue() < ((20 * jProgressBar1.getMaximum()) / 100)) {
            jProgressBar1.setForeground(Color.RED);

        }

    }

    private void setPP() {
        pp1 = dati.getListapokemonlotta().get(0).mossa1.pp;
        pp2 = dati.getListapokemonlotta().get(0).mossa2.pp;
        pp3 = dati.getListapokemonlotta().get(0).mossa3.pp;
        pp4 = dati.getListapokemonlotta().get(0).mossa4.pp;
    }

    private void initHP(JDatiCondivisiConnessione dati) {
        jProgressBar1.setMaximum(dati.getListapokemonlotta().get(0).pokemon.HP);
        jProgressBar1.setValue(dati.getListapokemonlotta().get(0).pokemon.HP);
        jProgressBar1.setForeground(Color.GREEN);

    }

    private void initHPAvversario(JDatiCondivisiConnessione dati) {
        jProgressBar2.setMaximum(pA.HP);
        jProgressBar2.setValue(pA.HP);
        jProgressBar2.setForeground(Color.GREEN);
        jLabel8.setText(jProgressBar2.getValue() + "/" + jProgressBar2.getMaximum());

    }

    private void initPokemonAvversario(JDatiCondivisiConnessione dati) {

        String[] v = dati.getTemp();

        if ("p".equals(v[0])) {

            pA = dati.searchPokemon(Integer.parseInt(v[2].trim()));
            //immagine
            String imagePath = pA.hires;
            Image myPicture;
            try {
                myPicture = ImageIO.read(new File(imagePath));
                Image newImage = myPicture.getScaledInstance(jLabel7.getWidth(), jLabel7.getHeight(), Image.SCALE_DEFAULT);
                ImageIcon icon = new ImageIcon(newImage);
                jLabel7.setIcon(icon);
            } catch (IOException ex) {
                Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
            }

            //nome pokemon
            jLabel9.setText(pA.name);

            //progressBar
            jProgressBar2.setMaximum(pA.HP);

            jLabel8.setText(jProgressBar2.getValue() + "/" + jProgressBar2.getMaximum());

        }

    }

    private void initPokemonInCampo(JDatiCondivisiConnessione dati) {
        jLabel1.setText(dati.getListapokemonlotta().get(0).pokemon.name);

        //immagine nella label2
        String imagePath = dati.getListapokemonlotta().get(0).pokemon.hires;
        Image myPicture;

        try {
            myPicture = ImageIO.read(new File(imagePath));
            Image newImage = myPicture.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_DEFAULT);
            ImageIcon icon = new ImageIcon(newImage);
            jLabel2.setIcon(icon);
        } catch (IOException ex) {
            Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
        }
        //barra messaggi
        jLabel5.setText("Cosa deve fare " + dati.getListapokemonlotta().get(0).pokemon.name + "?");

        //progressiveBar
        jProgressBar1.setMaximum(dati.getListapokemonlotta().get(0).pokemon.HP);

        jLabel3.setText(jProgressBar1.getValue() + "/" + jProgressBar1.getMaximum());

        //setPP();
        //bottoni
        jButton1.setText(dati.getListapokemonlotta().get(0).mossa1.ename);
        jTextArea2.setText(dati.getListapokemonlotta().get(0).mossa1.type + "\n accurancy: " + dati.getListapokemonlotta().get(0).mossa1.accurancy + "\n power: " + dati.getListapokemonlotta().get(0).mossa1.power + "\n pp: " + pp1 + "/" + dati.getListapokemonlotta().get(0).mossa1.pp);
        jButton2.setText(dati.getListapokemonlotta().get(0).mossa2.ename);
        jTextArea4.setText(dati.getListapokemonlotta().get(0).mossa2.type + "\n accurancy: " + dati.getListapokemonlotta().get(0).mossa2.accurancy + "\n power: " + dati.getListapokemonlotta().get(0).mossa2.power + "\n pp: " + pp2 + "/" + dati.getListapokemonlotta().get(0).mossa2.pp);
        jButton3.setText(dati.getListapokemonlotta().get(0).mossa3.ename);
        jTextArea1.setText(dati.getListapokemonlotta().get(0).mossa3.type + "\n accurancy: " + dati.getListapokemonlotta().get(0).mossa3.accurancy + "\n power: " + dati.getListapokemonlotta().get(0).mossa3.power + "\n pp: " + pp3 + "/" + dati.getListapokemonlotta().get(0).mossa3.pp);
        jButton4.setText(dati.getListapokemonlotta().get(0).mossa4.ename);
        jTextArea3.setText(dati.getListapokemonlotta().get(0).mossa4.type + "\n accurancy: " + dati.getListapokemonlotta().get(0).mossa4.accurancy + "\n power: " + dati.getListapokemonlotta().get(0).mossa4.power + "\n pp: " + pp4 + "/" + dati.getListapokemonlotta().get(0).mossa4.pp);
        jTextArea1.setEditable(false);
        jTextArea2.setEditable(false);
        jTextArea3.setEditable(false);
        jTextArea4.setEditable(false);

        //setPokeball
        String imagePokeball = "pokeball.png";
        Image pokeball;
        try {
            pokeball = ImageIO.read(new File(imagePokeball));
            //  Image newImage2 = pokeball.getScaledInstance(jLabel130.getWidth(), jLabel130.getHeight(), Image.SCALE_DEFAULT);
            //  ImageIcon icon = new ImageIcon(newImage2);
//            jLabel11.setIcon(icon);
//            jLabel12.setIcon(icon);
//            jLabel13.setIcon(icon);
//            jLabel14.setIcon(icon);
//            jLabel15.setIcon(icon);
            // jLabel130.setIcon(icon);

        } catch (IOException ex) {
            Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void blocca(Boolean b) {
        if (b == false) {
            jLabel5.setText("In attesa dell'avversario");
        } else {
            jLabel5.setText("Cosa deve fare" + dati.getListapokemonlotta().get(0).pokemon.name + "?");
        }
        jButton1.setVisible(b);
        jButton2.setVisible(b);
        jButton3.setVisible(b);
        jButton4.setVisible(b);
        jTextArea1.setVisible(b);
        jTextArea2.setVisible(b);
        jTextArea3.setVisible(b);
        jTextArea4.setVisible(b);
    }

    private void attesaTurno(JDatiCondivisiConnessione dati) {
        blocca(false);
        Thread tAttesa = new Thread() {
            public void run() {
                do {
                    try {
                        dati.ricevi();
                    } catch (IOException ex) {
                        Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String[] v = dati.getTemp();
                    if ("p".equals(v[0])) {
                        listaAvversario--;
                        pA = dati.searchPokemon(Integer.parseInt(v[2].trim()));
                        //immagine
                        String imagePath = pA.hires;
                        Image myPicture;
                        try {
                            myPicture = ImageIO.read(new File(imagePath));
                            Image newImage = myPicture.getScaledInstance(jLabel7.getWidth(), jLabel7.getHeight(), Image.SCALE_DEFAULT);
                            ImageIcon icon = new ImageIcon(newImage);
                            jLabel7.setIcon(icon);
                        } catch (IOException ex) {
                            Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        //nome pokemon
                        jLabel9.setText(pA.name);

                        //progressBar
                        jProgressBar2.setMaximum(pA.HP);
                        jProgressBar2.setValue(pA.HP);
                        jProgressBar2.setForeground(Color.GREEN);
                        jLabel8.setText(jProgressBar2.getValue() + "/" + jProgressBar2.getMaximum());
                        switch (listaAvversario) {
                            case 5:
                                //metto la label verde a rossa e quella dopo a verde
                                jLabel23.setForeground(Color.RED);
                                jLabel24.setForeground(Color.GREEN);
                                break;
                            case 4:
                                //metto la label verde a rossa
                                jLabel24.setForeground(Color.RED);
                                jLabel25.setForeground(Color.GREEN);
                                break;
                            case 3:
                                //metto la label verde a rossa
                                jLabel25.setForeground(Color.RED);
                                jLabel26.setForeground(Color.GREEN);
                                break;
                            case 2:
                                //metto la label verde a rossa
                                jLabel26.setForeground(Color.RED);
                                jLabel27.setForeground(Color.GREEN);
                                break;
                            case 1:
                                //metto la label verde a rossa
                                jLabel27.setForeground(Color.RED);
                                jLabel28.setForeground(Color.GREEN);
                                break;
                            case 0:
                                //metto la label verde a rossa
                                jLabel28.setForeground(Color.RED);
                                // jLabel23.setForeground(Color.GREEN);
                                break;

                        }
                    }

                    if ("m".equals(v[0])) {
                        mA = dati.searchMossa(Integer.parseInt(v[2].trim()));
                        jProgressBar1.setValue(jProgressBar1.getValue() - mA.power);
                        jLabel3.setText(jProgressBar1.getValue() + "/" + jProgressBar1.getMaximum());
                        dati.setTurno(true);
                        try {
                            initResetPokemon(dati);
                        } catch (IOException ex) {
                            Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    if ("f".equals(v[0])) {
                        jLabel10.setText("Hai vinto");
                        JOptionPane.showMessageDialog(null, "Hai vinto! torna al menu principale");
                        JFormMenu jfmv = new JFormMenu(dati);
                        jfmv.setVisible(true);
                        setVisible(false);

                    }
                } while (!dati.getTurno());
                blocca(true);
            }
        };
        tAttesa.start();
    }

    private void initColorButton(JDatiCondivisiConnessione dati) {

        //colori in base al tipo delle mosse
        switch (dati.getListapokemonlotta().get(0).mossa1.type) {
            case "Normal":
                jButton1.setBackground(Color.GRAY);
                break;
            case "Fire":
                jButton1.setBackground(Color.RED);
                break;
            case "Water":
                jButton1.setBackground(Color.CYAN);
                break;
            case "Flying":
                jButton1.setBackground(Color.WHITE);
                break;
            case "Poison":
                jButton1.setBackground(Color.PINK);
                break;
            case "Grass":
                jButton1.setBackground(Color.GREEN);
                break;
            case "Fighting":
                jButton1.setBackground(Color.ORANGE);
                break;
            case "Ice":
                jButton1.setBackground(Color.CYAN);
                break;
            case "Electric":
                jButton1.setBackground(Color.YELLOW);
                break;
            case "Ground":
                jButton1.setBackground(Color.ORANGE);
                break;
            case "Bug":
                jButton1.setBackground(Color.GREEN);
                break;
            case "Dark":
                jButton1.setBackground(Color.BLACK);
                jButton1.setForeground(Color.WHITE);
                break;
            case "Psychic":
                jButton1.setBackground(Color.MAGENTA);
                break;
            case "Dragon":
                jButton1.setBackground(Color.BLUE);
                jButton1.setForeground(Color.WHITE);
                break;
            case "Ghost":
                jButton1.setBackground(Color.BLUE);
                jButton1.setForeground(Color.WHITE);
                break;
            case "Rock":
                jButton1.setBackground(Color.ORANGE);
                break;
            case "Fairy":
                jButton1.setBackground(Color.PINK);
                break;
            case "Steel":
                jButton1.setBackground(Color.LIGHT_GRAY);
                break;
        }

        switch (dati.getListapokemonlotta().get(0).mossa2.type) {
            case "Normal":
                jButton2.setBackground(Color.GRAY);
                break;
            case "Fire":
                jButton2.setBackground(Color.RED);
                break;
            case "Water":
                jButton2.setBackground(Color.CYAN);
                break;
            case "Flying":
                jButton2.setBackground(Color.WHITE);
                break;
            case "Poison":
                jButton2.setBackground(Color.PINK);
                break;
            case "Grass":
                jButton2.setBackground(Color.GREEN);
                break;
            case "Fighting":
                jButton2.setBackground(Color.ORANGE);
                break;
            case "Ice":
                jButton2.setBackground(Color.CYAN);
                break;
            case "Electric":
                jButton2.setBackground(Color.YELLOW);
                break;
            case "Ground":
                jButton2.setBackground(Color.ORANGE);
                break;
            case "Bug":
                jButton2.setBackground(Color.GREEN);
                break;
            case "Dark":
                jButton2.setBackground(Color.BLACK);
                jButton2.setForeground(Color.WHITE);
                break;
            case "Psychic":
                jButton2.setBackground(Color.MAGENTA);
                break;
            case "Dragon":
                jButton2.setBackground(Color.BLUE);
                jButton2.setForeground(Color.WHITE);
                break;
            case "Ghost":
                jButton2.setBackground(Color.BLUE);
                jButton2.setForeground(Color.WHITE);
                break;
            case "Rock":
                jButton2.setBackground(Color.ORANGE);
                break;
            case "Fairy":
                jButton2.setBackground(Color.PINK);
                break;
            case "Steel":
                jButton2.setBackground(Color.LIGHT_GRAY);
                break;
        }

        switch (dati.getListapokemonlotta().get(0).mossa3.type) {
            case "Normal":
                jButton3.setBackground(Color.GRAY);
                break;
            case "Fire":
                jButton3.setBackground(Color.RED);
                break;
            case "Water":
                jButton3.setBackground(Color.CYAN);
                break;
            case "Flying":
                jButton3.setBackground(Color.WHITE);
                break;
            case "Poison":
                jButton3.setBackground(Color.PINK);
                break;
            case "Grass":
                jButton3.setBackground(Color.GREEN);
                break;
            case "Fighting":
                jButton3.setBackground(Color.ORANGE);
                break;
            case "Ice":
                jButton3.setBackground(Color.CYAN);
                break;
            case "Electric":
                jButton3.setBackground(Color.YELLOW);
                break;
            case "Ground":
                jButton3.setBackground(Color.ORANGE);
                break;
            case "Bug":
                jButton3.setBackground(Color.GREEN);
                break;
            case "Dark":
                jButton3.setBackground(Color.BLACK);
                jButton3.setForeground(Color.WHITE);
                break;
            case "Psychic":
                jButton3.setBackground(Color.MAGENTA);
                break;
            case "Dragon":
                jButton3.setBackground(Color.BLUE);
                jButton3.setForeground(Color.WHITE);
                break;
            case "Ghost":
                jButton3.setBackground(Color.BLUE);
                jButton3.setForeground(Color.WHITE);
                break;
            case "Rock":
                jButton3.setBackground(Color.ORANGE);
                break;
            case "Fairy":
                jButton3.setBackground(Color.PINK);
                break;
            case "Steel":
                jButton3.setBackground(Color.LIGHT_GRAY);
                break;
        }

        switch (dati.getListapokemonlotta().get(0).mossa4.type) {
            case "Normal":
                jButton4.setBackground(Color.GRAY);
                break;
            case "Fire":
                jButton4.setBackground(Color.RED);
                break;
            case "Water":
                jButton4.setBackground(Color.CYAN);
                break;
            case "Flying":
                jButton4.setBackground(Color.WHITE);
                break;
            case "Poison":
                jButton4.setBackground(Color.PINK);
                break;
            case "Grass":
                jButton4.setBackground(Color.GREEN);
                break;
            case "Fighting":
                jButton4.setBackground(Color.ORANGE);
                break;
            case "Ice":
                jButton4.setBackground(Color.CYAN);
                break;
            case "Electric":
                jButton4.setBackground(Color.YELLOW);
                break;
            case "Ground":
                jButton4.setBackground(Color.ORANGE);
                break;
            case "Bug":
                jButton4.setBackground(Color.GREEN);
                break;
            case "Dark":
                jButton4.setBackground(Color.BLACK);
                jButton4.setForeground(Color.WHITE);
                break;
            case "Psychic":
                jButton4.setBackground(Color.MAGENTA);
                break;
            case "Dragon":
                jButton4.setBackground(Color.BLUE);
                jButton4.setForeground(Color.WHITE);
                break;
            case "Ghost":
                jButton4.setBackground(Color.BLUE);
                jButton4.setForeground(Color.WHITE);
                break;
            case "Rock":
                jButton4.setBackground(Color.ORANGE);
                break;
            case "Fairy":
                jButton4.setBackground(Color.PINK);
                break;
            case "Steel":
                jButton4.setBackground(Color.LIGHT_GRAY);
                break;
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 255, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setToolTipText("");
        jLabel5.setName(""); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        jLabel3.setText("jLabel3");

        jLabel8.setText("jLabel8");

        jLabel9.setText("jLabel9");

        jLabel17.setText("jLabel17");

        jLabel18.setText("jLabel18");

        jLabel19.setText("jLabel19");

        jLabel20.setText("jLabel20");

        jLabel21.setText("jLabel21");

        jLabel22.setText("jLabel22");

        jLabel23.setText("jLabel23");

        jLabel24.setText("jLabel24");

        jLabel25.setText("jLabel25");

        jLabel26.setText("jLabel26");

        jLabel27.setText("jLabel27");

        jLabel28.setText("jLabel28");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel11)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel19)))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(jLabel1))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel22)))
                        .addGap(228, 228, 228)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel9)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel26)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel27)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel21)
                                        .addComponent(jLabel22)
                                        .addComponent(jLabel23)
                                        .addComponent(jLabel24)
                                        .addComponent(jLabel25)
                                        .addComponent(jLabel26)
                                        .addComponent(jLabel27))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel6)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addGap(54, 54, 54)))
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (dati.getTurno() == true) {
            if (pp3 != 0) {
                try {
                    jProgressBar2.setValue(jProgressBar2.getValue() - dati.getListapokemonlotta().get(0).mossa3.power);
                    jLabel8.setText(jProgressBar2.getValue() + "/" + jProgressBar2.getMaximum());
                    if (jProgressBar2.getValue() < ((50 * jProgressBar2.getMaximum()) / 100)) {
                        jProgressBar2.setForeground(Color.YELLOW);

                    }
                    if (jProgressBar2.getValue() < ((20 * jProgressBar2.getMaximum()) / 100)) {
                        jProgressBar2.setForeground(Color.RED);

                    }
                    dati.manda("m;" + dati.getpNoi().getIpAndport() + ";" + dati.getListapokemonlotta().get(0).mossa3.id);

                } catch (IOException ex) {
                    Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
                }
                dati.setTurno(false);
                pp3--;
                initPokemonInCampo(dati);
                attesaTurno(dati);

            } else {
                JOptionPane.showMessageDialog(null, dati.getListapokemonlotta().get(0).mossa3.ename + " non ha pi?? pp");
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (dati.getTurno() == true) {
            if (pp1 != 0) {
                try {
                    // TODO add your handling code here:
                    //JPeer p = new JPeer();
                    jProgressBar2.setValue(jProgressBar2.getValue() - dati.getListapokemonlotta().get(0).mossa1.power);
                    jLabel8.setText(jProgressBar2.getValue() + "/" + jProgressBar2.getMaximum());
                    if (jProgressBar2.getValue() < ((50 * jProgressBar2.getMaximum()) / 100)) {
                        jProgressBar2.setForeground(Color.YELLOW);

                    }
                    if (jProgressBar2.getValue() < ((20 * jProgressBar2.getMaximum()) / 100)) {
                        jProgressBar2.setForeground(Color.RED);

                    }
                    dati.manda("m;" + dati.getpNoi().getIpAndport() + ";" + dati.getListapokemonlotta().get(0).mossa1.id);
                    //dati.ricevi();

                } catch (IOException ex) {
                    Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
                }
                dati.setTurno(false);
                pp1--;
                initPokemonInCampo(dati);
                attesaTurno(dati);
            } else {
                JOptionPane.showMessageDialog(null, dati.getListapokemonlotta().get(0).mossa1.ename + " non ha pi?? pp");
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (dati.getTurno() == true) {
            if (pp4 != 0) {
                try {
                    // TODO add your handling code here:
                    //JPeer p = new JPeer();
                    jProgressBar2.setValue(jProgressBar2.getValue() - dati.getListapokemonlotta().get(0).mossa4.power);
                    jLabel8.setText(jProgressBar2.getValue() + "/" + jProgressBar2.getMaximum());
                    if (jProgressBar2.getValue() < ((50 * jProgressBar2.getMaximum()) / 100)) {
                        jProgressBar2.setForeground(Color.YELLOW);

                    }
                    if (jProgressBar2.getValue() < ((20 * jProgressBar2.getMaximum()) / 100)) {
                        jProgressBar2.setForeground(Color.RED);

                    }
                    dati.manda("m;" + dati.getpNoi().getIpAndport() + ";" + dati.getListapokemonlotta().get(0).mossa4.id);
                    //dati.ricevi();

                } catch (IOException ex) {
                    Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
                }
                dati.setTurno(false);
                pp4--;
                initPokemonInCampo(dati);
                attesaTurno(dati);
            } else {
                JOptionPane.showMessageDialog(null, dati.getListapokemonlotta().get(0).mossa4.ename + " non ha pi?? pp");
            }
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        if (dati.getTurno() == true) {
            if (pp2 != 0) {
                try {
                    // TODO add your handling code here:
                    //JPeer p = new JPeer();
                    jProgressBar2.setValue(jProgressBar2.getValue() - dati.getListapokemonlotta().get(0).mossa2.power);
                    jLabel8.setText(jProgressBar2.getValue() + "/" + jProgressBar2.getMaximum());
                    if (jProgressBar2.getValue() < ((50 * jProgressBar2.getMaximum()) / 100)) {
                        jProgressBar2.setForeground(Color.YELLOW);

                    }
                    if (jProgressBar2.getValue() < ((20 * jProgressBar2.getMaximum()) / 100)) {
                        jProgressBar2.setForeground(Color.RED);

                    }
                    dati.manda("m;" + dati.getpNoi().getIpAndport() + ";" + dati.getListapokemonlotta().get(0).mossa2.id);
                    //dati.ricevi();

                } catch (IOException ex) {
                    Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
                }
                dati.setTurno(false);
                pp2--;
                initPokemonInCampo(dati);
                attesaTurno(dati);
            } else {
                JOptionPane.showMessageDialog(null, dati.getListapokemonlotta().get(0).mossa2.ename + " non ha pi?? pp");
            }
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFormLotta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFormLotta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFormLotta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFormLotta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFormLotta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    // End of variables declaration//GEN-END:variables
}
