package spyAdventure.view.UI;

import spyAdventure.common.Globals;
import spyAdventure.view.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Timer extends UIElement {
    public Timer(GamePanel gamePanel) {
        GamePanel = gamePanel;
        Font = Globals.UI_FONT;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(Font);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("Time: " + GamePanel.getTimeSpent()/60 + " Seconds", 25, 25);
    }
}
