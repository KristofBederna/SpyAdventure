package spyAdventure.common.minigame.view;

import spyAdventure.common.minigame.Main;
import spyAdventure.common.minigame.model.MiniGameModel;
import spyAdventure.common.minigame.util.*;
import spyAdventure.view.GameFrame;
import spyAdventure.view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiniGamePanel extends JPanel {
    private MiniGameModel gameModel;
    private MiniGameFrame gameFrame;
    private GamePanel gamePanel;

    public MiniGamePanel(MiniGameModel gameModel, MiniGameFrame gameFrame, GamePanel gamePanel) {
        this.gameModel = gameModel;
        this.gameFrame = gameFrame;
        this.gamePanel = gamePanel;
        newGame();
    }

    public void newGame() {
        setUpGamePanel();
        refreshUI();
    }

    private void setUpGamePanel() {
        removeAll();
        int n = gameModel.getSize();
        setLayout(new GridLayout(n, n));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                GridButton gridButton = new GridButton(i, j);
                gridButton.setPreferredSize(new Dimension(50,50));
                gridButton.addActionListener(new GridButtonListener());
                add(gridButton);
            }
        }
        revalidate();
        repaint();
    }

    private void refreshUI() {
        for (Component component: getComponents()) {
            GridButton gridButton = (GridButton) component;
            gameModel.getField(gridButton.getRow(), gridButton.getColumn());
            gridButton.setBackground(getColorByValue(gameModel.getField(gridButton.getRow(), gridButton.getColumn())));
        }
    }

    private Color getColorByValue(int value) {
        return switch (value) {
            case 0 -> Color.cyan;
            case 1 -> Color.orange;
            case 2 -> Color.green;
            default -> Color.white;
        };
    }


    private class GridButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GridButton gridButton = (GridButton) e.getSource();
            int row = gridButton.getRow();
            int column = gridButton.getColumn();
            gameModel.changeFields(row, column);

            refreshUI();

            checkForGameEnd();
        }
    }

    private void checkForGameEnd() {
        if (gameModel.isGameOver()) {
            gamePanel.setFinishedMinigame(true);
            gamePanel.setGameState("go");
            this.gameFrame.dispose();
        }
    }
}
