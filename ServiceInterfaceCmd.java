/*
 *
 * Jeu ±123D
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServiceInterfaceCmd extends ServiceInterface {
    public ServiceInterfaceCmd(){
        out = System.out;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }
    public static void main(String[] args) throws Exception {
        ServiceInterfaceCmd sicmd = new ServiceInterfaceCmd();
        sicmd.servir();
    }
}
