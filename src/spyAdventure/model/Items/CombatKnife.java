package spyAdventure.model.Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class CombatKnife extends Item{
    public CombatKnife() {
        name = "combat_Knife";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Assets/Items/combatKnife.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
