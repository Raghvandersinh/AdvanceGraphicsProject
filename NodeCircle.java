package advanceGraphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class NodeCircle extends Node {

	// Every subclass of Node must provide implementation 
	// for the draw() method (see below) as well as inheriting the 
	// protected instance variable c for the shape color
	
	private int x = 0;
	private int y = 0;
	private int radius = 0;
	private int plotWidth = 0;
	private int plotHeight = 0;
		
	public NodeCircle(int x, int y, int radius, Color c) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.c = c;
	}
	

	public String toString(int red, int green, int blue) {
		return "Circle X" + x + " Y" + y + " Rad" + radius + " R" + red + " G" + green + " B" + blue ;
	}



	public void draw(Graphics g) {
		
		plotWidth = (int) g.getClipBounds().getWidth();
		plotHeight = (int) g.getClipBounds().getHeight();
		
		int dX = x - radius + (plotWidth / 2);
		int dY = y - radius + (plotHeight / 2);
		g.setColor(c);
		g.drawOval(dX, dY, radius*2, radius*2);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.fillOval(dX, dY, radius*2, radius*2);
				
	}
	
}
