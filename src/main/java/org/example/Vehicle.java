package org.example;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Vehicle
{
    private IdGenerator idGenerator = IdGenerator.getInstance("next-id-store.txt");  // get access to the id Generator

    private int id;
    private String type;    // type of vehicle "Truck","Van","4x4", "Car" ...
    private String make;
    private String model;
    private double milesPerKwH;
    private String registration;
    private double costPerMile;
    private LocalDate lastServicedDate;
    private int mileage; // mileage recorded at last service
    private double latitude;
    private double longitude;

    // Constructor called when a new Vehicle is being created.
    // No vehicle id is passed in as an argument,
    // so the constructor will autogenerate the id.
    //
    public Vehicle(String type, String make, String model, double milesPerKwH,
                   String registration, double costPerMile,
                   int year, int month, int day,
                   int mileage, double latitude, double longitude)
    {
        this.id = idGenerator.getNextId();  // auto generated id (new for each run of the system)
        this.type = type;
        this.make = make;
        this.model = model;
        this.milesPerKwH = milesPerKwH;
        this.registration = registration;
        this.costPerMile = costPerMile;
        this.lastServicedDate = LocalDate.of(year, month,day);
        this.mileage = mileage;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Constructor to create a Vehicle object, when the id is available.
    // So this is called to construct a Vehicle when the vehicle record is read from
    // the vehicles.txt file, and the id is known.
    //
    public Vehicle(int id, String type, String make, String model, double milesPerKwH,
                   String registration, double costPerMile,
                   int year, int month, int day,
                   int mileage, double latitude, double longitude)
    {
        this.id = id;
        this.type = type;
        this.make = make;
        this.model = model;
        this.milesPerKwH = milesPerKwH;
        this.registration = registration;
        this.costPerMile = costPerMile;
        this.lastServicedDate = LocalDate.of(year, month,day);
        this.mileage = mileage;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }
    private void setId() {}; // prevents the id from being set (as it should only come from autogenerator)

    public String getMake()
    {
        return make;
    }
    public void setMake(String make)
    {
        this.make = make;
    }
    public String getModel()
    {
        return model;
    }
    public void setModel(String model)
    {
        this.model = model;
    }
    public double getMilesPerKm()
    {
        return milesPerKwH;
    }
    public void setMilesPerKm(double milesPerKm)
    {
        this.milesPerKwH = milesPerKm;
    }
    public String getRegistration()
    {
        return registration;
    }
    public void setRegistration(String registration)
    {
        this.registration = registration;
    }
    public double getCostPerMile()
    {
        return costPerMile;
    }
    public void setCostPerMile(double costPerMile)
    {
        this.costPerMile = costPerMile;
    }
    public LocalDate getLastServicedDate() { return lastServicedDate; }
    public void setLastServicedDate(LocalDate lastServicedDate) { this.lastServicedDate = lastServicedDate; }
    public int getMileage()
    {
        return mileage;
    }
    public void setMileage(int mileage)
    {
        this.mileage = mileage;
    }
    public void setDepotGPSLocation(double latitude, double longitude) {
        new LocationGPS(latitude,longitude);
    }
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return  "ID:"+ this.id + "\n" + "==================================================================" + "\n"  + "type:"+type+ "\nmake=" + make + "\nmodel=" + model
                + "\nmilesPerKm=" + milesPerKwH +  "\nregistration=" + registration
                + "\ncostPerMile=" + costPerMile + "\nlastServicedDate="
                + lastServicedDate + "\nmileage=" + mileage + "\ndepotGPSLocation=" + "{" + "latitude="
                + latitude + "," + "longitude=" + longitude + "}"  + "\n" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return registration.equals(vehicle.registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registration);
    }
}
