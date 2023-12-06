package spyAdventure.model.Items;

import spyAdventure.common.Globals;
import spyAdventure.view.GamePanel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class ItemManager {
    private GamePanel gamePanel;
    private ArrayList<Item> items = new ArrayList<>();
    private int[][] intMap;
    public ItemManager(GamePanel GamePanel) {
        gamePanel = GamePanel;
        intMap = new int[Globals.MAX_TILES_WIDTH][Globals.MAX_TILES_HEIGHT];
        loadMap(gamePanel.getTM().getCurrentMap());
    }

    public void placeItem(Item item, int X, int Y) {
        item.setX(X* Globals.SCALED_TILE_SIZE);
        item.setY(Y*Globals.SCALED_TILE_SIZE); // Places an item in the n+1,m+1 slot
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void ghostItem(Item item) {
        item.setX(-100); item.setY(-100);
        item.setVisible(false);
        if (Objects.equals(item.name, "health_kit")) {
            if (gamePanel.getPlayer().getHealth() < 5) {
                gamePanel.getPlayer().heal();
                gamePanel.getPlayer().getInventory().remove(item);
            }
        }
    }

    public void loadMap(int index) {
        try {
            InputStream IS = getClass().getResourceAsStream("/Assets/ItemMaps/Map"+index+".txt");
            BufferedReader BR = new BufferedReader(new InputStreamReader(IS));

            for (int i = 0; i < Globals.MAX_TILES_HEIGHT; i++) {
                String line = BR.readLine();
                String numbers[] = line.split("\t");
                for (int j = 0; j < Globals.MAX_TILES_WIDTH; j++) {
                    int num = Integer.parseInt(numbers[j]);
                    intMap[j][i] = num;
                }
            }
            BR.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        putItemsInGame();
    }

    public void putItemsInGame() {
        items.clear();
        for (int i = 0; i < Globals.MAX_TILES_HEIGHT; i++) {
            for (int j = 0; j < Globals.MAX_TILES_WIDTH; j++) {
                int num = intMap[j][i];
                switch (num) {
                    case(-1):
                        break;
                    case(0):
                        Item tempBlue = new Keycard("blue");
                        placeItem(tempBlue,j, i);
                        items.add(tempBlue);
                        break;
                    case(1):
                        Item tempRed = new Keycard("red");
                        placeItem(tempRed,j, i);
                        items.add(tempRed);
                        break;
                    case(2):
                        Item tempGreen = new Keycard("green");
                        placeItem(tempGreen,j, i);
                        items.add(tempGreen);
                        break;
                    case(3):
                        Item tempHealKit = new HealthKit();
                        placeItem(tempHealKit,j, i);
                        items.add(tempHealKit);
                        break;
                }
            }
        }
    }
}
