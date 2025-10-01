package com.minirpg;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;

public class GamePanel extends JPanel implements Runnable {
	
	// Screen settings
	
	final int originalTileSize = 16; // 16x16 px tiles
	final int scale = 3;
	final int tileSize = originalTileSize * scale; // 48x48 px tiles
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 768 px
	final int screenHeight = tileSize * maxScreenRow; // 576 px
	
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int maxWorldWidth = tileSize * maxWorldCol;
	public final int maxWorldHeight = tileSize * maxWorldRow;
	
	int FPS = 60;
	
	// GPT
	
	Font pixelFont;
	
	boolean dialogueActive = false;
	String dialogueText = "";
	//
	
	TileManager tileM = new TileManager(this);
	
	InputHandler keyH = new InputHandler();
	
	Thread gameThread;
	
	Player player = new Player(this,keyH);
	
	// GPT
	Skeleton skeleton;
	
	public boolean playerNear(Skeleton s) {
	    int dx = Math.abs(player.x - s.x);
	    int dy = Math.abs(player.y - s.y);

	    System.out.println("dx=" + dx + ", dy=" + dy); // debug distance

	    int margin = 100; // large margin for testing
	    boolean near = dx < margin && dy < margin;
	    System.out.println("near=" + near);
	    return near;
	}
	//
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // improves rendering performance
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
		//GPT
		this.requestFocusInWindow();
		
		skeleton = new Skeleton(450, 300);
		
		pixelFont = new Font("Monospaced", Font.PLAIN, 20); // simple built-in option
		//
	}

	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();		
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;			
			}					
		}	
	}
	
	public void update() {  // X increases going right, Y increases going down
		
		player.update();
		
		// GPT
		skeleton.update();
		
		keyH.update();

		// Open dialogue when near skeleton and E is pressed
		if (!dialogueActive && playerNear(skeleton) && keyH.interactPressed) {
		    System.out.println("Dialogue triggered!");
		    startDialogue("Orc: Sadly, you are not ready to battle yet.");
		}

		// Close dialogue when Q is pressed
		if (dialogueActive && keyH.closePressed) {
		    System.out.println("Dialogue closed");
		    dialogueActive = false;
		}
		//
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent (g);
		
		Graphics2D g2 = (Graphics2D)g;

		tileM.draw(g2);
		
		player.draw(g2);
		
		// GPT
		skeleton.draw(g2, tileSize);
		
		if (dialogueActive) {
		    g2.setColor(Color.black);
		    g2.fillRect(50, screenHeight - 100, screenWidth - 100, 60);
		    g2.setColor(Color.white);
		    g2.drawRect(50, screenHeight - 100, screenWidth - 100, 60);

		    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF); // pixel look
		    g2.setFont(pixelFont);
		    g2.drawString(dialogueText, 70, screenHeight - 60);
		}
		
		g2.dispose();
	}
	
	public void startDialogue(String text) {
	    dialogueText = text;
	    dialogueActive = true; // set dialogueActive to true when starting
	}
		//
}
