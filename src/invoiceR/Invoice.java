package invoiceR;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Date;

public class Invoice {
    public String invoiceNumber;
    public String buyerId;
    public String invoiceId;
    public Date invoicdDate;
    public Date fullfillmentDate;
    public String BuyerDetails;
    public int invoiceNetSum;
    public int invoiceGrossSum;

    public static ArrayList<Invoice> invoiceList = new ArrayList<>();
    ObservableList<Invoice> invoicesToShow = FXCollections.observableArrayList();


    public Invoice(String invoiceNumber, String buyerId, String invoiceId, Date invoicdDate,
                   Date fullfillmentDate, String buyerDetails, int invoiceNetSum, int invoiceGrossSum) {
        this.invoiceNumber = invoiceNumber;
        this.buyerId = buyerId;
        this.invoiceId = invoiceId;
        this.invoicdDate = invoicdDate;
        this.fullfillmentDate = fullfillmentDate;
        BuyerDetails = buyerDetails;
        this.invoiceNetSum = invoiceNetSum;
        this.invoiceGrossSum = invoiceGrossSum;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getInvoicdDate() {
        return invoicdDate;
    }

    public void setInvoicdDate(Date invoicdDate) {
        this.invoicdDate = invoicdDate;
    }

    public Date getFullfillmentDate() {
        return fullfillmentDate;
    }

    public void setFullfillmentDate(Date fullfillmentDate) {
        this.fullfillmentDate = fullfillmentDate;
    }

    public String getBuyerDetails() {
        return BuyerDetails;
    }

    public void setBuyerDetails(String buyerDetails) {
        BuyerDetails = buyerDetails;
    }

    public int getInvoiceNetSum() {
        return invoiceNetSum;
    }

    public void setInvoiceNetSum(int invoiceNetSum) {
        this.invoiceNetSum = invoiceNetSum;
    }

    public int getInvoiceGrossSum() {
        return invoiceGrossSum;
    }

    public void setInvoiceGrossSum(int invoiceGrossSum) {
        this.invoiceGrossSum = invoiceGrossSum;
    }
}
