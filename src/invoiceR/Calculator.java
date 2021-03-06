package invoiceR;

import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Calculator {
    AlertController alertController = new AlertController();
    String formatter = "#.##";
    DecimalFormat df = new DecimalFormat(formatter);
    public static int invoicePayment = 0;
    public static int receiveNotePayment = 0;

    public Calculator() {
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

    public Double setSumNetPrice(ObservableList<Product> productlist) {
        Double netPriceSum = 0.0;
        for (int i = 0; i < productlist.size(); i++) {
            if (productlist.get(i).isDiscounted) {
                netPriceSum = netPriceSum + (Double.valueOf(productlist.get(i).discountNetPrice) * productlist.get(i).productQuantity);
            } else {
                netPriceSum = netPriceSum + (Double.valueOf(productlist.get(i).productNetPrice) * productlist.get(i).productQuantity);
            }
        }
        df.format(netPriceSum);
        return netPriceSum;
    }

    public int setSumGrossPrice(ObservableList<Product> productlist) {
        int grossPriceSum = 0;
        for (int i = 0; i < productlist.size(); i++) {
            if (productlist.get(i).isDiscounted) {
                grossPriceSum = grossPriceSum + (int) (Double.valueOf(productlist.get(i).discountGrossPrice) * productlist.get(i).productQuantity);
            } else {
                grossPriceSum = grossPriceSum + (int) (Double.valueOf(productlist.get(i).productGrossPrice) * productlist.get(i).productQuantity);
            }
        }
        df.format(grossPriceSum);
        return grossPriceSum;
    }

    public Double setPurchaseNetPrice(ObservableList<Product> productlist) {
        Double purchaseNetSum = 0.0;
        for (int i = 0; i < productlist.size(); i++) {
            purchaseNetSum = purchaseNetSum + purchaseNetSum + (Double.valueOf(productlist.get(i).purchaseNetPrice) * productlist.get(i).productQuantity);
        }
        return purchaseNetSum;
    }

    public int setPurchaseGrossSum(ObservableList<Product> productlist) {
        int purchaseGrossSum = 0;
        for (int i = 0; i < productlist.size(); i++) {
            purchaseGrossSum = purchaseGrossSum + (int) (Double.valueOf(productlist.get(i).purchaseGrossPrice) * productlist.get(i).productQuantity);
        }
        return purchaseGrossSum;
    }

    public LocalDate calculateDays(int daysToAdd) {
        String today = String.valueOf(LocalDate.now());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, daysToAdd);
        LocalDate localDate = LocalDateTime.ofInstant(c.toInstant(), c.getTimeZone().toZoneId()).toLocalDate();
        return localDate;
    }

    public int calculateDaysToAdd(String paymentString) {
        int daystoadd = 0;
        switch (paymentString) {
            case "??tutal??s - 8 nap":
                daystoadd = 8;
                break;
            case "??tutal??s - 15 nap":
                daystoadd = 15;
                break;
            case "??tutal??s - 30 nap":
                daystoadd = 30;
                break;
            case "Ut??nv??t":
                daystoadd = 8;
                break;
            default:
                daystoadd = 0;
        }
        return daystoadd;
    }

    public int calculateComboboxIndex(String paymentString) {
        int index = 0;
        switch (paymentString) {
            case "??tutal??s - 8 nap":
                index = 1;
                break;
            case "??tutal??s - 15 nap":
                index = 2;
                break;
            case "??tutal??s - 30 nap":
                index = 3;
                break;
            case "Ut??nv??t":
                index = 4;
                break;
            default:
                index = 0;
        }
        return index;
    }

    public int remainingStock(int stock, int replacement) {
        if (stock > replacement) {
            return replacement;
        }
        alertController.remainderAlert(stock);
        return stock;
    }
}