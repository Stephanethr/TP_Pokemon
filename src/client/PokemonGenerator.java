package src.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokemonGenerator {
    private static final Random random = new Random();
    private static final List<String> infosPokemons = loadPokemonInfo();

    private static List<String> loadPokemonInfo() {
        List<String> pokemonInfoList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src\\pokemonsNonEvolue.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                pokemonInfoList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pokemonInfoList;
    }

    public static Pokemon generateRandomPokemon() {
        // Choisir une ligne aléatoire dans la liste des infosPokemons
        String randomPokemonInfo = infosPokemons.get(random.nextInt(infosPokemons.size()));

        // Diviser la ligne en nom et type en utilisant la virgule comme séparateur
        String[] parts = randomPokemonInfo.split(",");
        String nom = parts[0];
        String type = parts[1].trim(); // Trim pour enlever les espaces éventuels

        return new Pokemon(nom, type);
    }

}
