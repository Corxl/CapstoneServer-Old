package me.corxl.capstoneclient.chess.spaces;

import me.corxl.capstoneclient.chess.pieces.Piece;

interface SpaceInterface {
    Piece getPiece();

    void setPiece(Piece p);

    boolean isEmpty();

    boolean isOccupied();

    SpaceColor getColor();

    void clearSpace();
}
