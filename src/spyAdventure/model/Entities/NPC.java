package spyAdventure.model.Entities;

import spyAdventure.common.Globals;
import spyAdventure.view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC extends Entity{
    private int actionLockCounter = 0;
    private boolean attacking = false;
    private boolean canDamage = true;
    private int attackTimeout = 0;

    //Constructor
    public NPC(GamePanel gamePanel, int X, int Y) {
        super(gamePanel);
        this.X = X*Globals.SCALED_TILE_SIZE;
        this.Y = Y*Globals.SCALED_TILE_SIZE;
        direction = "idle";
        speed = 1;
        health = 2;
        previousHealth = 2;

        hitBox = new Rectangle(8, 16, 24, 24);

        attackBox = new Rectangle(0, 0, Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE);
        attackDirection = "down";

        setupNPCImage();
    }

    //Setup
    private void setupNPCImage() {
        try {
            up1 = setImage("/Assets/Doctor/Doctor_Moving_Up_1.png");
            up2 = setImage("/Assets/Doctor/Doctor_Moving_Up_2.png");
            down1 = setImage("/Assets/Doctor/Doctor_Moving_Down_1.png");
            down2 = setImage("/Assets/Doctor/Doctor_Moving_Down_2.png");
            left1 = setImage("/Assets/Doctor/Doctor_Moving_Left_1.png");
            left2 = setImage("/Assets/Doctor/Doctor_Moving_Left_2.png");
            right1 = setImage("/Assets/Doctor/Doctor_Moving_Right_1.png");
            right2 = setImage("/Assets/Doctor/Doctor_Moving_Right_2.png");
            idle = setImage("/Assets/Doctor/Doctor_Idle.png");

            attackUp1 = setImage("/Assets/Doctor/Doctor_Attacking_Up_1.png");
            attackUp2 = setImage("/Assets/Doctor/Doctor_Attacking_Up_2.png");
            attackDown1 = setImage("/Assets/Doctor/Doctor_Attacking_Down_1.png");
            attackDown2 = setImage("/Assets/Doctor/Doctor_Attacking_Down_2.png");
            attackLeft1  = setImage("/Assets/Doctor/Doctor_Attacking_Left_1.png");
            attackLeft2 = setImage("/Assets/Doctor/Doctor_Attacking_Left_2.png");
            attackRight1 = setImage("/Assets/Doctor/Doctor_Attacking_Right_1.png");
            attackRight2 = setImage("/Assets/Doctor/Doctor_Attacking_Right_2.png");
        } catch (IOException ignored) { }
    }

    //Update
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

        if (gamePanel.getCM().checkAttack(this,false)){
            if (canDamage) {
                Random random = new Random();
                if (random.nextInt(0, 100) % 75 == 0) {
                    canDamage = false;
                    attacking = true;
                    gamePanel.getPlayer().damage();
                    attackTimeout = 90;
                }
            }
        }
        if (attackTimeout == 60) {
            attacking = false;
        }
        if (attackTimeout > 0) {
            attackTimeout--;
        } else {
            attacking = false;
            canDamage = true;
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
            if (health < previousHealth) {
                if (attacking) {
                    if (attackDirection.equals("right") || attackDirection.equals("left")) {
                        graphics2D.drawImage(image, getX(), getY(), Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE, new Color(255, 0, 0, 50), null);
                    } else {
                        graphics2D.drawImage(image, getX(), getY(), Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE, new Color(255, 0, 0, 50), null);
                    }
                } else {
                    graphics2D.drawImage(image, getX(), getY(), Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE, new Color(255, 0, 0, 50),null);
                }
            } else {
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
    }

    private void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            actionLockCounter = 0;
            Random random = new Random();
            int i = random.nextInt(100)+1;
            if (i < 25) {
                direction = "up";
                attackDirection = "up";
            } else if (i > 25 && i <= 50) {
                direction = "right";
                attackDirection = "right";
            } else if (i > 50 && i <= 75) {
                direction = "left";
                attackDirection = "left";
            } else if (i > 75 && i < 100) {
                direction = "down";
                attackDirection = "down";
            }
            if (i % 10 == 0) {
                direction = "idle";
            }
        }
    }
}
