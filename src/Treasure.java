/**
 * Treasure Interface
 * @author Elizabeth Roberts
 * @version 1.00, 24 January 2021
 */

public interface Treasure {
    /**
     * converts treasure information to a string
     * @return string
     */
    String toString();

    /**
     * set type of treasure
     * @param type type
     */
    void setType(String type);

    /**
     * get type of treasure
     * @return type
     */
    String getType();

    /**
     * get image url of treasure
     * @return
     */
    String getUrl();
}
