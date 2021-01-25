import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Room Class
 * @author Elizabeth Roberts
 * @version 1.00, 24 January 2021
 */

public class Room extends JPanel{

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    Enemy enemy;
    Treasure treasure;
    private Image enemy_img, treasure_img;
    private BufferedImage background_img;
    private boolean enemy_bool = false;
    private boolean treasure_bool = false;

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * initialize a Room object
     * @param enemy enemy
     * @param treasure treasure
     * @throws IOException io
     */
    Room(Enemy enemy, Treasure treasure) throws IOException {
        setEnemy(enemy);
        setTreasure(treasure);
        init_room();
        init_enemy(enemy);
        init_treas(treasure);
    }

    /**
     * initialize a Room object
     * @param enemy enemy
     * @throws IOException io
     */
    Room(Enemy enemy) throws IOException {
        setEnemy(enemy);
        setTreasure(null);
        init_room();
        init_enemy(enemy);
    }

    /**
     * initialize a Room object
     * @param treasure treasure
     * @throws IOException io
     */
    Room(Treasure treasure) throws IOException {
        setEnemy(null);
        setTreasure(treasure);
        init_room();
        init_treas(treasure);
    }

    /**
     * initialize a Room object
     * @throws IOException io
     */
    Room() throws IOException {
        setEnemy(null);
        setTreasure(null);
        init_room();
    }

    /**
     * initialize the background image of the room
     * @throws IOException io
     */
    private void init_room() throws IOException {
        background_img = ImageIO.read(new File("src/resources/sprites/background.png"));
    }

    /**
     * initialize the enemy image in the room
     * @param enemy enemy
     * @throws IOException io
     */
    private void init_enemy(Enemy enemy) throws IOException {
        enemy_bool = true;

        enemy_img = ImageIO.read(this.getClass().getResourceAsStream(enemy.getURL()));

        if (enemy.getName().equals("Malicious Spectre")
                || enemy.getName().equals("Stinging JellyFish")
                || enemy.getName().equals("Twilight Nightmare")
                || enemy.getName().equals("Sneaky Shades")) {
            enemy_img = enemy_img.getScaledInstance(300,300,Image.SCALE_SMOOTH);
        } else if (enemy.getName().equals("Pale Shadow")) {
            enemy_img = enemy_img.getScaledInstance(500, 550, Image.SCALE_SMOOTH);
        } else {
            enemy_img = enemy_img.getScaledInstance(400,400,Image.SCALE_SMOOTH);
        }
    }

    /**
     * initialize the treasure image in the room
     * @param treasure treasure
     * @throws IOException io
     */
    private void init_treas(Treasure treasure) throws IOException {
        treasure_img = ImageIO.read(this.getClass().getResourceAsStream(treasure.getUrl()));
        if (treasure.getType().equals("Chest")) {
            treasure_img = treasure_img.getScaledInstance(300,300,Image.SCALE_SMOOTH);
        } else {
            treasure_img = treasure_img.getScaledInstance(200,250,Image.SCALE_SMOOTH);
        }
        treasure_bool = true;
    }

    /**
     * paint the room, randomly choose placement
     * for enemy from three options
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int e_n, e_x = 0, e_y = 0, t_x = 800, t_y = 400;
        Random rand = new Random();
        e_n = rand.nextInt(3);

        if (e_n == 0) {e_x = 250; e_y = 250;}
        if (e_n == 1) {e_x = 300; e_y = 300;}
        if (e_n == 2) {e_x = 250; e_y = 350;}

        g.drawImage(background_img, 0,0,null);
        if (enemy_bool) { g.drawImage(enemy_img, e_x,e_y, null); }
        if (treasure_bool) { g.drawImage(treasure_img, t_x,t_y,null);}
    }

    /////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////Get and Set Functions//////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * get enemy
     * @return enemy
     */
    public Enemy getEnemy() {
        return enemy;
    }

    /**
     * get treasure
     * @return treasure
     */
    public Treasure getTreasure() {
        return treasure;
    }

    /**
     * set enemy
     * @param enemy enemy
     */
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * set treasure
     * @param treasure treasure
     */
    public void setTreasure(Treasure treasure) {
        this.treasure = treasure;
    }
}
