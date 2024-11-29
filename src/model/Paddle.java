/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import static controller.GameConst.Movement.*;
import static controller.GameConst.Size.BOARD_SIZE;
import static controller.GameConst.PADDLE_SPEED;
import java.awt.Graphics;

/**
 *
 * @author Fernando GJ
 */
public class Paddle extends Rectangle implements ItselfAction {
    private final Color PADDLE_COLOR;
    private int currentMovement = NO_MOVEMENT;

    public Paddle(Point coords, Dimension dimensions, Color backgroundColor) {
        super(coords.x, coords.y, dimensions.width, dimensions.height);
        
        this.PADDLE_COLOR = backgroundColor;
    }

    public void setCurrentMovement(int currentMovement) {
        this.currentMovement = currentMovement;
    }
    
    @Override
    public void update() {
        // position update
        
        if(this.currentMovement == NO_MOVEMENT)
            return;
        
        switch (this.currentMovement) {
            case UP -> {
                if (this.y - PADDLE_SPEED > 0)
                    this.y -= PADDLE_SPEED;
            }
            case DOWN -> {
                if((this.y + this.height) + PADDLE_SPEED < BOARD_SIZE.height)
                    this.y += PADDLE_SPEED;
            }
        }
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(this.PADDLE_COLOR);
        g.fillRect(this.x, this.y, this.width, this.height);
    }

} // end Paddle