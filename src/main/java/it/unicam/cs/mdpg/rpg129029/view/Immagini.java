package it.unicam.cs.mdpg.rpg129029.view;

import it.unicam.cs.mdpg.rpg129029.model.falco.TipoFalco;
import it.unicam.cs.mdpg.rpg129029.model.preda.TipoPreda;
import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * Carica le immagini del gioco tramite classpath (getResourceAsStream),
 * così da funzionare correttamente anche dopo il packaging dell'app JavaFX.
 */
public final class Immagini {
    /**
     * Sfondo della schermata di benvenuto.
     *
     */
    public static final String SFONDO_BENVENUTO = "/schermata_di_benvenuto.png";

    /**
     * Sfondo della schermata finale (vittoria/sconfitta + classifica).
     */
    public static final String SFONDO_FINALE = "/sfondo_finale.png";

    private Immagini() {
    }

    /** Carica un'immagine dal classpath, restituendo {@code null} se non trovata. */
    public static Image carica(String percorsoClasspath) {
        try (InputStream is = Immagini.class.getResourceAsStream(percorsoClasspath)) {
            if (is != null) {
                return new Image(is);
            }
        } catch (Exception ignorata) {
            // Se l'immagine non si carica, chi la usa mostrerà un placeholder grafico.
        }
        return null;
    }

    /** Associa il nome del tipo di falco (usato da FalcoFactory) al file immagine corrispondente. */
    public static String immagineFalco(String tipoFalco) {
        try{
            return TipoFalco.valueOf(tipoFalco.toUpperCase()).getPercorsoImmagine();
        }catch (IllegalArgumentException | NullPointerException e){
            return null;
        }
    }

    /** Associa il nome della preda (Preda.getNome()) al file immagine corrispondente. */
    public static String immaginePreda(String nomePreda) {
        try{
            return TipoPreda.valueOf(nomePreda.toUpperCase()).getPercorsoImmagine();
        }catch (IllegalArgumentException | NullPointerException e){
            return null;
        }
    }
}
