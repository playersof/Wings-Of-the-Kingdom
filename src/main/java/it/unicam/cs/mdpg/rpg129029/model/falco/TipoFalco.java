package it.unicam.cs.mdpg.rpg129029.model.falco;

/**
 * Raccoglie in un unico posto tutti i dati che caratterizzano ogni tipo di falco
 * disponibile, elimina la duplicazione di questi dati centralizzandoli in un
 * unico luogo evitando che restino disallineati in futuro se vengono modificati
 */

public enum TipoFalco {
    PELLEGRINO("Pellegrino", 30 , 80, 40, "/Falchi/Pellegrino.png"),
    HARRIS("Poiana di Harris", 35, 90, 30, "/Falchi/Harris.png"),
    ASTORE("Astore", 40, 70, 50, "/Falchi/Astore.png");

    private final String nomeVisualizzato;
    private final int fameIniziale;
    private final int energiaIniziale;
    private final int addestramentroIniziale;
    private final String percorsoImmagine;

    TipoFalco(String nomeVisualizzato, int fameIniziale, int energiaIniziale, int addestramentroIniziale, String percorsoImmagine){
        this.nomeVisualizzato = nomeVisualizzato;
        this.fameIniziale = fameIniziale;
        this.energiaIniziale = energiaIniziale;
        this.addestramentroIniziale = addestramentroIniziale;
        this.percorsoImmagine = percorsoImmagine;
    }

    public int getFameIniziale() {
        return fameIniziale;
    }

    public String getNomeVisualizzato() {
        return nomeVisualizzato;
    }

    public int getEnergiaIniziale() {
        return energiaIniziale;
    }

    public String getPercorsoImmagine() {
        return percorsoImmagine;
    }

    public int getAddestramentroIniziale() {
        return addestramentroIniziale;
    }
}

