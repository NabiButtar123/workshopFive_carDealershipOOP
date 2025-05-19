package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    //SALE EXAMPLE
    //SALE|20210928|Dana Wyatt|dana@texas.com|10112|1993|Ford|
    // Explorer|SUV|Red|525123|995.00|49.75|100.00|295.00|1439.75|NO|0.00
    //apprach - if statement - read the  first line if is sale do this, if its lease do that
    //which is to write it in the file in a different way append it into the csv
    //total price, write indv then print
    public void saveContract(Contract contract) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("contracts.csv", true))) {
                Vehicle vehicle = contract.getVehicleSold();
                String outputLine = "";

                if (contract instanceof SalesContract sales) {

                    double price = vehicle.getPrice();
                    double tax = price * 0.05;
                    double recordingFee = 100;
                    double processingFee;
                    if (price < 10000) {
                        processingFee = 295;
                    } else {
                        processingFee = 495;
                    }

                    String financeOption;
                    if (sales.isFinance()) {
                        financeOption = "YES";
                    } else {
                        financeOption = "NO";
                    }
                    //didnt use getters for more understanding of whats going on behind the scene

                    outputLine = "SALE|" +
                                    contract.getDate() + "|" +
                                    contract.getCustomerName() + "|" +
                                    contract.getCustomerEmail() + "|" +
                                    vehicle.getVin() + "|" +
                                    vehicle.getYear() + "|" +
                                    vehicle.getMake() + "|" +
                                    vehicle.getModel() + "|" +
                                    vehicle.getVehicleType() + "|" +
                                    vehicle.getColor() + "|" +
                                    vehicle.getOdometer() + "|" +
                                    price + "|" +
                                    String.format("%.2f", tax) + "|" +
                                    String.format("%.2f", recordingFee) + "|" +
                                    String.format("%.2f", processingFee) + "|" +
                                    String.format("%.2f", sales.getTotalPrice()) + "|" +
                                    financeOption + "|" +
                                    String.format("%.2f", sales.getMonthlyPayment());
                }

                else if (contract instanceof LeaseContract lease) {

                    //didnt use getters for more understanding of whats going on behind the scene
                    double price = vehicle.getPrice();
                    double endingValue = price * 0.50;
                    double leaseFee = price * 0.07;

                    outputLine =
                            "LEASE|" +
                                    contract.getDate() + "|" +
                                    contract.getCustomerName() + "|" +
                                    contract.getCustomerEmail() + "|" +
                                    vehicle.getVin() + "|" +
                                    vehicle.getYear() + "|" +
                                    vehicle.getMake() + "|" +
                                    vehicle.getModel() + "|" +
                                    vehicle.getVehicleType() + "|" +
                                    vehicle.getColor() + "|" +
                                    vehicle.getOdometer() + "|" +
                                    price + "|" +
                                    String.format("%.2f", endingValue) + "|" +
                                    String.format("%.2f", leaseFee) + "|" +
                                    String.format("%.2f", lease.getTotalPrice()) + "|" +
                                    String.format("%.2f", lease.getMonthlyPayment());
                }
                String empty = "";
                writer.write(empty);
                writer.write(outputLine);
                writer.newLine();
                System.out.println("Contract saved. Good job.");
            } catch (IOException e) {
                System.out.println("Failed to save contract. womp womp");
                e.printStackTrace();
            }
        }
    }
