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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.UUID;

public class ReceiveNoteController implements Initializable {

    Connect connect = new Connect();
    Calculator calculator = new Calculator();
    Seller seller = new Seller();

    ObservableList<String> paymentMethodList = FXCollections.observableArrayList("Készpénz", "Átutalás - 8 nap", "Átutalás - 15 nap", "Átutalás - 30 nap", "Utánvét", "Bankkártya");
    ObservableList<Product> receiveNoteProductList = FXCollections.observableArrayList();

    @FXML
    private TextField sellerNameField;

    @FXML
    private TextField sellerVATField;

    @FXML
    private TextField sellerAddressField;

    @FXML
    private TextField sellerBankNumberField;

    @FXML
    private TextField sellerPhoneField;

    @FXML
    private TextField sellerEmailField;

    @FXML
    private Button selectSellerButton;

    @FXML
    private TextField receiveNoteNameField;

    @FXML
    private TextField receiveNoteVATField;

    @FXML
    private TextField receiveNoteAddressField;

    @FXML
    private TextField receiveNoteBankNumberField;

    @FXML
    private TextField receiveNotePhoneField;

    @FXML
    private TextField receiveNoteEmailField;

    @FXML
    private ComboBox<String> selectPaymentMethod;

    @FXML
    private DatePicker currentDate;

    @FXML
    private DatePicker paymentDate;

    @FXML
    private DatePicker fulfilmentDate;

    @FXML
    private TableView<?> receiveNoteProductTable;

    @FXML
    private TableColumn<?, ?> receiveNoteProductNameColumn;

    @FXML
    private TableColumn<?, ?> receiveNoteProductNetPriceColumn;

    @FXML
    private TableColumn<?, ?> receiveNoteProductGrossPriceColumn;

    @FXML
    private TableColumn<?, ?> receiveNoteProductQuantityColumn;

    @FXML
    private TableColumn<?, ?> receiveNoteProductNumberColumn;

    @FXML
    private TableColumn<?, ?> receiveNoteBoughtQuantityColumn;

    @FXML
    private TextField sumGrossPriceField;

    @FXML
    private TextField sumNetPriceField;

    @FXML
    private Button addProductToReceiveNoteButton;

    @FXML
    private Button removeProductFromReceiveNoteListButton;

    @FXML
    private Button doneReceiveNoteButton;

    @FXML
    private Button cancelReceiveNoteButton;

    @FXML
    void addProductToReceiveNote(ActionEvent event) {

    }

    @FXML
    void cancelReceiveNote(ActionEvent event) {

    }

    @FXML
    void changePaymentMethod(ActionEvent event) {
        String paymentString = (String) selectPaymentMethod.getSelectionModel().getSelectedItem();
        paymentDate.setValue(calculator.calculateDays(calculator.calculateDaysToAdd(paymentString)));
    }

    @FXML
    void closeReceiveNote(ActionEvent event) {
        UUID uuid = UUID.randomUUID();
        String receiveNoteId = String.valueOf(uuid);
        String sellerId = SelectBuyerController.buyerId;
        String receiveNoteName = SelectBuyerController.customerName;
        String receiveNoteFullAddress = SelectBuyerController.customerFullAddress;
        String receiveNoteSumNetPrice = sumNetPriceField.getText();
        String receiveNoteSumGrossPrice = sumGrossPriceField.getText();
        String receiveNoteCurrentDateString = currentDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        connect.addNewReceiveNote(receiveNoteId, sellerId, receiveNoteName,
                receiveNoteFullAddress, receiveNoteSumNetPrice, receiveNoteSumGrossPrice, receiveNoteCurrentDateString);

        Stage stage = (Stage) doneReceiveNoteButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void removeProductReceiveNoteList(ActionEvent event) {

    }

    @FXML
    void selectSeller(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/buyerSelectStage.fxml"));
        Stage buyerSelectStage = new Stage();
        buyerSelectStage.setScene(new Scene(root));
        buyerSelectStage.show();

        Stage stage = (Stage) sumGrossPriceField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void updateQuantity(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        receiveNoteNameField.setText(Seller.defaultSeller.sellerName);
        receiveNoteAddressField.setText(seller.setSellerAddress(
                Seller.defaultSeller.getSellerPostalCode(), Seller.defaultSeller.getSellerCity(),
                Seller.defaultSeller.getSellerAddress(), Seller.defaultSeller.getSellerAddressType(),
                Seller.defaultSeller.getSellerHouseNumber(), Seller.defaultSeller.getSellerStairway(),
                Seller.defaultSeller.getSellerFloor()
        ));
        receiveNoteEmailField.setText(Seller.defaultSeller.sellerEmail);
        receiveNoteVATField.setText(Seller.defaultSeller.sellerVAT);
        receiveNotePhoneField.setText(Seller.defaultSeller.sellerPhone);
        receiveNoteBankNumberField.setText(Seller.defaultSeller.sellerBankAccount);
        selectPaymentMethod.setItems(paymentMethodList);
        currentDate.setValue(LocalDate.now());
        paymentDate.setValue(calculator.calculateDays(0));
        fulfilmentDate.setValue(LocalDate.now());

        sellerNameField.setText(SelectBuyerController.customerName);
        sellerAddressField.setText(SelectBuyerController.customerFullAddress);
        sellerVATField.setText(SelectBuyerController.customerVAT);
        sellerBankNumberField.setText(SelectBuyerController.customerBankNumber);
        sellerPhoneField.setText(SelectBuyerController.customerPhone);
        sellerEmailField.setText(SelectBuyerController.customerEmail);

        sumNetPriceField.setText(String.valueOf(calculator.setSumNetPrice(receiveNoteProductList)));
        sumGrossPriceField.setText(String.valueOf(calculator.setSumGrossPrice(receiveNoteProductList)));
    }

}
