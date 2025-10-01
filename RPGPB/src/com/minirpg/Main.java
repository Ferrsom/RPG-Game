package com.minirpg;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame(); // new instance of JFrame
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("RPGPB Adventure");
		
		GamePanel gamePanel = new GamePanel(); // new instance of custom GamePanel
		window.add(gamePanel); // adding to this window
		window.pack(); // resize window to panel size
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
	}
}
