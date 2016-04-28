import java.awt.Color;
import java.awt.Graphics;

public class GameBoard {
	
	private static int hintSize = 10;
	private static int pegSize = 16;
	private static int pegWidth = 24;
	private static int startX = 25;
	private static int startY = 75;
	
	static void drawBoard(char[][] pegs, char[][] hints, int guesses, int size, Graphics g){
		for(int i = 0; i < guesses; i++){
			for (int j = 0; j < size; j++){
				drawPeg(startX + j*27,startY + i*35, pegs[i][j], g);
				drawHint(startX + (size-1)*27 + 40 + 20*(j%(size/2 + size%2)), 
						startY + 15*(j/(size/2 + size%2)) + 35*i, hints[i][j], g);	
			}
		}
	}
	
	static void drawHint(int x, int y, char color, Graphics g){
		if(color == 'B')
			g.setColor(Color.black);
		else if(color == 'W')
			g.setColor(Color.white);
		if(color != 'X')
			g.fillOval(x, y, hintSize, hintSize);
		g.setColor(Color.black);
		g.drawOval(x, y, hintSize, hintSize);
		
		
	}
	
	static void drawPeg(int x, int y, char color, Graphics g){
		if(color == 'R')
			g.setColor(Color.red);
		else if(color == 'O')
			g.setColor(Color.orange);
		else if(color == 'Y')
			g.setColor(Color.yellow);
		else if(color == 'G')
			g.setColor(Color.green);
		else if(color == 'B')
			g.setColor(Color.blue);
		else if(color == 'P')
			g.setColor(Color.magenta);		//purple
		else if(color == 'E')
			g.setColor(Color.gray);
		else if(color == 'K')
			g.setColor(Color.pink);
		if(color != 'X')
			g.fillRoundRect(x, y, pegSize, pegWidth, 50, 50);
		g.setColor(Color.black);
		g.drawRoundRect(x, y, pegSize, pegWidth, 50, 50);
	}
	
}
