import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

/**
 * This class displays each slot in the vending machine
 */
public class slotView extends JPanel {
    private JLabel name;
    private JPanel pictures;
    private JPanel info;
    private JLabel stock;
    private JLabel calories;
    private JLabel price;
    private SlotButton dispense;
    private SlotButton restock;
    private SlotButton setPrice;
    private JPanel maintenanceButtons;
    private JPanel details;

    /**
     * Constructor initializes elements, adds elements, and sets labels
     * @param slotNum - slot number associated with slotView
     * @param name - name of item associated with slotView
     * @param calories - calories of item associated with slotView
     * @param price - price of item associated with slotView
     */

    public slotView(int slotNum, String name, double calories, double price){
        super(new BorderLayout());
        this.name = new JLabel(name);
        this.name.setHorizontalAlignment(SwingConstants.CENTER);
        this.pictures = new JPanel(new GridLayout(2,5));
        this.info = new JPanel(new BorderLayout());
        this.stock = new JLabel("Stock: 0");
        this.calories = new JLabel("Calories: " + calories);
        this.price = new JLabel("Price: " + price);
        this.dispense = new SlotButton("Dispense", slotNum);
        this.restock = new SlotButton("Restock", slotNum);
        this.setPrice = new SlotButton("Set Price", slotNum);
        this.maintenanceButtons = new JPanel(new FlowLayout());
        this.details = new JPanel(new FlowLayout());

        add(this.name, BorderLayout.NORTH);
        add(pictures, BorderLayout.CENTER);
        add(info, BorderLayout.SOUTH);
        info.add(details, BorderLayout.NORTH);
        details.add(stock);
        details.add(this.calories);
        details.add(this.price);


        maintenanceButtons.add(restock);
        maintenanceButtons.add(setPrice);
    }

    /**
     * Sets up slot for vending features view
     */
    public void testingScreen(){
        info.remove(maintenanceButtons); //maintenance not used in vending features
        info.add(dispense, BorderLayout.SOUTH); //dispense is used in vending features
    }


    /**
     * Sets up slot for maintenance features view
     */
    public void maintenanceScreen(){
        info.remove(dispense); //dispense is not used in vending features
        info.add(maintenanceButtons, BorderLayout.SOUTH); //maintenance elements used in vending features
    }

    /**
     * Sets stock display label
     * @param stockText - updated value of stock in text
     */
    public void setStock(String stockText){
        this.stock.setText(stockText);
    }

    /**
     * Sets price display label
     * @param price - updated price of stock in text
     */
    public void setPrice(double price){
        this.price.setText("Price: " + price);
    }

    /**
     * Adds action listener for restock button
     * @param listener - listener for button
     */
    public void addRestockListener(ActionListener listener){
        this.restock.addActionListener(listener);
    }

    /**
     * Adds action listener for Set Price button
     * @param listener - listener for button
     */
    public void addSetPriceListener(ActionListener listener){
        this.setPrice.addActionListener(listener);
    }

    /**
     * Adds action listener for Dispense button
     * @param listener - listener for button
     */
    public void addDispenseListener(ActionListener listener){
        this.dispense.addActionListener(listener);
    }

    /**
     * Sets the name of button names for easier referencing
     * @param buttonName - name of button
     */
    public void setButtonName(String buttonName){
        this.restock.setName(buttonName);
        this.setPrice.setName(buttonName);
    }

    /**
     * Sets the visibility of the dispense button
     * @param set - true if visible, false otherwise
     */
    public void setDispenseVisible(boolean set){dispense.setVisible(set);}

    /**
     * Adds picture of item associated with slot to display
     * @param picture - picture of item
     */
    public void addPicture(JLabel picture){pictures.add(picture);}

    /**
     * Removes all pictures in view of slot
     */
    public void clearPictures(){pictures.removeAll();}

    /**
     * Makes background of pictures panel black
     */
    public void setBlackContainer(){
        this.pictures.setBackground(Color.black);
    }
}
