//fichier Serveur.java
package src.serveur;
import java.net.ServerSocket;
public class Serveur {

    


//     public static void combat(Dresseur dresseur1, Dresseur dresseur2) {

//         System.out.println("Combat entre " + dresseur1.getPseudo() + " et " + dresseur2.getPseudo());

//         int i = 0;
//         int j = 0;

//         while (i < dresseur1.getPokemonsEquipe().size() && j < dresseur2.getPokemonsEquipe().size()) {

//             System.out.println(dresseur1.getPseudo() + " envoie " + dresseur1.getPokemonsEquipe().get(i).getNom() + " ! \n");

//             System.out.println(dresseur2.getPseudo() + " envoie " + dresseur2.getPokemonsEquipe().get(j).getNom() + " ! \n");

            

            
//     }
// }




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

