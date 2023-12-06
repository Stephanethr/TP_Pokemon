import java.util.Random;

public class PokemonGenerator {
    private static final Random random = new Random();
    private static final String[] types = {"Feu", "Eau", "Plante", "Électrique"};

    

    public static Pokemon generateRandomPokemon() {



        int pc = random.nextInt(100) + 1; // Entre 1 et 100
        int pv = random.nextInt(100) + 1; // Entre 1 et 100
        int evolutionStage = random.nextInt(3) + 1; // Entre 1 et 3

        
    }
}
