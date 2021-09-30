package invoiceR;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertController {

    Connect connect = new Connect();

    ButtonType Cancel = new ButtonType("Mégse", ButtonBar.ButtonData.CANCEL_CLOSE);
    ButtonType Confirm = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

    public void deleteProductConfirmAlert(String id, String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Biztos vagy benne?", Cancel, Confirm);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("A következő termék törlésére készülsz: " + name);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == Confirm) {
            connect.deleteProduct(id);
            deletedProduct();
        } else {
            alert.close();
        }
    }

    public void deleteCustomerConfirmAlert(String id, String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Biztos vagy benne?", Cancel, Confirm);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("A következő partner törlésére készülsz: " + name);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == Confirm) {
            deletedCustomer();
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

    public void invoiceSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Siker!");
        alert.setHeaderText("A számla sikeresen létrehozva!");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    public void newProductAdded() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Siker!");
        alert.setHeaderText("A termék sikeresen hozzáadva!");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    private void deletedProduct() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Siker!");
        alert.setHeaderText("A termék törlésre került!");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    public void editDoneProduct() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Siker!");
        alert.setHeaderText("A termék sikeresen módosítva!");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    public void newCustomerAdded() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Siker!");
        alert.setHeaderText("Az ügyfél sikeresen hozzáadva!");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    private void deletedCustomer() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Siker!");
        alert.setHeaderText("Az ügyfél törlésre került!");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    public void editDoneCustomer() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Siker!");
        alert.setHeaderText("Az ügyfél adatai sikeresen módosítva!");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    public void newReceiveNoteAdded() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Siker!");
        alert.setHeaderText("A bevételi bizonylat sikeresen hozzáadva!");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    public void noStockNoReplacementAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Hiba!");
        alert.setHeaderText("Sajnos a vásárlás nem teljesíthető");
        alert.setContentText("Nincs elég termék, és helyettesítő termék sincs");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

}

