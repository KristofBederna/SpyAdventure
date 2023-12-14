package spyAdventure.model.Items;

import spyAdventure.common.Globals;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {
    protected BufferedImage image;
    protected String name;
    protected int X, Y;
    protected Rectangle hitBox = new Rectangle(0, 0, 48, 48);
    protected boolean visible = true;

    //Update
    public void draw(Graphics2D graphics2D) {
        if (image != null && this.visible) {
        graphics2D.drawImage(image, X, Y, Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE, null);
        }
    }

    //Getters
    public Rectangle getHitBox() {
        return hitBox;
    }
    public BufferedImage getImage() {
        return this.image;
    }
    public int getY() {
        return Y;
    }
    public int getX() {
        return X;
    }

    //Setters
    public void setX(int x) {
        X = x;
    }
    public void setY(int y) {
        Y = y;
    }
}
