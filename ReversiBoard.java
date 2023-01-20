/*
*
* @author      Zachary Caldwell
* @id          zachary.caldwell@betheluniversity.edu
* @course      CSC 321: Programming 3
* @assignment  Reversi Project
* @related     Piece
*/
public class ReversiBoard extends Board{
        private ReversiPiece[][] pieces;
        private int size;
        private char turn;
        public ReversiBoard(){
            size = super.DEFAULTSIZE;
            pieces = new ReversiPiece[size][size];
            turn = 'w';
            blankBoard();//makes board
            //white center pieces
            pieces[3][3].set(1);
            pieces[4][4].set(1);
            //Black center pieces
            pieces[3][4].set(2);
            pieces[4][3].set(2);
        }
        private void blankBoard(){
            int x, y;
            x=0;
            y=0;
            while(x<8){
                while(y<8){
                    pieces[x][y] = new ReversiPiece(Piece.BLANK,1);
                    y++;
                }
                y=0;
                x++;
            }
        }
        public ReversiPiece getPiece(int x, int y){
            return pieces[x][y];
        }
        public char getTurn(){
            return turn;
        }
        public void changeTurn(){
            if (turn == 'w'){
                turn='b';
            }
            else if (turn=='b'){
                turn='w';
            }
            else{//error case
                turn='w';
            }
        }
        
        //needs isLegal, onBoard, hasFlip, isOpposite, isOpen
        //did isOpen, isOpposite, isLegal, onBoard
        public boolean isOpen(int x, int y){
            if(!onBoard(x,y)) return false;
            if (pieces[x][y].toChar()=='w'||pieces[x][y].toChar()=='b'){
                return false;
            }
            return true;
        }
        public boolean onBoard(int x, int y){
            if (x>7 || x<0 || y>7 || y<0){
                return false;
            }
            return true;
        }
        public boolean isOpposite(char player, int x, int y, int dir){
            
            if (!onBoard(x,y) || isOpen(x,y)) return false;
            switch (dir){
                case 0: if(onBoard(x,y-1)){
                            if(player!=pieces[x][y-1].toChar() && pieces[x][y-1].getColour()!=Piece.BLANK){
                                return true;
                            }
                        }
                        return false;
                case 1: if(onBoard(x+1,y-1)){
                            if(player!=pieces[x+1][y-1].toChar() && pieces[x+1][y-1].getColour()!=Piece.BLANK){
                                return true;
                            }
                        }
                        return false;
                case 2: if (onBoard(x+1,y)){
                            if(player!=pieces[x+1][y].toChar() && pieces[x+1][y].getColour()!=Piece.BLANK){
                                return true;
                            }
                        }
                        return false;
                case 3: if(onBoard(x+1,y+1)){
                            if(player!=pieces[x+1][y+1].toChar() && pieces[x+1][y+1].getColour()!=Piece.BLANK){
                                return true;
                            }
                        }
                        return false;
                case 4: if(onBoard(x,y+1)){
                            if(player!=pieces[x][y+1].toChar() && pieces[x][y+1].getColour()!=Piece.BLANK){
                                return true;
                            }
                        }    
                        return false;
                case 5: if(onBoard(x-1,y+1)){
                            if(player!=pieces[x-1][y+1].toChar() && pieces[x-1][y+1].getColour()!=Piece.BLANK){
                                return true;
                            }
                        }    
                        return false;
                case 6: if(onBoard(x-1,y)){
                            if(player!=pieces[x-1][y].toChar() && pieces[x-1][y].getColour()!=Piece.BLANK){
                                return true;
                            }
                        }
                        return false;
                case 7: if(onBoard(x-1,y-1)){
                            if(player!=pieces[x-1][y-1].toChar() && pieces[x-1][y-1].getColour()!=Piece.BLANK){
                                return true;
                            }
                        }
                        return false;
                default: return false;
            }
        }
        public boolean hasFlip(char player, int x, int y){
            int testX, testY, dir;
            int[] curPos;
            curPos = new int[2];
            curPos[0] = x;
            curPos[1]=y;
            dir=0;
            while (dir<8){
                //move off targeted pos
                curPos = moveInDir(curPos[0], curPos[1], dir);
                while(isOpposite(player, curPos[0], curPos[1], dir)){ 
                    //Goes until it finds a point that is not the opposite
                    curPos = moveInDir(curPos[0], curPos[1], dir);
                }
                //moves into the space that is not opposite
                curPos = moveInDir(curPos[0], curPos[1], dir);
                //if same as player there is a flip
                if (onBoard(curPos[0],curPos[1]) && pieces[curPos[0]][curPos[1]].toChar()==player){
                        return true;
                }
                //else try again going in another direction
                dir++;
                curPos[0]=x;
                curPos[1]=y;    
            }
            //no flips
            return false;
        }
        
        public boolean isLegal(char player, int x, int y){
            if (onBoard(x,y) && isOpen(x,y) && hasFlip(player, x, y)){
                return true;
            }
            return false;
        }
        //Can be improved to only go in the direction once,
        //however time was running short and I just needed to get it working
        public void flip(char player, int x, int y){
            int colour, dir;
            int[] curPos;
            curPos = new int[2];
            curPos[0] =x;
            curPos[1]=y;
            dir=0;
            //set it to white
            colour = 1;
            //if black's turn set to black
            if (player == 'b'){
                colour = 2;
            }
            
            while (dir<8){
                //move off targeted pos
                if (isOpposite(player,curPos[0], curPos[1], dir)){
                    curPos = moveInDir(curPos[0], curPos[1], dir);
                }
                    while(isOpposite(player, curPos[0], curPos[1], dir)){ 
                        //Goes until it finds a point that is not the opposite
                        curPos = moveInDir(curPos[0], curPos[1], dir);
                    }
                    //moves into the space that is not opposite
                    curPos = moveInDir(curPos[0], curPos[1], dir);
                    //if same as player there is a flip
                    if (onBoard(curPos[0],curPos[1]) && pieces[curPos[0]][curPos[1]].toChar()==player){
                        //goes back
                        curPos[0]=x;
                        curPos[1]=y;
                        //starts flipping
                        curPos = moveInDir(curPos[0], curPos[1], dir);
                        pieces[curPos[0]][curPos[1]].set(colour);
                        while(isOpposite(player, curPos[0], curPos[1], dir)){ 
                            //Goes until it finds a point that is not the opposite
                            curPos = moveInDir(curPos[0], curPos[1], dir);
                            pieces[curPos[0]][curPos[1]].set(colour);
                        }
                            
                        
                    }
                
                //go in another direction
                dir++;
                curPos[0]=x;
                curPos[1]=y;    
            }
        }
        public boolean hasMove(char player){
            int i,j;
            i=0;
            j=0;
            while(i<8){
                while (j<8){
                    if (isLegal(player, i,j)){
                        return true;
                    }
                    j++;
                }
                j=0;
                i++;
            }
            return false;
       }
        
        
        
        
        
        
        
        
        
        public void placePiece(char player, int x, int y){
            if (isLegal(player, x, y)){
                if (player == 'w'){
                    pieces[x][y].set(1);
                }
                else {
                    pieces[x][y].set(2);
                }
            }
        }
        
        
        
        public String toString() {
        String tempString;
        int row, column;

        tempString = "";
        for (row = 0; row < size; row++) {
            for (column = 0; column < size; column++) {
                tempString = tempString+pieces[row][column].toChar()+" ";
            }
            tempString = tempString+"\n";
        }
        return tempString;
    }
    private int[] moveInDir(int x, int y, int dir){
        int[] newPos;
        newPos = new int[2];
        switch (dir){//moves off of targeted position
                        case 0: y--;
                                break;
                        case 1: x++;
                                y--;
                                break;
                        case 2: x++;
                                break;
                        case 3: x++;
                                y++;
                                break;
                        case 4: y++;
                                break;
                        case 5: x--;
                                y++;
                                break;
                        case 6: x--;
                                break;
                        case 7: x--;
                                y--;
                                break;
                        default: x=8;
                                 y=8;
                                 break;
        }
        newPos[0]=x;
        newPos[1]=y;
        return newPos;
    }
       
        
}