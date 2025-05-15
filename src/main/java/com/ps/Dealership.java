package com.ps;

import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private String Test;

    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public ArrayList<Vehicle> vehiclesByPrice(double min, double max){
        return null;
    }

    public ArrayList<Vehicle> vehiclesByMakeModel(String make, String model){
        return null;
    }

    public ArrayList<Vehicle> vehiclesByYear(int min, int max){
        return null;
    }

    public ArrayList<Vehicle> vehiclesByColor(String color){
        return null;
    }

    public ArrayList<Vehicle> getAllVehicles(){
        return inventory;
    }

    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }
    public void removeVehicle(int vin){
        for (int i = 0; i < inventory.size(); i++){
            Vehicle vehicle = inventory.get(i);
            if (vehicle.getVin() == vin){
                inventory.remove(i);
                break;
            }
        }
    }
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            double price = vehicle.getPrice();
            if (price >= min && price <= max){
                result.add(vehicle);
            }
        }
        return result;
    }
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getMake().equalsIgnoreCase(make)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            int year = vehicle.getYear();
            if (year >= minYear && year <= maxYear) {
                result.add(vehicle);
            }
        }
        return result;
    }
    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                result.add(vehicle);
            }
        }
        return result;
    }
    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            int miles = vehicle.getOdometer();
            if (miles >= min && miles <= max) {
                result.add(vehicle);
            }
        }
        return result;
    }
    public ArrayList<Vehicle> getVehiclesByType(String type) {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(type)) {
                result.add(vehicle);
            }
        }
        return result;
    }




// TODO
//    get VehiclesByMileage(min, max) : List<Vehicle>
//    get VehiclesByType(vehicleType) : List<Vehicle>
//    remove Vehicle(vehicle)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
