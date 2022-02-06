import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

//This class creates a frame with a menu and panel to display various 
public class FractalFrame extends JFrame implements ActionListener{
	
	//Class wide instance variables
	
	//menubar
	private JMenuBar menubar = new JMenuBar();
	private JMenu fractalMenu = new JMenu("Fractal");
	private JMenuItem[] fractalArray = new JMenuItem[5];
	
	//Panel
	private JPanel controlJPanel = new JPanel();
	private FractalJPanel fractalJPanel = new FractalJPanel(0);
	
	//Buttons and labels for the control panel
	private JButton changeColorJButton = new JButton("Color");
	private JButton increaseLevelJButton = new JButton("Increase Level");
	private JButton decreaseLevelJButton = new JButton ("Decrease Level");
	private JLabel levelJLabel = new JLabel("Level: 0");
	
	//Constants to limit the levels of recursion
	private static final int MIN_LEVEL=0, MAX_LEVEL = 40;
	
	//Tracks the current fractal type
	public static String fractalType = "";
	
	//Constructor Method used to setup the frame and its contents
	public FractalFrame() {
		
		//Setup the frame
		setLayout(new FlowLayout());
		setTitle("Speddy Fractal designs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,800);
		
		//Setup the menu
		fractalArray[0] = new JMenuItem("Lo Fractal");
		fractalArray[1] = new JMenuItem("Lo Star Fractal");
		fractalArray[2] = new JMenuItem("Dragon Curve");
		fractalArray[3] = new JMenuItem("Sierpinkski Triangle");
		fractalArray[4] = new JMenuItem("Koch Snowflake");
		
		//add the menu items to the menu
		for(JMenuItem item: fractalArray) {
			fractalMenu.add(item);
			item.addActionListener(this);
		}
		
		//add the menubar to the menu, set the menubar for the frame
		menubar.add(fractalMenu);
		setJMenuBar(menubar);
		
		//Setup the control buttons
		setupControlButtons();
		
		//Add the level label to the control panel
		controlJPanel.add(levelJLabel);
		
		//Add the panels to the frame
		add(controlJPanel);
		add(fractalJPanel);
		
		//Make the frame appear
		setVisible(true);
	}
	
	private void setupControlButtons() {
		
		//Setup the button to manage the color chooser
		controlJPanel.add(changeColorJButton);
		
		changeColorJButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						
						Color color = JColorChooser.showDialog(
								FractalFrame.this, "Choose a color", Color.BLUE);
						
						if(color == null)
							color=Color.blue;
						
						fractalJPanel.setColor(color);
					}
				}
			);
		
		//Setup the button to manage the level decrease
				controlJPanel.add(decreaseLevelJButton);
				
				decreaseLevelJButton.addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								
								int level = fractalJPanel.getLevel();
								--level;
								
								if((level>=MIN_LEVEL) && (level<=MAX_LEVEL)) {
									levelJLabel.setText("Level: "+level);
									fractalJPanel.setLevel(level);
									repaint();
								}
							}
						}
					);
				
			//Setup the button to manage the level increase
				controlJPanel.add(increaseLevelJButton);
				
				increaseLevelJButton.addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								
								int level = fractalJPanel.getLevel();
								++level;
								
								if((level>=MIN_LEVEL) && (level<=MAX_LEVEL)) {
									levelJLabel.setText("Level: "+level);
									fractalJPanel.setLevel(level);
									repaint();
								}
							}
						}
					);
				
			
	}

	//This method is used to manage the menu actions based on the user's
	@Override
	public void actionPerformed(ActionEvent event) {
		
		//IF the user selects a fractal from the menu then
		//set the fractal type, reset the level to 0, and repaint the frame
		if(event.getSource() instanceof JMenuItem) {
			
			JMenuItem menuItem = (JMenuItem) event.getSource();
			fractalType = menuItem.getText();
			levelJLabel.setText("Level: 0");
			fractalJPanel.setLevel(0);
			repaint();
			
		}
		
	}

	

}
