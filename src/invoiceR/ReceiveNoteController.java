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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;

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
    private TableView<Product> receiveNoteProductTable;

    @FXML
    private TableColumn<Product, String> receiveNoteProductNameColumn;

    @FXML
    private TableColumn<Product, String> receiveNoteProductNetPriceColumn;

    @FXML
    private TableColumn<Product, String> receiveNoteProductGrossPriceColumn;

    @FXML
    private TableColumn<Product, String> receiveNoteProductQuantityColumn;

    @FXML
    private TableColumn<Product, String> receiveNoteProductNumberColumn;

    @FXML
    private TableColumn<Product, Integer> receiveNoteBoughtQuantityColumn;

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
    void addProductToReceiveNote(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/productSelectStage.fxml"));
        Stage addProductToInvoice = new Stage();
        addProductToInvoice.setScene(new Scene(root));
        addProductToInvoice.initStyle(StageStyle.UTILITY);
        addProductToInvoice.show();

        Stage stage = (Stage) addProductToReceiveNoteButton.getScene().getWindow();
        stage.close();
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

        connect.increaseStockQuantity(receiveNoteProductList);

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

        receiveNoteProductTable.setEditable(true);
        receiveNoteProductNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("Name"));
        receiveNoteProductNetPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productNetPrice"));
        receiveNoteProductGrossPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productGrossPrice"));
        receiveNoteProductQuantityColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("Stock"));
        receiveNoteProductNumberColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("ProductNr"));
        receiveNoteBoughtQuantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productQuantity"));
        receiveNoteBoughtQuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        receiveNoteProductList.addAll(SelectProductController.addedProducts);
        receiveNoteProductTable.setItems(receiveNoteProductList);

        sumNetPriceField.setText(String.valueOf(calculator.setSumNetPrice(receiveNoteProductList)));
        sumGrossPriceField.setText(String.valueOf(calculator.setSumGrossPrice(receiveNoteProductList)));
    }

    public void updateQuantity(TableColumn.CellEditEvent editcell) {
        Product selectedproduct = receiveNoteProductTable.getSelectionModel().getSelectedItem();
        selectedproduct.setProductQuantity(editcell.getNewValue().hashCode());
        sumNetPriceField.setText(String.valueOf(calculator.setSumNetPrice(receiveNoteProductList)));
        sumGrossPriceField.setText(String.valueOf(calculator.setSumGrossPrice(receiveNoteProductList)));
    }

}
