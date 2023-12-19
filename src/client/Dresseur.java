package src.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Dresseur implements Serializable {

    // Attributs

    private static final long serialVersionUID = 1L;
    private String pseudo;
    private int niveau;
    private int xp;
    ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
    ArrayList<Bonbon> bonbons = new ArrayList<Bonbon>();
    ArrayList<Pokemon> listCombat = new ArrayList<Pokemon>();

    public Dresseur(String pseudo) {
        this.pseudo = pseudo;
        this.niveau = 1;
        this.xp = 0;
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

    public ArrayList<Pokemon> getPokemonsEquipe() {
        return listCombat;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        if (niveau >= 1 && niveau <= 100) {
            this.niveau = niveau;
        } else {
            System.out.println("Niveau invalide. Le niveau doit être compris entre 1 et 100.");
        }
    }

    public int getXp() {
        return xp;
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

    public void ajouterPokemonEquipe(int id) {
        if (listCombat.size() == 6) {
            System.out.println("Vous avez déjà 6 pokémons dans votre équipe de combat");
            return;
        }
        if (id >= 0 && id < pokemons.size()) {
            Pokemon pokemon = pokemons.get(id);
            if (listCombat.contains(pokemon)) {
                System.out.println("Vous avez déjà ajouté ce pokémon à votre équipe de combat");
                return;
            }
            listCombat.add(pokemon);
            System.out.println("Vous avez ajouté " + pokemon.getNom() + " à votre équipe de combat");
        } else {
            System.out.println("ID invalide. Aucun Pokémon n'a été ajouté.");
        }
    }
    

    

    public void afficherPokemonsEquipe() {
        for (int i = 0; i < listCombat.size(); i++) {
            Pokemon pokemon = listCombat.get(i);
            System.out.println("ID: " + i);
            System.out.println(pokemon.toString() + "\n");
        }
    }

    public void gagnerXp(int xp) {
        this.xp += xp;
        if (this.xp >= 100) {
            this.xp -= 100;
            this.niveau++;
            System.out.println("Vous avez gagné un niveau !\n");
        }
    }

    public static Dresseur charge(String filePath) {
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

    public void save(String filePath) {
        try {
            // Création du flux de sortie vers le fichier
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Enregistrement du nom du dresseur dans le fichier
            objectOutputStream.writeObject(this);

            // Fermeture des flux
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
