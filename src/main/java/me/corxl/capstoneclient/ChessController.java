package me.corxl.capstoneclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import me.corxl.capstoneclient.chess.board.Board;
import me.corxl.capstoneclient.chess.players.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class ChessController implements Initializable {

    @FXML
    private VBox mainPane;

    private Board currentBoard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentBoard = new Board(new Player("1"), new Player("2"), this);

        mainPane.getChildren().add(0, currentBoard);
    }

    @FXML
    void resetGame(ActionEvent event) {
        Board.checkKingsSaftey();
//        mainPane.getChildren().remove(currentBoard);
//        currentBoard = new Board(new Player("1"), new Player("2"), this);
//        mainPane.getChildren().add(0, currentBoard);
    }
}