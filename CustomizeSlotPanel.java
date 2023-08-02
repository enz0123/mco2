import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomizeSlotPanel extends JPanel{
    private JButton addButton;
    private JButton removeButton;
    private JLabel amount;
    private JLabel itemName;
    private String progressDisplay;

    public CustomizeSlotPanel(){
        super(new BorderLayout());

        itemName = new JLabel();
        itemName.setHorizontalAlignment(SwingConstants.CENTER);
        amount = new JLabel("0");
        amount.setHorizontalAlignment(SwingConstants.CENTER);
        removeButton = new JButton("-");
        addButton = new JButton("+");

        add(itemName, BorderLayout.NORTH);
        add(removeButton, BorderLayout.WEST);
        add(addButton, BorderLayout.EAST);
        add(amount, BorderLayout.CENTER);
    }

    public String getItemName(){return itemName.getText();}

    public void setProgressDisplay(String display){this.progressDisplay = display;}

    public String getProgressDisplay(){return this.progressDisplay;}

    public void addAddListener(ActionListener listener){
        addButton.addActionListener(listener);
    }

    public void setItemName(String name){
        itemName.setText(name);
    }

    public void addRemoveListener(ActionListener listener){
        removeButton.addActionListener(listener);
    }

    public void increaseAmountLabel(){
        amount.setText("" + ((int) ((Double.parseDouble(amount.getText()))+1)));
    }

    public void decreaseAmountLabel(){
        amount.setText("" + ((int) ((Double.parseDouble(amount.getText()))-1)));
    }

    public void setAddButtonName(String name){
        addButton.setName(name);
    }

    public void setRemoveButtonName(String name){
        removeButton.setName(name);
    }

    public double getAmount(){return Double.parseDouble(amount.getText());}
}
