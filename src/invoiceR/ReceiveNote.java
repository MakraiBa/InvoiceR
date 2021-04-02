package invoiceR;

public class ReceiveNote {
    String receiveNoteId;
    String sellerId;
    String receiveNoteName;
    String receiveNoteFullAddress;
    Double receiveNoteSumNetPrice;
    int receiveNoteSumGrossPrice;
    String receiveNoteCurrentDate;

    public ReceiveNote(String receiveNoteId, String sellerId, String receiveNoteName,
                       String receiveNoteFullAddress, Double receiveNoteSumNetPrice,
                       int receiveNoteSumGrossPrice, String receiveNoteCurrentDate) {
        this.receiveNoteId = receiveNoteId;
        this.sellerId = sellerId;
        this.receiveNoteName = receiveNoteName;
        this.receiveNoteFullAddress = receiveNoteFullAddress;
        this.receiveNoteSumNetPrice = receiveNoteSumNetPrice;
        this.receiveNoteSumGrossPrice = receiveNoteSumGrossPrice;
        this.receiveNoteCurrentDate = receiveNoteCurrentDate;
    }

    public String getReceiveNoteId() {
        return receiveNoteId;
    }

    public void setReceiveNoteId(String receiveNoteId) {
        this.receiveNoteId = receiveNoteId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getReceiveNoteName() {
        return receiveNoteName;
    }

    public void setReceiveNoteName(String receiveNoteName) {
        this.receiveNoteName = receiveNoteName;
    }

    public String getReceiveNoteFullAddress() {
        return receiveNoteFullAddress;
    }

    public void setReceiveNoteFullAddress(String receiveNoteFullAddress) {
        this.receiveNoteFullAddress = receiveNoteFullAddress;
    }

    public Double getReceiveNoteSumNetPrice() {
        return receiveNoteSumNetPrice;
    }

    public void setReceiveNoteSumNetPrice(Double receiveNoteSumNetPrice) {
        this.receiveNoteSumNetPrice = receiveNoteSumNetPrice;
    }

    public int getReceiveNoteSumGrossPrice() {
        return receiveNoteSumGrossPrice;
    }

    public void setReceiveNoteSumGrossPrice(int receiveNoteSumGrossPrice) {
        this.receiveNoteSumGrossPrice = receiveNoteSumGrossPrice;
    }

    public String getReceiveNoteCurrentDate() {
        return receiveNoteCurrentDate;
    }

    public void setReceiveNoteCurrentDate(String receiveNoteCurrentDate) {
        this.receiveNoteCurrentDate = receiveNoteCurrentDate;
    }
}
