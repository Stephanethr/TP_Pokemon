package src;

import java.net.ServerSocket;
public class Serveur {
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
