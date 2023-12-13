package src.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.Serializable;

public class Pokemon implements Serializable {
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
        this.pc = random.nextInt(800) + 1;
        this.pv = random.nextInt(100) + 20;
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

    // Méthode pour définir les évolutions du Pokémon
    public void setEvolutions() {
        String evolutionFileName = "src\\listeEvolution.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(evolutionFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals(this.nom)) {
                    for (int i = 1; i < parts.length; i++) {
                        String[] evolutionParts = parts[i].split(",");
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

    // Méthode pour vérifier le coût d'évolution du Pokémon
    public int verifCoutEvolution() {

        int valeur = 0;
        switch (this.evolutions.size()) {
            case 1:
                valeur = 10;
                break;
            case 2:
                valeur = 5;
                break;
            case 3:
                valeur = 10;
                break;
            default:
                break;
        }

        return valeur;
    }

    // Méthode pour faire évoluer le Pokémon
    public void evolution(Dresseur dresseur) {
        // Vérifier si le Pokémon a des évolutions possibles
        if (this.evolutions.size() == 0) {
            System.out.println("Votre Pokémon " + this.nom + " ne peut pas évoluer.");
            return;
        }
    
        // Calculer le coût d'évolution
        int coutEvolution = this.verifCoutEvolution();
        System.out.println("Coût d'évolution pour " + this.nom + ": " + coutEvolution + " bonbons.");
    
        // Vérifier si le dresseur a suffisamment de bonbons pour l'évolution
        if (dresseur.getNombreBonbonsParType(this.type) >= coutEvolution) {
            // Déduire les bonbons pour l'évolution
            dresseur.supprimerBonbon(this.type, coutEvolution);
            System.out.println("Bonbons déduits pour l'évolution de " + this.nom + ".");
    
            // Gérer l'évolution spécifique pour Evoli
            if (this.nom.equals("Evoli")) {
                int randomEvolutionIndex = random.nextInt(this.evolutions.size());
                this.nom = this.evolutions.get(randomEvolutionIndex);
                this.evolutions.clear();
    
                // Mise à jour du type en fonction de l'évolution
                switch (this.nom) {
                    case "Voltali":
                        this.type = "Electrique";
                        break;
                    case "Pyroli":
                        this.type = "Feu";
                        break;
                    case "Aquali":
                        this.type = "Eau";
                        break;
                    default:
                        break;
                }
            } else {
                // Pour les autres Pokémon, prendre la première évolution disponible
                this.nom = this.evolutions.get(0);
                this.evolutions.remove(0);
            }
    
            // Mise à jour des stats du Pokémon après l'évolution
            this.pc += (int) (this.pc * 0.7); // Augmentation de 70% des PC
            this.pv += (int) (this.pv * 0.7); // Augmentation de 70% des PV
            System.out.println("Votre Pokémon a évolué en " + this.nom + " !");
        } else {
            System.out.println("Vous n'avez pas assez de bonbons pour faire évoluer " + this.nom);
        }
    }
    

    public int calculerDegats() {
        return (int) (this.pc * 0.1);
    }

    // Méthode pour que le pokemon reçoive les dégâts
    public void recevoirDegats(int degats) {
        this.pv -= degats;
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
