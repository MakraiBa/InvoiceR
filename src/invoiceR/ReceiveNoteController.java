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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    AlertController alertController = new AlertController();

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
    private ImageView maximizeSceneButton;

    @FXML
    private ImageView closeAndReturnButton;

    @FXML
    private ImageView minimizeSceneButton;

    @FXML
    void maximizeScene(MouseEvent event) {
        Stage stage = (Stage) cancelReceiveNoteButton.getScene().getWindow();
        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }

    @FXML
    void minimizeScene(MouseEvent event) {
        Stage stage = (Stage) minimizeSceneButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void cancelAndReturn(MouseEvent event) throws IOException {
        SelectBuyerController.customerBankNumber = "";
        SelectBuyerController.customerName = "";
        SelectBuyerController.customerEmail = "";
        SelectBuyerController.customerFullAddress = "";
        SelectBuyerController.customerPhone = "";
        SelectBuyerController.customerVAT = "";
        Stage stage = (Stage) closeAndReturnButton.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("scenes/mainStage.fxml"));
        Stage returnToMain = new Stage();
        returnToMain.setScene(new Scene(root));
        Image icon = new Image(getClass().getResourceAsStream("images/invoice.png"));
        returnToMain.getIcons().add(icon);
        returnToMain.initStyle(StageStyle.UNDECORATED);
        returnToMain.setMaximized(true);
        returnToMain.show();
    }

    @FXML
    void cancelReceiveNote(ActionEvent event) throws IOException {
        Stage stage = (Stage) closeAndReturnButton.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("scenes/mainStage.fxml"));
        Stage returnToMain = new Stage();
        returnToMain.setScene(new Scene(root));
        Image icon = new Image(getClass().getResourceAsStream("images/invoice.png"));
        returnToMain.getIcons().add(icon);
        returnToMain.initStyle(StageStyle.UNDECORATED);
        returnToMain.setMaximized(true);
        returnToMain.show();
    }

    @FXML
    void addProductToReceiveNote(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/productSelectStage.fxml"));
        Stage addProductToInvoice = new Stage();
        addProductToInvoice.setScene(new Scene(root));
        Image icon = new Image(getClass().getResourceAsStream("images/invoice.png"));
        addProductToInvoice.getIcons().add(icon);
        addProductToInvoice.initStyle(StageStyle.UNDECORATED);
        addProductToInvoice.show();

        Stage stage = (Stage) addProductToReceiveNoteButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void changePaymentMethod(ActionEvent event) {
        String paymentString = (String) selectPaymentMethod.getSelectionModel().getSelectedItem();
        paymentDate.setValue(calculator.calculateDays(calculator.calculateDaysToAdd(paymentString)));
    }

    @FXML
    void closeReceiveNote(ActionEvent event) throws IOException {
        UUID uuid = UUID.randomUUID();
        String receiveNoteId = String.valueOf(uuid);
        String sellerId = SelectBuyerController.buyerId;
        String receiveNoteName = SelectBuyerController.customerName;
        String receiveNoteFullAddress = SelectBuyerController.customerFullAddress;
        String receiveNoteSumNetPrice = sumNetPriceField.getText();
        String receiveNoteSumGrossPrice = sumGrossPriceField.getText();
        String receiveNoteCurrentDateString = currentDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (receiveNoteName == null || receiveNoteProductList.isEmpty()) {
            alertController.emptyFieldAlert();
        } else {
            connect.addNewReceiveNote(receiveNoteId, sellerId, receiveNoteName,
                    receiveNoteFullAddress, receiveNoteSumNetPrice, receiveNoteSumGrossPrice, receiveNoteCurrentDateString);

            connect.increaseStockQuantity(receiveNoteProductList);

            Stage stage = (Stage) doneReceiveNoteButton.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource("scenes/mainStage.fxml"));
            Stage returnToMain = new Stage();
            returnToMain.setScene(new Scene(root));
            Image icon = new Image(getClass().getResourceAsStream("images/invoice.png"));
            returnToMain.getIcons().add(icon);
            returnToMain.initStyle(StageStyle.UNDECORATED);
            returnToMain.setMaximized(true);
            returnToMain.show();
        }
    }

    @FXML
    void removeProductReceiveNoteList(ActionEvent event) {
        Product selectedproduct = receiveNoteProductTable.getSelectionModel().getSelectedItem();
        try {
            for (int i = 0; i < SelectProductController.addedProducts.size(); i++) {
                if (SelectProductController.addedProducts.get(i).getId().equals(selectedproduct.getId())) {
                    SelectProductController.addedProducts.remove(i);
                }
            }
            receiveNoteProductList.clear();
            receiveNoteProductList.addAll(SelectProductController.addedProducts);
            receiveNoteProductTable.setItems(receiveNoteProductList);
            receiveNoteProductTable.refresh();

            sumNetPriceField.setText(String.valueOf(calculator.setSumNetPrice(receiveNoteProductList)));
            sumGrossPriceField.setText(String.valueOf(calculator.setSumGrossPrice(receiveNoteProductList)));
        } catch (Exception e) {
            alertController.noProductSelectedAlert();
        }
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
        receiveNoteProductNetPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("purchaseNetPrice"));
        receiveNoteProductGrossPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("purchaseGrossPrice"));
        receiveNoteProductQuantityColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("Stock"));
        receiveNoteProductNumberColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("ProductNr"));
        receiveNoteBoughtQuantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productQuantity"));
        receiveNoteBoughtQuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        receiveNoteProductList.addAll(SelectProductController.addedProducts);
        receiveNoteProductTable.setItems(receiveNoteProductList);

        sumNetPriceField.setText(String.valueOf(calculator.setPurchaseNetPrice(receiveNoteProductList)));
        sumGrossPriceField.setText(String.valueOf(calculator.setPurchaseGrossSum(receiveNoteProductList)));

        receiveNoteProductTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void updateQuantity(TableColumn.CellEditEvent editcell) {
        Product selectedproduct = receiveNoteProductTable.getSelectionModel().getSelectedItem();
        selectedproduct.setProductQuantity(editcell.getNewValue().hashCode());
        sumNetPriceField.setText(String.valueOf(calculator.setPurchaseNetPrice(receiveNoteProductList)));
        sumGrossPriceField.setText(String.valueOf(calculator.setPurchaseGrossSum(receiveNoteProductList)));
    }

}
