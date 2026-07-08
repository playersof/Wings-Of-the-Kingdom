package it.unicam.cs.mdpg.rpg129029.app;

import it.unicam.cs.mdpg.rpg129029.controller.GameControl;
import it.unicam.cs.mdpg.rpg129029.persistence.file.ClassificaRepositoryFile;
import it.unicam.cs.mdpg.rpg129029.view.MusicaSottofondo;
import it.unicam.cs.mdpg.rpg129029.view.SchermataBenvenuto;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Punto d'ingresso JavaFX di "Wings of the Kingdom".
 * Si limita a costruire il {@link GameControl} e a mostrare la prima
 * schermata: tutta la logica resta nel controller e nel model.
 */
public class GameApp extends Application {

    /** Percorso condiviso del file di persistenza della classifica. */
    public static final String PERCORSO_CLASSIFICA = "data/classifica.json";

    @Override
    public void start(Stage stagePrincipale) {
        MusicaSottofondo.avvia();
        GameControl gameControl = new GameControl(new ClassificaRepositoryFile(PERCORSO_CLASSIFICA));

        stagePrincipale.setTitle("Wings of the Kingdom");
        stagePrincipale.setResizable(true);
        SchermataBenvenuto.mostra(stagePrincipale, gameControl);
        stagePrincipale.show();
    }

    @Override
    public void stop() {
        MusicaSottofondo.ferma();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
