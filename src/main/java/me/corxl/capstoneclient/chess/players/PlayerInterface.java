package me.corxl.capstoneclient.chess.players;

import me.corxl.capstoneclient.chess.pieces.Piece;

import java.util.ArrayList;

public interface PlayerInterface {
    String getName();
    ArrayList<Piece> getRemainingPieces();
    ArrayList<Piece> getDestroyedPieces();
}
