import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] args) {
        ImageIcon snakeicon = new ImageIcon("C:\\Users\\anshi\\OneDrive\\Desktop\\SnakeGame\\snakeicon.png");

        JFrame frame = new JFrame("Snake Game");
        frame.setBounds(10,10,905,700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(snakeicon.getImage());

        Panel  panel = new Panel();
        panel.setBackground(Color.ORANGE);
        frame.add(panel);
        
        frame.setVisible(true);
    }
}