import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Game Class
 * @author Elizabeth Roberts
 * @version 1.00, 24 January 2021
 */

public class Game {

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    Random rand = new Random();
    ArrayList<Spell> spells;
    ArrayList<Treasure> treasures;
    ArrayList<Enemy> enemies;
    Player player;
    ArrayList<Spell> player_spells;
    private int xPos;
    private int yPos;
    private final int SIZE = 4;
    private final Room[][] rooms = new Room[SIZE][SIZE];
    private int gold = 0;
    private int enemies_killed = 0;
    private final ArrayList<Potion> current_hp_potions;
    private final ArrayList<Potion> current_sp_potions;

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * initialize a game object
     * set spells, treasures, enemies
     * @param spells array list of spells
     * @param treasures array list of treasures
     * @param enemies array list of enemies
     * @throws IOException io
     */
    Game(ArrayList<Spell> spells, ArrayList<Treasure> treasures, ArrayList<Enemy> enemies) throws IOException {
        setSpells(spells);
        setTreasures(treasures);
        setEnemies(enemies);
        current_hp_potions = new ArrayList<>();
        current_sp_potions = new ArrayList<>();
        init();
    }

    /**
     * create the player object, set player position
     * @throws IOException io
     */
    private void init() throws IOException {
        generatePlayerSpells();
        player = new Player(player_spells);
        generateRooms();
        xPos = 1;
        yPos = 1;
    }

    /**
     * randomly generate the player's spells
     */
    private void generatePlayerSpells() {
        ArrayList<Spell> tempSpell = (ArrayList<Spell>) spells.clone();
        player_spells = new ArrayList<>();
        final int MAX_SPELL = 4;
        int n;

        for (int i = 0; i < MAX_SPELL; i ++) {
            n = rand.nextInt(tempSpell.size());
            player_spells.add(tempSpell.get(n));
            tempSpell.remove(tempSpell.get(n));
        }
    }

    /**
     * randomly generate rooms using treasures and enemies
     * @throws IOException io
     */
    private void generateRooms() throws IOException {
        Room room;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int n = rand.nextInt(5);

                int e = rand.nextInt(enemies.size());
                Enemy en = enemies.get(e);
                enemies.remove(en);

                int t = rand.nextInt(treasures.size());
                Treasure tr = treasures.get(t);
                treasures.remove(tr);

                if (n == 0) {
                    room = new Room(tr);
                } else if (n == 1 || n == 2) {
                    room = new Room(en);
                } else {
                    room = new Room(en,tr);
                }
                rooms[i][j] = room;
            }
        }
    }

    /**
     * remove an enemy from a room
     * @param x x position
     * @param y y position
     * @throws IOException io
     */
    public void changeRoomRemoveEnemy(int x, int y) throws IOException {
        Room room;
        Treasure t = rooms[x][y].getTreasure();
        if (t == null) {
            room = new Room();
        } else {
            room = new Room(t);
        }
        rooms[x][y] = room;
    }

    /**
     * remove treasure from a room
     * @param x x position
     * @param y y position
     * @throws IOException io
     */
    public void changeRoomRemoveTreasure(int x, int y) throws IOException {
        Room room;
        Enemy e = rooms[x][y].getEnemy();
        if (e == null) {
            room = new Room();
        } else {
            room = new Room(e);
        }
        rooms[x][y] = room;
    }

    /**
     * add to the player's gold
     * @param g gold
     */
    public void addGold(int g) {
        this.gold = gold + g;
    }

    /**
     * add to enemies killed
     */
    public void addEnemyKilled() {
        enemies_killed++;
    }

    /**
     * add health potion to current potions
     * @param potion health potion
     */
    public void addHPPotion(Potion potion) {
        current_hp_potions.add(potion);
    }

    /**
     * remove health potion from current potions
     * @param potion health potion
     */
    public void removeHPPotion(Potion potion) {
        current_hp_potions.remove(potion);
    }

    /**
     * player has a health potion
     * @return true if has hp potion
     */
    public boolean hasTypeHP() {
        return current_hp_potions.size() > 0;
    }

    /**
     * add spell potion to current potions
     * @param potion spell potion
     */
    public void addSPPotion(Potion potion) {
        current_sp_potions.add(potion);
    }

    /**
     * remove spell potion from current potions
     * @param potion spell potion
     */
    public void removeSPPotion(Potion potion) {
        current_sp_potions.remove(potion);
    }

    /**
     * player has a spell potion
     * @return true if has sp potion
     */
    public boolean hasTypeSP() {
        return current_sp_potions.size() > 0;
    }

    /**
     * Retrieve game information as a string
     * @return String
     */
    @Override
    public String toString() {
        return "Position: " + xPos + "," + yPos +
                "\nGold: " + gold +
                "\nHealth Points: " + player.getHealth() + "/" + player.getTOTAL_HEALTH() +
                "\nSpell Points: " + player.getSpell() + "/" + player.getTOTAL_SPELL() +
                "\nEnemies Killed: " + enemies_killed +
                "\nCurrent Potions: " + potionsToString();
    }

    /**
     * retrieve all player's potions as a string
     * @return String
     */
    public String potionsToString() {
        StringBuilder potion_string = new StringBuilder();
        for (Potion s: current_hp_potions) {
            potion_string.append("\n    ").append(s.toString());
        }
        for (Potion s: current_sp_potions) {
            potion_string.append("\n    ").append(s.toString());
        }
        return potion_string.toString();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////Get and Set Functions//////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * get player
     * @return player object
     */
    public Player getPlayer() { return player; }

    /**
     * get player spells
     * @return String of spells
     */
    public String getPlayerSpells() {
        return player.getSpellsToString();
    }

    /**
     * get rooms
     * @return 2d array of rooms
     */
    public Room[][] getRooms() {
        return rooms;
    }

    /**
     * get current health potions
     * @return array list health potions
     */
    public ArrayList<Potion> getCurrent_hp_potions() { return current_hp_potions; }

    /**
     * get current spell potions
     * @return array list spell potions
     */
    public ArrayList<Potion> getCurrent_sp_potions() { return current_sp_potions; }

    /**
     * get x position of player
     * @return x pos
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * get y position of player
     * @return y pos
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * get size of the 2d array of rooms
     * @return size
     */
    public int getSIZE() {
        return SIZE;
    }

    /**
     * get the player's current amount of gold
     * @return gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * set spells of game object
     * @param spells array list of spells
     */
    public void setSpells(ArrayList<Spell> spells) {
        this.spells = spells;
    }

    /**
     * set treasures of game object
     * @param treasures array list of treasure
     */
    public void setTreasures(ArrayList<Treasure> treasures) {
        this.treasures = treasures;
    }

    /**
     * set enemies of game object
     * @param enemies array list of enemies
     */
    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    /**
     * set x position of player
     * @param xPos x position
     */
    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * set y position of player
     * @param yPos y position
     */
    public void setYPos(int yPos) {
        this.yPos = yPos;
    }
}
