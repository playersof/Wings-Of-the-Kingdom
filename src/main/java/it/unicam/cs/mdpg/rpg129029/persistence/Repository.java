package it.unicam.cs.mdpg.rpg129029.persistence;

import java.util.List;

public interface Repository <T, ID>{
        void salva(T elemento);
        List<T> trovaTutti();
        void elimina(ID id);
}
