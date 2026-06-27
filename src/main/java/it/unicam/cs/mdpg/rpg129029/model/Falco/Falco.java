package it.unicam.cs.mdpg.rpg129029.model.Falco;

public abstract class Falco {
    private String nome;
    private int fame;
    private int energia;
    private int addestramento;

    public Falco (String nome, int fame, int energia, int addestramento){
        this.nome = nome;
        this.fame = fame;
        this.energia = energia;
        this.addestramento = addestramento;
    }

    public int getEnergia() {
        return energia;
    }

    public int getFame() {
        return fame;
    }

    public int getAddestramento() {
        return addestramento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFame(int fame) {
        this.fame = fame;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void setAddestramento(int addestramento) {
        this.addestramento = addestramento;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
