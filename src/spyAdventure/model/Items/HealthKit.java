package spyAdventure.model.Items;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class HealthKit extends Item{
    //Constructor
    public HealthKit() {
        name = "health_kit";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Assets/Items/healthKit.png")));
        } catch (IOException ignored) {}
    }
}
