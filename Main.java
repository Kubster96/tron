import java.awt.*;

public class Main {

    //Main towrzy obiekt menu i pokazuje go
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
    }
}
