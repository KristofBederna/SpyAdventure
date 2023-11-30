package spyAdventure.common;

import spyAdventure.model.Entity;
import spyAdventure.view.GamePanel;

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

        int leftMargin = leftX/Globals.SCALED_TILE_SIZE;
        int rightMargin = rightX/Globals.SCALED_TILE_SIZE;
        int topMargin = topY/Globals.SCALED_TILE_SIZE;
        int bottomMargin = bottomY/Globals.SCALED_TILE_SIZE;

        int tile1, tile2;

        switch (entity.getDirection()) {
            case ("up"):
                topMargin = (topY - entity.getSpeed())/Globals.SCALED_TILE_SIZE;
                tile1 = gamePanel.getTM().mapNums[leftMargin][topMargin];
                tile2 = gamePanel.getTM().mapNums[rightMargin][topMargin];

                if (gamePanel.getTM().tiles[tile1].collision || gamePanel.getTM().tiles[tile2].collision) {
                    entity.setColliding(true);
                }
                break;
            case ("down"):
                bottomMargin = (bottomY + entity.getSpeed())/Globals.SCALED_TILE_SIZE;
                tile1 = gamePanel.getTM().mapNums[leftMargin][bottomMargin];
                tile2 = gamePanel.getTM().mapNums[rightMargin][bottomMargin];

                if (gamePanel.getTM().tiles[tile1].collision || gamePanel.getTM().tiles[tile2].collision) {
                    entity.setColliding(true);
                }
                break;
            case ("left"):
                leftMargin = (leftX - entity.getSpeed())/Globals.SCALED_TILE_SIZE;
                tile1 = gamePanel.getTM().mapNums[leftMargin][topMargin];
                tile2 = gamePanel.getTM().mapNums[leftMargin][bottomMargin];

                if (gamePanel.getTM().tiles[tile1].collision || gamePanel.getTM().tiles[tile2].collision) {
                    entity.setColliding(true);
                }
                break;
            case ("right"):
                rightMargin = (rightX + entity.getSpeed())/Globals.SCALED_TILE_SIZE;
                tile1 = gamePanel.getTM().mapNums[rightMargin][topMargin];
                tile2 = gamePanel.getTM().mapNums[rightMargin][bottomMargin];

                if (gamePanel.getTM().tiles[tile1].collision || gamePanel.getTM().tiles[tile2].collision) {
                    entity.setColliding(true);
                }
                break;
        }
    }
}
