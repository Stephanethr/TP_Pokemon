package src.serveur;

import src.client.Dresseur;

import java.io.*;
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
                    handleCombat(socket, out);
                }
    
                new ClientHandler(socket, out).start();
            }
    
            // Attendez que les deux clients soient connectés
            while (joueurs.size() < 2) {
                Thread.sleep(100); // Attendez 100 millisecondes avant de vérifier à nouveau
            }
    
            // Après que les deux clients sont connectés, créer le combat
            Dresseur j1 = joueurs.get(0);
            Dresseur j2 = joueurs.get(1);
    
            Combat combat = new Combat(j1, j2);
            combat.combat();

            System.out.println("Le combat est terminé !");
            
    
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    

    private void handleCombat(Socket socket, PrintWriter out) {
        ObjectInputStream in = null;
        
        try {
            in = new ObjectInputStream(socket.getInputStream());
            Dresseur dresseur = (Dresseur) in.readObject();
            joueurs.add(dresseur);
    
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                out.println("fin");
                socket.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            // Gérez les actions des joueurs (si nécessaire) pendant le combat ici
        }
    }
}
