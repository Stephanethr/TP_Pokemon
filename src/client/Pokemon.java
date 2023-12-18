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
    private boolean estVivant = true;
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

    public int getPc() {
        return pc;
    }

    public boolean getEstVivant() {
        return estVivant;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getPv() {
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
    // et renvoie cette valeur
    public int verifCoutEvolution() {

        int valeur = 0;
        switch (this.evolutions.size()) {
            // Si le Pokémon a 1 évolution, il faut 10 bonbons
            case 1:
                valeur = 10;
                break;

            // Si le Pokémon a 2 évolutions, il faut 5 bonbons pour la première et 10 pour
            // la deuxième
            case 2:
                valeur = 5;
                break;

            // Si le Pokémon a 3 évolutions (Evoli uniquement), il faut 10 bonbons
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
        System.out.println("Coût d'évolution pour " + this.nom + ": " + coutEvolution + " bonbons. \n");

        // Vérifier si le dresseur a suffisamment de bonbons pour l'évolution
        if (dresseur.getNombreBonbonsParType(this.type) >= coutEvolution) {
            // Déduire les bonbons pour l'évolution
            dresseur.supprimerBonbon(this.type, coutEvolution);

            // Gérer l'évolution spécifique pour Evoli
            if (this.nom.equals("Evoli")) {
                int randomEvolutionIndex = random.nextInt(this.evolutions.size());
                this.nom = this.evolutions.get(randomEvolutionIndex);
                this.evolutions.clear();

                // Mise à jour du type en fonction de l'évolution
                switch (this.nom) {
                    case "Voltali":
                        this.setType("Electrique");
                        break;
                    case "Pyroli":
                        this.setType("Feu");
                        break;
                    case "Aquali":
                        this.setType("Eau");
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

    // Méthode pour faire combattre le Pokémon
    public void combat(Pokemon pokemonAdverse) {
        int degatsInitiaux = (int) (this.pc * 0.1);

        // Calcul des dégâts en fonction du type du Pokémon
        // (Insecte, Glace, Normal, Spectre, Dragon, Electrique, Sol, Vol, Roche, Feu,
        // Combat, Psy, Eau, Poison, Plante)
        switch (this.type) {
            case "Insecte":
                switch (pokemonAdverse.type) {
                    case "Insecte":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Combat":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Feu":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Plante":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Poison":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Psy":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Spectre":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Vol":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    default:
                        break;
                }
                break;

            case "Glace":
                switch (pokemonAdverse.type) {
                    case "Glace":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Dragon":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Eau":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Plante":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Feu":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Sol":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Vol":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    default:
                        break;
                }
                break;
            case "Normal":
                switch (pokemonAdverse.type) {
                    case "Roche":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Spectre":
                        pokemonAdverse.pv -= degatsInitiaux * 0;
                        break;
                    default:
                        break;
                }
                break;
            case "Spectre":
                switch (pokemonAdverse.type) {
                    case "Normal":
                        pokemonAdverse.pv -= degatsInitiaux * 0;
                        break;
                    case "Spectre":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Psy":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    default:
                        break;
                }
            case "Dragon":
                switch (pokemonAdverse.type) {
                    case "Dragon":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    default:
                        break;
                }
            case "Electrique":
                switch (pokemonAdverse.type) {
                    case "Eau":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Electrique":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Plante":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Vol":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Sol":
                        pokemonAdverse.pv -= degatsInitiaux * 0;
                        break;
                    case "Dragon":
                        pokemonAdverse.pv -= degatsInitiaux * 0.5;
                        break;
                    default:
                        break;
                }
            case "Sol":
                switch (pokemonAdverse.type) {
                    case "Electrique":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Feu":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Insecte":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Plante":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Poison":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Roche":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Sol":
                        pokemonAdverse.pv -= degatsInitiaux * 0;
                        break;
                    default:
                        break;
                }
            case "Vol":
                switch (pokemonAdverse.type) {
                    case "Combat":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Electrique":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Plante":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Insecte":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Roche":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    default:
                        break;
                }
            case "Roche":
                switch (pokemonAdverse.type) {
                    case "Combat":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Feu":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Insecte":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Glace":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Vol":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Sol":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    default:
                        break;
                }
            case "Feu":
                switch (pokemonAdverse.type) {
                    case "Dragon":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Eau":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Feu":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Plante":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Glace":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Insecte":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Roche":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    default:
                        break;
                }
            case "Combat":
                switch (pokemonAdverse.type) {
                    case "Insecte":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Roche":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Spectre":
                        pokemonAdverse.pv -= degatsInitiaux * 0;
                        break;
                    case "Normal":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Glace":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Poison":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Vol":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Psy":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    default:
                        break;
                }
            case "Psy":
                switch (pokemonAdverse.type) {
                    case "Combat":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Poison":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Psy":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    default:
                        break;
                }
            case "Eau":
                switch (pokemonAdverse.type) {
                    case "Dragon":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Eau":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Plante":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Feu":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Sol":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Roche":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    default:
                        break;
                }
            case "Poison":
                switch (pokemonAdverse.type) {
                    case "Insecte":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Plante":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Poison":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Sol":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Roche":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Spectre":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    default:
                        break;
                }
            case "Plante":
                switch (pokemonAdverse.type) {
                    case "Eau":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Plante":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Poison":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Roche":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Sol":
                        pokemonAdverse.pv -= degatsInitiaux * 2;
                        break;
                    case "Feu":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Insecte":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Dragon":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    case "Vol":
                        pokemonAdverse.pv -= (int) (degatsInitiaux * 0.5);
                        break;
                    default:
                        break;
                }
            default:
                pokemonAdverse.pv -= degatsInitiaux;
        }

    }

    // Méthode pour vérifier si le Pokémon est KO
    public void estKO() {
        this.estVivant = this.pv <= 0;
        if (this.estVivant) {
            System.out.println(this.nom + " est KO !");
        }
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
