package spyAdventure.view.UI;


import spyAdventure.common.Globals;
import spyAdventure.view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class InventoryLabel extends UIElement {
    GamePanel gamePanel;
    BufferedImage InventoryBoxImage = ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Floor/DefaultTile.png"));
    public InventoryLabel(GamePanel gamePanel) throws IOException {
        this.gamePanel = gamePanel;
    }
    @Override
    public void draw(Graphics2D graphics2D) {
        for (int i = 0; i < gamePanel.getPlayer().getInventory().size(); i++) {
            if (gamePanel.getPlayer().getInventory().get(i) != null)
            {
                if (gamePanel.getPlayer().getActiveItem() == i+1) {
                    graphics2D.drawImage(InventoryBoxImage, Globals.SCALED_TILE_SIZE*6+i*Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE*11,Globals.SCALED_TILE_SIZE,Globals.SCALED_TILE_SIZE,new Color(246, 255, 0, 102),null);
                    graphics2D.drawImage(gamePanel.getPlayer().getInventory().get(i).getImage(), Globals.SCALED_TILE_SIZE*6+i*Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE*11,Globals.SCALED_TILE_SIZE,Globals.SCALED_TILE_SIZE,null);

                } else {
                    graphics2D.drawImage(InventoryBoxImage, Globals.SCALED_TILE_SIZE*6+i*Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE*11,Globals.SCALED_TILE_SIZE,Globals.SCALED_TILE_SIZE,new Color(0, 0, 0,50),null);
                    graphics2D.drawImage(gamePanel.getPlayer().getInventory().get(i).getImage(), Globals.SCALED_TILE_SIZE*6+i*Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE*11,Globals.SCALED_TILE_SIZE,Globals.SCALED_TILE_SIZE,null);
                }
            }
        }
    }
}
