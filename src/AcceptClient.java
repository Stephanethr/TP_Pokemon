package src;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AcceptClient extends Thread {
    private ServerSocket server;
    private Socket socket;
    private PrintWriter out;

    public AcceptClient(ServerSocket server) {
        this.server = server;
    }

    public void run() {
        System.out.println("Serveur en attente de connexion sur le port 2000");
        int nbClients = 0;
        try {
            while (true) {
                nbClients++;
                socket = server.accept();
                System.out.println("Utilisateur connecté #" + nbClients);
                out = new PrintWriter(socket.getOutputStream());
                out.println("Bienvenue sur PokeJava !");
                out.flush();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
