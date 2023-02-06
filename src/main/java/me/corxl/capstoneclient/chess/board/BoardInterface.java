package me.corxl.capstoneclient.chess.board;
import me.corxl.capstoneclient.chess.players.Player;
import me.corxl.capstoneclient.chess.spaces.Space;

public interface BoardInterface {
    Player getBlackPlayer();
    Player getWhitePlayer();
    boolean isGameOver();
    Space[][] getSpaces();
    void restart();
    void updateBoard(Space[][] spaces);
}
