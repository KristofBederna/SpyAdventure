package spyAdventure.view;

import spyAdventure.common.SoundManager;
import spyAdventure.common.database.dao.HighScoreDao;
import spyAdventure.common.database.entity.HighScore;
import spyAdventure.model.CollisionManager;
import spyAdventure.common.Globals;
import spyAdventure.model.Entities.NPCManager;
import spyAdventure.model.Items.ItemManager;
import spyAdventure.common.MovementHandler;
import spyAdventure.model.MinigameManager;
import spyAdventure.model.TileManager;
import spyAdventure.model.Entities.Player;
import spyAdventure.view.UI.FinishedScreen;
import spyAdventure.view.UI.UI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable{
    Thread GameThread;
    MovementHandler MH = new MovementHandler(this);
    TileManager TM = new TileManager();
    CollisionManager CM = new CollisionManager(this);
    ItemManager IM = new ItemManager(this);
    Player player = new Player(MH, this, IM);
    NPCManager NM = new NPCManager(this);
    UI Ui = new UI(this);
    int timeSpent = 0;
    boolean finished = false;
    boolean dead = false;
    MinigameManager MM = new MinigameManager(this);
    String gameState = "go";
    private boolean finishedMinigame = false;

    int[] spawnPointsX = new int[10];
    int[] spawnPointsY = new int[10];

    SoundManager MusicManager = new SoundManager();

    private HighScoreDao highScoreDao;
    private String userName;

    public GamePanel() throws IOException {
        this.highScoreDao = new HighScoreDao();
        userName = JOptionPane.showInputDialog(null, "What is your name?");
        if (userName == null || userName.trim().isEmpty()) {
            userName = "Default User";
        }
        setPreferredSize(new Dimension(Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT));
        setBackground(Color.GREEN);
        setDoubleBuffered(true); // smooth rendering
        addKeyListener(MH);
        setFocusable(true);
        setUpSpawnPoints();
        playMusic(0);
        startGameThread();
    }

    public void startGameThread() {
        GameThread = new Thread(this);
        GameThread.start();
    }

    public void setUpSpawnPoints() {
        setUpASpawnPoint(1, 1, 1);
        setUpASpawnPoint(2, 7, 1);
        setUpASpawnPoint(3, 2, 1);
        setUpASpawnPoint(4, 10, 12);
        setUpASpawnPoint(5, 10, 4);
        setUpASpawnPoint(6, 8, 1);
        setUpASpawnPoint(7, 2, 1);
        setUpASpawnPoint(8, 10, 12);
        setUpASpawnPoint(9, 2, 1);
        setUpASpawnPoint(10, 1, 13);

    }
    public void setUpASpawnPoint(int index, int Y, int X) {
        spawnPointsX[index-1] = X*Globals.SCALED_TILE_SIZE;
        spawnPointsY[index-1] = Y*Globals.SCALED_TILE_SIZE;
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
        if (Objects.equals(gameState, "go")) {
            for (int i = 0; i < NM.getNPCs().size(); i++) {
                if (NM.getNPCs().get(i) != null) {
                    NM.getNPCs().get(i).update();
                }
            }
            player.update();
            if (player.getHealth() <= 0) {
                dead = true;
                GameThread.interrupt();
                GameThread = null;
            }
            if (player.getX() > Globals.MAX_TILES_WIDTH * Globals.SCALED_TILE_SIZE || player.getX() <= 0 || player.getY() > Globals.MAX_TILES_HEIGHT * Globals.SCALED_TILE_SIZE || player.getY() <= 0) {
                if (TM.getCurrentMap() == 10) {
                    finished = true;
                    saveTime();
                    GameThread.interrupt();
                    GameThread = null;
                } else {
                    TM.loadMap(TM.getCurrentMap() + 1);
                    player.nullInventory();
                    TM.setCurrentMap(TM.getCurrentMap() + 1);
                    IM.loadMap(TM.getCurrentMap());
                    NM.loadMap(TM.getCurrentMap());
                    player.setX(spawnPointsX[TM.getCurrentMap() - 1]);
                    player.setY(spawnPointsY[TM.getCurrentMap() - 1]);
                }
            }
        } else {
            GameThread.interrupt();
        }
    }
    public void paintComponent(Graphics g){
            super.paintComponent(g);

            Graphics2D graphics2D = (Graphics2D) g;

            TM.draw(graphics2D);

            for (int i = 0; i < IM.getItems().size(); i++) {
                if (IM.getItems().get(i) != null) {
                    IM.getItems().get(i).draw(graphics2D);
                }
            }

            if (player.getDirection() != null) {
                player.draw(graphics2D);
            }

            for (int i = 0; i < NM.getNPCs().size(); i++) {
                if (NM.getNPCs().get(i) != null) {
                    NM.getNPCs().get(i).draw(graphics2D);
                }
            }

            Ui.draw(graphics2D);

            if (finished) {
                FinishedScreen finishedScreen = new FinishedScreen(this, false);
                Ui.add(finishedScreen);
                finishedScreen.draw(graphics2D);
            }
            if (dead) {
                FinishedScreen finishedScreen = new FinishedScreen(this, true);
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

    public NPCManager getNM() {
        return NM;
    }

    public MinigameManager getMM() {
        return MM;
    }

    public void setGameState(String state) {
        switch (state) {
            case ("pause"):
                gameState = "pause";
                break;
            case("go"):
                gameState = "go";
                break;
        }
    }

    public void setFinishedMinigame(boolean b) {
        finishedMinigame = b;
        if (finishedMinigame) {
            setGameState("go");
        }
    }
    public boolean getFinishedMinigame() {
        return finishedMinigame;
    }
    public void playMusic(int i) {
        MusicManager.setFile(i);
        MusicManager.play();
        MusicManager.loop();
    }

    public void saveTime() {
        HighScore highScore = new HighScore();
        highScore.setName(userName);
        highScore.setScore(timeSpent);
        try {
            highScoreDao.add(highScore);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save high_score to database!", e);
        }
    }
}
