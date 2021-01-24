/**
 * Chest Class
 * @author Elizabeth Roberts
 * @version 1.00, 24 January 2021
 */

public class Chest implements Treasure {

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    private String type;
    private int gold;

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * create a chest object
     * @param gold amount of gold in chest
     */
    Chest(String gold) {
        setGold(gold);
        setType("Chest");
    }

    /**
     * convert Chest to string
     * @return string
     */
    @Override
    public String toString() {
        return "Chest: " + gold + " gold";
    }

    /////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////Get and Set Functions//////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * set type of treasure
     * @param type type is chest
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * set amount of gold in a chest
     * @param gold amount of gold
     */
    public void setGold(String gold) {
        this.gold = Integer.parseInt(gold);
    }

    /**
     * retrieve the type of treasure
     * @return type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * retrieve the gold
     * @return int gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * retrieve the image url
     * @return String url
     */
    @Override
    public String getUrl() {
        return "src/resources/sprites/chest.png";
    }
}
