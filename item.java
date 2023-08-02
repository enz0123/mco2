/**
 * Class for the item
 */
public class item {
    private String name;

    /**
     * Sets the name of the item when created
     * @param name - name of the item
     */
    public item(String name){
        this.name = name;
    }

    /**
     * Gets the name of the item
     * @return name of item
     */
    public String getName(){return this.name;}
}
