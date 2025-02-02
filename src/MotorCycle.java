public class MotorCycle extends Vehicle{
    public MotorCycle(String name){
        super(name);
    }

    @Override
    public int getFixName() {
        return 3;
    }

    public static Vehicle buildVehicle(String name) {
        return new MotorCycle(name);
    }


    @Override
    public void fixed() {
        System.out.println("MotorCycle " + getName() + " - Both wheels have been fixed.");
    }


}
