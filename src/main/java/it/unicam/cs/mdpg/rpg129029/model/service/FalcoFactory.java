package it.unicam.cs.mdpg.rpg129029.model.service;

import it.unicam.cs.mdpg.rpg129029.model.falco.Astore;
import it.unicam.cs.mdpg.rpg129029.model.falco.Falco;
import it.unicam.cs.mdpg.rpg129029.model.falco.Harris;
import it.unicam.cs.mdpg.rpg129029.model.falco.Pellegrino;

import java.util.Map;
import java.util.function.Supplier;

/**
 * FalcoFactory gestisce la scelta del falco da parte del falconiere
 * tra le possibili sottoclassi di Falco (Harris, Pellegrino, Astore)
 */
public class FalcoFactory {
    /**
     * Map che tiene coppia chiave, valore per creare falchi,
     * in caso di estendibilità basta aggiungere al
     * Supplier una nuova riga
     */
    private final Map<String, Supplier<Falco>> tipiDisponibili = Map.of(
            "Pellegrino", Pellegrino::new,
            "Harris", Harris::new,
            "Astore", Astore::new
    );

    /**
     * creaFalco(...)
     * crea un nuovo falco del tipo richiesto
     *
     * @param tipo Nome del tipo di falco tra quelli disponibili
     * @return una nuova istanza del falco scelto
     * @throws IllegalArgumentException se il tipo di falco non è tra quelli disponibili
     */
    public Falco creaFalco(String tipo) {
        Supplier<Falco> costruttoreFalco = tipiDisponibili.get(tipo);
        if (costruttoreFalco == null) throw new IllegalArgumentException("il falco " + tipo + " non esiste");
        return costruttoreFalco.get();
    }
    //public getTipiDisponibili()
}

