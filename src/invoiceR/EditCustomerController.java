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

public class EditCustomerController implements Initializable {

    Connect connect = new Connect();
    AlertController alert = new AlertController();

    @FXML
    private TextField editCustomerNameField;

    @FXML
    private TextField editCityField;

    @FXML
    private TextField editPostalCodeField;

    @FXML
    private TextField editAddressField;

    @FXML
    private TextField editAddressTypeField;

    @FXML
    private TextField editHouseNumberField;

    @FXML
    private TextField editStairWayField;

    @FXML
    private TextField editFloorField;

    @FXML
    private CheckBox editInvoiceAddressCheckBox;

    @FXML
    private Pane editDeliveryAddressPane;

    @FXML
    private TextField editDeliveryCityField;

    @FXML
    private TextField editDeliveryPostalCodeField;

    @FXML
    private TextField editDeliveryAddressField;

    @FXML
    private TextField editDeliveryAddressTypeField;

    @FXML
    private TextField editDeliveryHouseNumberField;

    @FXML
    private TextField editDeliveryStairWayField;

    @FXML
    private TextField editDeliveryFloorField;

    @FXML
    private TextField editVATNumberField;

    @FXML
    private TextField editTelephoneField;

    @FXML
    private TextField editEmailField;

    @FXML
    private TextField editWebPageField;

    @FXML
    private TextField editCustomerCommentField;

    @FXML
    private TextField editBankAccountField;

    @FXML
    private Button editCustomerDoneButton;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private Button cancelEditCustomerButton;

    @FXML
    void closeEditCustomerScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelEditCustomerButton.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("scenes/mainStage.fxml"));
        Stage mainStage = new Stage();
        mainStage.setScene(new Scene(root));
        mainStage.initStyle(StageStyle.UNDECORATED);
        Image icon=new Image(getClass().getResourceAsStream("images/invoice.png"));
        mainStage.getIcons().add(icon);
        mainStage.setMaximized(true);
        mainStage.show();
    }

    @FXML
    void deleteCustomer(ActionEvent event) throws IOException {
        alert.deleteCustomerConfirmAlert(MainController.customerId, MainController.customerBillingName);

        Parent root = FXMLLoader.load(getClass().getResource("scenes/mainStage.fxml"));
        Stage mainStage = new Stage();
        mainStage.setScene(new Scene(root));
        Image icon=new Image(getClass().getResourceAsStream("images/invoice.png"));
        mainStage.getIcons().add(icon);
        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.setMaximized(true);
        mainStage.show();
    }

    @FXML
    void doneEditCustomer(ActionEvent event) throws IOException {
        if (editCustomerNameField.getText().isEmpty() || editAddressField.getText().isEmpty()
                || editPostalCodeField.getText().isEmpty() || editCityField.getText().isEmpty()) {
            alert.emptyTextAlert();
        } else {
            connect.updateCustomerDetails(
                    MainController.customerId, editCustomerNameField.getText(), editCityField.getText(), editPostalCodeField.getText(),
                    editAddressField.getText(), editAddressTypeField.getText(), editHouseNumberField.getText(), editStairWayField.getText(),
                    editFloorField.getText(), editDeliveryCityField.getText(), editDeliveryPostalCodeField.getText(), editDeliveryAddressField.getText(),
                    editDeliveryAddressTypeField.getText(), editDeliveryHouseNumberField.getText(), editDeliveryStairWayField.getText(),
                    editDeliveryFloorField.getText(), editVATNumberField.getText(), editTelephoneField.getText(),
                    editEmailField.getText(), editWebPageField.getText(), editBankAccountField.getText(),
                    editCustomerCommentField.getText(), getSameAddressValue());

            Stage stage = (Stage) editCustomerDoneButton.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource("scenes/mainStage.fxml"));
            Stage mainStage = new Stage();
            mainStage.setScene(new Scene(root));
            Image icon=new Image(getClass().getResourceAsStream("images/invoice.png"));
            mainStage.getIcons().add(icon);
            mainStage.initStyle(StageStyle.UNDECORATED);
            mainStage.setMaximized(true);
            mainStage.show();
        }
    }

    @FXML
    void editFillDeliveryAddress(ActionEvent event) {
        if (editInvoiceAddressCheckBox.isSelected()) {
            editDeliveryAddressPane.setDisable(true);
            editDeliveryCityField.setText(editCityField.getText());
            editDeliveryPostalCodeField.setText(editPostalCodeField.getText());
            editDeliveryAddressField.setText(editAddressField.getText());
            editDeliveryAddressTypeField.setText(editAddressTypeField.getText());
            editDeliveryHouseNumberField.setText(editHouseNumberField.getText());
            editDeliveryStairWayField.setText(editStairWayField.getText());
            editDeliveryFloorField.setText(editFloorField.getText());
        } else {
            editDeliveryAddressPane.setDisable(false);
            editDeliveryAddressField.clear();
            editDeliveryCityField.clear();
            editDeliveryPostalCodeField.clear();
            editDeliveryAddressField.clear();
            editDeliveryAddressTypeField.clear();
            editDeliveryHouseNumberField.clear();
            editDeliveryStairWayField.clear();
            editDeliveryFloorField.clear();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editCustomerNameField.setText(MainController.customerBillingName);
        editCityField.setText(MainController.customerBillingCity);
        editPostalCodeField.setText(MainController.customerBillingPostalCode);
        editAddressField.setText(MainController.customerBillingAddress);
        editAddressTypeField.setText(MainController.customerBillingAddressType);
        editHouseNumberField.setText(MainController.customerBillingHouseNumber);
        editStairWayField.setText(MainController.customerBillingStairway);
        editFloorField.setText(MainController.customerBillingFloor);
        editDeliveryCityField.setText(MainController.customerDeliveryCity);
        editDeliveryPostalCodeField.setText(MainController.customerDeliveryPostalCode);
        editDeliveryAddressField.setText(MainController.customerDeliveryAddress);
        editDeliveryAddressTypeField.setText(MainController.customerDeliveryAddressType);
        editDeliveryHouseNumberField.setText(MainController.customerDeliveryHouseNumber);
        editDeliveryStairWayField.setText(MainController.customerDeliveryStairway);
        editDeliveryFloorField.setText(MainController.customerDeliveryFloor);
        editVATNumberField.setText(MainController.customerVAT);
        editTelephoneField.setText(MainController.customerPhone);
        editEmailField.setText(MainController.customerEmail);
        editWebPageField.setText(MainController.customerWebPage);
        editCustomerCommentField.setText(MainController.customerComment);
        editBankAccountField.setText(MainController.customerBankAccount);
        editInvoiceAddressCheckBox.setSelected(MainController.hasSameAddress);
        if (MainController.hasSameAddress) {
            editDeliveryAddressPane.setDisable(true);
        } else {
            editDeliveryAddressPane.setDisable(false);
        }
    }

    private int getSameAddressValue() {
        if (editInvoiceAddressCheckBox.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

}
