package spyAdventure.model.Items;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class CombatKnife extends Item{
    //Constructor
    public CombatKnife() {
        name = "combat_Knife";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Assets/Items/combatKnife.png")));
        } catch (IOException ignored) {}
    }
}
