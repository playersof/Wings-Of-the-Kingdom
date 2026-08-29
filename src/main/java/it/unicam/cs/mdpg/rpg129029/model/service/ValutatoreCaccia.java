package it.unicam.cs.mdpg.rpg129029.model.service;

import it.unicam.cs.mdpg.rpg129029.model.falco.Falco;
import it.unicam.cs.mdpg.rpg129029.model.preda.Preda;

import java.util.Random;

/**
 * Questa classe si deve occupare di calcolare l'esito di una battuta di caccia,
 * prende i dati di ogni preda per missione e del falco,
 * li combina e con una formula basata sulle statistiche ritorna un boolean
 * che descrive se la caccia ha avuto esito positivo o meno
 */
public class ValutatoreCaccia {
    //random.nextInt(11) genera un numero casuale da 0 a 10; sottraendo 5 si ottiene
    //un fattore casuale centrato su 0, compreso tra -5 e +5.
    private static final int AMPIEZZA_FATTORE_CASUALE = 11;
    private static final int OFFSET_FATTORE_CASUALE = 5;
    private final Random random;


    public ValutatoreCaccia(Random random){
        if(random == null) throw new NullPointerException("il random non può essere nullo");
        this.random = random;
    }

    public boolean valutaCaccia(Falco falco, Preda preda){
        if(!falco.haEnergia() || falco.haTroppaFame()) return false;
        int fattoreCasuale = random.nextInt(AMPIEZZA_FATTORE_CASUALE) -OFFSET_FATTORE_CASUALE;
        int punteggioFalco = falco.getAddestramento() + (falco.getEnergia() / 2) - (falco.getFame() / 2);
        int difficoltaEffettiva = preda.getDifficolta() + fattoreCasuale;
        return punteggioFalco >= difficoltaEffettiva;
    }
}
