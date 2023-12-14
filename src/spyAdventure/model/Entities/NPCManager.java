package spyAdventure.model.Entities;

import spyAdventure.common.Globals;
import spyAdventure.model.Items.Item;
import spyAdventure.model.Items.Keycard;
import spyAdventure.view.GamePanel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NPCManager {
    private GamePanel gamePanel;
    private ArrayList<NPC> NPCs = new ArrayList<>();
    int[][] intMap;
    public NPCManager(GamePanel GamePanel) {
        gamePanel = GamePanel;
        intMap = new int[Globals.MAX_TILES_WIDTH][Globals.MAX_TILES_HEIGHT];
        loadMap(gamePanel.getTM().getCurrentMap());
    }

    public void setupNPCs() {
        NPCs.clear();
        for (int i = 0; i < intMap.length; i++) {
            for (int j = 0; j < intMap[i].length; j++) {
                if (intMap[i][j] == 1) {
                    NPCs.add(new NPC(gamePanel, i, j));
                }
                if (intMap[i][j] == 2) {
                    NPCs.add(new Boss(gamePanel, i, j));
                }
            }
        }
    }

    public ArrayList<NPC> getNPCs() {
        return NPCs;
    }

    public void loadMap(int index) {
        try {
            InputStream IS = getClass().getResourceAsStream("/Assets/NPCMaps/Map"+index+".txt");
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
        setupNPCs();
    }
}
