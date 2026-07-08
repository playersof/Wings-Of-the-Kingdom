package it.unicam.cs.mdpg.rpg129029.model;

import it.unicam.cs.mdpg.rpg129029.model.preda.Preda;

import java.util.List;

public class Missione {
    private List<Preda> prede;
    private int predeCatturate;
    private boolean completata;
    //variabile modificabile in caso di estendibilità per far si che in futuro si possano aggiungere più caccie per missione
    private static final int NUM_PREDE_PER_MISSIONE = 2;

    public Missione(List<Preda> prede ){
        if (prede.size() != NUM_PREDE_PER_MISSIONE) {
            throw new IllegalArgumentException("Una missione deve contenere esattamente "+ NUM_PREDE_PER_MISSIONE + " prede.");
        }
        this.prede = prede ;
        this.predeCatturate = 0;
        completata = false;
    }
    public List<Preda> getPrede(){ return prede; }

    public void incrementaPredeCatturate() {
        this.predeCatturate++;
        if(this.predeCatturate == prede.size()){
            completata = true;
        }
    }

    public boolean isCompletata(){ return completata; }

    public static int getNumPredePerMissione() { return NUM_PREDE_PER_MISSIONE; }
}
