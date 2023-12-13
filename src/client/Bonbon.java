package src.client;
import java.io.Serializable;

public class Bonbon implements Serializable {

    // Attributs

    private String type;

    // Constructeur

    public Bonbon(String type) {
        this.type = type;
    }

    // Getters chaque attribut

    public String getType() {
        return type;
    }


    
}
