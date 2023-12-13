package src.client;

import java.io.*;
import java.util.Scanner;

public class Main implements Serializable {

    public static void save(String filePath, Dresseur dresseur) {
        try {
            // Création du flux de sortie vers le fichier
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Enregistrement du nom du dresseur dans le fichier
            objectOutputStream.writeObject(dresseur);

            // Fermeture des flux
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String filePath = "";
        String pseudo = "";
        Dresseur dresseur = null;
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenue dans le monde des Pokémons !");

        System.out.println("Avez vous un compte ? (y/n)");
        String reponse = sc.nextLine();

        while (true) {
            if (reponse.equals("y")) {

                System.out.println("quel est le pseudo de votre sauvegarde ?");

                pseudo = sc.nextLine();

                filePath = "src\\sauvegarde\\" + pseudo + ".txt";

                if (!new File(filePath).exists()) {
                    System.out.println("Ce pseudo n'existe pas !");
                    System.out.println("Avez vous un compte ? (y/n)");
                    reponse = sc.nextLine();
                    continue;
                }

                dresseur = Dresseur.charger(filePath);

            } else if (reponse.equals("n")) {
                System.out.println("Entrez votre pseudo :");

                pseudo = sc.nextLine();

                dresseur = new Dresseur(pseudo);

                filePath = "src\\sauvegarde\\" + pseudo + ".txt";

                save(filePath, dresseur);

            } else {
                System.out.println("Vous n'avez pas choisi une option valide !");
                System.out.println("Avez vous un compte ? (y/n)");
                reponse = sc.nextLine();
            }

            System.out.println("Bonjour " + pseudo + "! \n");

            System.out.println("Quel est votre choix de départ ? \n");

            while (true) {
                System.out.println("\n Que voulez-vous faire " + dresseur.getPseudo() + " ?");
                System.out.println("1. Ouvrir une lootbox");
                System.out.println("2. Combattre un autre dresseur");
                System.out.println("3. Voir ses pokemons");
                System.out.println("4. Voir ses bonbons");
                System.out.println("5. Sauvegarder votre partie");
                System.out.println("6. Charger une partie");
                System.out.println("7. Quitter le jeu \n");
                System.out.println("8. GENERER PLEINS DE POKEMONS\n");

                int choix = sc.nextInt();
                sc.nextLine(); // Pour consommer la nouvelle ligne après avoir lu l'entier

                switch (choix) {

                    case 1:
                        System.out.println("Vous avez choisi d'ouvrir une lootbox ! \n");
                        Pokemon pokemon = PokemonGenerator.generateRandomPokemon();
                        System.out.println(pokemon.toString() + "\n"); // Affiche les détails du Pokémon

                        Bonbon bonbon = new Bonbon(pokemon.getType());
                        dresseur.ajouterBonbon(bonbon);

                        System.out.println("Vous avez reçu un bonbon de type " + bonbon.getType() + "\n");

                        System.out.println("Voulez-vous garder ce Pokemon ? (y/n) \n");
                        reponse = sc.nextLine();

                        if (reponse.equals("y")) {
                            dresseur.ajouterPokemon(pokemon);
                            System.out.println("Vous avez choisi de garder ce Pokémon ! \n");

                        } else if (reponse.equals("n")) {

                            System.out.println("Vous avez choisi de jeter " + pokemon.getNom() + " à la poubelle ! \n");

                        } else {
                            System.out.println("Vous n'avez pas choisi une option valide !");
                            System.out.println("Voulez-vous le garder ? (y/n)");
                            reponse = sc.nextLine();
                        }

                        break;

                    case 2:
                        if (dresseur.getPokemons().size() < 6) {
                            System.out.println("Vous n'avez pas assez de Pokémons ! Vous devez en avoir minimum 6 !\n");
                        } else {
                            System.out.println("Vous avez choisi de combattre un autre dresseur !\n");
                        }
                        break;

                    case 3:

                        System.out.println("Vous avez choisi de voir vos pokemons !\n");
                        if (dresseur.getPokemons().size() == 0) {
                            System.out.println("Vous n'avez pas de Pokémons ! \n");
                        } else {

                            System.out.println("Voici vos Pokémons :");
                            dresseur.afficherPokemons();

                            System.out.println("Que voulez vous faire : \n");
                            System.out.println("1. Evoluer un Pokémon");
                            System.out.println("2. Supprimer un Pokémon");
                            System.out.println("3. Retourner au menu \n");

                            int choix2 = sc.nextInt();
                            sc.nextLine(); // Pour consommer la nouvelle ligne après avoir lu l'entier

                            switch (choix2) {
                                case 1:
                                    System.out.println("Vous avez choisi d'évoluer un Pokémon ! \n");
                                    System.out.println("Quel est l'ID du Pokémon que vous voulez faire évoluer ? \n");

                                    int id2 = sc.nextInt();
                                    sc.nextLine(); // Pour consommer la nouvelle ligne après avoir lu l'entier

                                    if (id2 >= 0 && id2 < dresseur.getPokemons().size()) {
                                        Pokemon pokEvolution = dresseur.getPokemons().get(id2);
                                        pokEvolution.evolution(dresseur);
                                    } else {
                                        System.out.println("ID invalide.");
                                    }
                                    break;

                                case 2:
                                    System.out.println("Vous avez choisi de supprimer un Pokémon ! \n");
                                    System.out.println("Quel est l'ID du Pokémon que vous voulez supprimer ? \n");

                                    int id = sc.nextInt();
                                    sc.nextLine(); // Pour consommer la nouvelle ligne après avoir lu l'entier

                                    if (id >= 0 && id < dresseur.getPokemons().size()) {
                                        dresseur.supprimerPokemon(id);
                                    } else {
                                        System.out.println("ID invalide.");
                                    }
                                    break;

                                case 3:
                                    System.out.println("Vous avez choisi de retourner au menu ! \n");
                                    break;

                                default:
                                    System.out.println("Vous n'avez pas choisi une option valide ! \n");
                                    break;
                            }

                        }

                        break;

                    case 4:

                        System.out.println("Vous avez choisi de voir vos bonbons !\n");
                        if (dresseur.bonbons.size() == 0) {

                            System.out.println("Vous n'avez pas de bonbons ! \n");

                        } else {

                            System.out.println("Voici vos bonbons :");
                            dresseur.afficherBonbons();
                            System.out.println("appuyez sur entrée pour continuer \n");
                            sc.nextLine();

                        }

                        break;

                    case 5:

                        System.out.println("Sauvegarde effectuée !");

                        save(filePath, dresseur);
                        break;

                    case 6:
                        System.out.println("Vous avez choisi de charger une partie ! \n");

                        System.out.println("quel est le pseudo de votre sauvegarde ?");
                        String pseudoCharge = sc.nextLine();

                        String fileCharge = "src\\sauvegarde\\" + pseudoCharge + ".txt";

                        if (!new File(fileCharge).exists()) {
                            System.out.println("Ce pseudo n'existe pas !");
                            break;
                        }

                        dresseur = Dresseur.charger(fileCharge);
                        pseudo = pseudoCharge;
                        filePath = fileCharge;

                        break;

                    case 7:
                        System.out.println("Vous avez choisi de quitter le jeu !\n");
                        System.out.println("Votre partie a été sauvegardée ! \n");
                        save(filePath, dresseur);
                        sc.close(); // Ferme le scanner avant de quitter
                        System.exit(0); // Termine le programme
                        break;

                    case 8:
                        System.out.println("Vous avez choisi de générer pleins de pokemons !\n");
                        System.out.println("Combien de pokemons voulez vous générer ?\n");
                        int nb = sc.nextInt();
                        for (int i = 0; i < nb; i++) {
                            Pokemon pokGen = PokemonGenerator.generateRandomPokemon();
                            System.out.println(pokGen.toString() + "\n"); // Affiche les détails du Pokémon
                            Bonbon bonbGen = new Bonbon(pokGen.getType());
                            dresseur.ajouterBonbon(bonbGen);
                            dresseur.ajouterPokemon(pokGen);
                        }
                        break;

                    default:
                        System.out.println("Vous n'avez pas choisi une option valide ! \n");
                        break;
                }
            }
        }

    }
}