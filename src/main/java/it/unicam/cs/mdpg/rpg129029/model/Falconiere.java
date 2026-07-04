package it.unicam.cs.mdpg.rpg129029.model;

import it.unicam.cs.mdpg.rpg129029.model.falco.Falco;

import java.util.Objects;
/**
 * Rappresenta il giocatore durante la partita, colui che prenderà le decisioni principali, scelgierà il suo falco ad inizio
 * partita e ,alla fine se sarà tra i migliori,
 * si ritroverà in classifica col suo relativo punteggio
 */

public class Falconiere {
    private final String id;
    private String nome;
    private final Falco falco;
    private int missioniCompletate;

    /**
     * Costruttore della classe Falconiere
     * @param id del Falconiere
     * @param nome del Falconiere
     * @param falco del Falconiere scelto ad inizo gioco e che rimarrà fisso per tutta la partita, tramite il quale vengono affrontate le missioni
     * @param missioniCompletate le missioni completate dal falconiere fino a questo momento
     * @throws IllegalArgumentException se id è < 0
     * @throws IllegalArgumentException se nome è null o lunghezza inferiore a 3
     */
    //Costruttore

    public Falconiere(String id, String nome, Falco falco, int missioniCompletate){
        if(id == null || id.isEmpty()) throw new IllegalArgumentException("id non valido");
        if(nome == null || nome.length() < 3 || nome.isBlank()) throw new IllegalArgumentException("Nome non valido");
        this.id = id;
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

    public void incrementaMissioniCompletate() {
        this.missioniCompletate++;
    }

    public Falco getFalco() { return this.falco; }

    public String getId(){ return this.id; }

    /**
     * Due Falconieri sono considerati uguali se hanno lo stesso id univoco.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Falconiere falconiere)) return false;
        return id.equals(falconiere.id);
    }

    /**
     * L'hashCode e' basato solo sull'id, coerente con equals.
     */
    @Override
    public int hashCode()
    {
        return id.hashCode();
    }
}
