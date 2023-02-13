package me.corxl.capstoneclient;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainPane.getChildren().add(new Board(new Player("1"), new Player("2")));
    }
}