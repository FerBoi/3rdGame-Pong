/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;
import static controller.GameConst.Size.BOARD_SIZE;
import static controller.GameConst.INITIAL_BALL_SPEED;

/**
 *
 * @author Fernando GJ
 */
public class Ball extends Rectangle implements ItselfAction {
    private int xVelocity;
    private int yVelocity;

    public Ball(Point initialCoords, Dimension dimensions) {
        super(initialCoords.x, initialCoords.y, dimensions.width, dimensions.height);
        
        // initial ball movement
        Random r = new Random();
        this.xVelocity = r.nextInt(2);
        
        if(this.xVelocity == 0)
            this.xVelocity--;
        
        this.xVelocity*= INITIAL_BALL_SPEED;
        
        this.yVelocity = r.nextInt(2);
        
        if(this.yVelocity == 0)
            this.yVelocity--;
        
        this.yVelocity*= INITIAL_BALL_SPEED;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }
    
    @Override
    public void update() {
        if(this.y < -1 || (this.y + this.height) > BOARD_SIZE.height)
            this.yVelocity*= -1;
        
        this.x += this.xVelocity;
        this.y += this.yVelocity;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(this.x, this.y, this.width, this.height);
    }

} // end Ball
