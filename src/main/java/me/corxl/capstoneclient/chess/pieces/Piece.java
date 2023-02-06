package me.corxl.capstoneclient.chess.pieces;

import javafx.scene.layout.StackPane;
import me.corxl.capstoneclient.chess.board.Board;
import me.corxl.capstoneclient.chess.players.Player;
import me.corxl.capstoneclient.chess.spaces.Space;

import java.util.ArrayList;

public abstract class Piece extends StackPane {
    protected Piece() {};
    abstract Space getSpace();
    abstract ArrayList<Space> getPossibleSpaces();
    abstract boolean isBlack();
    abstract boolean isWhite();
    abstract TeamColor getColor();
    abstract void setTeamColor(TeamColor c);
    abstract Player getPieceOwner();
    abstract void setPieceOwner(Player p);
    abstract PieceEnum getPieceEnum();
    abstract Board getBoard();
}
