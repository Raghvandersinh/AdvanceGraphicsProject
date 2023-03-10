package advanceGraphics;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class NodePlot extends JComponent {
	
	private ArrayList<Node> listNode = new ArrayList<>();

	public NodePlot() {

	}
	
	public void paintComponent(Graphics g) {
		for (Node item : listNode) {
			item.draw(g);
		}
	}
	
	public void addItem(Node node) {
		listNode.add(node);
		this.repaint();
	}
	public int getLength()
	{
		return listNode.size();
	}
	
	public void removeItem(int index) {
		listNode.remove(index);
		this.repaint();
	}
	
	public void clear() {
		listNode.clear();
		this.repaint();
	}
	
	public ArrayList<Node> getList() {
		return listNode;
	}
	
	public int length()
	{
		return listNode.size();
	}
	
}
