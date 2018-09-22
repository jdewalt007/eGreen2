package com.taggart.e_green2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
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
import android.graphics.Color;
import android.widget.CompoundButton;
import android.widget.Toast;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.text.NumberFormat;


public class UserInfo extends Fragment  {




    // callback method implemented by MainActivity
    public interface UserInfoFragmentListener {

        // called after EV Search function completes
        void onSearchComplete(int car_pairs_total, ConsumerInfo consumer);

    }

    private UserInfoFragmentListener listener;

    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();

    private static final NumberFormat currencyFormat2 =
            NumberFormat.getCurrencyInstance();




    Double DailyCommuteInput = 0.0;
    Double AnnualMilesInput = 0.0;
    Double MonthlyIncomeInput = 0.0;
    Double DownPaymentInput = 0.0;
    Double InterestRateInput = 0.0;
    String seat_selection = "...";
    String home_charge_selection = "...";
    String months_financed_selection = "...";


    private AppCompatSpinner Seating_Num;
    private AppCompatSpinner Months_Financed;
    private AppCompatSpinner Home_Charg;


    private TextView dailyCommuteTextView;
    private TextView annualMilesTextView;
    private TextView monthlyIncomeTextView;
    private TextView downPaymentTextView;
    private TextView interestRateTextView;




    private EditText dailyCommuteEditText;
    private EditText annualMilesEditText;
    private EditText monthlyIncomeEditText;
    private EditText downPaymentEditText;
    private EditText interestRateEditText;



    private ConsumerInfo ConsumerInformation = new ConsumerInfo();





    private Button continueButton;
    private  double ev_charge_range = 0.0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(false);
        View view = inflater.inflate(
                R.layout.activity_user_info, container, false);

        CheckBox publicCharging;
        CheckBox workCharging;

        dailyCommuteEditText = (EditText) view.findViewById(R.id.dailyCommuteEditText);
        dailyCommuteTextView = (TextView) view.findViewById(R.id.dailyCommuteTextView);
        dailyCommuteEditText.addTextChangedListener(new GenericTextWatcher(dailyCommuteEditText));

        annualMilesEditText = (EditText) view.findViewById(R.id.annualMilesEditText);
        annualMilesTextView = (TextView) view.findViewById(R.id.annualMilesTextView);
        annualMilesEditText.addTextChangedListener(new GenericTextWatcher(annualMilesEditText));

        monthlyIncomeEditText = (EditText) view.findViewById(R.id.monthlyIncomeEditText);
        monthlyIncomeTextView = (TextView) view.findViewById(R.id.monthlyIncomeTextView);
        monthlyIncomeEditText.addTextChangedListener(new GenericTextWatcher(monthlyIncomeEditText));

        downPaymentEditText = (EditText) view.findViewById(R.id.downPaymentEditText);
        downPaymentTextView = (TextView) view.findViewById(R.id.downPaymentTextView);
        downPaymentEditText.addTextChangedListener(new GenericTextWatcher(downPaymentEditText));

        interestRateEditText = (EditText) view.findViewById(R.id.interestRateEditText);
        interestRateTextView = (TextView) view.findViewById(R.id.interestRateTextView);
        interestRateEditText.addTextChangedListener(new GenericTextWatcher(interestRateEditText));



        //remember to implement these
        Seating_Num = (AppCompatSpinner) view.findViewById(R.id.Seating_Num);
        Seating_Num.setOnItemSelectedListener(new GenericOnItemSelectedListener(Seating_Num));

        Months_Financed = (AppCompatSpinner) view.findViewById(R.id.Months_Financed);
        Months_Financed.setOnItemSelectedListener(new GenericOnItemSelectedListener(Months_Financed));



        Home_Charg = (AppCompatSpinner) view.findViewById(R.id.Home_Charg);
        Home_Charg.setOnItemSelectedListener(new GenericOnItemSelectedListener(Home_Charg));



        continueButton = (Button) view.findViewById(R.id.UserContinuebutton);
        continueButton.setOnClickListener(continueButtonListener);




        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (getActivity().getBaseContext(),  R.array.Seating_Number,
                        android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Seating_Num.setAdapter(adapter);



        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource
                (getActivity().getBaseContext(), R.array.Months_Financed,
                        android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Months_Financed.setAdapter(adapter1);



        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource
                (getActivity().getBaseContext(), R.array.Home_Charg_Opt,
                        android.R.layout.simple_spinner_item);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Home_Charg.setAdapter(adapter2);



        publicCharging = (CheckBox) view.findViewById(R.id.publicChargAvailCheckbox);
        workCharging = (CheckBox) view.findViewById(R.id.workChargAvailCheckbox);



        publicCharging.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked())  {

                    ConsumerInformation.setPublic_Charg_Avail(true);

                }

                else
                    ConsumerInformation.setPublic_Charg_Avail(false);

            }
        });


        workCharging.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (compoundButton.isChecked())  {

                    ConsumerInformation.setWork_Charg_Avail(true);

                }

                else ConsumerInformation.setWork_Charg_Avail(false);
            }
        });


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




    private class GenericTextWatcher implements TextWatcher  {


        private View view;

        private GenericTextWatcher(View view)  {

            this.view = view;
        }


        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try {

                switch (view.getId()) {

                    case R.id.dailyCommuteEditText:  {

                        DailyCommuteInput = Double.parseDouble(s.toString())/10.0;
                        ConsumerInformation.setDaily_Commute(DailyCommuteInput);
                        dailyCommuteTextView.setText(String.format("%.1f %s", DailyCommuteInput," miles"));

                        break;
                    }

                    case R.id.annualMilesEditText:  {

                        AnnualMilesInput = Double.parseDouble(s.toString())/10.0;
                        ConsumerInformation.setAnnual_Mileage(AnnualMilesInput);
                        annualMilesTextView.setText(String.format("%.1f %s", AnnualMilesInput, " miles"));

                        break;
                    }

                    case R.id.monthlyIncomeEditText:  {
                        MonthlyIncomeInput = Double.parseDouble(s.toString())/100.0;
                        ConsumerInformation.setMonthly_income(MonthlyIncomeInput);
                        monthlyIncomeTextView.setText(currencyFormat.format(MonthlyIncomeInput));
                        break;
                    }

                    case R.id.downPaymentEditText:  {
                        DownPaymentInput = Double.parseDouble(s.toString())/100.0;
                        ConsumerInformation.setDown_Payment(DownPaymentInput);
                        downPaymentTextView.setText(currencyFormat2.format(DownPaymentInput));
                        break;
                    }

                    case R.id.interestRateEditText:  {
                        InterestRateInput = Double.parseDouble(s.toString())/100.0;
                        ConsumerInformation.setInterest_Rate(InterestRateInput/100.0);


                        interestRateTextView.setText(String.format("%.2f %s", InterestRateInput,"%"));
                        break;
                    }

                }

            }

            catch (NumberFormatException e) {

                switch (view.getId()) {

                    case R.id.dailyCommuteTextView:  {
                        dailyCommuteTextView.setText("0.0");
                        ConsumerInformation.setDaily_Commute(0.0);
                        break;  }

                    case R.id.annualMilesTextView:    {
                        annualMilesTextView.setText("0.0");
                        ConsumerInformation.setAnnual_Mileage(0.0);
                        break;  }

                    case R.id.monthlyIncomeTextView:   {
                        monthlyIncomeTextView.setText("0.0");
                        ConsumerInformation.setMonthly_income(0.0);
                        break;  }

                    case R.id.downPaymentTextView:  {
                        downPaymentTextView.setText("0.0");
                        ConsumerInformation.setDown_Payment(0.0);
                        break;  }

                    case R.id.interestRateTextView:   {
                        interestRateTextView.setText("0.0");
                        ConsumerInformation.setInterest_Rate(0.0);
                        break;  }


                    default:

                        break;
                }

            }
        }


        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }

    }



    public class GenericOnItemSelectedListener implements AdapterView.OnItemSelectedListener {


        private View view;

        private GenericOnItemSelectedListener(View view) {

            this.view = view;
        }



        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {



            switch (view.getId()) {

                case R.id.Seating_Num: {


                    seat_selection = parent.getItemAtPosition(position).toString();
                    String y = "...";
                    if (!seat_selection.equals(y))  {

                        int seats_required = Integer.parseInt(seat_selection);
                        ConsumerInformation.setPass_Capacity(seats_required);

                    }

                    break;
                }

                case R.id.Home_Charg: {


                    String charge1 = "level 1";
                    String charge2 = "level 2";

                    if (parent.getItemAtPosition(position).toString().equals(charge1))
                    {
                        ConsumerInformation.setHome_Charg_Avail(true);
                        home_charge_selection = charge1;
                        break;

                    }

                   else if (parent.getItemAtPosition(position).toString().equals(charge2))

                    {
                        ConsumerInformation.setHome_Charg_Avail2(true);
                        home_charge_selection = charge2;
                        break;

                    }

                }

                case R.id.Months_Financed:  {

                    months_financed_selection = parent.getItemAtPosition(position).toString();
                    String y = "...";
                    if (!months_financed_selection.equals(y))  {

                        int months_selected = Integer.parseInt(months_financed_selection);
                        ConsumerInformation.setTerm_Length(months_selected);

                    }

                    break;
                }

            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {  }

    }




    // listener for when the continue button is pressed
    // will need to ensure all consumer inputs are complete and valid before
    // initiating the Search function
    private OnClickListener continueButtonListener = new OnClickListener () {

        @Override
        public void onClick(View view) {

            String default_selection = "...";
            if ((DailyCommuteInput == 0.0) || (AnnualMilesInput == 0.0) || (MonthlyIncomeInput == 0.0)
                    || (DownPaymentInput == 0.0) || (InterestRateInput == 0.0) ||
                    (seat_selection.equals(default_selection)) || (months_financed_selection.equals(default_selection))
                    || (home_charge_selection.equals(default_selection)))
            {

                Toast toast = Toast.makeText(getActivity(), R.string.missing_data_input, Toast.LENGTH_LONG);
                View view1 = toast.getView();
                view1.setBackgroundResource(R.drawable.toast);
                TextView toastTextView  = (TextView) view1.findViewById(android.R.id.message);
                toastTextView.setTextColor(Color.BLACK);
                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.START, 185, 230);
                toast.show();
            }

            else {

                Search();
                if(car_pairs_total == 0)
                {
                    Toast toast = Toast.makeText(getActivity(), R.string.no_cars_meet_criteria, Toast.LENGTH_LONG);
                    View view1 = toast.getView();
                    view1.setBackgroundResource(R.drawable.toast);
                    TextView toastTextView  = (TextView) view1.findViewById(android.R.id.message);
                    toastTextView.setTextColor(Color.BLACK);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 25);
                    toast.show();
                }
                else {

                    DailyCommuteInput = 0.0;
                    dailyCommuteTextView.setText("0.0");
                    AnnualMilesInput = 0.0;

                    listener.onSearchComplete(car_pairs_total, ConsumerInformation);
                }
            }

        }
    };


    private  ObjectInputStream input;
    public  EV [] ev_array = null;
    public  GPV [] gpv_array = null;
    private  int car_pairs_total = 0;





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


    public int findEV_GPV_pairs()  //consumer object passed into this function to manipulate
    //the seating and daily commute user inputs
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

                if (ConsumerInformation.isHome_Charg_Avail())
                    ev_charge_range = ev_record.getCharge_range_lvl1();

                else if (ConsumerInformation.isHome_Charg_Avail2())
                    ev_charge_range = ev_record.getCharge_range_lvl2();


                count += 1;


                if ((ev_charge_range  > ConsumerInformation.getDaily_Commute())
                        && (ev_record.getSeat_capacity() >= ConsumerInformation.getPass_Capacity()))
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
