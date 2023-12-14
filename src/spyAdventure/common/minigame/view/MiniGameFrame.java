package spyAdventure.common.minigame.view;
import spyAdventure.common.minigame.model.MiniGameModel;
import spyAdventure.view.GameFrame;
import spyAdventure.view.GamePanel;

import javax.swing.*;

public class MiniGameFrame extends JFrame {
    //Constructor
    public MiniGameFrame(GamePanel gamePanel, MiniGameModel gameModel) {
        setTitle("Color Matcher - Mini game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(200, 200);

        gameModel.newGame(2);

        MiniGamePanel miniGamePanel = new MiniGamePanel(gameModel, this, gamePanel);

        getContentPane().add(miniGamePanel);

        pack();
    }
}
