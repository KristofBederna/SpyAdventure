package spyAdventure.model.Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class HealthKit extends Item{
    private boolean used = false;
    public HealthKit() {
        name = "health_kit";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Assets/Items/healthKit.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
