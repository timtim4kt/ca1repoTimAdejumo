package org.example;

import java.util.Comparator;

public class VehicleRegComparator implements Comparator<Vehicle> {

    public int compare(Vehicle v1, Vehicle v2) {
        return v1.getRegistration().compareTo(v2.getRegistration());
    }
}
