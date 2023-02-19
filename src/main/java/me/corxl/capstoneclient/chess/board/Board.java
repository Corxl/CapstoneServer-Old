package me.corxl.capstoneclient.chess.board;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import me.corxl.capstoneclient.ChessController;
import me.corxl.capstoneclient.chess.pieces.Piece;
import me.corxl.capstoneclient.chess.pieces.PieceEnum;
import me.corxl.capstoneclient.chess.pieces.TeamColor;
import me.corxl.capstoneclient.chess.players.Player;
import me.corxl.capstoneclient.chess.spaces.BoardLocation;
import me.corxl.capstoneclient.chess.spaces.Space;
import me.corxl.capstoneclient.chess.spaces.SpaceColor;

public class Board extends GridPane implements BoardInterface {

    private Player white, black;
    private ChessController controller;
    public static boolean isPieceSelected;
    private static Space[][] spaces;
    public static boolean[][] selectedSpaces;
    private static TeamColor turn;
    public static Piece selectedPiece;
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

    public Board(Player white, Player black, ChessController controller) {
        isPieceSelected = false;
        spaces = new Space[8][8];
        selectedSpaces = new boolean[8][8];
        turn = TeamColor.WHITE;
        this.controller = controller;
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
                TeamColor c = (i < 2) ? TeamColor.BLACK : TeamColor.WHITE;
                BoardLocation loc = new BoardLocation(i, j);
                //Space space = (defaultPieces[i][j] == null) ? new Space(colorIndex, loc) : new Space(colorIndex, loc, new Piece(defaultPieces[i][j], c, loc));
                Space space = new Space(colorIndex, loc);
                spaces[i][j] = space;
                // Adds the space object to the Board at the grid index [i][j].
                this.add(space, j, i);
            }
        }
        //setPiece(new Piece(PieceEnum.KING, TeamColor.BLACK, new BoardLocation(5, 0)));
        setPiece(new Piece(PieceEnum.KNIGHT, TeamColor.WHITE, new BoardLocation(3, 3)));

    }

    public static TeamColor getTurn() {
        return turn;
    }

    public static void setTurn(TeamColor color) {
        turn = color;
    }

    public static void checkKingsSaftey() {
        boolean[][] whiteMoves = new boolean[8][8];
        for (Space[] sp : getSpaces()) {
            for (Space s : sp) {
                if (!s.isEmpty())
                    if (s.getPiece().isWhite()) {
                        boolean[][] moveable = Piece.getPossibleMoves(s.getPiece());
                        for (int i = 0; i < moveable.length; i++) {
                            for (int j = 0; j < moveable[i].length; j++) {
                                if (moveable[i][j]) {
                                    whiteMoves[i][j] = true;
                                    spaces[i][j].setSelected(true);
                                }
                            }
                        }
                    }
            }
        }
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

    public static void setPiece(Piece p) {
        if (p == null)
            return;
        Space s = Board.getSpaces()[p.getLocation().getX()][p.getLocation().getY()];
        s.setPiece(p);
    }

    public static void setPiece(Piece p, BoardLocation newLocation) {
        if (p == null)
            return;
        Space s = Board.getSpaces()[newLocation.getX()][newLocation.getY()];
        s.setPiece(p);
    }

    public static void setPiece(Piece p, BoardLocation newLocation, BoardLocation oldLocation) {
        if (p == null)
            return;
        Space s = spaces[newLocation.getX()][newLocation.getY()];
        Space old = spaces[oldLocation.getX()][oldLocation.getY()];
        s.setPiece(p);
        old.setPiece(null);
    }

    public static void clearSelections() {
        for (Space[] spArr : spaces) {
            for (Space sp : spArr) {
                sp.setSelected(false);
            }
        }
        selectedSpaces = new boolean[8][8];
    }
}
