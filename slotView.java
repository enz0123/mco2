import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

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

    public void testingScreen(){
        info.remove(maintenanceButtons);
        info.add(dispense, BorderLayout.SOUTH);
    }

    public void maintenanceScreen(){
        info.remove(dispense);
        info.add(maintenanceButtons, BorderLayout.SOUTH);
    }

    public void setStock(String stockText){
        this.stock.setText(stockText);
    }

    public void setPrice(double price){
        this.price.setText("Price: " + price);
    }

    public void addRestockListener(ActionListener listener){
        this.restock.addActionListener(listener);
    }

    public void addSetPriceListener(ActionListener listener){
        this.setPrice.addActionListener(listener);
    }

    public void addDispenseListener(ActionListener listener){
        this.dispense.addActionListener(listener);
    }

    public void setButtonName(String buttonName){
        this.restock.setName(buttonName);
        this.setPrice.setName(buttonName);
    }

    public void setDispenseVisible(boolean set){dispense.setVisible(set);}

    public void addPicture(JLabel picture){pictures.add(picture);}

    public void clearPictures(){pictures.removeAll();}

    public void setBlackContainer(){
        this.pictures.setBackground(Color.black);
    }
}
