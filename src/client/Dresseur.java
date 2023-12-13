package src.client;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Dresseur implements Serializable{

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


    public void setBonbons(ArrayList<Bonbon> bonbons) {
        this.bonbons = bonbons;
    }

    public void ajouterPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
    }

    public void ajouterBonbon(Bonbon bonbon) {
        bonbons.add(bonbon);
    }

    public void supprimerPokemon(Pokemon pokemon) {

        pokemons.remove(pokemon);
        System.out.println("Vous avez supprimé " + pokemon.getNom() + " son corps a été envoyer à l'abbatoire");

    }

    public void supprimerBonbon(Bonbon bonbon, int quantite) {

        for (int i = 0; i < quantite; i++) {
            if (bonbons.get(i).getType().equals(bonbon.getType())) {
                bonbons.remove(i);
            }
        }
        
    }

    public void afficherPokemons() {
        for (Pokemon pokemon : pokemons) {
            System.out.println(pokemon.toString() + "\n");
        }
    }

    public void afficherBonbons() {
    Map<String, Integer> bonbonsParType = new HashMap<>();

    // Parcourir la liste des bonbons pour compter leur nombre par type
    for (Bonbon bonbon : bonbons) {
        String type = bonbon.getType();
        bonbonsParType.put(type, bonbonsParType.getOrDefault(type, 0) + 1);
    }

    // Afficher les bonbons par type et leur compteur
    for (Map.Entry<String, Integer> entry : bonbonsParType.entrySet()) {
        String type = entry.getKey();
        int compteur = entry.getValue();
        System.out.println(type + " (x" + compteur + ")");
    }
}
    

    
    

    
}
