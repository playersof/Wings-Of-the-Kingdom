package it.unicam.cs.mdpg.rpg129029.persistence.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Crea l'istanza di Gson configurata
 */

public class GsonProvider {
    public static Gson creaGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

}
