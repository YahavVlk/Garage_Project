import java.util.ArrayList;

public class DB {
    private ArrayList<Vehicle> vehicles;

    public DB() {
        vehicles = new ArrayList<>();
    }

    public void addVehicles(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    public ArrayList<Vehicle> getVehicles(){
        return vehicles;
    }
}
