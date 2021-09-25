package invoiceR;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PreviewInvoiceController implements Initializable {

    Connect connect = new Connect();

    String buyerInvoiceName;
    String buyerAddress;
    String buyerPhone;
    String buyerBankNumber;
    String buyerEmail;
    String buyerVAT;

    @FXML
    private TextField sellerInvoiceNameField;

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
    private ComboBox<?> selectPaymentMethod;

    @FXML
    private DatePicker currentDate;

    @FXML
    private DatePicker paymentDate;

    @FXML
    private DatePicker fulfilmentDate;

    @FXML
    private TableView<?> invoiceProductTable;

    @FXML
    private TableColumn<?, ?> invoiceProductNameColumn;

    @FXML
    private TableColumn<?, ?> invoiceProductNetPriceColumn;

    @FXML
    private TableColumn<?, ?> invoiceProductGrossPriceColumn;

    @FXML
    private TableColumn<?, ?> invoiceDiscountNetPriceColumn;

    @FXML
    private TableColumn<?, ?> invoiceDiscountGrossPriceColumn;

    @FXML
    private TableColumn<?, ?> invoiceProductQuantityColumn;

    @FXML
    private TableColumn<?, ?> invoiceProductNumberColumn;

    @FXML
    private TableColumn<?, ?> boughtQuantityColumn;

    @FXML
    private TextField sumGrossPriceField;

    @FXML
    private TextField sumNetPriceField;

    @FXML
    private Button exitInvoicePreviewButton;

    @FXML
    private ImageView minimizeSceneButton;

    @FXML
    private ImageView maximizeSceneButton;

    @FXML
    private ImageView closeAndReturnButton;

    @FXML
    void changePaymentMethod(ActionEvent event) {

    }

    @FXML
    void exitInvoicePreview(ActionEvent event) {

    }

    @FXML
    void invoiceCancel(MouseEvent event) {

    }

    @FXML
    void maximizeScene(MouseEvent event) {

    }

    @FXML
    void minimizeScene(MouseEvent event) {

    }

    @FXML
    void updateQuantity(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect.getInvoiceBuyerData(MainController.customerId);

    }
}


