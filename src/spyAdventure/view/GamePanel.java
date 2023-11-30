package spyAdventure.view;

import spyAdventure.model.CollisionManager;
import spyAdventure.common.Globals;
import spyAdventure.model.Entities.NPC;
import spyAdventure.model.Entities.NPCManager;
import spyAdventure.model.Items.ItemManager;
import spyAdventure.common.MovementHandler;
import spyAdventure.model.TileManager;
import spyAdventure.model.Entities.Player;
import spyAdventure.view.UI.FinishedScreen;
import spyAdventure.view.UI.UI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    Thread GameThread;
    MovementHandler MH = new MovementHandler();
    TileManager TM = new TileManager();
    CollisionManager CM = new CollisionManager(this);
    ItemManager IM = new ItemManager(this);
    Player player = new Player(MH, this, IM);
    NPCManager NM = new NPCManager(this);
    UI Ui = new UI(this);
    long timeSpent = 0;
    Boolean finished = false;

    public GamePanel() {
        setPreferredSize(new Dimension(Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT));
        setBackground(Color.GREEN);
        setDoubleBuffered(true); // smooth rendering
        addKeyListener(MH);
        setFocusable(true);
        IM.setupItems();
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
        do {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / interval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                timeSpent++;
            }
        } while(GameThread != null);
    }

    public void update() {
        for (int i = 0; i < NM.getNPCs().length; i++) {
            if (NM.getNPCs()[i] != null) {
                NM.getNPCs()[i].update();
            }
        }
        player.update();
        if (player.getX() > Globals.MAX_TILES_WIDTH * Globals.SCALED_TILE_SIZE || player.getX() <= 0 || player.getY() > Globals.MAX_TILES_HEIGHT * Globals.SCALED_TILE_SIZE || player.getY() <= 0) {
            if (TM.getCurrentMap() == 10) {
                finished = true;
                GameThread.interrupt();
                GameThread = null;
            }
            else {
                TM.loadMap(TM.getCurrentMap() + 1);
                TM.setCurrentMap(TM.getCurrentMap() + 1);
                player.setX(100); player.setY(100);
            }
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;

        TM.draw(graphics2D);

        for (int i = 0; i < IM.getItems().length; i++) {
            if (IM.getItems()[i] != null) {
                IM.getItems()[i].draw(graphics2D);
            }
        }

        if (player.getDirection() != null) {
            player.draw(graphics2D);
        }

        for (int i = 0; i < NM.getNPCs().length; i++) {
            if (NM.getNPCs()[i] != null) {
                NM.getNPCs()[i].draw(graphics2D);
            }
        }

        Ui.draw(graphics2D);

        if (finished) {
            FinishedScreen finishedScreen = new FinishedScreen(this);
            Ui.add(finishedScreen);
            finishedScreen.draw(graphics2D);
        }

        graphics2D.dispose();
    }

    public CollisionManager getCM() {
        return CM;
    }

    public TileManager getTM() {
        return TM;
    }
    public ItemManager getIM() {return IM;}

    public long getTimeSpent() {
        return timeSpent;
    }
    public Player getPlayer() {
        return player;
    }
    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public NPCManager getNM() {
        return NM;
    }
}
