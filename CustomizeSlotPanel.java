import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class for parts of display when customizing pizza
 */
public class CustomizeSlotPanel extends JPanel{
    private JButton addButton;
    private JButton removeButton;
    private JLabel amount;
    private JLabel itemName;
    private String progressDisplay;

    /**
     * Initializes components of the display and sets their alignments, etc.
     */
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

    /**
     * Gets name of item the panel is for
     * @return name of item
     */
    public String getItemName(){return itemName.getText();}

    /**
     * Sets what text is to be displayed when pizza is being made (in progress)
     * @param display - text to be displayed
     */
    public void setProgressDisplay(String display){this.progressDisplay = display;}

    /**
     * Gets the text to be displayed when pizza is being made (in progress)
     * @return text to be displayed
     */
    public String getProgressDisplay(){return this.progressDisplay;}

    /**
     * Adds actions listener for the "+" button
     * @param listener - listener for button
     */
    public void addAddListener(ActionListener listener){
        addButton.addActionListener(listener);
    }

    /**
     * Sets text display based on slot's item
     * @param name - name of item
     */
    public void setItemName(String name){
        itemName.setText(name);
    }

    /**
     * Adds action listener for "-" button
     * @param listener - listener for button
     */
    public void addRemoveListener(ActionListener listener){
        removeButton.addActionListener(listener);
    }

    /**
     * Increases the label of the amount by one
     */
    public void increaseAmountLabel(){
        amount.setText("" + ((int) ((Double.parseDouble(amount.getText()))+1))); //converts the text in the label to a number before setting text
    }

    /**
     * Decreases the label of the amount by one
     */
    public void decreaseAmountLabel(){
        amount.setText("" + ((int) ((Double.parseDouble(amount.getText()))-1))); //converts the text in the label to a number before setting text
    }

    /**
     * Sets the name of the "+" button
     * @param name - name for the button
     */
    public void setAddButtonName(String name){
        addButton.setName(name);
    }

    /**
     * Sets the name of the "-" button
     * @param name - name foe the button
     */
    public void setRemoveButtonName(String name){
        removeButton.setName(name);
    }

    /**
     * Gets the number in the amount label
     * @return number displayed in amount
     */
    public double getAmount(){return Double.parseDouble(amount.getText());} //converts text in label to number
}
