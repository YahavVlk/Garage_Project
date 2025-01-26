public class Truck extends Vehicle{
    public Truck(String name){
        super(name);
    }

    @Override
    public int getFixName() {
        return 10;
    }

    @Override
    public void fixed() {
        System.out.println("Truck " + getName() + " - heavy-duty repairs have been completed.");
    }
}
