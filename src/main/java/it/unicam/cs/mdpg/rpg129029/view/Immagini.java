package it.unicam.cs.mdpg.rpg129029.view;

import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * Carica le immagini del gioco tramite classpath (getResourceAsStream),
 * così da funzionare correttamente anche dopo il packaging dell'app JavaFX.
 */
public final class Immagini {

    public static final String FALCO_ASTORE = "/Falchi/Astore.png";
    public static final String FALCO_PELLEGRINO = "/Falchi/Pellegrino.png";
    public static final String FALCO_HARRIS = "/Falchi/Harris.png";

    public static final String PREDA_VOLPE = "/Prede/Volpe.png";
    public static final String PREDA_CONIGLIO = "/Prede/Coniglio.png";
    public static final String PREDA_ANATRA = "/Prede/Anatra.png";
    public static final String PREDA_QUAGLIA = "/Prede/Quaglia.png";

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
        return switch (tipoFalco) {
            case "Astore" -> FALCO_ASTORE;
            case "Pellegrino" -> FALCO_PELLEGRINO;
            case "Harris" -> FALCO_HARRIS;
            default -> null;
        };
    }

    /** Associa il nome della preda (Preda.getNome()) al file immagine corrispondente. */
    public static String immaginePreda(String nomePreda) {
        return switch (nomePreda) {
            case "Volpe" -> PREDA_VOLPE;
            case "Coniglio" -> PREDA_CONIGLIO;
            case "Anatra" -> PREDA_ANATRA;
            case "Quaglia" -> PREDA_QUAGLIA;
            default -> null;
        };
    }
}
