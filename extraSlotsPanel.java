import javax.swing.*;
import java.awt.*;

/**
 * Variation of the CustomizeSlotPanel found in customizing display
 */
public class extraSlotsPanel extends CustomizeSlotPanel {

    private JPanel infoPanel;
    private JLabel stock;
    private JLabel calories;
    private JLabel price;

    /**
     * Initializes elements of the class
     */
    public extraSlotsPanel(){
        super();

        infoPanel = new JPanel(new FlowLayout());

        stock = new JLabel();
        calories = new JLabel();
        price = new JLabel();

        infoPanel.add(stock);
        infoPanel.add(calories);
        infoPanel.add(price);
        add(infoPanel, BorderLayout.SOUTH);
    }

}
