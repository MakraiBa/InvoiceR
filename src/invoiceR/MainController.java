package invoiceR;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class MainController implements Initializable {

    Connect connect = new Connect();
    AlertController alertController = new AlertController();

    public MainController() {
    }

    public static String productID;
    public static String productName;
    public static String productNetPrice;
    public static String productGrossPrice;
    public static String purchaseNetPrice;
    public static String purchaseGrossPrice;
    public static String discountNetPrice;
    public static String dicsountGrossPrice;
    public static String productCode;
    public static String Teszor;
    public static String productComment;
    public static boolean isDiscounted;
    public static boolean isService;

    public static String customerId;
    public static String customerBillingName;
    public static String customerBillingCity;
    public static String customerBillingPostalCode;
    public static String customerBillingAddress;
    public static String customerBillingAddressType;
    public static String customerBillingHouseNumber;
    public static String customerBillingStairway;
    public static String customerBillingFloor;
    public static String customerDeliveryCity;
    public static String customerDeliveryPostalCode;
    public static String customerDeliveryAddress;
    public static String customerDeliveryAddressType;
    public static String customerDeliveryHouseNumber;
    public static String customerDeliveryStairway;
    public static String customerDeliveryFloor;
    public static String customerVAT;
    public static String customerPhone;
    public static String customerEmail;
    public static String customerWebPage;
    public static String customerBankAccount;
    public static String customerComment;
    public static boolean hasSameAddress;

    ObservableList<Product> product = FXCollections.observableArrayList();
    ObservableList<Customer> customer = FXCollections.observableArrayList();
    ObservableList<Invoice> invoice = FXCollections.observableArrayList();

    @FXML
    private Button newInvoiceButton;

    @FXML
    private Button newReceiveNoteButton;


    @FXML
    private Button newProductButton;

    @FXML
    private Button newCustomerButton;

    @FXML
    public TabPane mainTabPane = new TabPane();

    @FXML
    private Tab invoiceTab;

    @FXML
    private Tab customerTab;

    @FXML
    private Tab productTab;

    @FXML
    private Tab receiveNoteTab;

    @FXML
    private TextField productSearchField;

    @FXML
    private TextField customerSearchField;

    @FXML
    private TextField invoiceSearchField;

    @FXML
    private TextField receiveNoteSearchField;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableView<Invoice> invoiceTable;

    @FXML
    private TableView<?> receiveNoteTable;

    @FXML
    private Button editCustomerButton;

    @FXML
    private Button mainDeleteCustomerButton;

    @FXML
    private Button editProductButton;

    @FXML
    private Button mainDeleteProductButton;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, String> productNetPriceColumn;

    @FXML
    private TableColumn<Product, String> productGrossPriceColumn;

    @FXML
    private TableColumn<Product, String> discountNetPriceColumn;

    @FXML
    private TableColumn<Product, String> discountGrossPriceColumn;

    @FXML
    private TableColumn<Product, Integer> stockQuantityColumn;

    @FXML
    private TableColumn<Customer, String> customerNameColumn;

    @FXML
    private TableColumn<Customer, String> customerAddressColumn;

    @FXML
    private TableColumn<Customer, String> customerPhoneColumn;

    @FXML
    private TableColumn<Customer, String> customerEmailColumn;

    @FXML
    private TableColumn<Customer, String> postalCodeColumn;

    @FXML
    private TableColumn<Customer, String> houseNumberColumn;

    @FXML
    private TableColumn<Customer, String> cityColumn;

    @FXML
    private TableColumn<Invoice, String> invoiceCustomerNameColumn;

    @FXML
    private TableColumn<Invoice, String> invoiceCustomerAddressColumn;

    @FXML
    private TableColumn<Invoice, String> invoiceDateColumn;

    @FXML
    private TableColumn<Invoice, String> invoiceSumNetPriceColumn;

    @FXML
    private TableColumn<Invoice, String> invoiceSumGrossPriceColumn;

    @FXML
    private TableColumn<?, ?> receiveNoteCustomerNameColumn;

    @FXML
    private TableColumn<?, ?> receiveNoteCustomerAddressColumn;

    @FXML
    private TableColumn<?, ?> receiveNoteDateColumn;

    @FXML
    private TableColumn<?, ?> receiveNoteSumNetPriceColumn;

    @FXML
    private TableColumn<?, ?> receiveNoteSumGrossPriceColumn;

    @FXML
    void addNewReceiveNote(ActionEvent event) {

    }

    @FXML
    void addNewCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/customerStage.fxml"));
        Stage newCustomerStage = new Stage();
        newCustomerStage.setScene(new Scene(root));
        newCustomerStage.initStyle(StageStyle.UTILITY);
        newCustomerStage.show();

        Stage stage = (Stage) newCustomerButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addNewInvoice(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/invoiceStage.fxml"));
        Stage newInvoiceStage = new Stage();
        newInvoiceStage.setScene(new Scene(root));
        newInvoiceStage.initStyle(StageStyle.UNDECORATED);
        newInvoiceStage.show();
    }

    @FXML
    void addNewProduct(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/productStage.fxml"));
        Stage newProductStage = new Stage();
        newProductStage.setScene(new Scene(root));
        newProductStage.initStyle(StageStyle.UTILITY);
        newProductStage.show();

        Stage stage = (Stage) newProductButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteCustomer(ActionEvent event) {
        Customer selectedcustomer = customerTable.getSelectionModel().getSelectedItem();
        try {
            alertController.deleteCustomerConfirmAlert(selectedcustomer.Id, selectedcustomer.billingName);
            getCustomer();
            FilteredList<Customer> filteredCustomerRefresh = new FilteredList<>(FXCollections.observableList(customer));
            customerSearchField.textProperty().addListener((observable, oldValue, newValue) ->
                    filteredCustomerRefresh.setPredicate(createPredicateCustomer(newValue))
            );
            customerTable.setItems(filteredCustomerRefresh);
            customerTable.refresh();

        } catch (Exception e) {
            alertController.noCustomerSelectedAlert();
        }
    }

    @FXML
    void deleteProduct(ActionEvent event) {
        Product selectedproduct = productTable.getSelectionModel().getSelectedItem();
        try {
            alertController.deleteProductConfirmAlert(selectedproduct.Id, selectedproduct.Name);
            getProduct();
            FilteredList<Product> filteredProductRefresh = new FilteredList<>(FXCollections.observableList(product));
            productSearchField.textProperty().addListener((observable, oldValue, newValue) ->
                    filteredProductRefresh.setPredicate(createPredicateProduct(newValue))
            );
            productTable.setItems(product);
            productTable.refresh();
        } catch (Exception e) {
            alertController.noProductSelectedAlert();
        }
    }

    @FXML
    void mainEditProduct(ActionEvent event) {
        try {
            Product selectedproduct = productTable.getSelectionModel().getSelectedItem();
            productID = selectedproduct.Id;
            productName = selectedproduct.Name;
            productNetPrice = selectedproduct.productNetPrice;
            productGrossPrice = selectedproduct.productGrossPrice;
            purchaseNetPrice = selectedproduct.purchaseNetPrice;
            purchaseGrossPrice = selectedproduct.purchaseGrossPrice;
            discountNetPrice = selectedproduct.discountNetPrice;
            dicsountGrossPrice = selectedproduct.discountGrossPrice;
            productCode = selectedproduct.ProductNr;
            Teszor = selectedproduct.Teszor;
            productComment = selectedproduct.Comment;
            isDiscounted = selectedproduct.isDiscounted;
            isService = selectedproduct.isService;

            Parent root = FXMLLoader.load(getClass().getResource("scenes/editProductStage.fxml"));
            Stage editProductStage = new Stage();
            editProductStage.setScene(new Scene(root));
            editProductStage.initStyle(StageStyle.UTILITY);
            editProductStage.show();

            Stage stage = (Stage) productTable.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            alertController.noProductSelectedAlert();
        }
    }

    @FXML
    void mainEditCustomer(ActionEvent event) {
        try {
            Customer selectedcustomer = customerTable.getSelectionModel().getSelectedItem();
            customerId = selectedcustomer.Id;
            customerBillingName = selectedcustomer.billingName;
            customerBillingCity = selectedcustomer.billingCity;
            customerBillingPostalCode = selectedcustomer.billingPostalCode;
            customerBillingAddress = selectedcustomer.billingAddress;
            customerBillingAddressType = selectedcustomer.billingAddressType;
            customerBillingHouseNumber = selectedcustomer.billingHouseNumber;
            customerBillingStairway = selectedcustomer.billingStairway;
            customerBillingFloor = selectedcustomer.billingFloor;
            customerDeliveryCity = selectedcustomer.deliveryCity;
            customerDeliveryPostalCode = selectedcustomer.deliveryPostalCode;
            customerDeliveryAddress = selectedcustomer.deliveryAddress;
            customerDeliveryAddressType = selectedcustomer.deliveryAddressType;
            customerDeliveryHouseNumber = selectedcustomer.deliveryHouseNumber;
            customerDeliveryStairway = selectedcustomer.deliveryStairway;
            customerDeliveryFloor = selectedcustomer.deliveryFloor;
            customerVAT = selectedcustomer.customerVAT;
            customerPhone = selectedcustomer.phone;
            customerEmail = selectedcustomer.email;
            customerWebPage = selectedcustomer.webPage;
            customerBankAccount = selectedcustomer.bankAccount;
            customerComment = selectedcustomer.customerComment;
            hasSameAddress = selectedcustomer.hasSameAddress;
            Parent root = FXMLLoader.load(getClass().getResource("scenes/editCustomerStage.fxml"));
            Stage editCustomerStage = new Stage();
            editCustomerStage.setScene(new Scene(root));
            editCustomerStage.initStyle(StageStyle.UTILITY);
            editCustomerStage.show();

            Stage stage = (Stage) customerTable.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            alertController.noCustomerSelectedAlert();
        }
    }

    @FXML
    void openProduct(MouseEvent event) throws IOException {
        Product selectedproduct = productTable.getSelectionModel().getSelectedItem();
        productID = selectedproduct.Id;
        productName = selectedproduct.Name;
        productNetPrice = selectedproduct.productNetPrice;
        productGrossPrice = selectedproduct.productGrossPrice;
        purchaseNetPrice = selectedproduct.purchaseNetPrice;
        purchaseGrossPrice = selectedproduct.purchaseGrossPrice;
        discountNetPrice = selectedproduct.discountNetPrice;
        dicsountGrossPrice = selectedproduct.discountGrossPrice;
        productCode = selectedproduct.ProductNr;
        Teszor = selectedproduct.Teszor;
        productComment = selectedproduct.Comment;
        isDiscounted = selectedproduct.isDiscounted;
        isService = selectedproduct.isService;
        if (event.getClickCount() == 2) {
            Parent root = FXMLLoader.load(getClass().getResource("scenes/editProductStage.fxml"));
            Stage editProductStage = new Stage();
            editProductStage.setScene(new Scene(root));
            editProductStage.initStyle(StageStyle.UTILITY);
            editProductStage.show();

            Stage stage = (Stage) productTable.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void openCustomer(MouseEvent event) throws IOException {
        Customer selectedcustomer = customerTable.getSelectionModel().getSelectedItem();
        customerId = selectedcustomer.Id;
        customerBillingName = selectedcustomer.billingName;
        customerBillingCity = selectedcustomer.billingCity;
        customerBillingPostalCode = selectedcustomer.billingPostalCode;
        customerBillingAddress = selectedcustomer.billingAddress;
        customerBillingAddressType = selectedcustomer.billingAddressType;
        customerBillingHouseNumber = selectedcustomer.billingHouseNumber;
        customerBillingStairway = selectedcustomer.billingStairway;
        customerBillingFloor = selectedcustomer.billingFloor;
        customerDeliveryCity = selectedcustomer.deliveryCity;
        customerDeliveryPostalCode = selectedcustomer.deliveryPostalCode;
        customerDeliveryAddress = selectedcustomer.deliveryAddress;
        customerDeliveryAddressType = selectedcustomer.deliveryAddressType;
        customerDeliveryHouseNumber = selectedcustomer.deliveryHouseNumber;
        customerDeliveryStairway = selectedcustomer.deliveryStairway;
        customerDeliveryFloor = selectedcustomer.deliveryFloor;
        customerVAT = selectedcustomer.customerVAT;
        customerPhone = selectedcustomer.phone;
        customerEmail = selectedcustomer.email;
        customerWebPage = selectedcustomer.webPage;
        customerBankAccount = selectedcustomer.bankAccount;
        customerComment = selectedcustomer.customerComment;
        hasSameAddress = selectedcustomer.hasSameAddress;
        if (event.getClickCount() == 2) {
            Parent root = FXMLLoader.load(getClass().getResource("scenes/editCustomerStage.fxml"));
            Stage editCustomerStage = new Stage();
            editCustomerStage.setScene(new Scene(root));
            editCustomerStage.initStyle(StageStyle.UTILITY);
            editCustomerStage.show();

            Stage stage = (Stage) customerTable.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("Name"));
        productNetPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productNetPrice"));
        productGrossPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productGrossPrice"));
        discountNetPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("discountNetPrice"));
        discountGrossPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("discountGrossPrice"));
        stockQuantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("Stock"));
        getProduct();

        customerNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("billingName"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("billingAddress"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        customerEmailColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("billingPostalCode"));
        houseNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("billingHouseNumber"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("billingCity"));
        getCustomer();

        invoiceCustomerNameColumn.setCellValueFactory(new PropertyValueFactory<Invoice, String>("invoiceCustomerName"));
        invoiceCustomerAddressColumn.setCellValueFactory(new PropertyValueFactory<Invoice, String>("customerFullAddress"));
        invoiceDateColumn.setCellValueFactory(new PropertyValueFactory<Invoice, String>("currentDate"));
        invoiceSumNetPriceColumn.setCellValueFactory(new PropertyValueFactory<Invoice, String>("sumNetPrice"));
        invoiceSumGrossPriceColumn.setCellValueFactory(new PropertyValueFactory<Invoice, String>("sumGrossPrice"));
        getInvoice();

        FilteredList<Product> filteredProduct = new FilteredList<>(FXCollections.observableList(product));
        productSearchField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredProduct.setPredicate(createPredicateProduct(newValue))
        );
        FilteredList<Customer> filteredCustomer = new FilteredList<>(FXCollections.observableList(customer));
        customerSearchField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredCustomer.setPredicate(createPredicateCustomer(newValue))
        );
        FilteredList<Invoice> filteredInvoice = new FilteredList<>(FXCollections.observableList(invoice));
        invoiceSearchField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredInvoice.setPredicate(createPredicateInvoice(newValue))
        );

        productTable.setItems(filteredProduct);
        customerTable.setItems(filteredCustomer);
        invoiceTable.setItems(filteredInvoice);
    }


    public ObservableList<Product> getProduct() {
        product.clear();
        connect.getProducts();
        for (int i = 0; i < Connect.productList.size(); i++) {
            product.add(new Product(
                    Connect.productList.get(i).Teszor,
                    Connect.productList.get(i).Stock,
                    Connect.productList.get(i).Id,
                    Connect.productList.get(i).isService,
                    Connect.productList.get(i).isDiscounted,
                    Connect.productList.get(i).Name,
                    Connect.productList.get(i).Comment,
                    Connect.productList.get(i).ProductNr,
                    Connect.productList.get(i).productNetPrice,
                    Connect.productList.get(i).productGrossPrice,
                    Connect.productList.get(i).purchaseNetPrice,
                    Connect.productList.get(i).purchaseGrossPrice,
                    Connect.productList.get(i).discountNetPrice,
                    Connect.productList.get(i).discountGrossPrice));
        }
        return product;
    }

    public ObservableList<Customer> getCustomer() {
        customer.clear();
        connect.getCustomers();
        for (int i = 0; i < Connect.customerList.size(); i++) {
            customer.add(new Customer(
                    Connect.customerList.get(i).Id,
                    Connect.customerList.get(i).billingName,
                    Connect.customerList.get(i).billingCity,
                    Connect.customerList.get(i).billingPostalCode,
                    Connect.customerList.get(i).billingAddress,
                    Connect.customerList.get(i).billingAddressType,
                    Connect.customerList.get(i).billingHouseNumber,
                    Connect.customerList.get(i).billingStairway,
                    Connect.customerList.get(i).billingFloor,
                    Connect.customerList.get(i).deliveryCity,
                    Connect.customerList.get(i).deliveryPostalCode,
                    Connect.customerList.get(i).deliveryAddress,
                    Connect.customerList.get(i).deliveryAddressType,
                    Connect.customerList.get(i).deliveryHouseNumber,
                    Connect.customerList.get(i).deliveryStairway,
                    Connect.customerList.get(i).deliveryFloor,
                    Connect.customerList.get(i).customerVAT,
                    Connect.customerList.get(i).phone,
                    Connect.customerList.get(i).email,
                    Connect.customerList.get(i).webPage,
                    Connect.customerList.get(i).bankAccount,
                    Connect.customerList.get(i).customerComment,
                    Connect.customerList.get(i).hasSameAddress));
        }
        return customer;
    }

    public ObservableList<Invoice> getInvoice() {
        invoice.clear();
        connect.getInvoices();
        for (int i = 0; i < Connect.invoiceList.size(); i++) {
            invoice.add(new Invoice(
                    Connect.invoiceList.get(i).invoiceId,
                    Connect.invoiceList.get(i).buyerId,
                    Connect.invoiceList.get(i).invoiceCustomerName,
                    Connect.invoiceList.get(i).customerFullAddress,
                    Connect.invoiceList.get(i).sumNetPrice,
                    Connect.invoiceList.get(i).sumGrossPrice,
                    Connect.invoiceList.get(i).currentDate
            ));
        }
        return invoice;
    }

    private Predicate<Product> createPredicateProduct(String searchText) {
        return product -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return productSearch(product, searchText);
        };
    }

    private Predicate<Customer> createPredicateCustomer(String searchText) {
        return customer -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return customerSearch(customer, searchText);
        };
    }

    private Predicate<Invoice> createPredicateInvoice(String searchText) {
        return invoice -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return invoiceSearch(invoice, searchText);
        };
    }

    private boolean productSearch(Product product, String searchText) {
        return
                product.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                        product.getProductNetPrice().toLowerCase().contains(searchText.toLowerCase()) ||
                        product.getProductNetPrice().toLowerCase().contains(searchText.toLowerCase()) ||
                        product.getProductNr().toLowerCase().contains(searchText.toLowerCase()) ||
                        product.getComment().toLowerCase().contains(searchText.toLowerCase());
    }

    private boolean customerSearch(Customer customer, String searchText) {
        return
                customer.getBillingName().toLowerCase().contains(searchText.toLowerCase()) ||
                        customer.getBillingCity().toLowerCase().contains(searchText.toLowerCase()) ||
                        customer.getBillingPostalCode().toLowerCase().contains(searchText.toLowerCase()) ||
                        customer.getBillingAddress().toLowerCase().contains(searchText.toLowerCase()) ||
                        customer.getPhone().toLowerCase().contains(searchText.toLowerCase()) ||
                        customer.getEmail().toLowerCase().contains(searchText.toLowerCase());
    }

    private boolean invoiceSearch(Invoice invoice, String searchText) {
        return
                invoice.getInvoiceCustomerName().toLowerCase().contains(searchText.toLowerCase()) ||
                        invoice.getCurrentDate().toLowerCase().contains(searchText.toLowerCase()) ||
                        invoice.getCustomerFullAddress().toLowerCase().contains(searchText.toLowerCase());
    }
}

