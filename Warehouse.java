import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private String id; // Warehouse ID
    private Address address; // Warehouse address
    private List<Location> locations; // List of locations in the warehouse

    // Constructor
    public Warehouse(String id, Address address) {
        this.id = id;
        this.address = address;
        this.locations = new ArrayList<>(); // Initialize an empty list of locations
    }

    //Getters
    public String getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public List<Location> getLocations() {
        return locations;
    }
    
    
    
  
    // Method to add a location to the warehouse
    public void addLocation(Location location) {
        locations.add(location);
    }
}