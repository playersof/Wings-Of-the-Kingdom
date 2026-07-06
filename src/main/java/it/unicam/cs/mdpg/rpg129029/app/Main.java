package it.unicam.cs.mdpg.rpg129029.app;

import it.unicam.cs.mdpg.rpg129029.controller.GameControl;
import it.unicam.cs.mdpg.rpg129029.persistence.file.ClassificaRepositoryFile;

public class Main {
    GameControl gameControl = new GameControl(new ClassificaRepositoryFile("data/classifica.json"));
}
