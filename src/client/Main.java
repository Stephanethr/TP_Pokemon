package src.client;
import java.io.*;
import java.util.Scanner;

public class Main {

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
                filePath = "src/sauvegarde/" + pseudo + ".txt";
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
                    dresseur = (Dresseur) ois.readObject();
                    ois.close();
                    System.out.println(dresseur.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();   
                }

                break;

            } else if (reponse.equals("n")) {
                System.out.println("Entrez votre pseudo :");

                pseudo = sc.nextLine();
                
                dresseur = new Dresseur(pseudo);

                filePath = "src/sauvegarde/" + pseudo + ".txt";

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
                break;

            } else {
                System.out.println("Vous n'avez pas choisi une option valide !");
                System.out.println("Avez vous un compte ? (y/n)");
                reponse = sc.nextLine();
            }
            
        }

        
        

        System.out.println("Bonjour " + pseudo + "!");

        System.out.println("Vous êtes un nouveau dresseur Pokémon et vous allez devoir vous battre contre des Pokémon sauvages pour les capturer !");

        while (true) {
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1. Ouvrir une lootbox");
            System.out.println("2. Combattre un autre dresseur");
            System.out.println("3. Voir ses pokemons");
            System.out.println("4. Sauvegarder votre partie");
            System.out.println("5. Charger une partie");
            System.out.println("6. Quitter le jeu");

            int choix = sc.nextInt();
            sc.nextLine(); // Pour consommer la nouvelle ligne après avoir lu l'entier

            switch (choix) {

                case 1:
                    System.out.println("Vous avez choisi d'ouvrir une lootbox !");
                    Pokemon pokemon = PokemonGenerator.generateRandomPokemon();
                    System.out.println(pokemon.toString()); // Affiche les détails du Pokémon

                    System.out.println("Voulez-vous le garder ? (y/n)");
                    reponse = sc.nextLine();
                    while (true) {
                        if (reponse.equals("y")) {
                            dresseur.ajouterPokemon(pokemon);
                            break;
                        } else if (reponse.equals("n")) {
                            System.out.println("Vous avez choisi de ne pas garder ce Pokémon !");
                            break;
                        } else {
                            System.out.println("Vous n'avez pas choisi une option valide !");
                            System.out.println("Voulez-vous le garder ? (y/n)");
                            reponse = sc.nextLine();
                        }
                        
                    }
                    

                case 2:
                    if (dresseur.getPokemons().size() < 6) {
                        System.out.println("Vous n'avez pas assez de Pokémons ! Vous devez en avoir minimum 6 !");
                    } else {
                        System.out.println("Vous avez choisi de combattre un autre dresseur !");
                    }
                    break;

                case 3:
                    dresseur.afficherPokemons();
                    System.out.println("appuyez sur entrée pour continuer");
                    sc.nextLine();
                    
                    break;

                case 4:

                    System.out.println("Vous avez choisi de sauvegarder votre partie !");
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
                    break;

                case 5:
                    System.out.println("Vous avez choisi de charger une partie !");
                    System.out.println("quel est votre pseudo ?");
                    pseudo = sc.nextLine();
                    filePath = "src/sauvegarde/" + pseudo + ".txt";
                    try {
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
                        dresseur = (Dresseur) ois.readObject();
                        ois.close();
                        System.out.println(dresseur.toString());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();   
                    }



                    break;

                case 6:
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
