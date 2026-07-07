package it.unicam.cs.mdpg.rpg129029.view;

import it.unicam.cs.mdpg.rpg129029.controller.GameControl;
import it.unicam.cs.mdpg.rpg129029.model.falco.Falco;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Schermata 4: fine missione (superata o fallita).
 * Determina l'esito leggendo {@code Missione.isCompletata()} tramite
 * {@link GameControl#getMissioneCorrente()}, senza calcolare nulla in proprio.
 */
public final class SchermataFineMissione {

    private SchermataFineMissione() {
    }

    public static void mostra(Stage stage, GameControl controller) {
        StackPane radice = new StackPane();
        radice.getStyleClass().add("sfondo-gioco");

        VBox box = new VBox(20);
        box.getStyleClass().add("box-pergamena");
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(35, 55, 35, 55));
        box.setMaxWidth(480);

        boolean superata = controller.getMissioneCorrente().isCompletata();

        Label esito = new Label(superata ? "Missione superata!" : "Missione fallita");
        esito.setFont(Fonts.titolo(24));
        esito.getStyleClass().add(superata ? "testo-esito-vittoria" : "testo-esito-sconfitta");
        esito.setWrapText(true);
        esito.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Falco falco = controller.getFalconiere().getFalco();
        Label statistiche = new Label(
                "Fame: " + falco.getFame() + "/100\n" +
                        "Energia: " + falco.getEnergia() + "/100\n" +
                        "Addestramento: " + falco.getAddestramento() + "/100"
        );
        statistiche.setFont(Fonts.testo(20));
        statistiche.getStyleClass().add("testo-pergamena");
        statistiche.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Button pulsanteContinua = new Button("Continua");
        pulsanteContinua.setFont(Fonts.testo(20));
        pulsanteContinua.getStyleClass().add("pulsante-pixel");
        pulsanteContinua.setOnAction(evento -> {
            boolean partitaProsegue = controller.getMissioniGiocate() < 5 && !controller.giocatorePerso();
            if (partitaProsegue) {
                SchermataMissione.mostra(stage, controller);
            } else {
                controller.terminaPartita();
                SchermataFinePartita.mostra(stage, controller);
            }
        });

        box.getChildren().addAll(esito, statistiche, pulsanteContinua);
        radice.getChildren().add(box);

        Scene scena = new Scene(radice, Layout.LARGHEZZA, Layout.ALTEZZA);
        scena.getStylesheets().add(Layout.FOGLIO_STILE);
        stage.setScene(scena);
    }
}
