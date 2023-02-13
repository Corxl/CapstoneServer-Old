package me.corxl.capstoneclient.chess.spaces;

public enum SpaceColor {
    LIGHT("pieces\\square brown light_2x_ns.png"), DARK("pieces\\square brown dark_2x_ns.png");
    public final String fileLocation;

    private SpaceColor(String fileLocation) {
        this.fileLocation = fileLocation;
    }
}
