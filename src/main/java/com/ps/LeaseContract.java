package com.ps;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;

    // Lease terms
    private double leaseRate = 0.04;
    private int leaseLength = 36;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        double price = vehicleSold.getPrice();
        expectedEndingValue = price * 0.50;
        leaseFee = price * 0.07;
    }

    public double getEndingValue() {
        return expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }
//no need for setters honeslty


    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double price = getVehicleSold().getPrice();
        double monthlyInterest = leaseRate / 12.0;

        double baseAmount = price - expectedEndingValue;
        double monthlyPayment = (baseAmount * monthlyInterest) / (1 - Math.pow(1 + monthlyInterest, -leaseLength));

        return monthlyPayment;
    }
}



