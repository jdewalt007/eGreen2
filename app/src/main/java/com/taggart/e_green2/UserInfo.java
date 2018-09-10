package com.taggart.e_green2;

import android.content.Context;
import android.inputmethodservice.ExtractEditText;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Arrays;
import java.text.NumberFormat;

public class UserInfo extends Fragment {


    // callback method implemented by MainActivity
    public interface UserInfoFragmentListener {

        // called after EV Search function completes
        void onSearchComplete(int car_pairs_total);

    }

    private UserInfoFragmentListener listener;



  /*private TextView Seating_Spin;
    private TextView Term_Numb;
    private TextView Charg_Avail;
    private TextView Work_Charg;
    private TextView Home_Charg_Options;
    private TextView public_Charg;*/



    private int DailyCommuteInput =0;
    private int AnnualMileageInput=0;
    private int MonthlyIncomeInput=0;
    private int DownPaymentInput=0;

    private AppCompatSpinner Seating_Num;
    private AppCompatSpinner Terms;
    private AppCompatSpinner Home_Charg;

   /* int SeatingNumber=0;*/

    private CheckBox checkBox;
    private CheckBox checkbox2;
    private CheckBox checkbox3;
    private CheckBox checkbox4;

    private Button continueButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(false);
        View view = inflater.inflate(
                R.layout.activity_user_info, container, false);

        final EditText Daily_Commute = (EditText) view.findViewById(R.id.Daily_Commute);
        final EditText Annual_Mil = (EditText) view.findViewById(R.id.Annual_Mil);
        final EditText Monthly_Inc = (EditText) view.findViewById(R.id.Monthly_Inc);
        final EditText Down_Payment = (EditText) view.findViewById(R.id.Down_Payment);

        Seating_Num = (AppCompatSpinner) view.findViewById(R.id.Seating_Num);
        Terms=(AppCompatSpinner) view.findViewById(R.id.Terms);
        Home_Charg=(AppCompatSpinner) view.findViewById(R.id.Home_Charg);

        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(getContext(), R.array.Seating_Number,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>adapter1= ArrayAdapter.createFromResource(getContext(), R.array.Terms_length,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.Home_Charg_Opt,
                android.R.layout.simple_spinner_item);



        continueButton = (Button) view.findViewById(R.id.continueButton);
        continueButton.setOnClickListener(continueButtonListener);









        // listener object for the EditText's text-changed events
        final TextWatcher textwatcher = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                try {


                    DailyCommuteInput = (int) Double.parseDouble(toString());
                    AnnualMileageInput = (int) Double.parseDouble(toString());
                    MonthlyIncomeInput = (int) Double.parseDouble(toString());
                    DownPaymentInput = (int) Double.parseDouble(toString());

                    Daily_Commute.setText(DailyCommuteInput);
                    Annual_Mil.setText(AnnualMileageInput);
                    Monthly_Inc.setText(MonthlyIncomeInput);
                    Down_Payment.setText(DownPaymentInput);

                } catch (NumberFormatException e) {

                    Daily_Commute.setText(" ");
                    Annual_Mil.setText(" ");
                    Monthly_Inc.setText(" ");
                    Down_Payment.setText(" ");

                }


            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }
        };


         final AdapterView.OnItemSelectedListener SetSeatingNumber= new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               int  SeatingNumber=Integer.valueOf((Integer) Seating_Num.getSelectedItem());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        final AdapterView.OnItemSelectedListener SetTermsLength= new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int  TermsLength=Integer.valueOf((Integer) Terms.getSelectedItem());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        final AdapterView.OnItemSelectedListener SetHomeChargingAvailability= new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //remember to call all these functions I just set for onItemSelected listener.
                //if (Home_Charg.getSelectedItem()= R.array.Home_Charg_Op()){}

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        return view;
    }



    // set UserInfoFragmentListener when fragment attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (UserInfoFragmentListener) context;
    }

    // remove UserInfoFragmentListener when fragment detached
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    // listener for when the continue button is pressed
    // will need to ensure all consumer inputs are complete and valid before
    // initiating the Search function
    private OnClickListener continueButtonListener = new OnClickListener () {

        @Override
        public void onClick(View view) {

            Search();
            listener.onSearchComplete(car_pairs_total);

        }
    };


    private  ObjectInputStream input;
    public  EV [] ev_array = null;
    public  GPV [] gpv_array = null;
    private  int car_pairs_total = 0;
    private  double consumer_daily_commute = 70.2;  // no Consumer class/object created yet
    private  int consumer_seat_capacity = 4;  // no Consumer class/object created yet



    public void Search()  {

        openFile();
        car_pairs_total = findEV_GPV_pairs();
        closeFile();

    }

    public void openFile() {


        try {


            int id = getResources().getIdentifier("ev_gpv", "raw", getContext().getPackageName());
            InputStream resourceInputStream = getResources().openRawResource(id);

            input = new ObjectInputStream(resourceInputStream);

        }

        catch (IOException ioException) {
            Log.e("UserInfo", ioException.getMessage(), ioException);
            System.err.println("Error opening file.");
            System.exit(1);
        }
    }


    public int findEV_GPV_pairs()  //consumer object will be passed into this function to manipulate
    //the seating and daily commute attributes
    {
        int index = 0; // array index tracking EV's meeting consumer's needs; & associated GPV
        try
        {
            int count = 1; // counting through the 22 EV records to check & store those meeting the consumer's needs

            while(count < 23)
            {

                EV ev_record = (EV) input.readObject();
                GPV gpv_record = (GPV) input.readObject();
                Log.e("serialization","EV name = " + ev_record.getName());

                count += 1;

                if ((ev_record.getCharge_range_lvl2()  > consumer_daily_commute)
                        && (ev_record.getSeat_capacity() >= consumer_seat_capacity))
                {
                    if (index == 0) {

                        ev_array = new EV[22];
                        gpv_array = new GPV[22];
                    }

                    ev_array[index] = ev_record;
                    gpv_array[index] = gpv_record;
                    index += 1;

                }

            } // end while loop

        } // end try

        catch(EOFException endofFileException)
        {
            System.out.println("No more records");
        }
        catch(ClassNotFoundException classNotFoundException)
        {
            System.err.println("Invalid object type. Terminating.");
        }
        catch(IOException ioException)
        {
            System.err.println("Error reading from file. Terminating.");
        }
        return index;  // returns number of EV & GPVs entered in array

    }// end method findEV_GPV_pairs


    public void closeFile()
    {
        try
        {
            if (input != null)
                input.close();
        }
        catch(IOException ioException)
        {
           System.err.println("Error closing file. Terminating.");
           System.exit(1);
        }
    }

}
