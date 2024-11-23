/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import java.awt.Color;
import java.awt.Point;
import model.Paddle;
import view.MainWindow;

import static controller.GameConst.Size.*;
import static controller.GameConst.FPS;
import static controller.GameConst.Movement.NO_MOVEMENT;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.SwingUtilities;
import model.Ball;

/**
 *
 * @author Fernando GJ
 */
public class Game implements Runnable {
    private final Thread GAME_THREAD;
    private final MainWindow WINDOW;
    private boolean gameStarted;
    
    // game components 
    private final Paddle[] PLAYERS_PADDLE = new Paddle[2];
    private Ball gameBall;
    private final int[] SCORE = new int[2];
    
    public Game() {
        this.GAME_THREAD = new Thread(this);
        this.WINDOW = new MainWindow(this);
        
        this.WINDOW.setLocationRelativeTo(null);
        this.WINDOW.setVisible(true);
        
        // players paddle initialize
        this.PLAYERS_PADDLE[0] = new Paddle(new Point(10, 20), PADDLE_SIZE, Color.RED);
        this.PLAYERS_PADDLE[1] = new Paddle(new Point(BOARD_SIZE.width - (PADDLE_SIZE.width + 10), BOARD_SIZE.height - PADDLE_SIZE.height - 20), PADDLE_SIZE, Color.BLUE);
        
        // game ball
        this.gameBall = new Ball(new Point((BOARD_SIZE.width / 2) - (BALL_DIAMETER / 2), 
                (BOARD_SIZE.height / 2) - (BALL_DIAMETER / 2)), new Dimension(BALL_DIAMETER, BALL_DIAMETER));
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        if(this.gameStarted)
            return;
        
        this.gameStarted = gameStarted;
        
        if(this.gameStarted)
            this.GAME_THREAD.start();
    }
    
    private void update() {
        this.PLAYERS_PADDLE[0].update();
        this.PLAYERS_PADDLE[1].update();
        this.checkCollision();
        this.gameBall.update();
        
        if(this.gameBall.getMaxX() < -1 || this.gameBall.getMinX() > BOARD_SIZE.width) {
            int index = 0;
            
            if(this.gameBall.getMaxX() < -1)
                index = 1;
            
            this.SCORE[index]++;
            this.WINDOW.incrementScore(this.SCORE[index], index == 0);
            this.gameBall = new Ball(new Point((BOARD_SIZE.width / 2) - (BALL_DIAMETER / 2), 
                (BOARD_SIZE.height / 2) - (BALL_DIAMETER / 2)), new Dimension(BALL_DIAMETER, BALL_DIAMETER));
        }
    }
    
    public void renderGame(Graphics g) {
        this.PLAYERS_PADDLE[0].render(g);
        this.PLAYERS_PADDLE[1].render(g);
        this.gameBall.render(g);
    }
    
    public void checkCollision() {
        if(this.PLAYERS_PADDLE[0].intersects(this.gameBall) || this.PLAYERS_PADDLE[1].intersects(this.gameBall))
            this.gameBall.setxVelocity(this.gameBall.getxVelocity() * -1);
    }
    
    public void playerMove(int movement, boolean playerOneMovement) {
        if(movement == NO_MOVEMENT) {
            this.PLAYERS_PADDLE[0].setCurrentMovement(movement);
            this.PLAYERS_PADDLE[1].setCurrentMovement(movement);
        } else {
            if(playerOneMovement)
                this.PLAYERS_PADDLE[0].setCurrentMovement(movement);
            else
                this.PLAYERS_PADDLE[1].setCurrentMovement(movement);
        }
    }

    @Override
    public void run() {
        double frameDuration = 1000000000 / FPS;
        double deltaTimeFPS = 0;
        
        long frameStart = System.currentTimeMillis();
        long lastLoopIteration = System.nanoTime();
        
        int currentFrames = 0;
        
        while(true) {
            long now = System.nanoTime();
            deltaTimeFPS+= (now - lastLoopIteration) / frameDuration;
            
            lastLoopIteration = now;
            
            if (deltaTimeFPS >= 1) {
                this.update();
                SwingUtilities.invokeLater(() -> this.WINDOW.updateUI());
                currentFrames++;
                deltaTimeFPS--;
            }
            
            // FPS COUNTER
            if(System.currentTimeMillis() - frameStart >= 1000) {
                frameStart = System.currentTimeMillis();
                System.out.println("Metricas Actuales --> FPS: " + currentFrames);
                currentFrames = 0;
            }
        }
    }







} // end Game
