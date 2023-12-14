package spyAdventure.model.Entities;

import spyAdventure.view.GamePanel;

public class Boss extends NPC{
    //Constructor
    public Boss(GamePanel gamePanel, int i, int j) {
        super(gamePanel, i, j);
        this.health = 5;
        this.speed = 3;
    }
}
