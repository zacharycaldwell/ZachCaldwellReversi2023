import java.util.*;
import java.io.*;
/*
* Author: Zachary Caldwell
* Modifier: N/A
* Class: CSC 122
* Assignment: Graph
*/
public class ReversiPiece extends Piece{
    private int colour;
    
    public ReversiPiece(int inColour, int inType){
        super(inType);
        colour = inColour;
    }
    
    public char toChar(){
        if(colour==1) return 'w';
        else if (colour==2) return 'b';
        return '-';
    }
    public int getColour(){
        return colour;
    }
    
    public void set(int inColour){
        if (colour>2||colour<0){
            System.out.println("Invalid colour");
        }
        colour = inColour;
    }
}