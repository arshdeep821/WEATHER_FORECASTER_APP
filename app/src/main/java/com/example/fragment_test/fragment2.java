package com.example.fragment_test;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class fragment2 extends Fragment {

    private TextView textViewResult;
    private TextView cityCountry;
    private RequestQueue queue;
    private JSONArray list;

    private double latitude;
    private double longitude;

    //for fetching image;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;
    private ImageView image7;
    private ImageView image8;
    private ImageView image9;
    private ImageView image10;
    private ImageView image11;
    private ImageView image12;
    private ImageView image13;
    private ImageView image14;
    private ImageView image15;
    private ImageView image16;
    private ImageView image17;
    private ImageView image18;
    private ImageView image19;
    private ImageView image20;
    private ImageView image21;
    private ImageView image22;
    private ImageView image23;
    private ImageView image24;
    private ImageView image25;
    private ImageView image26;
    private ImageView image27;
    private ImageView image28;
    private ImageView image29;
    private ImageView image30;
    private ImageView image31;
    private ImageView image32;
    private ImageView image33;
    private ImageView image34;
    private ImageView image35;
    private ImageView image36;
    private ImageView image37;
    private ImageView image38;
    private ImageView image39;
    private ImageView image40;
    // list of image codes
    ArrayList<String> imageCodes;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);

        // weather data
        textViewResult = view.findViewById(R.id.test_view_result);
        // city name + country
        cityCountry = view.findViewById(R.id.city_country);

        //find images by id
        initAllImageView(view);

        Button buttonParse = view.findViewById(R.id.button_parse);

        imageCodes = new ArrayList<>();

        queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        buttonParse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText cityEditView = getView().findViewById(R.id.city_enter_textview);
                String city = cityEditView.getText().toString();
                textViewResult.setText("");

                //addCity(city);

                jsonParse(city);



            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    private void initAllImageView(View view) {
        image1 = view.findViewById(R.id.imageView1);
        image2 = view.findViewById(R.id.imageView2);
        image3 = view.findViewById(R.id.imageView3);
        image4 = view.findViewById(R.id.imageView4);
        image5 = view.findViewById(R.id.imageView5);
        image6 = view.findViewById(R.id.imageView6);
        image7 = view.findViewById(R.id.imageView7);
        image8 = view.findViewById(R.id.imageView8);
        image9 = view.findViewById(R.id.imageView9);
        image10 = view.findViewById(R.id.imageView10);
        image11 = view.findViewById(R.id.imageView11);
        image12 = view.findViewById(R.id.imageView12);
        image13 = view.findViewById(R.id.imageView13);
        image14 = view.findViewById(R.id.imageView14);
        image15 = view.findViewById(R.id.imageView15);
        image16 = view.findViewById(R.id.imageView16);
        image17 = view.findViewById(R.id.imageView17);
        image18 = view.findViewById(R.id.imageView18);
        image19 = view.findViewById(R.id.imageView19);
        image20 = view.findViewById(R.id.imageView20);
        image21 = view.findViewById(R.id.imageView21);
        image22 = view.findViewById(R.id.imageView22);
        image23 = view.findViewById(R.id.imageView23);
        image24 = view.findViewById(R.id.imageView24);
        image25 = view.findViewById(R.id.imageView25);
        image26 = view.findViewById(R.id.imageView26);
        image27 = view.findViewById(R.id.imageView27);
        image28 = view.findViewById(R.id.imageView28);
        image29 = view.findViewById(R.id.imageView29);
        image30 = view.findViewById(R.id.imageView30);
        image31 = view.findViewById(R.id.imageView31);
        image32 = view.findViewById(R.id.imageView32);
        image33 = view.findViewById(R.id.imageView33);
        image34 = view.findViewById(R.id.imageView34);
        image35 = view.findViewById(R.id.imageView35);
        image36 = view.findViewById(R.id.imageView36);
        image37 = view.findViewById(R.id.imageView37);
        image38 = view.findViewById(R.id.imageView38);
        image39 = view.findViewById(R.id.imageView39);
        image40 = view.findViewById(R.id.imageView40);
    }

    private void jsonParse(String city) {
        String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + city +
                "&appid=8da6492738337503ce2b55e76c52c78d&units=metric";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            list = response.getJSONArray("list");
                            JSONObject city = response.getJSONObject("city");
                            JSONObject cordinates = city.getJSONObject("coord");
                            String cityName = city.getString("name");
                            String country = city.getString("country");
                            cityCountry.setText(cityName+", " + country +"\n\n");
                            latitude = cordinates.getDouble("lat");
                            longitude = cordinates.getDouble("lon");

                            for (int i = 0; i < list.length(); i++) {
                                JSONObject day = list.getJSONObject(i);
                                JSONObject main = day.getJSONObject("main");
                                double temp = main.getDouble("temp");
                                double humidity = main.getDouble("humidity");
                                double pressure = main.getDouble("pressure");
                                JSONArray weather = day.getJSONArray("weather");
                                JSONObject weatherobj = weather.getJSONObject(0);
                                String mainWeather = weatherobj.getString("main");
                                String description = weatherobj.getString("description");

                                String imageCode = weatherobj.getString("icon");
                                imageCodes.add(imageCode);


                                JSONObject wind = day.getJSONObject("wind");
                                double windSpeed = wind.getDouble("speed");
                                String date = day.getString("dt_txt");

                                textViewResult.append("Temp: " + temp + "\n" + "humidity: " + humidity +
                                        "\n" + "Pressure: " + pressure + "\n" + "Weather: " + mainWeather +
                                        "\n" + "Description: " + description + "\n" + "Wind speed: "
                                        + windSpeed + "\n" + "Date: " + date + "\n\n");
                            }
                            produceImage();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("app", "Error");
            }

        });
        queue.add(request);
    }

    private void produceImage() {
        // set all images to null
        setImagesToNull();

        // get all the images URL's
        String imageURL1 = "http://openweathermap.org/img/wn/" + imageCodes.get(0) + "@2x.png";
        String imageURL2 = "http://openweathermap.org/img/wn/" + imageCodes.get(1) + "@2x.png";
        String imageURL3 = "http://openweathermap.org/img/wn/" + imageCodes.get(2) + "@2x.png";
        String imageURL4 = "http://openweathermap.org/img/wn/" + imageCodes.get(3) + "@2x.png";
        String imageURL5 = "http://openweathermap.org/img/wn/" + imageCodes.get(4) + "@2x.png";
        String imageURL6 = "http://openweathermap.org/img/wn/" + imageCodes.get(5) + "@2x.png";
        String imageURL7 = "http://openweathermap.org/img/wn/" + imageCodes.get(6) + "@2x.png";
        String imageURL8 = "http://openweathermap.org/img/wn/" + imageCodes.get(7) + "@2x.png";
        String imageURL9 = "http://openweathermap.org/img/wn/" + imageCodes.get(8) + "@2x.png";
        String imageURL10 = "http://openweathermap.org/img/wn/" + imageCodes.get(9) + "@2x.png";
        String imageURL11 = "http://openweathermap.org/img/wn/" + imageCodes.get(10) + "@2x.png";
        String imageURL12 = "http://openweathermap.org/img/wn/" + imageCodes.get(11) + "@2x.png";
        String imageURL13 = "http://openweathermap.org/img/wn/" + imageCodes.get(12) + "@2x.png";
        String imageURL14 = "http://openweathermap.org/img/wn/" + imageCodes.get(13) + "@2x.png";
        String imageURL15 = "http://openweathermap.org/img/wn/" + imageCodes.get(14) + "@2x.png";
        String imageURL16 = "http://openweathermap.org/img/wn/" + imageCodes.get(15) + "@2x.png";
        String imageURL17 = "http://openweathermap.org/img/wn/" + imageCodes.get(16) + "@2x.png";
        String imageURL18 = "http://openweathermap.org/img/wn/" + imageCodes.get(17) + "@2x.png";
        String imageURL19 = "http://openweathermap.org/img/wn/" + imageCodes.get(18) + "@2x.png";
        String imageURL20 = "http://openweathermap.org/img/wn/" + imageCodes.get(19) + "@2x.png";
        String imageURL21 = "http://openweathermap.org/img/wn/" + imageCodes.get(20) + "@2x.png";
        String imageURL22 = "http://openweathermap.org/img/wn/" + imageCodes.get(21) + "@2x.png";
        String imageURL23 = "http://openweathermap.org/img/wn/" + imageCodes.get(22) + "@2x.png";
        String imageURL24 = "http://openweathermap.org/img/wn/" + imageCodes.get(23) + "@2x.png";
        String imageURL25 = "http://openweathermap.org/img/wn/" + imageCodes.get(24) + "@2x.png";
        String imageURL26 = "http://openweathermap.org/img/wn/" + imageCodes.get(25) + "@2x.png";
        String imageURL27 = "http://openweathermap.org/img/wn/" + imageCodes.get(26) + "@2x.png";
        String imageURL28 = "http://openweathermap.org/img/wn/" + imageCodes.get(27) + "@2x.png";
        String imageURL29 = "http://openweathermap.org/img/wn/" + imageCodes.get(28) + "@2x.png";
        String imageURL30 = "http://openweathermap.org/img/wn/" + imageCodes.get(29) + "@2x.png";
        String imageURL31 = "http://openweathermap.org/img/wn/" + imageCodes.get(30) + "@2x.png";
        String imageURL32 = "http://openweathermap.org/img/wn/" + imageCodes.get(31) + "@2x.png";
        String imageURL33 = "http://openweathermap.org/img/wn/" + imageCodes.get(32) + "@2x.png";
        String imageURL34 = "http://openweathermap.org/img/wn/" + imageCodes.get(33) + "@2x.png";
        String imageURL35 = "http://openweathermap.org/img/wn/" + imageCodes.get(34) + "@2x.png";
        String imageURL36 = "http://openweathermap.org/img/wn/" + imageCodes.get(35) + "@2x.png";
        String imageURL37 = "http://openweathermap.org/img/wn/" + imageCodes.get(36) + "@2x.png";
        String imageURL38 = "http://openweathermap.org/img/wn/" + imageCodes.get(37) + "@2x.png";
        String imageURL39 = "http://openweathermap.org/img/wn/" + imageCodes.get(38) + "@2x.png";
        String imageURL40 = "http://openweathermap.org/img/wn/" + imageCodes.get(39) + "@2x.png";

        // clear the list after using it
        imageCodes.clear();

        // load the images
        Picasso.get().load(imageURL1).into(image1);
        Picasso.get().load(imageURL2).into(image2);
        Picasso.get().load(imageURL3).into(image3);
        Picasso.get().load(imageURL4).into(image4);
        Picasso.get().load(imageURL5).into(image5);
        Picasso.get().load(imageURL6).into(image6);
        Picasso.get().load(imageURL7).into(image7);
        Picasso.get().load(imageURL8).into(image8);
        Picasso.get().load(imageURL9).into(image9);
        Picasso.get().load(imageURL10).into(image10);
        Picasso.get().load(imageURL11).into(image11);
        Picasso.get().load(imageURL12).into(image12);
        Picasso.get().load(imageURL13).into(image13);
        Picasso.get().load(imageURL14).into(image14);
        Picasso.get().load(imageURL15).into(image15);
        Picasso.get().load(imageURL16).into(image16);
        Picasso.get().load(imageURL17).into(image17);
        Picasso.get().load(imageURL18).into(image18);
        Picasso.get().load(imageURL19).into(image19);
        Picasso.get().load(imageURL20).into(image20);
        Picasso.get().load(imageURL21).into(image21);
        Picasso.get().load(imageURL22).into(image22);
        Picasso.get().load(imageURL23).into(image23);
        Picasso.get().load(imageURL24).into(image24);
        Picasso.get().load(imageURL25).into(image25);
        Picasso.get().load(imageURL26).into(image26);
        Picasso.get().load(imageURL27).into(image27);
        Picasso.get().load(imageURL28).into(image28);
        Picasso.get().load(imageURL29).into(image29);
        Picasso.get().load(imageURL30).into(image30);
        Picasso.get().load(imageURL31).into(image31);
        Picasso.get().load(imageURL32).into(image32);
        Picasso.get().load(imageURL33).into(image33);
        Picasso.get().load(imageURL34).into(image34);
        Picasso.get().load(imageURL35).into(image35);
        Picasso.get().load(imageURL36).into(image36);
        Picasso.get().load(imageURL37).into(image37);
        Picasso.get().load(imageURL38).into(image38);
        Picasso.get().load(imageURL39).into(image39);
        Picasso.get().load(imageURL40).into(image40);
    }

    private void setImagesToNull() {
        image1.setImageBitmap(null);
        image2.setImageBitmap(null);
        image3.setImageBitmap(null);
        image4.setImageBitmap(null);
        image5.setImageBitmap(null);
        image6.setImageBitmap(null);
        image7.setImageBitmap(null);
        image8.setImageBitmap(null);
        image9.setImageBitmap(null);
        image10.setImageBitmap(null);
        image11.setImageBitmap(null);
        image12.setImageBitmap(null);
        image13.setImageBitmap(null);
        image14.setImageBitmap(null);
        image15.setImageBitmap(null);
        image16.setImageBitmap(null);
        image17.setImageBitmap(null);
        image18.setImageBitmap(null);
        image19.setImageBitmap(null);
        image20.setImageBitmap(null);
        image21.setImageBitmap(null);
        image22.setImageBitmap(null);
        image23.setImageBitmap(null);
        image24.setImageBitmap(null);
        image25.setImageBitmap(null);
        image26.setImageBitmap(null);
        image27.setImageBitmap(null);
        image28.setImageBitmap(null);
        image29.setImageBitmap(null);
        image30.setImageBitmap(null);
        image31.setImageBitmap(null);
        image32.setImageBitmap(null);
        image33.setImageBitmap(null);
        image34.setImageBitmap(null);
        image35.setImageBitmap(null);
        image36.setImageBitmap(null);
        image37.setImageBitmap(null);
        image38.setImageBitmap(null);
        image39.setImageBitmap(null);
        image40.setImageBitmap(null);
    }

}