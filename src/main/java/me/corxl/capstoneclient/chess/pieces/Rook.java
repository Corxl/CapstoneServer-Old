package me.corxl.capstoneclient.chess.pieces;

import me.corxl.capstoneclient.chess.board.Board;
import me.corxl.capstoneclient.chess.players.Player;
import me.corxl.capstoneclient.chess.spaces.Space;

import java.util.ArrayList;

public class Rook extends Piece {
    @Override
    public Space getSpace() {
        return null;
    }

    @Override
    public ArrayList<Space> getPossibleSpaces() {
        return null;
    }

    @Override
    public boolean isBlack() {
        return false;
    }

    @Override
    public boolean isWhite() {
        return false;
    }

    @Override
    public TeamColor getColor() {
        return null;
    }

    @Override
    public void setTeamColor(TeamColor c) {

    }

    @Override
    public Player getPieceOwner() {
        return null;
    }

    @Override
    public void setPieceOwner(Player p) {

    }

    @Override
    public PieceEnum getPieceEnum() {
        return null;
    }

    @Override
    Board getBoard() {
        return null;
    }
}
