import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Author: Mario Molina & Tauseef Aziz
 * 
 * Purpose: To obtain input from the player
 * and then send that information to the respective classes
 * that will then return the condition in which the player 
 * currently is in.
 * 
 * 
 */

public class MasterMindDriver extends JFrame{
	 JPanel screen;
	 char pegs[][];
	 char hints[][];
	 static int guesses = 12; //amount of guesses you have
	 int size = 4; //amount of pegs you need to match
	 int colorNum = 6; //amount of colors
	 boolean start = false;
	 int turn = 0;
	 int winner = 0; //0 -> game not done 1->winner 2-> loser
	 
	MasterMindDriver(){
		initComponents();
	}
	
	//MasterMindDriver Needs a Serial Number
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new MasterMindDriver().setVisible(true);	
		
	}

	private void initComponents() {				
		 setSize(500, 700); // size of screen
	     setDefaultCloseOperation(EXIT_ON_CLOSE); 
	     screen = new GameScreen();
	     
	     add(screen); //adds it to our MindMatrixDriver Frame
	}
		
	public void startGame(){
	}
	
	class GameScreen extends JPanel implements MouseListener {
		
		JSpinner sizeSpinner;
		JSpinner guessSpinner;
		JSpinner colorSpinner;
		JButton begin;
		JButton enter;
		JButton reset;
		JButton mainMenu;
		
		
		 public GameScreen() {
	            setPreferredSize(new Dimension(400, 600));
	            this.addMouseListener(this);
	            
	            //buttons and options
	            SpinnerNumberModel sizeModel = new SpinnerNumberModel(4,4,8,1);
	            SpinnerNumberModel guessModel = new SpinnerNumberModel(12,1,12,1);  
	            SpinnerNumberModel colorModel = new SpinnerNumberModel(6,6,8,1);  
	            sizeSpinner = new JSpinner(sizeModel);
	            guessSpinner = new JSpinner(guessModel);
	            colorSpinner = new JSpinner(colorModel);
	            begin = new JButton("Start!");
	            enter = new JButton("Enter");
	            reset = new JButton("Reset");
	            mainMenu = new JButton("Menu");
	                        
	            add(sizeSpinner);
	            add(guessSpinner);
	            add(colorSpinner); 
	            add(begin);
	            add(enter);
	            add(reset);
	            add(mainMenu);
	            enter.setEnabled(false);
	            enter.setVisible(false);
	            reset.setVisible(false);
	            reset.setEnabled(false);
	            mainMenu.setVisible(false);
	            mainMenu.setEnabled(false);
	            
	            
	            begin.addActionListener(new ActionListener() {
	           	 
	                public void actionPerformed(ActionEvent e)
	                {
	                    size = (Integer)sizeSpinner.getValue();
	                    guesses = (Integer)guessSpinner.getValue();
	                    colorNum = (Integer)colorSpinner.getValue();
	                    start = true;
	                    turn = 0;
	                    
	                    sizeSpinner.setVisible(false);
	                    sizeSpinner.setEnabled(false);
	                    guessSpinner.setVisible(false);
	                    guessSpinner.setEnabled(false);
	                    colorSpinner.setVisible(false);
	                    colorSpinner.setEnabled(false);
	                    begin.setVisible(false);
	                    begin.setEnabled(false);
	                    enter.setVisible(true);
	                    enter.setEnabled(true);
	                    reset.setVisible(true);
	    	            reset.setEnabled(true);
	    	            mainMenu.setVisible(true);
	    	            mainMenu.setEnabled(true);
	                    
	                  //initialize all spaces
	                    pegs = new char[guesses][size];
	            		hints = new char[guesses][size];
	            		ColorPicker.initColors(size);
	            		BackEnd.generateCode(size, colorNum);
	            		
	            		for(int i = 0; i < guesses; i ++){
	            			for (int j = 0; j < size; j++){
	            				pegs[i][j] = 'X';  	// X means no peg color chosen yet
	            				hints[i][j] = 'X';	// X means no hint given yet
	            			}
	            		}
	                    
	            		repaint();
	                }
	            });   
	            
	            enter.addActionListener(new ActionListener() {
		           	 
	                public void actionPerformed(ActionEvent e)
	                {
					if (winner == 0) {
						char[] guess = new char[size];
						
						for (int i = 0; i < size; i++) {
							pegs[turn][i] = ColorPicker.color[i];
							guess[i] = ColorPicker.color[i];
						}

						char[] feedback = BackEnd.calculateResult(guess);

						for (int i = 0; i < size; i++) {
							hints[turn][i] = feedback[i];
						}

						turn = (turn + 1) % guesses;

						for (int i = 0; i < size; i++) {
							if (feedback[i] == 'B') {
								winner = 1;
							} else {
								winner = 0;
								break;
							}
						}

						if (turn == 0) {

							winner = -1;
						}

						repaint();
					}
	                }
	            }); 
	            mainMenu.addActionListener(new ActionListener() {
		           	 
	                public void actionPerformed(ActionEvent e)
	                {
	                	
	                	//reinitialize important variables
	                	start = false;	 
	                	winner = 0;
	                	
	                	//reset the view
	                	sizeSpinner.setVisible(true);
	                    sizeSpinner.setEnabled(true);
	                    guessSpinner.setVisible(true);
	                    guessSpinner.setEnabled(true);
	                    colorSpinner.setVisible(true);
	                    colorSpinner.setEnabled(true);
	                    begin.setVisible(true);
	                    begin.setEnabled(true);
	                    enter.setVisible(false);
	                    enter.setEnabled(false);
	                    reset.setVisible(false);
	    	            reset.setEnabled(false);
	    	            mainMenu.setVisible(false);
	    	            mainMenu.setEnabled(false);
	    	            
	    	            repaint(); //repaint
	                }
	            }); 
	            reset.addActionListener(new ActionListener() {
		           	 
	                public void actionPerformed(ActionEvent e)
	                {
	                	winner = 0;
	                	//initialize to no answer and hints given
	                	for(int i = 0; i < guesses; i ++){
	            			for (int j = 0; j < size; j++){
	            				pegs[i][j] = 'X';  	// X means no peg color chosen yet
	            				hints[i][j] = 'X';	// X means no hint given yet
	            			}
	            		}
	                	BackEnd.generateCode(size, colorNum);//new code
	                	turn = 0; //reset turn to 0
	                	repaint(); //paint everything again
	                }
	            }); 
	        }		 	
         
	        @Override
	        public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	           
	            
	            g.clearRect(0, 0, 500, 700);
	            
	            
	            if(start){
		            try {
						GameBoard.drawBoard(pegs, hints, guesses, size, g);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            ColorPicker.drawColorPicker(size, g);
		            enter.setLocation(365, 530);
		            reset.setLocation(405, 10);
		            mainMenu.setLocation(10, 10);
		            if(winner == -1){
		            	try {
							GameBoard.printLoser(g);
							GameBoard.printSolution(g, size, guesses);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            } else if (winner == 1){
		            	try {
							GameBoard.printWinnner(g);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		            
	            }
	            else{ //beginning screen
	            	
	            	try {
						GameBoard.printMasterMind(g);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	g.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

	            	
	            	g.drawString("The computer will think of a secret code. You will try to guess", 10, 120);
	            	g.drawString("what colored pegs are in the code and what order they are in.", 10, 140);
	            	g.drawString("After you make a guess, the result will be displayed. The result", 10, 160);
	            	g.drawString("consists of a black peg for each peg that you have guessed the", 10,180);
	            	g.drawString("correct position and color and a white peg for each peg that is", 10, 200);
	            	g.drawString("the correct color, but not the correct position. You can set the", 10, 220);
	            	g.drawString("number of pegs with Size, the amount of different colors with", 10, 240);
	            	g.drawString("Colors and the amount of guesses allowed in Guesses. These", 10, 260);
	            	g.drawString("options are available below.", 10, 280);
	            	g.drawString("When you start, you will see this: ", 10, 320);
	            	ColorPicker.drawExample(300, 305, g);
	            	g.drawString("The up and down arrows are used to select a color.", 10, 360);
	            	g.drawString("When done contemplating, click Enter to submit your guess", 10, 380);
	            	g.drawString("Good Luck!", 10, 400);
//This is a text version of the classic board game Mastermind.
//The computer will think of a secret code. The code consists of 4 colored pegs.
//The pegs may be one of six colors: blue, green, orange, purple, red, or yellow. A color may appear
//more than once in the code. 
//in. After you make a valid guess the result (feedback) will be displayed.
//The result consists of a black peg for each peg you have guessed exactly correct (color and position) in
//your guess. For each peg in the guess that is the correct color, but is out of position, you get a white
//peg. For each peg, which is fully incorrect, you get no feedback.
//Only the first letter of the color is displayed. B for Blue, R for Red, and so forth.
//When entering guesses you only need to enter the first character of each color as a capital letter.
	            	
	            	
	            	g.drawString("Size: ", 100, 474);
	            	g.drawString("Guesses: ", 100, 509);
	            	g.drawString("Colors: ", 100, 544);

	            	
	            	sizeSpinner.setLocation(200, 460);
	                guessSpinner.setLocation(200, 495);
	                colorSpinner.setLocation(200, 530);
	                begin.setLocation(300,560);   
	                
	            }
	        }	
	        
	        public boolean inUpTriangle(int[] x, int[] y, int pX, int pY){
	    		int slope = 2;
	    		
	    		if(y[0] < pY || pY < y[1]){
	    			return false;
	    		}
	    		
	    		if(x[0] < pX && pX < x[1]){
	    			if(y[0] > pY && pY > (y[1] - (pX - x[0])*slope)){
	    				return true;
	    			}
	    			
	    		} else if(x[1] < pX && pX < x[2]){
	    			if(y[1] < pY && pY > (y[0] + (pX - x[0])*slope)){
	    				return true;
	    			}
	    		}
	    		
	    		return false;
	    		
	    	}
	    	
	    	public boolean inDownTriangle(int[] x, int[] y, int pX, int pY){
	    		int slope = 2;
	    		
	    		if(y[0] > pY || pY > y[1]){
	    			return false;
	    		}
	    		
	    		if(x[0] < pX && pX < x[1]){
	    			if(y[0] < pY && pY < (y[1] + (pX - x[0])*slope)){
	    				return true;
	    			}
	    			
	    		} else if(x[1] < pX && pX < x[2]){
	    			if(y[1] > pY && pY < (y[0] - (pX - x[0])*slope)){
	    				return true;
	    			}
	    		}
	    		
	    		return false;
	    		
	    	}

	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if(start && winner == 0){
	    			for (int i = 0; i < size; i++) {
	    				int[] triangleX = { 85 + 35 * i - 3, 95 + 35 * i + 1, 105 + 35 * i + 11 };
	    				int[] triangleUpY = { 540 - 6, 540 - 8 - 15, 540 - 6 };
	    				int[] triangleDownY = { 540 + 16, 540 + 16 + 13, 540 + 16 };
	    				if (inUpTriangle(triangleX, triangleUpY, e.getX(), e.getY())) {
	    					ColorPicker.colorChange(i, 1, colorNum);
	    					repaint();
	    					
	    				} else if (inDownTriangle(triangleX, triangleDownY, e.getX(), e.getY())) {
	    					
	    					ColorPicker.colorChange(i, -1, colorNum);
	    					repaint();
	    				}
	    			}
	    		}
	    		
	    	}
	    	@Override
	    	public void mouseEntered(MouseEvent e) {
	    		// TODO Auto-generated method stub
	    		
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		// TODO Auto-generated method stub
	    		
	    	}
	    	@Override
	    	public void mousePressed(MouseEvent e) {
	    		// TODO Auto-generated method stub
	    		
	    	}
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		// TODO Auto-generated method stub
	    		
	    	}
	        
	}
	
}
