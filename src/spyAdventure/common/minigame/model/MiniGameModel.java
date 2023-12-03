package spyAdventure.common.minigame.model;

import spyAdventure.view.GameFrame;
import spyAdventure.view.GamePanel;

import java.util.Random;

public class MiniGameModel {
    private int[][] fields;
    private int size;

    public void newGame(int size) {
        this.size = size;
        this.fields = new int[size][size];

        fillFields();
    }

    private void fillFields() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j] = random.nextInt(3);
            }
        }
    }

    public int getField(int row, int column) {
        return fields[row][column];
    }

    public void changeFields(int row, int column) {
        changeSingleField(row, column);
        changeSingleField(row-1, column);
        changeSingleField(row+1, column);
        changeSingleField(row, column-1);
        changeSingleField(row, column+1);
    }

    public void changeSingleField(int row, int column) {
        if (!(row < 0 || column < 0 || row >= size || column >= size)) {
            fields[row][column]++;
            fields[row][column] %= 3;
        }
    }

    public boolean isGameOver() {
        int firstColor = fields[0][0];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (fields[i][j] != firstColor) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getSize() {
        return this.size;
    }
}
