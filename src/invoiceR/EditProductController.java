package invoiceR;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProductController implements Initializable {

    public EditProductController() {
    }

    Double VAT;

    Connect connect = new Connect();
    PriceCalculator priceCalculator = new PriceCalculator();

    ObservableList<String> editTypeList = FXCollections.observableArrayList("Termék", "Szolgáltatás");
    ObservableList<String> editVATTypeList = FXCollections.observableArrayList("0%", "5%", "18%", "27%");

    @FXML
    private ComboBox<String> editVATtype;

    @FXML
    private ComboBox<String> editSelectType;

    @FXML
    private Button editCancelProductButton;

    @FXML
    private Button editDoneProductButton;

    @FXML
    private Button deleteProductButton;

    @FXML
    private TextField editProductNetPrice;

    @FXML
    private TextField editProductGrossPrice;

    @FXML
    private TextField editPurchaseNetPrice;

    @FXML
    private TextField editPurchaseGrossPrice;

    @FXML
    private HBox editDiscountPriceHBox;

    @FXML
    private TextField editDiscountNetPrice;

    @FXML
    private TextField editDiscountGrossPrice;

    @FXML
    private TextField editProductNameField;

    @FXML
    private CheckBox editDiscountCheckbox;

    @FXML
    private TextArea editProductCommentField;

    @FXML
    private Text storageQuantity;

    @FXML
    private TextField editProductCodeField;

    @FXML
    private TextField editTeszorField;

    @FXML
    void closeProductScene(ActionEvent event) {
        Stage stage = (Stage) editCancelProductButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteProduct(ActionEvent event) {
        connect.deleteProduct(MainController.productID);
        Stage stage = (Stage) deleteProductButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void editAddDiscountPrice(ActionEvent event) {
        if (editDiscountCheckbox.isSelected()) {
            editDiscountPriceHBox.setDisable(false);
        } else {
            editDiscountPriceHBox.setDisable(true);
            editDiscountGrossPrice.clear();
            editDiscountNetPrice.clear();
        }
    }

    @FXML
    void editCalculateDiscountGrossPrice(KeyEvent event) {
        priceCalculator.calculateGrossPrices(editDiscountNetPrice, editDiscountGrossPrice, VAT);
    }

    @FXML
    void editCalculateDiscountNetPrice(KeyEvent event) {
        priceCalculator.calculateNetPrices(editDiscountGrossPrice, editDiscountNetPrice, VAT);
    }

    @FXML
    void editCalculateGross(KeyEvent event) {
        priceCalculator.calculateGrossPrices(editProductNetPrice, editProductGrossPrice, VAT);
    }

    @FXML
    void editCalculateNet(KeyEvent event) {
        priceCalculator.calculateNetPrices(editProductGrossPrice, editProductNetPrice, VAT);
    }

    @FXML
    void editCalculatePurchaseGross(KeyEvent event) {
        priceCalculator.calculateGrossPrices(editPurchaseNetPrice, editPurchaseGrossPrice, VAT);
    }

    @FXML
    void editCalculatePurchaseNet(KeyEvent event) {
        priceCalculator.calculateNetPrices(editPurchaseGrossPrice, editPurchaseNetPrice, VAT);
    }

    @FXML
    void editDoneAddProduct(ActionEvent event) throws IOException {
        if (editProductNameField.getText().isEmpty() || editProductNetPrice.getText().isEmpty() || editProductGrossPrice.getText().isEmpty()) {
            Parent root = FXMLLoader.load(getClass().getResource("scenes/emptyFieldErrorStage.fxml"));
            Stage missingErrorStage = new Stage();
            missingErrorStage.setScene(new Scene(root));
            missingErrorStage.initStyle(StageStyle.UTILITY);
            missingErrorStage.show();
        } else {
            connect.updateProductDetails(editTeszorField.getText(), MainController.productID,
                    getServiceValueFromString((String) editSelectType.getSelectionModel().getSelectedItem()),
                    editProductNameField.getText(), editProductCommentField.getText(), editProductCodeField.getText(),
                    editProductNetPrice.getText(), editProductGrossPrice.getText(), editPurchaseNetPrice.getText(),
                    editPurchaseGrossPrice.getText(), editDiscountNetPrice.getText(), editDiscountGrossPrice.getText(),
                    getDiscountedValue(editDiscountCheckbox.isSelected()));
            Stage stage = (Stage) editDoneProductButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void editSetType(ActionEvent event) {

    }

    @FXML
    void editSetVATValue(ActionEvent event) {
        String VATString = (String) editVATtype.getSelectionModel().getSelectedItem();
        switch (VATString) {
            case "0%":
                VAT = 1.0;
                break;
            case "5%":
                VAT = 1.05;
                break;
            case "18%":
                VAT = 1.18;
                break;
            default:
                VAT = 1.27;
        }
        priceCalculator.updateGrossPrices(VAT, editProductNetPrice, editProductGrossPrice, editPurchaseNetPrice,
                editPurchaseGrossPrice, editDiscountNetPrice, editDiscountGrossPrice);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editSelectType.setItems(editTypeList);
        editVATtype.setItems(editVATTypeList);
        editProductNameField.setText(MainController.productName);
        editProductNetPrice.setText(MainController.productNetPrice);
        editProductGrossPrice.setText(MainController.productGrossPrice);
        editProductCodeField.setText(MainController.productCode);
        editProductCommentField.setText(MainController.productComment);
        editPurchaseNetPrice.setText(MainController.purchaseNetPrice);
        editPurchaseGrossPrice.setText(MainController.purchaseGrossPrice);
        editDiscountNetPrice.setText(MainController.discountNetPrice);
        editDiscountGrossPrice.setText(MainController.dicsountGrossPrice);
        editTeszorField.setText(MainController.Teszor);
        editDiscountCheckbox.setSelected(MainController.isDiscounted);
        editSelectType.getSelectionModel().select(getServiceValue(MainController.isService));
        editVATtype.getSelectionModel().select(getVATValue(MainController.productNetPrice, MainController.productGrossPrice));
        if (MainController.isDiscounted) {
            editDiscountPriceHBox.setDisable(false);
        }
    }

    private int getServiceValue(boolean isItService) {
        if (isItService) {
            return 1;
        } else {
            return 0;
        }
    }

    private int getServiceValueFromString(String serviceOrProduct) {
        if (serviceOrProduct.equals("Termék")) {
            return 0;
        } else {
            return 1;
        }
    }

    private int getDiscountedValue(boolean isItDiscounted) {
        if (isItDiscounted) {
            return 1;
        } else {
            return 0;
        }
    }

    private int getVATValue(String net, String gross) {
        double netPrice = Double.parseDouble(net);
        double grossPrice = Double.parseDouble(gross);
        double percent = (grossPrice / netPrice);
        VAT = percent;
        if (percent == 1) {
            return 0;
        }
        if (percent == 1.05) {
            return 1;
        }
        if (percent == 1.18) {
            return 2;
        } else {
            return 3;
        }
    }


}
