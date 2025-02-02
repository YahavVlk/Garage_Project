import java.util.Scanner;

import static java.lang.System.exit;

public class MainScreen {
    private DB db;
    private Garage garage;

    public MainScreen() {
        db = new DB(); // Initialize DB
        garage = new Garage(db.getMap(), protocolGarage);
    }

    ProtocolGarage protocolGarage = new ProtocolGarage() {
        @Override
        public void Fixed() {
            System.out.println("Notification: A vehicle has been fixed!");
        }
    };

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter 1 For MotorCycle, 2 For Car, 3 For Truck, or 0 to start garage");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;

            System.out.println("Enter vehicle name: ");
            String name = scanner.nextLine();
            build(choice, name);
        }
        System.out.println("Starting the Garage...");
        garage.start();
    }

    public void build(int choice, String name) {
        Vehicle vehicle = null;
        switch (choice) {
            case 1:
                vehicle = MotorCycle.buildVehicle(name);
                break;
            case 2:
                vehicle = Car.buildVehicle(name);
                break;
            case 3:
                vehicle = Truck.buildVehicle(name);
                break;
            default:
                System.out.println("Invalid input");
                exit(1);
        }

        if (vehicle != null) {
            db.addVehiclesToMap(vehicle, choice);
            System.out.println("Vehicle added: " + name);
        }
    }
}
