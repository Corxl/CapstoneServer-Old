package me.corxl.capstoneclient.chess.spaces;

import javafx.scene.paint.Color;
import me.corxl.capstoneclient.chess.pieces.Piece;

interface SpaceInterface {
    Piece getPiece();
    void setPiece(Piece p);
    boolean isEmpty();
    boolean isOccupied();
    Color getColor();
}
