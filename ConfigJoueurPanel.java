/*
 * TP2 - Jeu ±123D
 */
import javax.swing.JFileChooser;


public class ConfigJoueurPanel extends javax.swing.JPanel {

    static JFileChooser filechooser = new JFileChooser();
    
    /**
     * Creates new form ConfigPanelJoueur
     */
    public ConfigJoueurPanel() {
        initComponents();
    }
    
    public Joueur getJoueur() throws Exception{
        if(manuelRB.isSelected())
            return null; // null sera converti en pointeur sur JeuGUI
        if(aleatoireRB.isSelected())
            return new JoueurAleatoire();
        if(interneRB.isSelected())
            return new JoueurArtificiel();
        if(externeCmdRB.isSelected())
            return new JoueurInterfaceCmd(commandeTF.getText());
        if(externeDistantRB.isSelected()){
            return new JoueurInterfaceSocket(hoteTF.getText(), Integer.parseInt(portTF.getText()));
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        interfaceJoueurGroup = new javax.swing.ButtonGroup();
        manuelRB = new javax.swing.JRadioButton();
        interneRB = new javax.swing.JRadioButton();
        externeCmdRB = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        commandeTF = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        debugCheckBox = new javax.swing.JCheckBox();
        externeDistantRB = new javax.swing.JRadioButton();
        hoteTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        portTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        aleatoireRB = new javax.swing.JRadioButton();

        interfaceJoueurGroup.add(manuelRB);
        manuelRB.setSelected(true);
        manuelRB.setText("Humain (manuel, par clic de la souris)  ");
        manuelRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manuelRBActionPerformed(evt);
            }
        });

        interfaceJoueurGroup.add(interneRB);
        interneRB.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        interneRB.setForeground(new java.awt.Color(204, 0, 51));
        interneRB.setText("Joueur artificiel interne (classe JoueurArtificiel)    ");
        interneRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interneRBActionPerformed(evt);
            }
        });

        interfaceJoueurGroup.add(externeCmdRB);
        externeCmdRB.setText("Joueur artificiel programme externe (interface stdin / stdout - classe JoueurInterfaceCmd)  [à venir]");
        externeCmdRB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                externeCmdRBStateChanged(evt);
            }
        });
        externeCmdRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                externeCmdRBActionPerformed(evt);
            }
        });

        jLabel1.setText("Commande: ");

        commandeTF.setText("/home/user/inf4230/tp2/tp2");
        commandeTF.setEnabled(false);
        commandeTF.setMinimumSize(new java.awt.Dimension(250, 22));
        commandeTF.setPreferredSize(new java.awt.Dimension(240, 22));
        commandeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commandeTFActionPerformed(evt);
            }
        });

        browseButton.setText("...");
        browseButton.setEnabled(false);
        browseButton.setMinimumSize(new java.awt.Dimension(35, 29));
        browseButton.setPreferredSize(null);
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        debugCheckBox.setText("Mode déboguage  (affiche des traces à la console)  ");
        debugCheckBox.setEnabled(false);
        debugCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugCheckBoxActionPerformed(evt);
            }
        });

        interfaceJoueurGroup.add(externeDistantRB);
        externeDistantRB.setText("Joueur artificiel distant (interface socket - classe JoueurInterfaceSocket)  [à venir]");
        externeDistantRB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                externeDistantRBStateChanged(evt);
            }
        });

        hoteTF.setText("cria2.uqam.ca");
        hoteTF.setEnabled(false);
        hoteTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoteTFActionPerformed(evt);
            }
        });

        jLabel2.setText("Hôte :");

        portTF.setText("1199");
        portTF.setEnabled(false);

        jLabel3.setText("Port :");

        interfaceJoueurGroup.add(aleatoireRB);
        aleatoireRB.setText("Aléatoire (classe JoueurAleatoire, objectif à battre)");
        aleatoireRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aleatoireRBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(debugCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(commandeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(manuelRB)
                            .addComponent(aleatoireRB)
                            .addComponent(interneRB)
                            .addComponent(externeCmdRB)
                            .addComponent(externeDistantRB)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hoteTF, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(portTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 36, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manuelRB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aleatoireRB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(interneRB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(externeCmdRB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(commandeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(debugCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(externeDistantRB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(hoteTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(portTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void manuelRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuelRBActionPerformed
        
    }//GEN-LAST:event_manuelRBActionPerformed

    private void interneRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interneRBActionPerformed
        
    }//GEN-LAST:event_interneRBActionPerformed

    private void externeCmdRBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_externeCmdRBStateChanged
        commandeTF.setEnabled(externeCmdRB.isSelected());
        browseButton.setEnabled(externeCmdRB.isSelected());
    }//GEN-LAST:event_externeCmdRBStateChanged

    private void commandeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commandeTFActionPerformed
    }//GEN-LAST:event_commandeTFActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        if(filechooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
        commandeTF.setText(filechooser.getSelectedFile().getAbsolutePath());
    }//GEN-LAST:event_browseButtonActionPerformed

    private void externeDistantRBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_externeDistantRBStateChanged
        hoteTF.setEnabled(externeDistantRB.isSelected());
        portTF.setEnabled(externeDistantRB.isSelected());
    }//GEN-LAST:event_externeDistantRBStateChanged

    private void hoteTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoteTFActionPerformed
    }//GEN-LAST:event_hoteTFActionPerformed

    private void externeCmdRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_externeCmdRBActionPerformed
    }//GEN-LAST:event_externeCmdRBActionPerformed

    private void aleatoireRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aleatoireRBActionPerformed
    }//GEN-LAST:event_aleatoireRBActionPerformed

    private void debugCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugCheckBoxActionPerformed
    }//GEN-LAST:event_debugCheckBoxActionPerformed


    // Variables declaration
    public javax.swing.JRadioButton aleatoireRB;
    private javax.swing.JButton browseButton;
    public javax.swing.JTextField commandeTF;
    private javax.swing.JCheckBox debugCheckBox;
    public javax.swing.JRadioButton externeCmdRB;
    public javax.swing.JRadioButton externeDistantRB;
    private javax.swing.JTextField hoteTF;
    private javax.swing.ButtonGroup interfaceJoueurGroup;
    public javax.swing.JRadioButton interneRB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JRadioButton manuelRB;
    private javax.swing.JTextField portTF;
    
}
