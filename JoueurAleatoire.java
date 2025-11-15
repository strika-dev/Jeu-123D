/*
 * 
 * Jeu ±123D
 *
 */

import java.util.ArrayList;
import java.util.Random;

/** Joueur aléatoire. Un premier objectif peut être de battre le joueur aléatoire!
 *
 */
public class JoueurAleatoire implements Joueur {

    Random random = new Random();
    
    @Override
    public String getProchaineAction(char[] cases, int pmax, int pmin, int pdrap, int dureeR) {
        ArrayList<Integer> deplacementsPossibles = new ArrayList<>(18);       
        for(int d=-9;d<=9;d++){
            if(d==0) 
                continue;
            int x = pmax+d;
            if(x<0 ||x>=cases.length) 
                continue;
            if(cases[pmax+d]=='x')
                continue;
            deplacementsPossibles.add(d);
        }
        if(deplacementsPossibles.isEmpty()){
            // Ceci ne devrait jamais arriver !
            return "--";
            //throw new Error("Aucune action possible! La partie devrait être déjà terminée !");
        }
        int deplacementVouluAleatoire = deplacementsPossibles.get(random.nextInt(deplacementsPossibles.size()));
        assert deplacementVouluAleatoire != 0;
        if(deplacementVouluAleatoire<-3)
            return "-D";
        if(deplacementVouluAleatoire>+3)
            return "+D";
        if(deplacementVouluAleatoire>=0)
            return "+" + deplacementVouluAleatoire;
        // contiendra le signe -
        return "" + deplacementVouluAleatoire;
    }

    @Override
    public String getAuteurs() {
        return "Aléatoire";
    }
}
