import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class handles what the user sees on screen.
 */

public class View {
    private JFrame frame;
    private JPanel container;
    private JPanel mainMenu;
    private JPanel mainBtnContainer;
    private JButton createVendingMachineButton;
    private JButton testVendingMachineButton;
    private JButton exitButton;
    private JPanel selectCreate;
    private JButton regularButton;
    private JButton specialButton;
    private JPanel selectButtonPanel;
    private JPanel testMenu;
    private JButton backToMainMenuButton;
    private JPanel testMenuBtnContainer;
    private JButton vendingFeaturesButton; //do listener
    private JButton maintenanceFeaturesButton; //do listener
    private JPanel backToMainMenuContainer;
    private JPanel vending;
    private JPanel itemSlotsContainer;
    private JButton backToFeaturesButton;
    private JLabel doughStock;
    private JLabel doughCalories;
    private JLabel doughPrice;
    private JLabel sauceStock;
    private JLabel sauceCalories;
    private JLabel saucePrice;
    private JLabel mozzarellaStock;
    private JLabel mozzarellaCalories;
    private JLabel mozzarellaPrice;
    private JLabel pepperoniStock;
    private JLabel pepperoniCalories;
    private JLabel pepperoniPrice;
    private JLabel pineappleStock;
    private JLabel pineappleCalories;
    private JLabel pineapplePrice;
    private JLabel spinachStock;
    private JLabel spinachCalories;
    private JLabel spinachPrice;
    private JLabel gorgonzolaStock;
    private JLabel gorgonzolaCalories;
    private JLabel gorgonzolaPrice;
    private JLabel hamStock;
    private JLabel hamPrice;
    private JLabel receivedMoney;
    private JPanel insertMoneyPanel;
    private JButton ₱1CoinButton;
    private JButton ₱5CoinButton;
    private JButton ₱10CoinButton;
    private JButton ₱20CoinButton;
    private JButton ₱50BillButton;
    private JButton ₱100BillButton;
    private JButton cancelButton;
    private JButton showSummaryButton;
    private JButton collectMoneyButton;
    private JPanel otherFeatures;
    private JPanel bottomRight;
    private JPanel totalReceivedPanel;
    private JButton customizePizzaButton;
    private JPanel customizableItemPanel;
    private JButton registerContentButton;
    private JLabel pizzaPictureLabel;
    private JPanel moneyButtonsPanel;
    private CardLayout cardLayout = (CardLayout) container.getLayout();
    private slotView[] slot;
    private slotView[] extraSlotsMaintenance;
    private JDialog priceDialog;
    private JPanel setPricePanel;
    private SlotButton confirmPriceBtn;
    private JTextField priceTextField;
    private JDialog summaryDialog;
    private JPanel summaryContainer;
    private JLabel[] itemSummaryInfo;
    private JDialog collectMoneyDialog;
    private JLabel collectedMoneyInfo;
    private JLabel replenishMoney;
    private JFrame customizeScreen;
    private CustomizeSlotPanel[] customizeSlot;
    private extraSlotsPanel[] extraSlots;
    private JButton makePizza;
    private JLabel totalPrice;
    private JLabel totalCalories;
    private ImageIcon[] pictures;
    private ImageIcon pizzaPicture;

    public View() {
        //The next lines initialize the pictures for each slot being resized to fit
        pictures = new ImageIcon[11];
        pictures[0] = resizeImageIcon(new ImageIcon(".\\MCO Pictures\\pizzaDough.png"), 25, 25);
        pictures[1] = resizeImageIcon(new ImageIcon(".\\MCO Pictures\\tomatoSauce.png"), 25, 25);
        pictures[2] = resizeImageIcon(new ImageIcon(".\\MCO Pictures\\mozzarellaCheese.png"), 25, 25);
        pictures[3] = resizeImageIcon(new ImageIcon(".\\MCO Pictures\\pepperoni.png"), 25, 25);
        pictures[4] = resizeImageIcon(new ImageIcon(".\\MCO Pictures\\pineapple.png"), 25, 25);
        pictures[5] = resizeImageIcon(new ImageIcon(".\\MCO Pictures\\spinach.png"), 25, 25);
        pictures[6] = resizeImageIcon(new ImageIcon(".\\MCO Pictures\\gorgonzola.png"), 25, 25);
        pictures[7] = resizeImageIcon(new ImageIcon(".\\MCO Pictures\\ham.png"), 25, 25);
        pictures[8] = resizeImageIcon(new ImageIcon(".\\MCO Pictures\\garlicPowder.png"), 25, 25);
        pictures[9] = resizeImageIcon(new ImageIcon(".\\MCO Pictures\\spice.png"), 25, 25);
        pictures[10] = resizeImageIcon(new ImageIcon(".\\MCO Pictures\\truffleOil.png"), 25, 25);

        pizzaPicture = resizeImageIcon(new ImageIcon(".\\MCO Pictures\\pizza.png"), 100, 100);
        pizzaPictureLabel.setIcon(pizzaPicture);
        pizzaPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pizzaPictureLabel.setBackground(Color.black);

        //The next lines initialize the displays for the slots and main frame in the vending machine
        slot = new slotView[8];
        extraSlotsMaintenance = new slotView[3];
        frame = new JFrame("Vending Machine Factory");
        frame.setContentPane(this.container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        itemSlotsContainer.setLayout(new GridLayout(4, 3));
        frame.setSize(1000,800);
        frame.setLocationRelativeTo(null);
        replenishMoney = new JLabel("Replenish money");

        frame.setVisible(true);
    }

    /**
     * Updates how many of each picture are display in a slot depending on the stock of each item
     * @param stock - number of stock of every kind of item
     */
    public void updatePictures(int[] stock){
        ImageIcon tempPicture;

        //updates slots for main ingredients
        for(int ctr = 0; ctr < 8; ctr++){
            slot[ctr].clearPictures();
            for(int ctr2 = 0; ctr2 < stock[ctr]; ctr2++){
                System.out.println("ctr " + ctr + "\nctr2 " + ctr2 + "stock: " + stock[ctr] + "\n");
                tempPicture = pictures[ctr];
                slot[ctr].addPicture(new JLabel(tempPicture));
            }
        }

        //updates slots for extra ingredients if VM is special
        if(extraSlotsMaintenance[0] != null)
            for(int ctr = 0; ctr < 3; ctr++){
                extraSlotsMaintenance[ctr].clearPictures();
                for(int ctr2 = 0; ctr2 < stock[ctr + 8]; ctr2++){
                    tempPicture = pictures[ctr + 8];
                    extraSlotsMaintenance[ctr].addPicture(new JLabel(tempPicture));
                }

            }

        frame.revalidate();
        frame.repaint();
    }

    /**
     * Initializes what is seen when user wants to customize pizza
     * @param extraStocks - list of stocks of extra ingredients
     * @param extraPrices - list of prices of extra ingredients
     * @param extraCalories - list of calories of extra ingredients
     */
    public void initializeCustomizeScreen(int[] extraStocks, double[] extraPrices, double[] extraCalories){
        customizeScreen = new JFrame("Customize Pizza");
        customizeScreen.setLayout(new GridLayout(15, 1));

        JLabel mainIngredients = new JLabel("Main Ingredients");
        mainIngredients.setHorizontalAlignment(SwingConstants.CENTER);
        mainIngredients.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        customizeScreen.add(mainIngredients);

        //initializes display for main ingredients in customize screen
        customizeSlot = new CustomizeSlotPanel[8];
        for(int ctr = 0; ctr < 8; ctr++) {
            customizeSlot[ctr] = new CustomizeSlotPanel();
            customizeSlot[ctr].setAddButtonName(ctr + "main");
            customizeSlot[ctr].setRemoveButtonName(ctr + "main");
            customizeSlot[ctr].setPreferredSize(new Dimension(100, 25));
            customizeScreen.add(customizeSlot[ctr]);
        }

        //setting item name helps program decipher which buttons are being referred to
        customizeSlot[0].setItemName("Pizza Dough");
        customizeSlot[1].setItemName("Tomato Sauce");
        customizeSlot[2].setItemName("Mozzarella Cheese");
        customizeSlot[3].setItemName("Pepperoni");
        customizeSlot[4].setItemName("Pineapple");
        customizeSlot[5].setItemName("Spinach");
        customizeSlot[6].setItemName("Gorgonzola");
        customizeSlot[7].setItemName("Ham");


        extraSlots = new extraSlotsPanel[3];

        JLabel extraSeasoning = new JLabel("Extra Seasoning");
        extraSeasoning.setHorizontalAlignment(SwingConstants.CENTER);
        extraSeasoning.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        customizeScreen.add(extraSeasoning);

        //initializes display for extra ingredients in customize screen; setting button names to "extra" helps program decide which labels to
        //update
        for(int ctr = 0; ctr < 3; ctr++){
            extraSlots[ctr] = new extraSlotsPanel();
            extraSlots[ctr].setAddButtonName(ctr + "extra");
            extraSlots[ctr].setRemoveButtonName(ctr + "extra");
            customizeScreen.add(extraSlots[ctr]);

        }

        extraSlots[0].setItemName("Garlic Powder");
        extraSlots[1].setItemName("Spice");
        extraSlots[2].setItemName("Truffle Oil");

        JPanel totalInfo = new JPanel(new FlowLayout());
        totalPrice = new JLabel("Total Price: 0");
        totalCalories = new JLabel("Total Calories: 0");

        totalInfo.add(totalPrice);
        totalInfo.add(totalCalories);
        customizeScreen.add(totalInfo);

        makePizza = new JButton("Make Pizza");
        customizeScreen.add(makePizza);

        customizeScreen.pack();
        customizeScreen.setVisible(true);
    }

    /**
     * Initializes display when money is being collected
     * @param info - String that contains information on the number of each denomination being collected
     */
    public void initializeCollectMoney(String info){
        String newInfo = info.replace("\n", "<br/>"); //replaces newlines, since they don't work in JLabels
        collectMoneyDialog = new JDialog();
        collectMoneyDialog.setUndecorated(false);
        collectMoneyDialog.setTitle("Collected money");

        collectedMoneyInfo = new JLabel("<html>" + newInfo + "<html"); //includes "html" to help in creating new lines
        collectMoneyDialog.add(collectedMoneyInfo);
        collectMoneyDialog.pack();
        collectMoneyDialog.setLocationRelativeTo(null);
        collectMoneyDialog.setVisible(true);
    }

    /**
     * Initializes display when summary of transactions is requested
     */
    public void initializeSummary(){
        summaryDialog = new JDialog();
        summaryDialog.setTitle("Summary");
        summaryDialog.setUndecorated(false);
        summaryContainer = new JPanel(new GridLayout(7,2 ));
        summaryDialog.add(summaryContainer);
        itemSummaryInfo = new JLabel[11];
        for (int i = 0; i < itemSummaryInfo.length; i++) {
            itemSummaryInfo[i] = new JLabel();
        }
    }

    /**
     * Sets the summary of transactions display visible (for regular vending machine)
     * @param totalSales - sum of all sales in vending machine
     */
    public void showSummary(double totalSales){
        summaryContainer.add(new JLabel("Total Sales: " + totalSales + " pesos"));
        summaryDialog.pack();
        summaryDialog.setLocationRelativeTo(null);
        summaryDialog.setVisible(true);
    }

    /**
     *Sets the summary of transactions display visible (for special vending machine)
     * @param totalSales - sum of all sales in vending machine
     * @param pizzasSold - number of pizzas sold from the current vending machine
     */
    public void showSummary(double totalSales, int pizzasSold){
        summaryContainer.add(new JLabel("<html>Total Sales: " + totalSales + " pesos<br/>Pizzas sold: " + pizzasSold + "<html>"));
        summaryDialog.pack();
        summaryDialog.setLocationRelativeTo(null);
        summaryDialog.setVisible(true);
    }

    /**
     * Displays how many of each denomination of money is in the register of the current vending machine
     * @param ones - number of one peso coins in the VM
     * @param fives - number of five peso coins in the VM
     * @param tens - number of ten peso coins in the VM
     * @param twenties - number of twenty peso coins in the VM
     * @param fifties - number of fifty peso bills in the VM
     * @param hundreds - number of hundred peso bills in the VM
     */
    public void showMoneyContent(int ones, int fives, int tens, int twenties, int fifties, int hundreds){
        JDialog moneyDialog = new JDialog();
        moneyDialog.setTitle("Register Content");
        JPanel moneyContainer = new JPanel();
        //using html and br help in creating new lines in the JLabel, since \n doesn't work.
        JLabel moneyLabel = new JLabel("<html>₱1 coins: " + ones + "<br/>₱5 coins: " + fives +
                "<br/>₱10 coins: " + tens + "<br/>₱20 coins: " + twenties + "<br/>₱50 bills: " + fifties + "<br/>₱100 bills: " + hundreds);

        moneyContainer.add(moneyLabel);
        moneyDialog.getContentPane().add(moneyContainer);

        moneyDialog.pack();
        moneyDialog.setVisible(true);
    }

    /**
     * Adds information of the main ingredients in the summary of transactions
     * @param index - current item slot number to be added
     * @param name - name of current item slot
     * @param initStock - initial stock of current item slot
     * @param currentStock - current stock of current item slot
     * @param numSold - number of items sold in current item slot
     * @param sales - total number of sales since last restocking
     */
    public void addSummaryInfo(int index, String name, int initStock, int currentStock, int numSold, double sales){
        itemSummaryInfo[index].setText("<html>" + name + "<br/>Number sold: " + numSold + "<br/>Sales: " + sales + " pesos<br/>Initial Stock: " + initStock + "<br/>Current Stock: " + currentStock + "<br/><br/><html>");
        itemSummaryInfo[index].setVerticalAlignment(SwingConstants.CENTER);
        itemSummaryInfo[index].setVerticalAlignment(SwingConstants.CENTER);
        itemSummaryInfo[index].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        summaryContainer.add(itemSummaryInfo[index]);
    }

    /**
     * Adds information of the extra ingredients in the summary of transactions
     * @param index - current item slot number to be added
     * @param name - name of current item slot
     * @param initStock - initial stock of current item slot
     * @param currentStock - current stock of current item slot
     * @param numSold - number of items sold in current item slot
     * @param sales - total number of sales since last restocking
     */
    public void addExtraSummaryInfo(int index, String name, int initStock, int currentStock, int numSold, double sales){
        itemSummaryInfo[index+8].setText("<html>" + name + "<br/>Number sold: " + numSold + "<br/>Sales: " + sales + " pesos<br/>Initial Stock: " + initStock + "<br/>Current Stock: " + currentStock + "<br/><br/><html>");
        itemSummaryInfo[index+8].setVerticalAlignment(SwingConstants.CENTER);
        itemSummaryInfo[index+8].setVerticalAlignment(SwingConstants.CENTER);
        itemSummaryInfo[index+8].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        summaryContainer.add(itemSummaryInfo[index+8]);
    }

    /**
     * Removes all contents in the container that displays item slots
     */
    public void removeAllSlots(){
        itemSlotsContainer.removeAll();
    }

    /**
     * Initializes contents of display of main ingredient slots
     * @param slotNum - index of current item slot being initialized
     * @param name - name of current item slot
     * @param calories - number of calories of items in current item slot
     * @param price - price of items in current item slot
     */
    public void initializeSlot(int slotNum, String name, double calories, double price){
        this.slot[slotNum] = new slotView(slotNum, name, calories, price);
        this.slot[slotNum].setButtonName("main");
        this.slot[slotNum].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.slot[slotNum].setBlackContainer();
        itemSlotsContainer.add(this.slot[slotNum]);
    }

    /**
     * Initializes contents of display of extra ingredient slots
     * @param slotNum - index of current item slot being initialized
     * @param name - name of current item slot
     * @param calories - number of calories of items in current item slot
     * @param price - price of items in current item slot
     */
    public void initializeExtras(int slotNum, String name, double calories, double price){
        this.extraSlotsMaintenance[slotNum] = new slotView(slotNum, name, calories, price);
        this.extraSlotsMaintenance[slotNum].setButtonName("extra");
        this.extraSlotsMaintenance[slotNum].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.extraSlotsMaintenance[slotNum].setBlackContainer();
        itemSlotsContainer.add(this.extraSlotsMaintenance[slotNum]);
    }

    /**
     * Gets the desired number of an item when customizing pizza
     * @param buttonName - name of the button that was pressed
     * @return desired number of an item
     */
    public double getCustomizableAmount(String buttonName){
        int index = Integer.parseInt("" + buttonName.charAt(0)); //gets index of the button which is the first character in its name

        if(buttonName.contains("main")) //determines if button is from main or extra ingredient
            return customizeSlot[index].getAmount();
        return extraSlots[index].getAmount();
    }

    /**
     * Increases the desired number of an item when customizing pizza
     * @param buttonName - name of the button that was pressed
     */
    public void increaseLabel(String buttonName){
        int index = Integer.parseInt("" + buttonName.charAt(0));  //gets index of the button which is the first character in its name

        if(buttonName.contains("main")) //determines if button is from main or extra ingredient
            customizeSlot[index].increaseAmountLabel();
        else
            extraSlots[index].increaseAmountLabel();

    }

    /**
     * Decreases the desired number of an item when customizing pizza
     * @param buttonName - name of the button that was pressed
     */
    public void decreaseLabel(String buttonName){
        int index = Integer.parseInt("" + buttonName.charAt(0));//gets index of the button which is the first character in its name

        if(buttonName.contains("main")) //determines if button is from main or extra ingredient
            customizeSlot[index].decreaseAmountLabel();
        else
            extraSlots[index].decreaseAmountLabel();
    }

    /**
     * Gets the numbers displayed on the label of every item when customizing pizza
     * @return amount - number of desired items to make pizza
     */
    public int[] getTotalAmounts(){
        int[] amount = new int[11];

        for(int ctr = 0; ctr < 8; ctr++) //gets desired amounts of main ingredients
        {
            amount[ctr] = (int) customizeSlot[ctr].getAmount();
        }

        for(int ctr = 0; ctr < 3; ctr++){ //gets desired amounts of extra ingredients
            amount[ctr + 8] = (int) extraSlots[ctr].getAmount();
        }

        return amount;
    }

    /**
     * Updates labels in the customizing screen when number of desired items is changed
     * @param price - list of prices of every item in the vending machine
     * @param calories - list of calories of every item in the vending machine
     */
    public void updateTotalInfo(double[] price, double[] calories){
        int[] amount;
        double totalPrice = 0;
        double totalCalories = 0;

        amount = getTotalAmounts();

        for(int ctr = 0; ctr < 11; ctr++)
           totalPrice += amount[ctr] * price[ctr]; //updates the total price of the pizza to be made
        for(int ctr = 0; ctr < 11; ctr++)
            totalCalories += amount[ctr] * calories[ctr]; //updates the total calories of the pizza to be made

        this.totalPrice.setText("Total Price: " + totalPrice);
        this.totalCalories.setText("Total Calories: " + totalCalories);
    }

    /**
     * Closes the customize pizza display
     */
    public void disposeCustomizeWindow(){
        if(customizeScreen != null)
            customizeScreen.dispose();
    }

    /**
     * Adds action listener for Money Content button
     * @param listener - listener for button
     */
    public void addMoneyContentListener(ActionListener listener){
        registerContentButton.addActionListener(listener);
    }

    /**
     * Add actions listener for Make Pizza button
     * @param listener - listener for button
     */
    public void addMakePizzaListener(ActionListener listener){makePizza.addActionListener(listener);}

    /**
     * Adds actions listener for Insert Money button
     * @param listener - listener for button
     */
    public void addInsertMoneyListener(ActionListener listener){
        ₱1CoinButton.addActionListener(listener);
        ₱5CoinButton.addActionListener(listener);
        ₱10CoinButton.addActionListener(listener);
        ₱20CoinButton.addActionListener(listener);
        ₱50BillButton.addActionListener(listener);
        ₱100BillButton.addActionListener(listener);
    }

    /**
     * Adds actions listener for Customize Pizza button
     * @param listener - listener for button
     */
    public void addCustomizePizzaListener(ActionListener listener){
        customizePizzaButton.addActionListener(listener);
    }

    /**
     * Adds actions listener for Maintenance Features button
     * @param listener - listener for button
     */
    public void addShowMaintenanceMenu(ActionListener listener){
        maintenanceFeaturesButton.addActionListener(listener);
    }

    /**
     *  Adds action listener for Collect Money button
     * @param listener - listener for button
     */
    public void addCollectMoneyListener(ActionListener listener){
        collectMoneyButton.addActionListener(listener);
    }

    /**
     * Adds actions listener for button to Show Summary
     * @param listener - listener for button
     */
    public void addShowSummaryListener(ActionListener listener){
        showSummaryButton.addActionListener(listener);
    }

    /**
     * Updates label for total received money
     * @param text - text for updated total received money
     */
    public void setTotalLabel(String text){
        receivedMoney.setText(text);
    }

    /**
     * Updates label for stock of item slot (main ingredient)
     * @param text - text containing stock of item slot
     * @param index - index of item slot to be updated
     */
    public void setStockText(String text, int index){
        slot[index].setStock(text);
    }

    /**
     * Updates label for stock of extra item slot (extra seasoning)
     * @param text - text containing stock of item slot
     * @param index - index of extra item slot to be updated
     */
    public void setExtraStockText(String text, int index){
        extraSlotsMaintenance[index].setStock(text);
    }

    /**
     * Updates label for price of item slot for main ingredient
     * @param price - new price of item
     * @param itemSlot - index of item slot to be updated
     */
    public void setPriceText(double price, int itemSlot){
        slot[itemSlot].setPrice(price);
    }

    /**
     * Updates label for price of extra item slot for extra ingredients
     * @param price - new price of item
     * @param itemSlot - index of extra item slot to be updated
     */
    public void setExtraPriceText(double price, int itemSlot){
        extraSlotsMaintenance[itemSlot].setPrice(price);
    }

    /**
     * Adds action listener for button to increase number of desired items for pizza
     * @param listener - listener for button
     */
    public void addAddListener(ActionListener listener){
        for(int ctr = 0; ctr < 8; ctr++) //adds listeners for main ingredient displays
            customizeSlot[ctr].addAddListener(listener);
        for(int ctr = 0; ctr < 3; ctr++)//adds listeners for extra ingredient displays
            extraSlots[ctr].addAddListener(listener);
    }

    /**
     * Adds action listener for button to decrease number of desired items for pizza
     * @param listener - listener for button
     */
    public void addRemoveListener(ActionListener listener){
        for(int ctr = 0; ctr < 8; ctr++)
            customizeSlot[ctr].addRemoveListener(listener);
        for(int ctr = 0; ctr < 3; ctr++)
            extraSlots[ctr].addRemoveListener(listener);
    }

    /**
     * Adds action listener for Cancel button
     * @param listener - listener for button
     */
    public void addCancelButtonListener(ActionListener listener){
        cancelButton.addActionListener(listener);
    }

    /**
     * Shows the display for vending
     * @param type - determines if display is for regular of special vending machine
     */
    public void showVendingFeatures(int type){
        if(type == 1) //hides display elements of special vending machine if current is regular
            customizableItemPanel.setVisible(false);
        else
        {
            for(int ctr = 0; ctr < 3; ctr++){ //changes display to fit vending features and hides maintenance features
                extraSlotsMaintenance[ctr].testingScreen();
                extraSlotsMaintenance[ctr].setDispenseVisible(false);
            }

            customizableItemPanel.setVisible(true);
        }

        for(int ctr = 0; ctr < 8; ctr++) //changes display of main ingredients slots for vending
            slot[ctr].testingScreen();

        //Next lines hide and show features for maintenance and vending respectively
        bottomRight.remove(otherFeatures);
        totalReceivedPanel.add(receivedMoney, BorderLayout.NORTH);
        replenishMoney.setVisible(false);
        receivedMoney.setVisible(true);
        cancelButton.setVisible(true);
        cardLayout.show(container, "Card4");
    }

    /**
     * Displays maintenance features
     * @param type - determines if current vending machine is special or regular
     */
    public void showMaintenanceMenu(int type){
        if(type == 1) //Hides special feature only to be seen in vending features
            customizableItemPanel.setVisible(false);
        else{
            for(int ctr = 0; ctr < 3; ctr++){//sets up extra ingredient slot display for maintenance features
                extraSlotsMaintenance[ctr].maintenanceScreen();
                extraSlotsMaintenance[ctr].setVisible(true);
            }
            customizableItemPanel.setVisible(false);
        }

        for(int ctr = 0; ctr < 8;ctr++) //sets up main ingredient slot display for maintenance features
            slot[ctr].maintenanceScreen();

        //Next lines hide and show features for maintenance and vending respectively
        bottomRight.add(otherFeatures, BorderLayout.EAST);
        receivedMoney.setVisible(false);
        totalReceivedPanel.add(replenishMoney, BorderLayout.NORTH);
        replenishMoney.setVisible(true);
        cancelButton.setVisible(false);
        cardLayout.show(container, "Card4");
    }

    /**
     * Adds action listener for Vending Features button
     * @param listener - listener for button
     */
    public void addVendingFeaturesListener(ActionListener listener){
        vendingFeaturesButton.addActionListener(listener);
    }

    /**
     * Adds actions listener for Back to Features button
     * @param listener - listener for button
     */
    public void addBackToFeaturesButton(ActionListener listener){
        backToFeaturesButton.addActionListener(listener);
    }

    /**
     * Displays menu to select which feature to do
     */
    public void showTestMenu(){
        cardLayout.show(container, "Card3");
    }

    /**
     * Adds action listeners for Restock buttons
     * @param listener - listener for button
     */
    public void addRestockListener(ActionListener listener){
        for(int ctr = 0; ctr < 8; ctr++) //adds listeners for restock buttons of main ingredient slots
            slot[ctr].addRestockListener(listener);

        if(extraSlotsMaintenance[0] != null) //adds listeners for restock buttons of extra ingredients slots (if possible)
            for(int ctr = 0; ctr < 3; ctr++)
                extraSlotsMaintenance[ctr].addRestockListener(listener);
    }

    /**
     * Adds action listener for Set Price buttons
     * @param listener - listener for button
     */
    public void addSetPriceListener(ActionListener listener){
        for(int ctr = 0; ctr < 8; ctr++) //Adds listeners for set price buttons of main ingredient slots
            slot[ctr].addSetPriceListener(listener);

        if(extraSlotsMaintenance[0] != null) //adds listeners for set price buttons of extra ingredient slots (if possible)
            for(int ctr = 0; ctr < 3; ctr++)
                extraSlotsMaintenance[ctr].addSetPriceListener(listener);
    }

    /**
     * Adds action listener for Dispense buttons
     * @param listener - listener for button
     */
    public void addDispenseListener(ActionListener listener){
        for(int ctr = 0; ctr < 8; ctr++)
            slot[ctr].addDispenseListener(listener);
    }

    /**
     * Initializes contents of display when setting price for item
     * @param component - button that was pressed to set price
     * @param x - horizontal location of popup relative to button
     * @param y - horizontal location of popup relative to button
     * @param slot - index of button pressed
     */
    public void initializePopupPrice(Component component, int x, int y, int slot){
        priceDialog = new JDialog();
        priceDialog.setUndecorated(false);
        priceDialog.setTitle("New price");
        priceDialog.setModal(true);

        setPricePanel = new JPanel(new BorderLayout());
        priceTextField = new JTextField();
        setPricePanel.add(priceTextField, BorderLayout.NORTH);

        confirmPriceBtn = new SlotButton("Confirm", slot);

        //if blocks help determine if the Popup's button came from a main ingredient slot or extra slot
        if(((SlotButton) component).getName().contains("main"))
            confirmPriceBtn.setName("main");
        else
            confirmPriceBtn.setName("extra");


        setPricePanel.add(confirmPriceBtn);

        priceDialog.getContentPane().add(setPricePanel);
        priceDialog.pack();

        priceDialog.setLocation(component.getLocationOnScreen().x + x, component.getLocationOnScreen().y + y);

    }

    /**
     * Displays feedback when main ingredient item is dispensed along with change
     * @param changeText - String containing information of change
     * @param index - index of the button pressed
     * @param component - button that was pressed
     */
    public void showChangeScreen(String changeText, int index, Component component){
        JDialog changeDialog = new JDialog();
        JPanel container =  new JPanel();
        String text = "<html>" + changeText.replace("\n", "<br/>") + "<html>"; //uses html and br for creating new lines in JLabel
       JLabel log = new JLabel(text);
       ImageIcon picture;
       JLabel picLabel = new JLabel();

       picture = resizeImageIcon(pictures[index],100, 100); //gets picture of item being dispensed and resizes it


       picLabel.setIcon(picture);
       container.add(picLabel, BorderLayout.NORTH);

       container.add(log);

       changeDialog.setLocationRelativeTo(null);
       changeDialog.getContentPane().add(container);
       changeDialog.pack();
       changeDialog.setVisible(true);
    }

    /**
     * Displays feedback when pizza is successfully made along with change
     * @param changeText - String containing information on change given
     */
    public void showChangeScreen(String changeText){
        JDialog changeDialog = new JDialog();
        JPanel container = new JPanel();
        String text = "<html>" + changeText.replace("\n", "<br/>") + "<html>"; //uses html and br for creating new lines in JLabel
        JLabel log = new JLabel(text);
        ImageIcon picture = pizzaPicture;
        JLabel picLabel = new JLabel();

        picLabel.setIcon(picture); //puts picture of pizza into JLabel
        container.add(picLabel, BorderLayout.NORTH);

        container.add(log);

        changeDialog.setLocationRelativeTo(null);
        changeDialog.getContentPane().add(container);
        changeDialog.pack();
        changeDialog.setVisible(true);
    }

    /**
     * Displays feedback on denominations received when canceling transaction
     * @param changeText - String containing information on change given
     */
    public void showCancelResultScreen(String changeText){
        JDialog changeDialog = new JDialog();
        JPanel container = new JPanel();
        String text = "<html>" + changeText.replace("\n", "<br/>") + "<html>";
        JLabel log = new JLabel(text);

        container.add(log);

        changeDialog.setLocationRelativeTo(null);
        changeDialog.getContentPane().add(container);
        changeDialog.pack();
        changeDialog.setVisible(true);
    }


    /**
     * Displays progress of pizza being made with change feedback
     * @param totalAmounts - list of total amounts of each item being used for pizza
     * @param changeLog - String containing information on change given
     */
    public void showProgressScreen(int[] totalAmounts, String changeLog){
        JDialog progressDialog = new JDialog();
        JPanel progressContainer = new JPanel();
        JProgressBar progressBar = new JProgressBar();
        JPanel textContainer = new JPanel(new GridLayout(11,1));

        progressDialog.setLocationRelativeTo(null);
        progressDialog.setSize(new Dimension(200,500));

        //Setting progress display is displayed during progress bar completion
        customizeSlot[0].setProgressDisplay("Kneading Dough...");

        for(int ctr = 1; ctr < 8; ctr++){
            customizeSlot[ctr].setProgressDisplay("Adding " + customizeSlot[ctr].getItemName() + "...");
        }

        extraSlots[0].setProgressDisplay("Sprinkling " + extraSlots[0].getItemName() +"...");
        extraSlots[1].setProgressDisplay("Sprinkling " + extraSlots[1].getItemName() +"...");
        extraSlots[2].setProgressDisplay("Drizzling " + extraSlots[2].getItemName() + "...");

        ArrayList<CustomizeSlotPanel> contents = new ArrayList<>();
        CustomizeSlotPanel temp;

        //Only adds verb displays if item is used in making of pizza
        for(int ctr = 0; ctr < 11; ctr++){
            if(totalAmounts[ctr] != 0){
                if(ctr < 8)
                    temp = customizeSlot[ctr];
                else
                    temp = extraSlots[ctr-8];
                contents.add(temp);
            }
        }

        progressBar.setMinimum(0);
        progressBar.setMaximum(contents.size()); //helps determine increments of progress bar

        progressContainer.add(progressBar, BorderLayout.NORTH);
        progressContainer.add(textContainer, BorderLayout.CENTER);
        progressDialog.add(progressContainer);

        progressDialog.setVisible(true);

        //Swing workers are utilized for updating progress bar in the background to avoid complications with the GUI displaying
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int ctr = 0; ctr < contents.size(); ctr++) { //increments progress bar displays with delays in between
                    progressBar.setValue(ctr);
                    textContainer.add(new JLabel(contents.get(ctr).getProgressDisplay()));
                    progressDialog.revalidate();
                    progressDialog.repaint();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();}

                return null;
            }

            @Override
            protected void done() {
                progressDialog.dispose();
                showChangeScreen(changeLog);
            }
        };

        worker.execute();
        progressDialog.setVisible(true);

    }

    /**
     * Closes popup
     */
    public void disposePopup(){
        priceDialog.dispose();
    }

    /**
     * Displays popup when setting price
     */
    public void showPopupPrice(){
        priceDialog.setVisible(true);
    }

    /**
     * Gets price from the text field when setting price
     * @return
     */
    public String getNewPrice(){
        return priceTextField.getText();
    }

    /**
     * Adds actions listener for Confirm Price button
     * @param listener - listener for button
     */
    public void addConfirmPriceListener(ActionListener listener){
        confirmPriceBtn.addActionListener(listener);
    }

    /**
     * Adds action listener for Back to Main button
     * @param listener - listener for button
     */
    public void addBackToMainListener(ActionListener listener){
        backToMainMenuButton.addActionListener(listener);
    }

    /**
     * Adds action listener for Create Vending Machine button
     * @param listener - listener for button
     */
    public void addCreateVMListener(ActionListener listener){
        createVendingMachineButton.addActionListener(listener);
    }

    /**
     * Adds action listener for Test Vending Machine button
     * @param listener - listener for button
     */
    public void addTestVMListener(ActionListener listener){
        testVendingMachineButton.addActionListener(listener);
    }

    /**
     * Adds action listener for Exit button
     * @param listener - listener for button
     */
    public void addExitListener(ActionListener listener){
        exitButton.addActionListener(listener);
    }

    /**
     * Adds action listener for Regular button
     * @param listener - listener for button
     */
    public void addRegularListener(ActionListener listener){
        regularButton.addActionListener(listener);
    }

    /**
     * Adds action listener for Special button
     * @param listener - listener for button
     */
    public void addSpecialListener(ActionListener listener){
        specialButton.addActionListener(listener);
    }

    /**
     * Shows the menu to create a vending machine
     */
    public void showCreateMenu(){
        cardLayout.show(container, "Card2");
    }

    /**
     * Closes application
     */
    public void disposeWindow(){
        frame.dispose();
    }

    /**
     * Shows main menu
     */
    public void showMainMenu(){
        cardLayout.show(container,"Card1");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    /**
     * Resizes image
     * @param icon - image to be resized
     * @param width - new width of image
     * @param height - new height of image
     * @return Resized Image
     */
    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height){
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
