public class Location {
    private String locationCode; // Location code
    private Product product; // Product stored at this location - kan gøres med null som jeg har gjort

    // Constructor
    public Location(String locationCode) {
        this.locationCode = locationCode;
        this.product = null; // Skal fikses
    }

    // Getters
    public String getLocationCode() {
        return locationCode;
    }


    public Product getProduct() {
        return product;
    }

    // Setter
    public void setProduct(Product product) {
        this.product = product;
    }
}
