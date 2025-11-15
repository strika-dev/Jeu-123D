/*
 * Jeu ±123D
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

/
public class JoueurConsole implements Joueur{

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    @Override
    public String getProchaineAction(char[] cases, int pmax, int pmin, int pdrap, int dureeR) {
        String action=null;
        try{
            while(true){
                System.out.println("Entrez action [-+][123D] :");
                action=br.readLine();
                if(action==null)
                    return "--";
               if(action.length()!=2)
                   continue;
               char c = action.charAt(0);
               if(c!='-' && c!='+')
                   continue;
               break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return action;
    }

    @Override
    public String getAuteurs() {
        return "Console";
    }
    
}
