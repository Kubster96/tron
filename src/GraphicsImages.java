import javax.swing.*;
import java.awt.*;

//klasa w ktorej mam wszystkie graficzne elementy

public class GraphicsImages {

    private Image scoreBoard;
    private Image logo;

    public GraphicsImages(String scoreBoard, String logo){

        loadScoreBoard(scoreBoard);
        loadLogo(logo);

    }

    public Image getScoreBoard() { return scoreBoard; }

    public Image getLogo() { return logo; }

    private void loadScoreBoard(String scoreBoard){
        ImageIcon ii = new ImageIcon(scoreBoard);
        this.scoreBoard = ii.getImage();
    }

    private void loadLogo(String logo){
        ImageIcon ii = new ImageIcon(logo);
        this.logo = ii.getImage();
    }

}
