package invoiceR;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PreviewReceiveNoteController implements Initializable {
    Connect connect = new Connect();
    Seller seller = new Seller();

    @FXML
    private ImageView minimizeSceneButton;

    @FXML
    private ImageView maximizeSceneButton;

    @FXML
    private ImageView closeAndReturnButton;

    @FXML
    private TextField sellerNameField;

    @FXML
    private TextField sellerVATField;

    @FXML
    private TextField sellerAddressField;

    @FXML
    private TextField sellerBankNumberField;

    @FXML
    private TextField sellerPhoneField;

    @FXML
    private TextField sellerEmailField;

    @FXML
    private TextField receiveNoteNameField;

    @FXML
    private TextField receiveNoteVATField;

    @FXML
    private TextField receiveNoteAddressField;

    @FXML
    private TextField receiveNoteBankNumberField;

    @FXML
    private TextField receiveNotePhoneField;

    @FXML
    private TextField receiveNoteEmailField;

    @FXML
    private TableView<Product> receiveNoteProductTable;

    @FXML
    private TableColumn<Product, String> receiveNoteProductNameColumn;

    @FXML
    private TableColumn<Product, String> receiveNoteProductNetPriceColumn;

    @FXML
    private TableColumn<Product, String> receiveNoteProductGrossPriceColumn;

    @FXML
    private TableColumn<Product, String> receiveNoteProductQuantityColumn;

    @FXML
    private TableColumn<Product, String> receiveNoteProductNumberColumn;

    @FXML
    private TableColumn<Product, String> receiveNoteBoughtQuantityColumn;

    @FXML
    private TextField sumGrossPriceField;

    @FXML
    private TextField sumNetPriceField;

    @FXML
    private Button cancelReceiveNoteButton;

    @FXML
    void cancelAndReturn(MouseEvent event) throws IOException {
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
    void cancelReceiveNote(ActionEvent event) throws IOException {
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
    void maximizeScene(MouseEvent event) {
        Stage stage = (Stage) cancelReceiveNoteButton.getScene().getWindow();
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
        sellerNameField.setText(Seller.defaultSeller.sellerName);
        sellerAddressField.setText(seller.setSellerAddress(
                Seller.defaultSeller.getSellerPostalCode(), Seller.defaultSeller.getSellerCity(),
                Seller.defaultSeller.getSellerAddress(), Seller.defaultSeller.getSellerAddressType(),
                Seller.defaultSeller.getSellerHouseNumber(), Seller.defaultSeller.getSellerStairway(),
                Seller.defaultSeller.getSellerFloor()
        ));
        sellerEmailField.setText(Seller.defaultSeller.sellerEmail);
        sellerVATField.setText(Seller.defaultSeller.sellerVAT);
        sellerPhoneField.setText(Seller.defaultSeller.sellerPhone);
        sellerBankNumberField.setText(Seller.defaultSeller.sellerBankAccount);
    }
}
