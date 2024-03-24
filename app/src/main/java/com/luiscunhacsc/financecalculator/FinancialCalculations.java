package com.luiscunhacsc.financecalculator;

public class FinancialCalculations {

    /**
     * Calculates the Future Value of an investment based on periodic, constant payments and a constant interest rate.
     *
     * @param pv Present Value
     * @param pmt Periodic Payment
     * @param iY Interest per Year (as a percentage, e.g., 6 for 6%)
     * @param n Number of Periods
     * @return Future Value
     */
    public static double calculateFutureValue(double pv, double pmt, double iY, int n) {
        double r = iY / 100;
        return pv * Math.pow(1 + r, n) + pmt * (Math.pow(1 + r, n) - 1) / r;
    }

    /**
     * Calculates the Periodic Payment needed to pay off a loan or reach a future value.
     *
     * @param pv Present Value
     * @param fv Future Value
     * @param iY Interest per Year (as a percentage, e.g., 6 for 6%)
     * @param n Number of Periods
     * @return Periodic Payment
     */
    public static double calculatePeriodicPayment(double pv, double fv, double iY, int n) {
        double r = iY / 100;
        return (fv - pv * Math.pow(1 + r, n)) * r / (Math.pow(1 + r, n) - 1);
    }

    /**
     * Calculates the Present Value of an investment or loan based on future value and periodic payments.
     *
     * @param pmt Periodic Payment
     * @param fv Future Value
     * @param iY Interest per Year (as a percentage, e.g., 6 for 6%)
     * @param n Number of Periods
     * @return Present Value
     */
    public static double calculatePresentValue(double pmt, double fv, double iY, int n) {
        double r = iY / 100;
        return (fv - pmt * (Math.pow(1 + r, n) - 1) / r) / Math.pow(1 + r, n);
    }

    /**
     * Calculates the Number of Periods required to reach a future value based on present value, periodic payments,
     * and interest rate.
     *
     * @param pv Present Value
     * @param pmt Periodic Payment
     * @param fv Future Value
     * @param iY Interest per Year (as a percentage, e.g., 6 for 6%)
     * @return Number of Periods
     */
    public static double calculateNumberOfPeriods(double pv, double pmt, double fv, double iY) {
        double r = iY / 100;
        return ((int) ((Math.log((fv * r + pmt) / (pv * r + pmt)) / Math.log(1 + r))*10.0))/10.0;
    }

    /**
     * Calculates the Interest per Year required to reach a future value based on present value and periodic payments.
     *
     * @param pv Present Value
     * @param pmt Periodic Payment
     * @param fv Future Value
     * @param n Number of Periods
     * @return Interest per Year (as a percentage, e.g., 6 for 6%)
     */

    public static double calculateInterestPerYear(double pv, double pmt, double fv, int n) {
        double lowRate = -0.99; // Starting with a rate that is potentially too low
        double highRate = 1.00; // Starting with a rate that is potentially too high
        double tolerance = 0.01;
        double midRate = 0;
        int maxIterations = 1000;

        for (int i = 0; i < maxIterations; i++) {
            midRate = (lowRate + highRate) / 2;
            double fMid = calculateFutureValue(pv, pmt, midRate, n) - fv;

            if (Math.abs(fMid) < tolerance) {
                return midRate * 100; // Solution found
            }

            // Adjust the bounds
            if (fMid * calculateFutureValue(pv, pmt, lowRate, n) < 0) {
                highRate = midRate;
            } else {
                lowRate = midRate;
            }
        }

        throw new ArithmeticException("Interest rate calculation did not converge.");
    }


}
