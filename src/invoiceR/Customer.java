package invoiceR;

public class Customer {
    String Id;
    String billingName;
    String billingCity;
    String billingPostalCode;
    String billingAddress;
    String billingAddressType;
    String billingHouseNumber;
    String billingStairway;
    String billingFloor;
    String deliveryCity;
    String deliveryPostalCode;
    String deliveryAddress;
    String deliveryAddressType;
    String deliveryHouseNumber;
    String deliveryStairway;
    String deliveryFloor;
    String customerVAT;
    String phone;
    String email;
    String webPage;
    String bankAccount;
    String customerComment;
    boolean hasSameAddress;

    public Customer(String id, String billingName, String billingCity, String billingPostalCode, String billingAddress,
                    String billingAddressType, String billingHouseNumber, String billingStairway, String billingFloor,
                    String deliveryCity, String deliveryPostalCode, String deliveryAddress, String deliveryAddressType,
                    String deliveryHouseNumber, String deliveryStairway, String deliveryFloor, String customerVAT,
                    String phone, String email, String webPage, String bankAccount,
                    String customerComment, boolean hasSameAddress) {
        Id = id;
        this.billingName = billingName;
        this.billingCity = billingCity;
        this.billingPostalCode = billingPostalCode;
        this.billingAddress = billingAddress;
        this.billingAddressType = billingAddressType;
        this.billingHouseNumber = billingHouseNumber;
        this.billingStairway = billingStairway;
        this.billingFloor = billingFloor;
        this.deliveryCity = deliveryCity;
        this.deliveryPostalCode = deliveryPostalCode;
        this.deliveryAddress = deliveryAddress;
        this.deliveryAddressType = deliveryAddressType;
        this.deliveryHouseNumber = deliveryHouseNumber;
        this.deliveryStairway = deliveryStairway;
        this.deliveryFloor = deliveryFloor;
        this.customerVAT = customerVAT;
        this.phone = phone;
        this.email = email;
        this.webPage = webPage;
        this.bankAccount = bankAccount;
        this.customerComment = customerComment;
        this.hasSameAddress = hasSameAddress;
    }

    public Customer(String id, String billingName, String billingCity, String billingPostalCode,
                    String billingAddress, String billingAddressType, String billingHouseNumber,
                    String billingStairway, String billingFloor, String customerVAT, String phone,
                    String email, String bankAccount) {
        Id = id;
        this.billingName = billingName;
        this.billingCity = billingCity;
        this.billingPostalCode = billingPostalCode;
        this.billingAddress = billingAddress;
        this.billingAddressType = billingAddressType;
        this.billingHouseNumber = billingHouseNumber;
        this.billingStairway = billingStairway;
        this.billingFloor = billingFloor;
        this.customerVAT = customerVAT;
        this.phone = phone;
        this.email = email;
        this.bankAccount = bankAccount;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getBillingName() {
        return billingName;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingAddressType() {
        return billingAddressType;
    }

    public void setBillingAddressType(String billingAddressType) {
        this.billingAddressType = billingAddressType;
    }

    public String getBillingHouseNumber() {
        return billingHouseNumber;
    }

    public void setBillingHouseNumber(String billingHouseNumber) {
        this.billingHouseNumber = billingHouseNumber;
    }

    public String getBillingStairway() {
        return billingStairway;
    }

    public void setBillingStairway(String billingStairway) {
        this.billingStairway = billingStairway;
    }

    public String getBillingFloor() {
        return billingFloor;
    }

    public void setBillingFloor(String billingFloor) {
        this.billingFloor = billingFloor;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryPostalCode() {
        return deliveryPostalCode;
    }

    public void setDeliveryPostalCode(String deliveryPostalCode) {
        this.deliveryPostalCode = deliveryPostalCode;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddressType() {
        return deliveryAddressType;
    }

    public void setDeliveryAddressType(String deliveryAddressType) {
        this.deliveryAddressType = deliveryAddressType;
    }

    public String getDeliveryHouseNumber() {
        return deliveryHouseNumber;
    }

    public void setDeliveryHouseNumber(String deliveryHouseNumber) {
        this.deliveryHouseNumber = deliveryHouseNumber;
    }

    public String getDeliveryStairway() {
        return deliveryStairway;
    }

    public void setDeliveryStairway(String deliveryStairway) {
        this.deliveryStairway = deliveryStairway;
    }

    public String getDeliveryFloor() {
        return deliveryFloor;
    }

    public void setDeliveryFloor(String deliveryFloor) {
        this.deliveryFloor = deliveryFloor;
    }

    public String getCustomerVAT() {
        return customerVAT;
    }

    public void setCustomerVAT(String customerVAT) {
        this.customerVAT = customerVAT;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getCustomerComment() {
        return customerComment;
    }

    public void setCustomerComment(String customerComment) {
        this.customerComment = customerComment;
    }

    public boolean isHasSameAddress() {
        return hasSameAddress;
    }

    public void setHasSameAddress(boolean hasSameAddress) {
        this.hasSameAddress = hasSameAddress;
    }
}
