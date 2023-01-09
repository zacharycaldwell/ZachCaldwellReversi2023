public interface BoardInterface {
    public void setPiece(Piece inPiece, int row, int column);
    public Piece getPiece(int row, int column);
    public int getSize();
    public boolean onBoard(int row, int column);
    public boolean equals(BoardInterface otherBoard);
    public String toString();
}