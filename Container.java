import java.util.ArrayList;

/*  Represents a shipping container bound for a single destination.
    Holds packages and enforces a maximum weight capacity.
*/

public class Container {

    // M1: Initialise this static counter to 1.
    private static int nextContainerId = 1;

    // M1: These fields are declared but not yet assigned.
    private String containerId;
    private String destination;
    private double maxWeightKg;
    private ArrayList<Package> packages;

    // M2: Implement this constructor.
    public Container(String destination, double maxWeightKg) {
        if (destination == null)
            throw new IllegalArgumentException("Destination is NULL.");

        if (maxWeightKg <= 0)
            throw new IllegalArgumentException("Weight is less than or equal to 0.");

        this.containerId = String.format("CNT-%03d", nextContainerId);
        nextContainerId++;
         
        this.destination = destination;
        this.maxWeightKg = maxWeightKg;
        packages = new ArrayList<Package>();
    }

    // M3: Chain to the 2-param constructor using this(...)
    public Container(String destination) {
        this(destination, 500.0);
    }

    // --- Getters ---
    // M4: Write getters for containerId, destination, maxWeightKg
    public String getContainerId(){
        return this.containerId;
    }

    public String getDestination(){
        return this.destination;
    }

    public double getMaxWeight(){
        return this.maxWeightKg;
    }

    // M8: Add a package to this container.
    public boolean addPackage(Package p) {
        if (p == null || !p.getDestination().equals(destination) || getCurrentWeightKg() + p.getWeightKg() > maxWeightKg)
            return false;

        packages.add(p);
        return true;
    }

    // M8: Return the sum of all packages' weightKg.
    public double getCurrentWeightKg() {
        double sum = 0.0;

        for (Package p : packages)
            sum += p.getWeightKg();

        return sum;
    }

    // M8: Return maxWeightKg - getCurrentWeightKg()
    public double getRemainingCapacityKg() {
        return maxWeightKg - getCurrentWeightKg();
    }

    // M8: Return the number of packages in this container.
    public int getPackageCount() {
        return packages.size();
    }

    // M8: Return the sum of all packages' getShippingCost().
    public double getTotalRevenue() {
        double totalCost = 0.0;

        for (Package p : packages)
            totalCost += p.getShippingCost();

        return totalCost;
    }

    /**
     * TODO M9: Build and return the multi-line manifest string.
     * Format:
     * === CNT-001 -> Trinidad (3 packages, 17.00 / 500.00 kg) ===
     * PKG-0001 Alice -> Bob Trinidad 5.00 kg $40.00
     * PKG-0005 Ivy -> Jack Trinidad 8.00 kg $95.00 [FRAGILE]
     * ...
     * Container revenue: $199.50
     * Each package line is indented with 2 spaces.
     * Use StringBuilder and String.format.
     */
    public String getManifest() {
        StringBuilder m = new StringBuilder();

        m.append(String.format("=== %s -> %s (%d packages, %.2f / %.2f kg) ===", 
            containerId, destination, packages.size(), getCurrentWeightKg(), maxWeightKg));
        
        for (Package p : packages){
            m.append("\n  ").append(p.toString());
        }

        String.format("\n  Container Revenue: $%.2f", getTotalRevenue());

        return m.toString();
    }

    /**
     * Returns the list of packages (needed by FreightTerminal.findPackage).
     */
    public ArrayList<Package> getPackages() {
        return packages;
    }

    // M9: Return a one-line summary
    @Override
    public String toString() {
        return String.format("%s -> %s [%d packages, %.2f / %.2f kg]", 
            containerId, destination, packages.size(), getCurrentWeightKg(), maxWeightKg);
    }
}
