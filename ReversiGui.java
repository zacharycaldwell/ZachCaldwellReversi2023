/*
* Author: Zachary Caldwell
* Course: CSC 321
* Assignment: Reversi GUI
* Date: 10 Feb 2023
*
*/
import java.awt.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
public class ReversiGui extends JFrame{
    //Field Variables
    private JButton[][] boardState;
    private JLabel curPlayer, error;
    private Listener buttonListener;
    private Color black, white, green;
    private ReversiBoard game;
    public ReversiGui(ReversiBoard game){
        //8x8 array of buttons
        boardState = new JButton[Board.DEFAULTSIZE][Board.DEFAULTSIZE]; 
        
        buttonListener = new Listener();
        black = new Color(0,0,0);
        white = new Color(255,255,255);
        green = new Color (0,255,0);
        curPlayer = new JLabel("Black's Turn");
        curPlayer.setFont(new Font("Serif", Font.BOLD, 30));
        error = new JLabel("Invalid move");
        error.setFont(new Font("Serif", Font.BOLD, 30));
        this.game = game;
        
        
        for (int row=0; row<Board.DEFAULTSIZE; row++){
            for (int column=0; column<Board.DEFAULTSIZE; column++){
                boardState[row][column] = new JButton("");
                boardState[row][column].setBackground(green);
                boardState[row][column].addActionListener(buttonListener);
                boardState[row][column].setEnabled(true);
                //boardState[row][column].setSize
            }
        }
        //Sets middle
        boardState[3][4].setBackground(black);
        boardState[4][3].setBackground(black);
        boardState[3][3].setBackground(white);
        boardState[4][4].setBackground(white);
    
    }
    public void showGui(){
        JPanel gamePanel, infoPanel;
        Container myPane;
        
        myPane = getContentPane();
        myPane.setLayout(new GridLayout(2,1));
        
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(8,8));
        myPane.add(gamePanel);
        
        infoPanel = new JPanel();
        infoPanel.setLayout (new GridLayout(1,1));
        myPane.add(infoPanel);
        //Adds Buttons
        for(int i=0; i < Board.DEFAULTSIZE; i++)
    		for(int j=0; j < Board.DEFAULTSIZE; j++)
    			gamePanel.add(boardState[i][j]); 
    		
        
        infoPanel.add(curPlayer);
        infoPanel.add(error);
        
        
        //configures the swing window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,1000);
        setVisible(true);
        error.setVisible(false);
    }
    
    //Currently is acting something like a driver. 
    /*Current Errors:
        Does not allow edges to be pressed
        Keeps error message up
        Needs to sync to board
        Will not flip due to failure to sync to board
        *Not an error but Coupling is horrendous
    */
    public class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	
        	        	
        	//Checks if any of the game buttons have been pressed
        	for(int i=0; i < Board.DEFAULTSIZE; i++){
        		for(int j=0; j < Board.DEFAULTSIZE; j++){
        			if(e.getSource()==boardState[i][j] && game.isLegal(game.getTurn(), i,j)) { 
        				
        				
        				if(game.getTurn()== 'w') {
                			boardState[i][j].setBackground(white);
                            curPlayer.setText("Black's Turn");
                            game.flip(game.getTurn(),i,j);
                            
                            
                		}
                        else { 
                			boardState[i][j].setBackground(black);
                            curPlayer.setText("White's Turn");
                            game.flip(game.getTurn(),i,j);
                		}
                        game.changeTurn();
                        error.setVisible(false);
                        
                        
                        
        			}
                    else{
                        error.setVisible(true);
                        
                    }
                    //changePlayer(ReversiDriver.curPlayer);
                }
            }
        } 
    }
    
    


}