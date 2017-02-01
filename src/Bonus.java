import java.awt.*;
import java.awt.Graphics;
import java.util.Random;


// bonus czyli zielony kwadrat pojawiajacy sie na mapie ktory przyspiesza gracza ktory go zebrał
public class Bonus {

    private int x;
    private int y;
    private Color color;
    private Board board;

    //konstruktor bonusu
    public Bonus(Color color, Board board){

        this.color = color;
        this.board = board;

    }

    public int getX(){ return x; }

    public int getY(){ return y; }

    public void setX(int x){ this.x = x; }

    public void setY(int y){ this.y = y; }

    public Color getColor() { return color; }

    //rysowanie bonusu
    public void draw(Graphics g){
        randomPosition();
        g.setColor(this.color);
        g.fillRect(x, y, 10, 10);
    }

    //losowanie pozycji ponusu tak aby bonus nie wyladował na sciezkach graczy
    public void randomPosition(){

        boolean notPossible = true;

        while(notPossible) {
            Random rand = new Random();
            this.x = rand.nextInt(700) + 50;
            this.y = rand.nextInt(500) + 50;

            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    if(notPossible) notPossible = board.getTaken(x+ i, y + j);
                }
            }
        }
    }


}
