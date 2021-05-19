package TheProject;

public class Ride {

    private int customerID;
    private int bookingID;
    private Taxi taxi;
    private final char taxiInitialLocation;
    private final char pickupPoint;
    private final char dropPoint;
    private final int numberOfPointsToTravel;
    private int pickupTime;
    private final int dropTime;
    private final double rideAmount;
    private final int rideDistance;

    public Ride (Taxi taxi, int customerID, char pickupPoint, char dropPoint, int pickupTime) {
        this.customerID = customerID;
        this.pickupPoint = pickupPoint;
        this.dropPoint = dropPoint;
        this.pickupTime = pickupTime;
        this.numberOfPointsToTravel = ((Math.abs(this.getPickupPoint() - this.getDropPoint())));
        this.taxi = (taxi);
        this.rideDistance = (this.getNumberOfPointsToTravel() * 15);
        this.rideAmount = ((((this.getRideDistance() - 5) * 10) + 100)); //100 bucks for the first 5 kilometers
        this.pickupTime = (this.getPickupTime());
        this.dropTime = (this.getPickupTime() + this.getNumberOfPointsToTravel()); //since it takes 1 hour to travel between points, points to travel = time taken.
        this.taxiInitialLocation = (taxi.getCurrentLocation());
        //Updating Taxi Details

    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID (int bookingID) {
        this.bookingID = bookingID;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    public char getTaxiInitialLocation() {
        return taxiInitialLocation;
    }

    public char getPickupPoint() {
        return pickupPoint;
    }

    public char getDropPoint() {
        return dropPoint;
    }

    public int getPickupTime() {
        return pickupTime;
    }

    public int getDropTime() {
        return dropTime;
    }

    public double getRideAmount() {
        return rideAmount;
    }

    public int getRideDistance() {
        return rideDistance;
    }

    public int getNumberOfPointsToTravel() {
        return numberOfPointsToTravel;
    }
}
