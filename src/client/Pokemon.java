package src.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.Serializable;

public class Pokemon implements Serializable{
    // Attributs

    private String nom;
    private String type;
    private int pc;
    private int pv;
    private static final Random random = new Random();

    private ArrayList<String> evolutions = new ArrayList<String>();

    public Pokemon(String nom, String type) {
        this.nom = nom;
        this.type = type;
        this.pc = random.nextInt(100) + 50;
        this.pv = random.nextInt(100) + 50;
        this.setEvolutions();
    }

    // Getters et Setters pour chaque attribut

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getpc() {
        return pc;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }

    public void setpc(int pc) {
        this.pc = pc;
    }

    public int getpv() {
        return pv;
    }

    public void setpv(int pv) {
        this.pv = pv;
    }

    public ArrayList<String> getEvolutions() {
        return evolutions;
    }

    public void setEvolutions() {
        String evolutionFileName = "src\\listeEvolution.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(evolutionFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals(this.nom)) {
                    for (int i = 1; i < parts.length; i++) {
                        String[] evolutionParts = parts[i].split(",");
                        System.out.println("nom récupérés : " + evolutionParts[0] + " " + evolutionParts[1] + " " + evolutionParts[2]);
                        String evolution1 = evolutionParts[0];
                        this.evolutions.add(evolution1);
                        if (evolutionParts.length > 1) {
                            String evolution2 = evolutionParts[1];
                            this.evolutions.add(evolution2);
                        }
                        if (evolutionParts.length > 2) {
                            String evolution3 = evolutionParts[2];
                            this.evolutions.add(evolution3);
                        }

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public void evolution(){
        if (this.evolutions.size() == 0) {
            System.out.println("Ce Pokémon ne peut pas évoluer !");
            return;
        }
        if (this.evolutions.size() < 2) {     
            System.out.println("Votre Pokémon a évolué en " + this.evolutions.get(0));
            this.nom = this.evolutions.get(0);
            this.evolutions.remove(0);
            return;
        }


        
        this.nom = this.evolutions.get(0);
        this.evolutions.remove(0);
    }

    // Méthode pour afficher les informations du Pokémon
    @Override
    public String toString() {
        return "Pokemon{" +
                "nom='" + nom + '\'' +
                "type='" + type + '\'' +
                ", pc=" + pc +
                ", pv=" + pv +
                ", evolutions=" + evolutions +
                '}';
    }
}
