package invoiceR;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class ProductController implements Initializable {


    double VAT = 1.27;
    boolean isService = false;
    String replacementID = "";

    ObservableList<String> typeList = FXCollections.observableArrayList("Termék", "Szolgáltatás");
    ObservableList<String> VATType = FXCollections.observableArrayList("0%", "5%", "18%", "27%");
    ObservableList<Product> replecementProducts = FXCollections.observableArrayList();
    ObservableList<String> replecementProductsString = FXCollections.observableArrayList();


    Connect productConnect = new Connect();
    Calculator calculator = new Calculator();
    AlertController alertController = new AlertController();

    @FXML
    private ComboBox<String> replecementProduct;

    @FXML
    private Button cancelProductButton;

    @FXML
    private Button doneProductButton;

    @FXML
    private TextField productNetPrice;

    @FXML
    private TextField productGrossPrice;

    @FXML
    private TextField purchaseNetPrice;

    @FXML
    private TextField purchaseGrossPrice;

    @FXML
    private TextField discountNetPrice;

    @FXML
    private TextField discountGrossPrice;

    @FXML
    private TextField productNameField;

    @FXML
    private CheckBox discountCheckbox;

    @FXML
    private TextField productCodeField;

    @FXML
    private TextArea productCommentField;

    @FXML
    private HBox discountPriceHBox;

    @FXML
    private ComboBox selectType;

    @FXML
    private ComboBox VATtype;

    @FXML
    private TextField TeszorField;

    @FXML
    void changeReplacementProduct(ActionEvent event) {
        int selectedIndex = replecementProduct.getSelectionModel().getSelectedIndex();
        replacementID = replecementProducts.get(selectedIndex).Id;
    }

    @FXML
    void closeProductScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelProductButton.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("scenes/mainStage.fxml"));
        Stage mainStage = new Stage();
        mainStage.setScene(new Scene(root));
        Image icon = new Image(getClass().getResourceAsStream("images/invoice.png"));
        mainStage.getIcons().add(icon);
        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.setMaximized(true);
        mainStage.show();
    }

    @FXML
    void doneAddProduct(ActionEvent event) throws IOException {
        if (productNameField.getText().isEmpty() || productNetPrice.getText().isEmpty() || productGrossPrice.getText().isEmpty()) {
            alertController.emptyTextAlert();
        } else {
            UUID uuid = UUID.randomUUID();

            productConnect.addNewProduct(TeszorField.getText(), String.valueOf(uuid), checkIfService(isService), productNameField.getText(),
                    productCommentField.getText(), productCodeField.getText(),
                    productNetPrice.getText(), productGrossPrice.getText(), purchaseNetPrice.getText(),
                    purchaseGrossPrice.getText(), discountNetPrice.getText(), discountGrossPrice.getText(),
                    checkIfDiscounted(), replacementID);

            Stage stage = (Stage) doneProductButton.getScene().getWindow();
            stage.close();

            alertController.newProductAdded();

            Parent root = FXMLLoader.load(getClass().getResource("scenes/mainStage.fxml"));
            Stage mainStage = new Stage();
            mainStage.setScene(new Scene(root));
            Image icon = new Image(getClass().getResourceAsStream("images/invoice.png"));
            mainStage.getIcons().add(icon);
            mainStage.initStyle(StageStyle.UNDECORATED);
            mainStage.setMaximized(true);
            mainStage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillReplacementList();
        selectType.setItems(typeList);
        VATtype.setItems(VATType);
    }

    public void calculateNet(KeyEvent keyEvent) {
        calculator.calculateNetPrices(productGrossPrice, productNetPrice, VAT);
    }

    public void calculateGross(KeyEvent keyEvent) {
        calculator.calculateGrossPrices(productNetPrice, productGrossPrice, VAT);
    }

    public void calculateDiscountGrossPrice(KeyEvent keyEvent) {
        calculator.calculateGrossPrices(discountNetPrice, discountGrossPrice, VAT);
    }

    public void calculateDiscountNetPrice(KeyEvent keyEvent) {
        calculator.calculateNetPrices(discountGrossPrice, discountNetPrice, VAT);
    }

    public void calculatePurchaseNet(KeyEvent keyEvent) {
        calculator.calculateNetPrices(purchaseGrossPrice, purchaseNetPrice, VAT);
    }

    public void calculatePurchaseGross(KeyEvent keyEvent) {
        calculator.calculateGrossPrices(purchaseNetPrice, purchaseGrossPrice, VAT);
    }

    public void addDiscountPrice(ActionEvent actionEvent) {
        if (discountCheckbox.isSelected()) {
            discountPriceHBox.setDisable(false);
        } else {
            discountPriceHBox.setDisable(true);
            discountGrossPrice.clear();
            discountNetPrice.clear();
        }
    }

    public void setVATValue(ActionEvent actionEvent) {
        String VATString = (String) VATtype.getSelectionModel().getSelectedItem();
        switch (VATString) {
            case "0%":
                VAT = 1;
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
        calculator.updateGrossPrices(VAT, productNetPrice, productGrossPrice, purchaseNetPrice,
                purchaseGrossPrice, discountNetPrice, discountGrossPrice);
    }

    public boolean setType(ActionEvent actionEvent) {
        String typeString = (String) selectType.getSelectionModel().getSelectedItem();
        if (typeString.equals("Termék")) {
            isService = false;
        } else {
            isService = true;
        }
        return isService;
    }

    private int checkIfService(boolean service) {
        if (service) {
            return 1;
        } else {
            return 0;
        }
    }

    private int checkIfDiscounted() {
        if (discountCheckbox.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    private void fillReplacementList() {
        replecementProducts.clear();
        replecementProductsString.clear();
        productConnect.getProducts();
        for (int i = 0; i < Connect.productList.size(); i++) {
            replecementProducts.add(new Product(Connect.productList.get(i).getId(), Connect.productList.get(i).getName(),
                    Connect.productList.get(i).getReplacementID()));
            replecementProductsString.add(replecementProducts.get(i).Name);
            replecementProduct.getItems().add(replecementProductsString.get(i));
        }
    }

}