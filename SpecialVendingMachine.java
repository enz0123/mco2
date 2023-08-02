/**
 * Class for a special kind of vending machine that makes a customizable item
 */
public class SpecialVendingMachine extends VendingMachine{
    private itemSlot[] extraSlot;
    private double pizzaCost = 0;
    private int pizzaSold = 0;

    /**
     * Constructor that initializes main ingredient slots as well as the extra ingredients
     */
    public SpecialVendingMachine(){
        super();
        extraSlot = new itemSlot[3];

        extraSlot[0] = new itemSlot("Garlic Powder", 5, 10);
        extraSlot[1] = new itemSlot("Spice", 5, 15);
        extraSlot[2] = new itemSlot("Truffle Oil", 10, 25);
    }

    /**
     * Checks if change is available
     * @param pizzaCost - cost of pizza to be made
     * @return true if change is available, false otherwise
     */

    private boolean checkChange(double pizzaCost){
        //variables are temporarily values to simulate giving change
        double tempTotal = getTotalReceived() - pizzaCost;
        int check = 1;
        int ones = getRegister().getNumofOnes();
        int fives = getRegister().getNumofFives();
        int tens = getRegister().getNumofTens();
        int twenties = getRegister().getNumofTwenties();
        int fifties = getRegister().getNumofFifties();
        int hundreds = getRegister().getNumofHundreds();

        //Continuously removes money and decreases change to see if giving change is possible
        while (tempTotal > 0 && check == 1) {
            if (tempTotal >= 100 && hundreds > 0) {
                hundreds -= 1;
                tempTotal -= 100;
            } else if (tempTotal >= 50 && fifties > 0) {
                fifties -= 1;
                tempTotal -= 50;
            } else if (tempTotal >= 20 && twenties > 0) {
                twenties -= 1;
                tempTotal -= 20;
            } else if (tempTotal >= 10 && tens > 0) {
                tens -= 1;
                tempTotal -= 10;
            } else if (tempTotal >= 5 && fives > 0) {
                fives -= 1;
                tempTotal -= 5;
            } else if(tempTotal >= 1 && ones > 0){
                ones -= 1;
                tempTotal -= 1;
            }
            else{
                check = 0; //if it reaches this point, it signifies that change can't be given
            }

            if(check == 0){
                return false;
            }


        }
        return true;
    }

    /**
     * Dispenses extra item requested
     * @param index - index of extra item
     */
    public void dispenseExtra(int index){
        this.extraSlot[index].dispenseThis();
        this.extraSlot[index].addSales();
    }

    /**
     * Makes pizza based on the list of amounts of ingredients needed
     * @param amounts - list of amounts of each ingredient to be used
     * @return Outcome of pizza making, 1 if successful, 2 if not enough change, 3 if not enough money given
     */
    public int makePizza(int[] amounts){ //case 1: pizza made, case 2: not enough change, case 3: not enough money
        pizzaCost = 0;

        //Gets total cost of pizza based on amounts of each ingredient
        for(int ctr = 0; ctr < 8; ctr++)
            pizzaCost += amounts[ctr] * getItemPrice(ctr);
        for(int ctr = 0; ctr < 3; ctr++)
            pizzaCost += amounts[ctr+8] * getExtraItemPrice(ctr);

        if(pizzaCost > getTotalReceived())
            return 3;

        if(!checkChange(pizzaCost))
            return 2;

        //Dispenses/removes items until requested number of items met for each ingredient
        for(int ctr = 0; ctr < 11; ctr++)
            while(amounts[ctr] > 0)
            {
                if(ctr < 8)
                    this.dispenseItem(ctr);
                else
                    this.dispenseExtra(ctr - 8);

                amounts[ctr] -= 1;
            }

        giveChange(pizzaCost);

        pizzaSold += 1;
        return 1;
    }

    /**
     * Removes money from register to give change. It is assumed that denominations are enough to give change
     * @param pizzaCost - total cost of pizza made
     */
    private void giveChange(double pizzaCost){
        double change = getTotalReceived() - pizzaCost;
        moneyHolder register = getRegister();

        //Continuously removes money and decreases change until enough of change has been given (change = 0)
        //Information is also added to change log for each denomination of money removed
        while(change > 0){
            if(change >= 100 && register.getNumofHundreds() > 0){
                addChangeLog("\n100 bill");
                register.removeMoney(6);
                change -= 100;
            }
            else if (change >= 50 && register.getNumofFifties() > 0){
                addChangeLog("\n50 bill");
                register.removeMoney(5);
                change -= 50;
            }
            else if(change >= 20 && register.getNumofTwenties() > 0){
                addChangeLog("\n20 coin");
                register.removeMoney(4);
                change -= 20;
            }
            else if(change >= 10 && register.getNumofTens() > 0){
                addChangeLog("\n10 coin");
                register.removeMoney(3);
                change -= 10;
            }
            else if(change >= 5 && register.getNumofFives() > 0){
                addChangeLog("\n5 coin");
                register.removeMoney(2);
                change -= 5;
            }
            else{
                addChangeLog("\n1 coin");
                change -= 1;
            }
        }


        this.resetTotalReceived();
    }

    /**
     * Gets item name of specified extra slot
     * @param index - index of item slot
     * @return item name of item slot
     */
    public String getExtraItemName(int index){return extraSlot[index].getName();}

    /**
     * Gets calories of specified extra slot
     * @param index - index of item slot
     * @return calories in item slot
     */
    public double getExtraItemCalories(int index){return extraSlot[index].getCalories();}

    /**
     * Gets price of items in extra item slot
     * @param index - index of item slot
     * @return price of item
     */
    public double getExtraItemPrice(int index){return extraSlot[index].getPrice();}

    /**
     * Gets stock of items in extra item slot
     * @param index - index of item slot
     * @return current stock of item slot
     */
    public int getExtraItemStock(int index){return extraSlot[index].getStock();}

    /**
     * Gets initial stock of items in extra item slot
     * @param index - index of item slot
     * @return initial stock of item slot
     */

    public int getExtraItemInitStock(int index){return extraSlot[index].getInitStock();}

    /**
     * Gets number of items sold in extra item slot
     * @param index - index of item slot
     * @return number of items sold of item slot
     */

    public int getExtraItemNumSold(int index){return extraSlot[index].getInitStock();}

    /**
     * Gets total sales of items in extra item slot
     * @param index - index of item slot
     * @return total sales of items in item slot
     */

    public double getExtraItemTotalSales(int index){return extraSlot[index].getTotalSales();}

    /**
     * Adds an item to extra item slot specified
     * @param index - index of item slot
     */

    public void stockExtraItem(int index){extraSlot[index].addStock();}


    /**
     * Sets price of extra item slot
     * @param index - index of item slot
     * @param price - new price of item
     */

    public void setExtraPrice(int index, double price){extraSlot[index].setPrice(price);}

    /**
     * Sets price of pizzas sold to zero
     */
    public void resetPizzaSold(){pizzaSold = 0;}

    /**
     * Gets number of pizzas sold
     * @return pizzaSold - number of pizzas sold
     */
    public int getPizzaSold(){return pizzaSold;}

}

