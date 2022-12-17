package advanceGraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class NodeTriangle extends Node {

	private int x = 0;
	private int y = 0;
	private int z = 0;
	

	public String toString(int red,int green,int blue) {
		return "Triangle X " + x + " Y " + y + " Z " + z + " R" + red + " G" + green + " B" + blue;
	}

	public NodeTriangle(int x, int y, int z, Color c) {
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.c = c;
	}
	
	public void draw(Graphics g)
	{
	
		int[] listX = {x ,y, z};
		int[] listY = {y,z,x};
		
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(c);
		g.drawPolygon(listX,listY,listX.length);
		g2.fillPolygon(listX,listY,listX.length);
		
	}

}
