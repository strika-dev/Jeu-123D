/*
 * Jeu ±123D
 *
 */

import javax.swing.JFileChooser;

/** Boîte de configuration.
 *
 */
public class ConfigDialog extends javax.swing.JDialog {

    ConfigJoueurPanel jcA;
    ConfigJoueurPanel jcB;

    /** Boîte de configuration ConfigDialog. */
    public ConfigDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        jcA = new ConfigJoueurPanel();
        jcB = new ConfigJoueurPanel();

        jcA.setBorder(javax.swing.BorderFactory.createTitledBorder("Joueur A (bleu)"));
        jcB.setBorder(javax.swing.BorderFactory.createTitledBorder("Joueur B (rouge)"));

        jPanel1.add(jcA);
        jPanel1.add(jcB);
        pack();
    }

    public Joueur getJoueurA() throws Exception{
        return jcA.getJoueur();
    }
    public Joueur getJoueurB() throws Exception{
        return jcB.getJoueur();
    }
    public int getTaillePlateau(){
        return Integer.parseInt(tailleTF.getText());
    }
    public int getDureeReflexion(){
        return Integer.parseInt(delaisReflexion.getText());
    }

    /** This method is called from within the constructor to
     * initialize the form.
    */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tailleTF = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        delaisReflexion = new javax.swing.JTextField();
        ignorerRetardCB = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        tolerate50pCB = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuration");

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jPanel4.setPreferredSize(new java.awt.Dimension(0, 100));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.X_AXIS));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Plateau"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Taille :");
        jPanel2.add(jLabel3, new java.awt.GridBagConstraints());

        tailleTF.setText("50");
        tailleTF.setMinimumSize(new java.awt.Dimension(80, 25));
        tailleTF.setPreferredSize(new java.awt.Dimension(80, 25));
        tailleTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tailleTFActionPerformed(evt);
            }
        });
        jPanel2.add(tailleTF, new java.awt.GridBagConstraints());

        jPanel4.add(jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Temps de réflexion (calcul)"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Durée : ");
        jPanel3.add(jLabel5, new java.awt.GridBagConstraints());

        delaisReflexion.setText("5000");
        delaisReflexion.setMinimumSize(new java.awt.Dimension(100, 20));
        delaisReflexion.setPreferredSize(new java.awt.Dimension(80, 20));
        delaisReflexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delaisReflexionActionPerformed(evt);
            }
        });
        jPanel3.add(delaisReflexion, new java.awt.GridBagConstraints());

        ignorerRetardCB.setText("Ignorer les dépassements (utile pour développement)");
        ignorerRetardCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ignorerRetardCBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(ignorerRetardCB, gridBagConstraints);

        jLabel6.setText(" millisecondes (ms)");
        jPanel3.add(jLabel6, new java.awt.GridBagConstraints());

        tolerate50pCB.setText("Tolérer jusqu'à 50% de retard et pénaliser le coup suivant");
        tolerate50pCB.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(tolerate50pCB, gridBagConstraints);

        jPanel4.add(jPanel3);

        jPanel5.setPreferredSize(new java.awt.Dimension(100, 10));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Fermer");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new java.awt.GridBagConstraints());

        jPanel4.add(jPanel5);

        getContentPane().add(jPanel4, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tailleTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tailleTFActionPerformed
        
}//GEN-LAST:event_tailleTFActionPerformed

    private void delaisReflexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delaisReflexionActionPerformed
    
}//GEN-LAST:event_delaisReflexionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ignorerRetardCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ignorerRetardCBActionPerformed
    }//GEN-LAST:event_ignorerRetardCBActionPerformed
    
    JFileChooser filechooser = new JFileChooser();
    
    // Variables declaration 
    protected javax.swing.JTextField delaisReflexion;
    public javax.swing.JCheckBox ignorerRetardCB;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    protected javax.swing.JTextField tailleTF;
    public javax.swing.JCheckBox tolerate50pCB;
    
    
}
