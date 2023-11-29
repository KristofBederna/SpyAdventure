package spyAdventure.common;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    private boolean collision = false;

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
