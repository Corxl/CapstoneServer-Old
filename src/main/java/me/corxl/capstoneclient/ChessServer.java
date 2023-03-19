package me.corxl.capstoneclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.corxl.capstoneclient.chess.board.Board;
import me.corxl.capstoneclient.chess.pieces.Piece;
import me.corxl.capstoneclient.chess.pieces.TeamColor;
import me.corxl.capstoneclient.chess.players.Player;
import me.corxl.capstoneclient.chess.spaces.BoardLocation;
import me.corxl.capstoneclient.chess.spaces.Space;
import me.corxl.capstoneclient.chess.spaces.SpaceColor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChessServer {

    private Socket socket;
    private ServerSocket server;
    private static int port = 4909;
    private ObjectInputStream oin;
    private ObjectOutputStream oout;

    private Space[][] SERVER_SPACES;

    public ChessServer() throws IOException, ClassNotFoundException {
        this.server = new ServerSocket(port);
        while (true) {
            socket = server.accept();
            new Board(null, null);
            new Thread(()->{
                while (true) {
                    try {
                        oin = new ObjectInputStream(socket.getInputStream());
                        Object[] data = (Object[]) oin.readObject();
                        String requestType = (String) data[0];
                        if (requestType.equals("getPossibleMoves")) {
                            requestPossibleMoves(data);
                        } else if (requestType.equals("getDefaultSpaces")) {
                            getDefaultSpaces();
                        } else if (requestType.equals("requestMove")) {
                            requestSetPiece(data);
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("A server error has occurred.");
                        break;
                    }
                }
            }).start();
        }
    }

    private void getDefaultSpaces() throws IOException {
        Space[][] spaces = new Space[8][8];
        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces[i].length; j++) {
                SpaceColor colorIndex;
                if (i % 2 == 0) {
                    if (j % 2 == 0)
                        colorIndex = SpaceColor.LIGHT;
                    else
                        colorIndex = SpaceColor.DARK;
                } else {
                    if (j % 2 == 0)
                        colorIndex = SpaceColor.DARK;
                    else
                        colorIndex = SpaceColor.LIGHT;
                }
                TeamColor c = (i < 2) ? TeamColor.BLACK : TeamColor.WHITE;
                BoardLocation loc = new BoardLocation(i, j);
                Space space = (Board.defaultPieces[i][j] == null) ? new Space(colorIndex, loc) : new Space(colorIndex, loc, new Piece(Board.defaultPieces[i][j], c, loc, false));
                //Space space = new Space(colorIndex, loc);
                spaces[i][j] = space;
                // Adds the space object to the Board at the grid index [i][j].
            }
        }

        oout = new ObjectOutputStream(socket.getOutputStream());
        oout.writeObject(spaces);
    }

    private void requestPossibleMoves(Object[] data) throws IOException {
        oout = new ObjectOutputStream(socket.getOutputStream());
        Piece p = (Piece) data[1];
        boolean targetFriend = (boolean) data[2];
        Space[][] spaces = (Space[][]) data[3];
        oout.writeObject(Piece.getPossibleMoves(p, targetFriend, spaces));
    }

    private void requestSetPiece(Object[] data) throws IOException {
        oout = new ObjectOutputStream(socket.getOutputStream());
        Piece p = new Piece((Piece) data[1]);
        System.out.println("----------");
        System.out.println(p.isPawnMoved());
        BoardLocation newLocation = new BoardLocation((BoardLocation) data[2]);
        BoardLocation oldLocation = new BoardLocation((BoardLocation) data[3]);
        Player player = (Player) data[4];
        // Check if player can do this.

        //

        Board.setPiece(p, newLocation, oldLocation);
        System.out.println(p.isPawnMoved());
        System.out.println("----------");
        oout.writeObject(Board.getSpaces());
    }

    public void closeConnection() throws IOException {
        oin.close();
        oout.close();
        socket.close();
        System.out.println("Server Shutting down...");
        server.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new ChessServer();
    }
}