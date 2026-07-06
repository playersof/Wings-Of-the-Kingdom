package it.unicam.cs.mdpg.rpg129029.model.service;

import it.unicam.cs.mdpg.rpg129029.model.falco.Astore;
import it.unicam.cs.mdpg.rpg129029.model.falco.Falco;
import it.unicam.cs.mdpg.rpg129029.model.falco.Harris;
import it.unicam.cs.mdpg.rpg129029.model.falco.Pellegrino;


/**
 * FalcoFactory gestisce la scelta del falco da parte del falconiere
 * tra le possibili sottoclassi di Falco (Harris, Pellegrino, Astore)
 */
public class FalcoFactory {
    /**
     * creaFalco(...)
     * crea un nuovo falco del tipo richiesto
     *
     * @param tipo Nome del tipo di falco tra quelli disponibili
     * @return una nuova istanza del falco scelto
     * @throws IllegalArgumentException se il tipo di falco non è tra quelli disponibili o se il tipo di falco è nullo
     */
    public Falco creaFalco (String tipo) {
        if(tipo == null || tipo.isBlank()) throw new IllegalArgumentException("il tipo deve essere valido");
        return switch (tipo){
            case "Pellegrino" -> new Pellegrino();
            case "Harris" -> new Harris();
            case "Astore" -> new Astore();
            default -> throw new IllegalArgumentException("il tipo di falco non esiste");
        };
    }
}

