import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//Author: Mario and Tauseef
//The point of this class is to draw and update the board and titles

public class GameBoard {
	//defines all the colors available in the game booard including the board itself
	final static Color PERU = new Color(205, 133, 63);
	final static Color SIENNA = new Color(160, 82, 45); 
	final static Color PURPLE = new Color(160, 32, 240);
	final static Color PINK = new Color(255, 20, 247);
	final static Color NEW_ORANGE = new Color(255, 165, 0);
	final static Color NEW_GREEN = new Color(69, 178, 69);
	
	//vairbles that define the board
	private static int hintSize = 10;
	private static int pegSize = 16;
	private static int pegWidth = 24;
	private static int startX = 85;
	private static int startY = 60;
	
	//this method draws the board and all the updated pegs and hints
	static void drawBoard(char[][] pegs, char[][] hints, int guesses, int size, Graphics g) throws Exception{
		g.setColor(PERU);
		g.fillRect(0, 0, 500, 700);
		for(int i = 0; i < guesses; i++){
			for (int j = 0; j < size; j++){
				drawPeg(startX + j*27,startY + i*35, pegs[i][j], g);
				drawHint(startX + (size-1)*27 + 40 + 20*(j%(size/2 + size%2)), 
						startY + 15*(j/(size/2 + size%2)) + 35*i, hints[i][j], g);	
			}
		}
		File fontFile = new File("Halo.ttf");
	    FileInputStream in = new FileInputStream(fontFile);
	    Font halo = Font.createFont(Font.TRUETYPE_FONT, in);
	    Font halo40Pt = halo.deriveFont(40f);
		g.setFont(halo40Pt);
		
		g.drawString("Master Mind", 92, 40);
	}
	
	//draws a single hint, used by drawBoard
	static void drawHint(int x, int y, char color, Graphics g){
		g.setColor(SIENNA);
		g.fillOval(x, y, hintSize, hintSize);
		if(color == 'B')
			g.setColor(Color.black);
		else if(color == 'W')
			g.setColor(Color.white);
		if(color != 'X')
			g.fillOval(x, y, hintSize, hintSize);
		g.setColor(Color.black);
		g.drawOval(x, y, hintSize, hintSize);
		
		
	}
	
	//draws a single Peg, used by drawBoard
	static void drawPeg(int x, int y, char color, Graphics g){
		g.setColor(SIENNA);
		g.fillRoundRect(x, y, pegSize, pegWidth, 50, 50);
		if(color == 'R')
			g.setColor(Color.red);
		else if(color == 'O')
			g.setColor(NEW_ORANGE);
		else if(color == 'Y')
			g.setColor(Color.yellow);
		else if(color == 'G')
			g.setColor(NEW_GREEN);
		else if(color == 'B')
			g.setColor(Color.blue);
		else if(color == 'P')
			g.setColor(PURPLE);		//purple
		else if(color == 'E')
			g.setColor(Color.lightGray);
		else if(color == 'K')
			g.setColor(PINK);
		if(color != 'X')
			g.fillRoundRect(x, y, pegSize, pegWidth, 50, 50);
		g.setColor(Color.black);
		g.drawRoundRect(x, y, pegSize, pegWidth, 50, 50);
	}
	
	//prints Success if player wins
	static void printWinnner(Graphics g) throws Exception
	{
		File fontFile = new File("Halo.ttf");
	    FileInputStream in = new FileInputStream(fontFile);
	    Font halo = Font.createFont(Font.TRUETYPE_FONT, in);
	    Font halo40Pt = halo.deriveFont(40f);
		g.setFont(halo40Pt);
		g.setColor(PERU);
		g.fillRect(0, 0, 500, 40);
		g.setColor(Color.white);
		g.drawString("SUCCESS", 90, 40);
	}
	
	//Prints Faliure if player loses
	static void printLoser(Graphics g) throws Exception
	{
		File fontFile = new File("Halo.ttf");
	    FileInputStream in = new FileInputStream(fontFile);
	    Font halo = Font.createFont(Font.TRUETYPE_FONT, in);
	    Font halo40Pt = halo.deriveFont(40f);
		g.setFont(halo40Pt);
		g.setColor(PERU);
		g.fillRect(0, 0, 500, 40);
		g.setColor(Color.red);
		g.drawString("FAILURE", 112, 40);
	}
	
	//Prints Welcome to mastermind for the title
	static void printMasterMind(Graphics g) throws Exception
	{
		File fontFile = new File("Halo.ttf");
	    FileInputStream in = new FileInputStream(fontFile);
	    Font halo = Font.createFont(Font.TRUETYPE_FONT, in);
	    Font halo40Pt = halo.deriveFont(40f);
	    g.setFont(halo40Pt);
	    g.drawString("Welcome To", 98, 40);
	    g.drawString("Master Mind", 90, 80);
	}
	
	//prints the solution if player failed
	static void printSolution(Graphics g, int size, int guesses){
		for(int i = 0; i < size; i++){
			drawPeg(startX + i*27,startY + guesses*35, BackEnd.code[i], g);
		}
		
		
	}
	
}
