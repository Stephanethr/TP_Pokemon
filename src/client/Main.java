package src.client;
import java.io.*;
import java.util.Scanner;
import java.io.Serializable;






public class Main implements Serializable {
    

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

                filePath = "src\\sauvegarde\\" + pseudo + ".txt";

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

        
        

        System.out.println("Bonjour " + pseudo + "! \n");

        System.out.println("Quel est votre choix de départ ? \n");

        while (true) {
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1. Ouvrir une lootbox");
            System.out.println("2. Combattre un autre dresseur");
            System.out.println("3. Voir ses pokemons");
            System.out.println("4. Voir ses bonbons");
            System.out.println("5. Sauvegarder votre partie");
            System.out.println("6. Charger une partie");
            System.out.println("7. Quitter le jeu \n");

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
                    if (dresseur.getPokemons().size() == 0 ) {
                        System.out.println("Vous n'avez pas de Pokémons ! \n");
                    } else {

                        System.out.println("Voici vos Pokémons :");
                        dresseur.afficherPokemons();
                        System.out.println("appuyez sur entrée pour continuer \n");
                        sc.nextLine();
                    
                    }

                    break;

                case 4:

                    System.out.println("Vous avez choisi de voir vos bonbons !\n");
                    if (dresseur.bonbons.size() == 0 ) {

                        System.out.println("Vous n'avez pas de bonbons ! \n");

                    } else {

                        System.out.println("Voici vos bonbons :");
                        dresseur.afficherBonbons();
                        System.out.println("appuyez sur entrée pour continuer \n");
                        sc.nextLine();
                    
                    }

                    break;
                    
                    
                case 5:

                    System.out.println("Vous avez choisi de sauvegarder votre partie !");
                    try {
                        // Création du flux de sortie vers le fichier
                        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                        // Enregistrement du nom du dresseur dans le fichier
                        objectOutputStream.writeObject(dresseur);

                        // Fermeture des flux
                        objectOutputStream.close();


                        System.out.println("Votre partie a bien été sauvegardée ! \n");
                        

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 6:
                    System.out.println("Vous avez choisi de charger une partie ! \n");

                    System.out.println("quel est le pseudo de votre sauvegarde ?");
                    String pseudoCharge = sc.nextLine();

                    
                    filePath = "src\\sauvegarde\\" + pseudoCharge + ".txt";

                    if (!new File(filePath).exists()) {
                        System.out.println("Ce pseudo n'existe pas !");
                        break;
                    }

                    try {

                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
                        dresseur = (Dresseur) ois.readObject();
                        ois.close();
                        System.out.println(dresseur.toString());
                        pseudo = pseudoCharge;

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();   
                    }



                    break;

                case 7:
                    System.out.println("Vous avez choisi de quitter le jeu !");
                    sc.close(); // Ferme le scanner avant de quitter
                    System.exit(0); // Termine le programme
                    break;

                default:
                    System.out.println("Vous n'avez pas choisi une option valide ! \n");
                    break;
            }
        }
    }
    
}
