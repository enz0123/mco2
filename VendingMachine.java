import java.util.Scanner;
public class VendingMachine {
    private itemSlot[] itemSlot;
    private itemSlot currentItem;
    private moneyHolder register;
    private double totalReceived = 0;
	
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

    public String getItemName(int itemSlot){
        return this.itemSlot[itemSlot].getName();
    }

    public void resetTotalReceived(){this.totalReceived = 0;}

    public int getNumSold(int itemSlot){
        return this.itemSlot[itemSlot].getNumSold();
    }

    public double getSales(int itemSlot){return this.itemSlot[itemSlot].getTotalSales();}

    public int getInitStock(int itemSlot){return this.itemSlot[itemSlot].getInitStock();}
    public int getItemStock(int itemSlot){
        return this.itemSlot[itemSlot].getStock();
    }
    public double getItemCalories(int itemSlot){
        return this.itemSlot[itemSlot].getCalories();
    }

    public double getItemPrice(int itemSlot){
        return this.itemSlot[itemSlot].getPrice();
    }

    public double getTotalReceived(){return this.totalReceived;}

	/**
	* Prints out all the names, stock, and calories of the items in the vending machine
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


    public int checkTransaction(int index){
        double change = totalReceived - itemSlot[index].getPrice();
        if(totalReceived < itemSlot[index].getPrice()){
            System.out.println("Error. Not enough money. Returning money...");
           /* while(totalReceived > 0){
                if(totalReceived >= 100 && register.getNumofHundreds() > 0){
                    System.out.println("Dispensed hundred bill");
                    register.removeMoney(6);
                    totalReceived -= 100;
                }
                else if (totalReceived >= 50 && register.getNumofFifties() > 0){
                    System.out.println("Dispensed fifty bill");
                    register.removeMoney(5);
                    totalReceived -= 50;
                }
                else if(totalReceived >= 20 && register.getNumofTwenties() > 0){
                    System.out.println("Dispensed twenty coin");
                    register.removeMoney(4);
                    totalReceived -= 20;
                }
                else if(totalReceived >= 10 && register.getNumofTens() > 0){
                    System.out.println("Dispensed 10 peso coin");
                    register.removeMoney(3);
                    totalReceived -= 10;
                }
                else if(totalReceived >= 5 && register.getNumofFives() > 0){
                    System.out.println("Dispensed 5 peso coin");
                    register.removeMoney(2);
                    totalReceived -= 5;
                }
                else{
                    System.out.println("Dispensed 1 peso coin");
                    totalReceived -= 1;
                }
            }*/
            return 1;
        }
        else if(!checkChange(index)){
          /*  System.out.println("Error. Not enough change. Returning money...");
            while(totalReceived > 0){
                if(totalReceived >= 100 && register.getNumofHundreds() > 0){
                    System.out.println("Dispensed hundred bill");
                    register.removeMoney(6);
                    totalReceived -= 100;
                }
                else if (totalReceived >= 50 && register.getNumofFifties() > 0){
                    System.out.println("Dispensed fifty bill");
                    register.removeMoney(5);
                    totalReceived -= 50;
                }
                else if(totalReceived >= 20 && register.getNumofTwenties() > 0){
                    System.out.println("Dispensed twenty coin");
                    register.removeMoney(4);
                    totalReceived -= 20;
                }
                else if(totalReceived >= 10 && register.getNumofTens() > 0){
                    System.out.println("Dispensed 10 peso coin");
                    register.removeMoney(3);
                    totalReceived -= 10;
                }
                else if(totalReceived >= 5 && register.getNumofFives() > 0){
                    System.out.println("Dispensed 5 peso coin");
                    register.removeMoney(2);
                    totalReceived -= 5;
                }
                else{
                    System.out.println("Dispensed 1 peso coin");
                    totalReceived -= 1;
                }
            }*/
            return 2;
        }

        else{
            System.out.println("\nGiving change of " + change);
            /*while(change > 0){
                if(change >= 100 && register.getNumofHundreds() > 0){
                    System.out.println("Dispensed hundred bill");
                    register.removeMoney(6);
                    change -= 100;
                }
                else if (change >= 50 && register.getNumofFifties() > 0){
                    System.out.println("Dispensed fifty bill");
                    register.removeMoney(5);
                    change -= 50;
                }
                else if(change >= 20 && register.getNumofTwenties() > 0){
                    System.out.println("Dispensed twenty coin");
                    register.removeMoney(4);
                    change -= 20;
                }
                else if(change >= 10 && register.getNumofTens() > 0){
                    System.out.println("Dispensed 10 peso coin");
                    register.removeMoney(3);
                    change -= 10;
                }
                else if(change >= 5 && register.getNumofFives() > 0){
                    System.out.println("Dispensed 5 peso coin");
                    register.removeMoney(2);
                    change -= 5;
                }
                else{
                    System.out.println("Dispensed 1 peso coin");
                    change -= 1;
                }
            }
            totalReceived = 0;*/
            return 0;
        }
    }

    public void giveChange(int index){
        double change = totalReceived - itemSlot[index].getPrice();
        while(change > 0){
            if(change >= 100 && register.getNumofHundreds() > 0){
                System.out.println("Dispensed hundred bill");
                register.removeMoney(6);
                change -= 100;
            }
            else if (change >= 50 && register.getNumofFifties() > 0){
                System.out.println("Dispensed fifty bill");
                register.removeMoney(5);
                change -= 50;
            }
            else if(change >= 20 && register.getNumofTwenties() > 0){
                System.out.println("Dispensed twenty coin");
                register.removeMoney(4);
                change -= 20;
            }
            else if(change >= 10 && register.getNumofTens() > 0){
                System.out.println("Dispensed 10 peso coin");
                register.removeMoney(3);
                change -= 10;
            }
            else if(change >= 5 && register.getNumofFives() > 0){
                System.out.println("Dispensed 5 peso coin");
                register.removeMoney(2);
                change -= 5;
            }
            else{
                System.out.println("Dispensed 1 peso coin");
                change -= 1;
            }
        }
        totalReceived = 0;
        dispenseItem(index);
    }
    public void giveBackMoney(){
        while(totalReceived > 0){
            if(totalReceived >= 100 && register.getNumofHundreds() > 0){
                System.out.println("Dispensed 100 peso bill");
                register.removeMoney(6);
                totalReceived -= 100;
            }
            else if (totalReceived >= 50 && register.getNumofFifties() > 0){
                System.out.println("Dispensed 50 peso bill");
                register.removeMoney(5);
                totalReceived -= 50;
            }
            else if(totalReceived >= 20 && register.getNumofTwenties() > 0){
                System.out.println("Dispensed 20 peso coin");
                register.removeMoney(4);
                totalReceived -= 20;
            }
            else if(totalReceived >= 10 && register.getNumofTens() > 0){
                System.out.println("Dispensed 10 peso coin");
                register.removeMoney(3);
                totalReceived -= 10;
            }
            else if(totalReceived >= 5 && register.getNumofFives() > 0){
                System.out.println("Dispensed 5 peso coin");
                register.removeMoney(2);
                totalReceived -= 5;
            }
            else{
                System.out.println("Dispensed 1 peso coin");
                totalReceived -= 1;
            }
        }
    }

    private boolean checkChange(int index) {
        double tempTotal = totalReceived - itemSlot[index].getPrice();
        int check = 1;
        int ones = register.getNumofOnes();
        int fives = register.getNumofFives();
        int tens = register.getNumofTens();
        int twenties = register.getNumofTwenties();
        int fifties = register.getNumofFifties();
        int hundreds = register.getNumofHundreds();

        System.out.println(ones);

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
                check = 0;
            }

            if(check == 0){
                return false;
            }


        }
        return true;
    }


	/**
	* Gives the change to the user depending on much money they gave
	*/

	/**
	* Reduces the stock of the selected item by 1 and add the price of the item to the amount of sales regarding the amount sold and money made
	*/
    public void dispenseItem(int index){
        this.itemSlot[index].dispenseThis();
        this.itemSlot[index].addSales();
        System.out.println("Stock is " + this.getItemStock(index));
    }

	/**
	* Prints out the info regarding the items in the vending machine about the amount of items sold and the amount of money made
	*/
    public void printSummary(){
        double total = 0;
        System.out.println("Summary:");
        for(int ctr = 0; ctr < 8; ctr++){
            total = total + itemSlot[ctr].getTotalSales();
            System.out.println(itemSlot[ctr].getName() + ": " + itemSlot[ctr].getNumSold() + " sold, " + itemSlot[ctr].getTotalSales() + " pesos total");
            System.out.println("Initial stock: " + itemSlot[ctr].getInitStock() + ", Current stock: " + itemSlot[ctr].getStock() + "\n");
        }

        System.out.println("\nTotal Sales: " + total + " pesos\n");


        //"there should be a display of the starting inventory and the ending inventory from the last restocking"
    }

	/**
	: @param price The price the user wants to set for the item
	* Allows the user to set the price of an item in the vending machine
	*/
    public void setPrice(int index, double price){
        itemSlot[index].setPrice(price);
    }

	/**
	* Allows the user to add stock to an item in the vending machine
	* @return false if the amount of items in stock plus the value of the paramenter is greater than 10, true if the parameter doesn't meet the conditions of the if statement
	*/
    public void stockItem(int index){
        itemSlot[index].addStock();
        for(int ctr = 0; ctr < 8; ctr++){
            itemSlot[ctr].resetSales();
            itemSlot[ctr].setInitStock(itemSlot[ctr].getStock());
        }
    }
	
	/**
	* Tells the user how much money the vending machine has created
	*/
    public String collectMoney(){
		return register.collectMoney();
	}

	/**
	* Tells the user that the money in the vending machine has been replinshed
	*/
    public void replenishMoney(Money money){
        register.addMoney(money);}
}
