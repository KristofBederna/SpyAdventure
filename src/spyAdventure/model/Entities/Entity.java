package spyAdventure.model.Entities;

import spyAdventure.common.Globals;
import spyAdventure.view.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    protected int X, Y;
    protected int speed = 4;
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, idle;
    protected String direction;
    protected int spriteCounter = 0;
    protected int spiteNum = 1;
    protected Rectangle hitBox;
    protected boolean isColliding;
    protected int health;
    protected int ammo;
    protected GamePanel gamePanel;

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

    public Entity(GamePanel gamePanel) {
        this.X = Globals.SCALED_TILE_SIZE;
        this.Y = Globals.SCALED_TILE_SIZE;
        this.gamePanel = gamePanel;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public String getDirection() {
        return direction;
    }

    public void setColliding(boolean colliding) {
        isColliding = colliding;
    }
    public int getHealth() {
        return health;
    }

    public int getAmmo() {
        return ammo;
    }
}
