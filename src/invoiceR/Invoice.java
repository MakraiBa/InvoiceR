package invoiceR;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

public class Invoice {
    String invoiceId;
    String customerInvoiceName;
    String customerInvoiceAddress;
    String customerInvoiceVAT;
    String customerInvoicePhone;
    String customerInvoiceEmail;
    String sellerInvoiceName;
    String sellerInvoiceAddress;
    String sellerInvoiceVAT;
    String sellerInvoicePhone;
    String sellerInvoiceEmail;
    int invoiceQuantity;
    int VATpercent;
    Double sumNetPrice;
    Double sumGrossPrice;
    Date invoiceDate;
    Date paymentDate;
    Date currentDate;
    boolean isItPaid;

    ObservableList<Product> invoiceProducts = FXCollections.observableArrayList();

    public Invoice(String invoiceId, String customerInvoiceName, String customerInvoiceAddress, String customerInvoiceVAT,
                   String customerInvoicePhone, String customerInvoiceEmail, String sellerInvoiceName,
                   String sellerInvoiceAddress, String sellerInvoiceVAT, String sellerInvoicePhone,
                   String sellerInvoiceEmail, int invoiceQuantity, int VATpercent, Double sumNetPrice,
                   Double sumGrossPrice, Date invoiceDate, Date paymentDate, Date currentDate, boolean isItPaid) {
        this.invoiceId = invoiceId;
        this.customerInvoiceName = customerInvoiceName;
        this.customerInvoiceAddress = customerInvoiceAddress;
        this.customerInvoiceVAT = customerInvoiceVAT;
        this.customerInvoicePhone = customerInvoicePhone;
        this.customerInvoiceEmail = customerInvoiceEmail;
        this.sellerInvoiceName = sellerInvoiceName;
        this.sellerInvoiceAddress = sellerInvoiceAddress;
        this.sellerInvoiceVAT = sellerInvoiceVAT;
        this.sellerInvoicePhone = sellerInvoicePhone;
        this.sellerInvoiceEmail = sellerInvoiceEmail;
        this.invoiceQuantity = invoiceQuantity;
        this.VATpercent = VATpercent;
        this.sumNetPrice = sumNetPrice;
        this.sumGrossPrice = sumGrossPrice;
        this.invoiceDate = invoiceDate;
        this.paymentDate = paymentDate;
        this.currentDate = currentDate;
        this.isItPaid = isItPaid;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCustomerInvoiceName() {
        return customerInvoiceName;
    }

    public void setCustomerInvoiceName(String customerInvoiceName) {
        this.customerInvoiceName = customerInvoiceName;
    }

    public String getCustomerInvoiceAddress() {
        return customerInvoiceAddress;
    }

    public void setCustomerInvoiceAddress(String customerInvoiceAddress) {
        this.customerInvoiceAddress = customerInvoiceAddress;
    }

    public String getCustomerInvoiceVAT() {
        return customerInvoiceVAT;
    }

    public void setCustomerInvoiceVAT(String customerInvoiceVAT) {
        this.customerInvoiceVAT = customerInvoiceVAT;
    }

    public String getCustomerInvoicePhone() {
        return customerInvoicePhone;
    }

    public void setCustomerInvoicePhone(String customerInvoicePhone) {
        this.customerInvoicePhone = customerInvoicePhone;
    }

    public String getCustomerInvoiceEmail() {
        return customerInvoiceEmail;
    }

    public void setCustomerInvoiceEmail(String customerInvoiceEmail) {
        this.customerInvoiceEmail = customerInvoiceEmail;
    }

    public String getSellerInvoiceName() {
        return sellerInvoiceName;
    }

    public void setSellerInvoiceName(String sellerInvoiceName) {
        this.sellerInvoiceName = sellerInvoiceName;
    }

    public String getSellerInvoiceAddress() {
        return sellerInvoiceAddress;
    }

    public void setSellerInvoiceAddress(String sellerInvoiceAddress) {
        this.sellerInvoiceAddress = sellerInvoiceAddress;
    }

    public String getSellerInvoiceVAT() {
        return sellerInvoiceVAT;
    }

    public void setSellerInvoiceVAT(String sellerInvoiceVAT) {
        this.sellerInvoiceVAT = sellerInvoiceVAT;
    }

    public String getSellerInvoicePhone() {
        return sellerInvoicePhone;
    }

    public void setSellerInvoicePhone(String sellerInvoicePhone) {
        this.sellerInvoicePhone = sellerInvoicePhone;
    }

    public String getSellerInvoiceEmail() {
        return sellerInvoiceEmail;
    }

    public void setSellerInvoiceEmail(String sellerInvoiceEmail) {
        this.sellerInvoiceEmail = sellerInvoiceEmail;
    }

    public int getInvoiceQuantity() {
        return invoiceQuantity;
    }

    public void setInvoiceQuantity(int invoiceQuantity) {
        this.invoiceQuantity = invoiceQuantity;
    }

    public int getVATpercent() {
        return VATpercent;
    }

    public void setVATpercent(int VATpercent) {
        this.VATpercent = VATpercent;
    }

    public Double getSumNetPrice() {
        return sumNetPrice;
    }

    public void setSumNetPrice(Double sumNetPrice) {
        this.sumNetPrice = sumNetPrice;
    }

    public Double getSumGrossPrice() {
        return sumGrossPrice;
    }

    public void setSumGrossPrice(Double sumGrossPrice) {
        this.sumGrossPrice = sumGrossPrice;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public boolean isItPaid() {
        return isItPaid;
    }

    public void setItPaid(boolean itPaid) {
        isItPaid = itPaid;
    }
}
