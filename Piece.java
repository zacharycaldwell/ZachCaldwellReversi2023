/** This class will store a piece which is just an integer.
  * @author     Cathy Bareiss
  * @id         cathy.bareiss@betheluniversity.edu
  * @course     CSIS 321:  Programming 3
  * @assignment Reversi Project
  * @related    none
 */
public class Piece {
    // fields
    /** the type of piece */
    private int type;

//Class constants
//   public
    /** blank */
    public static final int BLANK = 0;
//   private
    /** smallest value allowed for a piece */
    private static final int MIN = 0;
    /** the largest value allowed for a piece */
    private static final int MAX = 2;

//Constructor
    /** create a piece of the given type
      * @param inType the type of piece to create
      */
    public Piece (int inType) {

        if (inType < MIN || inType > MAX) {
            System.err.println("invalid piece valid");
            type = -1;
        }
        // exception for invalid type needed
        type = inType;
    }

//Accessor
    /** returns integer representing the type of piece
      * @return the type of piece
      */
    public int getType() {
        return type;
    }

//Helper methods

//Standard methods
    /** if second piece is identical to current (deep compare)
      * @param otherPiece piece to compare to it.
      * @return if the to pieces are identical
      */
    public boolean equals(Piece otherPiece) {
        if (type == otherPiece.getType()) return true;
        return false;
    }
    
    public char toChar() {
        return ' ';
    }

    /** return a string representing a piece
      * @return string for the type of piece
      */
    public String toString() {
        if (type == BLANK) return "blank";
        if (type == -1) return "invalid piece";
        return null;
    }
}
