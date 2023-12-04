package spyAdventure.common;

import spyAdventure.common.minigame.Main;
import spyAdventure.view.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.Key;

public class MovementHandler implements KeyListener {
    public boolean up, down, left, right;
    private GamePanel gamePanel;

    public MovementHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try{
            switch (e.getKeyCode()) {
                case(KeyEvent.VK_W):
                    up = true;
                    break;
                case(KeyEvent.VK_S):
                    down = true;
                    break;
                case(KeyEvent.VK_A):
                    left = true;
                    break;
                case(KeyEvent.VK_D):
                    right = true;
                    break;
                case(KeyEvent.VK_1):
                    gamePanel.getPlayer().setActiveItem(1);
                    break;
                case(KeyEvent.VK_2):
                    gamePanel.getPlayer().setActiveItem(2);
                    break;
                case(KeyEvent.VK_3):
                    gamePanel.getPlayer().setActiveItem(3);
                    break;
                case(KeyEvent.VK_4):
                    gamePanel.getPlayer().setActiveItem(4);
                    break;
                case(KeyEvent.VK_5):
                    gamePanel.getPlayer().setActiveItem(5);
                    break;
                case(KeyEvent.VK_6):
                    gamePanel.getPlayer().setActiveItem(6);
                    break;
                case(KeyEvent.VK_7):
                    gamePanel.getPlayer().setActiveItem(7);
                    break;
                case(KeyEvent.VK_8):
                    gamePanel.getPlayer().setActiveItem(8);
                    break;
                case(KeyEvent.VK_9):
                    gamePanel.getPlayer().setActiveItem(9);
                    break;
                case(KeyEvent.VK_ENTER):
                    gamePanel.getPlayer().useActiveItem();
                    break;
                case(KeyEvent.VK_E):
                    if (gamePanel.getMM().canPlay()) {
                        up = false; left = false; right = false; down = false;
                        gamePanel.setGameState("pause");
                        gamePanel.getMM().playMinigame();
                    }
            }
        } catch (Exception ignored)  {

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case(KeyEvent.VK_W):
                up = false;
                break;
            case(KeyEvent.VK_S):
                down = false;
                break;
            case(KeyEvent.VK_A):
                left = false;
                break;
            case(KeyEvent.VK_D):
                right = false;
                break;
        }
    }
}
