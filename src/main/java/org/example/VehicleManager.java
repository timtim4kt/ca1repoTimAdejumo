package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VehicleManager {
    private final ArrayList<Vehicle> vehicleList;  // for Car and Van objects
    private VehicleManager vehicleManager;

    public VehicleManager(String fileName, VehicleManager vehicleManager) {
        this.vehicleList = new ArrayList<>();
        this.vehicleManager = vehicleManager;
        loadVehiclesFromFile(fileName);
    }

    public void displayAllVehicles() {
        for (Vehicle v : vehicleList)
            System.out.println(v.toString());
    }

    public void deleteById(int vehicleId){
        boolean found = false;
        Vehicle v = findVehicleByVehicleID(vehicleId);
        if(v!=null)
        {
            vehicleList.remove(v);
            System.out.println("Vehicle deleted");
        }
        else
        {
            System.out.println("Vehicle doesn't exist");
        }
    }

    public void loadVehiclesFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String type = sc.next();  // vehicle type
                String make = sc.next();
                String model = sc.next();
                double milesPerKwH = sc.nextDouble();
                String registration = sc.next();
                double costPerMile = sc.nextDouble();
                int year = sc.nextInt();   // last service date
                int month = sc.nextInt();
                int day = sc.nextInt();
                int mileage = sc.nextInt();
                double latitude = sc.nextDouble();  // Depot GPS location
                double longitude = sc.nextDouble();
                double loadSpace = 0.0;
                int noOfSeats = 0;
                if (type.equalsIgnoreCase("Van") || type.equalsIgnoreCase("Truck")) {
                    loadSpace = sc.nextDouble();
                }
                else if(type.equalsIgnoreCase("Car") || type.equalsIgnoreCase("4x4")) {
                    noOfSeats = sc.nextInt();
                }
                if (type.equalsIgnoreCase("Van") ||
                        type.equalsIgnoreCase("Truck")) {

                    // construct a Van object and add it to the passenger list
                    vehicleList.add(new Van(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            loadSpace));
                }
                else if(type.equalsIgnoreCase("Car") || type.equalsIgnoreCase("4x4"))
                {
                    vehicleList.add(new Car(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            noOfSeats));
                }



            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    //TODO add more functionality as per spec.

    public Vehicle findVehicleByRegistrationNumber(String registration)
    {
        for(Vehicle v : vehicleList){
            if(v.getRegistration().equalsIgnoreCase(registration))
            {
               return v;
            }
        }
        return null;
    }

    public Vehicle findVehicleByVehicleID(int id)
    {
        for(Vehicle v : vehicleList){
            if(v.getId() == (id))
            {
                return v;
            }
        }
        return null;
    }

    public void listByType(String type,ArrayList<Vehicle> list) {

        for(Vehicle v: vehicleList )
        {
            if(v.getType().equalsIgnoreCase(type))
            {
                list.add(v);
            }
        }

    }

    public void addCar(String type,String make,String model,double milesPerKwH,
                             String registration,double costPerMile,
                             int year,int month,int day,
                             int mileage,double latitude,double longitude,
                             int noOfSeats)
    {
        boolean found = false;
        Car i = new Car(type, make, model, milesPerKwH,
                registration, costPerMile,
                year, month, day,
                mileage, latitude, longitude,
                noOfSeats);

        for(Vehicle v: vehicleList)
        {
            if(v.equals(i))
            {
                found = true;
                break;
            }

        }
        if(found == false)
        {
            vehicleList.add(i);
            System.out.print("Vehicle added" +"\n" + "==============" + "\n" + i.toString());
        }
        else
        {
            System.out.println("Vehicle already exists");
        }
    }

    public void addVan(String type,String make,String model,double milesPerKwH,
                       String registration,double costPerMile,
                       int year,int month,int day,
                       int mileage,double latitude,double longitude,
                       double loadspace)
    {
        boolean found = false;
        Van i = new Van(type, make, model, milesPerKwH,
                registration, costPerMile,
                year, month, day,
                mileage, latitude, longitude,
                loadspace);
        for(Vehicle v: vehicleList)
        {
            if(v.equals(i))
            {
                found = true;
                break;
            }


        }
        if(found == false)
        {
            vehicleList.add(i);
            System.out.print("Vehicle added" +"\n" + "==============" + "\n" + i.toString() );
        }
        else
        {
            System.out.println("Vehicle already exists");
        }
    }

    public void displayVehiclesMenu() {
        final String MENU_ITEMS = "\n*** VEHICLE MENU ***\n"
                + "1. Show all Vehicles\n"
                + "2. Find vehicle by Registration\n"
                + "3. List vehicles by Type\n"
                + "4. Add vehicles\n"
                + "5. Delete vehicle\n"
                + "6. Exit\n"
                + "Enter Option [1,6]";

        final int SHOW_ALL = 1;
        final int FIND_BY_REG = 2;
        final int LIST_BY_TYPE = 3;
        final int ADD_VEHICLE = 4;
        final int DELETE_VEHICLE = 5;
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
                        System.out.println("All Vehicles");
                        System.out.println("============");
                        displayAllVehicles();
                        break;
                    case FIND_BY_REG:
                        System.out.println("Find Vehicle by Registration");
                        System.out.println("============================");
                        System.out.print("Enter vehicle registration: ");
                        String reg = keyboard.nextLine();
                        Vehicle v = findVehicleByRegistrationNumber(reg);
                        if(v==null)
                            System.out.println("No vehicle matching the registration \"" + reg +"\"");
                        else
                            System.out.print("Found vehicle" + "\n" + "============="+ "\n" + v.toString());
                        break;
                    case ADD_VEHICLE:
                        System.out.println("Add Vehicle");
                        System.out.println("===========");
                        System.out.print("Enter vehicle type: ");
                        String newType = keyboard.nextLine();
                        System.out.print("Enter vehicle make: ");
                        String newMake = keyboard.nextLine();
                        System.out.print("Enter vehicle model: ");
                        String newModel = keyboard.nextLine();
                        System.out.print("Enter vehicle milesPerKwH: ");
                        Double newMilesPerKwH = keyboard.nextDouble();
                        keyboard.nextLine();
                        System.out.print("Enter vehicle registration: ");
                        String newRegistration = keyboard.nextLine();
                        System.out.print("Enter vehicle costPerMile: ");
                        Double newCostPerMile = keyboard.nextDouble();
                        System.out.print("Enter vehicle year: ");
                        int newYear = keyboard.nextInt();
                        System.out.print("Enter vehicle month: ");
                        int newMonth = keyboard.nextInt();
                        System.out.print("Enter vehicle day: ");
                        int newDay = keyboard.nextInt();
                        System.out.print("Enter vehicle mileage: ");
                        int newMileage = keyboard.nextInt();
                        System.out.print("Enter vehicle latitude: ");
                        Double newLatitude = keyboard.nextDouble();
                        System.out.print("Enter vehicle longitude: ");
                        Double newLongitude = keyboard.nextDouble();
                        if(newType.equalsIgnoreCase("van") || newType.equalsIgnoreCase("truck")){
                            System.out.print("Enter vehicle loadspace: ");
                            Double newLoadSpace = keyboard.nextDouble();
                            addVan(newType,newMake,newModel,newMilesPerKwH,newRegistration,newCostPerMile,newYear,newMonth,newDay,newMileage,newLatitude,newLongitude,newLoadSpace);
                            keyboard.nextLine();
                        }
                        else if(newType.equalsIgnoreCase("car") || newType.equalsIgnoreCase("4x4")){
                            System.out.print("Enter vehicle number of seats: ");
                            int newNoOfSeats = keyboard.nextInt();
                            addCar(newType,newMake,newModel,newMilesPerKwH,newRegistration,newCostPerMile,newYear,newMonth,newDay,newMileage,newLatitude,newLongitude,newNoOfSeats);
                            keyboard.nextLine();
                        }
                        else
                        {
                            System.out.println("Invalid type");
                        }
                        break;
                    case LIST_BY_TYPE:
                        ArrayList<Vehicle> list = new ArrayList<>();
                        VehicleRegComparator regComparator = new VehicleRegComparator();
                        System.out.println("List of vehicles by type");
                        System.out.println("========================");
                        System.out.print("Enter vehicle type (Car, 4x4, Truck or Van): ");
                        String type = keyboard.nextLine();
                        if(!(type.equalsIgnoreCase("car") || type.equalsIgnoreCase("4x4") || type.equalsIgnoreCase("truck") || type.equalsIgnoreCase("van")))
                        {
                            System.out.println("Type " + type + " does not exist");
                        }
                        else {
                            listByType(type, list);
                            Collections.sort(list, regComparator);
                            System.out.println("List of " + type + "'s sorted by registration");
                            System.out.println("====================================");
                            for (Vehicle x : list) {
                                System.out.println(x.toString());
                            }
                        }
                        break;
                    case DELETE_VEHICLE:
                        System.out.println("Delete Vehicle");
                        System.out.println("================");
                        System.out.print("Enter Vehicle ID: ");
                        int vId = keyboard.nextInt();
                        keyboard.nextLine();
                        deleteById(vId);
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
