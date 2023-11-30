package spyAdventure.model.Items;

import spyAdventure.common.Globals;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {
    protected BufferedImage image;
    protected String name;
    protected Boolean collision = false;
    protected int X, Y;
    protected Rectangle hitBox = new Rectangle(0, 0, 48, 48);
    protected boolean visible = true;

    public Boolean getCollision() {
        return collision;
    }

    public void setCollision(Boolean collision) {
        this.collision = collision;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public boolean getVisible() {
        return this.visible;
    }

    public void draw(Graphics2D graphics2D) {
        if (image != null && this.visible) {
        graphics2D.drawImage(image, X, Y, Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE, null);
        }
    }
}
