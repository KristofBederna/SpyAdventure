package spyAdventure.model;

import spyAdventure.common.Globals;
import spyAdventure.common.Items.Item;
import spyAdventure.common.Items.ItemManager;
import spyAdventure.common.MovementHandler;
import spyAdventure.view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity{
    private MovementHandler MH;
    private GamePanel GamePanel;
    private ArrayList<Item> Inventory;
    private ItemManager IM;


    public Player(int X, int Y, MovementHandler movementHandler, GamePanel gamePanel, ItemManager im) {
        super(X, Y);
        this.MH = movementHandler;
        this.GamePanel = gamePanel;
        direction = "idle";

        hitBox = new Rectangle(8, 16, 32, 32);

        IM = im;
        Inventory = new ArrayList<>();

        getPlayerImage();
    }

    public void update() {
        if (MH.up) {
            direction = "up";
        } else if (MH.down) {
            direction = "down";
        } else if (MH.right) {
            direction = "right";
        } else if (MH.left) {
            direction = "left";
        } else {
            direction = "idle";
        }

        isColliding = false;
        GamePanel.getCM().checkTile(this);

        int itemIndex = GamePanel.getCM().checkObject(this, true);
        if (itemIndex != 999) {
            Inventory.add(IM.getItems()[itemIndex]);
            IM.ghostItem(itemIndex);
        }

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
            } if (isColliding) {
                direction = "idle";
            }
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
            up1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Moving_Up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Moving_Up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Moving_Down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Moving_Down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Moving_Left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Moving_Left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Moving_Right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Moving_Right_2.png"));
            idle = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Idle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDirection() {
        return direction;
    }
    public ArrayList<Item> getInventory() {
        return Inventory;
    }
}
