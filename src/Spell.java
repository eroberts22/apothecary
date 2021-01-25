import java.util.Comparator;

/**
 * Spell Class
 * @author Elizabeth Roberts
 * @version 1.00, 24 January 2021
 */

public class Spell implements Comparator<Spell>{

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    private String name;
    private int damage;
    private int sp_cost;
    private String type;

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * Initialize Spell object
     */
    Spell() {}

    /**
     * Initialize Spell object
     * @param name name of spell
     * @param damage damage of spell
     * @param sp_cost spell point cost
     * @param type type of spell
     */
    Spell(String name, String damage, String sp_cost, String type) {
        setName(name);
        setDamage(damage);
        setSp_cost(sp_cost);
        setType(type);
    }

    /**
     * generate string of spell information
     * @return string
     */
    @Override
    public String toString() {
        return "\n" + name + "\n   Damage: " + damage + "\n   SP Cost: " + sp_cost + "\n   type: " + type;
    }

    /**
     * compare sp cost of two spells
     * @param o1 spell 1
     * @param o2 spell 2
     * @return the smaller spell
     */
    @Override
    public int compare(Spell o1, Spell o2) {
        return o1.getSp_cost() - o2.getSp_cost();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////Get and Set Functions//////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * get name of spell
     * @return name
     */
    public String getName() { return name; }

    /**
     * get damage of spell
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * get spell point cost of spell
     * @return sp_cost
     */
    public int getSp_cost() {
        return sp_cost;
    }

    /**
     * set name of spell
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set damage of spell
     * @param damage damage
     */
    public void setDamage(String damage) {
        this.damage = Integer.parseInt(damage);
    }

    /**
     * set spell point cost of spell
     * @param sp_cost spells point cost
     */
    public void setSp_cost(String sp_cost) {
        this.sp_cost = Integer.parseInt(sp_cost);
    }

    /**
     * set type of spell
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

}
