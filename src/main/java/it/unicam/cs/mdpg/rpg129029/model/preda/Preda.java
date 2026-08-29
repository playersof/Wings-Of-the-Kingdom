package it.unicam.cs.mdpg.rpg129029.model.preda;

/**
 * Rappresenta la preda che verrà cacciata in ogni missione
 * caratterizzata da una difficoltà, energiaRichiesta e fameGenerata che
 * influiranno sull'esito della caccia e/o sulle statistiche del falco
 */

public abstract class Preda {
    private final TipoPreda tipo;

    public Preda (TipoPreda tipo){
        if(tipo == null) throw new NullPointerException("il tipo non può essere nullo");
        this.tipo = tipo;
    }

    public String getNome() { return tipo.getNomeVisualizzato(); }

    public int getDifficolta() { return tipo.getDifficolta(); }

    public int getEnergiaRichiesta() { return tipo.getEnergiaRichiesta(); }

    public int getFameGenerata() { return tipo.getFameGenerata();}

    public TipoPreda getTipo() {
        return tipo;
    }
}
