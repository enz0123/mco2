import javax.swing.*;
import java.awt.*;

public class extraSlotsPanel extends CustomizeSlotPanel {

    private JPanel infoPanel;
    private JLabel stock;
    private JLabel calories;
    private JLabel price;

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

    public void setStock(String text){stock.setText(text);}

    public void setCalories(String text){calories.setText(text);}
    public void setPrice(String text){price.setText(text);}


}
