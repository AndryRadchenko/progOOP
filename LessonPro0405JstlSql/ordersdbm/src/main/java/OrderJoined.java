public class OrderJoined {
    private long number;
    private String clientName;
    private String clientLastName;
    private int goodId;
    private int goodQuantity;

    public OrderJoined(long number, String clientName, String clientLastName, int goodId, int goodQuantity) {
        this.number = number;
        this.clientName = clientName;
        this.clientLastName = clientLastName;
        this.goodId = goodId;
        this.goodQuantity = goodQuantity;
    }

    public OrderJoined() {
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getGoodQuantity() {
        return goodQuantity;
    }

    public void setGoodQuantity(int goodQuantity) {
        this.goodQuantity = goodQuantity;
    }
}