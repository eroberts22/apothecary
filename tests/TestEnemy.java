import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TestEnemy {

    static ArrayList<Enemy> enemies;

    public static void checkSprite() {
        JFrame frame = new JFrame("Test Enemy Sprite");
        frame.setBounds(0, 0, 1700, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();

        Image img;

        JPanel panel = new JPanel(new GridLayout(3,3,10,10));

        for (Enemy e: enemies) {
            img = tk.createImage(e.getURL());
            img = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(img));
            label.setText(e.getName());
            panel.add(label);
        }

        frame.add(panel);
        frame.setVisible(true);
    }


    public static void main(String[] args) throws IOException {

        FileInput fileInput = new FileInput();
        fileInput.initEnemyTest();
        enemies = fileInput.getEnemies();
        for (Enemy e: enemies) {
            System.out.printf("%s\n", e.toString());
        }

        //File n = new File(new File("/resources/images/background.png").getCanonicalPath());
        System.out.println(new File("sprites/background.png").exists());

        checkSprite();


    }
}
