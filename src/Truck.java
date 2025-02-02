public class Truck extends Vehicle{
    public Truck(String name){
        super(name);
    }

    @Override
    public int getFixName() {
        return 10;
    }

    public static Vehicle buildVehicle(String name) {
        return new Truck(name);
    }

    @Override
    public void fixed() {
        System.out.println("Truck " + getName() + " - heavy-duty repairs have been completed.");
    }
}
