package TheProject;

import java.util.*;

public class Booking {

    private int bookingID = 1;
    private final List<Taxi> taxis = new ArrayList<>();
    private final HashMap<Integer, Ride> bookings = new HashMap<>();

    //Public constructor
    public Booking() {
        createTaxis();
    }

    // Based on the no of taxis selected, they should be created and stored inside the list.
    private void createTaxis() {
        int numberOfFunctioningTaxis = 4;
        for (int i = 1; i <= numberOfFunctioningTaxis; i++) {
            Taxi taxi = new Taxi(i);
            taxis.add(taxi);
        }
    }

    //Stuff to do:
    // Need to work on formatting the print functionality


    //1.BOOK A TAXI:
    public void bookATaxi () {
        Scanner taxiBooking = new Scanner(System.in);
        System.out.print("Enter customer ID(Number): ");
        int customerID = taxiBooking.nextInt();
        System.out.print("Enter Current Location:\n1.A\n2.B\n3.C\n4.D\n5.E\n\nSelect your choice: ");
        int pickupSelection = taxiBooking.nextInt();
        char pickupLocation = characterAssigner(pickupSelection);
        System.out.print("Enter Destination:\n1.A\n2.B\n3.C\n4.D\n5.E\n\nSelect your choice: ");
        int dropSelection = taxiBooking.nextInt();
        char dropLocation = characterAssigner(dropSelection);
        System.out.print("\nEnter pickup time in 24hours format (1-24): ");
        int pickupTime = taxiBooking.nextInt();

        Taxi taxi = selectTaxi(pickupLocation); // Selects the taxi
        //Logic
        if (taxi == null) { System.out.println("No taxis around, try again later!"); }
        else { bookARide(taxi, customerID, pickupLocation, dropLocation, pickupTime);}

    }


    //2.PRINT TAXI EARNINGS:
    public void printTaxiEarnings () {
        boolean isRunning = true;
        while (isRunning) {
            //sorts the taxis by their earnings
            taxis.sort(Comparator.comparing(Taxi::getMoneyEarned).reversed());

            System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$ TAXI EARNINGS $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
            for (int i=0; i<taxis.size(); i++) {

                Taxi taxi = taxis.get(i);
                System.out.println("\n========================================================================= ");
                System.out.println("Taxi ID: "+ taxi.getTaxiID()+ "\nTotal Taxi Earnings: "+ taxi.getMoneyEarned()+"\nTaxi-"+taxi.getTaxiID()+"'s Rides:\n");
                System.out.println("-------------------------------------------------------------------------");
                for (int j=0; j<taxi.getRides().size(); j++){
                    Ride ride = taxi.getRides().get(j);
                    System.out.println("Ride-"+(j+1)+":");
                    System.out.println("Customer ID: "+ride.getCustomerID()+"  Booking No: "+ride.getBookingID()+"  Ride Amount:"+ride.getRideAmount()+"  Ride Distance: "+ride.getRideDistance()+"Kms");
                    System.out.println("Pickup Point | Drop Point | Pickup Time | Drop Time");
                    System.out.println("     "+ride.getPickupPoint()+"       |      "+ride.getDropPoint()+"     |       "+ride.getPickupTime()+"     |    "+ride.getDropTime());
                    System.out.println("-------------------------------------------------------------------------");
                }
            }
            isRunning = UI.backButton(isRunning);
        }

    }


    // Book a Taxi sub methods
    private char characterAssigner (int userSelection) {
        char character = 'a';
        switch (userSelection) {
            case 1: character = 'a'; break;
            case 2: character = 'b'; break;
            case 3: character = 'c'; break;
            case 4: character = 'd'; break;
            case 5: character = 'e'; break;
        }
        return character;
    }

    //Selects a taxi, otherwise returns null
    private Taxi selectTaxi (char pickupPoint) {
        //Sorts the list ranking the nearest taxi with lowest earnings at the top
        taxiSort(pickupPoint);

        // Loops through the sorted list to see if a taxi is available and returns
        // Even if a taxi has no earnings and is the closest to the pickupPoint, it has to be available ¯\_(ツ)_/¯
        // only returns a taxi if available other wise it returns the initial condition null
        // If null is returned there is no taxi available and the booking can be rejected.
        Taxi taxi = null;
        for (int i=0; i<taxis.size(); i++) {
            if (taxis.get(i).isAvailable()) {
                taxi = taxis.get(i);
                break;
            }
        }
        return taxi;
    }

    // Book a ride:
    private void bookARide (Taxi taxi, int customerID, char pickupLocation, char dropLocation, int pickupTime) {
        Ride ride = new Ride( taxi, customerID, pickupLocation, dropLocation, pickupTime);
        //Timer starts
        TaxiTimer timer = new TaxiTimer(taxi, ride);
        timer.start();
        System.out.println("\nTaxi "+taxi.getTaxiID()+" is on its way and will reach you in "+taxi.getDistanceFromPickupPoint()+" hour(s).\n\n" );
        ride.setBookingID(bookingID++);
        bookings.put(ride.getBookingID(), ride); // Adds the ride to the total bookings

        //Updating ride details to taxi
        taxi.setCurrentLocation(ride.getDropPoint()); // SETS THE DROP POINT AS TAXI'S CURRENT LOCATION.
        taxi.addRide(ride); // adds the ride to the respective taxi's ride.
        taxi.setMoneyEarned(taxi.getMoneyEarned()+ ride.getRideAmount()); // this is equivalent to +=
    }


    //Sorting the taxis in the list based on the distance and money earned.
    private void taxiSort(char pickupPoint) {
        //Calculates how far the taxis are located from the pick up point using a for each loop.

        for (Taxi taxi : taxis) {
            // will calculate it each and every time since
            // the current location of the taxi will keep changing after every ride
            taxi.setDistanceFromPickupPoint(Math.abs(taxi.getCurrentLocation() - pickupPoint));
        }

        // Sorting based on distance first, if the distance is same then sort based on
        taxis.sort(Comparator.comparing(Taxi::getDistanceFromPickupPoint).thenComparingDouble(Taxi::getMoneyEarned));
    }

}