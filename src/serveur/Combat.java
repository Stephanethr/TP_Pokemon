package src.serveur;
import src.client.Pokemon;
import src.client.Dresseur;

public class Combat {

    private Dresseur j1, j2;
    private AcceptClient acceptClient;

    // Constructeur modifié pour inclure une référence à AcceptClient
    public Combat(Dresseur j1, Dresseur j2, AcceptClient acceptClient) {
        this.j1 = j1;
        this.j2 = j2;
        this.acceptClient = acceptClient;
    }

    public String combat() {
        System.out.println("Début du combat entre " + j1.getPseudo() + " et " + j2.getPseudo());
        while (j1.getPokemonsEquipe().size() > 0 && j2.getPokemonsEquipe().size() > 0) {
            // Envoyer des mises à jour aux clients
            String update = j1.getPseudo() + " envoie " + j1.getPokemonsEquipe().get(0).getNom();
            acceptClient.sendUpdateToClients(update);
            update = j2.getPseudo() + " envoie " + j2.getPokemonsEquipe().get(0).getNom();
            acceptClient.sendUpdateToClients(update);

            j1.getPokemonsEquipe().get(0).attaque(j2.getPokemonsEquipe().get(0));
            j2.getPokemonsEquipe().get(0).estKO();

            if (j2.getPokemonsEquipe().get(0).getEstVivant()) {
                update = j2.getPokemonsEquipe().get(0).getNom() + " agonise sur le sol";
                acceptClient.sendUpdateToClients(update);
                j2.getPokemonsEquipe().remove(0);
            } else {
                update = j2.getPokemonsEquipe().get(0).getNom()+ " " + j2.getPokemonsEquipe().get(0).getPv() + " pv restant";
                acceptClient.sendUpdateToClients(update);
            }

            if (j1.getPokemonsEquipe().isEmpty() || j2.getPokemonsEquipe().isEmpty()) {
                break; // Arrêter le combat si l'une des équipes n'a plus de Pokémon
            }
            

            j2.getPokemonsEquipe().get(0).attaque(j1.getPokemonsEquipe().get(0));
            j1.getPokemonsEquipe().get(0).estKO();

            if (j1.getPokemonsEquipe().get(0).getEstVivant()) {
                update = j1.getPokemonsEquipe().get(0).getNom() + " agonise sur le sol";
                acceptClient.sendUpdateToClients(update);
                j1.getPokemonsEquipe().remove(0);
            } else {
                update = j1.getPokemonsEquipe().get(0).getNom() + " " + j1.getPokemonsEquipe().get(0).getPv() + " pv restant";
                acceptClient.sendUpdateToClients(update);
            }

            
            
        }

        String winner = j1.getPokemonsEquipe().size() > 0 ? j1.getPseudo() : j2.getPseudo();
        acceptClient.sendUpdateToClients("Le gagnant est : " + winner);
        return winner;
    }
}