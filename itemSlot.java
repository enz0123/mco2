/**
 * Class responsible for managing the items within it and communicating information with the vending machine
 */
public class itemSlot {
    private String name;
    private item[] currentItems;
    private double price;
    private int stock = 0;
    private double calories;
    private int numSold = 0;
    private double totalSales = 0;
    private int initStock = 0;

    /**
     * Initializes attributes of the item slot
     * @param name - name of items inside item slot
     * @param price - price of items inside item slot
     * @param calories - calories of items inside item slot
     */
    public itemSlot(String name, double price, double calories)
    {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.currentItems = new item[10];
    }

    /**
     * Sets initial stock of item slot
     * @param initStock - new initial stock of item slot
     */
    public void setInitStock(int initStock){
        this.initStock = initStock;
    }

    /**
     * Gets initial stock of item slot
     * @return initial stock of item slot
     */
    public int getInitStock(){
        return this.initStock;
    }

    /**
     * Gets price of items in item slot
     * @return price of items
     */
    public double getPrice()
    {
        return this.price;
    }

    /**
     * Gets calories of items in item slot
     * @return calories of items
     */
    public double getCalories()
    {
        return this.calories;
    }

    /**
     * Gets stock of items in item slot
     * @return current stock of item slot
     */
    public int getStock()
    {
        return this.stock;
    }

    /**
     * Gets name of items in item slot
     * @return name of items contained in item slot
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Gets number of items sold from item slot
     * @return number of items sold
     */
    public int getNumSold() {return this.numSold; }

    /**
     * Gets total sales of items in item slot
     * @return total sales
     */
    public double getTotalSales() {return this.totalSales; }

    /**
     * Sets price for items in item slot
     * @param price - new price for items
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Adds one item in item slot
     */
    public void addStock()
    {
        this.currentItems[this.stock] = new item(this.name);
        this.stock = this.stock + 1;
    }

    /**
     * Increments number of items sold and increases total sales of items in item slot
     */
    public void addSales(){
        this.numSold = this.numSold + 1;
        this.totalSales = this.totalSales + this.price;
    }

    /**
     * Removes item from item slot and decreases stock
     */
    public void dispenseThis()
    {
        currentItems[stock-1] = null;
        this.stock = this.stock - 1;
    }

	/**
	* Sets the variables "numSold" and "totalSales" to 0
	*/
    public void resetSales(){
        this.numSold = 0;
        this.totalSales = 0;
    }
}
