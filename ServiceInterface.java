/*
 * Jeu ±123D
 *
 */

import java.io.BufferedReader;
import java.io.PrintStream;

public abstract class ServiceInterface {
    protected Joueur joueur;
    protected PrintStream out;
    protected BufferedReader reader;
    static boolean DEBUG = true;
    
    public void servir() throws Exception {
        out.println("UQAM - Département d'informatique");
        out.println("INF4230 - Intelligence Artificielle");
        out.println("Joueur artificiel pour le jeu ±123D");
        out.println("Auteur(s): " + joueur.getAuteurs());
        out.println("");
        out.println("PRET");
        out.flush();
        
        while(true){
            System.out.println("lire première ligne...");
            String premiereLigne = reader.readLine();
            System.out.println("pl: " + premiereLigne);
            if(premiereLigne==null)
                break;
            if(premiereLigne.isEmpty())
                continue;
            if(premiereLigne.toLowerCase().startsWith("echo")){
                out.println(premiereLigne);
                continue;
            }
            if(premiereLigne.equalsIgnoreCase("quitter") || premiereLigne.equalsIgnoreCase("exit")){
                out.println("Bye !");
                break;
            }
            int iespace = premiereLigne.indexOf(' ');
            String premierMot = iespace==-1 ? premiereLigne : premiereLigne.substring(0, iespace);
            if(!premierMot.equalsIgnoreCase("Plateau")){
                out.println("Erreur protocole: première chaîne attendue : \"Plateau\". Bye!");
                break;
            }
            int taille = Integer.parseInt(premiereLigne.substring(iespace+1));
            System.out.println("lire 2e ligne..." + this);
            String scases = reader.readLine();
            System.out.println("cases: " + scases + " | " + this);
            if(scases.length()!=taille){
                out.println("Erreur protocole: la chaîne qui suit Plateau doit avoir la bonne taille (" + taille + ")!");
                out.println("Chaîne reçue: \"" + scases + "\"");
                break;                
            }
            char[] cases = scases.toCharArray();
            for(char c : cases)
                if(c!=' ' && c!='x'){
                    out.println("Erreur protocole: les cases ne doivent contenir que des espaces ' ' ou 'x'!");
                    out.println("Cases reçues: \"" + scases + "\"");
                    break;
                }
            System.out.println("lire 3e ligne...");
            String positions = reader.readLine();
            System.out.println("positions: " + positions);
            if(!positions.startsWith("Positions")){
                out.println("Erreur protocole: chaîne attendue : \"Positions:\" !");
                out.println("Cases reçues: \"" + positions + "\"");
                break;
            }
            int idp = positions.indexOf(':');
            String snombres = positions.substring(idp+1).trim();
            String[] anombres = snombres.split(" ");
            int pmax = Integer.parseInt(anombres[0]);
            int pmin = Integer.parseInt(anombres[1]);
            int pdrapeau = Integer.parseInt(anombres[2]);
            System.out.println("lire 4e ligne...");
            String sduree = reader.readLine();
            System.out.println("sduree:" + sduree);
            if(!sduree.startsWith("Dur")){
                out.println("Erreur protocole: chaîne attendue : \"Durée:\" !");
                out.println("Cases reçues: \"" + positions + "\"");
                break;
            }
            sduree = sduree.substring(sduree.indexOf(':')+1).trim();
            int duree = Integer.parseInt(sduree);
            System.out.println("Plateau:\n" + scases);
            System.out.println("Positions: " + pmax + " " + pmin + " " + pdrapeau);
            String action = joueur.getProchaineAction(cases, pmax, pmin, pdrapeau, duree);
            
            out.println(action);
            out.flush();
            System.gc();
        }
        out.close();
        reader.close();
    }
}
