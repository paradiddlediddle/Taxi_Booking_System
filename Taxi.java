package TheProject;

import java.util.ArrayList;
import java.util.List;

public class Taxi {

   private int taxiID;
   private boolean isAvailable = true;
   private List<Ride> rides = new ArrayList<>();
   private char currentLocation = 'a';
   private int distanceFromPickupPoint;
   private double moneyEarned = 0;
   private int distanceTravelled = 0;

   //Public Constructor
    public Taxi (int taxiID) {
        this.taxiID = taxiID;
    }

    //Getters and Setters
    public int getTaxiID() { return taxiID; }

    public void setTaxiID(int taxiID) { this.taxiID = taxiID; }

    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available) { isAvailable = available; }

    public List<Ride> getRides() { return rides; }

    public void addRide (Ride ride) { this.rides.add(ride); }

    public char getCurrentLocation() { return currentLocation; }

    public void setCurrentLocation(char currentLocation) { this.currentLocation = currentLocation; }

    public double getMoneyEarned() { return moneyEarned; }

    public void setMoneyEarned(double moneyEarned) { this.moneyEarned = moneyEarned; }

    public int getDistanceTravelled() { return distanceTravelled; }

    public void setDistanceTravelled(int distanceTravelled) { this.distanceTravelled = distanceTravelled; }

    public int getDistanceFromPickupPoint() { return distanceFromPickupPoint; }

    public void setDistanceFromPickupPoint(int distanceFromPickupPoint) { this.distanceFromPickupPoint = distanceFromPickupPoint; }
}
