import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class ColorPicker {

	
	private static int triangleSize = 20;
	public static char[] color;
	private static int startX = 20;
	private static int startY = 510;
	
	static void initColors(int size){
			color = new char[size];
		for(int i = 0; i < size; i++){
			color[i] = 'R';
		}
		
	}
	
	static void colorChange(int index, int change, int colorNum)
	{
		for(int i = 0; i < colorNum; i ++)
		{
			if(color[index] == BackEnd.colors[i])
			{	
				if(change == -1 && i == 0)
				{
					color[index] = BackEnd.colors[colorNum - 1];
					break;
				}
				else
				{
					color[index] = BackEnd.colors[(i + change) % colorNum];
					break;
				}
				
			}
		}
		
	}
	
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
	
	static Polygon makeUpTriangle(int x, int y){
		Polygon poly;
		int[] xPos = {x,x + triangleSize/2, x + triangleSize};
		int[] yPos = {y, y - 2*triangleSize/3, y};
		
		poly = new Polygon(xPos,yPos,3);
		
		return poly;
	}
	
	static Polygon makeDownTriangle(int x, int y){
		Polygon poly;
		int[] xPos = {x,x + triangleSize/2, x + triangleSize};
		int[] yPos = {y, y + 2*triangleSize/3, y};
		
		poly = new Polygon(xPos,yPos,3);
		
		return poly;
	}
	
	static void drawColor(int x, int y, char color, Graphics g){
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
			g.setColor(Color.lightGray);
		else if(color == 'K')
			g.setColor(Color.pink.darker());
		g.fillRect(x, y, 16, 16);
		g.setColor(Color.black);
		g.drawRect(x, y, 16, 16);
	}
	
	static void drawPolygon(Polygon poly, Graphics g){
		g.setColor(Color.black);
		g.fillPolygon(poly);
	}
	
	
	
}
