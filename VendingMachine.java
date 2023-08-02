/**
 * Class for regular vending machine
 */
public class VendingMachine {
    private itemSlot[] itemSlot;
    private moneyHolder register;
    private double totalReceived = 0;
    private String changeLog = "Dispensed change: ";


    /**
     * Instantiates new item slots and the register
     */
    public VendingMachine()
    {
        itemSlot = new itemSlot[8];

        itemSlot[0] = new itemSlot("Pizza Dough", 50, 100);
        itemSlot[1] = new itemSlot("Tomato Sauce", 20, 40);
        itemSlot[2] = new itemSlot("Mozzarella Cheese", 50, 50);
        itemSlot[3] = new itemSlot("Pepperoni", 40, 50);
        itemSlot[4] = new itemSlot("Pineapple", 60, 30);
        itemSlot[5] = new itemSlot("Spinach", 20, 20);
        itemSlot[6] = new itemSlot("Gorgonzola", 70, 30);
        itemSlot[7] = new itemSlot("Ham", 40, 40);
        register = new moneyHolder();
    }

    /**
     * Gets the change log
     * @return changeLog - information on change being displayed
     */

    public String getChangeLog(){return this.changeLog;}

    /**
     * Gets item name of specified slot
     * @param itemSlot - index of item slot
     * @return item name of item slot
     */
    public String getItemName(int itemSlot){
        return this.itemSlot[itemSlot].getName();
    }

    /**
     * Resets the value of total amount of money received
     */
    public void resetTotalReceived(){this.totalReceived = 0;}

    /**
     * Gets number of items sold for specified slot
     * @param itemSlot - index of item slot requested
     * @return number of items sold of item slot
     */
    public int getNumSold(int itemSlot){
        return this.itemSlot[itemSlot].getNumSold();
    }

    /**
     * Gets sales of items for specified slot
     * @param itemSlot - index of requested item slot
     * @return number of items for specified slot
     */
    public double getSales(int itemSlot){return this.itemSlot[itemSlot].getTotalSales();}

    /**
     * Gets initial stock of item slot requested
     * @param itemSlot - index of item slot requested
     * @return initial stock of item slot requested
     */
    public int getInitStock(int itemSlot){return this.itemSlot[itemSlot].getInitStock();}

    /**
     * Gets current stock of item slot requested
     * @param itemSlot - index of item slot requested
     * @return current stock of item slot requested
     */
    public int getItemStock(int itemSlot){
        return this.itemSlot[itemSlot].getStock();
    }

    /**
     * Gets calories of item slot requested
     * @param itemSlot - index of item slot requested
     * @return calories of item slot requested
     */
    public double getItemCalories(int itemSlot){
        return this.itemSlot[itemSlot].getCalories();
    }

    /**
     * Gets item price of item slot requested
     * @param itemSlot - index of item slot requested
     * @return item price of item slot requested
     */
    public double getItemPrice(int itemSlot){
        return this.itemSlot[itemSlot].getPrice();
    }

    /**
     * Gets total received money in the vending machine
     * @return total received money
     */
    public double getTotalReceived(){return this.totalReceived;}

    /**
     * Adds the value of inserted money to total received money and adds the money itself to the cash register
     * @param money - money being inserted
     */
    public void addMoney(Money money)
    {
        switch(money.getAmount()){
            case 1: totalReceived += 1; break;
            case 5: totalReceived += 5; break;
            case 10: totalReceived += 10; break;
            case 20: totalReceived += 20; break;
            case 50: totalReceived += 50; break;
            case 100: totalReceived += 100; break;
        }
        register.addMoney(money);
    }

    /**
     * Verifies if transaction is to be pushed through
     * @param index - index of item to be dispensed
     * @return Outcome of verification
     */
    public int checkTransaction(int index){
        double change = totalReceived - itemSlot[index].getPrice();
        if(totalReceived < itemSlot[index].getPrice()){
            return 1; //returned if not enough money was inserted
        }
        else if(!checkChange(index)){
            return 2; //returned if change isn't available
        }

        else{
            return 0; //returned if transaction pushes through
        }
    }

    /**
     * Removes money from register to give change. It is assumed that denominations are enough to give change
     * @param index - index of item to be dispensed
     */
    public void giveChange(int index){
        double change = totalReceived - itemSlot[index].getPrice();

        //Continuously removes money and decreases change until enough of change has been given (change = 0)
        //Information is also added to change log for each denomination of money removed
        while(change > 0){
            if(change >= 100 && register.getNumofHundreds() > 0){
                changeLog = changeLog + "\n100 bill";
                register.removeMoney(6);
                change -= 100;
            }
            else if (change >= 50 && register.getNumofFifties() > 0){
                changeLog = changeLog + "\n50 bill";
                register.removeMoney(5);
                change -= 50;
            }
            else if(change >= 20 && register.getNumofTwenties() > 0){
                changeLog = changeLog + "\n20 coin";
                register.removeMoney(4);
                change -= 20;
            }
            else if(change >= 10 && register.getNumofTens() > 0){
                changeLog = changeLog + "\n10 coin";
                register.removeMoney(3);
                change -= 10;
            }
            else if(change >= 5 && register.getNumofFives() > 0){
                changeLog = changeLog + "\n5 coin";
                register.removeMoney(2);
                change -= 5;
            }
            else{
                changeLog = changeLog + "\n1 coin";
                change -= 1;
            }
        }
        totalReceived = 0; //resets total received
        dispenseItem(index); //dispenses item;
    }

    /**
     * Resets change log
     */
    public void resetChangeLog(){changeLog = "Dispensed change: ";}

    /**
     * Removes money from register to give back money if transaction is cancelled. Register is assumed to have enough change.
     */
    public void giveBackMoney(){
        //Continuously removes money and decreases change until enough of change has been given (totalReceived = 0)
        while(totalReceived > 0){
            if(totalReceived >= 100 && register.getNumofHundreds() > 0){
                changeLog = changeLog + "\n100 bill";
                register.removeMoney(6);
                totalReceived -= 100;
            }
            else if (totalReceived >= 50 && register.getNumofFifties() > 0){
                changeLog = changeLog + "\n50 bill";
                register.removeMoney(5);
                totalReceived -= 50;
            }
            else if(totalReceived >= 20 && register.getNumofTwenties() > 0){
                changeLog = changeLog + "\n20 coin";
                register.removeMoney(4);
                totalReceived -= 20;
            }
            else if(totalReceived >= 10 && register.getNumofTens() > 0){
                changeLog = changeLog + "\n10 coin";
                register.removeMoney(3);
                totalReceived -= 10;
            }
            else if(totalReceived >= 5 && register.getNumofFives() > 0){
                changeLog = changeLog + "\n5 coin";
                register.removeMoney(2);
                totalReceived -= 5;
            }
            else{
                changeLog = changeLog + "\n1 coin";
                totalReceived -= 1;
            }
        }
    }

    /**
     * Gets the register of the vending machine
     * @return register of the vending machine
     */
    public moneyHolder getRegister(){return this.register;}

    /**
     * Checks if the vending machine can provide enough change
     * @param index - index of the item to be dispensed
     * @return true if enough change, false otherwise
     */
    private boolean checkChange(int index) {
        //variables are temporarily values to simulate giving change
        double tempTotal = totalReceived - itemSlot[index].getPrice();
        int check = 1;
        int ones = register.getNumofOnes();
        int fives = register.getNumofFives();
        int tens = register.getNumofTens();
        int twenties = register.getNumofTwenties();
        int fifties = register.getNumofFifties();
        int hundreds = register.getNumofHundreds();

        System.out.println(ones);

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
     * Dispenses item requested
     * @param index - index of item requested
     */
    public void dispenseItem(int index){
        this.itemSlot[index].dispenseThis();
        this.itemSlot[index].addSales();
    }


    /**
     * Sets price for requested item
     * @param index - index of requested item
     * @param price - new price for item
     */
    public void setPrice(int index, double price){
        itemSlot[index].setPrice(price);
    }

    /**
     * Adds one item to item slot
     * @param index - index of item to be restocked
     */
    public void stockItem(int index){
        itemSlot[index].addStock();
        for(int ctr = 0; ctr < 8; ctr++){ //resets sales, since summary of transactions only goes back to last restocking
            itemSlot[ctr].resetSales();
            itemSlot[ctr].setInitStock(itemSlot[ctr].getStock());
        }
    }

    /**
     * Removes all money from the cash register
     * @return Text containing information about all collected money
     */
    public String collectMoney(){
		return register.collectMoney();
	}

    /**
     * Updates change feedback log
     * @param add - new information to be added to the log
     */
    public void addChangeLog(String add){this.changeLog = this.changeLog + add;}
}
