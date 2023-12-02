import spyAdventure.view.GameFrame;
import spyAdventure.view.GamePanel;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new GameFrame(new GamePanel());
    }
}