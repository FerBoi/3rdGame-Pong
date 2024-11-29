/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import java.awt.Dimension;

/**
 *
 * @author Fernando GJ
 */
public class GameConst {
    public final static int FPS = 60;
    public final static int INITIAL_BALL_SPEED = 4;
    public final static int PADDLE_SPEED = 5;
    public final static int POINT_TO_WIN = 11;
    
    public static class Size {

        private static final int BOARD_WIDHT = 1000;
        private static final int BOARD_HEIGHT = (int) (BOARD_WIDHT * 0.55);

        public static final Dimension BOARD_SIZE = new Dimension(BOARD_WIDHT, BOARD_HEIGHT);
        public static final int BALL_DIAMETER = 20;
        public static final Dimension PADDLE_SIZE = new Dimension(15, 100);
        
    }
    
    public static class Movement {
        
        public final static int NO_MOVEMENT = -1;
        public final static int UP = 0;
        public final static int DOWN = 1;
        
    }
    
    public static class Text {
        
        public final static String BEFORE_STARTING = "Press SPACE to start the game";
        public final static String AFTER_MATCH = "Player %s won";
        public final static String AFTER_MATCH_ONE = "Press SPACE to play again";
        public final static String POINTS_TO_WIN = "Best Of " + POINT_TO_WIN;
    }

} // end GameConst
