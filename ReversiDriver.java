import java.util.*;
import java.io.*;
/*
*
* @author      Zachary Caldwell
* @id          zachary.caldwell@betheluniversity.edu
* @course      CSC 321: Programming 3
* @assignment  Reversi Project
* @related     Piece
*/

public class ReversiDriver{
    public static void main(String args[]){
            ReversiBoard board;
            board = new ReversiBoard();
            char curPlayer;
            int[] curMove;
            
            
            
            ReversiGui game;
            game = new ReversiGui(board);
            game.showGui();
            
            
            curMove = new int[2];
            curPlayer = 'b';
            System.out.println("Board as been made");
            //changeTurn() + end condition
            while(board.hasMove(curPlayer)){
                if (curPlayer == 'w'){
                    System.out.println("It is white's turn");
                    //game.set
                }
                else if (curPlayer == 'b'){
                    System.out.println("It is black's turn");
                }
                System.out.println(board.toString());
                curMove = getMove(curPlayer, board);
                makeMove(curPlayer, curMove[0], curMove[1], board);                
                curPlayer = changeTurn(curPlayer);//gives progress towards end condition
            }
            System.out.println(board.toString());
            if (curPlayer == 'w'){
                System.out.println("Black wins!");
            }
            if (curPlayer =='b'){
                System.out.println("White wins!");
            }
            
            
            
            
            
            
            /*
            //board.pieces[3][3].set(1);
            System.out.println(board.isOpen(3,3));
            System.out.println(board.isOpen(1,1));
            System.out.println(board.isOpen(1,10000000));
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(board.getPiece(3,3).toChar());
            System.out.println(board.isOpposite('b',3,2,4));
            System.out.println(board.isOpposite('w',3,2,4));
            System.out.println(board.toString());
            System.out.println(board.hasFlip('w',4,2));
            System.out.println(board.hasFlip('b',4,2));
            /*board.placePiece('w',4,2);
            board.flip('w',4,2);
            curMove = getMove('w', board);
            makeMove('w',curMove[0],curMove[1],board);
            System.out.println(board.toString());
            */
        }
        
        //polish the driver and get input for move
        private static void makeMove(char player, int x, int y, ReversiBoard board){
            if (board.isLegal(player, x, y)){
                board.placePiece(player, x, y);
                board.flip(player, x, y);
            }
        }
        private static int[] getMove(char player, ReversiBoard board){
            Scanner keyboard;
            int[] move;
            keyboard = new Scanner(System.in);
            move = new  int[2];
            move[0] = -1;
            move[1] = -1;
            while (!board.isLegal(player, move[0], move[1])){
                while (move[0]<0 || move[0]>7){
                    System.out.println("How far down do you want to place? (0-7)");
                    try{
                        move[0] = keyboard.nextInt();
                    }catch (InputMismatchException e){
                        System.out.println("This requires an int");
                    }
                    
                    
                }
                while (move[1]<0 || move[1]>7){
                    System.out.println("How far across do you want to place? (0-7)");
                    try{
                        move[1] = keyboard.nextInt();
                    }catch (InputMismatchException e){
                        System.out.println("This requires an int");
                    }
                
                }
                if (!board.isLegal(player, move[0],move[1])){
                    System.out.println("Illegal move");
                    move[0] = -1;
                    move[1] = -1;
                }
            }
            return move;           
        }
        private static char changeTurn(char player){
            if (player == 'w'){
                player = 'b';
                //System.out.println(player);
            }
            else{
                player = 'w';
                //System.out.println(player);
            }
            return player;
        }
}