/*
 * Jeu ±123D
 *
 */

import java.util.Random;

/** Plateau représente l'environnement de jeu sur une dimension (1D).
 *
 */
public class Plateau {
    // **** ÉTAT du Plateau **********
    /** État des cases
       ' ' : case libre
       'x' : case brûlée
       '_' : trempoline (non utilisée dans le TP2, idée pour le futur)
     */
    char[] cases;
    /** Position des joueurs. A=>[0] B=>[1]. */
    int[]  posJoueurs = new int[2];
    /** Position du drapeau */
    int   posDrapeau;
    // ***********************

    // **** Pour la simulation d'une partie ********
    /** Générateur de nombre aléatoires pour la simulation (actions ±D).*/
    Random random = new Random();
    /** Les retards ne sont pas encore bien gérés. */
    boolean retardTolere = true;
    /** Durée de sleep après chaque tour. Utile pour le GUI quand on fait jouer 2 AI rapides.*/
    int dureePauseParTour = 0; // en MS
    /** Si true : il faut arrêter la partie */
    boolean forcerArretPartie = false;
    
    /** Pour mettre à jour l'interface GUI si elle a été lancée. */
    JeuGUI jeugui = null;
    
    public Plateau(int taille){
        cases = new char[taille];
        for(int i=0;i<cases.length;i++)
            cases[i] = ' ';
        posDrapeau = taille - 1;
    }
    
    public Plateau(char[] cases){
        this.cases = cases;
        posDrapeau = cases.length - 1;
    }
    
    public Plateau(){
        this(50);
    }
    
    /** Réinitialise les cases brûlées. Pour nouvelle partie. */
    public void reinitialiser(){
        for(int i=0;i<cases.length;i++)
            if(cases[i]=='x')
                cases[i] = ' ';
        posJoueurs[0] = posJoueurs[1] = 0;
        posDrapeau = cases.length - 1;
    }
    
    /** Méthode principale pour jouer une partie. Gestion du tour de rôle ici. */
    public char jouerPartie(Joueur A, Joueur B, int dureeReflexionMax) throws Exception {
        System.out.println("Début de la partie...");
        forcerArretPartie = false;
        // ID du joueur à qui c'est rendu le tour ('A' ou 'B'). On commence par A.
        char queljoueur = 'A';
        // ID de l'autre joueur qui attend son tour.
        char autreJoueur = 'B';
        if(jeugui!=null) 
            jeugui.updateJeu(queljoueur);
        char gagnant = '?';
        while(!forcerArretPartie && (gagnant=quiGagne(queljoueur))=='?'){
            System.out.println("Tour à joueur " + queljoueur);
            // Afficher le plateau
            System.out.println(this);
            String action;
            
            // ------ OBTENIR LA PROCHAINE ACTION -------
            // Noter le temps de début.
            long tempsDebut = System.currentTimeMillis();
            if(queljoueur=='A')
                action = A.getProchaineAction(cases, posJoueurs[0], posJoueurs[1], posDrapeau, dureeReflexionMax);
            else
                // Important : Inversion des positions pour adopter une perspective max.
                action = B.getProchaineAction(cases, posJoueurs[1], posJoueurs[0], posDrapeau, dureeReflexionMax);
            // Noter le temps de fin (utilie pour vérifier si le délai a été dépassé.
            long tempsFin = System.currentTimeMillis();
            long duree = (tempsFin-tempsDebut);
            System.out.println("  Action: " + action + "  (en " + duree + " ms)");
            if(duree>=dureeReflexionMax){
                System.out.println("Le joueur " + queljoueur + " a mis trop de temps à jouer !");
                if((queljoueur=='A' ? A : B) instanceof JeuGUI || (queljoueur=='A' ? A : B) instanceof  JoueurConsole){
                    System.out.println("Joueur humain : on tollère le retard !");
                }else if(!retardTolere){
                    System.out.println("Forcer la défaite!");
                    cases[posJoueurs[queljoueur-'A']] = 'x'; // forcer la défaite
                    gagnant = autreJoueur;
                    break;
                }
            }
            
            if(action==null || action.length()<2){
                System.out.println("Action \"" + action +"\" invalide!");
                cases[posJoueurs[queljoueur-'A']] = 'x'; // forcer la défaite
                gagnant = autreJoueur;
                break;
            }
            
            // ---- EXÉCUTION DE L'ACTION COURANTE ------
            char c1 = action.charAt(0);
            char c2 = action.charAt(1);
            if(c1!='+' && c1!='-'){
                System.out.println("Le premier caractère doit être + ou -, pas '" + c1 +"'!");
                cases[posJoueurs[queljoueur-'A']] = 'x'; // forcer la défaite
                gagnant = autreJoueur;
                break;
            }
            // Décodage de l'action
            int nb; // nombre de cases à reculer ou avancer
            if(c2>='1' && c2<='3'){
                nb = c2-'0';
            }else if(c2=='D')
                nb = 4 + random.nextInt(6); // 4, 5, 6, 7, 8, 9
            else if(action.equals("--")){ // Action spéciale pour forcer l'arrêt.
                cases[posJoueurs[queljoueur-'A']] = 'x'; // forcer la défaite
                gagnant = autreJoueur;
                break;
            }else{
                System.out.println("Erreur : deuxième caractère '" + c2 + "' invalid. Action invalide!");
                cases[posJoueurs[queljoueur-'A']] = 'x'; // forcer la défaite
                gagnant = autreJoueur;
                break;
            }
            if(c1=='-')
                nb = -nb;
            // Vérifier la faisabilité de l'action.
            boolean aFaisable;
            if(c2=='D')
                aFaisable = actionDFaisable(queljoueur, c1=='+');
            else
                aFaisable = action123Faisable(queljoueur, nb);
            if(!aFaisable){
                System.out.println("L'action \"" + action + "\" n'était pas faisable! Défaite automatique!");
                cases[posJoueurs[queljoueur-'A']] = 'x'; // forcer la défaite
                gagnant = autreJoueur;
                break;
            }
            
            avancer(queljoueur, nb);
            
            // --- PRÉPARER PROCHAIN TOUR, METTRE À JOUR GUI -----
            autreJoueur = queljoueur;
            queljoueur = (char) ('A' + (queljoueur-'A'+1)%2);
            if(jeugui!=null) 
                jeugui.updateJeu(queljoueur);
            if(dureePauseParTour>0)
                try{Thread.sleep(dureePauseParTour);} catch(InterruptedException ie){}
        }
        
        if(gagnant=='-')
            System.out.println("Partie nulle!");
        
        System.out.println("Gagnant: " + gagnant);
        if(jeugui!=null) {
            jeugui.updateJeu('-');
            jeugui.montrerGagnant(gagnant);
        }
        return gagnant;
    }
    
    /** Vérifier la faisabilé d'une action ±D.
     *  Une action D est faisable s'il y a une possibilité de bouger.
     */
    public boolean actionDFaisable(char joueur, boolean avance){
        for(int i=3+1;i<=3+6;i++)
            if(action123Faisable(joueur, avance ? i : -i))
                return true;
        return false;
    
    }
    /** Vérifier la faisabilé d'une action ±123. */
    public boolean action123Faisable(char joueur, int nbcases){
        int nouvellePos = posJoueurs[joueur-'A'];
        nouvellePos += nbcases;
        if(nouvellePos<0 || nouvellePos>=cases.length)
            return false;
        return cases[nouvellePos]==' ';
    }

    protected boolean avancer(char ljoueur, int nbcases){
        int joueur = ljoueur - 'A'; // A=0  B=1
        int nouvellePos = posJoueurs[joueur];
        // Avancer 1 fois, continuer tant qu'il y a une trempoline.
        nouvellePos += nbcases;
        // Vérifier si on sort du plateau.
        if(nouvellePos<0 || nouvellePos>=cases.length)
            return false;
        // Si la case est brûlée, alors on ne peut pas bouger.
        if(cases[nouvellePos]=='x')
            return false;
        // Si le joueur a le drapeau ET que l'autre n'est pas là, alors:
        if(posDrapeau==posJoueurs[joueur] && posDrapeau!=posJoueurs[(joueur+1)%2]){
            // le joueur emporte le drapeau avec lui.
            posDrapeau = nouvellePos;
        }
        // On brûle la case sauf si (case zéro ou trempoline (_) ou 2e joueur est aussi là).
        if(posJoueurs[joueur]!=0 && cases[posJoueurs[joueur]]!='_' && posJoueurs[0]!=posJoueurs[1]){
            cases[posJoueurs[joueur]] = 'x';
        }
        // Déplacement
        posJoueurs[joueur] = nouvellePos;
        return true;
    }
    
    /** Détermine le gagnant sur le plateau courant.
     * @param prochainJoueur Le joueur à qui est rendu le tour de jouer.
     * @return Le joueur gagnant ('A' ou 'B'), ' ' si nulle ou '?' si le jeu n'est pas terminé.
     */
    public char quiGagne(char prochainJoueur){
        if(cases[posJoueurs[prochainJoueur-'A']]=='x')
            return (char) ('A' + (prochainJoueur-'A'+1)%2);
        if(posDrapeau==0){
            if(posJoueurs[0]==posJoueurs[1])
                return '-'; // seule possible nulle !
            if(posJoueurs[0]==0) 
                return 'A';
            if(posJoueurs[1]==0) 
                return 'B';
            throw new Error("Ça ne devrait jamais arriver!");
        }
        // Vérifier si le joueur est bloqué.
        boolean aucuneActionPossible=true;
        for(int d=-9;d<=9;d++){
            int x = posJoueurs[prochainJoueur-'A'] + d;
            if(d!=0 && x>=0 && x<cases.length)
                if(cases[x]==' ')
                    aucuneActionPossible = false;
        }
        // Aucune possibilité ==> défaite ==> victoire de l'adverssaire
        if(aucuneActionPossible)
            return (char) ('A' + (prochainJoueur-'A'+1)%2);
        return '?'; // pas encore de gagnant
    }
    
    
    @Override
    public String toString(){
        char[] sortie1 = new char[cases.length];
        char[] sortie2 = new char[cases.length];
        for(int i=0;i<cases.length;i++){
            sortie1[i] = (char)('0'+(i%10));
            sortie2[i] = ' ';
        }
        sortie2[posDrapeau] = 'D';
        sortie2[posJoueurs[0]] = 'A';
        sortie2[posJoueurs[1]] = 'B';
        if(posJoueurs[0]==posJoueurs[1])
            sortie2[posJoueurs[1]] = '#';
        return new String(sortie1) + "\n" + new String(sortie2) + "\n" + new String(cases) + "\n";
    }
    
}
