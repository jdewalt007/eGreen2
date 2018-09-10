package com.taggart.e_green2;

//EV.java
//Serializable EV Class for storing records as objects
import java.io.Serializable;

public class EV implements Serializable
{
    private String name;
    private int msrp;
    private double charge_range_lvl1;
    private double charge_range_lvl2;
    private double battery_size;
    private int seat_capacity;
    private double actual_range;
    private double maint_cost_100k;
    private int id;




    public EV(String name, int msrp, double charge_range_lvl1, double charge_range_lvl2, double battery_size, int seat_capacity,
              double actual_range, double maint_cost_100k, int id)
    {
        this.name = name;
        this.msrp = msrp;
        this.charge_range_lvl1 = charge_range_lvl1;
        this.charge_range_lvl2 = charge_range_lvl2;
        this.battery_size = battery_size;
        this.seat_capacity = seat_capacity;
        this.actual_range = actual_range;
        this.maint_cost_100k = maint_cost_100k;
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setMSRP(int msrp)
    {
        this.msrp = msrp;
    }

    public int getMSRP()
    {
        return msrp;
    }

    public void setCharge_range_lvl1(double charge_range_lvl1)
    {
        this.charge_range_lvl1 = charge_range_lvl1;
    }

    public double getCharge_range_lvl1()
    {
        return charge_range_lvl1;
    }

    public void setCharge_range_lvl2(double charge_range_lvl2)
    {
        this.charge_range_lvl2 = charge_range_lvl2;
    }

    public double getCharge_range_lvl2()
    {
        return charge_range_lvl2;
    }

    public void setBattery_size(double battery_size)
    {
        this.battery_size = battery_size;
    }

    public double getBattery_size()
    {
        return battery_size;
    }

    public void setSeat_capacity(int seat_capacity)
    {
        this.seat_capacity = seat_capacity;
    }

    public int getSeat_capacity()
    {
        return seat_capacity;
    }

    public void setActual_range(double actual_range)
    {
        this.actual_range = actual_range;
    }

    public double getActual_range()
    {
        return actual_range;
    }

    public void setMaint_cost_100k(double maint_cost_100k)
    {
        this.maint_cost_100k = maint_cost_100k;
    }

    public double getMaint_cost_100k()
    {
        return maint_cost_100k;
    }


    public void setId(int id) { this.id = id; }

    public int getId()  { return id; }  // EV and comparable GPV have same number


} //end class EV