/*
 * 
 * Jeu ±123D
 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author Éric Beaudry
 */
public class Serveur {
    private final ServerSocket serversocket;
    public Serveur(int port) throws IOException{
        System.out.println("Ouverture du port " + port);
        serversocket = new ServerSocket(port);
    }
    
    private void run() throws Exception{
        System.out.println("Attente de connection...");
        while(true){
            Socket socket = serversocket.accept();
            System.out.println("Client: " + socket.toString() +"  @ " + new Date() );
            Session session = new Session(socket);
            Thread thread = new Thread(session);
            thread.start();
        }
    }
    
    public static void main(String[] args) throws Exception{
        System.out.println("UQAM - Département d'informatique");
        System.out.println("INF4230 - Intelligence Artificielle");
        System.out.println("Serveur Joueur artificiel pour le jeu ±123D");
        System.out.println("");
        int port = args.length>0 ? Integer.parseInt(args[0]) : 1199;
        Serveur serveur = new Serveur(port);
        serveur.run();
    }
    
    private synchronized void compter(int diff){
        nbSessionsActives += diff;
        System.out.println(" #clients actifs:" + nbSessionsActives);
    }
    
    private int     nextID=1;
    private int     nbSessionsActives = 0;
    
    private class Session extends ServiceInterface implements Runnable{
        Socket         socket;
        int            id;

        public Session(Socket socket){
            this.socket = socket;
            id=nextID++;
        }

        @Override
        public void run() {
            System.out.println("run: " + socket);
            compter(+1);
            try{
                out = new PrintStream(socket.getOutputStream());
                if(nbSessionsActives>20){
                    out.println("Trop de connexions ! Bye !");
                }else{
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    joueur = new JoueurArtificiel();
                    servir();
                }
            }catch(Exception e){
                e.printStackTrace();
            }catch(Error e){
                e.printStackTrace();
            }

            compter(-1);

            try{
                socket.close();
            }catch(IOException ioe){
            }
        }
        
    }
}
