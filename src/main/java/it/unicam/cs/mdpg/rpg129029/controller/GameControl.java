package it.unicam.cs.mdpg.rpg129029.controller;

import it.unicam.cs.mdpg.rpg129029.model.Falconiere;
import it.unicam.cs.mdpg.rpg129029.model.Missione;
import it.unicam.cs.mdpg.rpg129029.model.falco.Falco;
import it.unicam.cs.mdpg.rpg129029.model.service.FalcoFactory;
import it.unicam.cs.mdpg.rpg129029.model.service.GeneratoreMissioniCasuali;
import it.unicam.cs.mdpg.rpg129029.model.service.ValutatoreCaccia;
import it.unicam.cs.mdpg.rpg129029.persistence.PunteggioRepository;

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
    private Falco falco;
    private Missione missioneCorrente;
    private int fallimenti;
    private int missioniCompletate;
    private static final int MISSIONI_TOTALI = 5;
    private GeneratoreMissioniCasuali generatoreMissioni;
    private ValutatoreCaccia valutatoreCaccia;
    private FalcoFactory falcoFactory;


    public GameControl(PunteggioRepository punteggioRepository){
        if(punteggioRepository == null) throw new NullPointerException("il parametro passato non può essere nullo");
        this.generatoreMissioni = new GeneratoreMissioniCasuali();
        this.valutatoreCaccia = new ValutatoreCaccia();
        this.falcoFactory = new FalcoFactory();
    }

    //Metodi


    private int missioniGiocate() {
        return missioniCompletate + fallimenti;
    }

//    inizioPartita(),
//    sceltaFalco(),
//
//        for(5volte)
//    azione(),
//    controllo(),
//    creaMissione(),
//    battaglia();
//    aggiornamento(),
//    controlloVittoria()
}
