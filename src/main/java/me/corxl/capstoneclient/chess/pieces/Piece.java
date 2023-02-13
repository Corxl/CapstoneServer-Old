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
            System.out.println("----------");
            for (boolean[] b : getPossibleMoves(this)) {
                System.out.println(Arrays.toString(b));
            }
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

    public boolean[][] getPossibleMoves(Piece piece) {

        Space[][] spaces = Board.getSpaces();
        BoardLocation location = this.getLocation();
        boolean[][] moveSpaces = new boolean[8][8];
        switch (piece.pieceType) {
            case PAWN:
                int modifier = this.getColor() == TeamColor.WHITE ? -1 : 1;
                int X = location.getY();
                int Y = location.getX();
                if (X + (modifier) >= 8 || location.getX() + (modifier) < 0)
                    return moveSpaces;
                if (spaces[X + modifier][Y].isEmpty())
                    moveSpaces[X + modifier][Y] = true;
                if (!piece.pawnMoved)
                    if (X + (2 * modifier) < 8 && X + (2 * modifier) > -1)
                        if (spaces[X + (2 * modifier)][Y].isEmpty())
                            moveSpaces[X + (2 * modifier)][Y] = true;
                if (location.getY() + 1 < 8)
                    if (spaces[X + modifier][Y + 1].getPiece() != null)
                        if (spaces[X + modifier][Y + 1].getPiece().getPieceType() != this.pieceType)
                            moveSpaces[X + modifier][Y + 1] = true;
                if (location.getY() - 1 > -1)
                    if (spaces[X + modifier][Y - 1].getPiece() != null)
                        if (spaces[X + modifier][Y - 1].getPiece().getPieceType() != this.pieceType)
                            moveSpaces[X + modifier][Y - 1] = true;
                break;

        }
        return moveSpaces;
    }
}
