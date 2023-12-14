package spyAdventure.common.minigame.util;

import javax.swing.*;

public class GridButton extends JButton {
    private final int row;
    private final int column;

    //Constructor
    public GridButton(int row, int column) {
        this.row = row;
        this.column = column;
    }

    //Getters
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
}
