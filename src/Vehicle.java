public abstract class Vehicle implements Fixable, Comparable<Vehicle> {
    private String name;

    public Vehicle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Abstract method to get fix name (time)
    public abstract int getFixName();

    // Implement compareTo to compare vehicles by their fix time
    @Override
    public int compareTo(Vehicle other) {
        // Compare based on fix name (fix time)
        return Integer.compare(this.getFixName(), other.getFixName());
    }
}
