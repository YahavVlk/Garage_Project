import java.util.ArrayList;
import java.util.Scanner;

public class MainScreen {
    private ArrayList<Vehicle> vehicles;
    private Garage garage;

    public MainScreen() {
        vehicles = new ArrayList<>();
        garage = new Garage(vehicles, protocolGarage);
    }

    ProtocolGarage protocolGarage = new ProtocolGarage() {
        @Override
        public void Fixed() {
            System.out.println("Notification: A vehicle has been fixed!");
        }
    };

    public void run(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Enter 1 For MotorCycle, 2 For Car, 3 For Truck, or 0 to start garage");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 0) break;

            System.out.println("Enter vehicle name: ");
            String name = scanner.nextLine();

            Vehicle vehicle = null;
            switch(choice){
                case 1:
                    vehicle = new MotorCycle(name);
                    break;
                case 2:
                    vehicle = new Car(name);
                    break;
                case 3:
                    vehicle = new Truck(name);
                    break;
                default:
                    System.out.println("Invalid input");
                    continue;
            }
            vehicles.add(vehicle);
        }
        System.out.println("Staring the Garage...");
        garage.start();
    }
}
