package invoiceR;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.util.UUID;


public class InvoiceController implements Initializable {

    Calculator calculator = new Calculator();
    AlertController alertController = new AlertController();
    Connect connect = new Connect();
    Seller seller = new Seller();

    ObservableList<String> paymentMethodList = FXCollections.observableArrayList("Készpénz", "Átutalás - 8 nap", "Átutalás - 15 nap", "Átutalás - 30 nap", "Utánvét", "Bankkártya");
    ObservableList<Product> invoiceProductList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> selectPaymentMethod;

    @FXML
    public TextField sellerInvoiceNameField;

    @FXML
    private TextField sellerInvoiceVATField;

    @FXML
    private TextField sellerInvoiceAddressField;

    @FXML
    private TextField sellerInvoiceBankNumberField;

    @FXML
    private TextField sellerPhoneField;

    @FXML
    private TextField sellerEmailField;

    @FXML
    private TextField buyerInvoiceNameField;

    @FXML
    private TextField buyerInvoiceVATField;

    @FXML
    private TextField buyerInvoiceAddressField;

    @FXML
    private TextField buyerInvoiceBankNumberField;

    @FXML
    private TextField buyerPhoneField;

    @FXML
    private TextField buyerEmailField;

    @FXML
    private Button selectCustomerButton;

    @FXML
    private TableView<Product> invoiceProductTable;

    @FXML
    private TableColumn<Product, String> invoiceProductNameColumn;

    @FXML
    private TableColumn<Product, String> invoiceProductNetPriceColumn;

    @FXML
    private TableColumn<Product, String> invoiceProductGrossPriceColumn;

    @FXML
    private TableColumn<Product, String> invoiceDiscountNetPriceColumn;

    @FXML
    private TableColumn<Product, String> invoiceDiscountGrossPriceColumn;

    @FXML
    private TableColumn<Product, Integer> invoiceProductQuantityColumn;

    @FXML
    private TableColumn<Product, String> invoiceProductNumberColumn;

    @FXML
    private TableColumn<Product, Integer> boughtQuantityColumn;

    @FXML
    private DatePicker currentDate;

    @FXML
    private DatePicker paymentDate;

    @FXML
    private DatePicker fulfilmentDate;

    @FXML
    private TextField sumGrossPriceField;

    @FXML
    private TextField sumNetPriceField;

    @FXML
    private Button addProductToInvoiceButton;

    @FXML
    private Button doneInvoiceButton;

    @FXML
    private Button cancelInvoiceButton;


    @FXML
    private Button removeProductFromInvoiceListButton;

    @FXML
    void removeProductFromInvoiceList(ActionEvent event) {
        Product selectedproduct = invoiceProductTable.getSelectionModel().getSelectedItem();
        try {
            for (int i = 0; i < SelectProductController.addedProducts.size(); i++) {
                if (SelectProductController.addedProducts.get(i).Name.equals(selectedproduct.Name)) {
                    SelectProductController.addedProducts.remove(i);
                }
            }
            invoiceProductList.clear();
            invoiceProductList.addAll(SelectProductController.addedProducts);
            invoiceProductTable.setItems(invoiceProductList);
            invoiceProductTable.refresh();

            sumNetPriceField.setText(String.valueOf(calculator.setSumNetPrice(invoiceProductList)));
            sumGrossPriceField.setText(String.valueOf(calculator.setSumGrossPrice(invoiceProductList)));
        } catch (Exception e) {
            alertController.noProductSelectedAlert();
        }
    }

    @FXML
    void closeInvoice(ActionEvent event) {
        UUID uuid = UUID.randomUUID();
        String invoiceInvoiceId = String.valueOf(uuid);
        String invoiceBuyerId = SelectBuyerController.buyerId;
        String invoiceCustomerName = SelectBuyerController.customerName;
        String invoiceCustomerFullAddress = SelectBuyerController.customerFullAddress;
        String invoiceSumNetPriceString = sumNetPriceField.getText();
        String invoiceGrossNetPriceString = sumGrossPriceField.getText();
        String invoiceCurrentDateString = currentDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        connect.addNewInvoice(invoiceInvoiceId, invoiceCustomerName, invoiceCustomerFullAddress,
                invoiceSumNetPriceString, invoiceGrossNetPriceString, invoiceCurrentDateString, invoiceBuyerId);

        connect.reduceStockQuantity(invoiceProductList);

        Stage stage = (Stage) doneInvoiceButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void cancelInvoice(ActionEvent event) {
        SelectProductController.addedProducts.clear();
        SelectBuyerController.customerName = "";
        SelectBuyerController.customerFullAddress = "";
        SelectBuyerController.customerVAT = "";
        SelectBuyerController.customerPhone = "";
        SelectBuyerController.customerEmail = "";
        SelectBuyerController.customerBankNumber = "";

        Stage stage = (Stage) cancelInvoiceButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void changePaymentMethod(ActionEvent event) {
        String paymentString = (String) selectPaymentMethod.getSelectionModel().getSelectedItem();
        paymentDate.setValue(calculator.calculateDays(calculator.calculateDaysToAdd(paymentString)));
    }

    @FXML
    void addProductToInvoice(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/productSelectStage.fxml"));
        Stage addProductToInvoice = new Stage();
        addProductToInvoice.setScene(new Scene(root));
        addProductToInvoice.initStyle(StageStyle.UTILITY);
        addProductToInvoice.show();

        Stage stage = (Stage) sumGrossPriceField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void selectCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/buyerSelectStage.fxml"));
        Stage buyerSelectStage = new Stage();
        buyerSelectStage.setScene(new Scene(root));
        buyerSelectStage.show();

        Stage stage = (Stage) sumGrossPriceField.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sellerInvoiceNameField.setText(Seller.defaultSeller.sellerName);
        sellerInvoiceAddressField.setText(seller.setSellerAddress(
                Seller.defaultSeller.getSellerPostalCode(), Seller.defaultSeller.getSellerCity(),
                Seller.defaultSeller.getSellerAddress(), Seller.defaultSeller.getSellerAddressType(),
                Seller.defaultSeller.getSellerHouseNumber(), Seller.defaultSeller.getSellerStairway(),
                Seller.defaultSeller.getSellerFloor()
        ));
        sellerEmailField.setText(Seller.defaultSeller.sellerEmail);
        sellerInvoiceVATField.setText(Seller.defaultSeller.sellerVAT);
        sellerPhoneField.setText(Seller.defaultSeller.sellerPhone);
        sellerInvoiceBankNumberField.setText(Seller.defaultSeller.sellerBankAccount);
        selectPaymentMethod.getItems().addAll(paymentMethodList);
        currentDate.setValue(LocalDate.now());
        paymentDate.setValue(calculator.calculateDays(0));
        fulfilmentDate.setValue(LocalDate.now());

        invoiceProductTable.setEditable(true);
        invoiceProductNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("Name"));
        invoiceProductNetPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productNetPrice"));
        invoiceProductGrossPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productGrossPrice"));
        invoiceDiscountNetPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("discountNetPrice"));
        invoiceDiscountGrossPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("discountGrossPrice"));
        invoiceProductQuantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("Stock"));
        invoiceProductNumberColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("ProductNr"));
        boughtQuantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productQuantity"));
        boughtQuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        invoiceProductList.addAll(SelectProductController.addedProducts);
        invoiceProductTable.setItems(invoiceProductList);

        buyerInvoiceNameField.setText(SelectBuyerController.customerName);
        buyerInvoiceAddressField.setText(SelectBuyerController.customerFullAddress);
        buyerInvoiceVATField.setText(SelectBuyerController.customerVAT);
        buyerInvoiceBankNumberField.setText(SelectBuyerController.customerBankNumber);
        buyerPhoneField.setText(SelectBuyerController.customerPhone);
        buyerEmailField.setText(SelectBuyerController.customerEmail);

        sumNetPriceField.setText(String.valueOf(calculator.setSumNetPrice(invoiceProductList)));
        sumGrossPriceField.setText(String.valueOf(calculator.setSumGrossPrice(invoiceProductList)));
    }

    public void updateQuantity(TableColumn.CellEditEvent editcell) {
        Product selectedproduct = invoiceProductTable.getSelectionModel().getSelectedItem();
        selectedproduct.setProductQuantity(editcell.getNewValue().hashCode());
        int stock = selectedproduct.getStock();
        int boughtQuantity = selectedproduct.productQuantity;
        if (!selectedproduct.isService) {
            if (boughtQuantity > stock) {
                alertController.stockAlert();
            }
        }
        sumNetPriceField.setText(String.valueOf(calculator.setSumNetPrice(invoiceProductList)));
        sumGrossPriceField.setText(String.valueOf(calculator.setSumGrossPrice(invoiceProductList)));
    }
}