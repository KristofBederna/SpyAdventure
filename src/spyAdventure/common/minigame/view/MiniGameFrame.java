package spyAdventure.common.minigame.view;
import spyAdventure.common.minigame.model.MiniGameModel;
import spyAdventure.view.GameFrame;
import spyAdventure.view.GamePanel;

import javax.swing.*;

public class MiniGameFrame extends JFrame {
    private MiniGameModel gameModel;

    private MiniGamePanel miniGamePanel;
    private GamePanel gamePanel;

    public MiniGameFrame(GamePanel gamePanel, MiniGameModel gameModel) {
        this.gameModel = gameModel;
        setTitle("Color Matcher - Mini game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(200, 200);

        this.gameModel.newGame(2);

        this.miniGamePanel = new MiniGamePanel(this.gameModel, this, gamePanel);

        getContentPane().add(miniGamePanel);

        pack();
    }
}
