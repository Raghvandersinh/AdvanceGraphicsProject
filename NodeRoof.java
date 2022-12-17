package advanceGraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class NodeRoof extends Node
	{

		private int x = 0;
		private int y = 0;
		
		
		private int plotWidth = 0;
		private int plotHeight = 0;
		

		public String toString(int red,int green,int blue) {
			return "Triangle V1" + x + " V2" + y  + " R" + red + " G" + green + " B" + blue;
		}

		public NodeRoof(int x, int y, Color c) {
			
			this.x = x;
			this.y = y;
			this.c = c;
		}
	@Override
	public void draw(Graphics g)
		{
			plotWidth = (int) g.getClipBounds().getWidth();
			plotHeight = (int) g.getClipBounds().getHeight();
			
			int dX = x -  (plotWidth/2);
			int dY = y -  (plotHeight/2);
			
			int[] listX = {dX+180, dX+70,dX+290};
			int[] listY = {dY, dY+100, dY+100};
			
			Graphics2D g2 = (Graphics2D) g;
			g.setColor(c);
			g.drawPolygon(listX,listY,listX.length);
			g2.fillPolygon(listX,listY,listX.length);
			
		}



	}
