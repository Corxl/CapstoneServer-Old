package me.corxl.capstoneclient.chess.pieces;

public enum PieceEnum {
    KING(new String[]{"pieces\\w_king_2x_ns.png", "pieces\\b_king_2x_ns.png"}),
    QUEEN(new String[]{"pieces\\w_queen_2x_ns.png", "pieces\\b_queen_2x_ns.png"}),
    PAWN(new String[]{"pieces\\w_pawn_2x_ns.png", "pieces\\b_pawn_2x_ns.png"}),
    BISHOP(new String[]{"pieces\\w_bishop_2x_ns.png", "pieces\\b_bishop_2x_ns.png"}),
    ROOK(new String[]{"pieces\\w_rook_2x_ns.png", "pieces\\b_rook_2x_ns.png"}),
    KNIGHT(new String[]{"pieces\\w_knight_2x_ns.png", "pieces\\b_knight_2x_ns.png"});
    public final String[] fileLocation;

    private PieceEnum(String[] fileLocation) {
        this.fileLocation = fileLocation;
    }
}
