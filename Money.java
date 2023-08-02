/**
 * Class for money and its value
 */
public class Money {
    private int amount;

    /**
     * Money needs value when it's created
     * @param amount - value of money
     */
    public Money(int amount){this.amount = amount;}

    /**
     * Gets value of money
     * @return value of money
     */
    public int getAmount(){return this.amount;}
}
