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
    private ImageView maximizeSceneButton;

    @FXML
    private ImageView closeAndReturnButton;

    @FXML
    private ImageView minimizeSceneButton;

    @FXML
    void invoiceCancel(MouseEvent event) throws IOException {
        emptyScene();
    }

    @FXML
    void removeProductFromInvoiceList(ActionEvent event) {
        Product selectedproduct = invoiceProductTable.getSelectionModel().getSelectedItem();
        try {
            for (int i = 0; i < SelectProductController.addedProducts.size(); i++) {
                if (SelectProductController.addedProducts.get(i).getId().equals(selectedproduct.getId())) {
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
    void closeInvoice(ActionEvent event) throws IOException {
        if (invoiceProductList.isEmpty() || SelectBuyerController.customerName == null) {
            alertController.emptyFieldAlert();
        } else {
            UUID uuid = UUID.randomUUID();
            connect.addNewInvoice(String.valueOf(uuid), SelectBuyerController.customerName, SelectBuyerController.customerFullAddress,
                    sumNetPriceField.getText(), sumGrossPriceField.getText(),
                    currentDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    SelectBuyerController.buyerId, selectPaymentMethod.getValue(),
                    paymentDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    fulfilmentDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            connect.reduceStockQuantity(invoiceProductList);

            for (int i = 0; i < invoiceProductList.size(); i++) {
                UUID productuuid = UUID.randomUUID();
                connect.addNewInvoiceProduct(String.valueOf(productuuid), String.valueOf(uuid),
                        SelectProductController.addedProducts.get(i).getId(), SelectProductController.addedProducts.get(i).getName(),
                        SelectProductController.addedProducts.get(i).getProductNetPrice(), SelectProductController.addedProducts.get(i).getProductGrossPrice(),
                        SelectProductController.addedProducts.get(i).getDiscountNetPrice(), SelectProductController.addedProducts.get(i).getDiscountGrossPrice(),
                        SelectProductController.addedProducts.get(i).getProductNr(), invoiceProductList.get(i).getProductQuantity());
                System.out.println();
            }

            Stage stage = (Stage) doneInvoiceButton.getScene().getWindow();
            stage.close();
            alertController.invoiceSuccessAlert();

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
    void cancelInvoice(ActionEvent event) throws IOException {
        emptyScene();
    }

    @FXML
    void changePaymentMethod(ActionEvent event) {
        String paymentString = (String) selectPaymentMethod.getSelectionModel().getSelectedItem();
        Calculator.payment = calculator.calculateComboboxIndex(paymentString);
        paymentDate.setValue(calculator.calculateDays(calculator.calculateDaysToAdd(paymentString)));
    }

    @FXML
    void addProductToInvoice(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/productSelectStage.fxml"));
        Stage addProductToInvoice = new Stage();
        addProductToInvoice.setScene(new Scene(root));
        Image icon = new Image(getClass().getResourceAsStream("images/invoice.png"));
        addProductToInvoice.getIcons().add(icon);
        addProductToInvoice.initStyle(StageStyle.UNDECORATED);
        addProductToInvoice.show();

        Stage stage = (Stage) sumGrossPriceField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void selectCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/buyerSelectStage.fxml"));
        Stage buyerSelectStage = new Stage();
        buyerSelectStage.setScene(new Scene(root));
        Image icon = new Image(getClass().getResourceAsStream("images/invoice.png"));
        buyerSelectStage.getIcons().add(icon);
        buyerSelectStage.initStyle(StageStyle.UNDECORATED);
        buyerSelectStage.show();

        Stage stage = (Stage) sumGrossPriceField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void maximizeScene(MouseEvent event) {
        Stage stage = (Stage) selectCustomerButton.getScene().getWindow();
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
        selectPaymentMethod.getSelectionModel().select(Calculator.payment);
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

        invoiceProductTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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

    private void emptyScene() throws IOException {
        SelectProductController.addedProducts.clear();
        SelectBuyerController.customerName = "";
        SelectBuyerController.customerFullAddress = "";
        SelectBuyerController.customerVAT = "";
        SelectBuyerController.customerPhone = "";
        SelectBuyerController.customerEmail = "";
        SelectBuyerController.customerBankNumber = "";

        Stage stage = (Stage) cancelInvoiceButton.getScene().getWindow();
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