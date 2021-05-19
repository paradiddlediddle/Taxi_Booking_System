package TheProject;

import java.util.Scanner;

public class UI {

public void mainScreen () {
    //Booking needs to be outside the loop
    Booking booking = new Booking();
    boolean isRunning = true;
    int userSelection;

    while (isRunning) {
        System.out.println("\n\n%%%%%%%%%% UBER 2.0 %%%%%%%%%%%%\n");
        System.out.println("1. Book a taxi");
        System.out.println("2. View taxi earnings");
        System.out.println("3. Exit\n");
        System.out.print("Enter your choice: ");
        Scanner mainScreenScan = new Scanner(System.in);
        userSelection = mainScreenScan.nextInt();

        switch (userSelection) {

            case 1: booking.bookATaxi(); break;

            case 2: booking.printTaxiEarnings(); break;

            case 3: isRunning = false; break;

        }
    }
}

public static boolean backButton (boolean isRunning) {

Scanner backButtonScan = new Scanner(System.in);
System.out.println("\nHit any key to go back to main menu!");
String userEntry = backButtonScan.nextLine();

if (!userEntry.isEmpty()) {
    isRunning = false;
}
return isRunning;
}


}
