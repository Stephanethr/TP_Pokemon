//fichier Serveur.java
package src.serveur;
import src.client.Dresseur;
import src.client.Pokemon;
import java.net.ServerSocket;
public class Serveur {


    public static void combat(Dresseur dresseur1, Dresseur dresseur2) {

        System.out.println("Combat entre " + dresseur1.getPseudo() + " et " + dresseur2.getPseudo());

        int i = 0;
        int j = 0;

        while (i < dresseur1.getPokemonsEquipe().size() && j < dresseur2.getPokemonsEquipe().size()) {

            Pokemon pokemon1 = dresseur1.getPokemonsEquipe().get(i);
            Pokemon pokemon2 = dresseur2.getPokemonsEquipe().get(j);

            System.out.println("Tour " + (i + j + 1) + " : " + pokemon1.getNom() + " VS " + pokemon2.getNom());

            while (pokemon1.getPv() > 0 && pokemon2.getPv() > 0) {

                
            }

            if (pokemon1.getPv() <= 0) {
                pokemon1.estKO();
                i++;
            } else {
                pokemon2.estKO();
                j++;
            }

        }

        if (i == dresseur1.getPokemonsEquipe().size()) {
            System.out.println(dresseur2.getPseudo() + " a gagné !");
        } else {
            System.out.println(dresseur1.getPseudo() + " a gagné !");
        }
    }




    public static void main(String[] args) {
        ServerSocket server;
        try{
            server = new ServerSocket(2000);
            AcceptClient client = new AcceptClient(server);
            client.start();

            
        }catch(Exception e){
            System.out.println("Erreur : " + e);
        }


        
    }
}
