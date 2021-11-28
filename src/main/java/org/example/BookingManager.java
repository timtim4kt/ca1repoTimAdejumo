package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BookingManager
{
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;

    // Constructor
    public BookingManager() {
        this.bookingList = new ArrayList<>();
    }

    public void displayAllBookings() {
        for (Booking b : bookingList)
            System.out.println(b.toString());
    }

    public void addBooking(int bookingId, int passengerId, int vehicleId,
                           LocalDateTime bookingDateTime,
                           LocationGPS startLocation, LocationGPS endLocation,
                           double cost)
    {
        boolean found = false;
        Booking i = new Booking(bookingId,passengerId,vehicleId,bookingDateTime,startLocation,endLocation,cost);
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
        }
        else
        {
            System.out.println("Booking already exists");
        }

    }


    //TODO implement functionality as per specification


}
