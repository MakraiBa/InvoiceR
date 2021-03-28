package invoiceR;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertController {

    Connect connect = new Connect();

    public void deleteProductConfirmAlert(String id, String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("A következő termék törlésére készülsz: " + name);
        alert.setContentText("Biztos vagy benne?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            connect.deleteProduct(id);
        } else {
            alert.close();
        }
    }

    public void deleteCustomerConfirmAlert(String id, String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("A következő partner törlésére készülsz: " + name);
        alert.setContentText("Biztos vagy benne?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            connect.deleteCustomer(id);
        } else {
            alert.close();
        }
    }

    public void noProductSelectedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Nincs termék kiválasztva");
        alert.setHeaderText("Nincs termék kiválasztva");
        alert.setContentText("Válassz ki egy terméket a szerkesztéshez!");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    public void noCustomerSelectedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Nincs ügyfél kiválasztva");
        alert.setHeaderText("Nincs ügyfél kiválasztva");
        alert.setContentText("Válassz ki egy ügyfelet a szerkesztéshez!");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }

        public void closeInvoiceAlert(String id, String name) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Törlés megerősítése");
            alert.setHeaderText("A következő termék törlésére készülsz: " + name);
            alert.setContentText("Biztos vagy benne?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                connect.deleteProduct(id);
            } else {
                alert.close();
            }
        }
    }
}
