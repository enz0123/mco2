public class SpecialVendingMachine extends VendingMachine{
    private itemSlot[] extraSlot;
    private double pizzaCost = 0;
    private int pizzaSold = 0;

    public SpecialVendingMachine(){
        super();
        extraSlot = new itemSlot[3];

        extraSlot[0] = new itemSlot("Garlic Powder", 5, 10);
        extraSlot[1] = new itemSlot("Spice", 5, 15);
        extraSlot[2] = new itemSlot("Truffle Oil", 10, 25);
    }

    private boolean checkChange(double pizzaCost){
        double tempTotal = getTotalReceived() - pizzaCost;
        int check = 1;
        int ones = getRegister().getNumofOnes();
        int fives = getRegister().getNumofFives();
        int tens = getRegister().getNumofTens();
        int twenties = getRegister().getNumofTwenties();
        int fifties = getRegister().getNumofFifties();
        int hundreds = getRegister().getNumofHundreds();

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

    public void dispenseExtra(int index){
        this.extraSlot[index].dispenseThis();
        this.extraSlot[index].addSales();
        System.out.println(extraSlot[index].getName() + " Stock is: " + extraSlot[index].getStock());
    }

    public int makePizza(int[] amounts){ //case 1: pizza made, case 2: not enough change, case 3: not enough money
        pizzaCost = 0;

        for(int ctr = 0; ctr < 8; ctr++)
            pizzaCost += amounts[ctr] * getItemPrice(ctr);
        for(int ctr = 0; ctr < 3; ctr++)
            pizzaCost += amounts[ctr+8] * getExtraItemPrice(ctr);

        if(pizzaCost > getTotalReceived())
            return 3;

        if(!checkChange(pizzaCost))
            return 2;

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

        System.out.println("Pizza made");
        pizzaSold += 1;
        return 1;
    }

    private void giveChange(double pizzaCost){
        double change = pizzaCost - getTotalReceived();
        moneyHolder register = getRegister();
        while(change > 0)
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

        this.resetTotalReceived();
    }

    public String getExtraItemName(int index){return extraSlot[index].getName();}
    public double getExtraItemCalories(int index){return extraSlot[index].getCalories();}
    public double getExtraItemPrice(int index){return extraSlot[index].getPrice();}
    public int getExtraItemStock(int index){return extraSlot[index].getStock();}

    public int getExtraItemInitStock(int index){return extraSlot[index].getInitStock();}

    public int getExtraItemNumSold(int index){return extraSlot[index].getInitStock();}

    public double getExtraItemTotalSales(int index){return extraSlot[index].getTotalSales();}

    public void stockExtraItem(int index){extraSlot[index].addStock();}

    public void setExtraPrice(int index, double price){extraSlot[index].setPrice(price);}

    public void resetPizzaSold(){pizzaSold = 0;}

    public int getPizzaSold(){return pizzaSold;}

}

