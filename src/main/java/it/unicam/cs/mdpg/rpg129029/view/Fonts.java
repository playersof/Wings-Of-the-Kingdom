package it.unicam.cs.mdpg.rpg129029.view;

import javafx.scene.text.Font;

import java.io.InputStream;

/**
 * Carica i font pixel usati nel gioco.
 * <p>
 * Font scelti (da scaricare da Google Fonts e inserire come .ttf in
 * {@code src/main/resources/it/unicam/cs/mdpg/rpg129029/fonts/}):
 * <ul>
 *     <li><b>Press Start 2P</b> - per titoli e numeri grandi, molto decorativo.
 *     https://fonts.google.com/specimen/Press+Start+2P
 *     (file da salvare come {@code PressStart2P-Regular.ttf})</li>
 *     <li><b>VT323</b> - monospace pixel, molto più leggibile per testi lunghi
 *     e statistiche. https://fonts.google.com/specimen/VT323
 *     (file da salvare come {@code VT323-Regular.ttf})</li>
 * </ul>
 * Se i file non sono presenti l'app non si blocca: viene usato un font
 * monospaced di sistema come ripiego, così il progetto è sempre eseguibile
 * anche prima di aver scaricato i font.
 */
public final class Fonts {

    private static final String PERCORSO_FONT_TITOLO =
            "/it/unicam/cs/mdpg/rpg129029/fonts/PressStart2P-Regular.ttf";
    private static final String PERCORSO_FONT_TESTO =
            "/it/unicam/cs/mdpg/rpg129029/fonts/VT323-Regular.ttf";

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
