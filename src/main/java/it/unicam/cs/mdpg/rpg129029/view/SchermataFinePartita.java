package it.unicam.cs.mdpg.rpg129029.view;

import it.unicam.cs.mdpg.rpg129029.app.GameApp;
import it.unicam.cs.mdpg.rpg129029.controller.GameControl;
import it.unicam.cs.mdpg.rpg129029.model.Punteggio;
import it.unicam.cs.mdpg.rpg129029.persistence.file.ClassificaRepositoryFile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

/**
 * Schermata 5: fine partita, con classifica dei migliori punteggi.
 */
public final class SchermataFinePartita {

    private SchermataFinePartita() {
    }

    public static void mostra(Stage stage, GameControl controller) {
        StackPane radice = new StackPane();
        radice.getStyleClass().add("sfondo-immagine");
        impostaSfondo(radice);

        VBox contenuto = new VBox(25);
        contenuto.setAlignment(Pos.TOP_CENTER);
        contenuto.setPadding(new Insets(45, 0, 0, 0));

        boolean vincitore = controller.giocatoreVincitore();
        Label esito = new Label(vincitore ? "Vittoria!" : "Sconfitta");
        esito.setFont(Fonts.titolo(32));
        esito.getStyleClass().add(vincitore ? "testo-esito-vittoria" : "testo-esito-sconfitta");

        VBox boxClassifica = costruisciBoxClassifica(controller);

        Button pulsanteNuovaPartita = new Button("Nuova partita");
        pulsanteNuovaPartita.setFont(Fonts.testo(20));
        pulsanteNuovaPartita.getStyleClass().add("pulsante-oro");
        pulsanteNuovaPartita.setOnAction(evento -> {
            GameControl nuovoController = new GameControl(new ClassificaRepositoryFile(GameApp.PERCORSO_CLASSIFICA));
            SchermataBenvenuto.mostra(stage, nuovoController);
        });

        contenuto.getChildren().addAll(esito, boxClassifica, pulsanteNuovaPartita);
        radice.getChildren().add(contenuto);

        Scene scena = new Scene(radice, Layout.LARGHEZZA, Layout.ALTEZZA);
        scena.getStylesheets().add(Layout.FOGLIO_STILE);
        stage.setScene(scena);
    }

    private static void impostaSfondo(StackPane radice) {
        Image immagine = Immagini.carica(Immagini.SFONDO_FINALE);
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

    private static VBox costruisciBoxClassifica(GameControl controller) {
        VBox box = new VBox(10);
        box.getStyleClass().add("box-pergamena");
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(20, 35, 20, 35));
        box.setMaxWidth(600);

        Label titolo = new Label("I migliori falconieri del regno");
        titolo.setFont(Fonts.testo(22));
        titolo.getStyleClass().add("testo-pergamena");

        box.getChildren().add(titolo);

        List<Punteggio> classifica = controller.getClassifica();
        if (classifica.isEmpty()) {
            Label vuota = new Label("Nessun punteggio registrato ancora.");
            vuota.setFont(Fonts.testo(18));
            vuota.getStyleClass().add("testo-pergamena");
            box.getChildren().add(vuota);
        } else {
            for (int i = 0; i < classifica.size(); i++) {
                box.getChildren().add(costruisciRigaClassifica(i + 1, classifica.get(i)));
            }
        }
        return box;
    }

    private static HBox costruisciRigaClassifica(int posizione, Punteggio punteggio) {
        HBox riga = new HBox(20);
        riga.getStyleClass().add("riga-classifica");
        riga.setAlignment(Pos.CENTER_LEFT);
        riga.setPadding(new Insets(6, 4, 6, 4));

        Label labelPosizione = new Label(posizione + "°");
        labelPosizione.setFont(Fonts.testo(20));
        labelPosizione.getStyleClass().add("testo-pergamena");
        labelPosizione.setPrefWidth(40);
        labelPosizione.setMinWidth(Region.USE_PREF_SIZE);

        Label labelNome = new Label(punteggio.getNomeFalconiere());
        labelNome.setFont(Fonts.testo(20));
        labelNome.getStyleClass().add("testo-pergamena");
        labelNome.setPrefWidth(200);
        labelNome.setMinWidth(Region.USE_PREF_SIZE);


        Label labelMissioni = new Label(punteggio.getMissioniCompletate() + " missioni");
        labelMissioni.setFont(Fonts.testo(18));
        labelMissioni.getStyleClass().add("testo-pergamena");
        labelMissioni.setPrefWidth(130);
        labelMissioni.setMinWidth(Region.USE_PREF_SIZE);


        Label labelPrede = new Label(punteggio.getPredeCatturate() + " prede");
        labelPrede.setFont(Fonts.testo(18));
        labelPrede.getStyleClass().add("testo-pergamena");
        labelNome.setPrefWidth(100);
        labelPrede.setMinWidth(Region.USE_PREF_SIZE);


        if (posizione == 1) {
            labelPosizione.getStyleClass().add("riga-classifica-prima");
            labelNome.getStyleClass().add("riga-classifica-prima");
        }

        riga.getChildren().addAll(labelPosizione, labelNome, labelMissioni, labelPrede);
        return riga;
    }
}
