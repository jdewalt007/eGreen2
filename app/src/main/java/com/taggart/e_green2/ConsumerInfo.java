package com.taggart.e_green2;

public class ConsumerInfo {

    private double Annual_Mileage;
    private double Daily_Commute;
    private double Down_Payment;
    private double Interest_Rate;
    private double Monthly_income;

    private int  Pass_Capacity;
    private int Term_Length;

    private boolean Home_Charg_Avail;
    private boolean Home_Charg_Avail2;
    private boolean work_Charg_Avail;
    private boolean public_Charg_Avail;


    public ConsumerInfo(){

        Annual_Mileage=0.0;
        Daily_Commute=0.0;
        Pass_Capacity=0;
        Down_Payment=0.0;
        Monthly_income=0.0;
        Term_Length=0;
        Interest_Rate=0.0;
        Home_Charg_Avail=false;
        Home_Charg_Avail2=false;
        work_Charg_Avail=false;
        public_Charg_Avail=false;
    }



    public double getAnnual_Mileage() {
        return Annual_Mileage;
    }

    public void setAnnual_Mileage(double annual_Mileage) {
        Annual_Mileage = annual_Mileage;
    }

    public double getDaily_Commute() {
        return Daily_Commute;
    }

    public void setDaily_Commute(double daily_Commute) {
        Daily_Commute = daily_Commute;
    }

    public int getPass_Capacity() {
        return Pass_Capacity;
    }

    public void setMonthly_income(double monthly_income) {
        Monthly_income = monthly_income;
    }

    public double getMonthly_income() {
        return Monthly_income;
    }

    public void setPass_Capacity(int pass_Capacity) {
        Pass_Capacity = pass_Capacity;
    }

    public void setDown_Payment(double down_Payment) {
        Down_Payment = down_Payment;
    }

    public double getDown_Payment() {
        return Down_Payment;
    }

    public int getTerm_Length() {
        return Term_Length;
    }
    public void setTerm_Length(int term_Length) {
        Term_Length = term_Length;
    }

    public void setInterest_Rate(double interest_Rate) {
        Interest_Rate = interest_Rate;
    }

    public double getInterest_Rate() {
        return Interest_Rate;
    }



    public void setHome_Charg_Avail(boolean home_Charg_Avail) {
        Home_Charg_Avail = home_Charg_Avail;
    }

    public boolean isHome_Charg_Avail() {
        return Home_Charg_Avail;
    }

    public void setHome_Charg_Avail2(boolean home_Charg_Avail2) {
        Home_Charg_Avail2 = home_Charg_Avail2;
    }

    public boolean isHome_Charg_Avail2() {
        return Home_Charg_Avail2;
    }
    public boolean isPublic_Charg_Avail() {
        return public_Charg_Avail;
    }

    public void setPublic_Charg_Avail(boolean public_Charg_Avail) {
        this.public_Charg_Avail = public_Charg_Avail;
    }

    public boolean isWork_Charg_Avail() {
        return work_Charg_Avail;
    }

    public void setWork_Charg_Avail(boolean work_Charg_Avail) {
        this.work_Charg_Avail = work_Charg_Avail;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}


