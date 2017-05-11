package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
 
public class MainGameField extends JPanel {
    private static MainGameField instance = null;
    public static final int FIELD_SIZE = 750;
    public final int NOT_SIGN = 0;
    boolean gameOver = false;
    String gameOverMessage = "";
    int cellSize;
    int x;
    int y;
    //boolean nextTurn = false;
    public int[][] cell;
    public static synchronized MainGameField getInstance() {
        if (instance == null) instance = new MainGameField();
        return instance;
    }
    void startNewGame() {
        gameOver = false;
        gameOverMessage = "";
        cellSize = FIELD_SIZE / 3;
        cell = new int[3][3];
        repaint();
        for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 3; j++) 
                cell[i][j] = NOT_SIGN;
        setVisible(true);
    }
     
    private MainGameField() {
        setVisible(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                x = e.getX() / cellSize;
                y = e.getY() / cellSize;
 
                if (!gameOver)  modeAgainstAI();
                
            }
        });
    }
    
    void modeAgainstAI() {
        Player player = new Player(1);
        PC ai = new PC(2,player.sign);
        if (!gameOver) {
            if (player.shot(x, y)) {
                if (player.win()) {
                    gameOver = true;
                    gameOverMessage = "ÒÛ ÂÛÈÃÐÀË;)";
                }
                if (isFieldFull()) {
                    gameOver = true;
                    gameOverMessage = "ÍÈ×Üß:|";
                }
                repaint();
                if (!gameOver) ai.steap(x, y);
                if (ai.win()) {
                    gameOver = true;
                    gameOverMessage = "ÒÛ ÏÐÎÈÃÐÀË:(";
                }
                repaint();
            }
        }
    }
 
    boolean isCellBusy(int x, int y) {
        return cell[x][y] != NOT_SIGN;
    }
 
    public boolean isFieldFull() {
        for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 3; j++)
            	if (cell[i][j] == NOT_SIGN) return false;
        return true;
    }
 
    public boolean checkLine(int start_x, int start_y, int dx, int dy, int sign) {
        for (int i = 0; i < 3; i++) 
            if (cell[start_x + i * dx][start_y + i * dy] != sign) return false;
        return true;
    }
 
    public boolean checkWin(int sign) {
        for (int i = 0; i < 3; i++) {
            if (checkLine(i, 0, 0, 1, sign)) return true;
            if (checkLine(0, i, 1, 0, sign)) return true;
        }
        if (checkLine(0, 0, 1, 1, sign)) return true;
        if (checkLine(0, 2, 1, -1, sign)) return true;
        return false;
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i <= 3; i++) {
            g.drawLine(0, i * this.cellSize, FIELD_SIZE, i * this.cellSize);
            g.drawLine(i * this.cellSize, 0, i * this.cellSize, FIELD_SIZE);
        }
        for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 3; j++) 
                if (cell[i][j] != NOT_SIGN) { 
                    if (cell[i][j] == 1) {
                        g.setColor(Color.black);
                        g.drawLine((i * cellSize)+40, (j * cellSize), (i + 1) * cellSize, (j + 1) * cellSize-40);
                        g.drawLine((i * cellSize)+30, (j * cellSize), (i + 1) * cellSize, (j + 1) * cellSize-30);
                        g.drawLine((i * cellSize)+20, (j * cellSize), (i + 1) * cellSize, (j + 1) * cellSize-20);
                        g.drawLine((i * cellSize)+10, (j * cellSize), (i + 1) * cellSize, (j + 1) * cellSize-10);
                        g.drawLine((i * cellSize), (j * cellSize), (i + 1) * cellSize, (j + 1) * cellSize);
                        g.drawLine((i + 1) * cellSize, (j * cellSize), (i * cellSize), (j + 1) * cellSize);
                        g.drawLine((i + 1) * cellSize-10, (j * cellSize), (i * cellSize), (j + 1) * cellSize-10);
                        g.drawLine((i + 1) * cellSize-20, (j * cellSize), (i * cellSize), (j + 1) * cellSize-20);
                        g.drawLine((i + 1) * cellSize-30, (j * cellSize), (i * cellSize), (j + 1) * cellSize-30);
                        g.drawLine((i + 1) * cellSize-40, (j * cellSize), (i * cellSize), (j + 1) * cellSize-40);
                    }
                    if (cell[i][j] == 2) {
                        g.setColor(Color.MAGENTA);
                        g.fillOval((i * cellSize)+50, (j * cellSize)+50, cellSize-100, cellSize-100);
                        g.drawOval((i * cellSize)+40, (j * cellSize)+40, cellSize-80, cellSize-80);
                        g.drawOval((i * cellSize)+30, (j * cellSize)+30, cellSize-60, cellSize-60);                
                        g.drawOval((i * cellSize)+20, (j * cellSize)+20, cellSize-40, cellSize-40);
                        g.drawOval((i * cellSize)+10, (j * cellSize)+10, cellSize-20, cellSize-20);
                        g.drawOval((i * cellSize), (j * cellSize), cellSize, cellSize);
                    }
        }
 
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Century Schoolbook", Font.ITALIC, 40));
            g.drawString(gameOverMessage, 280, 19 * FIELD_SIZE / 32);
        }
    }
}