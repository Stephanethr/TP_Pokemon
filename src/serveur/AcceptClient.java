package src.serveur;

import src.client.Dresseur;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AcceptClient extends Thread {
    private ServerSocket server;
    private List<ClientHandler> clientHandlers = new ArrayList<>();

    public AcceptClient(ServerSocket server) {
        this.server = server;
    }

    public void run() {
        System.out.println("Serveur en attente de connexion sur le port 2000");

        try {
            while (true) {
                Socket socket = server.accept();
                System.out.println("Utilisateur connecté");

                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandlers.add(clientHandler);
                clientHandler.start();

                if (clientHandlers.size() == 2) {
                    System.out.println("Deux joueurs connectés, démarrage du combat");
                    startCombat(); // Démarre le combat une fois que deux clients sont connectés
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendUpdateToClients(String message) {
        System.out.println("Envoi de la mise à jour aux clients : " + message);
        for (ClientHandler handler : clientHandlers) {
            handler.sendResult(message);
        }
    }

    private void startCombat() {
        System.out.println("Début de la méthode startCombat");
        Dresseur j1 = clientHandlers.get(0).getDresseur();

        System.out.println("j1 : " + j1);

        Dresseur j2 = clientHandlers.get(1).getDresseur();

        System.out.println("j2 : " + j2);
        
        if (j1 != null && j2 != null) {
            System.out.println("rentre dans le if   ");
            Combat combat = new Combat(j1, j2, this);
            String winner = combat.combat();
            
        }
        System.out.println("Fin de la méthode startCombat");    

        clientHandlers.clear();
    }
    

    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private Dresseur dresseur;
        private PrintWriter out;
    
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }
    
        public void run() {
            try {
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                
                dresseur = (Dresseur) in.readObject();
                // Autres logiques...
    
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    
        public void sendResult(String message) {
            if (out != null) {
                out.println(message);
            }
        }
    
        public Dresseur getDresseur() {
            return dresseur;
        }
    }
    
}
