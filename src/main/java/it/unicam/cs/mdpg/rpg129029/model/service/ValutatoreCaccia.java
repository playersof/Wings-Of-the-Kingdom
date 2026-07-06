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
    final Random random = new Random();

    public boolean valutaCaccia(Falco falco, Preda preda){
        if(!falco.haEnergia() || falco.haTroppaFame()) return false;
        int fattoreCasuale = random.nextInt(11) -5;
        int punteggioFalco = falco.getAddestramento() + (falco.getEnergia() / 2) - (falco.getFame() / 2);
        int difficoltaEffettiva = preda.getDifficolta() + fattoreCasuale;
        return punteggioFalco >= difficoltaEffettiva;
    }
}
