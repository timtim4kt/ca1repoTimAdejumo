package org.example;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/** Timothy Adejumo
 * This Vehicle Bookings Management Systems manages the booking of Vehicles
 * by Passengers.
 *
 * This program reads from 3 text files:
 * "vehicles.txt", "passengers.txt", and "next-id-store.txt"
 * You should be able to see them in the project pane.
 * You will create "bookings.txt" at a later stage, to store booking records.
 *
 * "next-id-store.txt" contains one number ("201"), which will be the
 * next auto-generated id to be used to when new vehicles, passengers, or
 * bookings are created.  The value in the file will be updated when new objects
 * are created - but not when objects are recreated from records in
 * the files - as they already have IDs.  Dont change it - it will be updated by
 * the IdGenerator class.
 */

public class App {
    // Components (objects) used in this App
    PassengerStore passengerStore;  // encapsulates access to list of Passengers
    VehicleManager vehicleManager;  // encapsulates access to list of Vehicles
    BookingManager bookingManager;  // deals with all bookings
    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    public void start() {
        // create PassengerStore and load all passenger records from text file
        passengerStore = new PassengerStore("passengers.txt");
        // create VehicleManager, and load all vehicles from text file
        vehicleManager = new VehicleManager("vehicles.txt");

        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }



        // create PassengerStore and load it with passenger records from text file



    }


        // Create BookingManager and load all bookings from file
        // bookingManager = new BookingManager("bookings.txt");

        //pMgr.saveToFile();



    private void displayMainMenu() throws IOException {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Passengers\n"
                + "2. Vehicles\n"
                + "3. Bookings\n"
                + "4. Exit\n"
                + "Enter Option [1,4]: ";

        final int PASSENGERS = 1;
        final int VEHICLES = 2;
        final int BOOKINGS = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println("\n"+MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case PASSENGERS:
                        System.out.println("Passengers option chosen");
                        displayPassengerMenu();
                        break;
                    case VEHICLES:
                        System.out.println("Vehicles option chosen");
                        displayVehiclesMenu();
                        break;
                    case BOOKINGS:
                        System.out.println("Bookings option chosen");
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException |NumberFormatException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }

    // Sub-Menu for Passenger operations
    //
    private void displayPassengerMenu() {
        final String MENU_ITEMS = "\n*** PASSENGER MENU ***\n"
                + "1. Show all Passengers\n"
                + "2. Find Passenger by Name\n"
                + "3. Add new Passenger\n"
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int SHOW_ALL = 1;
        final int FIND_BY_NAME = 2;
        final int ADD_PASSENGER = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println("\n"+MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case SHOW_ALL:
                        System.out.println("All Passengers");
                        System.out.println("==============");
                        passengerStore.displayAllPassengers();
                        break;
                    case FIND_BY_NAME:
                        System.out.println("Find Passenger by Name");
                        System.out.println("======================");
                        System.out.print("Enter passenger name: ");
                        String name = keyboard.nextLine();
                        Passenger p = passengerStore.findPassengerByName(name);
                        if(p==null)
                            System.out.println("No passenger matching the name \"" + name +"\"");
                        else
                            System.out.print("Found passenger: " + p.toString());
                        break;
                    case ADD_PASSENGER:
                        System.out.println("Add Passenger");
                        System.out.println("=============");
                        System.out.print("Enter passenger name: ");
                        String newName = keyboard.nextLine();
                        System.out.print("Enter passenger email: ");
                        String newEmail = keyboard.nextLine();
                        System.out.print("Enter passenger phone: ");
                        String newPhone = keyboard.nextLine();
                        System.out.print("Enter passenger latitude: ");
                        Double newLatitude = keyboard.nextDouble();
                        System.out.print("Enter passenger longitude: ");
                        Double newLongitude = keyboard.nextDouble();
                        passengerStore.addPassenger(newName,newEmail,newPhone,newLatitude,newLongitude);
                        System.out.println();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException |NumberFormatException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }

    private void displayVehiclesMenu() {
        final String MENU_ITEMS = "\n*** VEHICLE MENU ***\n"
                + "1. Show all Vehicles\n"
                + "2. Find vehicle by Registration\n"
                + "3. List vehicles by Type\n"
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int SHOW_ALL = 1;
        final int FIND_BY_REG = 2;
        final int LIST_BY_TYPE = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println("\n"+MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case SHOW_ALL:
                        System.out.println("All Vehicles");
                        System.out.println("============");
                        vehicleManager.displayAllVehicles();
                        break;
                    case FIND_BY_REG:
                        System.out.println("Find Vehicle by Registration");
                        System.out.println("============================");
                        System.out.print("Enter vehicle registration: ");
                        String reg = keyboard.nextLine();
                        Vehicle v = vehicleManager.findVehicleByRegistrationNumber(reg);
                        if(v==null)
                            System.out.println("No vehicle matching the registration \"" + reg +"\"");
                        else
                            System.out.print("Found vehicle: " + v.toString());
                        break;
                    case LIST_BY_TYPE:
                        ArrayList<Vehicle> list = new ArrayList<>();
                        VehicleRegComparator regComparator = new VehicleRegComparator();
                        System.out.println("List of vehicles by type");
                        System.out.print("Enter vehicle type (Car, 4x4, Truck or Van): ");
                        String type = keyboard.nextLine();
                        if(!(type.equalsIgnoreCase("car") || type.equalsIgnoreCase("4x4") || type.equalsIgnoreCase("truck") || type.equalsIgnoreCase("van")))
                        {
                            System.out.println("Type " + type + " does not exist");
                        }
                        else {
                            vehicleManager.listByType(type, list);
                            Collections.sort(list, regComparator);
                            System.out.println("List of " + type + "'s sorted by registration");
                            System.out.println("====================================");
                            for (Vehicle x : list) {
                                System.out.println(x.toString());
                            }
                        }
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException |NumberFormatException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }



}


