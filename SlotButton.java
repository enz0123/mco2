import javax.swing.*;

public class SlotButton extends JButton {
    private int index;

    public SlotButton(String text, int index){
        super(text);
        this.index = index;
    }

    public int getIndex(){return this.index;}
}
