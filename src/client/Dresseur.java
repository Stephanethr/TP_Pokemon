package src.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;




import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;


public class Dresseur implements Serializable {

    // Attributs


    private static final long serialVersionUID = 1L;
    private String pseudo;
    ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
    ArrayList<Bonbon> bonbons = new ArrayList<Bonbon>();

    public Dresseur(String pseudo) {

        this.pseudo = pseudo;

    }

    // Getters et Setters pour chaque attribut

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public ArrayList<Bonbon> getBonbons() {
        return bonbons;
    }


    public void setBonbons(ArrayList<Bonbon> bonbons) {
        this.bonbons = bonbons;
    }

    // ----------- Méthodes classe Dresseur -------------------

    // Retourne une liste de bonbons par type
    public Map<String, Integer> getListeBonbonsParType() {
        Map<String, Integer> bonbonsParType = new HashMap<>();

        // Parcourir la liste des bonbons pour compter leur nombre par type
        for (Bonbon bonbon : bonbons) {
            String type = bonbon.getType();
            bonbonsParType.put(type, bonbonsParType.getOrDefault(type, 0) + 1);
        }

        return bonbonsParType;
    }

    // Retourne le nombre de bonbons d'un type donné
    public int getNombreBonbonsParType(String type) {
        Map<String, Integer> bonbonsParType = this.getListeBonbonsParType();
        return bonbonsParType.getOrDefault(type, 0);
    }

    // Ajoute un pokémon à la liste des pokémons
    public void ajouterPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
    }

    // Ajoute un bonbon à la liste des bonbons
    public void ajouterBonbon(Bonbon bonbon) {
        bonbons.add(bonbon);
    }

    // Supprime un pokémon donné
    public void supprimerPokemon(int id) {
        if (id >= 0 && id < pokemons.size()) {
            Pokemon pokemon = pokemons.get(id);
            pokemons.remove(id);
            System.out.println("Vous avez supprimé " + pokemon.getNom() + " son corps a été envoyé à l'abattoir");
        } else {
            System.out.println("ID invalide. Aucun Pokémon n'a été supprimé.");
        }
    }

    // Supprime un nombre de bonbons donné d'un type donné
    public void supprimerBonbon(String typeBonbon, int quantite) {
        Iterator<Bonbon> iterator = bonbons.iterator();
        int compteur = 0;
    
        while (iterator.hasNext()) {
            Bonbon bonbon = iterator.next();
            if (bonbon.getType().equals(typeBonbon)) {
                iterator.remove();
                compteur++;
                if (compteur >= quantite) {
                    break; // Arrête la boucle une fois la quantité requise supprimée
                }
            }
        }
    }
    
    // Affiche les pokémons du dresseur
    public void afficherPokemons() {
        for (int i = 0; i < pokemons.size(); i++) {
            Pokemon pokemon = pokemons.get(i);
            System.out.println("ID: " + i);
            System.out.println(pokemon.toString() + "\n");
        }
    }

    // Affiche les bonbons par type et leur compteur
    public void afficherBonbons() {
    Map<String, Integer> bonbonsParType = this.getListeBonbonsParType();

    // Afficher les bonbons par type et leur compteur
    for (Map.Entry<String, Integer> entry : bonbonsParType.entrySet()) {
        String type = entry.getKey();
        int compteur = entry.getValue();
        System.out.println(type + " (x" + compteur + ")");
    }
}

    public static Dresseur charger(String filePath) {
            Dresseur dresseur = null;

            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
                dresseur = (Dresseur) ois.readObject();
                ois.close();
                System.out.println("\n Dresseur chargé ! \n");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();   
            }

            return dresseur;
    }
    
    

}
