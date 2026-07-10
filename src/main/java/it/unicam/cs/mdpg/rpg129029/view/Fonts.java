package it.unicam.cs.mdpg.rpg129029.view;

import javafx.scene.text.Font;

import java.io.InputStream;

/**
 * Carica i font pixel usati nel gioco.
 *
 * Font scelti:
 *     Press Start 2P: per titoli e numeri grandi
 *     VT323: monospace pixel, più leggibile per testi lunghi e statistiche
 * Se i file dovessero avere problemi l'app non si bloccherebbe: viene usato un font
 * monospaced di sistema come sostituto, così il progetto è sempre eseguibile
 * anche prima di aver scaricato i font.
 */
public final class Fonts {

    private static final String PERCORSO_FONT_TITOLO =
            "/fonts/PressStart2P-Regular.ttf";
    private static final String PERCORSO_FONT_TESTO =
            "/fonts/VT323-Regular.ttf";

    private static final String FAMIGLIA_FALLBACK = "Monospaced";

    private Fonts() {
    }

    /** Font decorativo per titoli e numeri grandi (Press Start 2P). */
    public static Font titolo(double dimensione) {
        return carica(PERCORSO_FONT_TITOLO, dimensione);
    }

    /** Font leggibile per testi lunghi e statistiche (VT323). */
    public static Font testo(double dimensione) {
        return carica(PERCORSO_FONT_TESTO, dimensione);
    }

    private static Font carica(String percorso, double dimensione) {
        try (InputStream is = Fonts.class.getResourceAsStream(percorso)) {
            if (is != null) {
                Font font = Font.loadFont(is, dimensione);
                if (font != null) {
                    return font;
                }
            }
        } catch (Exception ignorata) {
            // Se il font non si carica correttamente si passa al fallback.
        }
        return Font.font(FAMIGLIA_FALLBACK, dimensione);
    }
}
