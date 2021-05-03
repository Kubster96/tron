import java.awt.*;

public class Player {
    private final Color color;
    private int score;
    public Movement movement;
    private int activeBonus;

    public Player(int x, int y, Color color, int up, int down, int left, int right, int dx, int dy, int speed) {
        this.color = color;
        this.score = 0;
        this.movement = new Movement(x, y, up, down, left, right, dx, dy, speed);
        this.activeBonus = 1000;
    }

    public Color getColor() {
        return color;
    }

    public boolean isDead() {
        if (movement.getX() <= movement.getSpeed() || movement.getX() >= 784 - movement.getSpeed() || movement.getY() <= movement.getSpeed() || movement.getY() >= 584 - movement.getSpeed()) {
            movement.setDX(0);
            movement.setDY(0);
            return true;
        }
        return false;
    }

    public void incScore() {
        score++;
    }

    public void draw(Graphics g, int x, int y, String colour) {
        g.setColor(getColor());
        if (movement.getDY() == 0) {

            g.fillRect(movement.getX(), movement.getY(), movement.getSpeed() - 1, 3);
            g.drawRect(movement.getX(), movement.getY(), movement.getSpeed() - 1, 3);

        } else {
            g.fillRect(movement.getX(), movement.getY(), 3, movement.getSpeed() - 1);
            g.drawRect(movement.getX(), movement.getY(), 3, movement.getSpeed() - 1);

        }
        g.drawString(colour + score, x, y);
    }

    public void set(int x, int y, int dx, int dy, int speed) {
        movement.setX(x);
        movement.setY(y);
        movement.setDX(dx);
        movement.setDY(dy);
        movement.changeSpeed(speed);
    }

    public boolean activeBonusPass() {
        activeBonus -= 10;
        if (activeBonus == 0) {
            movement.changeSpeed(2);
            activeBonus = 1000;
            return false;
        }
        return true;
    }
}
