package org.example;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookingManager
{
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;
    // Constructor
    public BookingManager(String filename, PassengerStore passengerStore, VehicleManager vehicleManager) {
        this.bookingList = new ArrayList<>();
        this.passengerStore = passengerStore;
        this.vehicleManager = vehicleManager;
        loadBookingDataFromFile(filename);
    }

    public void displayAllBookings() {
        for (Booking b : bookingList)
            System.out.println(b.toString());
    }

    public void deleteById(int bookingId){
        boolean found = false;
        Booking b = findBookingByID(bookingId);
        if(b!=null)
        {
            bookingList.remove(b);
            System.out.println("Booking deleted");
        }
        else
        {
            System.out.println("Booking doesn't exist");
        }
    }

    public void displayCurrentBookings() {
        for(Booking b: bookingList)
        {
            LocalDateTime now = LocalDateTime.now();
            if(b.getBookingDateTime().isAfter(now))
            {
                System.out.println(b.toString());
            }
        }
    }

    public void displayBookingsMenu() {
        final String MENU_ITEMS = "\n*** BOOKING MENU ***\n"
                + "1. Show all Bookings\n"
                + "2. Make Booking\n"
                + "3. Show Current Bookings\n"
                + "4. Show Bookings by PassengerID\n"
                + "5. Show Bookings by Passenger Name\n"
                + "6. Show Average Journey Length\n"
                + "7. Delete Booking\n"
                + "8. Edit Booking\n"
                + "9. Exit\n"
                + "Enter Option [1,9]";

        final int SHOW_ALL = 1;
        final int MAKE_BOOKING = 2;
        final int SHOW_CURRENT_BOOKINGS = 3;
        final int SHOW_BOOKINGS_BY_PASSENGER = 4;
        final int SHOW_BOOKINGS_BY_PASSENGER_NAME = 5;
        final int AVERAGE_LENGTH_JOURNEYS = 6;
        final int DELETE_BOOKING = 7;
        final int EDIT_BOOKING = 8;
        final int EXIT = 9;

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
                        System.out.println("All Bookings");
                        System.out.println("==============");
                        displayAllBookings();
                        break;
                    case MAKE_BOOKING:
                        System.out.println("Make a Booking");
                        System.out.println("==============");
                        System.out.print("Enter Passenger ID: ");
                        int passengerId = keyboard.nextInt();
                        System.out.print("Enter Vehicle ID: ");
                        int vehicleId = keyboard.nextInt();
                        System.out.print("Enter Booking Year: ");
                        int year = keyboard.nextInt();
                        System.out.print("Enter Booking Month: ");
                        int month = keyboard.nextInt();
                        System.out.print("Enter Booking Date: ");
                        int day = keyboard.nextInt();
                        System.out.print("Enter Booking Hour: ");
                        int hour = keyboard.nextInt();
                        System.out.print("Enter Booking Minute: ");
                        int minute = keyboard.nextInt();
                        System.out.print("Enter Start Latitude: ");
                        double startLatitude = keyboard.nextDouble();
                        System.out.print("Enter Start Longitude: ");
                        double startLongitude = keyboard.nextDouble();
                        System.out.print("Enter End Latitude: ");
                        double endLatitude = keyboard.nextDouble();
                        System.out.print("Enter End Longitude: ");
                        double endLongitude = keyboard.nextDouble();
                        Vehicle i = vehicleManager.findVehicleByVehicleID(vehicleId);
                        double d1 = distance(i.getLatitude(),i.getLongitude(),startLatitude,startLongitude);
                        double d2 = distance(startLatitude,startLongitude,endLatitude,endLongitude);
                        double d3 = distance(endLatitude,endLongitude,i.getLatitude(),i.getLongitude());
                        double totalDistance = d1 + d2 + d3;
                        double cost = i.getCostPerMile() * totalDistance;

                        makeBooking(passengerId,vehicleId,year,month,day,hour,minute,startLatitude,startLongitude,endLatitude,endLongitude,cost);
                        keyboard.nextLine();
                        break;
                    case SHOW_CURRENT_BOOKINGS:
                        System.out.println("Current Bookings");
                        System.out.println("================");
                        displayCurrentBookings();
                        break;
                    case  SHOW_BOOKINGS_BY_PASSENGER:
                        ArrayList<Booking> list = new ArrayList<>();
                        BookingDateComparator dateComparator = new BookingDateComparator();
                        System.out.println("List of bookings by passenger");
                        System.out.println("=============================");
                        System.out.print("Enter passenger id: ");
                        int pId = keyboard.nextInt();
                        if(passengerStore.findPassengerByID(pId) == null)
                        {
                            System.out.println("Passenger doesnt exist");
                        }
                        else {
                            showBookingsByPassenger(pId, list);
                            Collections.sort(list, dateComparator);
                            System.out.println();
                            System.out.println("List of bookings by passenger " + pId + " sorted by date");
                            System.out.println("====================================");
                            for (Booking x : list) {
                                System.out.println(x.toString());
                            }
                            keyboard.nextLine();
                        }
                        break;
                    case  SHOW_BOOKINGS_BY_PASSENGER_NAME:
                        ArrayList<Booking> listByName = new ArrayList<>();
                        BookingDateComparator dateComparatorName = new BookingDateComparator();
                        System.out.println("List of bookings by passenger name");
                        System.out.println("=============================");
                        System.out.print("Enter passenger name: ");
                        String passengerName = keyboard.nextLine();
                        if(passengerStore.findPassengerByName(passengerName) == null)
                        {
                            System.out.println("Passenger doesnt exist");
                        }
                        else {
                            showBookingsByPassengerName(passengerName, listByName);
                            Collections.sort(listByName, dateComparatorName);
                            System.out.println();
                            System.out.println("List of bookings by passenger " + passengerName + " sorted by date");
                            System.out.println("========================================================");
                            for (Booking x : listByName) {
                                System.out.println(x.toString());
                            }
                        }
                        break;
                    case AVERAGE_LENGTH_JOURNEYS:
                        System.out.println("Average Length of Booking journeys");
                        System.out.println("==================================");
                        double AL = averageLength(bookingList);
                        System.out.println("Average length(start location --> end location) = " + AL + " miles");
                        break;
                    case DELETE_BOOKING:
                        System.out.println("Delete Booking");
                        System.out.println("================");
                        System.out.print("Enter Booking ID: ");
                        int bId = keyboard.nextInt();

                        if(findBookingByID(bId) == null){
                            System.out.println("Booking doesnt't exist");
                            keyboard.nextLine();
                        }
                        else
                        {
                            keyboard.nextLine();
                            deleteById(bId);
                        }

                        break;
                    case EDIT_BOOKING:
                        System.out.println("Edit Booking");
                        System.out.println("============");
                        System.out.print("Enter Booking ID: ");
                        int bId2 = keyboard.nextInt();
                        if(findBookingByID(bId2) == null)
                        {
                            System.out.println("Booking doesn't exist");
                            keyboard.nextLine();
                        }
                        else {
                            System.out.print("Enter new(or unchanged) PassengerID: ");
                            int newPassengerId = keyboard.nextInt();
                            System.out.print("Enter new(or unchanged) VehicleID: ");
                            int newVehicleId = keyboard.nextInt();
                            System.out.print("Enter new(or unchanged) Year: ");
                            int newYear = keyboard.nextInt();
                            System.out.print("Enter new(or unchanged) Month: ");
                            int newMonth = keyboard.nextInt();
                            System.out.print("Enter new(or unchanged) Day: ");
                            int newDay = keyboard.nextInt();
                            System.out.print("Enter new(or unchanged) Hour: ");
                            int newHour = keyboard.nextInt();
                            System.out.print("Enter new(or unchanged) Minute: ");
                            int newMinute = keyboard.nextInt();
                            System.out.print("Enter new(or unchanged) start latitude: ");
                            double newStartLatitude = keyboard.nextDouble();
                            System.out.print("Enter new(or unchanged) start longitude: ");
                            double newStartLongitude = keyboard.nextDouble();
                            System.out.print("Enter new(or unchanged) end latitude: ");
                            double newEndLatitude = keyboard.nextDouble();
                            System.out.print("Enter new(or unchanged) end longitude: ");
                            double newEndLongitude = keyboard.nextDouble();
                            Vehicle newI = vehicleManager.findVehicleByVehicleID(newVehicleId);
                            double newD1 = distance(newI.getLatitude(),newI.getLongitude(),newStartLatitude,newStartLongitude);
                            double newD2 = distance(newStartLatitude,newStartLongitude,newEndLatitude,newEndLongitude);
                            double newD3 = distance(newEndLatitude,newEndLongitude,newI.getLatitude(),newI.getLongitude());
                            double newTotalDistance = newD1 + newD2 + newD3;
                            double newCost = newI.getCostPerMile() * newTotalDistance;
                            editBooking(bId2,newPassengerId,newVehicleId,newYear,newMonth,newDay,newHour,newMinute,newStartLatitude,newStartLongitude,newEndLatitude,newEndLongitude,newCost);
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

            } catch (InputMismatchException | NumberFormatException  e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }

    public double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public void makeBooking(int passengerId, int vehicleId,
                            int year, int month, int day, int hour, int minute,
                            double startLatitude, double startLongitude,
                            double endLatitude, double endLongitude,double cost)
    {

        boolean found = false;

        Passenger x = passengerStore.findPassengerByID(passengerId);
        Vehicle z = vehicleManager.findVehicleByVehicleID(vehicleId);

        if(!(x==null))
        {
            if(!(z==null))
            {
                Booking i = new Booking(passengerId, vehicleId,
                        year, month, day, hour, minute,
                        startLatitude, startLongitude,
                        endLatitude, endLongitude, cost);

                Passenger p = passengerStore.findPassengerByID(passengerId);
                String name = p.getName();
                LocalDateTime time = LocalDateTime.of(year,month,day,hour,minute);
                Vehicle v = vehicleManager.findVehicleByVehicleID(vehicleId);

                for(Booking b: bookingList)
                {
                    if(b.equals(i))
                    {
                        found = true;
                        break;
                    }


                }
                if(found == false)
                {
                    bookingList.add(i);
                    System.out.println("Booking added:" + i);
                    System.out.println("*CONFIRMATION EMAIL*");
                    System.out.println("====================");
                    System.out.println("Dear " + name + "," + "\n"
                            + "This is an email to remind about the booking you have made." + "\n"
                            + "Your Details" + "\n"
                            + "============" + "\n"
                            + "Booking Date and Time: " + time + "\n"
                            + "Vehicle: " + v.toString() + "\n"
                            + "Booking Cost:" + cost +"\n"
                    );
                }
                else
                {
                    System.out.println("Booking already exists");
                }
            }
            else {
                System.out.println("Vehicle doesnt exist");
            }

        }
        else
        {
            System.out.println("Passenger doesnt exist");
        }


    }

    public void showBookingsByPassenger(int passengerId,ArrayList<Booking> list){
        Passenger x = passengerStore.findPassengerByID(passengerId);

        if(x!=null)
        {
            for(Booking b: bookingList )
            {
                if(b.getPassengerId() == passengerId)
                {
                    list.add(b);
                }
            }

        }



    }

    public void showBookingsByPassengerName(String passengerName, ArrayList<Booking> list){
        Passenger x = passengerStore.findPassengerByName(passengerName);
        int id = x.getId();

        if(x!=null)
        {
            for(Booking b: bookingList )
            {
                if(b.getPassengerId() == id)
                {
                    list.add(b);
                }
            }

        }



    }

    public Booking findBookingByID(int id) {
        for(Booking b : bookingList){
            if(b.getBookingId() == id)
            {
                return b;
            }
        }
        return null;
    }

    public Booking findBookingByDateTime(LocalDateTime time) {
        for(Booking b : bookingList){
            if(b.getBookingDateTime() == time)
            {
                return b;
            }
        }
        return null;
    }

    public double averageLength(ArrayList<Booking> bookingList)
    {
        int count = 0;
        double totalLength = 0.0;
        for(Booking b: bookingList)
        {
            count++;
            totalLength += distance(b.getStartLatitude(),b.getStartLatitude(),b.getEndLatitude(),b.getEndLongitude());
        }
        double averageLength = totalLength/(double)count;

        return averageLength;
    }

    public void editBooking(int bookingId, int newPassengerId, int newVehicleId,
                            int newYear, int newMonth, int newDay, int newHour, int NewMinute,
                            double newStartLatitude, double newStartLongitude,
                            double newEndLatitude, double newEndLongitude,double newCost)
    {
        Booking b = findBookingByID(bookingId);
        b.setPassengerId(newPassengerId);
        b.setVehicleId(newVehicleId);
        b.setBookingDateTime(LocalDateTime.of(newYear,newMonth,newDay,newHour,NewMinute));
        b.setStartLatitude(newStartLatitude);
        b.setStartLongitude(newStartLongitude);
        b.setEndLatitude(newEndLatitude);
        b.setEndLongitude(newEndLongitude);
        b.setCost(newCost);

        System.out.println("Updated Booking: " + b.toString());
    }




    private void loadBookingDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int bookingId = sc.nextInt();
                int passengerId = sc.nextInt();
                int vehicleId = sc.nextInt();
                int year = sc.nextInt();   // last service date
                int month = sc.nextInt();
                int day = sc.nextInt();
                int hour = sc.nextInt();
                int minute = sc.nextInt();
                double startLatitude = sc.nextDouble();
                double startLongitude = sc.nextDouble();
                double endLatitude = sc.nextDouble();
                double endLongitude = sc.nextDouble();
                double cost = sc.nextDouble();

                bookingList.add(new Booking(bookingId, passengerId, vehicleId,
                        year, month, day, hour, minute,
                        startLatitude, startLongitude, endLatitude, endLongitude,
                        cost));
            }
            sc.close();


        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    /*public void loadBookingDataToFile(String outputFileName) throws FileNotFoundException {
        File outputFile = new File(outputFileName);
        PrintWriter out = new PrintWriter(outputFileName);
        for(Booking b: bookingList)
        {
            out.print(b);
        }
    }*/




    //TODO implement functionality as per specification


}
