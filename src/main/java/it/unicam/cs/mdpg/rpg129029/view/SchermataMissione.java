package it.unicam.cs.mdpg.rpg129029.view;

import it.unicam.cs.mdpg.rpg129029.controller.GameControl;
import it.unicam.cs.mdpg.rpg129029.model.Missione;
import it.unicam.cs.mdpg.rpg129029.model.azione.Addestra;
import it.unicam.cs.mdpg.rpg129029.model.azione.Nutri;
import it.unicam.cs.mdpg.rpg129029.model.azione.Riposa;
import it.unicam.cs.mdpg.rpg129029.model.falco.Falco;
import it.unicam.cs.mdpg.rpg129029.model.preda.Preda;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Schermata 3: missione di caccia in corso (schermata principale di gioco).
 * Nessuna logica di gioco è presente qui: ogni azione richiama uno dei metodi
 * già pronti di {@link GameControl} e la schermata si limita ad aggiornare la
 * grafica in base ai risultati ricevuti.
 */
public final class SchermataMissione {

    private static final int MASSIMO_STAT = 100;

    private final Stage stage;
    private final GameControl controller;

    private int indicePreda = 0;

    private Label labelFame;
    private Label labelEnergia;
    private Label labelAddestramento;
    private ProgressBar barraFame;
    private ProgressBar barraEnergia;
    private ProgressBar barraAddestramento;

    private Label sottotitoloPreda;
    private VBox pannelloPreda;
    private Label labelEsito;
    private Button pulsanteCaccia;
    private Button pulsanteNutri;
    private Button pulsanteRiposa;
    private Button pulsanteAllena;

    private boolean azioneEseguita = false;

    private SchermataMissione(Stage stage, GameControl controller) {
        this.stage = stage;
        this.controller = controller;
    }

    /** Costruisce e mostra una nuova missione (viene generata qui una missione nuova). */
    public static void mostra(Stage stage, GameControl controller) {
        controller.iniziaMissione();
        new SchermataMissione(stage, controller).costruisci();
    }

    private void costruisci() {
        BorderPane radice = new BorderPane();
        radice.getStyleClass().add("sfondo-gioco");
        radice.setPadding(new Insets(25));

        Label titolo = new Label("Missione " + (controller.getMissioniGiocate() + 1) + " di 5");
        titolo.setFont(Fonts.titolo(20));
        titolo.getStyleClass().add("titolo-sezione");
        BorderPane.setAlignment(titolo, Pos.TOP_CENTER);
        BorderPane.setMargin(titolo, new Insets(0, 0, 20, 0));
        radice.setTop(titolo);

        radice.setLeft(costruisciPannelloStatistiche());
        radice.setCenter(costruisciPannelloCentrale());

        Scene scena = new Scene(radice, Layout.LARGHEZZA, Layout.ALTEZZA);
        scena.getStylesheets().add(Layout.FOGLIO_STILE);
        stage.setScene(scena);

        aggiornaStatisticheFalco();
        aggiornaSottotitoloPreda();
        mostraPredaCorrente();
    }

    private VBox costruisciPannelloStatistiche() {
        VBox pannello = new VBox(14);
        pannello.getStyleClass().add("pannello-stats");
        pannello.setPadding(new Insets(20));
        pannello.setPrefWidth(230);
        BorderPane.setMargin(pannello, new Insets(0, 20, 0, 0));

        Label titoloFalco = new Label(controller.getFalconiere().getFalco().getNome());
        titoloFalco.setFont(Fonts.titolo(13));
        titoloFalco.getStyleClass().add("titolo-sezione");
        titoloFalco.setWrapText(true);

        labelFame = new Label();
        labelFame.setFont(Fonts.testo(18));
        labelFame.getStyleClass().add("testo-legno");
        barraFame = new ProgressBar();
        barraFame.getStyleClass().add("barra-fame");
        barraFame.setPrefWidth(190);

        labelEnergia = new Label();
        labelEnergia.setFont(Fonts.testo(18));
        labelEnergia.getStyleClass().add("testo-legno");
        barraEnergia = new ProgressBar();
        barraEnergia.getStyleClass().add("barra-energia");
        barraEnergia.setPrefWidth(190);

        labelAddestramento = new Label();
        labelAddestramento.setFont(Fonts.testo(18));
        labelAddestramento.getStyleClass().add("testo-legno");
        barraAddestramento = new ProgressBar();
        barraAddestramento.getStyleClass().add("barra-addestramento");
        barraAddestramento.setPrefWidth(190);

        ImageView vistaFalco = new ImageView();
        vistaFalco.setFitWidth(140);
        vistaFalco.setFitHeight(140);
        vistaFalco.setPreserveRatio(true);
        Falco falco = controller.getFalconiere().getFalco();
        Image immagineFalco = Immagini.carica(Immagini.immagineFalco(falco.getClass().getSimpleName()));
        if (immagineFalco != null) {
            vistaFalco.setImage(immagineFalco);
        }

        pannello.getChildren().addAll(
                titoloFalco, vistaFalco, new Separator(),
                labelFame, barraFame,
                labelEnergia, barraEnergia,
                labelAddestramento, barraAddestramento
        );
        return pannello;
    }

    private VBox costruisciPannelloCentrale() {
        VBox pannello = new VBox(20);
        pannello.setAlignment(Pos.TOP_CENTER);

        HBox azioni = new HBox(15);
        azioni.setAlignment(Pos.CENTER);

        pulsanteNutri = creaPulsanteAzione("Nutri", new Nutri());
        pulsanteRiposa = creaPulsanteAzione("Riposa", new Riposa());
        pulsanteAllena = creaPulsanteAzione("Allena", new Addestra());
        azioni.getChildren().addAll(pulsanteNutri, pulsanteRiposa, pulsanteAllena);

        sottotitoloPreda = new Label();
        sottotitoloPreda.setFont(Fonts.testo(18));
        sottotitoloPreda.getStyleClass().add("titolo-sezione");

        pulsanteCaccia = new Button("Affronta la caccia");
        pulsanteCaccia.setFont(Fonts.testo(20));
        pulsanteCaccia.getStyleClass().add("pulsante-oro");
        pulsanteCaccia.setOnAction(evento -> affrontaProssimaPreda());

        pannelloPreda = new VBox(10);
        pannelloPreda.getStyleClass().add("box-legno");
        pannelloPreda.setAlignment(Pos.CENTER);
        pannelloPreda.setPadding(new Insets(15));
        pannelloPreda.setPrefWidth(300);
        pannelloPreda.setVisible(false);
        pannelloPreda.setManaged(false);

        labelEsito = new Label();
        labelEsito.setFont(Fonts.testo(20));
        labelEsito.getStyleClass().add("titolo-sezione");

        pannello.getChildren().addAll(azioni, new Separator(), sottotitoloPreda, pulsanteCaccia, pannelloPreda, labelEsito);
        return pannello;
    }

    private Button creaPulsanteAzione(String testo, it.unicam.cs.mdpg.rpg129029.model.azione.Azione azione) {
        Button pulsante = new Button(testo);
        pulsante.setFont(Fonts.testo(18));
        pulsante.getStyleClass().add("pulsante-pixel");
        pulsante.setPrefWidth(110);
        pulsante.setOnAction(evento -> {
            if(azioneEseguita) return;
            controller.eseguiAzione(azione);
            aggiornaStatisticheFalco();
            azioneEseguita = true;
            disabilitaPulsanteAzione();
        });
        return pulsante;
    }

    private void disabilitaPulsanteAzione(){
        pulsanteNutri.setDisable(true);
        pulsanteRiposa.setDisable(true);
        pulsanteAllena.setDisable(true);
    }

    private void mostraPredaCorrente(){
        Preda preda = controller.getMissioneCorrente().getPrede().get(indicePreda);
        mostraSchedaPreda(preda);
    }

    private void affrontaProssimaPreda() {
        disabilitaPulsanteAzione();
        Missione missioneCorrente = controller.getMissioneCorrente();
        Preda preda = missioneCorrente.getPrede().get(indicePreda);

        boolean successo = controller.affrontaCaccia(preda);
        aggiornaStatisticheFalco();

        if (successo) {
            labelEsito.setText("Caccia riuscita: " + preda.getNome() + " catturata!");
        } else {
            labelEsito.setText("Caccia fallita: " + preda.getNome() + " è scappata.");
        }

        if (controller.giocatorePerso()) {
            controller.terminaPartita();
            SchermataFinePartita.mostra(stage, controller);
            return;
        }

        indicePreda++;
        if (indicePreda >= missioneCorrente.getPrede().size()) {
            controller.concludiMissione();
            SchermataFineMissione.mostra(stage, controller);
        } else {
            aggiornaSottotitoloPreda();
            mostraPredaCorrente();
        }
    }

    private void mostraSchedaPreda(Preda preda) {
        pannelloPreda.getChildren().clear();

        ImageView vistaImmagine = new ImageView();
        vistaImmagine.setFitWidth(90);
        vistaImmagine.setFitHeight(90);
        vistaImmagine.setPreserveRatio(true);
        Image immagine = Immagini.carica(Immagini.immaginePreda(preda.getNome()));
        if (immagine != null) {
            vistaImmagine.setImage(immagine);
        }

        Label nome = new Label(preda.getNome());
        nome.setFont(Fonts.titolo(13));
        nome.getStyleClass().add("testo-legno");

        Label statistiche = new Label(
                "Difficoltà: " + preda.getDifficolta() + "\n" +
                        "Energia richiesta: " + preda.getEnergiaRichiesta() + "\n" +
                        "Fame generata: " + preda.getFameGenerata()
        );
        statistiche.setFont(Fonts.testo(17));
        statistiche.getStyleClass().add("testo-legno");
        statistiche.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        pannelloPreda.getChildren().addAll(vistaImmagine, nome, statistiche);
        pannelloPreda.setVisible(true);
        pannelloPreda.setManaged(true);
    }

    private void aggiornaSottotitoloPreda() {
        int numeroPreda = indicePreda + 1;
        int totalePrede = controller.getMissioneCorrente().getPrede().size();
        sottotitoloPreda.setText("Preda " + numeroPreda + " di " + totalePrede);
        pulsanteCaccia.setText(numeroPreda == 1 ? "Affronta la caccia" : "Affronta la seconda preda");
    }

    private void aggiornaStatisticheFalco() {
        Falco falco = controller.getFalconiere().getFalco();
        labelFame.setText("Fame: " + falco.getFame() + "/" + MASSIMO_STAT);
        barraFame.setProgress(falco.getFame() / (double) MASSIMO_STAT);

        labelEnergia.setText("Energia: " + falco.getEnergia() + "/" + MASSIMO_STAT);
        barraEnergia.setProgress(falco.getEnergia() / (double) MASSIMO_STAT);

        labelAddestramento.setText("Addestramento: " + falco.getAddestramento() + "/" + MASSIMO_STAT);
        barraAddestramento.setProgress(falco.getAddestramento() / (double) MASSIMO_STAT);
    }
}
