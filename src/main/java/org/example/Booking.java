package org.example;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Objects;

class Booking
{
    private int bookingId;
    private int passengerId;
    private int vehicleId;
    private LocalDateTime bookingDateTime;
    private double startLatitude;
    private double startLongitude;
    private double endLatitude;
    private double endLongitude;
    private double cost;  //Calculated at booking time



    public Booking(int passengerId, int vehicleId,
                   int year, int month, int day, int hour, int minute,
                   double startLatitude, double startLongitude, double endLatitude, double endLongitude,
                   double cost)
    {
        this.bookingId = idGenerator.getNextId();;
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
        this.bookingDateTime = LocalDateTime.of(year,month,day,hour,minute);
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.cost = cost;
    }

    public Booking(int bookingId, int passengerId, int vehicleId,
                   int year, int month, int day, int hour, int minute,
                   double startLatitude, double startLongitude, double endLatitude, double endLongitude,
                   double cost)
    {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
        this.bookingDateTime = LocalDateTime.of(year,month,day,hour,minute);
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.cost = cost;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public double getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(double startLatitude) {
        this.startLatitude = startLatitude;
    }

    public double getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(double startLongitude) {
        this.startLongitude = startLongitude;
    }

    public double getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(double endLatitude) {
        this.endLatitude = endLatitude;
    }

    public double getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(double endLongitude) {
        this.endLongitude = endLongitude;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingId == booking.bookingId && passengerId == booking.passengerId && vehicleId == booking.vehicleId && bookingDateTime.equals(booking.bookingDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, passengerId, vehicleId, bookingDateTime);
    }

    private IdGenerator idGenerator = IdGenerator.getInstance("next-id-store.txt");


    @Override
    public String toString() {
        return "ID:" + bookingId + "\n" +
                "=====================================================" + "\n" +
                "passengerId=" + passengerId +
                "\nvehicleId=" + vehicleId +
                "\nbookingDateTime=" + bookingDateTime +
                "\nstartLocation={Latitude=" + startLatitude +",Longitude=" + startLongitude + "}" +
                "\nendLocation={Latitude=" + endLatitude +",Longitude=" + endLongitude + "}" +
                "\ncost=" + cost +
                "\n" + "=====================================================" + "\n";
    }
    //TODO - see specification

}
