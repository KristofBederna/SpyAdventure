package spyAdventure.model.Entities;

import spyAdventure.common.Globals;
import spyAdventure.common.Tiles.Door;
import spyAdventure.model.Items.*;
import spyAdventure.common.MovementHandler;
import spyAdventure.view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Key;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity {
    private MovementHandler MH;
    private ArrayList<Item> Inventory;
    private ItemManager IM;
    private int activeItem = 0;
    private int attackTimeout = 0;
    private boolean attacking = false;
    private boolean canDamage = true;

    public Player(MovementHandler movementHandler, GamePanel gamePanel, ItemManager im) {
        super(gamePanel);

        health = 3;
        this.MH = movementHandler;
        direction = "idle";

        hitBox = new Rectangle(8, 16, 24, 24);
        setHitBoxDefaultX(hitBox.x);
        setHitBoxDefaultY(hitBox.y);

        attackBox = new Rectangle(0, 0, Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE);
        attackDirection = "down";

        IM = im;
        Inventory = new ArrayList<>();
        Inventory.add(new CombatKnife());
        Inventory.add(new HealthKit());

        getPlayerImage();
    }

    public void update() {
        if (MH.up) {
            direction = "up";
            attackDirection = "up";
        } else if (MH.down) {
            direction = "down";
            attackDirection = "down";
        } else if (MH.right) {
            direction = "right";
            attackDirection = "right";
        } else if (MH.left) {
            direction = "left";
            attackDirection = "left";
        } else {
            direction = "idle";
        }

        isColliding = false;
        gamePanel.getCM().checkTile(this);

        int itemIndex = gamePanel.getCM().checkObject(this, true);
        if (itemIndex != 999) {
            Inventory.add(IM.getItems().get(itemIndex));
            IM.ghostItem(IM.getItems().get(itemIndex));
        }

        int NPCIndex = gamePanel.getCM().checkAttack(this);
        if (NPCIndex != 999) {
            if (attacking && canDamage) {
                canDamage = false;
                gamePanel.getNM().getNPCs()[NPCIndex].damage();
                if (gamePanel.getNM().getNPCs()[NPCIndex].getHealth() == 0) {
                    gamePanel.getNM().getNPCs()[NPCIndex] = null;
                }
            }
        }
        if (attackTimeout > 0) {
            attackTimeout--;
        } else {
            attacking = false;
            canDamage = true;
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
        if (!attacking) {
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
        }
        if (attacking) {
            switch (this.attackDirection) {
                case "up":
                    if (spiteNum == 1) {
                        image = attackUp1;
                    }
                    if (spiteNum == 2) {
                        image = attackUp2;
                    }
                    break;
                case "down":
                    if (spiteNum == 1) {
                        image = attackDown1;
                    }
                    if (spiteNum == 2) {
                        image = attackDown2;
                    }
                    break;
                case "right":
                    if (spiteNum == 1) {
                        image = attackRight1;
                    }
                    if (spiteNum == 2) {
                        image = attackRight2;
                    }
                    break;
                case "left":
                    if (spiteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spiteNum == 2) {
                        image = attackLeft2;
                    }
                    break;
                case "idle":
                    image = attackDown1;
                    break;
            }
        }
        if (image != null) {
            if (attacking) {
                if (attackDirection.equals("right") || attackDirection.equals("left")) {
                    graphics2D.drawImage(image, getX(), getY(), Globals.SCALED_TILE_SIZE+48, Globals.SCALED_TILE_SIZE, null);
                } else {
                    graphics2D.drawImage(image, getX(), getY(), Globals.SCALED_TILE_SIZE+8, Globals.SCALED_TILE_SIZE+16, null);
                }
            } else {
                graphics2D.drawImage(image, getX(), getY(), Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE, null);
            }
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

            attackUp1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Attacking_Up_1.png"));
            attackUp2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Attacking_Up_2.png"));
            attackDown1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Attacking_Down_1.png"));
            attackDown2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Attacking_Down_2.png"));
            attackLeft1  = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Attacking_Left_1.png"));
            attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Attacking_Left_2.png"));
            attackRight1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Attacking_Right_1.png"));
            attackRight2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Spy/Spy_Attacking_Right_2.png"));
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
    public void nullInventory() {
        for (int i = 0; i < Inventory.size(); i++) {
            if (Inventory.get(i).getClass() == Keycard.class) {
                Inventory.set(i, null);
            }
        }
        clearInventory();
    }
    public void clearInventory() {
        ArrayList<Item> temp = new ArrayList<>();
        for (Item item: Inventory) {
            if (item != null) {
                temp.add(item);
            }
        }
        Inventory.clear();
        Inventory.addAll(temp);
    }

    public int getActiveItem() {
        return activeItem;
    }

    public void setActiveItem(int activeItem) {
        this.activeItem = activeItem;
    }

    public void useActiveItem() {
        if (activeItem == 1) {
            attackTimeout = 30;
            attacking = true;
        }
        if (Inventory.get(activeItem-1).getClass() == HealthKit.class) {
            if (health < 5) {
                heal();
            }
            Inventory.set(activeItem-1, null);
        }
        clearInventory();
    }

    public boolean takeCurrentCard(Door door) {
        boolean foundCard = false;
        for (int i = 1; i < Inventory.size(); i++) {
            Keycard card = null;
            if (Inventory.get(i).getClass() == Keycard.class) {
                card = (Keycard)Inventory.get(i);
            }
            if (activeItem == i+1) {
                if (Objects.equals(door.getColor(), card.getColor())) {
                    Inventory.set(i, null);
                    foundCard = true;
                }
            }
            else {
                //TODO: implement warning message
            }
            clearInventory();
        }
        return foundCard;
    }
}
