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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class SelectProductController implements Initializable {

    private String stageToOpen;

    Connect connect = new Connect();
    AlertController alertController = new AlertController();

    ObservableList<Product> productsToAdd = FXCollections.observableArrayList();
    public static ArrayList<Product> addedProducts = new ArrayList<>();

    @FXML
    private TextField productSearchField;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private Button selectProductButton;

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
    private TableColumn<Product, String> invoiceProductNumberColumn;

    @FXML
    private ImageView closeAndReturnButton;

    @FXML
    void cancelStage(MouseEvent event) throws IOException {
        Stage stage = (Stage) closeAndReturnButton.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("scenes/invoiceStage.fxml"));
        Stage returnToInvoice = new Stage();
        returnToInvoice.setScene(new Scene(root));
        Image icon = new Image(getClass().getResourceAsStream("images/invoice.png"));
        returnToInvoice.getIcons().add(icon);
        returnToInvoice.initStyle(StageStyle.UNDECORATED);
        returnToInvoice.show();
    }

    @FXML
    void selectProductViaButton(ActionEvent event) throws IOException {
        boolean addProduct = true;
        Product selectedproduct = productTable.getSelectionModel().getSelectedItem();
        try {
            for (int i = 0; i < addedProducts.size(); i++) {
                if (addedProducts.get(i).Id.equals(selectedproduct.Id)) {
                    addProduct = false;
                }
            }
            if (addProduct) {
                addedProducts.add(selectedproduct);
            }

            if (MainController.isItInvoice) {
                stageToOpen = "scenes/invoiceStage.fxml";
            } else {
                stageToOpen = "scenes/receiveNoteStage.fxml";
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(stageToOpen));
            Parent root = loader.load();
            Stage invoiceStage = new Stage();
            invoiceStage.setScene(new Scene(root));
            Image icon = new Image(getClass().getResourceAsStream("images/invoice.png"));
            invoiceStage.getIcons().add(icon);
            invoiceStage.initStyle(StageStyle.UNDECORATED);
            invoiceStage.show();

            Stage stage = (Stage) productSearchField.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            alertController.noProductSelectedAlert();
        }
    }


    @FXML
    void addProduct(MouseEvent event) throws IOException {
        boolean addProduct = true;
        Product selectedproduct = productTable.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            Stage stage = (Stage) productSearchField.getScene().getWindow();
            stage.close();
            for (int i = 0; i < addedProducts.size(); i++) {
                if (addedProducts.get(i).Id.equals(selectedproduct.Id)) {
                    addProduct = false;
                }
            }
            if (addProduct) {
                addedProducts.add(selectedproduct);
            }

            if (MainController.isItInvoice) {
                stageToOpen = "scenes/invoiceStage.fxml";
            } else {
                stageToOpen = "scenes/receiveNoteStage.fxml";
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(stageToOpen));
            Parent root = loader.load();
            Stage invoiceStage = new Stage();
            invoiceStage.setScene(new Scene(root));
            Image icon = new Image(getClass().getResourceAsStream("images/invoice.png"));
            invoiceStage.getIcons().add(icon);
            invoiceStage.initStyle(StageStyle.UNDECORATED);
            invoiceStage.show();
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
        invoiceProductNumberColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("ProductNr"));
        getSelectedProductList();

        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        FilteredList<Product> filteredProduct = new FilteredList<>(FXCollections.observableList(productsToAdd));
        productSearchField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredProduct.setPredicate(createPredicateProduct(newValue))
        );

        productTable.setItems(filteredProduct);
    }

    public ObservableList<Product> getSelectedProductList() {
        productsToAdd.clear();
        connect.getProducts();
        for (int i = 0; i < Connect.productList.size(); i++) {
            productsToAdd.add(new Product(Connect.productList.get(i).Stock, Connect.productList.get(i).Id,
                    Connect.productList.get(i).isService, Connect.productList.get(i).Name,
                    Connect.productList.get(i).ProductNr, Connect.productList.get(i).productNetPrice,
                    Connect.productList.get(i).productGrossPrice, Connect.productList.get(i).discountNetPrice,
                    Connect.productList.get(i).discountGrossPrice, 0, Connect.productList.get(i).isDiscounted,
                    Connect.productList.get(i).purchaseNetPrice, Connect.productList.get(i).purchaseGrossPrice,
                    Connect.productList.get(i).replacementID));
        }
        return productsToAdd;
    }

    private Predicate<Product> createPredicateProduct(String searchText) {
        return product -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return productSearch(product, searchText);
        };
    }

    private boolean productSearch(Product product, String searchText) {
        return
                product.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                        product.getProductNetPrice().toLowerCase().contains(searchText.toLowerCase()) ||
                        product.getProductGrossPrice().toLowerCase().contains(searchText.toLowerCase());
    }
}
