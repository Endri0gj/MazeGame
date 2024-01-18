import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeGameGUI extends JFrame {

    private KontrollerLojes gameController;
    private JButton[][] buttons;

    public MazeGameGUI() {
        gameController = new KontrollerLojes();
        buttons = new JButton[10][10];

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Maze Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(12, 10));

        // Initialize buttons
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setPreferredSize(new Dimension(50, 50));
                buttons[i][j].setEnabled(false);
                add(buttons[i][j]);
            }
        }

        updateButtons();

        JButton upButton = new JButton("Up");
        JButton downButton = new JButton("Down");
        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");


        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameController.hasValidPath()) {
                    movePlayer('1');
                } else {
                    JOptionPane.showMessageDialog(MazeGameGUI.this, "No valid path. Game over!");
                    dispose();
                }
            }
        });

        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameController.hasValidPath()) {
                    movePlayer('2');
                } else {
                    JOptionPane.showMessageDialog(MazeGameGUI.this, "No valid path. Game over!");
                    dispose();
                }
            }
        });

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameController.hasValidPath()) {
                    movePlayer('3');
                } else {
                    JOptionPane.showMessageDialog(MazeGameGUI.this, "No valid path. Game over!");
                    dispose();
                }
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameController.hasValidPath()) {
                    movePlayer('4');
                } else {
                    JOptionPane.showMessageDialog(MazeGameGUI.this, "No valid path. Game over!");
                    dispose();
                }
            }
        });


        add(new JLabel());
        add(upButton);
        add(new JLabel());
        add(leftButton);
        add(new JLabel());
        add(rightButton);
        add(new JLabel());
        add(downButton);
        add(new JLabel());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void movePlayer(char direction) {
        gameController.movePlayer(direction);

        if (gameController.isHitWall()) {
            JOptionPane.showMessageDialog(this, "You hit a wall. Game over!");
            dispose();
        } else {
            updateButtons();
            if (gameController.kaFituar()) {
                JOptionPane.showMessageDialog(this, "Congratulations! You won!");
                dispose();
            }
        }
    }
    private void updateButtons() {
        int[][] maze = gameController.getLabirint().getRrjeti();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (maze[i][j] == 1) {
                    buttons[i][j].setBackground(Color.BLACK);
                } else if (maze[i][j] == 7) {
                    buttons[i][j].setBackground(Color.BLUE);
                } else if (maze[i][j] == 8) {
                    buttons[i][j].setBackground(Color.GREEN);
                } else if (maze[i][j] == 9) {
                    buttons[i][j].setBackground(Color.YELLOW);
                } else {
                    buttons[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MazeGameGUI());
    }
}
