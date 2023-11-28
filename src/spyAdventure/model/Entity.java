package spyAdventure.model;

import java.awt.image.BufferedImage;

public class Entity {
    protected int X, Y;
    protected int speed = 4;
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, idle, idleBack;
    protected String direction;
    protected int spriteCounter = 0;
    protected int spiteNum = 1;

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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Entity(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
}
