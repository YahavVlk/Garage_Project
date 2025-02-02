import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class Garage {
    private int time = 0;
    private HashMap<DB.key, PriorityQueue<Vehicle>> map;  // Receive map from DB, now using PriorityQueue
    private ProtocolGarage protocol;

    // Constructor: Receive map directly from DB
    public Garage(HashMap<DB.key, PriorityQueue<Vehicle>> map, ProtocolGarage protocol) {
        this.map = map;
        this.protocol = protocol;
    }

    // Convert map to ArrayList of Vehicles when needed
    private ArrayList<Vehicle> convertMapToArrayList() {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();

        // Use a foreach loop to add vehicles from the map's priority queues to the ArrayList
        for (Map.Entry<DB.key, PriorityQueue<Vehicle>> entry : map.entrySet()) {
            PriorityQueue<Vehicle> queue = entry.getValue();
            while (!queue.isEmpty()) {
                vehicleList.add(queue.poll()); // Remove each vehicle from the priority queue and add to the list
            }
        }

        return vehicleList;
    }

    public void start() {
        // Convert map to ArrayList of vehicles
        ArrayList<Vehicle> vehicleList = convertMapToArrayList();

        // Check if the vehicle list is empty
        if (vehicleList.isEmpty()) {
            System.out.println("No vehicles to repair.");
            return;
        }

        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        System.out.println("\nGarage starting at " + timeStamp);

        // Runnable to simulate vehicle repair process
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tick(vehicleList);  // Pass the vehicleList to the tick method
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }

    private void tick(ArrayList<Vehicle> vehicleList) {
        time++;  // Increment time at each tick

        // Iterate over the vehicle list
        Iterator<Vehicle> iterator = vehicleList.iterator();
        while (iterator.hasNext()) {
            Vehicle v = iterator.next();
            if (time >= v.getFixName()) {  // Process the vehicle when time exceeds or equals fix time
                String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                v.fixed();
                protocol.Fixed();
                System.out.println("Vehicle fixed at " + timeStamp);
                iterator.remove();  // Remove the fixed vehicle from the list
                break;  // Exit the loop to avoid ConcurrentModificationException
            }
        }

        // Check if all vehicles are fixed
        if (vehicleList.isEmpty()) {
            System.out.println("All vehicles have been fixed! Exiting...");
            exit(0);  // Exit the program
        }
    }
}
