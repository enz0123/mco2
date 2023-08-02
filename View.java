import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    public void updatePictures(int[] stock){
        ImageIcon tempPicture;

        for(int ctr = 0; ctr < 8; ctr++){
            slot[ctr].clearPictures();
            for(int ctr2 = 0; ctr2 < stock[ctr]; ctr2++){
                System.out.println("ctr " + ctr + "\nctr2 " + ctr2 + "stock: " + stock[ctr] + "\n");
                tempPicture = pictures[ctr];
                slot[ctr].addPicture(new JLabel(tempPicture));
            }
        }

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

    public void initializeCustomizeScreen(int[] extraStocks, double[] extraPrices, double[] extraCalories){
        customizeScreen = new JFrame("Customize Pizza");
        customizeScreen.setLayout(new GridLayout(15, 1));

        JLabel mainIngredients = new JLabel("Main Ingredients");
        mainIngredients.setHorizontalAlignment(SwingConstants.CENTER);
        mainIngredients.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        customizeScreen.add(mainIngredients);

        customizeSlot = new CustomizeSlotPanel[8];
        for(int ctr = 0; ctr < 8; ctr++) {
            customizeSlot[ctr] = new CustomizeSlotPanel();
            customizeSlot[ctr].setAddButtonName(ctr + "main");
            customizeSlot[ctr].setRemoveButtonName(ctr + "main");
            customizeSlot[ctr].setPreferredSize(new Dimension(100, 25));
            customizeScreen.add(customizeSlot[ctr]);
        }


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

    public void initializeCollectMoney(String info){
        String newInfo = info.replace("\n", "<br/>");
        collectMoneyDialog = new JDialog();
        collectMoneyDialog.setUndecorated(false);
        collectMoneyDialog.setTitle("Collected money");

        collectedMoneyInfo = new JLabel("<html>" + newInfo + "<html");
        collectMoneyDialog.add(collectedMoneyInfo);
        collectMoneyDialog.pack();
        collectMoneyDialog.setLocationRelativeTo(null);
        collectMoneyDialog.setVisible(true);
    }

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

    public void showSummary(double totalSales){
        summaryContainer.add(new JLabel("Total Sales: " + totalSales + " pesos"));
        summaryDialog.pack();
        summaryDialog.setLocationRelativeTo(null);
        summaryDialog.setVisible(true);
    }

    public void showSummary(double totalSales, int pizzasSold){
        summaryContainer.add(new JLabel("<html>Total Sales: " + totalSales + " pesos<br/>Pizzas sold: " + pizzasSold + "<html>"));
        summaryDialog.pack();
        summaryDialog.setLocationRelativeTo(null);
        summaryDialog.setVisible(true);
    }

    public void showMoneyContent(int ones, int fives, int tens, int twenties, int fifties, int hundreds){
        JDialog moneyDialog = new JDialog();
        moneyDialog.setTitle("Register Content");
        JPanel moneyContainer = new JPanel();
        JLabel moneyLabel = new JLabel("<html>₱1 coins: " + ones + "<br/>₱5 coins: " + fives +
                "<br/>₱10 coins: " + tens + "<br/>₱20 coins: " + twenties + "<br/>₱50 bills: " + fifties + "<br/>₱100 bills: " + hundreds);

        moneyContainer.add(moneyLabel);
        moneyDialog.getContentPane().add(moneyContainer);

        moneyDialog.pack();
        moneyDialog.setVisible(true);
    }

    public void addSummaryInfo(int index, String name, int initStock, int currentStock, int numSold, double sales){
        itemSummaryInfo[index].setText("<html>" + name + "<br/>Number sold: " + numSold + "<br/>Sales: " + sales + " pesos<br/>Initial Stock: " + initStock + "<br/>Current Stock: " + currentStock + "<br/><br/><html>");
        itemSummaryInfo[index].setVerticalAlignment(SwingConstants.CENTER);
        itemSummaryInfo[index].setVerticalAlignment(SwingConstants.CENTER);
        itemSummaryInfo[index].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        summaryContainer.add(itemSummaryInfo[index]);
    }

    public void addExtraSummaryInfo(int index, String name, int initStock, int currentStock, int numSold, double sales){
        itemSummaryInfo[index+8].setText("<html>" + name + "<br/>Number sold: " + numSold + "<br/>Sales: " + sales + " pesos<br/>Initial Stock: " + initStock + "<br/>Current Stock: " + currentStock + "<br/><br/><html>");
        itemSummaryInfo[index+8].setVerticalAlignment(SwingConstants.CENTER);
        itemSummaryInfo[index+8].setVerticalAlignment(SwingConstants.CENTER);
        itemSummaryInfo[index+8].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        summaryContainer.add(itemSummaryInfo[index+8]);
    }

    public void removeAllSlots(){
        itemSlotsContainer.removeAll();
    }

    public void initializeSlot(int slotNum, String name, double calories, double price){
        this.slot[slotNum] = new slotView(slotNum, name, calories, price);
        this.slot[slotNum].setButtonName("main");
        this.slot[slotNum].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.slot[slotNum].setBlackContainer();
        itemSlotsContainer.add(this.slot[slotNum]);
    }

    public void initializeExtras(int slotNum, String name, double calories, double price){
        this.extraSlotsMaintenance[slotNum] = new slotView(slotNum, name, calories, price);
        this.extraSlotsMaintenance[slotNum].setButtonName("extra");
        this.extraSlotsMaintenance[slotNum].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.extraSlotsMaintenance[slotNum].setBlackContainer();
        itemSlotsContainer.add(this.extraSlotsMaintenance[slotNum]);
    }

    public double getCustomizableAmount(String buttonName){
        int index = Integer.parseInt("" + buttonName.charAt(0));

        if(buttonName.contains("main"))
            return customizeSlot[index].getAmount();
        return extraSlots[index].getAmount();
    }

    public void increaseLabel(String buttonName){
        int index = Integer.parseInt("" + buttonName.charAt(0));

        if(buttonName.contains("main"))
            customizeSlot[index].increaseAmountLabel();
        else
            extraSlots[index].increaseAmountLabel();

    }

    public void decreaseLabel(String buttonName){
        int index = Integer.parseInt("" + buttonName.charAt(0));

        if(buttonName.contains("main"))
            customizeSlot[index].decreaseAmountLabel();
        else
            extraSlots[index].decreaseAmountLabel();
    }

    public int[] getTotalAmounts(){
        int[] amount = new int[11];

        for(int ctr = 0; ctr < 8; ctr++)
        {
            amount[ctr] = (int) customizeSlot[ctr].getAmount();
        }

        for(int ctr = 0; ctr < 3; ctr++){
            amount[ctr + 8] = (int) extraSlots[ctr].getAmount();
        }

        return amount;
    }

    public void updateTotalInfo(double[] price, double[] calories){
        int[] amount;
        double totalPrice = 0;
        double totalCalories = 0;

        amount = getTotalAmounts();

        for(int ctr = 0; ctr < 11; ctr++)
           totalPrice += amount[ctr] * price[ctr];
        for(int ctr = 0; ctr < 11; ctr++)
            totalCalories += amount[ctr] * calories[ctr];

        this.totalPrice.setText("Total Price: " + totalPrice);
        this.totalCalories.setText("Total Calories: " + totalCalories);
    }

    public void disposeCustomizeWindow(){
        if(customizeScreen != null)
            customizeScreen.dispose();
    }

    public void addMoneyContentListener(ActionListener listener){
        registerContentButton.addActionListener(listener);
    }

    public void addMakePizzaListener(ActionListener listener){makePizza.addActionListener(listener);}

    public void addInsertMoneyListener(ActionListener listener){
        ₱1CoinButton.addActionListener(listener);
        ₱5CoinButton.addActionListener(listener);
        ₱10CoinButton.addActionListener(listener);
        ₱20CoinButton.addActionListener(listener);
        ₱50BillButton.addActionListener(listener);
        ₱100BillButton.addActionListener(listener);
    }

    public void addCustomizePizzaListener(ActionListener listener){
        customizePizzaButton.addActionListener(listener);
    }

    public void addShowMaintenanceMenu(ActionListener listener){
        maintenanceFeaturesButton.addActionListener(listener);
    }

    public void addCollectMoneyListener(ActionListener listener){
        collectMoneyButton.addActionListener(listener);
    }

    public void addShowSummaryListener(ActionListener listener){
        showSummaryButton.addActionListener(listener);
    }
    public void setTotalLabel(String text){
        receivedMoney.setText(text);
    }

    public void setStockText(String text, int index){
        slot[index].setStock(text);
    }

    public void setExtraStockText(String text, int index){
        extraSlotsMaintenance[index].setStock(text);
    }

    public void setPriceText(double price, int itemSlot){
        slot[itemSlot].setPrice(price);
    }

    public void setExtraPriceText(double price, int itemSlot){
        extraSlotsMaintenance[itemSlot].setPrice(price);
    }

    public void addAddListener(ActionListener listener){
        for(int ctr = 0; ctr < 8; ctr++)
            customizeSlot[ctr].addAddListener(listener);
        for(int ctr = 0; ctr < 3; ctr++)
            extraSlots[ctr].addAddListener(listener);
    }

    public void addRemoveListener(ActionListener listener){
        for(int ctr = 0; ctr < 8; ctr++)
            customizeSlot[ctr].addRemoveListener(listener);
        for(int ctr = 0; ctr < 3; ctr++)
            extraSlots[ctr].addRemoveListener(listener);
    }

    public void addCancelButtonListener(ActionListener listener){
        cancelButton.addActionListener(listener);
    }

    public void showVendingFeatures(int type){
        if(type == 1)
            customizableItemPanel.setVisible(false);
        else
        {
            for(int ctr = 0; ctr < 3; ctr++){
                extraSlotsMaintenance[ctr].testingScreen();
                extraSlotsMaintenance[ctr].setDispenseVisible(false);
            }

            customizableItemPanel.setVisible(true);
        }


        for(int ctr = 0; ctr < 8; ctr++)
            slot[ctr].testingScreen();
        bottomRight.remove(otherFeatures);
        totalReceivedPanel.add(receivedMoney, BorderLayout.NORTH);
        replenishMoney.setVisible(false);
        receivedMoney.setVisible(true);
        cancelButton.setVisible(true);
        cardLayout.show(container, "Card4");
    }

    public void showMaintenanceMenu(int type){
        if(type == 1)
            customizableItemPanel.setVisible(false);
        else{
            for(int ctr = 0; ctr < 3; ctr++){
                extraSlotsMaintenance[ctr].maintenanceScreen();
                extraSlotsMaintenance[ctr].setVisible(true);
            }
            customizableItemPanel.setVisible(false);
        }

        for(int ctr = 0; ctr < 8;ctr++)
            slot[ctr].maintenanceScreen();
        bottomRight.add(otherFeatures, BorderLayout.EAST);
        receivedMoney.setVisible(false);
        totalReceivedPanel.add(replenishMoney, BorderLayout.NORTH);
        replenishMoney.setVisible(true);
        cancelButton.setVisible(false);
        cardLayout.show(container, "Card4");
    }

    public void addVendingFeaturesListener(ActionListener listener){
        vendingFeaturesButton.addActionListener(listener);
    }

    public void addBackToFeaturesButton(ActionListener listener){
        backToFeaturesButton.addActionListener(listener);
    }


    public void showTestMenu(){
        cardLayout.show(container, "Card3");
    }

    public void addShowMaintenanceListener(ActionListener listener){
        maintenanceFeaturesButton.addActionListener(listener);
    }

    public void addRestockListener(ActionListener listener){
        for(int ctr = 0; ctr < 8; ctr++)
            slot[ctr].addRestockListener(listener);

        if(extraSlotsMaintenance[0] != null)
            for(int ctr = 0; ctr < 3; ctr++)
                extraSlotsMaintenance[ctr].addRestockListener(listener);
    }

    public void addSetPriceListener(ActionListener listener){
        for(int ctr = 0; ctr < 8; ctr++)
            slot[ctr].addSetPriceListener(listener);

        if(extraSlotsMaintenance[0] != null)
            for(int ctr = 0; ctr < 3; ctr++)
                extraSlotsMaintenance[ctr].addSetPriceListener(listener);
    }

    public void addDispenseListener(ActionListener listener){
        for(int ctr = 0; ctr < 8; ctr++)
            slot[ctr].addDispenseListener(listener);
    }

    public void initializePopupPrice(Component component, int x, int y, int slot){
        priceDialog = new JDialog();
        priceDialog.setUndecorated(false);
        priceDialog.setTitle("New price");
        priceDialog.setModal(true);

        setPricePanel = new JPanel(new BorderLayout());
        priceTextField = new JTextField();
        setPricePanel.add(priceTextField, BorderLayout.NORTH);

        confirmPriceBtn = new SlotButton("Confirm", slot);

        if(((SlotButton) component).getName().contains("main"))
            confirmPriceBtn.setName("main");
        else
            confirmPriceBtn.setName("extra");


        setPricePanel.add(confirmPriceBtn);

        priceDialog.getContentPane().add(setPricePanel);
        priceDialog.pack();

        priceDialog.setLocation(component.getLocationOnScreen().x + x, component.getLocationOnScreen().y + y);

    }

    public void showProgressScreen(int[] totalAmounts){
        JDialog progressDialog = new JDialog();
        JPanel progressContainer = new JPanel();
        JProgressBar progressBar = new JProgressBar();
        JPanel textContainer = new JPanel(new GridLayout(11,1));

        progressDialog.setLocationRelativeTo(null);
        progressDialog.setSize(new Dimension(200,500));

        customizeSlot[0].setProgressDisplay("Kneading Dough...");

        for(int ctr = 1; ctr < 8; ctr++){
            customizeSlot[ctr].setProgressDisplay("Adding " + customizeSlot[ctr].getItemName() + "...");
        }

        extraSlots[0].setProgressDisplay("Sprinkling " + extraSlots[0].getItemName() +"...");
        extraSlots[1].setProgressDisplay("Sprinkling " + extraSlots[1].getItemName() +"...");
        extraSlots[2].setProgressDisplay("Drizzling " + extraSlots[2].getItemName() + "...");

        ArrayList<CustomizeSlotPanel> contents = new ArrayList<>();
        CustomizeSlotPanel temp;

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
        progressBar.setMaximum(contents.size());

        progressContainer.add(progressBar, BorderLayout.NORTH);
        progressContainer.add(textContainer, BorderLayout.CENTER);
        progressDialog.add(progressContainer);

        progressDialog.setVisible(true);

        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int ctr = 0; ctr < contents.size(); ctr++) {
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
            }
        };

        worker.execute();
        progressDialog.setVisible(true);

    }

    public void disposePopup(){
        priceDialog.dispose();
    }
    public void showPopupPrice(){
        priceDialog.setVisible(true);
    }

    public String getNewPrice(){
        return priceTextField.getText();
    }

    public void addConfirmPriceListener(ActionListener listener){
        confirmPriceBtn.addActionListener(listener);
    }
    public void addBackToMainListener(ActionListener listener){
        backToMainMenuButton.addActionListener(listener);
    }
    public void addCreateVMListener(ActionListener listener){
        createVendingMachineButton.addActionListener(listener);
    }

    public void addTestVMListener(ActionListener listener){
        testVendingMachineButton.addActionListener(listener);
    }

    public void addExitListener(ActionListener listener){
        exitButton.addActionListener(listener);
    }

    public void addRegularListener(ActionListener listener){
        regularButton.addActionListener(listener);
    }

    public void addSpecialListener(ActionListener listener){
        specialButton.addActionListener(listener);
    }

    public void showCreateMenu(){
        cardLayout.show(container, "Card2");
    }

    public void disposeWindow(){
        frame.dispose();
    }

    public void showMainMenu(){
        cardLayout.show(container,"Card1");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height){
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
