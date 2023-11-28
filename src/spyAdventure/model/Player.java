package spyAdventure.model;

import spyAdventure.common.Globals;
import spyAdventure.common.MovementHandler;
import spyAdventure.view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    private MovementHandler MH;
    private GamePanel GamePanel;


    public Player(int X, int Y, MovementHandler movementHandler, GamePanel gamePanel) {
        super(X, Y);
        this.MH = movementHandler;
        this.GamePanel = gamePanel;
        direction = "idle";
        getPlayerImage();
    }

    public void update() {
        if (MH.up) {
            setY(getY()-getSpeed());
            direction = "up";
        } else if (MH.down) {
            setY(getY()+getSpeed());
            direction = "down";
        } else if (MH.right) {
            setX(getX()+getSpeed());
            direction = "right";
        } else if (MH.left) {
            setX(getX() - getSpeed());
            direction = "left";
        } else {
            direction = "idle";
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
            graphics2D.drawImage(image, getX(), getY(), Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE, null);
        }
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy_Moving_Up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy_Moving_Up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy_Moving_Down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy_Moving_Down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy_Moving_Left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy_Moving_Left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy_Moving_Right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy_Moving_Right_2.png"));
            idle = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy_Idle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDirection() {
        return direction;
    }
}
