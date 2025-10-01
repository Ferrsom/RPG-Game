package com.minirpg;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {

	GamePanel gp;
	InputHandler keyH;
	
	public Player(GamePanel gp, InputHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		x = 200;
		y = 200;
		speed= 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/boy_right_2.png"));
			
		}catch(IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		// SELF
		
		/*
		 	if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
		 

			if(keyH.upPressed == true) {
				direction = "up";
				y -= speed;
			}
		
			else if(keyH.downPressed == true) {
				direction = "down";
				y += speed;
			}
	    
			else if(keyH.leftPressed == true) {
				direction = "left";
				x -= speed;
			}
	    
			else if(keyH.rightPressed == true) {
				direction = "right";
				x += speed;
			}
		
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2 ) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		 */
		
		// GPT
		
		    boolean moving = false;

		    int nextX = x;
		    int nextY = y;
		    
		    // Determine next position
		    if (keyH.upPressed) {
		        direction = "up";
		        nextY -= speed;
		        moving = true;
		    } else if (keyH.downPressed) {
		        direction = "down";
		        nextY += speed;
		        moving = true;
		    } else if (keyH.leftPressed) {
		        direction = "left";
		        nextX -= speed;
		        moving = true;
		    } else if (keyH.rightPressed) {
		        direction = "right";
		        nextX += speed;
		        moving = true;
		    }

		    if (moving) {
		        // Check collision at the four corners
		        int leftTile = nextX / gp.tileSize;
		        int rightTile = (nextX + gp.tileSize - 1) / gp.tileSize;
		        int topTile = nextY / gp.tileSize;
		        int bottomTile = (nextY + gp.tileSize - 1) / gp.tileSize;

		        boolean collision = false;

		        // Top-left corner
		        if (gp.tileM.tile[gp.tileM.mapTileNum[leftTile][topTile]].collision) collision = true;
		        // Top-right corner
		        if (gp.tileM.tile[gp.tileM.mapTileNum[rightTile][topTile]].collision) collision = true;
		        // Bottom-left corner
		        if (gp.tileM.tile[gp.tileM.mapTileNum[leftTile][bottomTile]].collision) collision = true;
		        // Bottom-right corner
		        if (gp.tileM.tile[gp.tileM.mapTileNum[rightTile][bottomTile]].collision) collision = true;

		        // Check skeleton collision
		        Rectangle nextPlayerRect = new Rectangle(nextX, nextY, gp.tileSize, gp.tileSize);
		        if (nextPlayerRect.intersects(gp.skeleton.solidArea)) collision = true;
		        
		        // Only move if no collision
		        if (!collision) {
		            x = nextX;
		            y = nextY;
		        }

		        // Sprite animation
		        spriteCounter++;
		        if (spriteCounter > 12) {
		            spriteNum = (spriteNum == 1) ? 2 : 1;
		            spriteCounter = 0;
		        }
		    }
	}
	//
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}
