package it.unicam.cs.mdpg.rpg129029.model.preda;

public enum TipoPreda {
    ANATRA("Anatra", 50, 12, 8, "/Prede/Anatra.png"),
    CONIGLIO("Coniglio", 25, 8, 5, "/Prede/Coniglio.png"),
    QUAGLIA("Quaglia", 35, 10, 6, "/Prede/Quaglia.png"),
    VOLPE("Volpe", 65, 18, 12, "/Prede/Volpe.png"),
    ;

    private final String nomeVisualizzato;
    private final int difficolta;
    private final int energiaRichiesta;
    private final int fameGenerata;
    private final String percorsoImmagine;

    TipoPreda(String nomeVisualizzato, int difficolta, int energiaRichiesta, int fameGenerata, String percorsoImmagine){
        this.nomeVisualizzato = nomeVisualizzato;
        this.difficolta= difficolta;
        this.energiaRichiesta = energiaRichiesta;
        this.fameGenerata = fameGenerata;
        this.percorsoImmagine = percorsoImmagine;
    }

    public String getNomeVisualizzato() {
        return nomeVisualizzato;
    }

    public int getDifficolta() {
        return difficolta;
    }

    public int getEnergiaRichiesta() {
        return energiaRichiesta;
    }

    public int getFameGenerata() {
        return fameGenerata;
    }

    public String getPercorsoImmagine() {
        return percorsoImmagine;
    }
}
