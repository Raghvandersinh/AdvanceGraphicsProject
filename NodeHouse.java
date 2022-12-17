package advanceGraphics;

import java.awt.Color;
import java.awt.Graphics;

public class NodeHouse extends Node
	{
	private int x = 0;
	private int y = 0;
	
	
	private int shapeWidth = 0;
	private int shapeHeight = 0;
	private int radius = 0;
	private int plotWidth = 0;
	private int plotHeight = 0;
	
	
	public NodeHouse(int x, int y, int shapeWidth, int shapeHeight, int radius, Color c, Color c2, Color c3) {
	
		this.x = x;
		this.y = y;
		this.shapeWidth = shapeWidth;
		this.shapeHeight = shapeHeight;
		this.radius = radius;
		this.c = c;
		this.c2 = c2;
		this.c3 = c3;
	}
	
	@Override
	public void draw(Graphics g) {
		plotWidth = (int) g.getClipBounds().getWidth();
		plotHeight = (int) g.getClipBounds().getHeight();
		
		int dXRect = x - (shapeWidth / 2) + (plotWidth / 2);
		int dYRect = y - (shapeHeight / 2) + (plotHeight / 2);
		
	
		g.drawRect(dXRect + 17, dYRect + 17, shapeWidth, shapeHeight);
		g.setColor(c);
		g.fillRect(dXRect+17, dYRect+17, shapeWidth, shapeHeight);
		
		g.setColor(c2);
		drawRoof(g);
		g.setColor(c3);
		drawWindows(g);
		
		g.drawRect(dXRect + 92, dYRect + 141, shapeWidth - 150, shapeHeight - 125);
		g.setColor(c2);
		g.fillRect(dXRect + 92, dYRect + 141, shapeWidth - 150, shapeHeight - 125);
	}
	/**
	 * draws the roof of the house
	 * @param g
	 */
	public void drawRoof(Graphics g)
	{
		int goldenHeight =  200;
		int pointY1 =  goldenHeight - (plotWidth/2);
		int pointY2 = goldenHeight + 100 - (plotWidth/2);
		int pointY3 = goldenHeight + 100 - (plotWidth/2);
		
		int goldenWidth = 200;
		int pointX1  = goldenWidth + 200 - (plotWidth/2);
		int pointX2 = goldenWidth+ 100 - (plotWidth/2);
		int pointX3  = goldenWidth+  300 - (plotWidth/2);
		
		
		if(shapeWidth == goldenWidth && shapeHeight == goldenHeight)
		{
			int[] listX = {pointX1 + x , pointX2 + x, pointX3 + x};
			int[] listY = {pointY1 ,pointY2 + y ,pointY3 + y};
			g.drawPolygon(listX, listY, listX.length);
			g.fillPolygon(listX,listY,listX.length);
			
		}
		else
		{
			g.drawPolygon(getRoofWidth(), getRoofHeight(), 3);
			g.fillPolygon(getRoofWidth(), getRoofHeight(), 3);
		}
		
	}
		/**
		 * gets x coordinates such that the base matches the width of the rectangle. 
		 * @return returns array of x coordinates
		 */
	public int[] getRoofWidth()
	{
		
		
		int goldenWidth = 200;
		double diff1 = 200;
		double diff2 = 100;
		double diff3 = 300;
		
		while(goldenWidth != shapeWidth)
		{
			if(shapeWidth > goldenWidth)
			{
				goldenWidth += 1;
				diff1 -= 1;
				diff2 -= 1.5;
				diff3 -= 0.5;
			}
			else
			{
				goldenWidth -= 1;
				diff1 += 1;
				diff2 += 1.5;
				diff3 += 0.5;
			}
		}
		int d1 = (int) diff1 + x;
		int d2 = (int) diff2 + x;
		int d3 = (int) diff3 + x;
		int X1  = (goldenWidth - (plotWidth/2)) + d1;
		int X2 = (goldenWidth - (plotWidth/2)) + d2;
		int X3  = (goldenWidth - (plotWidth/2)) + d3;
		
	    int[] listX = {X1,X2,X3};
	    return listX;
		
	}
	/**
	 * gets y coordinates such that it the base of the triangle will stick to the top of the 
	 * Rectangle
	 * @return return y coordinates of the triangle. 
	 */
	public int[] getRoofHeight()
	{
		
		double diff1 = 0;
		double diff2 = 100;
		int goldenHeight =  200;
		while(goldenHeight != shapeHeight)
		{
			if(shapeHeight > goldenHeight)
			{
				goldenHeight += 1;
				diff1 -= 1.25;
				diff2 -= 1.5;
			}
			else
			{
				goldenHeight -= 1;
				diff1 += 1.25;
				diff2 += 1.5;
			}
		}
		int d1 = (int) diff1;
		int d2 = (int) diff2 + y;
		int Y1 =  (goldenHeight -  (plotWidth/2)) + d1;
		int Y2 = (goldenHeight - (plotWidth/2)) + d2;
		int Y3 = (goldenHeight - (plotWidth/2)) + d2;
		
	    int[] listY = {Y1,Y2,Y3};
	    return listY;
	}
	
	public void drawWindows(Graphics g)
	{
		
	
		g.drawOval(getLeftWindow(), getWindowHeight(), radius*2, radius*2);
		g.fillOval(getLeftWindow(), getWindowHeight(), radius*2, radius*2);
		
	
		g.drawOval(getRightWindow(),  getWindowHeight(), radius*2, radius*2);
		g.fillOval(getRightWindow(),  getWindowHeight(), radius*2, radius*2);
		
	}
	
	public int getLeftWindow()
	{
		
		int goldenLeft = -45;
		int goldenWidth  = 200;
		
		
		double diffLeft = 0;
		while(goldenWidth != shapeWidth)
		{
			if(shapeWidth > goldenWidth)
			{
				goldenWidth += 1;
				diffLeft -= 0.25;
				
			}
			else
			{
				goldenWidth -= 1;
				diffLeft += 0.25;
			}
		}
		int dL = (int) diffLeft + goldenLeft + x;
	
		int leftWindow = (dL - radius + (plotWidth / 2));
		return leftWindow;
	}
	
	public int getRightWindow()
	{
		int goldenRight = 75;
		int goldenWidth  = 200;
		
		double diffRight = 0;
		while(goldenWidth != shapeWidth)
		{
			if(shapeWidth > goldenWidth)
			{
				goldenWidth += 1;
				diffRight+= 0.25;
				
			}
			else
			{
				goldenWidth -= 1;
				diffRight -= 0.25;
			}
		}
		int dR = (int) diffRight + goldenRight + x;
		int rightWindow = (dR - radius + (plotWidth / 2));
		return rightWindow;
	}
	
	public int getWindowHeight()
	{
		int goldenHeightCircle = -20;
		int goldenHeight = 200;
		double diffHeight = 0;
		
		if(shapeHeight > goldenHeight)
		{
			goldenHeight += 1;
			diffHeight -= 20.25;
		}
		
		else
		{
			goldenHeight -= 1;
			diffHeight += 20.25;
		}
		int dH = (int) diffHeight + goldenHeightCircle + y;
		int windowHeight = (dH - radius + (plotHeight / 2));
		return windowHeight;
	}
	
	@Override
	protected String toString(int red, int green, int blue) {
		return "NodeHouse: " + " X" + x + " Y" + y + " Width:" + shapeWidth + " Height" + 
	shapeHeight + " Radius" + radius + " R" + red + " G" + green + " B" + blue;
	}
	@Override
	protected String toStringHouse(int red, int green, int blue, int redW, int greenW,int blueW, int redDR,
			int greenDR, int blueDR) {
		// TODO Auto-generated method stub
		return "NodeHouse: " + " X" + x + " Y" + y + " Width:" + shapeWidth + " Height" + 
		shapeHeight + " Radius" + radius + " R" + red + " G" + green + " B" + blue + 
		" Window: " + "RW" + redW + " GW" + greenW + " BW" + blueW
		+ " Door/Roof: " + "RDR" + redDR + " GDR" + greenDR + " BDR" + blueDR;
	}
	
	


	
}
