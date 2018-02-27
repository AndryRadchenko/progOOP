public class Flat {

    private int id;
    private String district;
    private String street;
    private int rooms;
    private int area;
    private int price;

    public Flat(int id, String district, String street, int rooms, int area, int price) {
        this.id = id;
        this.district = district;
        this.street = street;
        this.rooms = rooms;
        this.area = area;
        this.price = price;
    }

    public Flat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
