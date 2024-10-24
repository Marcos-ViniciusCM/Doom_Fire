package main;

import java.awt.Dimension;
import java.awt.Window;

import javax.swing.*;

public class Main {


	public static void main(String[] args) {
	JPanel panel = new JPanel();
	JPanel buttonPanel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
	JFrame windows = new JFrame();
	
	JButton debug = new JButton("Debug");
	JButton more = new JButton("More Fire");
	JButton less = new JButton("Less Fire");
	JButton normal = new JButton("Normal Fire");
	
	GamePanel painel = new GamePanel();
	panel.add(painel);
	panel.add(buttonPanel);
	
	
	buttonPanel.add(debug);
	buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); 
    debug.addActionListener(e -> GamePanel.debugMode = !GamePanel.debugMode);
    
    buttonPanel.add(more);
    buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); 
    more.addActionListener(e -> GamePanel.fireDecay = 2);
    
    buttonPanel.add(less);
    buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
	less.addActionListener(e -> GamePanel.fireDecay = 4);
	
	buttonPanel.add(normal);
	buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); 
    normal.addActionListener(e -> GamePanel.fireDecay = 3);
	
	windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	windows.setSize(390,490);
	windows.setTitle("Doom Fire");
	windows.add(panel);
	windows.pack();
	windows.setLocationRelativeTo(null);
	windows.setVisible(true);
	painel.startGameThread();
		
	}
	
	

}
