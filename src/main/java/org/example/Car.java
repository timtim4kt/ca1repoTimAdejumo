package org.example;

public class Car extends Vehicle {

    private int noOfSeats;

    public Car(String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile,
               int year, int month, int day,
               int mileage, double latitude, double longitude,
               int noOfSeats)
    {
        super(type, make, model, milesPerKwH,
                registration, costPerMile,
                year, month, day,
                mileage, latitude, longitude);

        this.noOfSeats = noOfSeats;
    }



    public Car(int id, String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile,
               int year, int month, int day,
               int mileage, double latitude, double longitude,
               int noOfSeats)
    {
        super(id, type, make, model, milesPerKwH,
                registration, costPerMile,
                year, month, day,
                mileage, latitude, longitude);

        this.noOfSeats = noOfSeats;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    @Override
    public String toString() {
        return "Car{" +
                "noOfSeats=" + noOfSeats +
                "} " + super.toString();
    }
}



