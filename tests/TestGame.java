import java.io.IOException;
import java.util.ArrayList;

public class TestGame {

    static ArrayList<Spell> spells;
    static ArrayList<Treasure> treasures;
    static ArrayList<Enemy> enemies;

    static FileInput fileInput;

    static Game game;

    public static void generate() throws IOException {

        fileInput = new FileInput();
        fileInput.initSpells();
        fileInput.initTreasure();
        fileInput.initEnemy();

        spells = fileInput.getSpells();
        treasures = fileInput.getTreasure();
        enemies = fileInput.getEnemies();
    }

    public static void main(String[] args) throws IOException {
        generate();
        game = new Game(spells, treasures, enemies);
        System.out.printf("%s\n", game.getPlayerSpells());
    }
}
