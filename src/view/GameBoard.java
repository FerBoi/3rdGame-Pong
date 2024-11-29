/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package view;

import controller.GameConst;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

import static controller.GameConst.Text.*;

/**
 *
 * @author Fernando GJ
 */
public class GameBoard extends JPanel {
    private MainWindow mainWindow;
    private String[] scoreView = {"00", "00"};
    private Font scoreFont;
    private Font textFont;
    private final Stroke DASHED = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 
                0, new float[] {10, 10}, 0);
    
    public GameBoard() {
        this.setPreferredSize(GameConst.Size.BOARD_SIZE);
        this.setBackground(Color.black);
        
        try {
            this.scoreFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/res/pong-score.ttf"));
            this.textFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/res/arcade-classic.ttf"));
            
            this.scoreFont = this.scoreFont.deriveFont(36f);
            this.textFont = this.textFont.deriveFont(36f);
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    
    public void incrementPoint(boolean playerOneScore, int newScore) {
        this.scoreView[playerOneScore ? 0 : 1] = String.format("%02d", newScore);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
       
        
        g2d.setColor(Color.WHITE);
        
        g2d.setStroke(this.DASHED);
        g2d.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight()); // field division
        
        if (!this.mainWindow.isGameStarted() || this.mainWindow.isGameFinished()) {
            g2d.setFont(this.textFont);
            g2d.drawString(!this.mainWindow.isGameStarted() ? BEFORE_STARTING : String.format(AFTER_MATCH, this.mainWindow.whoWon()), this.getWidth() / 4, this.getHeight() / 2);
            
            if(this.mainWindow.isGameFinished()) {
                g2d.drawString(AFTER_MATCH_ONE, this.getWidth() / 4, this.getHeight() / 2 + 50);
                this.scoreView[0] = "00";
                this.scoreView[1] = "00";
            }
            
            return;
        }

        this.mainWindow.renderGame(g);
        
        g2d.setFont(this.textFont);
        g2d.drawString(POINTS_TO_WIN, this.getWidth() / 2 - 90, 50);

        g2d.setFont(this.scoreFont);
        g2d.drawString(this.scoreView[0], this.getWidth() / 2 - 100, this.getHeight() / 4);
        g2d.drawString(this.scoreView[1], this.getWidth() / 2 + 45 , this.getHeight() / 4);
    }
    
    
    
    
   
} // end GameBoard
