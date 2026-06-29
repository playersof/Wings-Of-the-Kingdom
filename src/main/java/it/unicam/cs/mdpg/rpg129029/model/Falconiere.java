package it.unicam.cs.mdpg.rpg129029.model;

import it.unicam.cs.mdpg.rpg129029.model.Falco.Falco;

import java.util.Objects;

public class Falconiere {
    private String nome;
    private final Falco falco;
    private int missioniCompletate;

    public Falconiere(String nome, Falco falco, int missioniCompletate){
        this.nome = nome;
        this.falco = falco;
        this.missioniCompletate = missioniCompletate;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMissioniCompletate() {
        return missioniCompletate;
    }

    public void setMissioniCompletate(int missioniCompletate) {
        this.missioniCompletate = missioniCompletate;
    }

    public Falco getFalco() {
        return falco;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Falconiere that = (Falconiere) o;
        return missioniCompletate == that.missioniCompletate && Objects.equals(nome, that.nome) && Objects.equals(falco, that.falco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, falco, missioniCompletate);
    }
}
