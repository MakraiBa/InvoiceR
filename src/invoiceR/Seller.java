package invoiceR;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Seller {
    String sellerId;
    String sellerName;
    String sellerCity;
    String sellerPostalCode;
    String sellerAddress;
    String sellerAddressType;
    String sellerHouseNumber;
    String sellerStairway;
    String sellerFloor;
    String sellerVAT;
    String sellerPhone;
    String sellerEmail;
    String sellerBankAccount;

    public static Seller defaultSeller=new Seller("jndsjvnjonr","Makrai Balázs",
            "Debrecen","4024","Sumen","utca",
            "7","A","1","212222","+36301712955",
            "b.makrai@gmail.com","123456789");

    public Seller() {
    }

    private String setSellerAddress(String postalcode, String city, String address,
                                   String addresstype, String housenumber,
                                   String stairway, String floor) {
        return postalcode + " " + city + ", " + address + " " + addresstype + " " + housenumber + " " + stairway + " " + floor;
    }

    public Seller(String sellerId, String sellerName, String sellerCity, String sellerPostalCode,
                  String sellerAddress, String sellerAddressType, String sellerHouseNumber,
                  String sellerStairway, String sellerFloor, String sellerVAT, String sellerPhone,
                  String sellerEmail, String sellerBankAccount) {
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.sellerCity = sellerCity;
        this.sellerPostalCode = sellerPostalCode;
        this.sellerAddress = sellerAddress;
        this.sellerAddressType = sellerAddressType;
        this.sellerHouseNumber = sellerHouseNumber;
        this.sellerStairway = sellerStairway;
        this.sellerFloor = sellerFloor;
        this.sellerVAT = sellerVAT;
        this.sellerPhone = sellerPhone;
        this.sellerEmail = sellerEmail;
        this.sellerBankAccount = sellerBankAccount;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerCity() {
        return sellerCity;
    }

    public void setSellerCity(String sellerCity) {
        this.sellerCity = sellerCity;
    }

    public String getSellerPostalCode() {
        return sellerPostalCode;
    }

    public void setSellerPostalCode(String sellerPostalCode) {
        this.sellerPostalCode = sellerPostalCode;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerAddressType() {
        return sellerAddressType;
    }

    public void setSellerAddressType(String sellerAddressType) {
        this.sellerAddressType = sellerAddressType;
    }

    public String getSellerHouseNumber() {
        return sellerHouseNumber;
    }

    public void setSellerHouseNumber(String sellerHouseNumber) {
        this.sellerHouseNumber = sellerHouseNumber;
    }

    public String getSellerStairway() {
        return sellerStairway;
    }

    public void setSellerStairway(String sellerStairway) {
        this.sellerStairway = sellerStairway;
    }

    public String getSellerFloor() {
        return sellerFloor;
    }

    public void setSellerFloor(String sellerFloor) {
        this.sellerFloor = sellerFloor;
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

    public String getSellerBankAccount() {
        return sellerBankAccount;
    }

    public void setSellerBankAccount(String sellerBankAccount) {
        this.sellerBankAccount = sellerBankAccount;
    }
}
