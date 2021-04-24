package invoiceR;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class CustomerController implements Initializable {

    Connect customerConnect = new Connect();
    AlertController alert = new AlertController();

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField postalCodeField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField addressTypeField;

    @FXML
    private TextField houseNumberField;

    @FXML
    private TextField stairWayField;

    @FXML
    private TextField floorField;

    @FXML
    private CheckBox invoiceAddressCheckBox;

    @FXML
    private TextField deliveryCityField;

    @FXML
    private TextField deliveryPostalCodeField;

    @FXML
    private TextField deliveryAddressField;

    @FXML
    private TextField deliveryAddressTypeField;

    @FXML
    private TextField deliveryHouseNumberField;

    @FXML
    private TextField deliveryStairWayField;

    @FXML
    private TextField deliveryFloorField;

    @FXML
    private TextField VATNumberField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField webPageField;

    @FXML
    private TextField customerBankAccountField;

    @FXML
    private TextField customerCommentField;

    @FXML
    private Button cancelCustomerButton;

    @FXML
    private Button doneCustomerButton;

    @FXML
    private Pane deliveryAddressPane;

    @FXML
    void closeCustomerScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/mainStage.fxml"));
        Stage mainStage = new Stage();
        mainStage.setScene(new Scene(root));
        mainStage.setMaximized(true);
        Image icon = new Image(getClass().getResourceAsStream("images/invoice.png"));
        mainStage.getIcons().add(icon);
        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.show();

        Stage stage = (Stage) cancelCustomerButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doneAddCustomer(ActionEvent event) throws IOException {
        UUID uuid = UUID.randomUUID();
        if (customerNameField.getText().isEmpty() || addressField.getText().isEmpty()
                || postalCodeField.getText().isEmpty() || cityField.getText().isEmpty()) {
            alert.emptyTextAlert();
        } else {
            customerConnect.addNewCustomer(
                    String.valueOf(uuid), customerNameField.getText(), cityField.getText(), postalCodeField.getText(),
                    addressField.getText(), addressTypeField.getText(), houseNumberField.getText(), stairWayField.getText(),
                    floorField.getText(), deliveryCityField.getText(), deliveryPostalCodeField.getText(), deliveryAddressField.getText(),
                    deliveryAddressTypeField.getText(), deliveryHouseNumberField.getText(), deliveryStairWayField.getText(),
                    deliveryFloorField.getText(), VATNumberField.getText(), telephoneField.getText(),
                    emailField.getText(), webPageField.getText(), customerBankAccountField.getText(), customerCommentField.getText(), checkIfHasSameAddress());

            Stage stage = (Stage) doneCustomerButton.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource("scenes/mainStage.fxml"));
            Stage mainStage = new Stage();
            mainStage.setScene(new Scene(root));
            mainStage.setMaximized(true);
            Image icon = new Image(getClass().getResourceAsStream("images/invoice.png"));
            mainStage.getIcons().add(icon);
            mainStage.initStyle(StageStyle.UNDECORATED);
            mainStage.show();
        }
    }

    @FXML
    void fillDeliveryAddress(ActionEvent event) {
        if (invoiceAddressCheckBox.isSelected()) {
            deliveryAddressPane.setDisable(true);
            deliveryCityField.setText(cityField.getText());
            deliveryPostalCodeField.setText(postalCodeField.getText());
            deliveryAddressField.setText(addressField.getText());
            deliveryAddressTypeField.setText(addressTypeField.getText());
            deliveryHouseNumberField.setText(houseNumberField.getText());
            deliveryStairWayField.setText(stairWayField.getText());
            deliveryFloorField.setText(floorField.getText());
        } else {
            deliveryAddressPane.setDisable(false);
            deliveryAddressField.clear();
            deliveryCityField.clear();
            deliveryPostalCodeField.clear();
            deliveryAddressField.clear();
            deliveryAddressTypeField.clear();
            deliveryHouseNumberField.clear();
            deliveryStairWayField.clear();
            deliveryFloorField.clear();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deliveryAddressPane.setDisable(false);
    }

    private int checkIfHasSameAddress() {
        if (invoiceAddressCheckBox.isSelected()) {
            return 1;
        } else return 0;
    }
}
