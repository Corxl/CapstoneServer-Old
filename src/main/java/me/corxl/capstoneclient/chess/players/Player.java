package me.corxl.capstoneclient.chess.players;

import me.corxl.capstoneclient.chess.pieces.Piece;

import java.util.ArrayList;

public class Player implements PlayerInterface {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ArrayList<Piece> getRemainingPieces() {
        return null;
    }

    @Override
    public ArrayList<Piece> getDestroyedPieces() {
        return null;
    }
}
