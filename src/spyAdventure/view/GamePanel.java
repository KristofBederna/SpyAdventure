package spyAdventure.view;

import spyAdventure.common.CollisionManager;
import spyAdventure.common.Globals;
import spyAdventure.common.MovementHandler;
import spyAdventure.common.TileManager;
import spyAdventure.model.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    Thread GameThread;
    MovementHandler MH = new MovementHandler();
    Player player = new Player(100, 100, MH, this);
    TileManager TM = new TileManager();
    CollisionManager CM = new CollisionManager(this);

    public GamePanel() {
        setPreferredSize(new Dimension(Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT));
        setBackground(Color.GREEN);
        setDoubleBuffered(true); // smooth rendering
        addKeyListener(MH);
        setFocusable(true);
        startGameThread();
    }

    public void startGameThread() {
        GameThread = new Thread(this);
        GameThread.start();
    }

    @Override
    public void run() {
        double interval = (double)1000000000 /Globals.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (GameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / interval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;

        TM.draw(graphics2D);
        if (player.getDirection() != null) {
            player.draw(graphics2D);
        }

        graphics2D.dispose();
    }

    public CollisionManager getCM() {
        return CM;
    }

    public TileManager getTM() {
        return TM;
    }
}
