package it.unicam.cs.mdpg.rpg129029.model.preda;

/**
 * Rappresenta la preda che verrà cacciata in ogni missione
 * caratterizzata da una difficoltà, energiaRichiesta e fameGenerata che
 * influiranno sull'esito della caccia e/o sulle statistiche del falco
 */

public abstract class Preda {
    private String nome;
    private final int difficolta;
    private final int energiaRichiesta;
    private final int fameGenerata;

    public Preda (String nome, int difficolta, int energia, int fame){
        this.nome = nome;
        this.difficolta = difficolta;
        this.energiaRichiesta = energia;
        this.fameGenerata= fame;
    }

    public String getNome() { return nome; }

    public int getDifficolta() { return difficolta; }

    public int getEnergiaRichiesta() { return energiaRichiesta; }

    public int getFameGenerata() { return fameGenerata; }
}
