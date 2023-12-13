package src.client;

import java.util.Scanner;

public class Main {

        public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenue dans le monde des Pokémons ! Quel est votre pseudo ?");

        String pseudo = sc.nextLine();
        Dresseur dresseur = new Dresseur(pseudo);

        System.out.println("Bonjour " + pseudo + "!");

        System.out.println("Vous êtes un nouveau dresseur Pokémon et vous allez devoir vous battre contre des Pokémon sauvages pour les capturer !");

        while (true) {
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1. Ouvrir une lootbox");
            System.out.println("2. Combattre un autre dresseur");
            System.out.println("3. Sauvegarder votre partie");
            System.out.println("4. Charger une partie");
            System.out.println("5. Quitter le jeu");

            int choix = sc.nextInt();
            sc.nextLine(); // Pour consommer la nouvelle ligne après avoir lu l'entier

            switch (choix) {
                case 1:
                    System.out.println("Vous avez choisi d'ouvrir une lootbox !");
                    Pokemon pokemon = PokemonGenerator.generateRandomPokemon();
                    System.out.println(pokemon.toString()); // Affiche les détails du Pokémon

                    System.out.println("Voulez-vous le garder ? (y/n)");
                    String reponse = sc.nextLine();

                    if (reponse.equals("y")) {
                        dresseur.ajouterPokemon(pokemon);
                        System.out.println("Pokémon ajouté à votre équipe !");
                    } else if (reponse.equals("n")) {
                        System.out.println("Vous avez choisi de ne pas garder ce Pokémon !");
                    } else {
                        System.out.println("Vous n'avez pas choisi une option valide !");
                    }
                    break;

                case 2:
                    if (dresseur.getPokemons().size() < 6) {
                        System.out.println("Vous n'avez pas de Pokémon ! Vous devez d'abord en capturer un !");
                    } else {
                        System.out.println("Vous avez choisi de combattre un autre dresseur !");
                    }
                    break;

                case 3:
                    System.out.println("Vous avez choisi de sauvegarder votre partie !");
                    break;

                case 4:
                    System.out.println("Vous avez choisi de charger une partie !");
                    break;

                case 5:
                    System.out.println("Vous avez choisi de quitter le jeu !");
                    sc.close(); // Ferme le scanner avant de quitter
                    System.exit(0); // Termine le programme
                    break;

                default:
                    System.out.println("Vous n'avez pas choisi une option valide !");
                    break;
            }
        }
    }
    
}
