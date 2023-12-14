package spyAdventure.common.Tiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Tile {
    protected BufferedImage image;
    protected boolean collision = false;

    //Getters
    public boolean getCollision() {
        return collision;
    }
    public Image getImage() {
        return image;
    }

    //Setters
    public void setImage(String path) throws IOException {
        this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
    }
    public void setCollision(boolean value) {
        this.collision = value;
    }
}
