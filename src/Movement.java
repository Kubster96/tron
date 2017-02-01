import java.awt.event.KeyEvent;

//metoda movement posiada pozycje predkosc oraz przesuniecie i zdefiniowane klawisze sterujace
public class Movement {

    private int x;
    private int y;
    private int dx;
    private int dy;
    private int speed;
    private int up;
    private int down;
    private int left;
    private int right;

    public Movement(int x, int y, int up, int down, int left, int right, int dx, int dy, int speed){

        this.x = x;
        this.y = y;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.speed = speed;
        this.dx = dx;
        this.dy = dy;

    }

    //metoda move porusza graczem
    public void move(){
        if(dx == 0) y+=dy;
        if(dy == 0) x+=dx;
    }

    public void setX(int x) { this.x =x; }

    public void setY(int y) { this.y =y; }

    public void setDX(int dx) { this.dx =dx; }

    public void setDY(int dy) { this.dy =dy; }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDX() { return dx; }

    public int getDY() { return dy; }

    public int getSpeed(){ return speed; }

    //nacisniecie klawisza przestawia pozycje gracza aby slad był ciagły
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == left && dx == 0) {
            if (dy > 0) {
                dx = -speed;
                dy = 0;
                y = y + speed - 4;
            } else {
                dx = -speed;
                dy = 0;
            }
        }

        if (key == right && dx == 0) {
            if (dy < 0) {
                dx = speed;
                dy = 0;
                x = x - speed + 4;
            } else {
                dx = speed;
                dy = 0;
                x = x - speed + 4;
                y = y + speed - 4;
            }

        }

        if (key == up && dy == 0) {
            if (dx < 0) {
                dy = -speed;
                dx = 0;
            } else {
                dy = -speed;
                dx = 0;
                x = x + speed - 4;
            }

        }

        if (key == down && dy == 0) {
            if (dx > 0) {
                dy = speed;
                dx = 0;
                x = x + speed - 4;
                y = y - speed + 4;
            } else {
                dy = speed;
                dx = 0;
                y = y - speed + 4;
            }
        }
    }

    //zmiana predkosci rowniez przestawia x
    public void changeSpeed(int speed){
        int oldSpeed = this.speed;
        this.speed = speed;
        if(dx<0){
            dx=-speed;
        }
        if(dx>0){
            x=x-speed+oldSpeed;
            dx=speed;
        }
        if(dy<0){
            dy=-speed;
        }
        if(dy>0){
            y=y-speed+oldSpeed;
            dy=speed;
        }
    }

}
