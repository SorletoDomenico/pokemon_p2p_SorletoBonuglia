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

    public JFormLotta(JDatiCondivisiConnessione dati) throws IOException {
        this.dati = dati;
        initComponents();
        setPP();
        initHP(this.dati);
        initPokemonInCampo(this.dati);
        initColorButton(this.dati);
        //richiamiamo metodo manda pokemon che abbiamo in campo
        dati.manda("p;" + dati.getpNoi().getPorte() + ";" + dati.getListapokemonlotta().get(0).pokemon.id.toString());
        //ricevo il pokemon dell'avversario
        dati.ricevi();

        initPokemonAvversario(this.dati);
        initHPAvversario(this.dati);
        dati.setTurno(true);
        dati.ChiInizia();
        if (dati.getTurno() == false) {
            attesaTurno(dati);
        }
    }

    private JFormLotta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void initResetPokemon(JDatiCondivisiConnessione dati) throws IOException {

        if (jProgressBar1.getValue() <= 0) {
            dati.getListapokemonlotta().remove(0);
            //mando il pokemon nuovo all'avversario
            dati.manda("p;" + dati.getpNoi().getPorte() + ";" + dati.getListapokemonlotta().get(0).pokemon.id.toString());
            setPP();
            initHP(dati);
            initPokemonInCampo(dati);
            initColorButton(dati);
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

        Thread Tattesa = new Thread("New Thread") {
            public void run() {
                do {
                    String[] v = dati.getTemp();
                    try {
                        dati.ricevi();
                    } catch (IOException ex) {
                        Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
                    
                    if ("m".equals(v[0])) {
                        mA = dati.searchMossa(Integer.parseInt(v[2].trim()));
                        jProgressBar1.setValue(jProgressBar1.getValue() - mA.power); //mA.Power
                        dati.setTurno(true);
                        try {
                            initResetPokemon(dati);
                        } catch (IOException ex) {
                            Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (!dati.getTurno());
                blocca(true);
                // initPokemonInCampo(dati);
                //  initPokemonAvversario(dati);
            }
        };
        Tattesa.start();
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
            .addGroup(layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1)))
                .addGap(221, 221, 221)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel6)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

                    // TODO add your handling code here:
                    //JPeer p = new JPeer();
                    jProgressBar2.setValue(jProgressBar2.getValue() - dati.getListapokemonlotta().get(0).mossa3.power);
                    jLabel8.setText(jProgressBar2.getValue() + "/" + jProgressBar2.getMaximum());
                    dati.manda("m;" + dati.getpNoi().getPorte() + ";" + dati.getListapokemonlotta().get(0).mossa3.id);

                    dati.setTurno(false);
                     pp3--;
                initPokemonInCampo(dati);
                    attesaTurno(dati);
                } catch (IOException ex) {
                    Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            } else {
                JOptionPane.showMessageDialog(null, dati.getListapokemonlotta().get(0).mossa3.ename + " non ha più pp");
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
                    dati.manda("m;" + dati.getpNoi().getPorte() + ";" + dati.getListapokemonlotta().get(0).mossa1.id);
                    dati.ricevi();

                    dati.setTurno(false);
                    attesaTurno(dati);

                } catch (IOException ex) {
                    Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
                }
                pp1--;
                initPokemonInCampo(dati);
            } else {
                JOptionPane.showMessageDialog(null, dati.getListapokemonlotta().get(0).mossa1.ename + " non ha più pp");
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
                    dati.manda("m;" + dati.getpNoi().getPorte() + ";" + dati.getListapokemonlotta().get(0).mossa4.id);
                    dati.ricevi();

                    dati.setTurno(false);
                    attesaTurno(dati);
                } catch (IOException ex) {
                    Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
                }
                pp4--;
                initPokemonInCampo(dati);
            } else {
                JOptionPane.showMessageDialog(null, dati.getListapokemonlotta().get(0).mossa4.ename + " non ha più pp");
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
                    dati.manda("m;" + dati.getpNoi().getPorte() + ";" + dati.getListapokemonlotta().get(0).mossa2.id);
                    dati.ricevi();

                    dati.setTurno(false);
                    attesaTurno(dati);
                } catch (IOException ex) {
                    Logger.getLogger(JFormLotta.class.getName()).log(Level.SEVERE, null, ex);
                }
                pp2--;
                initPokemonInCampo(dati);
            } else {
                JOptionPane.showMessageDialog(null, dati.getListapokemonlotta().get(0).mossa2.ename + " non ha più pp");
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
    private javax.swing.JLabel jLabel2;
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
