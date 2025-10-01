
// GPT

package com.minirpg;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Skeleton extends Entity {

    public boolean alive = true;
    public Rectangle solidArea;

    public Skeleton(int x, int y) {
        
    	this.x = x;
        this.y = y;
        this.direction = "down";
        loadImages();
        // collision box, adjust size as needed
        solidArea = new Rectangle(x, y, 48, 48); 
    }

    private void loadImages() {
        
    	try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/skeleton_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/skeleton_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (!alive) return;

        spriteCounter++;
        if (spriteCounter > 20) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }

        // update collision box position
        solidArea.x = x;
        solidArea.y = y;
    }

    public void draw(Graphics2D g2, int tileSize) {
        if (!alive) return;

        BufferedImage image = (spriteNum == 1) ? down1 : down2;
        g2.drawImage(image, x, y, tileSize, tileSize, null);
    }
}
	//
