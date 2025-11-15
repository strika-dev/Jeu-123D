/*
 * Jeu ±123D
 */

/** Pour lancer le jeu en mode console interactive (stdin / stdout).
 *
 */
public class JeuConsole {
       
    public static void main(String[] args) throws Exception {
        Plateau p = new Plateau();
        JoueurConsole jc = new JoueurConsole();
        p.jouerPartie(jc, jc, 30000);
    }
    
}
