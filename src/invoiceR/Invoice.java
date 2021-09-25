package invoiceR;

public class Invoice {
    String invoiceId;
    String buyerId;
    String invoiceCustomerName;
    String customerFullAddress;
    Double sumNetPrice;
    int sumGrossPrice;
    String currentDate;
    String paymentMethod;
    String fulfilmentDate;
    String paymentDate;

    public Invoice(String invoiceId, String buyerId, String invoiceCustomerName,
                   String customerFullAddress, Double sumNetPrice, int sumGrossPrice,
                   String currentDate, String paymentMehod, String fulfilmentDate, String paymentDate) {
        this.invoiceId = invoiceId;
        this.buyerId = buyerId;
        this.invoiceCustomerName = invoiceCustomerName;
        this.customerFullAddress = customerFullAddress;
        this.sumNetPrice = sumNetPrice;
        this.sumGrossPrice = sumGrossPrice;
        this.currentDate = currentDate;
        this.paymentMethod = paymentMehod;
        this.fulfilmentDate = fulfilmentDate;
        this.paymentDate = paymentDate;
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

    public String getInvoiceCustomerName() {
        return invoiceCustomerName;
    }

    public void setInvoiceCustomerName(String invoiceCustomerName) {
        this.invoiceCustomerName = invoiceCustomerName;
    }

    public String getCustomerFullAddress() {
        return customerFullAddress;
    }

    public void setCustomerFullAddress(String customerFullAddress) {
        this.customerFullAddress = customerFullAddress;
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

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getFulfilmentDate() {
        return fulfilmentDate;
    }

    public void setFulfilmentDate(String fulfilmentDate) {
        this.fulfilmentDate = fulfilmentDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}