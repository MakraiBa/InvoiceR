package invoiceR;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class Connect {

    public static List<Product> productList = new ArrayList<>();
    public static List<Customer> customerList = new ArrayList<>();
    public static List<Invoice> invoiceList = new ArrayList<>();
    public static List<ReceiveNote> receiveNoteList = new ArrayList<>();


    public Connection addNewProduct
            (String Teszor, String Id, int isService, String Name, String Comment, String ProductNr,
             String productNetPrice, String productGrossPrice, String purchaseNetPrice, String purchaseGrossPrice,
             String discountNetPrice, String discountGrossPrice, int isDiscounted) {
        Connection newProductToAdd = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            newProductToAdd = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/invoicerdb", "admin", "admin");
            Statement add;
            add = newProductToAdd.createStatement();
            String insert = "INSERT INTO `products` ( `productID`,`teszor`,`isservice`,`name`, `comment`, `productnumber`, `netprice`, `grossprice`, `purchasenetprice`, `purchasegrossprice`, `discountnetprice`, `dicountgrossprice`,`isdiscounted`) " +
                    "VALUES ('" + Id + "','" + Teszor + "','" + isService + "','" + Name + "','" + Comment + "','" + ProductNr + "','" + productNetPrice + "','" + productGrossPrice + "','" + purchaseNetPrice + "','" + purchaseGrossPrice + "','" + discountNetPrice + "','" + discountGrossPrice + "','" + isDiscounted + "')";

            add.executeUpdate(insert);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return newProductToAdd;
    }

    public Connection getProducts() {
        Connection getProducts = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String productsQuery = "SELECT * FROM products";
            getProducts = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/invoicerdb", "admin", "admin");
            Statement prdct;
            prdct = getProducts.createStatement();
            ResultSet productResult = prdct.executeQuery(productsQuery);
            productList.clear();
            while (productResult.next()) {
                productList.add(new Product(
                        productResult.getString("teszor"),
                        productResult.getInt("stock"),
                        productResult.getString("productId"),
                        getServiceBoolean(productResult.getInt("isservice")),
                        getDiscountedBoolean(productResult.getInt("isdiscounted")),
                        productResult.getString("name"),
                        productResult.getString("comment"),
                        productResult.getString("productnumber"),
                        productResult.getString("netprice"),
                        productResult.getString("grossprice"),
                        productResult.getString("purchasenetprice"),
                        productResult.getString("purchasegrossprice"),
                        productResult.getString("discountnetprice"),
                        productResult.getString("dicountgrossprice")));
            }
        } catch (ClassNotFoundException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sikertelen csatlakozás");
            alert.setHeaderText("Az adatbázishoz való csatlakozás sikertelen");
            alert.setContentText("Próbáld meg később!");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                System.exit(0);
            }
        }
        return getProducts;
    }

    public Connection deleteProduct(String id) {
        Connection removeProduct = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            removeProduct = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/invoicerdb", "admin", "admin");
            Statement delete;
            delete = removeProduct.createStatement();
            String dltProduct = "DELETE FROM `products` WHERE `products`.`productID` = '" + id + "'";
            delete.executeUpdate(dltProduct);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return removeProduct;
    }

    public Connection updateProductDetails
            (String Teszor, String Id, int isService, String Name, String Comment, String productNr,
             String productNetPrice, String productGrossPrice, String purchaseNetPrice, String purchaseGrossPrice,
             String discountNetPrice, String discountGrossPrice, int isDiscounted) {
        Connection productDataUpdate = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            productDataUpdate = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/invoicerdb", "admin", "admin");
            Statement st;
            st = productDataUpdate.createStatement();
            String updatePrd = "UPDATE `products` SET `teszor` = '" + Teszor + "', `isservice` = '" + isService + "', `name` = '" + Name + "', `comment` = '" + Comment + "', `productnumber` = '" + productNr + "', `netprice` = '" + productNetPrice + "', `grossprice` = '" + productGrossPrice + "', `purchasenetprice` = '" + purchaseNetPrice + "', `purchasegrossprice` = '" + purchaseGrossPrice + "', `discountnetprice` = '" + discountNetPrice + "', `dicountgrossprice` = '" + discountGrossPrice + "', `isdiscounted` = '" + isDiscounted + "' WHERE `products`.`productID` = '" + Id + "'";
            st.executeUpdate(updatePrd);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return productDataUpdate;
    }

    public Connection addNewCustomer
            (String id, String billingName, String billingCity, String billingPostalCode, String billingAddress,
             String billingAddressType, String billingHouseNumber, String billingStairway, String billingFloor,
             String deliveryCity, String deliveryPostalCode, String deliveryAddress, String deliveryAddressType,
             String deliveryHouseNumber, String deliveryStairway, String deliveryFloor, String customerVAT,
             String phone, String email, String webPage, String bankAccount, String customerComment, int hassameAddress) {
        Connection newCustomerToAdd = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            newCustomerToAdd = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/invoicerdb", "admin", "admin");
            Statement add;
            add = newCustomerToAdd.createStatement();
            String insert = "INSERT INTO `customers` ( `customerId`,`billingName`,`billingCity`,`billingPostalCode`,`billingAddress`, `billingAddressType`, `billingHouseNumber`, `billingStairway`, `billingFloor`, `deliveryCity`, `deliveryPostalCode`, `deliveryAddress`, `deliveryAddressType`, `deliveryHouseNumber`, `deliveryStairway`, `deliveryFloor`, `customerVAT`, `phone`, `email`, `webPage`, `bankAccount`, `contactComment` ,`hasSameAddress`) " +
                    "VALUES ('" + id + "','" + billingName + "','" + billingCity + "','" + billingPostalCode + "','" + billingAddress + "','" + billingAddressType + "','" + billingHouseNumber + "','" + billingStairway + "','" + billingFloor + "','" + deliveryCity + "','" + deliveryPostalCode + "','" + deliveryAddress + "','" + deliveryAddressType + "','" + deliveryHouseNumber + "','" + deliveryStairway + "','" + deliveryFloor + "','" + customerVAT + "','" + phone + "','" + email + "','" + webPage + "','" + bankAccount + "','" + customerComment + "','" + hassameAddress + "')";

            add.executeUpdate(insert);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return newCustomerToAdd;
    }

    public Connection getCustomers() {
        Connection getCustomers = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String customerQuery = "SELECT * FROM customers";
            getCustomers = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/invoicerdb", "admin", "admin");
            Statement cstmer;
            cstmer = getCustomers.createStatement();
            ResultSet customerResult = cstmer.executeQuery(customerQuery);
            customerList.clear();
            while (customerResult.next()) {
                customerList.add(new Customer(
                        customerResult.getString("customerId"),
                        customerResult.getString("billingName"),
                        customerResult.getString("billingCity"),
                        customerResult.getString("billingPostalCode"),
                        customerResult.getString("billingAddress"),
                        customerResult.getString("billingAddressType"),
                        customerResult.getString("billingHouseNumber"),
                        customerResult.getString("billingStairway"),
                        customerResult.getString("billingFloor"),
                        customerResult.getString("deliveryCity"),
                        customerResult.getString("deliveryPostalCode"),
                        customerResult.getString("deliveryAddress"),
                        customerResult.getString("deliveryAddressType"),
                        customerResult.getString("deliveryHouseNumber"),
                        customerResult.getString("deliveryStairway"),
                        customerResult.getString("deliveryFloor"),
                        customerResult.getString("customerVAT"),
                        customerResult.getString("phone"),
                        customerResult.getString("email"),
                        customerResult.getString("webPage"),
                        customerResult.getString("bankAccount"),
                        customerResult.getString("contactComment"),
                        hasTheSameAddress(customerResult.getInt("hasSameAddress")))
                );
            }
        } catch (ClassNotFoundException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sikertelen csatlakozás");
            alert.setHeaderText("Az adatbázishoz való csatlakozás sikertelen");
            alert.setContentText("Próbáld meg később!");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                System.exit(0);
            }
        }
        return getCustomers;
    }

    public Connection deleteCustomer(String id) {
        Connection removeCustomer = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            removeCustomer = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/invoicerdb", "admin", "admin");
            Statement delete;
            delete = removeCustomer.createStatement();
            String dltCustomer = "DELETE FROM `customers` WHERE `customers`.`customerId` = '" + id + "'";
            delete.executeUpdate(dltCustomer);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return removeCustomer;
    }

    public Connection updateCustomerDetails
            (String Id, String billingName, String billingCity, String billingPostalCode, String billingAddress,
             String billingAddressType, String billingHouseNumber, String billingStairway, String billingFloor,
             String deliveryCity, String deliveryPostalCode, String deliveryAddress, String deliveryAddressType,
             String deliveryHouseNumber, String deliveryStairway, String deliveryFloor, String customerVAT,
             String phone, String email, String webPage, String bankAccount, String customerComment,
             int hasSameAddress) {
        Connection customerDataUpdate = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            customerDataUpdate = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/invoicerdb", "admin", "admin");
            Statement st;
            st = customerDataUpdate.createStatement();
            String updateCstmr = "UPDATE `customers` SET `billingName` = '" + billingName + "', `billingCity` = '" + billingCity + "', `billingPostalCode` = '" + billingPostalCode + "', `billingAddress` = '" + billingAddress + "', `billingAddressType` = '" + billingAddressType + "', `billingHouseNumber` = '" + billingHouseNumber + "', `billingStairway` = '" + billingStairway + "', `billingFloor` = '" + billingFloor + "', `deliveryCity` = '" + deliveryCity + "', `deliveryPostalCode` = '" + deliveryPostalCode + "', `deliveryAddress` = '" + deliveryAddress + "',`deliveryAddress` = '" + deliveryAddress + "',`deliveryAddressType` = '" + deliveryAddressType + "',`deliveryHouseNumber` = '" + deliveryHouseNumber + "',`deliveryStairway` = '" + deliveryStairway + "',`deliveryFloor` = '" + deliveryFloor + "',`customerVAT` = '" + customerVAT + "',`phone` = '" + phone + "',`email` = '" + email + "',`webPage` = '" + webPage + "',`bankAccount` = '" + bankAccount + "',`contactComment` = '" + customerComment + "', `hasSameAddress` = '" + hasSameAddress + "' WHERE `customers`.`customerId` = '" + Id + "'";
            st.executeUpdate(updateCstmr);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return customerDataUpdate;
    }

    public Connection addNewInvoice(String invoiceId, String customerInvoiceName, String customerInvoiceAddress,
                                    String sumNetPrice, String sumGrossPrice, String invoiceDate, String buyerId) {
        Connection addNewInvoice = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            addNewInvoice = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/invoicerdb", "admin", "admin");
            Statement st;
            st = addNewInvoice.createStatement();
            String insert = "INSERT INTO `invoices` ( `invoiceId`,`customerInvoiceName`,`customerInvoiceAddress`,`sumNetPrice`,`sumGrossPrice`,`invoiceDate`,`buyerId`) " +
                    "VALUES ('" + invoiceId + "','" + customerInvoiceName + "','" + customerInvoiceAddress + "','" + sumNetPrice + "','" + sumGrossPrice + "','" + invoiceDate + "','" + buyerId + "')";
            st.executeUpdate(insert);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return addNewInvoice;
    }

    public Connection getInvoices() {
        Connection getInvoices = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String customerQuery = "SELECT * FROM invoices";
            getInvoices = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/invoicerdb", "admin", "admin");
            Statement invcs;
            invcs = getInvoices.createStatement();
            ResultSet invoiceResult = invcs.executeQuery(customerQuery);
            invoiceList.clear();
            while (invoiceResult.next()) {
                invoiceList.add(new Invoice(
                        invoiceResult.getString("invoiceId"),
                        invoiceResult.getString("buyerId"),
                        invoiceResult.getString("customerInvoiceName"),
                        invoiceResult.getString("customerInvoiceAddress"),
                        Double.parseDouble(invoiceResult.getString("sumNetPrice")),
                        Integer.parseInt(invoiceResult.getString("sumGrossPrice")),
                        invoiceResult.getString("invoiceDate")
                ));
            }
        } catch (ClassNotFoundException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sikertelen csatlakozás");
            alert.setHeaderText("Az adatbázishoz való csatlakozás sikertelen");
            alert.setContentText("Próbáld meg később!");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                System.exit(0);
            }
        }
        return getInvoices;
    }

    public Connection addNewReceiveNote(String receiveNoteId, String sellerId, String receiveNoteName,
                                        String receiveNoteFullAddress, String receiveNoteSumNetPrice,
                                        String receiveNoteSumGrossPrice, String receiveNoteCurrentDate) {
        Connection addNewReceiveNote = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            addNewReceiveNote = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/invoicerdb", "admin", "admin");
            Statement st;
            st = addNewReceiveNote.createStatement();
            String insert = "INSERT INTO `receivenotes` ( `receivenoteid `,`sellerid`,`receivenotename`,`fulladdress`,`sumnetprice`,`sumgrossprice`,`receivenotedate`) " +
                    "VALUES ('" + receiveNoteId + "','" + sellerId + "','" + receiveNoteName + "','" + receiveNoteFullAddress + "','" + receiveNoteSumNetPrice + "','" + receiveNoteSumGrossPrice + "','" + receiveNoteCurrentDate + "')";
            st.executeUpdate(insert);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return addNewReceiveNote;
    }

    public Connection getReceiveNotes() {
        Connection getReceiveNotes = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String customerQuery = "SELECT * FROM receivenotes";
            getReceiveNotes = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/invoicerdb", "admin", "admin");
            Statement rcnts;
            rcnts = getReceiveNotes.createStatement();
            ResultSet receivenoteResult = rcnts.executeQuery(customerQuery);
            receiveNoteList.clear();
            while (receivenoteResult.next()) {
                receiveNoteList.add(new ReceiveNote(
                        receivenoteResult.getString("receivenoteid"),
                        receivenoteResult.getString("sellerid"),
                        receivenoteResult.getString("receivenotename"),
                        receivenoteResult.getString("fulladdress"),
                        Double.parseDouble(receivenoteResult.getString("sumnetprice")),
                        Integer.parseInt(receivenoteResult.getString("sumgrossprice")),
                        receivenoteResult.getString("receivenotedate")
                ));
            }
        } catch (ClassNotFoundException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sikertelen csatlakozás");
            alert.setHeaderText("Az adatbázishoz való csatlakozás sikertelen");
            alert.setContentText("Próbáld meg később!");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                System.exit(0);
            }
        }
        return getReceiveNotes;
    }

    private boolean getServiceBoolean(int isservice) {
        if (isservice == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean getDiscountedBoolean(int isdiscounted) {
        if (isdiscounted == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean hasTheSameAddress(int address) {
        if (address == 1) {
            return true;
        } else {
            return false;
        }
    }
}
