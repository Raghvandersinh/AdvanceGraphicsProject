package advanceGraphics;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;

public class AppWindow {

	private JFrame frame;
	private NodePlot plot;
	private JList<String> jList;
	private ArrayList<String> storeJList = new ArrayList<>();
	private DefaultListModel<String> model = new DefaultListModel<String>();

	/**
	 * Create the application.
	 */
	public AppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame("Advanced Graphics");
		plot = new NodePlot();

		frame.setResizable(false);
		frame.setBounds(100, 100, 537, 590);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		;

		JScrollPane scrollPane = new JScrollPane(plot);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));

		scrollPane.setBounds(119, 8, 403, 403);

		frame.getContentPane().add(scrollPane);

		JButton btnGrid = new JButton("Add Grid");
		btnGrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NodeGrid node = new NodeGrid();
				plot.addItem(node);
				model.addElement(node.toString(0, 0, 0));
				jList.setModel(model);
			}
		});
		btnGrid.setBounds(12, 28, 97, 23);
		frame.getContentPane().add(btnGrid);

		JButton btnOrigin = new JButton("Add Origin");
		btnOrigin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NodeOrigin node = new NodeOrigin();
				plot.addItem(node);
				model.addElement(node.toString(0, 0, 0));
				jList.setModel(model);
			}
		});
		btnOrigin.setBounds(12, 60, 97, 23);
		frame.getContentPane().add(btnOrigin);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				plot.clear();
				clearJList();
			}
		});
		btnClear.setBounds(12, 230, 97, 23);
		frame.getContentPane().add(btnClear);

		JButton btnAddRandom = new JButton("Add 10");
		btnAddRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawRandomShapes(10);
			}
		});
		btnAddRandom.setBounds(12, 94, 97, 23);
		frame.getContentPane().add(btnAddRandom);

		JButton btnAdd = new JButton("Add 1000");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawRandomShapes(1000);

			}
		});
		btnAdd.setBounds(12, 128, 97, 23);
		frame.getContentPane().add(btnAdd);

		JButton btn_Recolor = new JButton("Recolor");
		btn_Recolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recolor();
			}
		});
		btn_Recolor.setBounds(12, 162, 97, 23);
		frame.getContentPane().add(btn_Recolor);

		JButton btn_Remove = new JButton("Remove Specific");
		btn_Remove.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btn_Remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeJList();
			}
		});
		btn_Remove.setBounds(12, 388, 97, 23);
		frame.getContentPane().add(btn_Remove);

		JPanel panel = new JPanel();
		panel.setBounds(12, 412, 501, 108);
		panel.setLayout(new BorderLayout(0, 0));

		jList = new JList<String>();
		jList.setBounds(12, 218, 200, 138);

		JScrollPane scrollPane_1 = new JScrollPane(jList);
		scrollPane_1.setViewportView(jList);
		jList.setLayoutOrientation(JList.VERTICAL);

		panel.add(scrollPane_1);

		frame.getContentPane().add(panel);

		JButton btn_RemoveLast = new JButton("Remove Last");
		btn_RemoveLast.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btn_RemoveLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeLastJList();
			}
		});
		btn_RemoveLast.setBounds(12, 196, 97, 23);
		frame.getContentPane().add(btn_RemoveLast);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		JMenuItem InputFromFile = new JMenuItem("Import Shapes From File");
		InputFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(".");

				int userPicked = fileChooser.showOpenDialog(null);
				if (userPicked == JFileChooser.APPROVE_OPTION) {
					try {

						fileShapeData(new File(fileChooser.getSelectedFile().getAbsolutePath()));

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						System.err.println("File Unavailable: " + e1.getMessage());
					}
				}
			}
		});
		mnNewMenu.add(InputFromFile);

		mnNewMenu.add(mntmExit);
		// drawHouse();
		// Color c = new Color(255,223,12);
		// plot.addItem(new NodeHouse(17,17,300,300,20, c));

	}

	/**
	 * Draw out random shapes in the Plot and stores them in the JList.
	 * 
	 * @param count - adds the number of random shapes to the Plot and JList
	 */
	private void drawRandomShapes(int count) {

		Random generator = new Random();

		for (int i = 0; i < count; i++) {

			// Create random X and Y locations
			int x = generator.nextInt(601) - 200;
			int y = generator.nextInt(601) - 200;
			int z = generator.nextInt(601) - 200;
			// Set a random radius or height/width
			int r = generator.nextInt(201);
			int w = generator.nextInt(401);
			int h = generator.nextInt(401);
			// Set a random Triangle points

			int red = 0;
			int green = 0;
			int blue = 0;

			int redWindow = 0;
			int greenWindow = 0;
			int blueWindow = 0;

			int redDoor = 0;
			int greenDoor = 0;
			int blueDoor = 0;

			Color c = new Color(0, 0, 0);
			Color c2 = new Color(0, 0, 0);
			Color c3 = new Color(0, 0, 0);

			// Create random color components (0-255)
			for (int j = 0; j < 3; j++) {
				red = generator.nextInt(128) + 128;
				green = generator.nextInt(128) + 128;
				blue = generator.nextInt(128) + 128;
				if (j == 0) {
					c3 = new Color(red, green, blue);
					redWindow = red;
					greenWindow = green;
					blueWindow = blue;
				} else if (j == 1) {
					c2 = new Color(red, green, blue);
					redDoor = red;
					greenDoor = green;
					blueDoor = blue;
				} else {
					c = new Color(red, green, blue);
				}
			}
			// Create Random House Plots
			int houseX = generator.nextInt(101);
			int houseY = generator.nextInt(101);
			int houseR = generator.nextInt(10) + 20;
			int houseW = generator.nextInt(101) + 200;
			int houseH = generator.nextInt(101) + 200;

			if (generator.nextBoolean()) {
				plot.addItem(new NodeCircle(x, y, r, c));
				model.add(i, new NodeCircle(x, y, r, c).toString(red, green, blue));

			} else if (generator.nextBoolean()) {
				plot.addItem(new NodeTriangle(x, y, z, c));
				model.add(i, new NodeTriangle(x, y, z, c).toString(red, green, blue));

			} else if (generator.nextBoolean()) {
				plot.addItem(new NodeHouse(houseX, houseY, houseW, houseH, houseR, c, c2, c3));
				model.add(i, new NodeHouse(houseX, houseY, houseW, houseH, houseR, c, c2, c3).toStringHouse(red, green,
						blue, redWindow, greenWindow, blueWindow, redDoor, greenDoor, blueDoor));
			} else {
				plot.addItem(new NodeRectangle(x, y, w, h, c));
				model.add(i, new NodeRectangle(x, y, w, h, c).toString(red, green, blue));
			}
		}

		for (int i = 0; i < count; i++) {
			storeJList.add((String) model.get(i));
		}
		model.clear();
		model.addAll(storeJList);
		jList.setModel(model);

	}

	private void removeLastJList() {

		int lastIndex = plot.length() - 1;
		if (lastIndex != -1) {
			model.remove(lastIndex);
			plot.removeItem(lastIndex);
			storeJList.remove(lastIndex);
		}

	}

	/**
	 * Removes specific shape from the JList and the Plot.
	 */
	private void removeJList() {

		int selectedIndex = jList.getSelectedIndex();

		if (selectedIndex != -1) {
			model.remove(selectedIndex);
			plot.removeItem(selectedIndex);
			storeJList.remove(selectedIndex);
		}

	}

	/**
	 * Clears all shape from the JList and Plot
	 */
	private void clearJList() {
		model.removeAllElements();
		jList.setModel(model);
	}

	/**
	 * Recolors all the shapes in the Plot and change the color portion of the
	 * JList.
	 */
	private void recolor() {

		Random generator = new Random();
		model.clear();

		for (Node node : plot.getList()) {

			int red = 0;
			int blue = 0;
			int green = 0;

			int redWindow = 0;
			int greenWindow = 0;
			int blueWindow = 0;

			int redDoor = 0;
			int greenDoor = 0;
			int blueDoor = 0;

			Color c = new Color(0, 0, 0);
			Color c2 = new Color(0, 0, 0);
			Color c3 = new Color(0, 0, 0);

			// Create random color components (0-255)
			for (int j = 0; j < 3; j++) {
				red = generator.nextInt(128) + 128;
				green = generator.nextInt(128) + 128;
				blue = generator.nextInt(128) + 128;
				if (j == 0) {
					c3 = new Color(red, green, blue);
					redWindow = red;
					greenWindow = green;
					blueWindow = blue;
					node.c3 = c3;
				} else if (j == 1) {
					c2 = new Color(red, green, blue);
					redDoor = red;
					greenDoor = green;
					blueDoor = blue;
					node.c2 = c2;
				} else {
					c = new Color(red, green, blue);
					node.c = c;
				}
			}
			if (node instanceof NodeHouse) {
				model.addElement(node.toStringHouse(red, green, blue, redWindow, greenWindow, blueWindow, redDoor,
						greenDoor, blueDoor));
			} else {
				model.addElement(node.toString(red, green, blue));
			}

		}
		jList.setModel(model);
		plot.repaint();
	}

	private void fileShapeData(File filename) throws FileNotFoundException {

		String circ = "circle";
		String rect = "rectangle";
		String tri = "triangle";
		String house = "house";

		try {
			Scanner inputFile = new Scanner(filename);

			try {

				while (inputFile.hasNextLine()) {

					String line = inputFile.nextLine();
					try (Scanner lineScan = new Scanner(line)) {
						lineScan.useDelimiter(",");

						while (!lineScan.hasNextInt()) {
							String shape = lineScan.next();

							if (shape.equalsIgnoreCase(circ)) {
								// int x, int y, int r, int red, int green, int blue
								int x = lineScan.nextInt();
								int y = lineScan.nextInt();
								int r = lineScan.nextInt();
								int red = lineScan.nextInt();
								int green = lineScan.nextInt();
								int blue = lineScan.nextInt();
								Color c = new Color(red, green, blue);

								plot.addItem(new NodeCircle(x, y, r, c));
								model.addElement(new NodeCircle(x, y, r, c).toString(red, green, blue));
								jList.setModel(model);
								break;
							} else if (shape.equalsIgnoreCase(rect)) {
								// int x, int y, int w, int h, int red, int green, int blue
								int x = lineScan.nextInt();
								int y = lineScan.nextInt();
								int w = lineScan.nextInt();
								int h = lineScan.nextInt();
								int red = lineScan.nextInt();
								int green = lineScan.nextInt();
								int blue = lineScan.nextInt();
								Color c = new Color(red, green, blue);
								plot.addItem(new NodeRectangle(x, y, w, h, c));
								model.addElement(new NodeRectangle(x, y, w, h, c).toString(red, green, blue));
								jList.setModel(model);
								break;
							} else if (shape.equalsIgnoreCase(tri)) {
								int x = lineScan.nextInt();
								int y = lineScan.nextInt();
								int z = lineScan.nextInt();
								int red = lineScan.nextInt();
								int green = lineScan.nextInt();
								int blue = lineScan.nextInt();
								Color c = new Color(red, green, blue);

								plot.addItem(new NodeTriangle(x, y, z, c));
								model.addElement(new NodeTriangle(x, y, z, c).toString(red, green, blue));
								jList.setModel(model);
								break;
							} else if (shape.equalsIgnoreCase(house)) {
								int x = lineScan.nextInt();
								int y = lineScan.nextInt();
								int w = lineScan.nextInt();
								int h = lineScan.nextInt();
								int r = lineScan.nextInt();

								int red = lineScan.nextInt();
								int green = lineScan.nextInt();
								int blue = lineScan.nextInt();

								int redWindow = lineScan.nextInt();
								int greenWindow = lineScan.nextInt();
								int blueWindow = lineScan.nextInt();

								int redDoorRoof = lineScan.nextInt();
								int greenDoorRoof = lineScan.nextInt();
								int blueDoorRoof = lineScan.nextInt();

								Color c = new Color(red, green, blue);
								Color c2 = new Color(redWindow, greenWindow, blueWindow);
								Color c3 = new Color(redDoorRoof, greenDoorRoof, blueDoorRoof);

								plot.addItem(new NodeHouse(x, y, w, h, r, c, c2, c3));
								model.addElement(new NodeHouse(x, y, w, h, r, c, c2, c3).toStringHouse(red, green, blue,
										redWindow, greenWindow, blueWindow, redDoorRoof, greenDoorRoof, blueDoorRoof));
								jList.setModel(model);
								break;
							} else {
								System.out.println("error");
								break;
							}

						}
					}

				}

			} catch (NumberFormatException e) {
				System.err.println("Data Error: " + e.getMessage());
			} catch (IndexOutOfBoundsException e) {
				System.err.println("Parse Error: " + e.getMessage());
			} catch (NoSuchElementException e) {
				System.err.println("Record Error: " + e.getMessage());
			} finally {
				inputFile.close();
			}

		} catch (FileNotFoundException e) {
			System.err.println("File Unavaiable: " + e.getMessage());
		}

	}

	public void setVisible(boolean b) {
		if (b) {
			frame.setVisible(true);
		} else {
			frame.setVisible(false);
		}
	}
}
