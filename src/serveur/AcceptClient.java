package src.serveur;

import src.client.Dresseur;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AcceptClient extends Thread {
    private ServerSocket server;
    private int nbClients = 0;
    List<Dresseur> joueurs = new ArrayList<>();


    public AcceptClient(ServerSocket server) {
        this.server = server;
    }

    public void run() {
        System.out.println("Serveur en attente de connexion sur le port 2000");

        try {
            while (nbClients < 2) {
                Socket socket = server.accept();
                nbClients++;
                System.out.println("Utilisateur connecté #" + nbClients);

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("Bienvenue sur PokeJava !");

                if (nbClients == 1) {
                    out.println("Veuillez patienter, en attente d'un autre joueur...");
                } else {
                    out.println("Un autre joueur est connecté. Le combat va commencer !");
                    run();
                }

                new ClientHandler(socket, out).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;

        public ClientHandler(Socket socket, PrintWriter out) {
            this.clientSocket = socket;
            this.out = out;
        }

        public void run() {
            try {
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                Dresseur dresseur = (Dresseur) in.readObject();
                joueurs.add(dresseur); // Ajouter le joueur à la liste
                
                // Vérifier s'il y a deux joueurs dans la liste
                if (joueurs.size() == 2) {

                    // Démarrez le combat entre les deux joueurs ici
                    out.println("Le combat commence !");
                    out.println("Joueur 1: " + joueurs.get(0).getPseudo() + " VS " + joueurs.get(1).getPseudo() + "\n");

                    // Créer un objet Combat et appeler la méthode combat() ici
                    Combat combat = new Combat(joueurs.get(0), joueurs.get(1));
                    combat.combat();
                    
                    



                    // Vous pouvez accéder aux joueurs par joueurs.get(0) et joueurs.get(1)
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        
    }
}
