package invoiceR;

import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class PriceCalculator {
    String formatter = "#.##";
    DecimalFormat df = new DecimalFormat(formatter);

    public PriceCalculator() {
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public DecimalFormat getDf() {
        return df;
    }

    public void setDf(DecimalFormat df) {
        this.df = df;
    }

    public void calculateGrossPrices(TextField fromTextField, TextField toTextField, Double VAT) {
        String fromPriceString = fromTextField.getText();
        if (!fromPriceString.isEmpty()) {
            try {
                double fromPriceDouble = Double.parseDouble(fromPriceString);
                double toPriceDouble = (double) (fromPriceDouble * VAT);
                toTextField.setText((String.valueOf(df.format(toPriceDouble).replace(",", "."))));
            } catch (Exception e) {
                String updatedInput = removeLetter(fromPriceString);
                fromTextField.setText(updatedInput);
                fromTextField.positionCaret(updatedInput.length());
            }
        } else {
            toTextField.clear();
        }
    }

    public void calculateNetPrices(TextField fromTextField, TextField toTextField, Double VAT) {
        String fromPriceString = fromTextField.getText();
        if (!fromPriceString.isEmpty()) {
            try {
                double fromPriceDouble = Double.parseDouble(fromPriceString);
                double toPriceDouble = (double) (fromPriceDouble / VAT);
                toTextField.setText((String.valueOf(df.format(toPriceDouble).replace(",", "."))));
            } catch (Exception e) {
                String updatedInput = removeLetter(fromPriceString);
                fromTextField.setText(updatedInput);
                fromTextField.positionCaret(updatedInput.length());
            }
        } else {
            toTextField.clear();
        }
    }

    private String removeLetter(String textFieldValue) {
        char charToReplace = textFieldValue.charAt(textFieldValue.length() - 1);
        String noLetter = (textFieldValue.replace(charToReplace, ' ')).trim();
        return noLetter;
    }

    public void updateGrossPrices(double vat, TextField productnet, TextField productgross,
                                  TextField purchasenet, TextField purchasegross,
                                  TextField discountnet, TextField discountgross) {
        if (!productnet.getText().isEmpty()) {
            double productNetPriceDB = Double.parseDouble(productnet.getText());
            productgross.setText((String.valueOf(df.format(productNetPriceDB * vat).replace(",", "."))));
        }
        if (!purchasenet.getText().isEmpty()) {
            double purchaseNetPriceDB = Double.parseDouble(purchasenet.getText());
            purchasegross.setText((String.valueOf(df.format(purchaseNetPriceDB * vat).replace(",", "."))));
        }
        if (!discountnet.getText().isEmpty()) {
            double discountNetPriceDB = Double.parseDouble(discountnet.getText());
            discountgross.setText((String.valueOf(df.format(discountNetPriceDB * vat).replace(",", "."))));
        }
    }
}