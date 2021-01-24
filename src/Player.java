import java.util.ArrayList;

/**
 * Player Class
 * @author Elizabeth Roberts
 * @version 1.00, 24 January 2021
 */

public class Player {

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    ArrayList<Spell> spells;
    private String spell_string = "";
    private final int TOTAL_HEALTH = 150;
    private final int TOTAL_SPELL = 50;
    private int health;
    private int spell;

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * Initialize a Player object
     * set health points and spell points
     * @param spells array list of spells
     */
    Player (ArrayList<Spell> spells) {
        setSpells(spells);
        spellString();
        setHealth(150);
        setSpell(50);
    }

    /**
     * generate a string of player information
     * @return String
     */
    @Override
    public String toString() {
        return "Player" +
                "\nSpells: " + spell_string +
                "\nHealth Points: " + health + " HP" +
                "\nSpell Points: " + spell + " SP";
    }

    /**
     * create String of spells
     */
    private void spellString() {
        for (Spell s: spells) {
            spell_string += s.toString();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////Get and Set Functions//////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * get max health points
     * @return total health points
     */
    public int getTOTAL_HEALTH() {
        return TOTAL_HEALTH;
    }

    /**
     * get max spell points
     * @return total spell points
     */
    public int getTOTAL_SPELL() {
        return TOTAL_SPELL;
    }

    /**
     * get current health points
     * @return health points
     */
    public int getHealth() {
        return health;
    }

    /**
     * get current spell points
     * @return spell points
     */
    public int getSpell() {
        return spell;
    }

    /**
     * get the players list of spells
     * @return array list of spells
     */
    public ArrayList<Spell> getSpells () { return (ArrayList<Spell>) spells.clone(); }

    /**
     * get the player's spells as a string
     * @return string of spells
     */
    public String getSpellsToString() {
        return spell_string;
    }

    /**
     * set player spells
     * @param spells array list of spells
     */
    public void setSpells(ArrayList<Spell> spells) {
        spells.sort(new Spell());
        this.spells = spells;
    }

    /**
     * set the player's health points
     * @param hp health points
     */
    public void setHealth(int hp) {
        this.health = hp;
    }

    /**
     * set the player's spell points
     * @param sp spell points
     */
    public void setSpell(int sp) {
        this.spell = sp;
    }

}
