import java.awt.Dimension;
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
	 int guesses = 12; //amount of guesses you have
	 int size = 4; //amount of pegs you need to match
	 int colorNum = 6; //amount of colors
	 boolean start = false;
	 int turn = 0;
	 
	MasterMindDriver(){
		initComponents();
	}
	
	//MasterMindDriver Needs a Serial Number
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		System.out.println("Hello World");
		new MasterMindDriver().setVisible(true);	
		
	}

	private void initComponents() {				
		 setSize(400, 600); // size of screen
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
	                        
	            add(sizeSpinner);
	            add(guessSpinner);
	            add(colorSpinner); 
	            add(begin);
	            add(enter);
	            enter.setEnabled(false);
	            enter.setVisible(false);
	            
	            
	            begin.addActionListener(new ActionListener() {
	           	 
	                public void actionPerformed(ActionEvent e)
	                {
	                    size = (Integer)sizeSpinner.getValue();
	                    guesses = (Integer)guessSpinner.getValue();
	                    colorNum = (Integer)colorSpinner.getValue();
	                    start = true;
	                    turn = 0;
	                    remove(sizeSpinner);
	                    remove(guessSpinner);
	                    remove(colorSpinner);
	                    remove(begin);
	                    enter.setVisible(true);
	                    enter.setEnabled(true);
	                    
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
	                	
	                	char[] guess = new char[size];
	                	
	                    System.out.println("Entered");
	                    for(int i = 0; i < size; i++){
	                    	pegs[turn][i] = ColorPicker.color[i];
	                    	guess[i] = ColorPicker.color[i];
	                    }
	                    
	                    char[] feedback = BackEnd.calculateResult(guess);
	                    
	                    for(int i = 0; i < size; i++){
	                    	hints[turn][i] = feedback[i];
	                    }
	                    
	                    turn = (turn+1)%guesses;
	                    
	                    //TODO victory
	                    
	                    if (turn == 0){
	                    	//TODO Faliure
	                    }
	                    
	            		repaint();
	                }
	            }); 
	        }		 	
         
	        @Override
	        public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            System.out.println("repainted");
	            //g.clearRect(0, 0, 400, 600);
	            if(start){
		            GameBoard.drawBoard(pegs, hints, guesses, size, g);
		            ColorPicker.drawColorPicker(size, g);
		            enter.setLocation(305, 500);
		            
	            }
	            else{ //beginning screen
	            	g.drawString("Size: ", 100, 414);
	            	g.drawString("Guesses: ", 100, 449);
	            	g.drawString("Colors: ", 100, 484);

	            	
	            	sizeSpinner.setLocation(200, 400);
	                guessSpinner.setLocation(200, 435);
	                colorSpinner.setLocation(200, 470);
	                begin.setLocation(300,500);   
	                
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
	    		if(start){
	    			for (int i = 0; i < size; i++) {
	    				int[] triangleX = { 20 + 35 * i - 3, 30 + 35 * i + 1, 40 + 35 * i + 11 };
	    				int[] triangleUpY = { 510 - 6, 510 - 8 - 15, 510 - 6 };
	    				int[] triangleDownY = { 510 + 16, 510 + 16 + 13, 510 + 16 };
	    				if (inUpTriangle(triangleX, triangleUpY, e.getX(), e.getY())) {
	    					System.out.println("Up Triangle " + i);
	    					ColorPicker.colorChange(i, 1, colorNum);
	    					repaint();
	    					
	    				} else if (inDownTriangle(triangleX, triangleDownY, e.getX(), e.getY())) {
	    					System.out.println("Down Triangle " + i);
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
