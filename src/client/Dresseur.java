package src.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Dresseur implements Serializable {

    // Attributs

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
    public void supprimerPokemon(Pokemon pokemon) {

        pokemons.remove(pokemon);
        System.out.println("Vous avez supprimé " + pokemon.getNom() + " son corps a été envoyer à l'abbatoire");

    }

    // Supprime un nombre de bonbons donné d'un type donné
    public void supprimerBonbon(String typeBonbon, int quantite) {

        for (int i = 0; i < quantite; i++) {
            if (bonbons.get(i).getType().equals(typeBonbon)) {
                bonbons.remove(i);
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

}
