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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class SelectBuyerController implements Initializable {

    public static String buyerId;
    public static String customerName;
    public static String customerFullAddress;
    public static String customerVAT;
    public static String customerPhone;
    public static String customerEmail;
    public static String customerBankNumber;
    private String stageToOpen;

    Connect connect = new Connect();
    ObservableList<Customer> customerToInvoice = FXCollections.observableArrayList();

    AlertController alertController = new AlertController();

    @FXML
    private TextField customerSearchField;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> customerNameColumn;

    @FXML
    private TableColumn<Customer, String> postalCodeColumn;

    @FXML
    private TableColumn<Customer, String> cityColumn;

    @FXML
    private TableColumn<Customer, String> customerAddressColumn;

    @FXML
    private TableColumn<Customer, String> houseNumberColumn;

    @FXML
    private TableColumn<Customer, String> customerEmailColumn;

    @FXML
    private TableColumn<Customer, String> customerPhoneColumn;

    @FXML
    private Button addCustomerButton;

    @FXML
    void addCustomerViaButton(ActionEvent event) throws IOException {
        try {
            Customer selectedBuyer = customerTable.getSelectionModel().getSelectedItem();
            buyerId = selectedBuyer.Id;
            customerName = selectedBuyer.billingName;
            customerFullAddress = setBuyerAddress(selectedBuyer.billingPostalCode, selectedBuyer.billingCity,
                    selectedBuyer.billingAddress, selectedBuyer.billingAddressType, selectedBuyer.billingHouseNumber,
                    selectedBuyer.billingStairway, selectedBuyer.billingFloor);
            customerVAT = selectedBuyer.customerVAT;
            customerPhone = selectedBuyer.phone;
            customerEmail = selectedBuyer.email;
            customerBankNumber = selectedBuyer.bankAccount;

            if (MainController.isItInvoice) {
                stageToOpen = "scenes/invoiceStage.fxml";
            } else {
                stageToOpen = "scenes/receiveNoteStage.fxml";
            }

            Parent root = FXMLLoader.load(getClass().getResource(stageToOpen));
            Stage invoiceStage = new Stage();
            invoiceStage.setScene(new Scene(root));
            invoiceStage.initStyle(StageStyle.UTILITY);
            invoiceStage.show();

            Stage stage = (Stage) customerTable.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            alertController.noCustomerSelectedAlert();
        }
    }

    @FXML
    void addCustomerToInvoice(MouseEvent event) throws IOException {
        Customer selectedBuyer = customerTable.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            buyerId = selectedBuyer.Id;
            customerName = selectedBuyer.billingName;
            customerFullAddress = setBuyerAddress(selectedBuyer.billingPostalCode, selectedBuyer.billingCity,
                    selectedBuyer.billingAddress, selectedBuyer.billingAddressType, selectedBuyer.billingHouseNumber,
                    selectedBuyer.billingStairway, selectedBuyer.billingFloor);
            customerVAT = selectedBuyer.customerVAT;
            customerPhone = selectedBuyer.phone;
            customerEmail = selectedBuyer.email;
            customerBankNumber = selectedBuyer.bankAccount;

            if (MainController.isItInvoice) {
                stageToOpen = "scenes/invoiceStage.fxml";
            } else {
                stageToOpen = "scenes/receiveNoteStage.fxml";
            }

            Parent root = FXMLLoader.load(getClass().getResource(stageToOpen));
            Stage invoiceStage = new Stage();
            invoiceStage.setScene(new Scene(root));
            invoiceStage.initStyle(StageStyle.UTILITY);
            invoiceStage.show();

            Stage stage = (Stage) customerTable.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("billingName"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("billingAddress"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        customerEmailColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("billingPostalCode"));
        houseNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("billingHouseNumber"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("billingCity"));
        getSelectedCustomers();

        FilteredList<Customer> filteredCustomer = new FilteredList<>(FXCollections.observableList(customerToInvoice));
        customerSearchField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredCustomer.setPredicate(createPredicateCustomer(newValue))
        );

        customerTable.setItems(filteredCustomer);
    }

    public ObservableList<Customer> getSelectedCustomers() {
        customerToInvoice.clear();
        connect.getCustomers();
        for (int i = 0; i < Connect.customerList.size(); i++) {
            customerToInvoice.add(new Customer(Connect.customerList.get(i).Id, Connect.customerList.get(i).billingName, Connect.customerList.get(i).billingCity,
                    Connect.customerList.get(i).billingPostalCode, Connect.customerList.get(i).billingAddress, Connect.customerList.get(i).billingAddressType,
                    Connect.customerList.get(i).billingHouseNumber, Connect.customerList.get(i).billingStairway, Connect.customerList.get(i).billingFloor,
                    Connect.customerList.get(i).deliveryCity, Connect.customerList.get(i).deliveryPostalCode, Connect.customerList.get(i).deliveryAddress,
                    Connect.customerList.get(i).deliveryAddressType, Connect.customerList.get(i).deliveryHouseNumber, Connect.customerList.get(i).deliveryStairway,
                    Connect.customerList.get(i).deliveryFloor, Connect.customerList.get(i).customerVAT, Connect.customerList.get(i).phone,
                    Connect.customerList.get(i).email, Connect.customerList.get(i).webPage, Connect.customerList.get(i).bankAccount,
                    Connect.customerList.get(i).customerComment, Connect.customerList.get(i).hasSameAddress));
        }
        return customerToInvoice;
    }

    private Predicate<Customer> createPredicateCustomer(String searchText) {
        return customer -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return customerSearch(customer, searchText);
        };
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

    private String setBuyerAddress(String postalcode, String city, String address,
                                   String addresstype, String housenumber,
                                   String stairway, String floor) {
        return postalcode + " " + city + ", " + address + " " + addresstype + " " + housenumber + " " + stairway + " " + floor;
    }
}
