package it.unicam.cs.mdpg.rpg129029.controller;

import it.unicam.cs.mdpg.rpg129029.model.Falconiere;
import it.unicam.cs.mdpg.rpg129029.model.Missione;

import java.util.List;

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
    private List<Missione> missioni;
    private int missioneCorrente;
    private int fallimenti;

    public void iniziaPartita(){

    }
}
