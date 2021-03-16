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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;

import java.text.SimpleDateFormat;

public class InvoiceController implements Initializable {

    public static String buyerID;
    ObservableList<String> paymentMethodList = FXCollections.observableArrayList("Készpénz", "Átutalás - 8 nap", "Átutalás - 15 nap", "Átutalás - 30 nap", "Utánvét", "Bankkártya");
    ObservableList<Product> invoiceProductList = FXCollections.observableArrayList();

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
    private ComboBox selectPaymentMethod;

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
    void changePaymentMethod(ActionEvent event) {
        String paymentString = (String) selectPaymentMethod.getSelectionModel().getSelectedItem();
        int daystoadd = 0;
        switch (paymentString) {
            case "Átutalás - 8 nap":
                daystoadd = 8;
                break;
            case "Átutalás - 15 nap":
                daystoadd = 15;
                break;
            case "Átutalás - 30 nap":
                daystoadd = 30;
                break;
            case "Utánvét":
                daystoadd = 8;
                break;
            default:
                daystoadd = 0;
        }
        paymentDate.setValue(calculateDays(daystoadd));
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
        sellerInvoiceAddressField.setText(setSellerAddress(Seller.defaultSeller));
        sellerEmailField.setText(Seller.defaultSeller.sellerEmail);
        sellerInvoiceVATField.setText(Seller.defaultSeller.sellerVAT);
        sellerPhoneField.setText(Seller.defaultSeller.sellerPhone);
        sellerInvoiceBankNumberField.setText(Seller.defaultSeller.sellerBankAccount);
        selectPaymentMethod.setItems(paymentMethodList);
        currentDate.setValue(LocalDate.now());
        paymentDate.setValue(calculateDays(0));
        fulfilmentDate.setValue(LocalDate.now());

        invoiceProductNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("Name"));
        invoiceProductNetPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productNetPrice"));
        invoiceProductGrossPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productGrossPrice"));
        invoiceDiscountNetPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("discountNetPrice"));
        invoiceDiscountGrossPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("discountGrossPrice"));
        invoiceProductQuantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("Stock"));
        invoiceProductNumberColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("ProductNr"));
        invoiceProductList.addAll(SelectProductController.addedProducts);
        invoiceProductTable.setItems(invoiceProductList);
    }


    private String setSellerAddress(Seller seller) {
        return seller.sellerPostalCode + " " + seller.sellerCity + ", " + seller.sellerAddress + " " + seller.sellerAddressType + " " + seller.sellerHouseNumber + " " + seller.sellerStairway + " " + seller.sellerFloor;
    }

    public void setBuyerAddress(Customer customer) {
        System.out.println("bejött ide");
        String addressToShow = customer.billingPostalCode + " " + customer.billingCity + ", " + customer.billingAddress + " " + customer.billingAddressType + " " + customer.billingHouseNumber + " " + customer.billingStairway + " " + customer.billingFloor;
        buyerInvoiceNameField.setText(SelectBuyerController.customerToAdd.get(0).billingName);
    }

    private LocalDate calculateDays(int daysToAdd) {
        String today = String.valueOf(LocalDate.now());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, daysToAdd);
        LocalDate localDate = LocalDateTime.ofInstant(c.toInstant(), c.getTimeZone().toZoneId()).toLocalDate();
        return localDate;
    }
}