package spyAdventure.view.UI;

import spyAdventure.common.Globals;

import java.awt.*;

public class FinishedScreen extends UIElement{
    public FinishedScreen(spyAdventure.view.GamePanel gamePanel) {
        GamePanel = gamePanel;
        Font = Globals.UI_FONT;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(Font);
        graphics2D.setColor(Color.RED);
        graphics2D.drawString("Game Over! Time: " + GamePanel.getTimeSpent()/60 + " Seconds", 284, 288);
    }
}
