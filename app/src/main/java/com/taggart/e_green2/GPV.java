package com.taggart.e_green2;

//GPV.java
//Serializable GPV Class for storing records as objects ///
import java.io.Serializable;

public class GPV implements Serializable
{
    private String name;
    private int msrp;
    private double mpg;
    private double maint_cost_100k;
    private int seat_capacity;
    private int id;


    public GPV(String name, int msrp, double mpg, double maint_cost_100k, int seat_capacity, int id)
    {
        this.name = name;
        this.msrp = msrp;
        this.mpg = mpg;
        this.maint_cost_100k = maint_cost_100k;
        this.seat_capacity = seat_capacity;
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

    public void setMPG(double mpg)
    {
        this.mpg = mpg;
    }

    public double getMPG()
    {
        return mpg;
    }

    public void setMaint_cost_100k(double maint_cost_100k)
    {
        this.maint_cost_100k = maint_cost_100k;
    }

    public double getMaint_cost_100k()
    {
        return maint_cost_100k;
    }

    public void setSeat_capacity(int seat_capacity)
    {
        this.seat_capacity = seat_capacity;
    }

    public int getSeat_capacity()
    {
        return seat_capacity;
    }

    public void setId(int id) { this.id = id; }  // comparable GPV and EV have same number

    public int getId()  { return id; }

} //end class GPV