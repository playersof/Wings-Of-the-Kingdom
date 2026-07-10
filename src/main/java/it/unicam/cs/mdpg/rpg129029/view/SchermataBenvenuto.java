package it.unicam.cs.mdpg.rpg129029.view;

import it.unicam.cs.mdpg.rpg129029.controller.GameControl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Schermata 1: schermata di benvenuto.
 * Non contiene alcuna logica di gioco: raccoglie il nome
 * del falconiere e lo passa a {@link GameControl#creaFalconiere(String)}.
 */
public final class SchermataBenvenuto {

    private SchermataBenvenuto() {
    }

    public static void mostra(Stage stage, GameControl controller) {
        StackPane radice = new StackPane();
        radice.getStyleClass().add("sfondo-immagine");
        impostaSfondo(radice);

        VBox contenuto = new VBox(30);
        contenuto.setAlignment(Pos.TOP_CENTER);
        contenuto.setPadding(new Insets(50, 0, 0, 0));

        Label titolo = new Label("Wings of the Kingdom");
        titolo.setFont(Fonts.titolo(34));
        titolo.getStyleClass().add("titolo-gioco");
        titolo.setWrapText(true);
        titolo.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        titolo.setMaxWidth(700);

        VBox boxPergamena = costruisciBoxPergamena(stage, controller);

        contenuto.getChildren().addAll(titolo, boxPergamena);
        radice.getChildren().add(contenuto);
        StackPane.setAlignment(contenuto, Pos.TOP_CENTER);

        Scene scena = new Scene(radice, Layout.LARGHEZZA, Layout.ALTEZZA);
        scena.getStylesheets().add(Layout.FOGLIO_STILE);
        stage.setScene(scena);
    }

    private static void impostaSfondo(StackPane radice) {
        Image immagine = Immagini.carica(Immagini.SFONDO_BENVENUTO);
        if (immagine != null) {
            BackgroundImage sfondo = new BackgroundImage(
                    immagine,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, false, true)
            );
            radice.setBackground(new javafx.scene.layout.Background(sfondo));
        }
    }

    private static VBox costruisciBoxPergamena(Stage stage, GameControl controller) {
        VBox box = new VBox(12);
        box.getStyleClass().add("box-pergamena");
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(25, 40, 25, 40));
        box.setMaxWidth(420);

        Label etichetta = new Label("Come ti chiami, falconiere?");
        etichetta.setFont(Fonts.testo(20));
        etichetta.getStyleClass().add("testo-pergamena");

        TextField campoNome = new TextField();
        campoNome.setFont(Fonts.testo(20));
        campoNome.getStyleClass().add("campo-nome");
        campoNome.setPromptText("Il tuo nome...");
        campoNome.setMaxWidth(280);
        campoNome.setAlignment(Pos.CENTER);

        Label errore = new Label();
        errore.setFont(Fonts.testo(16));
        errore.getStyleClass().add("errore-pergamena");
        errore.setVisible(false);
        errore.setManaged(false);

        campoNome.setOnAction(evento -> {
            try {
                controller.creaFalconiere(campoNome.getText());
                errore.setVisible(false);
                errore.setManaged(false);
                SchermataSceltaFalco.mostra(stage, controller);
            } catch (IllegalArgumentException e) {
                errore.setText("Il nome deve avere almeno 3 caratteri.");
                errore.setVisible(true);
                errore.setManaged(true);
            }
        });

        box.getChildren().addAll(etichetta, campoNome, errore);
        return box;
    }
}
