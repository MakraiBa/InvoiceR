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
    Seller seller = new Seller();

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
    private TextField selectedPaymentMethod;

    @FXML
    private TextField invoiceDate;

    @FXML
    private TextField paymentDate;

    @FXML
    private TextField fullfilmentDate;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect.getInvoiceBuyerData(MainController.customerId);
        connect.getInvoices();

        String fullAddress = setBuyerAddress(Connect.previewInvoiceBuyerList.get(0).billingPostalCode, Connect.previewInvoiceBuyerList.get(0).billingCity,
                Connect.previewInvoiceBuyerList.get(0).billingAddress, Connect.previewInvoiceBuyerList.get(0).billingAddressType,
                Connect.previewInvoiceBuyerList.get(0).billingHouseNumber, Connect.previewInvoiceBuyerList.get(0).billingStairway,
                Connect.previewInvoiceBuyerList.get(0).billingFloor);

        buyerInvoiceNameField.setText(Connect.previewInvoiceBuyerList.get(0).getBillingName());
        buyerInvoiceAddressField.setText(fullAddress);
        buyerPhoneField.setText(Connect.previewInvoiceBuyerList.get(0).getPhone());
        buyerInvoiceBankNumberField.setText(Connect.previewInvoiceBuyerList.get(0).getBankAccount());
        buyerEmailField.setText(Connect.previewInvoiceBuyerList.get(0).getEmail());
        buyerInvoiceVATField.setText(Connect.previewInvoiceBuyerList.get(0).getCustomerVAT());

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

        for (int i = 0; i < Connect.invoiceList.size(); i++) {
            if (Connect.invoiceList.get(i).getInvoiceId().equals(MainController.invoiceId)) {

            }
        }
    }

    private String setBuyerAddress(String postalcode, String city, String address,
                                   String addresstype, String housenumber,
                                   String stairway, String floor) {
        return postalcode + " " + city + ", " + address + " " + addresstype + " " + housenumber + " " + stairway + " " + floor;
    }
}


