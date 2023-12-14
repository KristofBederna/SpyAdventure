package spyAdventure.model;

import spyAdventure.common.minigame.*;
import spyAdventure.view.GamePanel;

public class MinigameManager {
    private final Main main;
    private boolean canPlay = false;

    //Constructor
    public MinigameManager(GamePanel gamePanel) {
        main = new Main(gamePanel);
    }

    public void playMinigame() {
        if (canPlay) {
            main.main();
            canPlay = false;
        }
    }

    //Getters
    public boolean canPlay() {
        return canPlay;
    }

    //Setters
    public void setCanPlay(boolean b) {
        canPlay = b;
    }
}
