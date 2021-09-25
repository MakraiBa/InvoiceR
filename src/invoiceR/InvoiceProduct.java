package invoiceR;

public class InvoiceProduct {
    String id;
    String invoiceId;
    String productId;
    String productName;
    String productNetPrice;
    String productGrossPrice;
    String discountNetPrice;
    String discountGrossPrice;
    String productNumber;
    int productQuantity;

    public InvoiceProduct(String id, String invoiceId, String productId,
                          String productName, String productNetPrice,
                          String productGrossPrice, String discountNetPrice,
                          String discountGrossPrice, String productNumber,
                          int productQuantity) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.productName = productName;
        this.productNetPrice = productNetPrice;
        this.productGrossPrice = productGrossPrice;
        this.discountNetPrice = discountNetPrice;
        this.discountGrossPrice = discountGrossPrice;
        this.productNumber = productNumber;
        this.productQuantity = productQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNetPrice() {
        return productNetPrice;
    }

    public void setProductNetPrice(String productNetPrice) {
        this.productNetPrice = productNetPrice;
    }

    public String getProductGrossPrice() {
        return productGrossPrice;
    }

    public void setProductGrossPrice(String productGrossPrice) {
        this.productGrossPrice = productGrossPrice;
    }

    public String getDiscountNetPrice() {
        return discountNetPrice;
    }

    public void setDiscountNetPrice(String discountNetPrice) {
        this.discountNetPrice = discountNetPrice;
    }

    public String getDiscountGrossPrice() {
        return discountGrossPrice;
    }

    public void setDiscountGrossPrice(String discountGrossPrice) {
        this.discountGrossPrice = discountGrossPrice;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
