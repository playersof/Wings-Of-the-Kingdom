package it.unicam.cs.mdpg.rpg129029.model.service;

import it.unicam.cs.mdpg.rpg129029.model.Missione;
import it.unicam.cs.mdpg.rpg129029.model.preda.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Un GeneratoreMissioniCasuali permette di generare una missione
 * sfruttando il costruttore di Missione, il suo compito è quello di
 * sceglie in maniera randomica due prede casuali tra quelle esistenti
 * e poi richiama il costrutture di Missione
 * mandando come parametri un id e scelta random tra le prede
 */
public class GeneratoreMissioniCasuali implements GeneratorePredaCasuale{
    private final Random generatoreInt = new Random();
    private int id = 1;

    public Missione generaMissione() {
        List<Preda> lista = new ArrayList<>();
        for (int i = 0; i < Missione.getNumPredePerMissione() ; i++) {
            int indicePreda= generatoreInt.nextInt(0,4);
            Preda preda = creaPreda(indicePreda);
            lista.add(preda);
        }
        Missione missione = new Missione(id,lista);
        id++;
        return missione;
    }

    @Override
    public Preda creaPreda(int ind){
        return switch (ind) {
            case 0 -> new Anatra();
            case 1 -> new Coniglio();
            case 2 -> new Quaglia();
            case 3 -> new Volpe();
            default -> throw new IllegalArgumentException("preda non valida");
        };
    }
}
