import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menu extends JFrame {
    Buttons buttons;

    public Menu() {
        buttons = new Buttons(this);
        add(buttons);

        setResizable(false);
        pack();
        setTitle("Tron - menu");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setFocusable(true);
        addKeyListener(new KAdapter());
    }

    private class KAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            buttons.board.keyPressed(e);
        }
    }
}
