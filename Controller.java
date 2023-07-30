import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller {
    View view;
    VendingMachine vendingMachine;

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
    }

    class CollectMoneyListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            view.initializeCollectMoney(vendingMachine.collectMoney());
        }
    }
    class ShowSummaryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            double totalSales = 0;
            view.initializeSummary();
            for(int ctr = 0; ctr < 8; ctr ++){
                totalSales += vendingMachine.getSales(ctr);
                view.addSummaryInfo(ctr, vendingMachine.getItemName(ctr), vendingMachine.getInitStock(ctr), vendingMachine.getItemStock(ctr), vendingMachine.getNumSold(ctr),vendingMachine.getSales(ctr));
            }

            view.showSummary(totalSales);
        }
    }

    class RestockListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            SlotButton sourceButton = (SlotButton) e.getSource();
            int index = sourceButton.getIndex();
            int stock = vendingMachine.getItemStock(index);

            if(stock == 10)
                JOptionPane.showMessageDialog(null, "Error. Slot is full.", "Stock full", JOptionPane.ERROR_MESSAGE);
            else{
                vendingMachine.stockItem(index);
                view.setStockText("Stock: " + vendingMachine.getItemStock(index), index);
            }

        }
    }

    class DispenseListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = ((SlotButton) e.getSource()).getIndex();
            int result = vendingMachine.checkTransaction(index);

            if(vendingMachine.getItemStock(index) == 0)
                JOptionPane.showMessageDialog(null, "Error. Selected item is out of stock.", "Error", JOptionPane.ERROR_MESSAGE);
            else{
                switch (result) {
                    case 1 ->
                            JOptionPane.showMessageDialog(null, "Error. Not enough money was inserted.", "Error", JOptionPane.ERROR_MESSAGE);
                    case 2 ->
                            JOptionPane.showMessageDialog(null, "Error. Unable to give change.", "Error", JOptionPane.ERROR_MESSAGE);
                    case 0 -> {
                        vendingMachine.giveChange(index);
                        view.setStockText("Stock: " + vendingMachine.getItemStock(index), index);
                        view.setTotalLabel("Total: " + vendingMachine.getTotalReceived());
                    }
                }

            }
        }
    }

    class ConfirmPriceListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String newPrice = view.getNewPrice();
            view.disposePopup();

            if(newPrice.matches("\\d+")){
                view.setPriceText(Double.parseDouble(newPrice), ((SlotButton) e.getSource()).getIndex());
                vendingMachine.setPrice(((SlotButton) e.getSource()).getIndex(), Double.parseDouble(newPrice));
            }
            else
                JOptionPane.showMessageDialog(null, "Error. Non-integer characters are not allowed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    class CancelButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            vendingMachine.giveBackMoney();
            view.setTotalLabel("Total: " + vendingMachine.getTotalReceived());
        }
    }

    class SetPriceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.initializePopupPrice((SlotButton) e.getSource(), 0, 0, ((SlotButton) e.getSource()).getIndex());
            view.addConfirmPriceListener(new ConfirmPriceListener());
            view.showPopupPrice();
        }
    }

    class ShowMaintenanceListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showMaintenanceMenu();
        }
    }
    class InsertMoneyListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();
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

    class BackToFeaturesListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showTestMenu();
        }
    }

    class BackToMainListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showMainMenu();
        }
    }

    class CreateVMListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showCreateMenu();
        }
    }

    class ExitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.disposeWindow();
        }
    }

    class RegularListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            vendingMachine = new VendingMachine();
            view.showMainMenu();
            for(int ctr = 0; ctr < 8; ctr++)
                view.initializeSlot(ctr, vendingMachine.getItemName(ctr), vendingMachine.getItemCalories(ctr), vendingMachine.getItemPrice(ctr));
            view.addRestockListener(new RestockListener());
            view.addSetPriceListener(new SetPriceListener());
            view.addDispenseListener(new DispenseListener());

            JOptionPane.showMessageDialog(null, "Regular vending machine has been created.","Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class SpecialListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            vendingMachine = new SpecialVendingMachine();
            view.showMainMenu();
            JOptionPane.showMessageDialog(null, "Special vending machine has been created.","Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class TestListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(vendingMachine == null)
                JOptionPane.showMessageDialog(null, "Error. Please create vending machine before testing.", "Error", JOptionPane.ERROR_MESSAGE);
            else{
                view.showTestMenu();
            }

        }
    }

    class VendingFeaturesListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            vendingMachine.resetTotalReceived();
            view.setTotalLabel("Total: " + vendingMachine.getTotalReceived());
            view.showVendingFeatures();
        }
    }
}
