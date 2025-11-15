/*
 * Jeu ±123D
 *

/** Interface Joueur que doit implémenter tous les joueurs, dont JoueurArtificiel
 * (la classe où coder l'IA), JeuGUI (le joueur humain), les interfaces externes
 * JoueurInterfaceCmd (ex.: programme C++), JoueurInterfaceSocket, etc.
 *
 */
public interface Joueur {
    public String getProchaineAction(char[] cases, int pmax, int pmin, int pdrap, int dureeR) ;
    public String getAuteurs();
}
