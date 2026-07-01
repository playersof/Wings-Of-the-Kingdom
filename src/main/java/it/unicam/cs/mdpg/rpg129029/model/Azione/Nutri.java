package it.unicam.cs.mdpg.rpg129029.model.Azione;

import it.unicam.cs.mdpg.rpg129029.model.Falco.Falco;

public class Nutri implements Azione{
    @Override
    public void esegui(Falco falco){
        falco.nutri();
    }
}
