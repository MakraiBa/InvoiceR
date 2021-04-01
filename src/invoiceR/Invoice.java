package invoiceR;

import java.util.Date;

public class Invoice {
    String invoiceId;
    String buyerId;
    String customerName;
    String customerFullAddress;
    String customerVAT;
    String customerPhone;
    String customerEmail;
    String sellerName;
    String sellerFullAddress;
    String sellerVAT;
    String sellerPhone;
    String sellerEmail;
    Double sumNetPrice;
    int sumGrossPrice;
    Date currentDate;
    Date paymentDate;
    Date fulfilmentDate;

    public Invoice() {
    }

    public Invoice(String invoiceId, String buyerId, String customerName, String customerFullAddress,
                   String customerVAT, String customerPhone, String customerEmail, String sellerName,
                   String sellerFullAddress, String sellerVAT, String sellerPhone, String sellerEmail,
                   Double sumNetPrice, int sumGrossPrice, Date currentDate,
                   Date paymentDate, Date fulfilmentDate) {
        this.invoiceId = invoiceId;
        this.buyerId = buyerId;
        this.customerName = customerName;
        this.customerFullAddress = customerFullAddress;
        this.customerVAT = customerVAT;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.sellerName = sellerName;
        this.sellerFullAddress = sellerFullAddress;
        this.sellerVAT = sellerVAT;
        this.sellerPhone = sellerPhone;
        this.sellerEmail = sellerEmail;
        this.sumNetPrice = sumNetPrice;
        this.sumGrossPrice = sumGrossPrice;
        this.currentDate = currentDate;
        this.paymentDate = paymentDate;
        this.fulfilmentDate = fulfilmentDate;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerFullAddress() {
        return customerFullAddress;
    }

    public void setCustomerFullAddress(String customerFullAddress) {
        this.customerFullAddress = customerFullAddress;
    }

    public String getCustomerVAT() {
        return customerVAT;
    }

    public void setCustomerVAT(String customerVAT) {
        this.customerVAT = customerVAT;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerFullAddress() {
        return sellerFullAddress;
    }

    public void setSellerFullAddress(String sellerFullAddress) {
        this.sellerFullAddress = sellerFullAddress;
    }

    public String getSellerVAT() {
        return sellerVAT;
    }

    public void setSellerVAT(String sellerVAT) {
        this.sellerVAT = sellerVAT;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public Double getSumNetPrice() {
        return sumNetPrice;
    }

    public void setSumNetPrice(Double sumNetPrice) {
        this.sumNetPrice = sumNetPrice;
    }

    public int getSumGrossPrice() {
        return sumGrossPrice;
    }

    public void setSumGrossPrice(int sumGrossPrice) {
        this.sumGrossPrice = sumGrossPrice;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getFulfilmentDate() {
        return fulfilmentDate;
    }

    public void setFulfilmentDate(Date fulfilmentDate) {
        this.fulfilmentDate = fulfilmentDate;
    }
}
