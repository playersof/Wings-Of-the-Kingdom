package it.unicam.cs.mdpg.rpg129029.model.service;

import it.unicam.cs.mdpg.rpg129029.model.Missione;
import it.unicam.cs.mdpg.rpg129029.model.preda.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//POSSIBILE MODIFICA IMPLEMENTA INTERFACCIA PER AVERE PIU GENERATORI DI MISSIONI

/**
 * Un GeneratoreMissioniCasuali permette di generare una missione
 * sfruttando il costruttore di Missione, il suo compito è quello di
 * sceglie in maniera randomica due prede casuali tra quelle esistenti
 * e poi richiama il costrutture di Missione
 * mandando come parametri un id e scelta random tra le prede
 */
public class GeneratoreMissioniCasuali {
    private final Random generatoreInt = new Random();
    private int id = 1;
//FUTURA MODIFICA POSSIBILE: <Supplier<Preda>> INVECE DI SWITCH CASE
    public Missione generaMissione() {
        List<Preda> lista = new ArrayList<>();
        for (int i = 0; i < Missione.getNumPredePerMissione() ; i++) {
            int indicePreda= generatoreInt.nextInt(0,4);
            Preda preda = switch (indicePreda) {
                case 0 -> new Anatra();
                case 1 -> new Coniglio();
                case 2 -> new Quaglia();
                case 3 -> new Volpe();
                default -> throw new IllegalArgumentException("preda non valida");
            };
            lista.add(preda);
        }
        Missione missione = new Missione(id,lista);
        id++;
        return missione;
    }
}
