package com.taggart.e_green2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class SelectEV extends Fragment {


    ImageButton imageButton1;
    TextView textView1;
    ImageButton imageButton2;
    TextView textView2;
    ImageButton imageButton3;
    TextView textView3;
    ImageButton imageButton4;
    TextView textView4;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(false);
        View view = inflater.inflate(
                R.layout.select_ev_fragment, container, false);



        imageButton1 = (ImageButton) view.findViewById(R.id.imageButton1);
        textView1 = (TextView) view.findViewById(R.id.textView1);
        imageButton2 = (ImageButton) view.findViewById(R.id.imageButton2);
        textView2 = (TextView) view.findViewById(R.id.textView2);
        imageButton3 = (ImageButton) view.findViewById(R.id.imageButton3);
        textView3 = (TextView) view.findViewById(R.id.textView3);
        imageButton4 = (ImageButton) view.findViewById(R.id.imageButton4);
        textView4 = (TextView) view.findViewById(R.id.textView4);





        return view;
    }



    public void displayEVs(EV [] ev_array, int car_pairs_total) {

        ImageButton [] imagebuttons = {imageButton1, imageButton2,imageButton3, imageButton4};
        TextView [] textviews = {textView1, textView2, textView3, textView4};


        for (int i = 0; i < car_pairs_total; i++) {

            switch (ev_array[i].getId()) {

                case 0:  {

                            imagebuttons[i].setImageResource(R.drawable.bmw_i3);
                            imagebuttons[i].setVisibility(View.VISIBLE);
                            textviews[i].setText(R.string.BMW_i3_EV);
                            textviews[i].setVisibility(View.VISIBLE);

                            continue;

                }

                case 1: {
                            imagebuttons[i].setImageResource(R.drawable.chevy_bolt);
                            imagebuttons[i].setVisibility(View.VISIBLE);
                            textviews[i].setText(R.string.Chevy_Bolt_EV);
                            textviews[i].setVisibility(View.VISIBLE);

                            continue;


                }


                case 2: {

                             imagebuttons[i].setImageResource(R.drawable.fiat_500e);
                             imagebuttons[i].setVisibility(View.VISIBLE);
                             textviews[i].setText(R.string.Fiat_500e);
                             textviews[i].setVisibility(View.VISIBLE);

                            continue;


                }


                case 3: {
                            imagebuttons[i].setImageResource(R.drawable.ford_focus_ev);
                            imagebuttons[i].setVisibility(View.VISIBLE);
                            textviews[i].setText(R.string.ford_focus_ev);
                            textviews[i].setVisibility(View.VISIBLE);

                            continue;



                }


                default:
                            continue;


            }

        }
    }


}

