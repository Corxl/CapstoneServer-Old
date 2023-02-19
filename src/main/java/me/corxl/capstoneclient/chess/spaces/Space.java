package me.corxl.capstoneclient.chess.spaces;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import me.corxl.capstoneclient.chess.board.Board;
import me.corxl.capstoneclient.chess.pieces.Piece;
import me.corxl.capstoneclient.chess.pieces.PieceEnum;
import me.corxl.capstoneclient.chess.pieces.TeamColor;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Random;

public class Space extends StackPane implements SpaceInterface {

    SpaceColor color;
    private final BoardLocation location;
    private Piece currentPiece;
    private final String SELECT_FILE_LOCATION = "\\src\\main\\resources\\me\\corxl\\capstoneclient\\pieces\\select.png";
    private final String soundDir = System.getProperty("user.dir") + "\\src\\main\\resources\\me\\corxl\\capstoneclient\\sounds\\";
    private ImageView select;
    private boolean isSelected = false;

    public Space(SpaceColor color, BoardLocation location) {
        this.location = location;
        this.setStyle("-fx-background-color: #1F1F1F");
        this.setPadding(new Insets(3, 3, 3, 3));
        this.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setAlignment(Pos.CENTER);
        this.color = color;
        String p = System.getProperty("user.dir") + "\\src\\main\\resources\\me\\corxl\\capstoneclient\\" + this.color.fileLocation;
        Image i = new Image(p);
        ImageView v = new ImageView(i);
        v.setFitWidth(80);
        v.setFitHeight(80);
        this.getChildren().add(v);
        this.setOnMouseClicked((e) -> {
            onClick();
        });
        currentPiece = null;

    }

    public Space(SpaceColor color, BoardLocation location, Piece piece) {
        this.currentPiece = piece;

        this.location = location;
        this.setStyle("-fx-background-color: #1F1F1F");
        this.setPadding(new Insets(3, 3, 3, 3));
        this.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setAlignment(Pos.CENTER);
        this.color = color;
        String p = System.getProperty("user.dir") + "\\src\\main\\resources\\me\\corxl\\capstoneclient\\" + this.color.fileLocation;
        Image i = new Image(p);
        ImageView v = new ImageView(i);
        v.setFitWidth(80);
        v.setFitHeight(80);
        this.getChildren().add(v);
        this.getChildren().add(this.currentPiece);
        this.setOnMouseClicked((e) -> {
            onClick();
        });
    }

    public void play(String filename) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(soundDir + filename)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    private void onClick() {
        Board.isPieceSelected = this.currentPiece != null;
        System.out.println(Board.isPieceSelected + ";;;");
        if (Board.selectedSpaces[this.getLocation().getX()][this.getLocation().getY()]) {
            if (Board.selectedPiece != null) {
                Piece deadPiece = Board.getSpaces()[this.getLocation().getX()][this.getLocation().getY()].getPiece();
                if (deadPiece != null) {
                    if (deadPiece.getPieceType() == PieceEnum.QUEEN)
                        play("queen_death.wav");
                    else
                        play(new Random().nextInt(5) + 1 + ".wav");
                } else
                    play(new Random().nextInt(5) + 1 + ".wav");
                Board.setPiece(Board.selectedPiece, new BoardLocation(this.getLocation().getX(), this.getLocation().getY()), Board.selectedPiece.getLocation());
                Board.isPieceSelected = false;
                Board.clearSelections();
                Board.setTurn(Board.getTurn() == TeamColor.WHITE ? TeamColor.BLACK : TeamColor.WHITE);
                Board.checkKingsSaftey();

            }
        }
        if (this.currentPiece == null) {
            boolean[][] selected = Board.selectedSpaces;
            for (int j = 0; j < selected.length; j++) {
                for (int k = 0; k < selected[j].length; k++) {
                    if (selected[j][k]) {
                        Board.getSpaces()[j][k].setSelected(false);
                    }
                }
            }
        }
    }

    @Override
    public Piece getPiece() {
        return this.currentPiece;
    }

    @Override
    public void setPiece(Piece p) {
        if (this.currentPiece != null) {
            this.getChildren().remove(this.currentPiece);
        }
        this.currentPiece = p;
        if (this.currentPiece != null) {
            this.getChildren().add(this.currentPiece);
            p.setLocation(this.location);
        }

    }

    public BoardLocation getLocation() {
        return this.location;
    }

    @Override
    public boolean isEmpty() {
        return this.currentPiece == null;
    }

    @Override
    public boolean isOccupied() {
        return this.currentPiece != null;
    }

    @Override
    public SpaceColor getColor() {
        return this.color;
    }

    @Override
    public void clearSpace() {
        if (this.currentPiece != null)
            this.getChildren().remove(this.currentPiece);
        this.currentPiece = null;
    }

    public void setSelected(boolean selected) {
        if (selected == isSelected)
            return;
        isSelected = selected;
        if (selected) {
            String p = System.getProperty("user.dir") + SELECT_FILE_LOCATION;
            Image i = new Image(p);
            ImageView v = new ImageView(i);
            select = v;
            v.setFitWidth(80);
            v.setFitHeight(80);
            if (this.isEmpty())
                this.getChildren().add(v);
            else
                this.getChildren().add(this.getChildren().size() - 1, v);
        } else if (select != null) {
            this.getChildren().remove(select);
            select = null;
        }

    }
}
