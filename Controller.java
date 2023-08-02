import groovyjarjarpicocli.CommandLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class is the controller the helps manage the interplay between the Model and View classes
 */
public class Controller {
    View view;
    VendingMachine vendingMachine;

    /**
     * This is the constructor for the controller class
     *
     * @param view - View class for the Vending Machine Factory Simulation
     */
    public Controller(View view){
        this.view = view;
        view.addCreateVMListener(new CreateVMListener());
        view.addExitListener(new ExitListener());
        view.addTestVMListener(new TestListener());
        view.addRegularListener(new RegularListener());
        view.addSpecialListener(new SpecialListener());
        view.addBackToMainListener(new BackToMainListener());
        view.addVendingFeaturesListener(new VendingFeaturesListener());
        view.addBackToFeaturesButton(new BackToFeaturesListener());
        view.addInsertMoneyListener(new InsertMoneyListener());
        view.addShowMaintenanceMenu(new ShowMaintenanceListener());
        view.addCancelButtonListener(new CancelButtonListener());
        view.addShowSummaryListener(new ShowSummaryListener());
        view.addCollectMoneyListener(new CollectMoneyListener());
        view.addCustomizePizzaListener(new CustomizePizzaListener());
        view.addMoneyContentListener(new MoneyContentListener());
    }

    /**
     * Gets the prices of every kind of item in the vending machine
     *
     * @return prices - prices of every kind of item in the current vending machine
     */

    private double[] getPrices(){
        double[] prices = new double[11];

        for(int ctr =  0; ctr < 8; ctr++)
            prices[ctr] = vendingMachine.getItemPrice(ctr);

        if(vendingMachine instanceof SpecialVendingMachine)
            for(int ctr = 0; ctr < 3; ctr++)
                prices[ctr + 8] = ((SpecialVendingMachine) vendingMachine).getExtraItemPrice(ctr);

        return prices;
    }

    /**
     * Gets the calories of every kind of item in the vending machine
     *
     * @return calories - calories of every kind of item in the current vending machine
     */

    private double[] getCalories(){
        double[] calories = new double[11];

        for(int ctr = 0; ctr < 8; ctr++)
            calories[ctr] = vendingMachine.getItemCalories(ctr);

        if(vendingMachine instanceof SpecialVendingMachine)
            for(int ctr = 0; ctr < 3; ctr++)
                calories[ctr + 8] = ((SpecialVendingMachine) vendingMachine).getExtraItemCalories(ctr);

        return calories;
    }

    /**
     * Gets the stocks of every kind of item in the vending machine
     *
     * @return stocks - stocks of every kind of item in the current vending machine
     */

    private int[] getStocks(){
        int[] stocks = new int[11];

        for(int ctr = 0; ctr < 8; ctr++)
            stocks[ctr] = vendingMachine.getItemStock(ctr);

        if(vendingMachine instanceof SpecialVendingMachine)
            for(int ctr = 0; ctr < 3; ctr++)
                stocks[ctr + 8] = ((SpecialVendingMachine) vendingMachine).getExtraItemStock(ctr);

        return stocks;
    }

    /**
     * This class provides a reaction when the Money Content Button is pressed
     */

    class MoneyContentListener implements ActionListener{

        /**
         * When button is pressed, shows a dialog showing the number of denominations of money in the vending machine.
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showMoneyContent(vendingMachine.getRegister().getNumofOnes(), vendingMachine.getRegister().getNumofFives(),
                    vendingMachine.getRegister().getNumofTens(), vendingMachine.getRegister().getNumofTwenties(), vendingMachine.getRegister().getNumofFifties(),
                    vendingMachine.getRegister().getNumofHundreds());
        }
    }

    /**
     * This class provides a reaction when the Customize Pizza Button is pressed
     */

    class CustomizePizzaListener implements ActionListener{
        /**
         * When Customize Pizza button is pressed, window for customizing pizza is shown including the initialization of its contents. Listeners are also added for buttons in the window.
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.disposeCustomizeWindow();
            view.initializeCustomizeScreen(getStocks(), getPrices(), getCalories());
            view.addAddListener(new AddListener());
            view.addRemoveListener(new RemoveListener());
            view.addMakePizzaListener(new MakePizzaListener());
        }
    }

    /**
     * This class provides a reaction when the Make Pizza button is pressed
     */
    class MakePizzaListener implements ActionListener{
        /**
         * When button is pressed, depending on the status of the transaction, a pizza may or may not be made based on customizations made.
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] totalAmounts = view.getTotalAmounts();

            if(view.getTotalAmounts()[0] == 0)
                JOptionPane.showMessageDialog(null, "At least 1 pizza dough is needed to make pizza.", "Error", JOptionPane.ERROR_MESSAGE);
            else
                switch(((SpecialVendingMachine) vendingMachine).makePizza(view.getTotalAmounts())){ //Tells whether a pizza should be made or if transaction pushes through
                    case 1 -> { //This case shows the progress bar for making of the pizza and updates labels
                        view.showProgressScreen(totalAmounts, vendingMachine.getChangeLog());
                        for(int ctr = 0; ctr < 8; ctr++)
                            view.setStockText("Stock: " + vendingMachine.getItemStock(ctr), ctr);
                        for(int ctr = 0; ctr < 3; ctr++)
                            view.setExtraStockText("Stock: " + ((SpecialVendingMachine) vendingMachine).getExtraItemStock(ctr), ctr);
                        view.disposeCustomizeWindow();
                        view.setTotalLabel("Total: " + vendingMachine.getTotalReceived());
                        vendingMachine.resetChangeLog();
                    }
                    case 2 -> JOptionPane.showMessageDialog(null, "Error. Not enough change in machine", "Error", JOptionPane.ERROR_MESSAGE);
                    case 3 -> JOptionPane.showMessageDialog(null, "Not enough money inserted.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            view.updatePictures(getStocks());//number of pictures shown depends on stock
        }
    }

    /**
     * This class provides a reaction when the "+" button when customizing pizza is pressed
     */
    class AddListener implements ActionListener{
        /**
         * Adds desired number of ingredients for pizza depending on stock
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonName = ((JButton) e.getSource()).getName();
            int index = Integer.parseInt("" + buttonName.charAt(0));

            //if statements determine whether buttons are connected to main or extra ingredients. If desired number of ingredients surpass stock, error is shown
            if(buttonName.contains("main") && view.getCustomizableAmount(buttonName) == vendingMachine.getItemStock(index))
                JOptionPane.showMessageDialog(null, "Not enough stock in vending machine.", "Error", JOptionPane.ERROR_MESSAGE);
            else if(buttonName.contains("extra") && view.getCustomizableAmount(buttonName) == ((SpecialVendingMachine) vendingMachine).getExtraItemStock(index))
                JOptionPane.showMessageDialog(null, "Not enough stock in vending machine.", "Error", JOptionPane.ERROR_MESSAGE);
            else
            {  //Updates labels based on input
                view.increaseLabel(((JButton) e.getSource()).getName());
                view.updateTotalInfo(getPrices(), getCalories());
            }

        }
    }

    /**
     * This class provides a reaction when the "-" button when customizing pizza is pressed
     */
    class RemoveListener implements ActionListener{
        /**
         * Decreases desired amount of ingredients when making pizza
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonName = ((JButton) e.getSource()).getName();

            if(view.getCustomizableAmount(buttonName) != 0){ //Negative amount of ingredients are not allowed
                view.decreaseLabel(buttonName);
                view.updateTotalInfo(getPrices(), getCalories());
            }

        }
    }

    /**
     * This class provides a reaction when the Collect Money button is pressed
     */
    class CollectMoneyListener implements ActionListener{
        /**
         * Screen showing how many of each denomination of money was taken out is shown
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.initializeCollectMoney(vendingMachine.collectMoney());
        }
    }

    /**
     * This class provides a reaction when the Show Summary button is pressed
     */
    class ShowSummaryListener implements ActionListener{
        /**
         * Shows screen detailing number sold, sales, and inventory status for each item
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            double totalSales = 0;
            view.initializeSummary();
            for(int ctr = 0; ctr < 8; ctr ++){ //Gets total sales of vending machine
                totalSales += vendingMachine.getSales(ctr);
                view.addSummaryInfo(ctr, vendingMachine.getItemName(ctr), vendingMachine.getInitStock(ctr), vendingMachine.getItemStock(ctr), vendingMachine.getNumSold(ctr),vendingMachine.getSales(ctr));
            }

            if(vendingMachine instanceof SpecialVendingMachine){ //Includes sales of extra items if vending machine is a special vending machine
                for(int ctr = 0; ctr < 3; ctr++){
                    totalSales += ((SpecialVendingMachine) vendingMachine).getExtraItemTotalSales(ctr);
                    view.addExtraSummaryInfo(ctr, ((SpecialVendingMachine) vendingMachine).getExtraItemName(ctr),
                            ((SpecialVendingMachine) vendingMachine).getExtraItemInitStock(ctr), ((SpecialVendingMachine) vendingMachine).getExtraItemStock(ctr)
                            , ((SpecialVendingMachine) vendingMachine).getExtraItemNumSold(ctr), ((SpecialVendingMachine) vendingMachine).getExtraItemTotalSales(ctr));
                }

            }

            if(vendingMachine instanceof SpecialVendingMachine) //Special vending machine takes pizzas sold into account
                view.showSummary(totalSales, ((SpecialVendingMachine) vendingMachine).getPizzaSold());
            else
                view.showSummary(totalSales);
        }
    }

    /**
     * This class provides a reaction when the Restock button is pressed
     */
    class RestockListener implements ActionListener{
        /**
         * Increases stock by one of current item
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            SlotButton sourceButton = (SlotButton) e.getSource();
            int index = sourceButton.getIndex();
            int stock;

            if(vendingMachine instanceof SpecialVendingMachine)
                ((SpecialVendingMachine) vendingMachine).resetPizzaSold();

            if(vendingMachine instanceof SpecialVendingMachine && sourceButton.getName().contains("extra")){ //Differentiates between extra and main ingredient slots
                stock = ((SpecialVendingMachine) vendingMachine).getExtraItemStock(index);
            }

            else
                stock = vendingMachine.getItemStock(index);

            if(stock == 10) //shows an error if item is full
                JOptionPane.showMessageDialog(null, "Error. Slot is full.", "Stock full", JOptionPane.ERROR_MESSAGE);
            else if(vendingMachine instanceof SpecialVendingMachine && sourceButton.getName().contains("extra")){
                ((SpecialVendingMachine) vendingMachine).stockExtraItem(index);
                view.setExtraStockText("Stock: " + ((SpecialVendingMachine) vendingMachine).getExtraItemStock(index), index);

            }
            else{
                vendingMachine.stockItem(index);
                view.setStockText("Stock: " + vendingMachine.getItemStock(index), index);
            }

            view.updatePictures(getStocks()); //Updates number of pictures based on stock
        }
    }

    /**
     * This class provides a reaction when the Dispense button is pressed
     */
    class DispenseListener implements ActionListener{
        /**
         * Depending on the status of transaction, one item from the selected item slot may be dispensed
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = ((SlotButton) e.getSource()).getIndex();
            int result = vendingMachine.checkTransaction(index);

            if(vendingMachine.getItemStock(index) == 0)
                JOptionPane.showMessageDialog(null, "Error. Selected item is out of stock.", "Error", JOptionPane.ERROR_MESSAGE);
            else{
                switch (result) {//Determines status of transaction and whether to dispense item
                    case 1 ->
                            JOptionPane.showMessageDialog(null, "Error. Not enough money was inserted.", "Error", JOptionPane.ERROR_MESSAGE);
                    case 2 ->
                            JOptionPane.showMessageDialog(null, "Error. Unable to give change.", "Error", JOptionPane.ERROR_MESSAGE);
                    case 0 -> {
                        vendingMachine.giveChange(index);
                        view.setStockText("Stock: " + vendingMachine.getItemStock(index), index);
                        view.setTotalLabel("Total: " + vendingMachine.getTotalReceived());
                        view.disposeCustomizeWindow();
                        view.showChangeScreen(vendingMachine.getChangeLog(), index, (JButton) e.getSource());
                        vendingMachine.resetChangeLog();
                    }
                }

            }

            view.updatePictures(getStocks());
        }
    }

    /**
     * Provides a reaction when Confirm Price button is pressed
     */
    class ConfirmPriceListener implements ActionListener{
        /**
         * Sets price of the current item slot and updates accordingly
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e){
            String newPrice = view.getNewPrice();
            view.disposePopup();

            if(newPrice.matches("\\d+")){ //Makes sure inputted value is a whole number
                if(vendingMachine instanceof SpecialVendingMachine){
                    if(((SlotButton) e.getSource()).getName().contains("extra")){ //Determines if extra ingredient slots are to be updated or main ingredients
                        view.setExtraPriceText(Double.parseDouble(newPrice), ((SlotButton) e.getSource()).getIndex());
                        ((SpecialVendingMachine) vendingMachine).setExtraPrice(((SlotButton) e.getSource()).getIndex(), Double.parseDouble(newPrice));
                    }
                    else{//
                        view.setPriceText(Double.parseDouble(newPrice), ((SlotButton) e.getSource()).getIndex());
                        vendingMachine.setPrice(((SlotButton) e.getSource()).getIndex(), Double.parseDouble(newPrice));
                    }


                }
                else{
                    view.setPriceText(Double.parseDouble(newPrice), ((SlotButton) e.getSource()).getIndex());
                    vendingMachine.setPrice(((SlotButton) e.getSource()).getIndex(), Double.parseDouble(newPrice));
                }

            }
            else //occurs when invalid value is inputted
                JOptionPane.showMessageDialog(null, "Error. Non-integer characters are not allowed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Provides a reaction when the Cancel button is pressed
     */
    class CancelButtonListener implements ActionListener{
        /**
         * Returns the money inserted in the vending machine
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            vendingMachine.giveBackMoney();
            view.showCancelResultScreen(vendingMachine.getChangeLog());
            vendingMachine.resetChangeLog();
            view.setTotalLabel("Total: " + vendingMachine.getTotalReceived());
        }
    }

    /**
     * Provides a reaction when the Set Price button is pressed
     */
    class SetPriceListener implements ActionListener {
        /**
         * Shows a window where you can input the new price
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.initializePopupPrice((SlotButton) e.getSource(), 0, 0, ((SlotButton) e.getSource()).getIndex());
            view.addConfirmPriceListener(new ConfirmPriceListener());
            view.showPopupPrice();
        }
    }

    /**
     * Provides a reaction when the Maintenance features are being accessed
     */
    class ShowMaintenanceListener implements ActionListener{
        /**
         * Shows a different display depending on the type of the current vending machine
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vendingMachine instanceof SpecialVendingMachine)
                view.showMaintenanceMenu(2);
            else
                view.showMaintenanceMenu(1);

        }
    }

    /**
     * Provides a reaction when one of the Money buttons are pressed
     */
    class InsertMoneyListener implements ActionListener{
        /**
         * Inserts money in the vending machine depending on which button was pressed
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();

            //If blocks to determine which denomination of money to insert based on the text on the button
            if(sourceButton.getText().contains("₱1 "))
            {
                System.out.println("1 peso"); //change
                vendingMachine.addMoney(new Money(1));
            }
            else if(sourceButton.getText().contains("₱5 "))
            {
                System.out.println("5 peso");
                vendingMachine.addMoney(new Money(5));
            }
            else if(sourceButton.getText().contains("₱10 "))
            {
                System.out.println("10 peso");
                vendingMachine.addMoney(new Money(10));
            }
            else if(sourceButton.getText().contains("₱20"))
            {
                System.out.println("20 peso");
                vendingMachine.addMoney(new Money(20));
            }
            else if(sourceButton.getText().contains("₱50 "))
            {
                System.out.println("50 pesos");
                vendingMachine.addMoney(new Money(50));
            }
            else
            {
                System.out.println("100 pesos");
                vendingMachine.addMoney(new Money(100));
            }

            view.setTotalLabel("Total: " + vendingMachine.getTotalReceived());


        }
    }

    /**
     * Provides a reaction when the Back to Features button is pressed
     */
    class BackToFeaturesListener implements ActionListener{
        /**
         * Disposes the customizing pizza display (if visible) then goes back to choosing desired feature to test
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.disposeCustomizeWindow();
            view.showTestMenu();
        }
    }

    /**
     * Provides a reaction when the Back to Main button is pressed
     */
    class BackToMainListener implements ActionListener{
        /**
         * Goes back to the main menu
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showMainMenu();
        }
    }

    /**
     * Provides a reaction when the Create Vending Machine button is pressed
     */
    class CreateVMListener implements ActionListener{
        /**
         * Shows screen to pick desired kind of vending machine to be created.
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showCreateMenu();
        }
    }

    /**
     * Provides a reaction when the Exit button is pressed
     */
    class ExitListener implements ActionListener{
        /**
         * Closes program
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.disposeWindow();
        }
    }

    /**
     * Provides a reaction when pressing button to create Regular Vending Machine
     */
    class RegularListener implements ActionListener{
        /**
         * Creates a regular vending machine and initializes some of its features
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            vendingMachine = new VendingMachine();
            view.showMainMenu();
            view.removeAllSlots(); //removes all item slot displays to avoid duplication
            for(int ctr = 0; ctr < 8; ctr++)
                view.initializeSlot(ctr, vendingMachine.getItemName(ctr), vendingMachine.getItemCalories(ctr), vendingMachine.getItemPrice(ctr));
            view.addRestockListener(new RestockListener());
            view.addSetPriceListener(new SetPriceListener());
            view.addDispenseListener(new DispenseListener());

            JOptionPane.showMessageDialog(null, "Regular vending machine has been created.","Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Provides a reaction when pressing button to create Special Vending Machine
     */
    class SpecialListener implements ActionListener{
        /**
         * Creates a special vending machine and initializes some of its features
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            vendingMachine = new SpecialVendingMachine();
            view.showMainMenu();
            view.removeAllSlots();
            for(int ctr = 0; ctr < 8; ctr++) //initializes main ingredients
                view.initializeSlot(ctr, vendingMachine.getItemName(ctr), vendingMachine.getItemCalories(ctr), vendingMachine.getItemPrice(ctr));
            for(int ctr = 0; ctr < 3; ctr++) //initializes extra ingredients
                view.initializeExtras(ctr, ((SpecialVendingMachine) vendingMachine).getExtraItemName(ctr), ((SpecialVendingMachine) vendingMachine).getExtraItemCalories(ctr), ((SpecialVendingMachine) vendingMachine).getExtraItemPrice(ctr));
            view.addRestockListener(new RestockListener());
            view.addSetPriceListener(new SetPriceListener());
            view.addDispenseListener(new DispenseListener());
            JOptionPane.showMessageDialog(null, "Special vending machine has been created.","Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Provides a reaction when pressing Test Vending Machine button
     */
    class TestListener implements ActionListener{
        /**
         * Shows "choose features" screen if there's a vending machine to be tested
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vendingMachine == null)
                JOptionPane.showMessageDialog(null, "Error. Please create vending machine before testing.", "Error", JOptionPane.ERROR_MESSAGE);
            else{
                view.showTestMenu();
            }

        }
    }

    /**
     * Provides reaction when Test Vending Features button is pressed
     */
    class VendingFeaturesListener implements ActionListener{
        /**
         * Shows interface for vending and resets features to ensure consistency
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            vendingMachine.resetTotalReceived();
            view.setTotalLabel("Total: " + vendingMachine.getTotalReceived());
            if(vendingMachine instanceof SpecialVendingMachine)
                view.showVendingFeatures(2);
            else
                view.showVendingFeatures(1);
        }
    }
}
