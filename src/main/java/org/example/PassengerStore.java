package org.example;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PassengerStore {

    private final ArrayList<Passenger> passengerList;
    private PassengerStore passengerStore;

    public PassengerStore(String fileName, PassengerStore passengerStore) {
        this.passengerList = new ArrayList<>();
        this.passengerStore = passengerStore;
        loadPassengerDataFromFile(fileName);
    }

    public List<Passenger> getAllPassengers() {
        return this.passengerList;
    }

    public void displayAllPassengers() {
        for (Passenger p : this.passengerList) {
            System.out.println(p.toString());
        }
    }

    public void deleteById(int passengerId){
        boolean found = false;
                Passenger p = findPassengerByID(passengerId);
                if(p!=null)
                {
                    passengerList.remove(p);
                    System.out.println("Passenger deleted");
                }
                else
                {
                    System.out.println("Passenger doesn't exist");
                }
    }

    public void editPassenger(int passengerId, String newName,String newEmail,String newPhone,
                              double newLatitude, double newLongitude)
    {
        Passenger p = findPassengerByID(passengerId);
        p.setName(newName);
        p.setEmail(newEmail);
        p.setPhone(newPhone);
        p.setLocation(newLatitude,newLongitude);

        System.out.println("Updated Passenger" + "\n" + "===============" + "\n" + p.toString());
    }

    public void addPassenger(String name,String email,String phone, double latitude, double longitude)
    {
        boolean found = false;
        Passenger i = new Passenger(name,email,phone,latitude,longitude);
        for(Passenger p: passengerList)
        {
            if(p.equals(i))
            {
                found = true;
                break;
            }


        }
        if(found == false)
        {
            passengerList.add(i);
            System.out.print("Passenger added" + "\n" + "===============" + "\n" + i.toString() );
        }
        else
        {
            System.out.println("Passenger already exists");
        }
    }

    /**
     * Read Passenger records from a text file and create and add Passenger
     * objects to the PassengerStore.
     */
    private void loadPassengerDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String name = sc.next();
                String email = sc.next();
                String phone = sc.next();
                double latitude = sc.nextDouble();
                double longitude = sc.nextDouble();

                // construct a Passenger object and add it to the passenger list
                passengerList.add(new Passenger(id, name, email, phone, latitude, longitude));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    public Passenger findPassengerByName(String name) {
        for(Passenger p : passengerList){
            if(p.getName().equalsIgnoreCase(name))
            {
                return p;
            }
        }
        return null;
    }

    public Passenger findPassengerByID(int id) {
        for(Passenger p : passengerList){
            if(p.getId() == id)
            {
                return p;
            }
        }
        return null;
    }

    public void displayPassengerMenu() {
        final String MENU_ITEMS = "\n*** PASSENGER MENU ***\n"
                + "1. Show all Passengers\n"
                + "2. Find Passenger by Name\n"
                + "3. Add new Passenger\n"
                + "4. Delete Passenger\n"
                + "5. Edit Passenger\n"
                + "6. Exit\n"
                + "Enter Option [1,6]";

        final int SHOW_ALL = 1;
        final int FIND_BY_NAME = 2;
        final int ADD_PASSENGER = 3;
        final int DELETE_PASSENGER = 4;
        final int EDIT_PASSENGER = 5;
        final int EXIT = 6;

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
                        displayAllPassengers();
                        break;
                    case FIND_BY_NAME:
                        System.out.println("Find Passenger by Name");
                        System.out.println("======================");
                        System.out.print("Enter passenger name: ");
                        String name = keyboard.nextLine();
                        Passenger p = findPassengerByName(name);
                        if(p==null) {

                            System.out.println("No passenger matching the name \"" + name + "\"");
                            keyboard.nextLine();
                        }
                        else
                    {

                        System.out.print("Found passenger " + "\n" + "===============" + "\n" + p.toString());
                        keyboard.nextLine();
                    }
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

                        addPassenger(newName,newEmail,newPhone,newLatitude,newLongitude);
                        keyboard.nextLine();
                        break;
                    case DELETE_PASSENGER:
                        System.out.println("Delete Passenger");
                        System.out.println("================");
                        System.out.print("Enter Passenger ID: ");

                        int pId = keyboard.nextInt();
                        keyboard.nextLine();
                        deleteById(pId);

                        break;
                    case EDIT_PASSENGER:
                        System.out.println("Edit Passenger Details");
                        System.out.println("======================");
                        System.out.print("Enter Passenger ID: ");

                        int pId2 = keyboard.nextInt();
                        keyboard.nextLine();
                        if(findPassengerByID(pId2) == null)
                        {
                            System.out.println("Passenger doesn't exist");
                            keyboard.nextLine();
                        }
                        else {
                            System.out.print("Enter new(or unchanged) name: ");
                            String editedName = keyboard.nextLine();
                            System.out.print("Enter new(or unchanged) email: ");
                            String editedEmail = keyboard.nextLine();
                            System.out.print("Enter new(or unchanged) phone: ");
                            String editedPhone = keyboard.nextLine();
                            System.out.print("Enter new(or unchanged) latitude: ");
                            double editedLatitude = keyboard.nextDouble();
                            System.out.print("Enter new(or unchanged) longitude: ");
                            double editedLongitude = keyboard.nextDouble();
                            editPassenger(pId2,editedName,editedEmail,editedPhone,editedLatitude,editedLongitude);
                            keyboard.nextLine();
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


    // TODO - see functional spec for details of code to add

} // end class
