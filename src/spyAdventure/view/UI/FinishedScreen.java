package spyAdventure.view.UI;

import spyAdventure.common.Globals;

import java.awt.*;

public class FinishedScreen extends UIElement{
    private boolean isDead;
    public FinishedScreen(spyAdventure.view.GamePanel gamePanel, boolean isDead) {
        GamePanel = gamePanel;
        Font = Globals.UI_FONT;
        this.isDead = isDead;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if (!isDead) {
            graphics2D.setFont(Font);
            graphics2D.setColor(Color.RED);
            graphics2D.drawString("Game Over! Time: " + GamePanel.getTimeSpent()/60 + " Seconds", 284, 288);
        } else {
            graphics2D.setFont(Font);
            graphics2D.setColor(Color.RED);
            graphics2D.drawString("Game Over! You failed!", 284, 288);
        }
    }
}
