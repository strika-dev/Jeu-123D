
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *  Interface à venir.
 */
public class JoueurInterfaceCmd implements Joueur {
    protected Process        process;
    protected PrintWriter    toProcess;
    protected BufferedReader fromProcess;
    protected BufferedReader fromProcessErr;
    protected boolean        debug=true;
    protected String         auteurs = "?";

    JoueurInterfaceCmd(String commande) throws IOException{
        ProcessBuilder pb = new ProcessBuilder(commande);
        File file = new File(commande);
        File parent = file.getParentFile();
        if(parent.exists())
            pb.directory(parent);
        process = pb.start();
        fromProcess = new BufferedReader(new InputStreamReader(process.getInputStream()));
        new Thread(new Runnable(){
            @Override
            public void run() {
                BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                try{
                    String ligne;
                    do{
                        ligne=err.readLine();
                        if(debug)
                            System.err.println("Externe STDERR: " + ligne);
                    }while(ligne!=null);
                    process.getErrorStream().close();
                }catch(Exception e){e.printStackTrace();}
            }
        }).start();
        
        toProcess = new PrintWriter(process.getOutputStream());
        
        System.out.println("Initialisation du joueur artificiel");
        String ligne;
        while(true){
            ligne = fromProcess.readLine();
            System.out.println(" STDOUT: " + ligne);
            if(ligne.equalsIgnoreCase("PRET"))
                break;
            if(!ligne.isEmpty())
                auteurs = ligne;
        }
        
        System.out.println("Joueur artificiel initialisé.");

    }
    
    @Override
    public String getProchaineAction(char[] cases, int pmax, int pmin, int pdrap, int dureeR) {
        toProcess.println("Plateau " + cases.length);
        toProcess.println(cases);
        toProcess.println("Positions (max, min, drapeau) : " + pmax  + " " +  pmin + " " + pdrap);
        toProcess.println("Duree: " + dureeR);
        toProcess.flush();
        String action = "##";
        try{
            action = fromProcess.readLine();
            System.out.println("Action reçue: " + action);
        }catch(Exception e){
            e.printStackTrace();
        }
        return action;
    }

    @Override
    public String getAuteurs() {
        return auteurs;
    }
    

}
