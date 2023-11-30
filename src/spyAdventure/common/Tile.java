package spyAdventure.common;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    protected BufferedImage image;
    protected boolean collision = false;

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
    public void setCollision(boolean value) {
        this.collision = value;
    }
}
