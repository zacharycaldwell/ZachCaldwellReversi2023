/** This class will store a generic square board of pieces.
  *     Details about how the board is used in a given game must
  *     be implemented in a subclass.
  *
  * @author      Cathy Bareiss
  * @id          cathy.bareiss@betheluniversity.edua
  * @course      CSC 321: Programming 3
  * @assignment  Reversi Project
  * @related     Piece
  */
public class Board {
    // fields
    /** pieces for the board */
    protected Piece[][] pieces;
    /** size of the board */
    protected int size;  // not needed - can go by length but implemented instead

    // public class constants about generic games
    /** the default size of the board */
    public static final int DEFAULTSIZE = 8;
    /** if the move specified indicates ending the game */
    public static final int ENDGAME = -1;
    /** if the move specified was legal */
    public static final int LEGALMOVE = 0;
    /** if the move specified was illegal */
    public static final int BADMOVE = 1;
    
    

//Constructors and related methods
    /** create a board of the default size */
    public Board() {
        int row, column;
        
        size = DEFAULTSIZE;
        pieces = new Piece[size][size];
        blankBoard();
    }

    /** create a square board of the size requested
      * @param inSize - size of board
      */
    public Board(int inSize) {
        int row, column;

        size = inSize;
        pieces = new Piece[size][size];
        blankBoard();
    }

    // sets entire board to blanks
    /** set all pieces to blank */
    private void blankBoard() {
        int row, column;

        for (row = 0; row < size; row++)
            for (column = 0; column < size; column++)
                pieces[row][column] = new Piece(Piece.BLANK);

    }

//Mutators
    /** sets a given location to a specified piece
      * @param inPiece the piece to store
      * @param row the row to store the piece
      * @param column the column to store the piece
      */
    public void setPiece(Piece inPiece, int row, int column) {
        pieces[row][column] = inPiece;
    }

//Accessors
    /** returns the piece at a given location
      * @param row the row of the piece to return
      * @param column the column of the piece to return
      * @return the piece requested
      */
    public Piece getPiece(int row, int column) {
        return pieces[row][column];
    }

    /** returns the size of the board
      * @return the size of the board
      */
    public int getSize() {
        return size;
    }

//Helper methods

    // is a given location on the board?
    /** returns if the specified location is on the board
      * @param row the row specifed
      * @param column the column specified
      * @return if the location is on the board
      */
    public boolean onBoard(int row, int column) {
        if (row < 0) return false;
        if (column  < 0) return false;
        if (row >= size) return false;
        if (column >= size) return false;
        return true;
    }

//Standard methods
    /** returns if two boards are the same
      * @param otherBoard the board to compare to
      * @return if same pieces at every location
      */
    public boolean equals(Board otherBoard) {
        int row, column;
        boolean same;

        same = true;
        if (size != otherBoard.getSize()) return false;
        for (row = 0; row < size; row++)
            for (column=0; column < size; column++)
                if (!pieces[row][column].equals(
                            otherBoard.getPiece(row,column)))
                    same = false;
        return same;
    }

    /** return string representing the board
      * @return a string representing the board
      */
    public String toString() {
        String tempString;
        int row, column;

        tempString = null;
        for (row = 0; row < size; row++) {
            for (column = 0; column < size; column++) {
                tempString = tempString+pieces[row][column].toChar()+" ";
            }
            tempString = tempString+"\n";
        }
        return tempString;
    }
}