public class itemSlot {
    private String name;
    private item[] currentItems;
    private double price;
    private int stock = 0;
    private double calories;
    private int numSold = 0;
    private double totalSales = 0;
    private int initStock = 0;

    public itemSlot(String name, double price, double calories)
    {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.currentItems = new item[10];
    }

    public void setInitStock(int initStock){
        this.initStock = initStock;
    }

    public int getInitStock(){
        return this.initStock;
    }

	/**
	* @return the double value of the variable "price"
	*/
    public double getPrice()
    {
        return this.price;
    }

	/**
	* @return the double value of the variable "calories"
	*/
    public double getCalories()
    {
        return this.calories;
    }

	/**
	* @return the int value of the variable "stock"
	*/
    public int getStock()
    {
        return this.stock;
    }
	
	/**
	* @return the String value of the variable "name"
	*/
    public String getName()
    {
        return this.name;
    }

	/**
	* @return the int value of the variable "numSold"
	*/
    public int getNumSold() {return this.numSold; }

	/**
	* @return the double value of the variable "totalSales"
	*/
    public double getTotalSales() {return this.totalSales; }

	/**
	* Sets the variable "price" to the value given in the parameter
	* @param price The price of the item 
	*/
    public void setPrice(double price)
    {
        this.price = price;
    }

    public void addStock()
    {
        this.currentItems[this.stock] = new item(this.name);
        this.stock = this.stock + 1;
    }

	/**
	* Adds to the value of the variable "numSold" by 1 and adds the variable "price" to the variable "totalSales"
	*/
    public void addSales(){
        this.numSold = this.numSold + 1;
        this.totalSales = this.totalSales + this.price;
    }
	
	/**
	* Reduces the value of the variable "stock" by 1 and prints the variable "name" stating it has been dispensed
	*/
    public void dispenseThis()
    {
        System.out.println("1 " + this.currentItems[stock - 1].getName() + " has been dispensed.");
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
