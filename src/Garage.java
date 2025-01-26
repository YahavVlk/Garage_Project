import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Garage {
    int time = 0;
    private ArrayList<Vehicle> vehicles;
    private ProtocolGarage protocol;

    public Garage(ArrayList<Vehicle> vehicles, ProtocolGarage protocol) {
        this.vehicles = vehicles;
        this.protocol = protocol;
    }

    public void start() {
        if (vehicles.isEmpty()) { // Corrected condition
            System.out.println("No vehicles to repair.");
            return;
        }

        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        System.out.println("\nGarage starting at " + timeStamp);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tick();
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }

    private void tick(){
            time++;
            if(time == vehicles.get(0).getFixName()){
                String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                Vehicle v = vehicles.remove(0);
                v.fixed();
                protocol.Fixed();
                System.out.println("Vehicles fixed at " + timeStamp);

                if(vehicles.isEmpty()){
                    System.out.println("All vehicles have been fixed. Exiting...");
                    System.exit(1);
                }
                time = 0;
        }
    }
}
