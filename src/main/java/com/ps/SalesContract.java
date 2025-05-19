package com.ps;

public class SalesContract extends Contract {
    // yes or no later to add on the writer
    private boolean finance;

    private double salesTaxAmount = 0.05;
    private double recordingFee = 100.0;
    //under $10,000
    private double processingFeeLow = 295.0;
    //over $10,000
    private double processingFeeHigh = 495.0;
    private double loanRateLow = 0.0525;
    private double loanRateHigh = 0.0425;
    private int loanTermLow = 24;          // Months
    private int loanTermHigh = 48;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.finance = finance;
    }
    //no setters for other variables since they are set in stone
    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    @Override
    public double getTotalPrice() {
        double price = getVehicleSold().getPrice();
        double tax = price * salesTaxAmount;
        double processingFee = 0.0;
        if (price < 10000) {
            processingFee = processingFeeLow;
        } else {
            processingFee = processingFeeHigh;
        }

         double total = (price + recordingFee + processingFee) * salesTaxAmount;
        return total;
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) {
            return 0.0;
        }

        double totalPrice = getTotalPrice();
        double interestRate = 0.0;
        int loanTerm = 0;

        if (getVehicleSold().getPrice() >= 10000) {
            interestRate = loanRateHigh;
            loanTerm = loanTermHigh;
        } else {
            interestRate = loanRateLow;
            loanTerm = loanTermLow;
        }

        double monthlyInterest = interestRate / 12.0;
        //google equation
        double monthlyPayment = (totalPrice * monthlyInterest) / (1 - Math.pow(1 + monthlyInterest, -loanTerm));


        return monthlyPayment;
    }
}
