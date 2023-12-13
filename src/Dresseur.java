package src;
import java.util.ArrayList;

public class Dresseur {

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
        System.out.println("Vous avez supprimé " + pokemon.getNom() + " son corp a été envoyer à l'abatoire");

    }

    public void supprimerBonbon(Bonbon bonbon) {

        for (int i = 0; i < 5; i++) {
            if (bonbons.get(i).getType().equals(bonbon.getType())) {
                bonbons.remove(i);
            }
        }
        
    }

    public void afficherPokemons() {
        for (Pokemon pokemon : pokemons) {
            System.out.println(pokemon.toString());
        }
    }

    public void afficherBonbons() {
        for (Bonbon bonbon : bonbons) {
            System.out.println(bonbon.getType());
        }
    }

    

    
    

    
}
