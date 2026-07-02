package it.unicam.cs.mdpg.rpg129029.model.azione;

import it.unicam.cs.mdpg.rpg129029.model.falco.Falco;

public class Nutri implements Azione{
    @Override
    public void esegui(Falco falco){
        falco.nutri();
    }
}
