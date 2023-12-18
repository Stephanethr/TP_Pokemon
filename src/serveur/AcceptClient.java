package src.serveur;

import src.client.Dresseur;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AcceptClient extends Thread {
    private ServerSocket server;
    private int nbClients = 0;

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
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                Dresseur dresseur = (Dresseur) ois.readObject();
                System.out.println("Dresseur reçu : " + dresseur.getPseudo());
                ois.close();
                out.close();
                clientSocket.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
