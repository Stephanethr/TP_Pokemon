package src.client;
import java.util.ArrayList;
import java.util.Random;

public class Pokemon {
    // Attributs

    private String nom;
    private String type;
    private int pc;
    private int pv;
    private static final Random random = new Random();

    private ArrayList<Pokemon> evolutions = new ArrayList<Pokemon>();

    public Pokemon(String nom, String type) {
        this.nom = nom;
        this.type = type;
        this.pc = random.nextInt(100) + 50;
        this.pv = random.nextInt(100) + 50;
    }

    // Getters et Setters pour chaque attribut

    public ArrayList<Pokemon> getEvolutions() {
        return evolutions;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getpc() {
        return pc;
    }

    public void setEvolutions(ArrayList<Pokemon> evolutions) {
        this.evolutions = evolutions;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }

    public void setpc(int pc) {
        this.pc = pc;
    }

    public int getpv() {
        return pv;
    }

    public void setpv(int pv) {
        this.pv = pv;
    }

    // Méthode pour afficher les informations du Pokémon
    @Override
    public String toString() {
        return "Pokemon{" +
                "nom='" + nom + '\'' +
                "type='" + type + '\'' +
                ", pc=" + pc +
                ", pv=" + pv +

                '}';
    }
}
