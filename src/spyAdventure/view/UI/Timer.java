package spyAdventure.view.UI;

import spyAdventure.common.Globals;
import spyAdventure.common.database.dao.HighScoreDao;
import spyAdventure.view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Timer extends UIElement {
    public Timer(GamePanel gamePanel) {
        GamePanel = gamePanel;
        Font = Globals.UI_FONT;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(Font);
        graphics2D.setColor(Color.WHITE);
        try {
            graphics2D.drawString("Time: " + GamePanel.getTimeSpent() / 60 + " Seconds | Record: " + GamePanel.getHighScoreDao().getTopScore() + " Seconds", 25, 25);
        } catch (SQLException ignored) { }
    }
}
