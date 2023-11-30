package spyAdventure.view.UI;

import spyAdventure.common.Globals;
import spyAdventure.view.GamePanel;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {
    GamePanel GamePanel;
    Font Font;
    Timer timer;
    HealthLabel healthLabel;
    AmmoLabel ammoLabel;
    public UI(GamePanel gamePanel) {
        GamePanel = gamePanel;
        Font = Globals.UI_FONT;
        timer = new Timer(gamePanel);
        healthLabel = new HealthLabel(gamePanel);
        ammoLabel = new AmmoLabel(gamePanel);
    }

    public void draw(Graphics2D graphics2D) {
        add(timer);
        timer.draw(graphics2D);
        add(healthLabel);
        healthLabel.draw(graphics2D);
        add(ammoLabel);
        ammoLabel.draw(graphics2D);
    }
}
