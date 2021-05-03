import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons extends JButton implements ActionListener {
    private final JButton startButton;
    private final JButton exitButton;

    public Board board;
    public Menu menu;

    public Buttons(Menu menu) {
        this.menu = menu;

        startButton = new JButton("New Game");
        exitButton = new JButton("Exit");

        startButton.addActionListener(this);
        exitButton.addActionListener(this);

        startButton.setPreferredSize(new Dimension(200, 40));
        exitButton.setPreferredSize(new Dimension(200, 40));

        setPreferredSize(new Dimension(400, 400));
        add(startButton);
        add(exitButton);
        setLayout(new FlowLayout(FlowLayout.LEFT, 70, 50));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == exitButton) {
            System.exit(0);
        } else if (source == startButton) {
            board = new Board(this, this.menu);
            menu.add(board);
            menu.setSize(new Dimension(800, 650));
            menu.setTitle("Tron");
            board.initBoard();
            this.setVisible(false);
        }
    }
}
