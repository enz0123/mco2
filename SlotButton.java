import javax.swing.*;

/**
 * This class is just a normal JButton except it contains an index for referencing
 */
public class SlotButton extends JButton {
    private int index;

    /**
     * Constructor initializes text displayed on button and index
     * @param text - text displayed on button
     * @param index - number associated with SlotButton
     */
    public SlotButton(String text, int index){
        super(text);
        this.index = index;
    }

    /**
     * Gets index of the SlotButton
     * @return index of the SlotButton
     */
    public int getIndex(){return this.index;}
}
