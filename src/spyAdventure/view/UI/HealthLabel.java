package spyAdventure.view.UI;

import spyAdventure.common.Globals;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HealthLabel extends UIElement {
    private BufferedImage heartImage = ImageIO.read(getClass().getResourceAsStream("/Assets/Common/hearth.png"));
    public HealthLabel(spyAdventure.view.GamePanel gamePanel) throws IOException {
        GamePanel = gamePanel;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        for (int i = 0; i < GamePanel.getPlayer().getHealth(); i++) {
            graphics2D.drawImage(heartImage, 625+i*25, 15, null);
        }
    }
}
