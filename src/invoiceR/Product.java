package invoiceR;

import java.util.UUID;

public class Product {
    int Teszor;
    int Stock;
    int VAT;
    String ProductId;
    boolean isService;
    Currency currency;
    String ProductName;
    String ProductDecription;
    String ProductNr;
    float NetPrice;
    float GrossPrice;
    float Discount;

    public Product() {
    }

<<<<<<< Updated upstream
    public Product(int teszor, int stock, boolean isService, String productName, String productDecription, String productNr, float netPrice, float grossPrice, float discount, Currency curr) {
=======
    public Product(int teszor, int stock, boolean isService, String name, String decription, String productNr, float netPrice, float grossPrice, float discount, Currency curr) {
>>>>>>> Stashed changes
        Teszor = teszor;
        currency=curr;
        Stock = stock;
        UUID uuid = UUID.randomUUID();
        ProductId =uuid.toString();
        this.isService = isService;
        ProductName = productName;
        ProductDecription = productDecription;
        ProductNr = productNr;
        NetPrice = netPrice;
        GrossPrice = grossPrice;
        Discount = discount;
    }

    public int getTeszor() {
        return Teszor;
    }

    public void setTeszor(int teszor) {
        Teszor = teszor;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public String getProductId() {
        return ProductId;
    }

    public boolean isService() {
        return isService;
    }

    public void setService(boolean service) {
        isService = service;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDecription() {
        return ProductDecription;
    }

    public void setProductDecription(String productDecription) {
        ProductDecription = productDecription;
    }

    public String getProductNr() {
        return ProductNr;
    }

    public void setProductNr(String productNr) {
        ProductNr = productNr;
    }

    public float getNetPrice() {
        return NetPrice;
    }

    public void setNetPrice(float netPrice) {
        NetPrice = netPrice;
    }

    public float getGrossPrice() {
        return GrossPrice;
    }

    public void setGrossPrice(float grossPrice) {
        GrossPrice = grossPrice;
    }

    public float getDiscount() {
        return Discount;
    }

    public void setDiscount(float discount) {
        Discount = discount;
    }
}
