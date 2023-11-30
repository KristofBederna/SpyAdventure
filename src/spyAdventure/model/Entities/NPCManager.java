package spyAdventure.model.Entities;

import spyAdventure.common.Globals;
import spyAdventure.model.Items.Item;
import spyAdventure.model.Items.Keycard;
import spyAdventure.view.GamePanel;

public class NPCManager {
    private GamePanel gamePanel;
    private NPC[] NPCs = new NPC[10];
    public NPCManager(GamePanel GamePanel) {
        gamePanel = GamePanel;
        setupNPCs();
    }

    public void setupNPCs() {
        NPCs[0] = new NPC(gamePanel, 11, 9);
        NPCs[1] = new NPC(gamePanel, 2, 10);
        NPCs[2] = new NPC(gamePanel, 8, 3);
    }

    public NPC[] getNPCs() {
        return NPCs;
    }
}
