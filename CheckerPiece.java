public class CheckerPiece extends Piece {
    private int color;
    
    public CheckerPiece(int inType, int inColor) {
        super(inType);
        color = inColor;
    }
    
    public char toChar() {
        if (type == 1) return 'p'; else return 'k';
    }
}