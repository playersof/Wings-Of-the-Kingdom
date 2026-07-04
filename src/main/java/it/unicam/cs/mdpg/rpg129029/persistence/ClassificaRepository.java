package it.unicam.cs.mdpg.rpg129029.persistence;

import it.unicam.cs.mdpg.rpg129029.model.Punteggio;
/**
 * Repository specializzata per la gestione dei punteggi.
 * Estende la repository generica specificando come tipo salvato
 * {@code Punteggio} e come identificatore una {@code int}.
 *
 * In futuro permette di aggiungere operazioni specifiche per la
 * gestione della classifica senza modificare la repository generica.
 */

public interface ClassificaRepository extends Repository <Punteggio, Integer> {
}
