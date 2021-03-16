package invoiceR;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class SelectBuyerController implements Initializable {

    Connect connect = new Connect();
    ObservableList<Customer> customerToInvoice = FXCollections.observableArrayList();
    public static ArrayList<Customer> customerToAdd = new ArrayList<>();

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
    void addCustomerToInvoice(MouseEvent event) throws IOException {
        Customer selectedBuyer = customerTable.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            customerToAdd.add(new Customer(selectedBuyer.Id, selectedBuyer.billingName, selectedBuyer.billingCity,
                    selectedBuyer.billingPostalCode, selectedBuyer.billingAddress, selectedBuyer.billingAddressType,
                    selectedBuyer.billingHouseNumber, selectedBuyer.billingStairway, selectedBuyer.billingFloor,
                    selectedBuyer.customerVAT, selectedBuyer.phone, selectedBuyer.email, selectedBuyer.bankAccount));
            Stage stage = (Stage) customerTable.getScene().getWindow();
            stage.close();
            passDataToInvoiceStage();
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

    private void passDataToInvoiceStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/invoiceStage.fxml"));
            Parent root = loader.load();
            InvoiceController invoicecontroller = loader.getController();
            invoicecontroller.setBuyerAddress(customerToAdd.get(0));
            Stage invoiceStage = new Stage();
            invoiceStage.setScene(new Scene(root));
            invoiceStage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
