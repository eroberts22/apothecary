/**
 * Potion Class
 * @author Elizabeth Roberts
 * @version 1.00, 24 January 2021
 */

public class Potion implements Treasure {

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    private String type;
    private int points;

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * Initialize a Potion Object
     * @param type type of potion
     * @param points points of potion
     */
    Potion(String type, String points) {
        setType(type);
        setPoints(points);
    }

    /**
     * generate string of a potion including type and points
     * @return string
     */
    @Override
    public String toString() {
        return switch (type) {
            case "Health Potion" -> type + ": " + points + " health points";
            case "Spell Potion" -> type + ": " + points + " spell points";
            case "Life Potion" -> type + ": +" + points + " health points";
            default -> type + ": " + points + " points";
        };
    }

    /////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////Get and Set Functions//////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * get the type of potion
     * @return type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * get the potion's image url
     * @return string url
     */
    @Override
    public String getUrl() {
        return switch (type) {
            case "Health Potion" -> "src/resources/sprites/health_potion.png";
            case "Spell Potion" -> "src/resources/sprites/spell_potion.png";
            case "Life Potion" -> "src/resources/sprites/life_potion.png";
            default -> "";
        };
    }

    /**
     * get the points of a potion
     * @return points
     */
    public int getPoints() {
        return points;
    }

    /**
     * set the type of potion
     * @param type type
     */
    @Override
    public void setType(String type) {
        this.type = type + " Potion";
    }

    /**
     * set the points of a potion
     * @param points points
     */
    public void setPoints(String points) {
        this.points = Integer.parseInt(points);
    }
}
