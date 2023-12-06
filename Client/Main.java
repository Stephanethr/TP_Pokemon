import java.util.Scanner; 

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        System.out.println("Bienvenue dans le monde des Pokémons ! Quel est votre pseudo ?");

        String pseudo = sc.nextLine();
        Dresseur dresseur = new Dresseur(pseudo);

        System.out.println("Bonjour " + pseudo + " !");

        System.out.println("Vous êtes un nouveau dresseur Pokémon et vous allez devoir vous battre contre des Pokémon sauvages pour les capturer !");

        System.out.println("Que voulez-vous faire ?");
        System.out.println("1. Capturer un Pokémon");
        System.out.println("2. Combattre un autre dresseur");
        System.out.println("3. Sauvegarder votre partie");
        System.out.println("4. Charger une partie");
        System.out.println("5. Quitter le jeu");

        int choix = sc.nextInt();

        

        switch (choix) {
            case 1:

                System.out.println("Vous avez choisi de capturer un Pokémon !");


                break;



            case 2:

                if(dresseur.getPokemons().size() < 6) {

                    System.out.println("Vous n'avez pas de Pokémon ! Vous devez d'abord en capturer un !");
                    break;
                    
                }

                else {
                    System.out.println("Vous avez choisi de combattre un autre dresseur !");
                    break;
                }
                
                
            case 3:

                System.out.println("Vous avez choisi de sauvegarder votre partie !");
                break;

            case 4:

                System.out.println("Vous avez choisi de charger une partie !");
                break;

            case 5:

                System.out.println("Vous avez choisi de quitter le jeu !");
                break;

            default:

                System.out.println("Vous n'avez pas choisi une option valide !");
                break;
        }

        sc.close();



        






    }
    
}
