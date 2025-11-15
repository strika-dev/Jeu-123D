/*
 * Jeu ±123D
 *
 */

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/** Composant permettant d'afficher un plateau de jeu dans Java Swing.
 *
 */
public class AfficheurPlateau extends JPanel{
    Plateau plateau;
    /** Nombre de pixels par case sur l'axe des X. */
    int echelleX = 25;
    /** Hauteur du rectange des cases sur l'axe des Y. */
    int echelleY = 55;
    
    public AfficheurPlateau(){
        setBackground(Color.white);
        setMinimumSize(new Dimension(200, 70));
        setPreferredSize(new Dimension(200, 80));
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    }
    
    public void setPlateau(Plateau p){
        plateau = p;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(plateau==null)
            return;
        // Dessiner les lignes.
        g.setColor(Color.BLACK);
        g.drawLine(0, 0, echelleX*plateau.cases.length, 0);
        g.drawLine(0, echelleY, echelleX*plateau.cases.length, echelleY);
        for(int i=0;i<=plateau.cases.length;i++){
            int x = i*echelleX;
            g.drawLine(x, 0, x, echelleY);
        }
        // Écrire les numéros de cases.
        g.setColor(Color.DARK_GRAY);
        for(int i=0;i<plateau.cases.length;i++){
            int x = i*echelleX;
            g.drawString("" + i, x, echelleY + 16);
        }
        // Mettre en gris les cases brûlées.
        g.setColor(Color.LIGHT_GRAY);
        for(int i=0;i<plateau.cases.length;i++){
            int x = i*echelleX;
            if(plateau.cases[i]=='x')
                g.fillRect(x+1, 1, echelleX-1, echelleY-1);
        }
        // Dessiner le drapeau (pour l'instant, un rectangle).
        {
            g.setColor(Color.GREEN);
            int x = plateau.posDrapeau*echelleX + echelleX*2/3 - 7;
            int y = 2;
            g.fillRect(x, y, 15, 15);
            g.setColor(Color.GREEN.darker());
            g.drawRect(x, y, 15, 15);
        }
        // Tracer les deux joueurs.
        for(int j=0;j<2;j++){
            int x = plateau.posJoueurs[j]*echelleX;
            int y = j*(echelleY/2);
            Color c = j==0 ? Color.BLUE : Color.RED;
            g.setColor(c);
            g.fillOval(x+1, y+1, echelleX-5, echelleX-5);
            g.setColor(c.darker());
            g.drawOval(x+1, y+1, echelleX-5, echelleX-5);
            g.setColor(Color.WHITE);
            g.drawString("" + (char)('A'+j), x+6, y+15);
        }
    }
}
