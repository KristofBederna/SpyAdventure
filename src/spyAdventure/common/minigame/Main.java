package spyAdventure.common.minigame;
import spyAdventure.common.minigame.model.MiniGameModel;
import spyAdventure.common.minigame.view.MiniGameFrame;
import spyAdventure.view.GamePanel;

import javax.swing.*;

public class Main {
    private final GamePanel gamePanel;
    //Constructor
    public Main(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void main() {
        SwingUtilities.invokeLater(() -> {
            new MiniGameFrame(gamePanel ,new MiniGameModel()).setVisible(true);
                });
    }
}