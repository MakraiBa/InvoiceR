package invoiceR;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.w3c.dom.Text;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    Product product=new Product();
    Text netValue;
    Text grossValue;

    @FXML
    private Button addModifyProductButton;

    @FXML
    private Button cancelProductButton;

    @FXML
    private TextField productNetPrice;

    @FXML
    private TextField productGrossPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void calculateNet(KeyEvent keyEvent) {
        String grossPriceString=productGrossPrice.getText();
        float grossPriceFloat=Float.parseFloat(grossPriceString);
        float netPriceFloat= (float) (grossPriceFloat/1.27);
        productNetPrice.setText(String.valueOf(netPriceFloat));
    }
}