package spyAdventure.model;

import spyAdventure.common.Globals;
import spyAdventure.common.Tiles.Door;
import spyAdventure.model.Entities.Entity;
import spyAdventure.model.Entities.Player;
import spyAdventure.view.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class CollisionManager {
    private GamePanel gamePanel;
    public CollisionManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void checkTile(Entity entity) {
        int leftX = (entity.getX() + entity.getHitBox().x);
        int rightX = (entity.getX() + entity.getHitBox().x + entity.getHitBox().width);
        int topY = (entity.getY() + entity.getHitBox().y);
        int bottomY = (entity.getY() + entity.getHitBox().y + entity.getHitBox().height);

        int leftMargin = leftX/ Globals.SCALED_TILE_SIZE;
        int rightMargin = rightX/Globals.SCALED_TILE_SIZE;
        int topMargin = topY/Globals.SCALED_TILE_SIZE;
        int bottomMargin = bottomY/Globals.SCALED_TILE_SIZE;

        int tile1, tile2;

        switch (entity.getDirection()) {
            case ("up"):
                topMargin = (topY - entity.getSpeed())/Globals.SCALED_TILE_SIZE;
                try {
                    tile1 = gamePanel.getTM().getIntMap()[leftMargin][topMargin];
                    tile2 = gamePanel.getTM().getIntMap()[rightMargin][topMargin];

                    if (gamePanel.getTM().getTiles()[tile1].getCollision()  || gamePanel.getTM().getTiles()[tile2].getCollision()) {
                        entity.setColliding(true);
                    }

                    Door door = new Door("");
                    if (gamePanel.getTM().getTiles()[tile1].getClass() == Door.class) {
                        door = (Door)gamePanel.getTM().getTiles()[tile1];
                    } else if (gamePanel.getTM().getTiles()[tile2].getClass() == Door.class) {
                        door = (Door)gamePanel.getTM().getTiles()[tile2];
                    }
                    Player player = null;
                    if (entity.getClass() == Player.class)
                    {
                        player = (Player)entity;
                    }
                    switch (door.getColor()) {
                        case("red"):
                            if (Objects.equals(player.getCurrentColor(), "red")) {
                                door.setCollision(false);
                                door.setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorOpenFront.png")));
                                door.setColor("");
                            }
                            break;
                        case("green"):
                            if (Objects.equals(player.getCurrentColor(), "green")) {
                                door.setCollision(false);
                                door.setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorOpenFront.png")));
                                door.setColor("");
                            }
                            break;
                        case("blue"):
                            if (Objects.equals(player.getCurrentColor(), "blue")) {
                                door.setCollision(false);
                                door.setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorOpenFront.png")));
                                door.setColor("");
                            }
                            break;
                    }
                } catch (Exception ignored) {

                }
                break;
            case ("down"):
                bottomMargin = (bottomY + entity.getSpeed())/Globals.SCALED_TILE_SIZE;
                try {
                    tile1 = gamePanel.getTM().getIntMap()[leftMargin][bottomMargin];
                    tile2 = gamePanel.getTM().getIntMap()[rightMargin][bottomMargin];
                    if (gamePanel.getTM().getTiles()[tile1].getCollision() || gamePanel.getTM().getTiles()[tile2].getCollision()) {
                        entity.setColliding(true);
                    }

                    Door door = new Door("");
                    if (gamePanel.getTM().getTiles()[tile1].getClass() == Door.class) {
                        door = (Door)gamePanel.getTM().getTiles()[tile1];
                    } else if (gamePanel.getTM().getTiles()[tile2].getClass() == Door.class) {
                        door = (Door)gamePanel.getTM().getTiles()[tile2];
                    }
                    Player player = null;
                    if (entity.getClass() == Player.class)
                    {
                        player = (Player)entity;
                    }
                    switch (door.getColor()) {
                        case("red"):
                            if (Objects.equals(player.getCurrentColor(), "red")) {
                                door.setCollision(false);
                                door.setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorOpenFront.png")));
                                door.setColor("");
                            }
                            break;
                        case("green"):
                            if (Objects.equals(player.getCurrentColor(), "green")) {
                                door.setCollision(false);
                                door.setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorOpenFront.png")));
                                door.setColor("");
                            }
                            break;
                        case("blue"):
                            if (Objects.equals(player.getCurrentColor(), "blue")) {
                                door.setCollision(false);
                                door.setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorOpenFront.png")));
                                door.setColor("");
                            }
                            break;
                    }
                } catch (Exception ignored) {

                }

                break;
            case ("left"):
                leftMargin = (leftX - entity.getSpeed())/Globals.SCALED_TILE_SIZE;
                try {
                    tile1 = gamePanel.getTM().getIntMap()[leftMargin][topMargin];
                    tile2 = gamePanel.getTM().getIntMap()[leftMargin][bottomMargin];

                    if (gamePanel.getTM().getTiles()[tile1].getCollision()  || gamePanel.getTM().getTiles()[tile2].getCollision()) {
                        entity.setColliding(true);
                    }

                    Door door = new Door("");
                    if (gamePanel.getTM().getTiles()[tile1].getClass() == Door.class) {
                        door = (Door)gamePanel.getTM().getTiles()[tile1];
                    } else if (gamePanel.getTM().getTiles()[tile2].getClass() == Door.class) {
                        door = (Door)gamePanel.getTM().getTiles()[tile2];
                    }
                    Player player = null;
                    if (entity.getClass() == Player.class)
                    {
                        player = (Player)entity;
                    }
                    switch (door.getColor()) {
                        case("red"):
                            if (Objects.equals(player.getCurrentColor(), "red")) {
                                door.setCollision(false);
                                door.setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorOpenSide.png")));
                                door.setColor("");
                            }
                            break;
                        case("green"):
                            if (Objects.equals(player.getCurrentColor(), "green")) {
                                door.setCollision(false);
                                door.setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorOpenSide.png")));
                                door.setColor("");
                            }
                            break;
                        case("blue"):
                            if (Objects.equals(player.getCurrentColor(), "blue")) {
                                door.setCollision(false);
                                door.setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorOpenSide.png")));
                                door.setColor("");
                            }
                            break;
                    }
                } catch (Exception ignored) {

                }
                break;
            case ("right"):
                rightMargin = (rightX + entity.getSpeed())/Globals.SCALED_TILE_SIZE;
                try {
                    tile1 = gamePanel.getTM().getIntMap()[rightMargin][topMargin];
                    tile2 = gamePanel.getTM().getIntMap()[rightMargin][bottomMargin];

                    if (gamePanel.getTM().getTiles()[tile1].getCollision()  || gamePanel.getTM().getTiles()[tile2].getCollision()) {
                        entity.setColliding(true);
                    }

                    Door door = new Door("");
                    if (gamePanel.getTM().getTiles()[tile1].getClass() == Door.class) {
                        door = (Door)gamePanel.getTM().getTiles()[tile1];
                    } else if (gamePanel.getTM().getTiles()[tile2].getClass() == Door.class) {
                        door = (Door)gamePanel.getTM().getTiles()[tile2];
                    }
                    Player player = null;
                    if (entity.getClass() == Player.class)
                    {
                        player = (Player)entity;
                    }
                    switch (door.getColor()) {
                        case("red"):
                            if (Objects.equals(player.getCurrentColor(), "red")) {
                                door.setCollision(false);
                                door.setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorOpenSide.png")));
                                door.setColor("");
                            }
                            break;
                        case("green"):
                            if (Objects.equals(player.getCurrentColor(), "green")) {
                                door.setCollision(false);
                                door.setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorOpenSide.png")));
                                door.setColor("");
                            }
                            break;
                        case("blue"):
                            if (Objects.equals(player.getCurrentColor(), "blue")) {
                                door.setCollision(false);
                                door.setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorOpenSide.png")));
                                door.setColor("");
                            }
                            break;
                    }
                } catch (Exception ignored) {

                }

                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        for (int i = 0; i < gamePanel.getIM().getItems().length; i++) {
            if (gamePanel.getIM().getItems()[i] != null) {
                int defaultX = entity.getHitBox().x;
                int defaultY = entity.getHitBox().y;
                entity.getHitBox().x = entity.getX() + entity.getHitBox().x;
                entity.getHitBox().y = entity.getY() + entity.getHitBox().y;

                int defaultItemX = gamePanel.getIM().getItems()[i].getHitBox().x;
                int defaultItemY = gamePanel.getIM().getItems()[i].getHitBox().y;
                gamePanel.getIM().getItems()[i].getHitBox().x = gamePanel.getIM().getItems()[i].getX() + gamePanel.getIM().getItems()[i].getHitBox().x;
                gamePanel.getIM().getItems()[i].getHitBox().y = gamePanel.getIM().getItems()[i].getY() + gamePanel.getIM().getItems()[i].getHitBox().y;
                switch (entity.getDirection()) {
                    case ("up"):
                        entity.getHitBox().y -= entity.getSpeed();
                        if (entity.getHitBox().intersects(gamePanel.getIM().getItems()[i].getHitBox())) {
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case ("down"):
                        entity.getHitBox().y += entity.getSpeed();
                        if (entity.getHitBox().intersects(gamePanel.getIM().getItems()[i].getHitBox())) {
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case ("left"):
                        entity.getHitBox().x -= entity.getSpeed();
                        if (entity.getHitBox().intersects(gamePanel.getIM().getItems()[i].getHitBox())) {
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case ("right"):
                        entity.getHitBox().x += entity.getSpeed();
                        if (entity.getHitBox().intersects(gamePanel.getIM().getItems()[i].getHitBox())) {
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.getHitBox().x = defaultX;
                entity.getHitBox().y = defaultY;
                gamePanel.getIM().getItems()[i].getHitBox().x = defaultItemX;
                gamePanel.getIM().getItems()[i].getHitBox().y = defaultItemY;
            }
        }
        return index;
    }
    public int checkAttack(Entity entity) {
        int index = 999;
        for (int i = 0; i < gamePanel.getNM().getNPCs().length; i++) {
            if (gamePanel.getNM().getNPCs()[i] != null) {
                int defaultX = entity.getAttackBox().x;
                int defaultY = entity.getAttackBox().y;
                entity.getAttackBox().x = entity.getX() + entity.getAttackBox().x;
                entity.getAttackBox().y = entity.getY() + entity.getAttackBox().y;

                int defaultNPCX = gamePanel.getNM().getNPCs()[i].getHitBox().x;
                int defaultNPCY = gamePanel.getNM().getNPCs()[i].getHitBox().y;
                gamePanel.getNM().getNPCs()[i].getHitBox().x = gamePanel.getNM().getNPCs()[i].getX() + gamePanel.getNM().getNPCs()[i].getHitBox().x;
                gamePanel.getNM().getNPCs()[i].getHitBox().y = gamePanel.getNM().getNPCs()[i].getY() + gamePanel.getNM().getNPCs()[i].getHitBox().y;
                switch (entity.getAttackDirection()) {
                    case ("up"):
                        entity.getAttackBox().y -= entity.getSpeed();
                        if (entity.getAttackBox().intersects(gamePanel.getNM().getNPCs()[i].getHitBox())) {
                            index = i;
                        }
                        break;
                    case ("down"):
                        entity.getAttackBox().y += entity.getSpeed();
                        if (entity.getAttackBox().intersects(gamePanel.getNM().getNPCs()[i].getHitBox())) {
                            index = i;
                        }
                        break;
                    case ("left"):
                        entity.getAttackBox().x -= entity.getSpeed();
                        if (entity.getAttackBox().intersects(gamePanel.getNM().getNPCs()[i].getHitBox())) {
                            index = i;
                        }
                        break;
                    case ("right"):
                        entity.getAttackBox().x += entity.getSpeed();
                        if (entity.getAttackBox().intersects(gamePanel.getNM().getNPCs()[i].getHitBox())) {
                            index = i;
                        }
                        break;
                }
                entity.getAttackBox().x = defaultX;
                entity.getAttackBox().y = defaultY;
                gamePanel.getNM().getNPCs()[i].getHitBox().x = defaultNPCX;
                gamePanel.getNM().getNPCs()[i].getHitBox().y = defaultNPCY;
            }
        }
        return index;
    }
}
