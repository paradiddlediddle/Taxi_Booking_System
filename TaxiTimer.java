package TheProject;

public class TaxiTimer extends Thread{
    //property of the class
    private Taxi taxi;
    private long sleepTimeInSeconds;

    // Public constructor:
    public TaxiTimer (Taxi taxi, Ride ride) {
        this.taxi = taxi;
        sleepTimeInSeconds = Math.abs(ride.getPickupPoint() - ride.getDropPoint()) * 10; // It takes 10 seconds to travel between each point
    }
    // Decides how long the thread needs to sleep
    @Override
    public void run () {
        if (taxi.isAvailable()) try {
            // Sets the status of the taxi to false for the desired amount of time and then sets the status back to available
            taxi.setAvailable(false);
            Thread.sleep(sleepTimeInSeconds * 1000);
            taxi.setAvailable(true);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
