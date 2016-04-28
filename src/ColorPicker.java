import java.awt.Graphics;
import java.awt.Polygon;

public class ColorPicker {

	
	private static int triangleSize = 20;
	private static char[] color;
	private static int startX = 20;
	private static int startY = 520;
	
	static void initColors(int size){
			color = new char[size];
		for(int i = 0; i < size; i++){
			color[i] = 'R';
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
		g.drawRect(x, y, 16, 16);	
	}
	
	static void drawPolygon(Polygon poly, Graphics g){
		g.fillPolygon(poly);
	}
	
	
	
}
