package spyAdventure.view.UI;

import spyAdventure.common.Globals;

import java.awt.*;

public class AmmoLabel extends UIElement{
    public AmmoLabel(spyAdventure.view.GamePanel gamePanel) {
        GamePanel = gamePanel;
        Font = Globals.UI_FONT;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(Font);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("Ammo left: " + GamePanel.getPlayer().getAmmo(), 625, 555);
    }
}
