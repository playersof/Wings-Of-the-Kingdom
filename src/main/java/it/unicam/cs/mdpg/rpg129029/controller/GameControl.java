package it.unicam.cs.mdpg.rpg129029.controller;

import it.unicam.cs.mdpg.rpg129029.model.Falconiere;
import it.unicam.cs.mdpg.rpg129029.model.Missione;
import it.unicam.cs.mdpg.rpg129029.model.Punteggio;
import it.unicam.cs.mdpg.rpg129029.model.azione.Azione;
import it.unicam.cs.mdpg.rpg129029.model.falco.Falco;
import it.unicam.cs.mdpg.rpg129029.model.preda.Preda;
import it.unicam.cs.mdpg.rpg129029.model.service.FalcoFactory;
import it.unicam.cs.mdpg.rpg129029.model.service.GeneratoreMissioniCasuali;
import it.unicam.cs.mdpg.rpg129029.model.service.ValutatoreCaccia;
import it.unicam.cs.mdpg.rpg129029.persistence.ClassificaRepository;

import java.util.UUID;

/**
 * Mediatore tra la GUI e il resto dell'applicazione (model e persistence).
 * E' l'unico punto di contatto: la GUI non accede mai direttamente alle classi
 * del model o della persistence, ma passa sempre da qui.
 * Questo garantisce che, se in futuro si vuole sostituire la GUI (es. passare
 * da Swing a JavaFX o a una versione web), il controller resta invariato.
 */
public class GameControl {
    //Attributi
    private Falconiere falconiere;
    private Missione missioneCorrente;
    private static final int MISSIONI_NECESSARIE = 3;
    private static final int PREDE_NECESSARIE = 5;
    private static final int MISSIONI_TOTALI = 5;
    private final GeneratoreMissioniCasuali generatoreMissioni;
    private final ValutatoreCaccia valutatoreCaccia;
    private final FalcoFactory falcoFactory;
    private int missioniGiocate;
    private final ClassificaRepository classificaRepository;


    public GameControl(ClassificaRepository classificaRepository) {
        if (classificaRepository == null) throw new NullPointerException("il parametro passato non può essere nullo");
        this.classificaRepository = classificaRepository;
        this.generatoreMissioni = new GeneratoreMissioniCasuali();
        this.valutatoreCaccia = new ValutatoreCaccia();
        this.falcoFactory = new FalcoFactory();
    }

    //CREAZIONE GIOCATORE

    public void creaFalconiere(String nome){
        if(nome == null || nome.isBlank() || nome.length() < 3) throw new IllegalArgumentException("Il nome non è valido");
        this.falconiere = new Falconiere(UUID.randomUUID().toString(), nome, 0);
    }

    public void sceltaFalco(String tipoScelto){
        Falco falcoScelto = falcoFactory.creaFalco(tipoScelto);
        falconiere.assegnaFalco(falcoScelto);
    }

    //MISSIONE


    public void eseguiAzione(Azione azione){
        if(azione==null) throw new NullPointerException("L'azione non può essere nulla");
        azione.esegui(falconiere.getFalco());
    }
    public void iniziaMissione(){
        missioneCorrente = generatoreMissioni.generaMissione();
    }
    public void concludiMissione() {
        if(missioneCorrente.isCompletata()) falconiere.incrementaMissioniCompletate();
        missioniGiocate++;
    }

    public boolean affrontaCaccia(Preda preda) {
        boolean successo = valutatoreCaccia.valutaCaccia(falconiere.getFalco(), preda);
        if (successo) {
            falconiere.incrementaPredeCatturate();
            missioneCorrente.incrementaPredeCatturate();
        } else {
            falconiere.incrementaPredePerse();
        }
        aggiornaStatsFalco(successo, preda);
        return successo;
    }

    private void aggiornaStatsFalco(boolean successo, Preda preda){
        Falco falco = falconiere.getFalco();
        falco.diminuisciEnergia(preda.getEnergiaRichiesta());
        if(successo){
            falco.aumentaAddestramento(10);
            falco.diminuisciFame(3);
        }
        else falco.aumentaFame(preda.getFameGenerata());
    }


    //PARTITA

//    public void avviaPartita(){
//        while(missioniGiocate<MISSIONI_TOTALI && !giocatorePerso()){
//            svolgiMissione();
//        }
//        terminaPartita();
//    }

    public boolean giocatorePerso(){
        return falconiere.getPredePerse()>= PREDE_NECESSARIE;
    }

    public boolean giocatoreVincitore(){
        return falconiere.getMissioniCompletate() >= MISSIONI_NECESSARIE;
    }

    public void terminaPartita(){
        if(giocatoreVincitore()) {
            Punteggio punteggio = new Punteggio(this.falconiere, falconiere.getMissioniCompletate(), falconiere.getPredeCatturate());
            classificaRepository.salva(punteggio);
        }
    }

    //GETTER

    public Missione getMissioneCorrente() {
        return missioneCorrente;
    }

    public Falconiere getFalconiere() {
        return falconiere;
    }

    public int getMissioniGiocate() {
        return missioniGiocate;
    }
    /**
     * Espone la classifica (i migliori punteggi salvati) alla GUI,
     * senza che questa debba accedere direttamente alla persistence.
     */
    public java.util.List<Punteggio> getClassifica() {
        return classificaRepository.trovaTutti();
    }
}
