package it.unicam.cs.mdpg.rpg129029.model.Azione;

import it.unicam.cs.mdpg.rpg129029.model.Falco.Falco;

public class Riposa implements Azione{
    @Override
    public void esegui(Falco falco){
        falco.riposa();
    }
}
