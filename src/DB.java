import java.util.*;

public class DB {
    public enum key {MOTORCYCLE, CAR, TRUCK}
    private ArrayList<Vehicle> vehicles;
    private HashMap<key, PriorityQueue<Vehicle>> map;

    public DB() {
        vehicles = new ArrayList<>();
        map = new HashMap<>();

        // Initialize queues using ArrayDeque instead of LinkedList
        map.put(key.MOTORCYCLE, new PriorityQueue<>());
        map.put(key.CAR, new PriorityQueue<>());
        map.put(key.TRUCK, new PriorityQueue<>());
    }

    public void addVehicles(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void addVehiclesToMap(Vehicle vehicle, int type) {
        switch (type) {
            case 1:
                map.get(key.MOTORCYCLE).add(vehicle);
                break;
            case 2:
                map.get(key.CAR).add(vehicle);
                break;
            case 3:
                map.get(key.TRUCK).add(vehicle);
                break;
            default:
                System.out.println("Invalid vehicle type.");
        }
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public HashMap<key, PriorityQueue<Vehicle>> getMap() {
        return map;
    }
}
