package it.unicam.cs.mdpg.rpg129029.view;

import it.unicam.cs.mdpg.rpg129029.controller.GameControl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Schermata 2: scelta del falco.
 * Le statistiche iniziali mostrate qui sono puramente informative (rispecchiano
 * i valori con cui le sottoclassi di Falco vengono costruite) e servono solo a
 * far vedere le differenze tra i 3 falchi prima della scelta: nessuna logica di
 * gioco viene eseguita in questa schermata, che si limita a richiamare
 * {@link GameControl#sceltaFalco(String)}.
 */
public final class SchermataSceltaFalco {

    /** tipo, immagine, fame iniziale, energia iniziale, addestramento iniziale, descrizione */
    private record OpzioneFalco(String tipo, String nomeVisualizzato, String immagine,
                                 int fame, int energia, int addestramento) {
    }

    private static final OpzioneFalco[] OPZIONI = {
            new OpzioneFalco("Pellegrino", "Pellegrino", Immagini.FALCO_PELLEGRINO, 30, 80, 40),
            new OpzioneFalco("Harris", "Poiana di Harris", Immagini.FALCO_HARRIS, 35, 90, 30),
            new OpzioneFalco("Astore", "Astore", Immagini.FALCO_ASTORE, 40, 70, 50),
    };

    private SchermataSceltaFalco() {
    }

    public static void mostra(Stage stage, GameControl controller) {
        BorderPane radice = new BorderPane();
        radice.getStyleClass().add("sfondo-gioco");
        radice.setPadding(new Insets(30));

        Label titolo = new Label("Scegli il tuo falco");
        titolo.setFont(Fonts.titolo(26));
        titolo.getStyleClass().add("titolo-sezione");
        BorderPane.setAlignment(titolo, Pos.CENTER);
        BorderPane.setMargin(titolo, new Insets(0, 0, 30, 0));
        radice.setTop(titolo);
        BorderPane.setAlignment(titolo, Pos.TOP_CENTER);

        HBox riquadri = new HBox(25);
        riquadri.setAlignment(Pos.CENTER);
        for (OpzioneFalco opzione : OPZIONI) {
            riquadri.getChildren().add(costruisciRiquadro(stage, controller, opzione));
        }
        radice.setCenter(riquadri);

        Scene scena = new Scene(radice, Layout.LARGHEZZA, Layout.ALTEZZA);
        scena.getStylesheets().add(Layout.FOGLIO_STILE);
        stage.setScene(scena);
    }

    private static VBox costruisciRiquadro(Stage stage, GameControl controller, OpzioneFalco opzione) {
        VBox riquadro = new VBox(10);
        riquadro.getStyleClass().addAll("box-legno", "box-legno-selezionabile");
        riquadro.setAlignment(Pos.TOP_CENTER);
        riquadro.setPadding(new Insets(18));
        riquadro.setPrefWidth(260);

        ImageView vistaImmagine = new ImageView();
        vistaImmagine.setFitWidth(150);
        vistaImmagine.setFitHeight(150);
        vistaImmagine.setPreserveRatio(true);
        Image immagine = Immagini.carica(opzione.immagine());
        if (immagine != null) {
            vistaImmagine.setImage(immagine);
        }

        Label nome = new Label(opzione.nomeVisualizzato());
        nome.setFont(Fonts.titolo(14));
        nome.getStyleClass().add("testo-legno");
        nome.setWrapText(true);
        nome.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Label statistiche = new Label(
                "Fame: " + opzione.fame() + "\n" +
                        "Energia: " + opzione.energia() + "\n" +
                        "Addestramento: " + opzione.addestramento()
        );
        statistiche.setFont(Fonts.testo(18));
        statistiche.getStyleClass().add("testo-legno");
        statistiche.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Button pulsanteScegli = new Button("Scegli");
        pulsanteScegli.setFont(Fonts.testo(18));
        pulsanteScegli.getStyleClass().add("pulsante-pixel");
        pulsanteScegli.setMaxWidth(Double.MAX_VALUE);
        pulsanteScegli.setOnAction(evento -> {
            controller.sceltaFalco(opzione.tipo());
            SchermataMissione.mostra(stage, controller);
        });

        riquadro.getChildren().addAll(vistaImmagine, nome, statistiche, pulsanteScegli);
        return riquadro;
    }
}
