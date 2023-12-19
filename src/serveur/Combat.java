package src.serveur;

import src.client.Pokemon;
import src.client.Dresseur;

public class Combat {

    private Dresseur j1, j2;
    private AcceptClient acceptClient;

    public Combat(Dresseur j1, Dresseur j2, AcceptClient acceptClient) {
        this.j1 = j1;
        this.j2 = j2;
        this.acceptClient = acceptClient;
    }

    // Méthode qui gère le combat

    public String combat() {
        System.out.println("Début du combat entre " + j1.getPseudo() + " et " + j2.getPseudo());
        String update = j1.getPseudo() + " envoie " + j1.getPokemonsEquipe().get(0).getNom();
        acceptClient.sendUpdateToClients(update);
        update = j2.getPseudo() + " envoie " + j2.getPokemonsEquipe().get(0).getNom();
        acceptClient.sendUpdateToClients(update);

        while (true) {

            if (j1.getPokemonsEquipe().isEmpty() || j2.getPokemonsEquipe().isEmpty()) {
                break; // Arrêter le combat si l'une des équipes n'a plus de Pokémon
            }

            try {
                Thread.sleep(2000); // Attendez 2 secondes entre les attaques
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Pokemon pokemonJ1 = j1.getPokemonsEquipe().get(0);
            Pokemon pokemonJ2 = j2.getPokemonsEquipe().get(0);
            update = "le " + pokemonJ1.getNom() + " de " + j1.getPseudo() + " attaque " + pokemonJ2.getNom();
            pokemonJ1.attaque(pokemonJ2);
            acceptClient.sendUpdateToClients(update);

            if (pokemonJ2.getPv() <= 0) {
                update = " Le " + pokemonJ2.getNom() + "de " + j2.getPseudo() + " agonise sur le sol";
                acceptClient.sendUpdateToClients(update);
                j2.getPokemonsEquipe().remove(0);
                if (j1.getPokemonsEquipe().isEmpty() || j2.getPokemonsEquipe().isEmpty()) {
                    break; // Arrêter le combat si l'une des équipes n'a plus de Pokémon
                }
                pokemonJ2 = j2.getPokemonsEquipe().get(0);
                System.out.println(j2.getPseudo() + " envoie " + pokemonJ2.getNom());
            } else {
                update = j2.getPseudo() + " : " + pokemonJ2.getNom() + " " + pokemonJ2.getPv() + " pv restant";
                acceptClient.sendUpdateToClients(update);
            }

            try {
                Thread.sleep(2000); // Attendez 2 secondes entre les attaques
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            update = "le " + pokemonJ2.getNom() + " de " + j2.getPseudo() + " attaque " + pokemonJ1.getNom();
            pokemonJ2.attaque(pokemonJ1);
            acceptClient.sendUpdateToClients(update);

            if (pokemonJ1.getPv() <= 0) {
                update = " le " + pokemonJ1.getNom() + " de " + j1.getPseudo() + " agonise sur le sol";
                acceptClient.sendUpdateToClients(update);
                j1.getPokemonsEquipe().remove(0);
                if (j1.getPokemonsEquipe().isEmpty() || j2.getPokemonsEquipe().isEmpty()) {
                    break; // Arrêter le combat si l'une des équipes n'a plus de Pokémon
                }
                pokemonJ1 = j1.getPokemonsEquipe().get(0);
                System.out.println(j1.getPseudo() + " envoie " + pokemonJ1.getNom());
            } else {
                update = j1.getPseudo() + " " + pokemonJ1.getNom() + " " + pokemonJ1.getPv() + " pv restant";
                acceptClient.sendUpdateToClients(update);
            }

            try {
                Thread.sleep(2000); // Attendez 2 secondes entre les attaques
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        String winner = j1.getPokemonsEquipe().size() > 0 ? j1.getPseudo() : j2.getPseudo();

        return winner;
    }
}