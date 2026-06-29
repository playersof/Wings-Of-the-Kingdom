package it.unicam.cs.mdpg.rpg129029.model.Preda;

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

}
