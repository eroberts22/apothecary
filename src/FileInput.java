import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * FileInput Class
 * @author Elizabeth Roberts
 * @version 1.00, 24 January 2021
 */

public class FileInput {

    static ArrayList<Spell> spells = new ArrayList<>();
    static ArrayList<Treasure> treasures = new ArrayList<>();
    static ArrayList<Enemy> enemies = new ArrayList<>();

    /**
     * Create a File Input object
     */
    FileInput() {}

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////Initialize Spells///////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * pull spells from file
     * @throws IOException io
     */
    public void initSpells() throws IOException {
        FileReader input = new FileReader("src/resources/txt_files/spell_list.txt");
        BufferedReader bufRead = new BufferedReader(input);
        String myLine;
        while ((myLine = bufRead.readLine()) != null) {
            String space = "[ ]";
            String[] tokens = myLine.split(space);
            createSpell(tokens);
        }
    }

    /**
     * generate spell
     * @param tokens String array
     */
    private void createSpell(String[] tokens) {
        Spell spell = new Spell(tokens[0], tokens[1], tokens[2], tokens[3]);
        spells.add(spell);
    }

    /**
     * retrieve array of all spells
     * @return ArrayList spells
     */
    public ArrayList<Spell> getSpells () {
        return (ArrayList<Spell>) spells.clone();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////Initialize Treasure///////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * pull treasure from file
     * @throws IOException io
     */
    public void initTreasure() throws IOException {
        FileReader input = new FileReader("src/resources/txt_files/treasure_list.txt");
        BufferedReader bufRead = new BufferedReader(input);
        String myLine;
        while ((myLine = bufRead.readLine()) != null) {
            String space = "[ ]";
            String[] tokens = myLine.split(space);
            createTreasure(tokens);
        }
    }

    /**
     * generate treasure
     * @param tokens String array
     */
    private void createTreasure(String[] tokens) {
        if(tokens[0].equals("Chest:")) {
            Chest chest = new Chest(tokens[1]);
            treasures.add(chest);
        } else if (tokens[0].equals("Potion:")) {
            Potion potion = new Potion(tokens[1],tokens[2]);
            treasures.add(potion);
        }
    }

    /**
     * retrieve array of all treasure
     * @return ArrayList treasure
     */
    public ArrayList<Treasure> getTreasure () {
        return (ArrayList<Treasure>) treasures.clone();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////Initialize Enemies///////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * pull enemies from file
     * @throws IOException io
     */
    public void initEnemy() throws IOException {
        FileReader input = new FileReader("src/resources/txt_files/enemy_list_extended.txt");
        BufferedReader bufRead = new BufferedReader(input);
        String myLine;
        while ((myLine = bufRead.readLine()) != null) {
            String space = "[ ]";
            String[] tokens = myLine.split(space);
            createEnemy(tokens);
        }
    }

    /**
     * pull enemies from file (used for testing)
     * @throws IOException io
     */
    public void initEnemyTest() throws IOException {
        FileReader input = new FileReader("src/resources/txt_files/enemy_list.txt");
        BufferedReader bufRead = new BufferedReader(input);
        String myLine;
        while ((myLine = bufRead.readLine()) != null) {
            String delims = "[ ]";
            String[] tokens = myLine.split(delims);
            createEnemy(tokens);
        }
    }

    /**
     * generate enemy
     * @param tokens String array
     */
    private void createEnemy(String[] tokens) {
        Enemy enemy = new Enemy(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
        enemies.add(enemy);
    }

    /**
     * retrieve array of all enemies
     * @return ArrayList enemies
     */
    public ArrayList<Enemy> getEnemies () { return (ArrayList<Enemy>) enemies.clone(); }
}
