package com.ps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    private void init(){
        dealership = DealershipFileManager.getDealership();
    }

    public UserInterface(){
        init();
        //checking file loadddddd
            if (dealership == null) {
                System.out.println("Failed to load dealership");
            }
        }


    public void display(){

        System.out.println("Welcome to the dealership program");

        int mainMenuCommand;

        do{
            System.out.println("1. Get by price");
            System.out.println("2. Get by make/model");
            System.out.println("3. Get by year");
            System.out.println("4. Get by color");
            System.out.println("5. Get by mileage");
            System.out.println("6. Get by type");
            System.out.println("7. Get all");
            System.out.println("8. Add vehicle");
            System.out.println("9. Remove vehicle");
            System.out.println("0. Exit");

            System.out.print("Command: ");
            mainMenuCommand = scanner.nextInt();
            scanner.nextLine();

            switch(mainMenuCommand){
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Command not found, try again");
            }
        } while(mainMenuCommand != 0);
    }


    private void processGetByPriceRequest(){
        // TODO: Ask the user for a starting price and ending price
        System.out.println("--------Display vehicles by price--------");
        System.out.print("Min: ");
        double min = scanner.nextDouble();

        System.out.print("Max: ");
        double max = scanner.nextDouble();

        // ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByPrice(startingPrice, endingPrice);
        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByPrice(min, max);

        // Display vehicles with for loop
        displayVehicles(filteredVehicles);
    }
    private void processGetByMakeModelRequest(){
        System.out.print("Enter Model/Make: ");
        String model = scanner.nextLine().trim();
        ArrayList<Vehicle> matches = new ArrayList<>();

        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getMake().equalsIgnoreCase(model) || vehicle.getModel().equalsIgnoreCase(model)) {
                matches.add(vehicle);
            }
        }
        displayVehicles(matches);
    }
    private void processGetByYearRequest(){
        System.out.println("Min year: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.println("Max year: ");
        int max = Integer.parseInt(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByYear(min,max));
    }
    private void processGetByColorRequest(){
        System.out.print("Color: ");
        String color = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByColor(color));
    }
    private void processGetByMileageRequest(){
        System.out.print("Min mileage: ");
        int min = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Max mileage: ");
        int max = Integer.parseInt(scanner.nextLine().trim());
        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }
    private void processGetByVehicleTypeRequest(){
        System.out.print("Type: ");
        String type = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByType(type));
    }
    private void processGetAllVehiclesRequest(){
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        System.out.println("---------Printing all vehicles-----------");
        displayVehicles(vehicles);
    }
    private void processAddVehicleRequest() {
        try {
            System.out.print("VIN: ");
            int vin = Integer.parseInt(scanner.nextLine());
            System.out.print("Year: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Make: ");
            String make = scanner.nextLine();
            System.out.print("Model: ");
            String model = scanner.nextLine();
            System.out.print("Type: ");
            String type = scanner.nextLine();
            System.out.print("Color: ");
            String color = scanner.nextLine();
            System.out.print("Odometer: ");
            int odometer = Integer.parseInt(scanner.nextLine());
            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
            dealership.addVehicle(vehicle);
            DealershipFileManager.saveDealership(dealership);
            System.out.println("Vehicle added.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("invalid input");
        }
    }
    private void processRemoveVehicleRequest(){
        System.out.print("Enter VIN to remove: ");
        int vin = Integer.parseInt(scanner.nextLine());
        dealership.removeVehicle(vin);
        DealershipFileManager.saveDealership(dealership);
        System.out.println("Vehicle removed if vin matched.");
    }

    public static void displayVehicles(ArrayList<Vehicle> vehicles){
        System.out.printf("%-8s %-6s %-10s %-12s %-10s %-10s %-10s %-10s%n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Mileage", "Price");
        System.out.println("-------------------------------------------------------------------------------------");
        for (Vehicle vehicle : vehicles) {
            System.out.printf("%-8d %-6d %-10s %-12s %-10s %-10s %-10d $%-10.2f%n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());
        }
    }

}