package advanceGraphics;
import java.awt.Graphics;

public class NodeOrigin extends Node {

	private int w = 0;
	private int h = 0;
	
	public NodeOrigin() {

	}
	
	public void draw(Graphics g) {
		w = (int) g.getClipBounds().getWidth();
		h = (int) g.getClipBounds().getHeight();
		
		g.setColor(c);
		g.fillOval(w/2 - 10, h/2 - 10, 20, 20);
	}
	public String toString(int red, int blue, int green)
	{
		return "Plot" + " R" + red + " B" + blue + " G" + green;
	}
}
