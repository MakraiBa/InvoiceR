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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PreviewInvoiceController implements Initializable {

    Connect connect = new Connect();
    Seller seller = new Seller();

    ObservableList<InvoiceProduct> invoiceProducts = FXCollections.observableArrayList();

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
    private TextField fulfilmentDate;

    @FXML
    private TableView<InvoiceProduct> invoiceProductTable;

    @FXML
    private TableColumn<InvoiceProduct, String> invoiceProductNameColumn;

    @FXML
    private TableColumn<InvoiceProduct, String> invoiceProductNetPriceColumn;

    @FXML
    private TableColumn<InvoiceProduct, String> invoiceProductGrossPriceColumn;

    @FXML
    private TableColumn<InvoiceProduct, String> invoiceDiscountNetPriceColumn;

    @FXML
    private TableColumn<InvoiceProduct, String> invoiceDiscountGrossPriceColumn;

    @FXML
    private TableColumn<InvoiceProduct, String> invoiceProductNumberColumn;

    @FXML
    private TableColumn<InvoiceProduct, Integer> boughtQuantityColumn;

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
    void invoiceCancel(MouseEvent event) throws IOException {
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
    void exitInvoicePreview(ActionEvent event) {
        Stage stage = (Stage) exitInvoicePreviewButton.getScene().getWindow();
        stage.close();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("scenes/mainStage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage returnToMain = new Stage();
        returnToMain.setScene(new Scene(root));
        Image icon = new Image(getClass().getResourceAsStream("images/invoice.png"));
        returnToMain.getIcons().add(icon);
        returnToMain.initStyle(StageStyle.UNDECORATED);
        returnToMain.setMaximized(true);
        returnToMain.show();
    }

    @FXML
    void maximizeScene(MouseEvent event) {
        Stage stage = (Stage) maximizeSceneButton.getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }

    @FXML
    void minimizeScene(MouseEvent event) {
        Stage stage = (Stage) minimizeSceneButton.getScene().getWindow();
        stage.setIconified(true);
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
                selectedPaymentMethod.setText(Connect.invoiceList.get(i).paymentMethod);
                invoiceDate.setText(Connect.invoiceList.get(i).currentDate);
                paymentDate.setText(Connect.invoiceList.get(i).paymentDate);
                fulfilmentDate.setText(Connect.invoiceList.get(i).fulfilmentDate);
                sumNetPriceField.setText(String.valueOf(Connect.invoiceList.get(i).sumNetPrice));
                sumGrossPriceField.setText(String.valueOf(Connect.invoiceList.get(i).sumGrossPrice));
            }
        }

        invoiceProductNameColumn.setCellValueFactory(new PropertyValueFactory<InvoiceProduct, String>("productName"));
        invoiceProductNetPriceColumn.setCellValueFactory(new PropertyValueFactory<InvoiceProduct, String>("productNetPrice"));
        invoiceProductGrossPriceColumn.setCellValueFactory(new PropertyValueFactory<InvoiceProduct, String>("productGrossPrice"));
        invoiceDiscountNetPriceColumn.setCellValueFactory(new PropertyValueFactory<InvoiceProduct, String>("discountNetPrice"));
        invoiceDiscountGrossPriceColumn.setCellValueFactory(new PropertyValueFactory<InvoiceProduct, String>("discountGrossPrice"));
        invoiceProductNumberColumn.setCellValueFactory(new PropertyValueFactory<InvoiceProduct, String>("productNumber"));
        boughtQuantityColumn.setCellValueFactory(new PropertyValueFactory<InvoiceProduct, Integer>("productQuantity"));
        getProduct();
        invoiceProductTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        invoiceProductTable.setItems(invoiceProducts);
    }

    private String setBuyerAddress(String postalcode, String city, String address,
                                   String addresstype, String housenumber,
                                   String stairway, String floor) {
        return postalcode + " " + city + ", " + address + " " + addresstype + " " + housenumber + " " + stairway + " " + floor;
    }

    public ObservableList<InvoiceProduct> getProduct() {
        invoiceProducts.clear();
        connect.getInvoiceProducts();
        for (int i = 0; i < Connect.previewInvoiceProductList.size(); i++) {
            if (Connect.previewInvoiceProductList.get(i).invoiceId.equals(MainController.invoiceId)) {
                invoiceProducts.add(new InvoiceProduct(
                        Connect.previewInvoiceProductList.get(i).getId(),
                        Connect.previewInvoiceProductList.get(i).getInvoiceId(),
                        Connect.previewInvoiceProductList.get(i).getProductId(),
                        Connect.previewInvoiceProductList.get(i).getProductName(),
                        Connect.previewInvoiceProductList.get(i).getProductNetPrice(),
                        Connect.previewInvoiceProductList.get(i).getProductGrossPrice(),
                        Connect.previewInvoiceProductList.get(i).getDiscountNetPrice(),
                        Connect.previewInvoiceProductList.get(i).getDiscountGrossPrice(),
                        Connect.previewInvoiceProductList.get(i).getProductNumber(),
                        Connect.previewInvoiceProductList.get(i).getProductQuantity()
                ));
            }
        }
        return invoiceProducts;
    }
}


