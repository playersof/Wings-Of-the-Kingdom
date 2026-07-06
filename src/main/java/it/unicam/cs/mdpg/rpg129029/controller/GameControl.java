package it.unicam.cs.mdpg.rpg129029.controller;

import it.unicam.cs.mdpg.rpg129029.model.Falconiere;
import it.unicam.cs.mdpg.rpg129029.model.Missione;
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
    private static final int MISSIONI_TOTALI = 5;
    private GeneratoreMissioniCasuali generatoreMissioni;
    private ValutatoreCaccia valutatoreCaccia;
    private FalcoFactory falcoFactory;


    public GameControl(ClassificaRepository classificaRepository) {
        if (classificaRepository == null) throw new NullPointerException("il parametro passato non può essere nullo");
        this.generatoreMissioni = new GeneratoreMissioniCasuali();
        this.valutatoreCaccia = new ValutatoreCaccia();
        this.falcoFactory = new FalcoFactory();
    }

    //Metodi

    public void creaFalconiere(String nome){
        if(nome == null || nome.isBlank() || nome.length() < 3) throw new IllegalArgumentException("Il nome non è valido");
        this.falconiere = new Falconiere(UUID.randomUUID().toString(), nome, 0);
    }

    public void sceltaFalco(String tipoScelto){
        Falco falcoScelto = falcoFactory.creaFalco(tipoScelto);
        falconiere.assegnaFalco(falcoScelto);
    }

    public void eseguiAzione(Falco falco){

    }
    public void nuovaMissione(){
        missioneCorrente = generatoreMissioni.generaMissione();
    }

    public void affrontaCaccia(Falco falco, Preda preda){
        if(valutatoreCaccia.valutaCaccia(falco, preda)) falconiere.incrementaMissioniCompletate();
        else falconiere.incrementaFallimenti();
    }
    public void controlloVittoria(){

    }
    //controllo se ho fatto 5 missioni
    public void fineMissioni(){

    }
    public void aggiornaStatsFalco(){

    }

    private int missioniGiocate(Falconiere falconiere) {
        return falconiere.getMissioniCompletate() + falconiere.getFallimenti();
    }

}
