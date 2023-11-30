package spyAdventure.model.Items;

import spyAdventure.common.Globals;
import spyAdventure.view.GamePanel;

public class ItemManager {
    private GamePanel gamePanel;
    private Item[] items = new Item[10];
    public ItemManager(GamePanel GamePanel) {
        gamePanel = GamePanel;
    }

    public void setupItems() {
        items[0] = new Keycard("blue");
        placeItem(0,13, 10);
        items[1] = new Keycard("red");
        placeItem(1,4, 4);
        items[2] = new Keycard("green");
        placeItem(2,5, 4);
    }

    public void placeItem(int index, int X, int Y) {
        items[index].setX(X* Globals.SCALED_TILE_SIZE);
        items[index].setY(Y*Globals.SCALED_TILE_SIZE); // Places an item in the n+1,m+1 slot
    }

    public Item[] getItems() {
        return items;
    }

    public void ghostItem(int index) {
        items[index].setX(-100); items[index].setY(-100);
        items[index].setVisible(false);
    }
}
