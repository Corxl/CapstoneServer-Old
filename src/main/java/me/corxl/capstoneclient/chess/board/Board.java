package me.corxl.capstoneclient.chess.board;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import me.corxl.capstoneclient.chess.players.Player;
import me.corxl.capstoneclient.chess.spaces.Space;

public class Board extends GridPane implements BoardInterface {

    private Player white, black;
    private final Space[][] spaces = new Space[8][8];

    public Board(Player white, Player black) {
        this.white = white;
        this.black = black;
        // Initialize spaces with alternative colors.
        Color spaceColor[] = {Color.WHITE, Color.LIGHTGRAY};
        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces[i].length; j++) {
                int colorIndex;
                if (i % 2 == 0){
                    if (j % 2 == 0)
                        colorIndex = 0;
                    else
                        colorIndex = 1;
                }
                else {
                    if (j % 2 == 0)
                        colorIndex = 1;
                    else
                        colorIndex = 0;
                }
                Space space = new Space(spaceColor[colorIndex]);
                spaces[i][j] = space;
                // Adds the space object to the Board at the grid index [i][j].
                this.add(space, i, j);
            }
        }
        // Initialize pieces with according color.

    }

    @Override
    public Player getBlackPlayer() {
        return this.black;
    }

    @Override
    public Player getWhitePlayer() {
        return this.white;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public Space[][] getSpaces() {
        return new Space[0][];
    }

    @Override
    public void restart() {

    }

    @Override
    public void updateBoard(Space[][] spaces) {

    }
}
