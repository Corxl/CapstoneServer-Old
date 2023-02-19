package me.corxl.capstoneclient.chess.pieces;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import me.corxl.capstoneclient.chess.board.Board;
import me.corxl.capstoneclient.chess.spaces.BoardLocation;
import me.corxl.capstoneclient.chess.spaces.Space;

import java.util.Arrays;

public class Piece extends VBox {
    private TeamColor color;
    private BoardLocation location;
    private PieceEnum pieceType;
    private boolean pawnMoved = false;


    public Piece(PieceEnum pieceType, TeamColor color, BoardLocation location) {
        this.color = color;
        this.location = location;
        this.pieceType = pieceType;
        this.setAlignment(Pos.CENTER);

        String p = this.isWhite()
                ?
                System.getProperty("user.dir") + "\\src\\main\\resources\\me\\corxl\\capstoneclient\\" + this.pieceType.fileLocation[0]
                :
                System.getProperty("user.dir") + "\\src\\main\\resources\\me\\corxl\\capstoneclient\\" + this.pieceType.fileLocation[1];
        Image i = new Image(p);
        ImageView v = new ImageView(i);
        v.setFitWidth(50);
        v.setFitHeight(50);
        this.getChildren().add(v);
        this.setOnMouseClicked((e) -> {
            if (this.color != Board.getTurn()) {
                return;
            }
            Board.clearSelections();
            System.out.println("----------");
            boolean[][] possileMoves = getPossibleMoves(this);
            Board.selectedSpaces = possileMoves;
            Space[][] spaces = Board.getSpaces();
            for (int j = 0; j < possileMoves.length; j++) {
                for (int k = 0; k < possileMoves[j].length; k++) {
                    if (possileMoves[j][k]) {
                        spaces[j][k].setSelected(true);
                    }
                }
                System.out.println(Arrays.toString(possileMoves[j]));
            }
            Board.selectedPiece = this;
        });
    }

    ;

    public boolean isBlack() {
        return this.color == TeamColor.BLACK;
    }

    public boolean isWhite() {
        return this.color == TeamColor.WHITE;
    }

    public TeamColor getColor() {
        return this.color;
    }

    public PieceEnum getPieceType() {
        return this.pieceType;
    }

    public void setLocation(BoardLocation location) {
        if (this.getPieceType() == PieceEnum.PAWN)
            if (!this.pawnMoved)
                this.pawnMoved = true;
        this.location = location;
    }

    public BoardLocation getLocation() {
        return this.location;
    }

    public static boolean[][] getPossibleMoves(Piece piece) {
        if (piece.getPieceType() == PieceEnum.ROOK)
            System.out.println("HORRAY!!");
        Space[][] spaces = Board.getSpaces();
        BoardLocation location = piece.getLocation();
        boolean[][] moveSpaces = new boolean[8][8];

        switch (piece.pieceType) {
            case PAWN:
                pawnMoves(moveSpaces, location, piece, spaces);
                break;
            case ROOK:
                rookMoves(moveSpaces, piece, spaces);
                break;
            case KING:
                kingMoves(moveSpaces, location, piece, spaces);
                break;
            case KNIGHT:
                knightMoves(moveSpaces, location, piece, spaces);
                break;
            case BISHOP:
                bishopMoves(moveSpaces, location, piece, spaces);
                break;
            case QUEEN:
                queenMoves(moveSpaces, location, piece, spaces);
                break;
        }
        return moveSpaces;
    }

    private static void bishopMoves(boolean[][] moveSpaces, BoardLocation location, Piece piece, Space[][] spaces) {
        int[][] relativeSpaces = {{1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
        for (int i = 0; i < relativeSpaces.length; i++) {
            int xMod = relativeSpaces[i][0];
            int yMod = relativeSpaces[i][1];
            int targetX = location.getX() + xMod;
            int targetY = location.getY() + yMod;
            while (targetX >= 0 && targetX < 8 && targetY >= 0 && targetY < 8) {
                if (spaces[targetX][targetY].isEmpty())
                    moveSpaces[targetX][targetY] = true;
                else {
                    if (spaces[targetX][targetY].getPiece().getColor() != piece.getColor())
                        moveSpaces[targetX][targetY] = true;
                    break;
                }
                targetX += xMod;
                targetY += yMod;
            }
        }
    }

    private static void queenMoves(boolean[][] moveSpaces, BoardLocation location, Piece piece, Space[][] spaces) {
        rookMoves(moveSpaces, piece, spaces);
        bishopMoves(moveSpaces, location, piece, spaces);
    }

    private static void knightMoves(boolean[][] moveSpaces, BoardLocation location, Piece piece, Space[][] spaces) {

        int[][] targetModifiers = {{1, 2}, {2, 1}, {-1, 2}, {1, -2}, {2, -1}, {-2, 1}};
        for (int i = 0; i < targetModifiers.length; i++) {
            int x = location.getX();
            int y = location.getY();
            int targetX = x - targetModifiers[i][0];
            int targetY = y - targetModifiers[i][1];
            if (targetX >= 0 && targetX < 8 && targetY >= 0 && targetY < 8) {
                if (spaces[targetX][targetY].isEmpty())
                    moveSpaces[targetX][targetY] = true;
                else {
                    if (spaces[targetX][targetY].getPiece().getColor() != piece.getColor())
                        moveSpaces[targetX][targetY] = true;
                }
            }
        }


    }

    private static void kingMoves(boolean[][] moveSpaces, BoardLocation location, Piece piece, Space[][] spaces) {
        int[][] relativeSpaces = {{1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
        for (int i = 0; i < relativeSpaces.length; i++) {
            int xMod = relativeSpaces[i][0];
            int yMod = relativeSpaces[i][1];
            if (location.getX() + xMod >= 0 && location.getX() + xMod < 8) {
                if (spaces[location.getX() + xMod][location.getY()].isEmpty())
                    moveSpaces[location.getX() + xMod][location.getY()] = true;
                else {
                    if (spaces[location.getX() + xMod][location.getY()].getPiece().getColor() != piece.getColor())
                        moveSpaces[location.getX() + xMod][location.getY()] = true;
                }
                if (spaces[location.getX() + xMod][location.getY() + 1].isEmpty())
                    moveSpaces[location.getX() + xMod][location.getY() + 1] = true;
                else {
                    if (spaces[location.getX() + xMod][location.getY() + 1].getPiece().getColor() != piece.getColor())
                        moveSpaces[location.getX() + xMod][location.getY() + 1] = true;
                }
                if (location.getY() - 1 >= 0 && location.getY() - 1 < 8) {
                    if (spaces[location.getX() + xMod][location.getY() - 1].isEmpty())
                        moveSpaces[location.getX() + xMod][location.getY() - 1] = true;
                    else {
                        if (spaces[location.getX() + xMod][location.getY() - 1].getPiece().getColor() != piece.getColor())
                            moveSpaces[location.getX() + xMod][location.getY() - 1] = true;
                    }
                }
            }
            if (!(location.getY() + yMod < 0 || location.getY() + yMod >= 8)) {
                if (spaces[location.getX()][location.getY() + yMod].isEmpty())
                    moveSpaces[location.getX()][location.getY() + yMod] = true;
                else {
                    if (spaces[location.getX()][location.getY() + yMod].getPiece().getColor() != piece.getColor())
                        moveSpaces[location.getX()][location.getY() + yMod] = true;
                }
            }
        }
    }

    private static void pawnMoves(boolean[][] moveSpaces, BoardLocation location, Piece piece, Space[][] spaces) {
        int modifier = piece.isWhite() ? -1 : 1;
        int pawnMovedModifier = !piece.pawnMoved ? 2 : 1;
        System.out.println(location.getX() + ", " + location.getY() + " :: " + pawnMovedModifier);
        if (!(location.getY() - 1 < 0)) {
            Space left = spaces[location.getX() + (modifier)][location.getY() - 1];
            if (!left.isEmpty())
                if (left.getPiece().getColor() != piece.getColor())
                    moveSpaces[location.getX() + (modifier)][location.getY() - 1] = true;
        }
        if (!(location.getY() + 1 >= spaces[location.getX() + (modifier)].length)) {
            Space right = spaces[location.getX() + (modifier)][location.getY() + 1];
            if (!right.isEmpty())
                if (right.getPiece().getColor() != piece.getColor())
                    moveSpaces[location.getX() + (modifier)][location.getY() + 1] = true;
        }
        for (int i = 1; i <= pawnMovedModifier; i++) {
            Space s = spaces[location.getX() + (modifier * i)][location.getY()];
            if (s.isEmpty()) {
                moveSpaces[location.getX() + (modifier * i)][location.getY()] = true;
            } else {
                break;
            }
        }
    }

    private static void rookMoves(boolean[][] moveSpaces, Piece piece, Space[][] spaces) {
        for (int i = piece.getLocation().getX() + 1; i < spaces.length; i++) {
            if (spaces[i][piece.getLocation().getY()].isEmpty())
                moveSpaces[i][piece.getLocation().getY()] = true;
            else {
                if (spaces[i][piece.getLocation().getY()].getPiece().getColor() != piece.getColor()) {
                    moveSpaces[i][piece.getLocation().getY()] = true;
                }
                break;
            }
        }
        for (int i = piece.getLocation().getX() - 1; i >= 0; i--) {
            if (spaces[i][piece.getLocation().getY()].isEmpty())
                moveSpaces[i][piece.getLocation().getY()] = true;
            else {
                if (spaces[i][piece.getLocation().getY()].getPiece().getColor() != piece.getColor()) {
                    moveSpaces[i][piece.getLocation().getY()] = true;
                }
                break;
            }
        }
        for (int i = piece.getLocation().getY() - 1; i >= 0; i--) {
            if (spaces[piece.getLocation().getX()][i].isEmpty())
                moveSpaces[piece.getLocation().getX()][i] = true;
            else {
                if (spaces[piece.getLocation().getX()][i].getPiece().getColor() != piece.getColor()) {
                    moveSpaces[piece.getLocation().getX()][i] = true;
                }
                break;
            }
        }
        for (int i = piece.getLocation().getY() + 1; i < 8; i++) {
            if (spaces[piece.getLocation().getX()][i].isEmpty())
                moveSpaces[piece.getLocation().getX()][i] = true;
            else {
                if (spaces[piece.getLocation().getX()][i].getPiece().getColor() != piece.getColor()) {
                    moveSpaces[piece.getLocation().getX()][i] = true;
                }
                break;
            }
        }
    }
}
