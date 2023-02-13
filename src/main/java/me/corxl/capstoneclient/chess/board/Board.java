package me.corxl.capstoneclient.chess.board;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import me.corxl.capstoneclient.chess.pieces.Piece;
import me.corxl.capstoneclient.chess.pieces.PieceEnum;
import me.corxl.capstoneclient.chess.pieces.TeamColor;
import me.corxl.capstoneclient.chess.players.Player;
import me.corxl.capstoneclient.chess.spaces.BoardLocation;
import me.corxl.capstoneclient.chess.spaces.Space;
import me.corxl.capstoneclient.chess.spaces.SpaceColor;

public class Board extends GridPane implements BoardInterface {

    private Player white, black;
    private static final Space[][] spaces = new Space[8][8];
    private final PieceEnum[][] defaultPieces = new PieceEnum[][]{
            {PieceEnum.ROOK, PieceEnum.KNIGHT, PieceEnum.BISHOP, PieceEnum.QUEEN, PieceEnum.KING, PieceEnum.BISHOP, PieceEnum.KNIGHT, PieceEnum.ROOK},
            {PieceEnum.PAWN, PieceEnum.PAWN, PieceEnum.PAWN, PieceEnum.PAWN, PieceEnum.PAWN, PieceEnum.PAWN, PieceEnum.PAWN, PieceEnum.PAWN},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {PieceEnum.PAWN, PieceEnum.PAWN, PieceEnum.PAWN, PieceEnum.PAWN, PieceEnum.PAWN, PieceEnum.PAWN, PieceEnum.PAWN, PieceEnum.PAWN},
            {PieceEnum.ROOK, PieceEnum.KNIGHT, PieceEnum.BISHOP, PieceEnum.KING, PieceEnum.QUEEN, PieceEnum.BISHOP, PieceEnum.KNIGHT, PieceEnum.ROOK}
    };

    public Board(Player white, Player black) {
        this.white = white;
        this.black = black;
        this.setAlignment(Pos.CENTER);
        // Initialize spaces with alternative colors.
        Color spaceColor[] = {Color.WHITE, Color.LIGHTGRAY};

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
                TeamColor c = (j < 2) ? TeamColor.BLACK : TeamColor.WHITE;
                BoardLocation loc = new BoardLocation(i, j);
                Space space = (defaultPieces[j][i] == null) ? new Space(colorIndex, loc) : new Space(colorIndex, loc, new Piece(defaultPieces[j][i], c, loc));
                spaces[i][j] = space;
                // Adds the space object to the Board at the grid index [i][j].
                this.add(space, i, j);
            }
        }

        //spaces[0][5].setPiece(new Piece(PieceEnum.KING, TeamColor.BLACK, new BoardLocation(0, 5)));
        // Initialize pieces with according color.

    }

    @Override
    public Player getBlackPlayer() {
        return this.black;
    }

    @Override
    public Player getWhitePlayer() {
        return this.white;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    public static Space[][] getSpaces() {
        return spaces;
    }

    @Override
    public void restart() {

    }

    @Override
    public void updateBoard(Space[][] spaces) {

    }
}
