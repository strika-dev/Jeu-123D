/*
 * Jeu ±123D
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/** Interface pour se connecter à un serveur de joueur distance via socket TCP/IP.
 *
 */
public class JoueurInterfaceSocket implements Joueur {
    protected Socket         socket;
    protected PrintWriter    versServeur;
    protected BufferedReader lecteurServeur;
    protected boolean        debug = false;

    public JoueurInterfaceSocket(String host, int port) throws Exception{
        socket = new Socket(host, port);
        lecteurServeur = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        versServeur = new PrintWriter(socket.getOutputStream());

        System.out.println("Initialisation du joueur distant");
        String ligne;
        while(true){
            ligne = lecteurServeur.readLine();
            System.out.println(" STDOUT: " + ligne);
            if(ligne.equalsIgnoreCase("PRET"))
                break;
            if(!ligne.isEmpty()){
                auteurs = ligne;
                if(auteurs.startsWith("Auteurs:")){
                    auteurs = auteurs.substring(8).trim();
                }
            }
        }

        System.out.println("Joueur artificiel initialisé.");
    }
    
    
    private String auteurs = "?";

    @Override
    public String getAuteurs() {
        return auteurs;
    }

    @Override
    public String getProchaineAction(char[] cases, int pmax, int pmin, int pdrap, int dureeR) {
        versServeur.println("Plateau " + cases.length);
        versServeur.println(cases);
        versServeur.println("Positions (max, min, drapeau) : " + pmax  + " " +  pmin + " " + pdrap);
        versServeur.println("Duree: " + dureeR);
        versServeur.flush();
        String action = "##";
        try{
            socket.getOutputStream().flush();
            action = lecteurServeur.readLine();
            System.out.println("Action reçue: " + action);
        }catch(Exception e){
            e.printStackTrace();
        }
        return action;
    }
}
