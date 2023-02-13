package me.corxl.capstoneclient.chess.spaces;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import me.corxl.capstoneclient.chess.pieces.Piece;

public class Space extends StackPane implements SpaceInterface {

    SpaceColor color;
    private final BoardLocation location;
    private Piece currentPiece;

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
            //this.clearSpace();
        });

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
            //this.clearSpace();
        });
    }

    @Override
    public Piece getPiece() {
        return this.currentPiece;
    }

    @Override
    public void setPiece(Piece p) {
        if (this.currentPiece != null)
            this.getChildren().remove(this.currentPiece);
        this.getChildren().add(p);
        this.currentPiece = p;
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
}
