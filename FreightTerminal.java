import java.util.ArrayList;

/*  The hub that receives packages, packs them into containers, 
    dispatches containers, and produces financial reports.
*/

public class FreightTerminal {

    // M1: These fields are declared but not yet initialised.
    private String terminalName;
    private ArrayList<Package> pendingPackages;
    private ArrayList<Container> activeContainers;
    private ArrayList<Container> dispatchedContainers;

    // M2: Initialise terminalName and all three ArrayLists.
    public FreightTerminal(String terminalName) {
        this.terminalName = terminalName;

        pendingPackages = new ArrayList<Package>();
        activeContainers = new ArrayList<Container>();
        dispatchedContainers = new ArrayList<Container>();
    }

    // M4: Add a non-null package to pendingPackages.
    public void receivePackage(Package p) {
        if (p != null){
            pendingPackages.add(p);
        }
    }

    // M4: Return the size of pendingPackages.
    public int getPendingCount() {
        return pendingPackages.size();
    }

    // M8: Group pending packages by destination.
    public int packContainers() {
        ArrayList<String> destinations = new ArrayList<>();

        for (Package p : pendingPackages){
            if (!destinations.contains(p.getDestination())){
                destinations.add(p.getDestination());
            }
        }

        for (String destination : destinations){
            Container c = new Container(destination);

            for (Package p : pendingPackages){
                if (destination.equals(p.getDestination())){
                    c.addPackage(p);
                }
            }

            activeContainers.add(c);
        }

        pendingPackages.clear();
       
        return destinations.size();
    }

    // M9: Move all activeContainers to dispatchedContainers.
    public int dispatchAll() {
        dispatchedContainers.addAll(activeContainers);
        activeContainers.clear();

        return dispatchedContainers.size();
    }

    // M9: Return the sum of getTotalRevenue() across all dispatched containers.
    public double getTotalRevenue() {
        double total = 0.0;

        for (Container c : dispatchedContainers){
            total += c.getTotalRevenue();
        }

        return total;
    }

    // M9: Return the sum of getPackageCount() across all dispatched containers.
    public int getTotalPackagesShipped() {
        int sum = 0;

        for (Container c : dispatchedContainers){
            sum += c.getPackageCount();
        }

        return sum;
    }

    // M9: Search pending, active containers, and dispatched containers for a package with the given tracking ID.
    public Package findPackage(String trackingId) {
        for (Package p : pendingPackages){
            if (p.getTrackingId().equals(trackingId))
                return p;
        }

        for (Container c : activeContainers){
            for (Package p : c.getPackages()){
                if (p.getTrackingId().equals(trackingId))
                    return p;
            }
        }

        for (Container c : dispatchedContainers){
            for (Package p : c.getPackages()){
                if (p.getTrackingId().equals(trackingId))
                    return p;
            }
        }

        return null;
    }

    /**
     * Returns the list of active containers (for printing manifests in Driver).
     */
    public ArrayList<Container> getActiveContainers() {
        return activeContainers;
    }

    /**
     * TODO M10: Print the formatted daily report.
     * Format:
     * === Daily Report: Port of Spain Hub ===
     * Packages received: 12
     * Containers packed: 5
     * Packages shipped: 12
     * Total revenue: $3248.50
     *
     * Revenue by destination:
     * Trinidad: $199.50 (3 packages)
     * Barbados: $1403.00 (3 packages)
     * ...
     */
    public void printDailyReport() {
        // TODO M10
    }
}
