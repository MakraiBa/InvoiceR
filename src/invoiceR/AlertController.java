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
        alert.setTitle("Hiba!");
        alert.setHeaderText("Nincs termék kiválasztva");
        alert.setContentText("Válassz ki egy terméket!");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    public void noCustomerSelectedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Hiba!");
        alert.setHeaderText("Nincs ügyfél kiválasztva");
        alert.setContentText("Válassz ki egy ügyfelet!");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    public void stockAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Raktárkészlet hiba");
        alert.setHeaderText("A vásárolni kívánt mennyiség nagyobb, mint a raktárkészleten lévő mennyiség!");
        alert.setContentText("Folytathatjuk?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }
    public void emptyTextAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Hiba!");
        alert.setHeaderText("A csillaggal jelölt mezők kitöltése kötelező!");
        alert.setContentText("Töltsd ki a szükséges mezőket!");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }
    public void emptyFieldAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Hiba!");
        alert.setHeaderText("A dokumentum nincs teljesen kitöltve!");
        alert.setContentText("Töltsd ki a szükséges mezőket!");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }
}

