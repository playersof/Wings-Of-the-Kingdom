package it.unicam.cs.mdpg.rpg129029.model.falco;

/**
 * Rappresenta un Falco con tutte le sue statistiche che nel corso della
 * partita verranno modificate automaticamente o manualmente tramite azioni
 * intraprese dal giocatore (falconiere) prima di ogni missione
 */
public abstract class Falco {
    private final TipoFalco tipo;
    private int fame;
    private int energia;
    private int addestramento;
    private static final int MAX_STAT = 100;
    private static final int MIN_STAT = 0;
    private static final int MIN_ENERGIA_CACCIA = 30;

    protected Falco (TipoFalco tipo){
        if(tipo == null) throw new NullPointerException("Il tipo non può essere nullo");
        this.tipo = tipo;
        this.fame = tipo.getFameIniziale();
        this.energia = tipo.getEnergiaIniziale();
        this.addestramento = tipo.getAddestramentroIniziale();
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
        return tipo.getNomeVisualizzato();
    }

    public void diminuisciEnergia(int valore) {
        this.energia = Math.max(MIN_STAT, energia - valore);
    }

    public void diminuisciFame(int valore){ this.fame = Math.max(MIN_STAT, fame - valore); }

    public void aumentaFame(int valore) {
        this.fame = Math.min(MAX_STAT, fame + valore);
    }

    public void aumentaAddestramento(int valore) {
        this.addestramento = Math.min(MAX_STAT, addestramento + valore);
    }

    public void nutri(){ this.fame= Math.max(MIN_STAT, this.fame - 10);}

    public void riposa(){ this.energia = Math.min(MAX_STAT, energia+20);}

    public void addestra(){
        aumentaAddestramento(5);
        diminuisciEnergia(10);
        aumentaFame(5);
    }

    public boolean haEnergia() {
        return energia >= MIN_ENERGIA_CACCIA;
    }
    public boolean haTroppaFame() { return fame >= MAX_STAT; }
}
