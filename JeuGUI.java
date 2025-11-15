/*

 * TP2 - Jeu ±123D
 *
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class JeuGUI extends JFrame implements ActionListener, Joueur{
    /** Le plateau courant */
    private Plateau plateau = new Plateau();
    
    // Composants GUI : afficheur, boutons, label, etc.
    AfficheurPlateau affichePlateau;
    private JPanel pactions;
    private JButton bConfiguration;
    private JButton bNP;
    private JButton bArreter;
    private JButton bmD, bm3, bm2, bm1, bp1, bp2, bp3, bpD;
    private JLabel lStatus;
    
    // Les 2 joueurs actuels. Sont créés à la configuration. Si null ==> manuel (humain)
    Joueur jA=this, jB=this;
    /** Temps alloué de réflexion en millisecondes (ms). Non applicable humain.*/
    int dureeRelexion = 5000;
    
    public JeuGUI(){
        super();
        setTitle("Jeu ±123D  (version 3)");
        affichePlateau = new AfficheurPlateau();
        affichePlateau.setPlateau(plateau);
        JPanel pp = new JPanel(new BorderLayout());
        pactions = new JPanel(new FlowLayout());
        pactions.add(bConfiguration = new JButton("Configuration"));
        bConfiguration.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                configurer();
            }
        });
        pactions.add(bNP = new JButton("Nouvelle partie"));
        bNP.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                nouvellePartie();
            }
        });
        pactions.add(bArreter = new JButton("Arrêter"));
        bArreter.setEnabled(false);
        bArreter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arreterPartie();
            }
        });
        ajouterBoutonAction(bmD = new JButton("-D"));
        ajouterBoutonAction(bm3 = new JButton("-3"));
        ajouterBoutonAction(bm2 = new JButton("-2"));
        ajouterBoutonAction(bm1 = new JButton("-1"));
        ajouterBoutonAction(bp1 = new JButton("+1"));
        ajouterBoutonAction(bp2 = new JButton("+2"));
        ajouterBoutonAction(bp3 = new JButton("+3"));
        ajouterBoutonAction(bpD = new JButton("+D"));
        pactions.add(lStatus = new JLabel("Prochain joueur: ??"));
        pp.add(pactions, BorderLayout.NORTH);
        JScrollPane sp = new JScrollPane(affichePlateau);
        pp.add(sp, BorderLayout.CENTER);
        setContentPane(pp);
        setPreferredSize(new Dimension(1300, 200));
        plateau.jeugui = this;
    }
    
    private void ajouterBoutonAction(JButton b){
        pactions.add(b);
        b.setEnabled(false);
        b.addActionListener(this);
    }
    private void desactiverBoutonsAction(){
        bmD.setEnabled(false);
        bm3.setEnabled(false);
        bm2.setEnabled(false);
        bm1.setEnabled(false);
        bp1.setEnabled(false);
        bp1.setEnabled(false);
        bp2.setEnabled(false);
        bp3.setEnabled(false);
        bpD.setEnabled(false);
    }
    
    public static void main(String[] args){
        JeuGUI frame = new JeuGUI();
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /** Contient l'action liée au bouton cliqué. Seulement joueur humain (manuel).*/
    public String prochaineActionGUI = null;

    /** La méthode actionPerformed est appelée lorsqu'on clique sur un bouton 
     * d'action. Voir ajouterBoutonAction. */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() instanceof JButton){
            JButton b = (JButton) ae.getSource();
            prochaineActionGUI = b.getText();
            synchronized(this) { 
                // Réveiller le thread de jeu bloqué à getProchaineAction.
                notifyAll(); 
            }
        }
    }
    private ConfigDialog configDialog = new ConfigDialog(this, true);
    
    private void configurer(){
        configDialog.setVisible(true);
        int taille = configDialog.getTaillePlateau();
        plateau = new Plateau(taille);
        plateau.jeugui = JeuGUI.this;
        affichePlateau.setPlateau(plateau);
        try{
            jA = configDialog.getJoueurA();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur d'initialisation de joueur", JOptionPane.ERROR_MESSAGE);
        }
        try{
            jB = configDialog.getJoueurB();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur d'initialisation de joueur", JOptionPane.ERROR_MESSAGE);
        }
        if(jA==null || jB==null)
            plateau.dureePauseParTour = 0;
        else
            plateau.dureePauseParTour = 100;
        if(jA==null) jA = JeuGUI.this;
        if(jB==null) jB = JeuGUI.this;
        dureeRelexion = configDialog.getDureeReflexion();
        plateau.retardTolere = configDialog.ignorerRetardCB.isSelected();
    }
    
    /** Fil d'exécution (thread) qui gère une partie (ultimement appelle Plateau.jouerPartie).*/
    Thread threadPartie;
    
    /** Lorsque le bouton nouvelle partie est cliqué. */
    private void nouvellePartie(){
        // Bloquer un 2e clic et la configuration durant la partie.
        bNP.setEnabled(false);
        bConfiguration.setEnabled(false);
        bArreter.setEnabled(true);
        // Lancer la partie
        threadPartie = new Thread(new Runnable() {
            @Override
            public void run() {
                // Réinitialiser les cases brûlées.
                plateau.reinitialiser();
                affichePlateau.repaint();
                prochaineActionGUI = null;
                try{
                    plateau.jouerPartie(jA, jB, dureeRelexion);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(JeuGUI.this, e);
                    e.printStackTrace();
                }
                // Débloquer les boutons.
                bArreter.setEnabled(false);
                bNP.setEnabled(true);
                bConfiguration.setEnabled(true);
                desactiverBoutonsAction();
            }
        });
        threadPartie.start();
    }
    
    public void arreterPartie(){
        prochaineActionGUI = "--";
        synchronized(this) { notifyAll();}
        plateau.forcerArretPartie = true;
    }
    
    /** Met à jour le statut des boutons et indication de c'est qui à jouer */
    public void updateJeu(char prochainJoueur){
        affichePlateau.repaint();
        lStatus.setText("Prochain joueur: " + prochainJoueur);
        if(prochainJoueur=='A' || prochainJoueur=='B'){
            if((prochainJoueur=='A' ? jA : jB) != this){
                desactiverBoutonsAction();
            }else{
            bmD.setEnabled(plateau.actionDFaisable(prochainJoueur, false));
            bm3.setEnabled(plateau.action123Faisable(prochainJoueur, -3));
            bm2.setEnabled(plateau.action123Faisable(prochainJoueur, -2));
            bm1.setEnabled(plateau.action123Faisable(prochainJoueur, -1));
            bp1.setEnabled(plateau.action123Faisable(prochainJoueur, +1));
            bp2.setEnabled(plateau.action123Faisable(prochainJoueur, +2));
            bp3.setEnabled(plateau.action123Faisable(prochainJoueur, +3));
            bpD.setEnabled(plateau.actionDFaisable(prochainJoueur, true));
            }
        }
    }
    public void montrerGagnant(char gagnant){
        lStatus.setText("Gagnant: " + gagnant);
    }

    @Override
    public synchronized String getProchaineAction(char[] cases, int pmax, int pmin, int pdrap, int dureeR) {
        while(prochaineActionGUI==null)
            try{
                wait();
            }catch(InterruptedException ie){}
        String r = prochaineActionGUI;
        prochaineActionGUI = null;
        return r;
    }
    
    @Override
    public void dispose(){
        // Pour forcer une action si le thread de jeu attend une action d'un joueur humain.
        System.out.println("DISPOSE");
        prochaineActionGUI = "--";
        synchronized(this) { notifyAll();}
        super.dispose();
    }

    /** getAuteurs est une méthode de l'interface joueur. Pas vraiment utilisée ici. */
    @Override
    public String getAuteurs() {
        return "GUI";
    }
    
}
