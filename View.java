import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
    private JPanel moneyButtonsPanel;
    private CardLayout cardLayout = (CardLayout) container.getLayout();
    private slotView[] slot;
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

    public View() {
        slot = new slotView[8];
        frame = new JFrame("Vending Machine Factory");
        frame.setContentPane(this.container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        itemSlotsContainer.setLayout(new GridLayout(4, 2));
        frame.setSize(700,500);
        frame.setLocationRelativeTo(null);
        replenishMoney = new JLabel("Replenish money");

        frame.setVisible(true);
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
        summaryContainer = new JPanel(new GridLayout(5,2 ));
        summaryDialog.add(summaryContainer);
        itemSummaryInfo = new JLabel[8];
        for (int i = 0; i < itemSummaryInfo.length; i++) {
            itemSummaryInfo[i] = new JLabel();
        }
    }

    public void showSummary(double totalSales){
        summaryContainer.add(new JLabel("Total Sales: " + totalSales + " pesos"));
        summaryDialog.setSize(400,600);
        summaryDialog.setLocationRelativeTo(null);
        summaryDialog.setVisible(true);
    }

    public void addSummaryInfo(int index, String name, int initStock, int currentStock, int numSold, double sales){
        itemSummaryInfo[index].setText("<html>" + name + "<br/>Number sold: " + numSold + "<br/>Sales: " + sales + " pesos<br/>Initial Stock: " + initStock + "<br/>Current Stock: " + currentStock + "<html>");
        itemSummaryInfo[index].setVerticalAlignment(SwingConstants.CENTER);
        itemSummaryInfo[index].setVerticalAlignment(SwingConstants.CENTER);
        summaryContainer.add(itemSummaryInfo[index]);
    }

    public void initializeSlot(int slotNum, String name, double calories, double price){
        this.slot[slotNum] = new slotView(slotNum, name, calories, price);
        itemSlotsContainer.add(this.slot[slotNum]);
    }

    public void addInsertMoneyListener(ActionListener listener){
        ₱1CoinButton.addActionListener(listener);
        ₱5CoinButton.addActionListener(listener);
        ₱10CoinButton.addActionListener(listener);
        ₱20CoinButton.addActionListener(listener);
        ₱50BillButton.addActionListener(listener);
        ₱100BillButton.addActionListener(listener);
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

    public void setPriceText(double price, int itemSlot){
        slot[itemSlot].setPrice(price);
    }

    public void addCancelButtonListener(ActionListener listener){
        cancelButton.addActionListener(listener);
    }

    public void setCaloriesText(double calories, int itemSlot){
        switch(itemSlot){
            case 0: doughCalories.setText("Calories: " + calories); break;
            case 1: sauceCalories.setText("Calories: " + calories); break;
            case 2: mozzarellaCalories.setText("Calories: " + calories); break;
            case 3: pepperoniCalories.setText("Calories: " + calories); break;
            case 4: pineappleCalories.setText("Calories: " + calories); break;
            case 5: spinachCalories.setText("Calories: " + calories); break;
            case 6: gorgonzolaCalories.setText("Calories: " + calories); break;
            case 7: hamStock.setText("Calories: " + calories); break;
        }
    }
    public void showVendingFeatures(){
        for(int ctr = 0; ctr < 8; ctr++)
            slot[ctr].testingScreen();
        bottomRight.remove(otherFeatures);
        totalReceivedPanel.add(receivedMoney, BorderLayout.NORTH);
        replenishMoney.setVisible(false);
        receivedMoney.setVisible(true);
        cancelButton.setVisible(true);
        cardLayout.show(container, "Card4");
    }

    public void showMaintenanceMenu(){
        for(int ctr = 0; ctr < 8;ctr++)
            slot[ctr].maintenanceScreen();
        bottomRight.add(otherFeatures, BorderLayout.EAST);
        receivedMoney.setVisible(false);
        totalReceivedPanel.add(replenishMoney, BorderLayout.NORTH);
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
    }

    public void addSetPriceListener(ActionListener listener){
        for(int ctr = 0; ctr < 8; ctr++)
            slot[ctr].addSetPriceListener(listener);
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
        setPricePanel.add(confirmPriceBtn);

        priceDialog.getContentPane().add(setPricePanel);
        priceDialog.pack();

        priceDialog.setLocation(component.getLocationOnScreen().x + x, component.getLocationOnScreen().y + y);

    }

    public void showMaintenance(){
        bottomRight.add(otherFeatures, BorderLayout.EAST);
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
}
