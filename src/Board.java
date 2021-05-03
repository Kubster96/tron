import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private Player redPlayer;
    private Player bluePlayer;
    private static final int DELAY = 10;

    private boolean redDead;
    private boolean blueDead;

    public boolean[][] taken;
    private boolean[][] bonusTab;

    private boolean drawBonus;
    private boolean init;
    private boolean redBonus;
    private boolean redActiveBonus;
    private boolean blueBonus;
    private boolean blueActiveBonus;
    private boolean activateTheBonus;
    private boolean cleared;
    private boolean bonusDrawn;

    private Music sounds;
    private Bonus bonus;
    private GraphicsImages graphics;
    private final Menu menu;
    private final Buttons buttons;

    public Board(Buttons buttons, Menu menu) {
        this.buttons = buttons;
        this.menu = menu;
    }

    public void initBoard() {
        setFocusable(true);

        cleared = false;
        bonusDrawn = false;
        redBonus = false;
        blueBonus = false;
        redActiveBonus = false;
        blueActiveBonus = false;
        drawBonus = false;
        init = true;
        sounds = new Music();
        redDead = false;
        blueDead = false;
        redPlayer = new Player(70, 200, Color.red, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, 2, 0, 2);
        bluePlayer = new Player(720, 200, Color.blue, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, -2, 0, 2);
        taken = new boolean[800][600];
        bonusTab = new boolean[800][600];
        timer = new Timer(DELAY, this);
        timer.start();
        bonus = new Bonus(Color.green, this);
        activateTheBonus = true;
        graphics = new GraphicsImages("images/scoreboard.png", "images/logo.jpg");
    }

    @Override
    public void paintComponent(Graphics g) {
        //at the beginning and after death we clear board
        if (init) {
            g.setColor(Color.black);
            g.fillRect(0, 0, 800, 650);
            init = false;
        }

        if ((redActiveBonus || blueActiveBonus) && !cleared) {
            cleared = true;
            g.setColor(Color.black);
            g.fillRect(bonus.getX(), bonus.getY(), 10, 10);
            g.drawRect(bonus.getX(), bonus.getY(), 10, 10);
        }

        if (drawBonus && !bonusDrawn) {
            bonus.draw(g);
            bonusDrawn = true;
        }

        if (!redDead && !blueDead) {
            g.setColor(Color.white);
            g.drawImage(graphics.getScoreBoard(), 4, 586, 123, 30, this);
            g.drawImage(graphics.getScoreBoard(), 670, 586, 123, 30, this);
            g.drawImage(graphics.getLogo(), 350, 586, 100, 30, this);
            g.drawRect(1, 1, 791, 581);
            redPlayer.draw(g, 30, 605, "Czerwony :");
            bluePlayer.draw(g, 700, 605, "Niebieski :");
        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (activateTheBonus) {
            drawBonus = true;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    bonusTab[bonus.getX() + i][bonus.getY() + j] = true;
                }
            }
        }

        redPlayer.movement.move();
        bluePlayer.movement.move();

        redDead = redPlayer.isDead();
        blueDead = bluePlayer.isDead();
        if (!redDead && !blueDead) {
            redDead = checkTaken(redPlayer);
            blueDead = checkTaken(bluePlayer);
        }

        if (blueDead) redPlayer.incScore();
        if (redDead) bluePlayer.incScore();

        markTaken(redPlayer);
        markTaken(bluePlayer);

        blueBonus = checkBonus(bluePlayer);
        redBonus = checkBonus(redPlayer);

        if (redBonus && redPlayer.movement.getSpeed() != 4) {
            redPlayer.movement.changeSpeed(4);
            bonusDrawn = false;
            redActiveBonus = true;
            cleared = false;
        }
        if (blueBonus && redPlayer.movement.getSpeed() != 4) {
            bluePlayer.movement.changeSpeed(4);
            bonusDrawn = false;
            blueActiveBonus = true;
            cleared = false;
        }

        if (redActiveBonus) {
            redActiveBonus = redPlayer.activeBonusPass();
        }

        if (blueActiveBonus) {
            blueActiveBonus = bluePlayer.activeBonusPass();
        }

        if (redActiveBonus || blueActiveBonus) {
            bonusTab = new boolean[800][600];
        }

        if (redDead || blueDead) {
            sounds.playSound("audio/dead.wav");
            taken = new boolean[800][600];
            init = true;
            redPlayer.set(70, 200, 2, 0, 2);
            bluePlayer.set(720, 200, -2, 0, 2);
            bonusDrawn = false;
            redActiveBonus = true;
            cleared = false;
        }
        repaint();
    }

    private void markTaken(Player player) {
        int height;
        int width;

        if (player.movement.getDY() == 0) {
            width = player.movement.getSpeed();
            height = 4;
        } else {
            width = 4;
            height = player.movement.getSpeed();
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                try {
                    if (player.movement.getX() + i < 800 && player.movement.getY() + j < 600)
                        taken[player.movement.getX() + i][player.movement.getY() + j] = true;
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean checkTaken(Player player) {
        int height;
        int width;

        if (player.movement.getDY() == 0) {
            width = player.movement.getSpeed();
            height = 4;
        } else {
            width = 4;
            height = player.movement.getSpeed();
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (player.movement.getX() + i <= 800 && player.movement.getY() + j <= 600) {
                    try {
                        if (taken[player.movement.getX() + i][player.movement.getY() + j]) return true;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    private boolean checkBonus(Player player) {
        int height;
        int width;

        if (player.movement.getDY() == 0) {
            width = player.movement.getSpeed();
            height = 4;
        } else {
            width = 4;
            height = player.movement.getSpeed();
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                try {
                    if (bonusTab[player.movement.getX() + i][player.movement.getY() + j]) {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public void keyPressed(KeyEvent e) {
        e.getKeyCode();

        if (e.getKeyCode() == KeyEvent.VK_Q) {
            this.setVisible(false);
            menu.setSize(new Dimension(400, 400));
            menu.setTitle("Tron - Menu");
            buttons.setVisible(true);
            timer.stop();
            buttons.board = null;
        }
        redPlayer.movement.keyPressed(e);
        bluePlayer.movement.keyPressed(e);
    }

    public boolean getTaken(int x, int y) {
        return taken[x][y];
    }
}
