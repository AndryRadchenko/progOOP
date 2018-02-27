public class Order {
    private long number;
    private int clientId;
    private int goodId;
    private int goodQuantity;

    public Order(long number, int clientId, int goodId, int goodQuantity) {
        this.number = number;
        this.clientId = clientId;
        this.goodId = goodId;
        this.goodQuantity = goodQuantity;
    }

    public Order() {
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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
