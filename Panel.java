import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener, KeyListener {

    private int[] snakelenx = new int[750];         
    private int[] snakeleny = new int[750];
    private int snakelen = 2;
    private int score = 0;
    private boolean gameOver = false;

    private int[] xpos = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475,
            500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850, 875 };

    private int[] ypos = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525,
            550, 575, 600, 625 };

    private Random random = new Random();
    private int foodx, foody;

    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;

    private int moves = 0;

    private ImageIcon snakebg = new ImageIcon(getClass().getResource("snakebg.png"));
    private ImageIcon leftsnake = new ImageIcon(getClass().getResource("leftsnake.png"));
    private ImageIcon rightsnake = new ImageIcon(getClass().getResource("rightsnake.png"));
    private ImageIcon upsnake = new ImageIcon(getClass().getResource("upsnake.png"));
    private ImageIcon downsnake = new ImageIcon(getClass().getResource("downsnake.png"));
    private ImageIcon snakebody = new ImageIcon(getClass().getResource("snakebody.png"));
    private ImageIcon snakefood = new ImageIcon(getClass().getResource("snakefood.jpg"));
    private ImageIcon snakelogo = new ImageIcon(getClass().getResource("snakelogo.png"));

    private Timer timer;
    private int delay = 100;

    Panel() {

        timer = new Timer(delay, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        newFood();
    }

    @Override
    public void paint(Graphics g) {

        

        super.paint(g);

        g.setColor(Color.BLACK);
        g.drawRect(24, 10, 851, 55);
        g.drawRect(24, 74, 851, 576);

        snakebg.paintIcon(this, g, 25, 11);

        snakelogo.paintIcon(this, g, 25, 75);
        
        if (moves == 0) {
            snakelenx[0] = 100;
            snakelenx[1] = 75;

            snakeleny[0] = 100;
            snakeleny[1] = 100;

        }

        if (left) {
            leftsnake.paintIcon(this, g, snakelenx[0], snakeleny[0]);
        }

        if (right) {
            rightsnake.paintIcon(this, g, snakelenx[0], snakeleny[0]);
        }

        if (up) {
            upsnake.paintIcon(this, g, snakelenx[0], snakeleny[0]);
        }

        if (down) {
            downsnake.paintIcon(this, g, snakelenx[0], snakeleny[0]);
        }

        for (int i = 1; i < snakelen; i++) {
            snakebody.paintIcon(this, g, snakelenx[i], snakeleny[i]);

        }

        snakefood.paintIcon(this, g, foodx, foody);
        EatFood();
        bodyTouch();
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over", 300, 300);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("PRESS SPACE BAR TO RESTART THE GAME", 250, 350);

        }

        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: "+score, 700, 30);
        g.drawString("Moves: "+moves, 700, 50);

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        for (int i = snakelen - 1; i > 0; i--) {
            snakelenx[i] = snakelenx[i - 1];
            snakeleny[i] = snakeleny[i - 1];
        }

        if (left) {
            snakelenx[0] = snakelenx[0] - 25;
        }

        if (right) {
            snakelenx[0] = snakelenx[0] + 25;
        }

        if (up) {
            snakeleny[0] = snakeleny[0] - 25;
        }

        if (down) {
            snakeleny[0] = snakeleny[0] + 25;
        }

        if (snakelenx[0] > 850) {
            // for left
            snakelenx[0] = 25;
        }

        if (snakelenx[0] < 25) {
            // for right
            snakelenx[0] = 850;
        }

        if (snakeleny[0] > 625) {
            // for up
            snakeleny[0] = 75;
        }

        if (snakeleny[0] < 75) {
            // for down
            snakeleny[0] = 625;
        }

        
        

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            restart();
        }


        if (e.getKeyCode() == KeyEvent.VK_LEFT && (!right)) {
            left = true;
            right = false;
            up = false;
            down = false;
            moves++;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && (!left)) {
            left = false;
            right = true;
            up = false;
            down = false;
            moves++;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && (!down)) {
            left = false;
            right = false;
            up = true;
            down = false;
            moves++;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && (!up)) {
            left = false;
            right = false;
            up = false;
            down = true;
            moves++;
        }
    }

    private void restart() {
        gameOver = false;
        moves = 0;
        score = 0;
        snakelen = 2;
        left = false;
        down = false;
        up = false;
        right = true;
        timer.start();
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void newFood() {
        foodx = xpos[random.nextInt(34)];
        foody = ypos[random.nextInt(23)];

        for (int i = snakelen - 1; i >= 0; i--) {
            if (snakelenx[0] == foodx && snakeleny[0] == foody) {
                newFood();
            }
        }
    }

    private void bodyTouch() {

        for(int i=snakelen-1; i>0; i--)
        {
            if(snakelenx[i] == snakelenx[0] && snakeleny[i]==snakeleny[0])
            {
                timer.stop();
                gameOver =true;
            }

        }
    }
    private void EatFood() {
        if (snakelenx[0] == foodx && snakeleny[0] == foody) 
        {
            newFood();
            snakelen++;
            score++;

        }
    }
}
