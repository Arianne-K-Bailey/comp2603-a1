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

    /**
     * TODO M8: Add a package to this container.
     * Return false if: p is null, p's destination does not match, or
     * adding p would exceed maxWeightKg.
     * Return true on success.
     */
    public boolean addPackage(Package p) {
        return false; // TODO M8
    }

    /**
     * TODO M8: Return the sum of all packages' weightKg.
     */
    public double getCurrentWeightKg() {
        return 0.0; // TODO M8
    }

    /**
     * TODO M8: Return maxWeightKg - getCurrentWeightKg()
     */
    public double getRemainingCapacityKg() {
        return 0.0; // TODO M8
    }

    /**
     * TODO M8: Return the number of packages in this container.
     */
    public int getPackageCount() {
        return 0; // TODO M8
    }

    /**
     * TODO M8: Return the sum of all packages' getShippingCost().
     */
    public double getTotalRevenue() {
        return 0.0; // TODO M8
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
        return ""; // TODO M9
    }

    /**
     * Returns the list of packages (needed by FreightTerminal.findPackage).
     */
    public ArrayList<Package> getPackages() {
        return packages;
    }

    /**
     * TODO M9: Return a one-line summary:
     * "CNT-001 -> Trinidad [3 packages, 17.00 / 500.00 kg]"
     */
    @Override
    public String toString() {
        return ""; // TODO M9
    }
}
