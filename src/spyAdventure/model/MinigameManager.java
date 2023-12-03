package spyAdventure.model;

import spyAdventure.common.minigame.*;
import spyAdventure.view.GamePanel;

public class MinigameManager {
    private GamePanel gamePanel;
    Main main;
    private boolean canPlay = false;
    public MinigameManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        main = new Main(gamePanel);
    }
    public void playMinigame() {
        if (canPlay) {
            main.main();
            canPlay = false;
        }
    }

    public void setCanPlay(boolean b) {
        canPlay = b;
    }

    public boolean canPlay() {
        return canPlay;
    }
}
