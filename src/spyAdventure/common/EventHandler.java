package spyAdventure.common;

import spyAdventure.view.GamePanel;

import java.awt.*;

public class EventHandler {
    GamePanel gamePanel;
    Rectangle eventHitbox;
    int eventHitboxX, eventHitboxY;
    int timeout = 0;
    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        eventHitbox = new Rectangle(23, 23, 2, 2);
        eventHitboxX = eventHitbox.x;
        eventHitboxY = eventHitbox.y;
    }

    public void checkEvent() {
        if (timeout <= 0){
            timeout += 30;
        } else {
            timeout--;
        }
    }

    public boolean hit(int row, int column) {
        boolean hit = false;

        gamePanel.getPlayer().getHitBox().x = gamePanel.getPlayer().getX() + gamePanel.getPlayer().getHitBox().x;
        gamePanel.getPlayer().getHitBox().y = gamePanel.getPlayer().getY() + gamePanel.getPlayer().getHitBox().y;

        eventHitbox.x = column*Globals.SCALED_TILE_SIZE + eventHitbox.x;
        eventHitbox.y = row*Globals.SCALED_TILE_SIZE + eventHitbox.y;

        if (gamePanel.getPlayer().getHitBox().intersects(eventHitbox)) {
            hit = true;
        }

        gamePanel.getPlayer().getHitBox().x = gamePanel.getPlayer().getHitBoxDefaultX();
        gamePanel.getPlayer().getHitBox().y = gamePanel.getPlayer().getHitBoxDefaultY();

        eventHitbox.x = eventHitboxX;
        eventHitbox.y = eventHitboxY;

        return hit;
    }
}
