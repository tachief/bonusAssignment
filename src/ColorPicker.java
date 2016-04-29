import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class ColorPicker {

	
	private static int triangleSize = 20;
	public static char[] color;
	private static int startX = 85;
	private static int startY = 540;
	
	static void initColors(int size){
			color = new char[size];
		for(int i = 0; i < size; i++){
			color[i] = 'R';
		}
		
	}
	/**
	 * 
	 * @param index	index of colorPicker to change
	 * @param change either +1 or -1
	 * @param colorNum	how many colors there are
	 */
	static void colorChange(int index, int change, int colorNum)
	{
		for(int i = 0; i < colorNum; i ++)
		{
			if(color[index] == BackEnd.colors[i])
			{	
				//avoids having a negative value
				if(change == -1 && i == 0)
				{
					color[index] = BackEnd.colors[colorNum - 1];
					break;
				}
				//default case
				else
				{
					color[index] = BackEnd.colors[(i + change) % colorNum];
					break;
				}
				
			}
		}
		
	}
	
	/**
	 * Draws the color picker construct
	 * @param size	how many color pickers to draw
	 * @param g	how it draws
	 */
	static void drawColorPicker(int size, Graphics g){
		Polygon upTriangle;
		Polygon downTriangle;
		for(int i = 0; i < size; i++){
			upTriangle = makeUpTriangle(startX + 35*i - 2,startY - 8);
			downTriangle = makeDownTriangle(startX + 35*i - 2,startY + 16);
			drawPolygon(upTriangle, g);
			drawPolygon(downTriangle, g);
			drawColor(startX + 35*i,startY - 5,color[i],g);
		}
		
	}
	
	/**
	 * constructs color picker up arrows
	 * @param x
	 * @param y
	 * @return
	 */
	static Polygon makeUpTriangle(int x, int y){
		Polygon poly;
		int[] xPos = {x,x + triangleSize/2, x + triangleSize};
		int[] yPos = {y, y - 2*triangleSize/3, y};
		
		poly = new Polygon(xPos,yPos,3);
		
		return poly;
	}
	
	/**
	 * constructs color picker down arrows
	 * @param x
	 * @param y
	 * @return
	 */
	static Polygon makeDownTriangle(int x, int y){
		Polygon poly;
		int[] xPos = {x,x + triangleSize/2, x + triangleSize};
		int[] yPos = {y, y + 2*triangleSize/3, y};
		
		poly = new Polygon(xPos,yPos,3);
		
		return poly;
	}
	/**
	 * draws current color of a color picker
	 * @param x 
	 * @param y
	 * @param color char representation of color
	 * @param g
	 */
	static void drawColor(int x, int y, char color, Graphics g){
		if(color == 'R')
			g.setColor(Color.red);
		else if(color == 'O')
			g.setColor(GameBoard.NEW_ORANGE);
		else if(color == 'Y')
			g.setColor(Color.yellow);
		else if(color == 'G')
			g.setColor(GameBoard.NEW_GREEN);
		else if(color == 'B')
			g.setColor(Color.blue);
		else if(color == 'P')
			g.setColor(GameBoard.PURPLE);		//purple
		else if(color == 'E')
			g.setColor(Color.lightGray);
		else if(color == 'K')
			g.setColor(GameBoard.PINK);
		g.fillRect(x, y, 16, 16);
		g.setColor(Color.black);
		g.drawRect(x, y, 16, 16);
	}
	
	/**
	 * Draws the polgon in black
	 * @param poly
	 * @param g
	 */
	static void drawPolygon(Polygon poly, Graphics g){
		g.setColor(Color.black);
		g.fillPolygon(poly);
	}
	
	/**
	 * Draws an example color picker
	 * @param x
	 * @param y
	 * @param g
	 */
	static void drawExample(int x, int y, Graphics g){
		Polygon upTriangle = makeUpTriangle(x,y);
		Polygon downTriangle = makeDownTriangle(x,y + 24);
		drawPolygon(upTriangle, g);
		drawPolygon(downTriangle, g);
		drawColor(x + 2,y + 3,'B',g);
		
	}
	
	
}
