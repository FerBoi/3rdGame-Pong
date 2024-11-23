/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static controller.GameConst.Movement.*;

/**
 *
 * @author Fernando GJ
 */
public class InputListener implements KeyListener {
    private final Game GAME;

    public InputListener(Game controller) {
        this.GAME = controller;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean playerOneMovement = true;
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                if(e.getKeyCode() != KeyEvent.VK_W)
                    playerOneMovement = false;
                
                this.GAME.playerMove(UP, playerOneMovement);
            } case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                if(e.getKeyCode() != KeyEvent.VK_S)
                    playerOneMovement = false;
                
                this.GAME.playerMove(DOWN, playerOneMovement);
            } case KeyEvent.VK_SPACE -> this.GAME.setGameStarted(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.GAME.playerMove(NO_MOVEMENT, false);
    }

} // end InputListener
