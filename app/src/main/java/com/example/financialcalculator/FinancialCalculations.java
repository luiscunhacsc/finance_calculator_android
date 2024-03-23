package com.example.financialcalculator;

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
    public static int calculateNumberOfPeriods(double pv, double pmt, double fv, double iY) {
        double r = iY / 100;
        return (int) (Math.log((fv * r + pmt) / (pv * r + pmt)) / Math.log(1 + r));
    }

    /**
     * Calculates the Interest per Year required to reach a future value based on present value and periodic payments.
     * Note: This method would typically require a numerical method to solve and is not implemented in this example.
     *
     * @param pv Present Value
     * @param pmt Periodic Payment
     * @param fv Future Value
     * @param n Number of Periods
     * @return Interest per Year (as a percentage, e.g., 6 for 6%)
     */
    public static double calculateInterestPerYear(double pv, double pmt, double fv, int n) {
        // Implementing a calculation method for interest per year is beyond the scope of this example.
        // This function is provided as a placeholder and should be replaced with a proper implementation.
        throw new UnsupportedOperationException("Calculating interest rate is not supported in this example.");
    }
}
