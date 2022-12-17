package advanceGraphics;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Node {
	
	// Every subclass supports a Color...
	// Access: protected (subclasses can access and modify directly) 
	protected Color c;
	protected Color c2;
	protected Color c3;
	
	protected String toStringHouse(int red, int green, int blue, int redW, int greenW, int blueW, int redDR, int greenDR, int blueDR)
	{
		return null;
	}
	
	// Every subclass of Node is forced to implement this method
	public abstract void draw(Graphics g);

	protected abstract String toString(int red, int green, int blue);


}
