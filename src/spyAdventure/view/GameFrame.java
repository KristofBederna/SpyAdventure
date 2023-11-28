package spyAdventure.view;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame(GamePanel gamePanel) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("SPY ADVENTURE");
        add(gamePanel);

        pack();
        setVisible(true);
    }
}
