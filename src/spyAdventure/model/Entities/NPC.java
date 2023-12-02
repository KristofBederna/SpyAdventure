package spyAdventure.model.Entities;

import spyAdventure.common.Globals;
import spyAdventure.view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC extends Entity{
    int actionLockCounter = 0;
    public NPC(GamePanel gamePanel, int X, int Y) {
        super(gamePanel);
        this.X = X*Globals.SCALED_TILE_SIZE;
        this.Y = Y*Globals.SCALED_TILE_SIZE;
        direction = "idle";
        speed = 1;
        health = 2;
        previousHealth = 2;

        hitBox = new Rectangle(8, 16, 24, 24);

        getNPCImage();
    }
    public void getNPCImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Doctor/Doctor_Moving_Up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Doctor/Doctor_Moving_Up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Doctor/Doctor_Moving_Down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Doctor/Doctor_Moving_Down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Doctor/Doctor_Moving_Left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Doctor/Doctor_Moving_Left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Doctor/Doctor_Moving_Right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Doctor/Doctor_Moving_Right_2.png"));
            idle = ImageIO.read(getClass().getResourceAsStream("/Assets/Doctor/Doctor_Idle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        setAction();
        isColliding = false;
        gamePanel.getCM().checkTile(this);
        if (!isColliding) {
            switch (direction) {
                case ("up"):
                    setY(getY()-getSpeed());
                    break;
                case ("down"):
                    setY(getY()+getSpeed());
                    break;
                case ("right"):
                    setX(getX()+getSpeed());
                    break;
                case ("left"):
                    setX(getX() - getSpeed());
                    break;
            }
        }
        if (isColliding) {
            direction = "idle";
            actionLockCounter = 119;
            setAction();
        }

        spriteCounter++;
        if (spriteCounter > 10) {
            if (spiteNum == 1) {
                spiteNum = 2;
            } else if (spiteNum == 2) {
                spiteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;
        switch (this.direction) {
            case "up":
                if (spiteNum == 1) {
                    image = up1;
                }
                if (spiteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spiteNum == 1) {
                    image = down1;
                }
                if (spiteNum == 2) {
                    image = down2;
                }
                break;
            case "right":
                if (spiteNum == 1) {
                    image = right1;
                }
                if (spiteNum == 2) {
                    image = right2;
                }
                break;
            case "left":
                if (spiteNum == 1) {
                    image = left1;
                }
                if (spiteNum == 2) {
                    image = left2;
                }
                break;
            case "idle":
                image = idle;
                break;
        }
        if (image != null) {
            if (health < previousHealth) {
                graphics2D.drawImage(image, getX(), getY(), Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE, new Color(255, 0, 0, 50), null);
            } else {
                graphics2D.drawImage(image, getX(), getY(), Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE, null);
            }
        }
    }
    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            actionLockCounter = 0;
            Random random = new Random();
            int i = random.nextInt(100)+1;
            if (i < 25) {
                direction = "up";
            } else if (i > 25 && i <= 50) {
                direction = "right";
            } else if (i > 50 && i <= 75) {
                direction = "left";
            } else if (i > 75 && i < 100) {
                direction = "down";
            }
            if (i % 10 == 0) {
                direction = "idle";
            }
        }
    }
}
