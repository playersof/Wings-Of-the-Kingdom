package it.unicam.cs.mdpg.rpg129029.persistence;

import java.util.List;

/**
 * Interface Repository <T, ID>
 * @param <T> parametro da salvare
 * @param <ID> chiave tramite la quale cercare valore da eliminare
 */

public interface Repository <T, ID>{
        void salva(T elemento);
        List<T> trovaTutti();
}
