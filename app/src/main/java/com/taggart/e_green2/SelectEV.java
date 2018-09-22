package com.taggart.e_green2;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class SelectEV extends Fragment {


    // callback method implemented by MainActivity
    public interface SelectEvFragmentListener {

        // called after User clicks on an EV
        void onEvSelected(EV ev, GPV gpv);

    }

    private SelectEvFragmentListener listener;
    private Button select_ev_continueButton;
    private boolean ev_selected = false;
    private EV current_ev_selected;
    private GPV current_gpv_selected;
    private ImageButton current_image_button_selected;
    private GradientDrawable drawable, drawable2,
                             drawable3, drawable4;
    


    ImageButton imageButton1, imageButton2, imageButton3, imageButton4, imageButton5, imageButton6,
            imageButton7, imageButton8, imageButton9, imageButton10, imageButton11, imageButton12,
            imageButton13, imageButton14, imageButton15, imageButton16, imageButton17, imageButton18,
            imageButton19, imageButton20, imageButton21, imageButton22;

    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7,
            textView8, textView9, textView10, textView11, textView12, textView13, textView14,
            textView15, textView16, textView17, textView18, textView19, textView20, textView21,
            textView22;





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
        imageButton5 = (ImageButton) view.findViewById(R.id.imageButton5);
        textView5 = (TextView) view.findViewById(R.id.textView5);
        imageButton6 = (ImageButton) view.findViewById(R.id.imageButton6);
        textView6 = (TextView) view.findViewById(R.id.textView6);
        imageButton7 = (ImageButton) view.findViewById(R.id.imageButton7);
        textView7 = (TextView) view.findViewById(R.id.textView7);
        imageButton8 = (ImageButton) view.findViewById(R.id.imageButton8);
        textView8 = (TextView) view.findViewById(R.id.textView8);
        imageButton9 = (ImageButton) view.findViewById(R.id.imageButton9);
        textView9 = (TextView) view.findViewById(R.id.textView9);
        imageButton10 = (ImageButton) view.findViewById(R.id.imageButton10);
        textView10 = (TextView) view.findViewById(R.id.textView10);

        select_ev_continueButton = (Button) view.findViewById(R.id.selectEVcontinueButton);



        select_ev_continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if(!ev_selected) {

                    Toast toast = Toast.makeText(getActivity(), R.string.ev_continue_button_error, Toast.LENGTH_SHORT);
                    View view1 = toast.getView();
                    view1.setBackgroundResource(R.drawable.toast);
                    TextView toastTextView  = (TextView) view1.findViewById(android.R.id.message);
                    toastTextView.setTextColor(Color.BLACK);
                    toast.show();
                }
                else {

                       select_ev_continueButton.setBackground(drawable4);
                       listener.onEvSelected(current_ev_selected, current_gpv_selected);
                }
            }
        });


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



    private void setCurrentEvGpvSelection(ImageButton button_selected, EV ev_clicked, GPV gpv_sent)  {


        current_ev_selected = ev_clicked;
        current_gpv_selected = gpv_sent;
        current_image_button_selected = button_selected;

    }




    public void displayEVs(EV [] ev_array, int car_pairs_total, GPV [] gpv_array) {

        ImageButton [] imagebuttons = {imageButton1, imageButton2,imageButton3, imageButton4,
        imageButton5, imageButton6, imageButton7, imageButton8, imageButton9, imageButton10};



        TextView [] textviews = {textView1, textView2, textView3, textView4, textView5, textView6,
        textView7, textView8, textView9, textView10};





        // highlights current vehicle selected
        drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(10, Color.rgb(48,159,54));
        drawable.setColor(Color.BLACK);

        // removes vehicle highlight when another vehicle selected
        drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setStroke(6, Color.BLACK);
        drawable2.setColor(Color.BLACK);

        // continue button border set to green when first vehicle selected
        drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setStroke(6, Color.rgb(48,159,54));
        drawable3.setColor(Color.WHITE);

        // continue button border set back to red after valid click
        drawable4 = new GradientDrawable();
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setStroke(6, Color.rgb(200,00,00));
        drawable4.setColor(Color.WHITE);




        for (int i = 0; i < car_pairs_total; i++) {

            switch (ev_array[i].getId()) {

                case 0:  {

                            imagebuttons[i].setImageResource(R.drawable.bmw_i3);
                            imagebuttons[i].setVisibility(View.VISIBLE);
                            textviews[i].setText(R.string.BMW_i3_EV);
                            textviews[i].setVisibility(View.VISIBLE);

                            final EV bmw_i3 = ev_array[i];
                            final GPV vw_beetle = gpv_array[ev_array[i].getId()];

                            final ImageButton imageButton = imagebuttons[i];
                            imageButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if (!ev_selected)  {
                                        ev_selected = true;
                                        select_ev_continueButton.setBackground(drawable3);
                                        imageButton.setBackground(drawable);
                                        setCurrentEvGpvSelection(imageButton, bmw_i3, vw_beetle);
                                    }
                                    else {

                                        current_image_button_selected.setBackground(drawable2);
                                        imageButton.setBackground(drawable);
                                        setCurrentEvGpvSelection(imageButton, bmw_i3, vw_beetle);
                                    }
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

                            final ImageButton imageButton = imagebuttons[i];

                            imageButton.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {

                                     if (!ev_selected)  {

                                         ev_selected = true;
                                         select_ev_continueButton.setBackground(drawable3);
                                         imageButton.setBackground(drawable);
                                         setCurrentEvGpvSelection(imageButton, chevy_bolt, chevy_sonic);
                                     }
                                     else {

                                         current_image_button_selected.setBackground(drawable2);
                                         imageButton.setBackground(drawable);
                                         setCurrentEvGpvSelection(imageButton, chevy_bolt, chevy_sonic);
                                     }
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

                            final ImageButton imageButton = imagebuttons[i];

                            imageButton.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {

                                     if (!ev_selected)  {

                                         ev_selected = true;
                                         select_ev_continueButton.setBackground(drawable3);
                                         imageButton.setBackground(drawable);
                                         setCurrentEvGpvSelection(imageButton, fiat_500e, fiat_500);
                                     }
                                     else {

                                         current_image_button_selected.setBackground(drawable2);
                                         imageButton.setBackground(drawable);
                                         setCurrentEvGpvSelection(imageButton, fiat_500e, fiat_500);
                                     }

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

                            final ImageButton imageButton = imagebuttons[i];

                            imageButton.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {

                                     if (!ev_selected)  {

                                         ev_selected = true;
                                         select_ev_continueButton.setBackground(drawable3);
                                         imageButton.setBackground(drawable);
                                         setCurrentEvGpvSelection(imageButton, ford_focus_ev, ford_focus_st);
                                     }
                                     else {

                                         current_image_button_selected.setBackground(drawable2);
                                         imageButton.setBackground(drawable);
                                         setCurrentEvGpvSelection(imageButton, ford_focus_ev, ford_focus_st);
                                     }
                                 }
                            });

                            continue;
                }

                case 4: {
                    imagebuttons[i].setImageResource(R.drawable.hyundai_ioniq);
                    imagebuttons[i].setVisibility(View.VISIBLE);
                    textviews[i].setText(R.string.Hyundai_Ioniq);
                    textviews[i].setVisibility(View.VISIBLE);

                    final EV hyundai_ioniq = ev_array[i];
                    final GPV hyundai_sonata = gpv_array[ev_array[i].getId()];

                    final ImageButton imageButton = imagebuttons[i];

                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (!ev_selected)  {

                                ev_selected = true;
                                select_ev_continueButton.setBackground(drawable3);
                                imageButton.setBackground(drawable);
                                setCurrentEvGpvSelection(imageButton, hyundai_ioniq, hyundai_sonata);
                            }
                            else {

                                current_image_button_selected.setBackground(drawable2);
                                imageButton.setBackground(drawable);
                                setCurrentEvGpvSelection(imageButton, hyundai_ioniq, hyundai_sonata);
                            }
                        }
                    });

                    continue;
                }


                case 5: {
                    imagebuttons[i].setImageResource(R.drawable.kia_soul_ev);
                    imagebuttons[i].setVisibility(View.VISIBLE);
                    textviews[i].setText(R.string.Kia_Soul_EV);
                    textviews[i].setVisibility(View.VISIBLE);

                    final EV kia_soul_ev = ev_array[i];
                    final GPV kia_soul_auto = gpv_array[ev_array[i].getId()];

                    final ImageButton imageButton = imagebuttons[i];

                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (!ev_selected)  {

                                ev_selected = true;
                                select_ev_continueButton.setBackground(drawable3);
                                imageButton.setBackground(drawable);
                                setCurrentEvGpvSelection(imageButton, kia_soul_ev, kia_soul_auto);
                            }
                            else {

                                current_image_button_selected.setBackground(drawable2);
                                imageButton.setBackground(drawable);
                                setCurrentEvGpvSelection(imageButton, kia_soul_ev, kia_soul_auto);
                            }
                        }
                    });

                    continue;
                }


                case 6: {
                    imagebuttons[i].setImageResource(R.drawable.mercedes_b_250e);
                    imagebuttons[i].setVisibility(View.VISIBLE);
                    textviews[i].setText(R.string.Mercedes_B_250e);
                    textviews[i].setVisibility(View.VISIBLE);

                    final EV mercedes_B250e = ev_array[i];
                    final GPV mercedes_GLA = gpv_array[ev_array[i].getId()];

                    final ImageButton imageButton = imagebuttons[i];

                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (!ev_selected)  {

                                ev_selected = true;
                                select_ev_continueButton.setBackground(drawable3);
                                imageButton.setBackground(drawable);
                                setCurrentEvGpvSelection(imageButton, mercedes_B250e, mercedes_GLA);
                            }
                            else {

                                current_image_button_selected.setBackground(drawable2);
                                imageButton.setBackground(drawable);
                                setCurrentEvGpvSelection(imageButton, mercedes_B250e, mercedes_GLA);
                            }
                        }
                    });

                    continue;
                }

                case 7: {
                    imagebuttons[i].setImageResource(R.drawable.mitsubishi_i_miev);
                    imagebuttons[i].setVisibility(View.VISIBLE);
                    textviews[i].setText(R.string.Mitsubishi_i_MiEV);
                    textviews[i].setVisibility(View.VISIBLE);

                    final EV mitsubishi_i_miev = ev_array[i];
                    final GPV mitsubishi_mirage = gpv_array[ev_array[i].getId()];

                    final ImageButton imageButton = imagebuttons[i];

                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (!ev_selected)  {

                                ev_selected = true;
                                select_ev_continueButton.setBackground(drawable3);
                                imageButton.setBackground(drawable);
                                setCurrentEvGpvSelection(imageButton, mitsubishi_i_miev, mitsubishi_mirage);
                            }
                            else {

                                current_image_button_selected.setBackground(drawable2);
                                imageButton.setBackground(drawable);
                                setCurrentEvGpvSelection(imageButton, mitsubishi_i_miev, mitsubishi_mirage);
                            }
                        }
                    });

                    continue;
                }


                case 8: {
                    imagebuttons[i].setImageResource(R.drawable.nissan_leaf);
                    imagebuttons[i].setVisibility(View.VISIBLE);
                    textviews[i].setText(R.string.Nissan_Leaf);
                    textviews[i].setVisibility(View.VISIBLE);

                    final EV nissan_leaf = ev_array[i];
                    final GPV vw_golf_gti = gpv_array[ev_array[i].getId()];

                    final ImageButton imageButton = imagebuttons[i];

                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (!ev_selected)  {

                                ev_selected = true;
                                select_ev_continueButton.setBackground(drawable3);
                                imageButton.setBackground(drawable);
                                setCurrentEvGpvSelection(imageButton, nissan_leaf, vw_golf_gti);
                            }
                            else {

                                current_image_button_selected.setBackground(drawable2);
                                imageButton.setBackground(drawable);
                                setCurrentEvGpvSelection(imageButton, nissan_leaf, vw_golf_gti);
                            }
                        }
                    });

                    continue;
                }

                case 9: {
                    imagebuttons[i].setImageResource(R.drawable.smart_ev);
                    imagebuttons[i].setVisibility(View.VISIBLE);
                    textviews[i].setText(R.string.Smart_EV);
                    textviews[i].setVisibility(View.VISIBLE);

                    final EV smart_ev = ev_array[i];
                    final GPV smart_cabriolet = gpv_array[ev_array[i].getId()];

                    final ImageButton imageButton = imagebuttons[i];

                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!ev_selected)  {

                                ev_selected = true;
                                select_ev_continueButton.setBackground(drawable3);
                                imageButton.setBackground(drawable);
                                setCurrentEvGpvSelection(imageButton, smart_ev, smart_cabriolet);
                            }
                            else {

                                current_image_button_selected.setBackground(drawable2);
                                imageButton.setBackground(drawable);
                                setCurrentEvGpvSelection(imageButton, smart_ev, smart_cabriolet);
                            }
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

