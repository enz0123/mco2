import java.util.ArrayList;

/**
 * Class responsible for storing denominations of money and managing them
 */
public class moneyHolder {
    private ArrayList<Money> ones = new ArrayList<Money>();
    private ArrayList<Money> fives = new ArrayList<Money>();
    private ArrayList<Money> tens = new ArrayList<Money>();
    private ArrayList<Money> twenties = new ArrayList<Money>();
    private ArrayList<Money> fifties = new ArrayList<Money>();
    private ArrayList<Money> hundreds = new ArrayList<Money>();

    /**
     * Gets number of one peso coins in register
     * @return number of one peso coins
     */
    public int getNumofOnes(){ return ones.size();}

    /**
     * Gets number of five peso coins in register
     * @return number of five peso coins
     */
    public int getNumofFives(){return fives.size();}

    /**
     * Gets number of ten peso coins in register
     * @return number of ten peso coins
     */
    public int getNumofTens(){return tens.size();}

    /**
     * Gets number of twenty peso coins in register
     * @return number of twenty peso coins
     */
    public int getNumofTwenties(){return twenties.size();}

    /**
     * Gets number of fifty peso bills in register
     * @return number of fifty peso bills
     */
    public int getNumofFifties(){return fifties.size();}

    /**
     * Gets number of hundred peso bills in register
     * @return number of hundred peso bills in register
     */
    public int getNumofHundreds(){return hundreds.size();}

    /**
     * Inserts money into a compartment in register
     * @param money - money inserted
     */
    public void addMoney(Money money){
        switch(money.getAmount()){ //inserts money in different compartment based on its value
            case 1 -> ones.add(money);
            case 5 -> fives.add(money);
            case 10 -> tens.add(money);
            case 20 -> twenties.add(money);
            case 50 -> fifties.add(money);
            case 100 -> hundreds.add(money);
        }
    }

    /**
     * Removes money from register
     * @param choice - value determines which denomination to remove
     */
    public void removeMoney(int choice){
        switch(choice){
            case 1: ones.remove(ones.size() - 1); break;
            case 2: fives.remove(fives.size() - 1); break;
            case 3: tens.remove(tens.size() - 1); break;
            case 4: twenties.remove(twenties.size() - 1); break;
            case 5: fifties.remove(fifties.size() - 1); break;
            case 6: hundreds.remove(hundreds.size() - 1); break;
        }

    }

    /**
     * Removes every denomination of money from register
     * @return text containing information on number of denominations removed
     */
    public String collectMoney(){
        int total = 0;
        int numOnes = 0;
        int numFives = 0;
        int numTens = 0;
        int numTwenties = 0;
        int numFifties = 0;
        int numHundreds = 0;

        while(ones.size() > 0){
            ones.remove(ones.size() - 1);
            total += 1;
            numOnes += 1;
        }
        while(fives.size() > 0){
            fives.remove(fives.size() - 1);
            total += 5;
            numFives += 1;
        }

        while(tens.size() > 0){
            tens.remove(tens.size() - 1);
            total += 10;
            numTens += 1;
        }

        while(twenties.size() > 0){
            twenties.remove(twenties.size() - 1);
            total += 20;
            numTwenties += 1;
        }
        while(fifties.size() > 0){
            fifties.remove(fifties.size() - 1);
            total += 50;
            numFifties += 1;
        }
        while(hundreds.size() > 0){
            hundreds.remove(hundreds.size() - 1);
            total += 100;
            numHundreds += 1;
        }

        return "₱1 coins collected: " + numOnes + "\n₱5 coins collected: " + numFives + "\n₱10 coins collected: " + numTens + "\n₱20 coins collected: " + numTwenties + "\n₱50 bills collected: " + numFifties + "\n₱100 bills collected: " +numHundreds + "\n\nTotal amount collected: " + total;
    }
}
