/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon_p2p;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.accessibility.AccessibleRole;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import static javax.swing.UIManager.get;
import javax.swing.event.ListDataListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author sorleto_domenico
 */
public class JFormSelezionaSquadra extends javax.swing.JFrame {

    /**
     * Creates new form JFormSelezionaSquadra
     */
    JDatiCondivisiConnessione dati;
    DefaultListModel model = new DefaultListModel();

    public JFormSelezionaSquadra(JDatiCondivisiConnessione dati) {
        this.dati = dati;
        initComponents();
        initLista(this.dati);
        initSquadra(this.dati);

    }

    private JFormSelezionaSquadra() {

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void initLista(JDatiCondivisiConnessione dati) {
        ArrayList<JPokemon> lista = dati.getListapokemon();
        Listapokemonfisica.setModel(model);
        
        for (int i = 0; i < lista.size(); i++) {
            model.addElement(lista.get(i).name + " " + lista.get(i).HP+"hp "+ lista.get(i).type1+" "+lista.get(i).type2);
        }
    }

    private void initSquadra(JDatiCondivisiConnessione dati) {

        for (int i = 0; i < dati.getListapokemonSelezionati().size(); i++) {
            if (i == 0) {
                String imagePath = dati.getListapokemonSelezionati().get(i).hires;
                Image myPicture;
                jLabel1.setText(dati.getListapokemonSelezionati().get(i).name);
                jLabel1.setVisible(true);
                try {
                    myPicture = ImageIO.read(new File(imagePath));
                    Image newImage = myPicture.getScaledInstance(jLabel7.getWidth(), jLabel7.getHeight(), Image.SCALE_DEFAULT);
                    ImageIcon icon = new ImageIcon(newImage);
                    jLabel7.setIcon(icon);
                    jLabel7.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (i == 1) {
                String imagePath = dati.getListapokemonSelezionati().get(i).hires;
                Image myPicture;
                jLabel2.setText(dati.getListapokemonSelezionati().get(i).name);
                jLabel2.setVisible(true);
                try {
                    myPicture = ImageIO.read(new File(imagePath));
                    Image newImage = myPicture.getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_DEFAULT);
                    ImageIcon icon = new ImageIcon(newImage);
                    jLabel8.setIcon(icon);
                    jLabel8.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (i == 2) {
                String imagePath = dati.getListapokemonSelezionati().get(i).hires;
                Image myPicture;
                jLabel3.setText(dati.getListapokemonSelezionati().get(i).name);
                jLabel3.setVisible(true);
                try {
                    myPicture = ImageIO.read(new File(imagePath));
                    Image newImage = myPicture.getScaledInstance(jLabel9.getWidth(), jLabel9.getHeight(), Image.SCALE_DEFAULT);
                    ImageIcon icon = new ImageIcon(newImage);
                    jLabel9.setIcon(icon);
                    jLabel9.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (i == 3) {
                String imagePath = dati.getListapokemonSelezionati().get(i).hires;
                Image myPicture;
                jLabel4.setText(dati.getListapokemonSelezionati().get(i).name);
                jLabel4.setVisible(true);
                try {
                    myPicture = ImageIO.read(new File(imagePath));
                    Image newImage = myPicture.getScaledInstance(jLabel10.getWidth(), jLabel10.getHeight(), Image.SCALE_DEFAULT);
                    ImageIcon icon = new ImageIcon(newImage);
                    jLabel10.setIcon(icon);
                    jLabel10.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (i == 4) {
                String imagePath = dati.getListapokemonSelezionati().get(i).hires;
                Image myPicture;
                jLabel5.setText(dati.getListapokemonSelezionati().get(i).name);
                jLabel5.setVisible(true);
                try {
                    myPicture = ImageIO.read(new File(imagePath));
                    Image newImage = myPicture.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_DEFAULT);
                    ImageIcon icon = new ImageIcon(newImage);
                    jLabel11.setIcon(icon);
                    jLabel11.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (i == 5) {
                String imagePath = dati.getListapokemonSelezionati().get(i).hires;
                Image myPicture;
                jLabel6.setText(dati.getListapokemonSelezionati().get(i).name);
                jLabel6.setVisible(true);
                try {
                    myPicture = ImageIO.read(new File(imagePath));
                    Image newImage = myPicture.getScaledInstance(jLabel12.getWidth(), jLabel12.getHeight(), Image.SCALE_DEFAULT);
                    ImageIcon icon = new ImageIcon(newImage);
                    jLabel12.setIcon(icon);
                    jLabel12.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

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

        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Listapokemonfisica = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("Conferma");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(Listapokemonfisica);

        jButton1.setText("Aggiungi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Rimuovi");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jButton2)
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addGap(71, 71, 71))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(dati.getListapokemonSelezionati().size() == 6){ 
            JFormMenu jfm = new JFormMenu(dati);
        jfm.setVisible(true);

        this.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "Devi scegliere almeno 6 pokemon!");
        }
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //aggiunta pokemon selezionati ad una lista

        if (dati.getListapokemonSelezionati().size() < 6) {
            dati.getListapokemonSelezionati().add(dati.getListapokemon().get(Listapokemonfisica.getSelectedIndex()));
            for (int i = 0; i < dati.getListapokemonSelezionati().size(); i++) {
                if (i == 0) {
                    String imagePath = dati.getListapokemonSelezionati().get(i).hires;
                    Image myPicture;
                    jLabel1.setText(dati.getListapokemonSelezionati().get(i).name);
                    jLabel1.setVisible(true);
                    try {
                        myPicture = ImageIO.read(new File(imagePath));
                        Image newImage = myPicture.getScaledInstance(jLabel7.getWidth(), jLabel7.getHeight(), Image.SCALE_DEFAULT);
                        ImageIcon icon = new ImageIcon(newImage);
                        jLabel7.setIcon(icon);
                        jLabel7.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (i == 1) {
                    String imagePath = dati.getListapokemonSelezionati().get(i).hires;
                    Image myPicture;
                    jLabel2.setText(dati.getListapokemonSelezionati().get(i).name);
                    jLabel2.setVisible(true);
                    try {
                        myPicture = ImageIO.read(new File(imagePath));
                        Image newImage = myPicture.getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_DEFAULT);
                        ImageIcon icon = new ImageIcon(newImage);
                        jLabel8.setIcon(icon);
                        jLabel8.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (i == 2) {
                    String imagePath = dati.getListapokemonSelezionati().get(i).hires;
                    Image myPicture;
                    jLabel3.setText(dati.getListapokemonSelezionati().get(i).name);
                    jLabel3.setVisible(true);
                    try {
                        myPicture = ImageIO.read(new File(imagePath));
                        Image newImage = myPicture.getScaledInstance(jLabel9.getWidth(), jLabel9.getHeight(), Image.SCALE_DEFAULT);
                        ImageIcon icon = new ImageIcon(newImage);
                        jLabel9.setIcon(icon);
                        jLabel9.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (i == 3) {
                    String imagePath = dati.getListapokemonSelezionati().get(i).hires;
                    Image myPicture;
                    jLabel4.setText(dati.getListapokemonSelezionati().get(i).name);
                    jLabel4.setVisible(true);
                    try {
                        myPicture = ImageIO.read(new File(imagePath));
                        Image newImage = myPicture.getScaledInstance(jLabel10.getWidth(), jLabel10.getHeight(), Image.SCALE_DEFAULT);
                        ImageIcon icon = new ImageIcon(newImage);
                        jLabel10.setIcon(icon);
                        jLabel10.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (i == 4) {
                    String imagePath = dati.getListapokemonSelezionati().get(i).hires;
                    Image myPicture;
                    jLabel5.setText(dati.getListapokemonSelezionati().get(i).name);
                    jLabel5.setVisible(true);
                    try {
                        myPicture = ImageIO.read(new File(imagePath));
                        Image newImage = myPicture.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_DEFAULT);
                        ImageIcon icon = new ImageIcon(newImage);
                        jLabel11.setIcon(icon);
                        jLabel11.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (i == 5) {
                    String imagePath = dati.getListapokemonSelezionati().get(i).hires;
                    Image myPicture;
                    jLabel6.setText(dati.getListapokemonSelezionati().get(i).name);
                    jLabel6.setVisible(true);
                    try {
                        myPicture = ImageIO.read(new File(imagePath));
                        Image newImage = myPicture.getScaledInstance(jLabel12.getWidth(), jLabel12.getHeight(), Image.SCALE_DEFAULT);
                        ImageIcon icon = new ImageIcon(newImage);
                        jLabel12.setIcon(icon);
                        jLabel12.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        }else{
        JOptionPane.showMessageDialog(null, "Hai già 6 pokemon!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //rimuovere un pokemon selezionato
        if (!dati.getListapokemonSelezionati().isEmpty()) {

            if (dati.getListapokemonSelezionati().size() == 1) {
                dati.getListapokemonSelezionati().remove(0);
                jLabel1.setText("");
                jLabel1.setVisible(false);
                jLabel7.setIcon(null);
            }
            if (dati.getListapokemonSelezionati().size() == 2) {
                dati.getListapokemonSelezionati().remove(1);
                jLabel2.setText("");
                jLabel2.setVisible(false);
                jLabel8.setIcon(null);
            }
            if (dati.getListapokemonSelezionati().size() == 3) {
                dati.getListapokemonSelezionati().remove(2);
                jLabel3.setText("");
                jLabel3.setVisible(false);
                jLabel9.setIcon(null);
            }
            if (dati.getListapokemonSelezionati().size() == 4) {
                dati.getListapokemonSelezionati().remove(3);
                jLabel4.setText("");
                jLabel4.setVisible(false);
                jLabel10.setIcon(null);
            }

            if (dati.getListapokemonSelezionati().size() == 5) {
                dati.getListapokemonSelezionati().remove(4);
                jLabel5.setText("");
                jLabel5.setVisible(false);
                jLabel11.setIcon(null);
            }

            if (dati.getListapokemonSelezionati().size() == 6) {
                dati.getListapokemonSelezionati().remove(5);
                jLabel6.setText("");
                jLabel6.setVisible(false);
                jLabel12.setIcon(null);

            }
        }else{
        JOptionPane.showMessageDialog(null, "Non hai pokemon da eliminare!");
        }


    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFormSelezionaSquadra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new JFormSelezionaSquadra().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> Listapokemonfisica;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
