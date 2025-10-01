package com.minirpg;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;

	// GPT
	
	public boolean interactPressed;
    private boolean interactPressedPrev;

    public boolean closePressed;
    private boolean closePressedPrev;

    	// Call this at the start of each update
    public void update() {
    	interactPressedPrev = interactPressed;
        closePressedPrev = closePressed;
        // interactPressed stays true if E key is pressed; false otherwise
        // This is handled in keyPressed / keyReleased
    }

    public boolean justPressedInteract() {
        return interactPressed && !interactPressedPrev;
    }
	
	public boolean justPressedClose() {
	    return closePressed && !closePressedPrev;
	}
	//
	
	@Override
	public void keyTyped(KeyEvent e) {
		// Unused
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        
        // GPT
        if (code == KeyEvent.VK_E) interactPressed = true;
        if (code == KeyEvent.VK_Q) closePressed = true; // Q closes dialogue
        //
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		//GPT
		System.out.println("Key pressed: " + e.getKeyCode());
		//
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        
        // GPT
        if (code == KeyEvent.VK_E) interactPressed = false;
        if (code == KeyEvent.VK_Q) closePressed = false;
        //
    }
}
