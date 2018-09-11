package com.taggart.e_green2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class SelectEV extends Fragment {


    // callback method implemented by MainActivity
    public interface SelectEvFragmentListener {

        // called after EV Search function completes
        void onEvSelected(EV ev, GPV gpv);

    }

    private SelectEvFragmentListener listener;


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



    // set SelectEvFragmentListener when fragment attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (SelectEvFragmentListener) context;
    }

    // remove SelectEvFragmentListener when fragment detached
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }



    public void displayEVs(EV [] ev_array, int car_pairs_total, GPV [] gpv_array) {

        ImageButton [] imagebuttons = {imageButton1, imageButton2,imageButton3, imageButton4};
        TextView [] textviews = {textView1, textView2, textView3, textView4};


        for (int i = 0; i < car_pairs_total; i++) {

            switch (ev_array[i].getId()) {

                case 0:  {

                            imagebuttons[i].setImageResource(R.drawable.bmw_i3);
                            imagebuttons[i].setVisibility(View.VISIBLE);
                            textviews[i].setText(R.string.BMW_i3_EV);
                            textviews[i].setVisibility(View.VISIBLE);

                            final EV bmw_i3 = ev_array[i];
                            final GPV vw_beetle = gpv_array[ev_array[i].getId()];

                            ImageButton bmw_i3ImageButton = imagebuttons[i];
                            bmw_i3ImageButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                   listener.onEvSelected(bmw_i3, vw_beetle);
                                }
                            });

                            continue;

                }

                case 1: {
                            imagebuttons[i].setImageResource(R.drawable.chevy_bolt);
                            imagebuttons[i].setVisibility(View.VISIBLE);
                            textviews[i].setText(R.string.Chevy_Bolt_EV);
                            textviews[i].setVisibility(View.VISIBLE);


                            final EV chevy_bolt = ev_array[i];
                            final GPV chevy_sonic = gpv_array[ev_array[i].getId()];

                            ImageButton chevy_boltImageButton = imagebuttons[i];

                            chevy_boltImageButton.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                      listener.onEvSelected(chevy_bolt, chevy_sonic);
                                 }
                            });

                            continue;
                }


                case 2: {

                            imagebuttons[i].setImageResource(R.drawable.fiat_500e);
                            imagebuttons[i].setVisibility(View.VISIBLE);
                            textviews[i].setText(R.string.Fiat_500e);
                            textviews[i].setVisibility(View.VISIBLE);

                            final EV fiat_500e = ev_array[i];
                            final GPV fiat_500 = gpv_array[ev_array[i].getId()];

                            ImageButton fiat_500eImageButton = imagebuttons[i];

                            fiat_500eImageButton.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                      listener.onEvSelected(fiat_500e, fiat_500);
                                 }
                            });
                            continue;
                }


                case 3: {
                            imagebuttons[i].setImageResource(R.drawable.ford_focus_ev);
                            imagebuttons[i].setVisibility(View.VISIBLE);
                            textviews[i].setText(R.string.ford_focus_ev);
                            textviews[i].setVisibility(View.VISIBLE);

                            final EV ford_focus_ev = ev_array[i];
                            final GPV ford_focus_st = gpv_array[ev_array[i].getId()];

                            ImageButton ford_focus_evImageButton = imagebuttons[i];

                            ford_focus_evImageButton.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                      listener.onEvSelected(ford_focus_ev, ford_focus_st);
                                 }
                            });

                            continue;
                }

                default:
                            continue;


            }

        }
    }


}

