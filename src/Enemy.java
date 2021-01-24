/**
 * Enemy Class
 * @author Elizabeth Roberts
 * @version 1.00, 24 January 2021
 */

public class Enemy {

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    private String name;
    private int MAX_HEALTH;
    private int health;
    private int min_damage;
    private int max_damage;
    private String weakness;
    private String url;

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * Create an enemy object
     * @param adj first part of name
     * @param name second part of name
     * @param health starting health
     * @param min_damage minimum damage
     * @param max_damage maximum damage
     * @param weakness elemental weakness
     * @param url picture url
     */
    Enemy(String adj, String name, String health, String min_damage, String max_damage, String weakness, String url) {
        setName(adj, name);
        setMAXHealth(health);
        setHealth(health);
        setMin_damage(min_damage);
        setMax_damage(max_damage);
        setWeakness(weakness);
        setUrl(url);
    }

    /**
     * Generate string of enemy information
     * @return string
     */
    @Override
    public String toString() {
        return name + "\n   Health: " + health + "\n   Damage: " + min_damage + " - " + max_damage + " points\n   Weakness: " + weakness;
    }

    /////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////Get and Set Functions//////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * set name of enemy
     * @param adj 1st part of name
     * @param name 2nd part of name
     */
    public void setName(String adj, String name) {
        this.name = adj + " " + name;
    }

    /**
     * set max health of enemy
     * @param health health
     */
    public void setMAXHealth(String health) {
        this.MAX_HEALTH = Integer.parseInt(health);
    }

    /**
     * set current health of enemy
     * @param health health
     */
    public void setHealth(String health) {
        this.health = Integer.parseInt(health);
    }

    /**
     * set minimum damage an enemy can deal in battle
     * @param min_damage minimum damage
     */
    public void setMin_damage(String min_damage) {
        this.min_damage = Integer.parseInt(min_damage);
    }

    /**
     * set maximum damage an enemy can deal in battle
     * @param max_damage minimum damage
     */
    public void setMax_damage(String max_damage) {
        this.max_damage = Integer.parseInt(max_damage);
    }

    /**
     * set the elemental weakness of an enemy
     * @param weakness weakness
     */
    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    /**
     * set the picture url for an enemy
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * retrieve name of enemy
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * retrieve max health of enemy
     * @return int MAX_HEALTH
     */
    public int getMAXHealth() {
        return MAX_HEALTH;
    }

    /**
     * retrieve current health
     * @return int health
     */
    public int getHealth() {
        return health;
    }

    /**
     * retrieve minimum damage
     * @return int min_damage
     */
    public int getMin_damage() {
        return min_damage;
    }

    /**
     * retrieve maximum damage
     * @return int max_damage
     */
    public int getMax_damage() {
        return max_damage;
    }

    /**
     * retrieve image url of enemy
     * @return String url
     */
    public String getUrl() {
        return url;
    }
}
