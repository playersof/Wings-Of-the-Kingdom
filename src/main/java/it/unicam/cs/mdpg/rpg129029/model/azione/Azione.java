package it.unicam.cs.mdpg.rpg129029.model.azione;

import it.unicam.cs.mdpg.rpg129029.model.falco.Falco;
/**
 * Rappresenta l'azione che il falconiere esegue prima di affrontare ogni missione.
 * Isolre l'azione in un interfaccia permette in caso di modifiche future di poter
 * aggiungere nuove azioni composte da più azioni già esistenti
 * aggiungendo semplicemente una nuova classe che implementi questa interfaccia
 */
public interface Azione {
    /**
     * @param falco il falco che subirà l'azione
     */
    void esegui(Falco falco);
}
