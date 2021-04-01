package invoiceR;


public class Product {
    String Teszor;
    int Stock;
    String Id;
    boolean isService;
    boolean isDiscounted;
    String Name;
    String Comment;
    String ProductNr;
    String productNetPrice;
    String productGrossPrice;
    String purchaseNetPrice;
    String purchaseGrossPrice;
    String discountNetPrice;
    String discountGrossPrice;
    int productQuantity;

    public Product(int stock, String id, boolean isService, String name, String productNr,
                   String productNetPrice, String productGrossPrice, String discountNetPrice,
                   String discountGrossPrice, int productQuantity, boolean isDiscounted) {
        Stock = stock;
        Id = id;
        this.isService = isService;
        Name = name;
        ProductNr = productNr;
        this.productNetPrice = productNetPrice;
        this.productGrossPrice = productGrossPrice;
        this.discountNetPrice = discountNetPrice;
        this.discountGrossPrice = discountGrossPrice;
        this.productQuantity = productQuantity;
        this.isDiscounted = isDiscounted;
    }

    public Product(String teszor, int stock, String id, boolean isService, boolean isDiscounted, String name,
                   String comment, String productNr, String productNetPrice, String productGrossPrice,
                   String purchaseNetPrice, String purchaseGrossPrice,
                   String discountNetPrice, String discountGrossPrice) {
        Teszor = teszor;
        Stock = stock;
        Id = id;
        this.isService = isService;
        this.isDiscounted = isDiscounted;
        Name = name;
        Comment = comment;
        ProductNr = productNr;
        this.productNetPrice = productNetPrice;
        this.productGrossPrice = productGrossPrice;
        this.purchaseNetPrice = purchaseNetPrice;
        this.purchaseGrossPrice = purchaseGrossPrice;
        this.discountNetPrice = discountNetPrice;
        this.discountGrossPrice = discountGrossPrice;
    }


    public boolean isDiscounted() {
        return isDiscounted;
    }

    public void setDiscounted(boolean discounted) {
        isDiscounted = discounted;
    }

    public String getTeszor() {
        return Teszor;
    }

    public void setTeszor(String teszor) {
        Teszor = teszor;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public boolean isService() {
        return isService;
    }

    public void setService(boolean service) {
        isService = service;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getProductNr() {
        return ProductNr;
    }

    public void setProductNr(String productNr) {
        ProductNr = productNr;
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

    public String getPurchaseNetPrice() {
        return purchaseNetPrice;
    }

    public void setPurchaseNetPrice(String purchaseNetPrice) {
        this.purchaseNetPrice = purchaseNetPrice;
    }

    public String getPurchaseGrossPrice() {
        return purchaseGrossPrice;
    }

    public void setPurchaseGrossPrice(String purchaseGrossPrice) {
        this.purchaseGrossPrice = purchaseGrossPrice;
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

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}