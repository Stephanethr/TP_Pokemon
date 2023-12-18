package src.serveur;

import src.client.Dresseur;

public class Combat {

    Dresseur j1;
    Dresseur j2;

    Combat(Dresseur j1, Dresseur j2) {
        this.j1 = j1;
        this.j2 = j2;
    }



    public void combat() {

        while (j1.getPokemonsEquipe().size() > 0 && j2.getPokemonsEquipe().size() > 0) {

            System.out.println(j1.getPseudo() + " envoie " + j1.getPokemonsEquipe().get(0).getNom() + " ! \n");

            System.out.println(j2.getPseudo() + " envoie " + j2.getPokemonsEquipe().get(0).getNom() + " ! \n");

            j1.getPokemonsEquipe().get(0).attaque(j2.getPokemonsEquipe().get(0));

            if(j2.getPokemonsEquipe().get(0).getEstVivant() == false){

                System.out.println(j2.getPokemonsEquipe().get(0).getNom() + " agonise sur le sol  \n");
                j2.getPokemonsEquipe().remove(0);

            } 
            else{
                System.out.println(j2.getPokemonsEquipe().get(0).getPv() + " pv restant \n");
            }

            j2.getPokemons().get(0).attaque(j1.getPokemonsEquipe().get(0));

            if(j1.getPokemonsEquipe().get(0).getEstVivant() == false){

                System.out.println(j1.getPokemonsEquipe().get(0).getNom() + " agonise sur le sol  \n");
                j1.getPokemonsEquipe().remove(0);

            }
            else{
                System.out.println(j1.getPokemonsEquipe().get(0).getPv() + " pv restant \n");
            }


           
        }

        if (j1.getPokemonsEquipe().size() == 0) {
            System.out.println(j2.getPseudo() + " a gagné !");
        } else {
            System.out.println(j1.getPseudo() + " a gagné !");
        }



    }
    
}
