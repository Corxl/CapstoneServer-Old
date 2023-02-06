package me.corxl.capstoneclient.chess.spaces;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import me.corxl.capstoneclient.chess.pieces.Piece;

public class Space extends StackPane implements SpaceInterface {
    private Color color; // 123


    public Space(Color color) {
        this.color = color;
    }

    @Override
    public Piece getPiece() {
        return null;
    }

    @Override
    public void setPiece(Piece p) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isOccupied() {
        return false;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
