package spyAdventure.view.UI;

import spyAdventure.common.Globals;
import java.awt.*;

public class HealthLabel extends UIElement {
    public HealthLabel(spyAdventure.view.GamePanel gamePanel) {
        GamePanel = gamePanel;
        Font = Globals.UI_FONT;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(Font);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("Lives left: " + GamePanel.getPlayer().getHealth(), 625, 25);
    }
}
