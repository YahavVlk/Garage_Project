public class Car extends Vehicle{
    public Car(String name){
        super(name);
    }

    @Override
    public int getFixName() {
        return 5;
    }

    public static Vehicle buildVehicle(String name) {
        return new Car(name);
    }

    @Override
    public void fixed() {
        System.out.println("Car " + getName() + " - all components have been fixed.");
    }


}
