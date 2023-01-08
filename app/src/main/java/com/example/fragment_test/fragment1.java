package com.example.fragment_test;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class fragment1 extends Fragment {

    private static final int REQUEST_LOCATION = 1;

    Button getLocationBtn;
    TextView showLocationTxt, weatherTxt, city_country;
    ImageView weatherImage;

    // location info
    LocationManager locationManager;
    String latitude,longitude;

    // json parsing
    private RequestQueue queue;

    // weather info
//    String mainDescription, description, cityName, icon;
//    double temp, windSpeed;
//    int pressure, humidity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        // Add permission
        ActivityCompat.requestPermissions(getActivity(), new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        showLocationTxt = view.findViewById(R.id.show_location);
        getLocationBtn = view.findViewById(R.id.getLocation);
        weatherTxt = view.findViewById(R.id.weatherDataTextView);
        weatherImage = view.findViewById(R.id.weatherImageView);
        city_country = view.findViewById(R.id.city_country);


        getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

                //check if GPS is enabled
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    // Write method to enable GPS
                    onGPS();
                } else {
                    // GPS is already on then
                    getLocation();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void getLocation() {
        // check permission again
        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location locationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();

                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

                showLocationTxt.setText("Your Location:" + "\n" +
                        "Latitude: " + latitude + "\n" +
                        "Longitude: " + longitude);
                jsonParse(lat, longi);

            } else if (locationNetwork != null) {
                double lat = locationNetwork.getLatitude();
                double longi = locationNetwork.getLongitude();

                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

                showLocationTxt.setText("Your Location:" + "\n" +
                        "Latitude: " + latitude + "\n" +
                        "Longitude: " + longitude);
                jsonParse(lat, longi);

            } else if (locationPassive != null) {
                double lat = locationPassive.getLatitude();
                double longi = locationPassive.getLongitude();

                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

                showLocationTxt.setText("Your Location:" + "\n" +
                        "Latitude: " + latitude + "\n" +
                        "Longitude: " + longitude);
                jsonParse(lat, longi);
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Can't get your location", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void onGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity().getApplicationContext());

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void jsonParse(double latitude, double longitude) {
        String lati = String.valueOf(latitude);
        String longi = String.valueOf(longitude);
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lati + "&lon=" + longi +
                "&appid=8da6492738337503ce2b55e76c52c78d&units=metric";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray weather = response.getJSONArray("weather");
                            JSONObject weatherOBJ = weather.getJSONObject(0);
                            String icon = weatherOBJ.getString("icon");
                            String mainDescription = weatherOBJ.getString("main");
                            String description = weatherOBJ.getString("description");

                            JSONObject weatherData = response.getJSONObject("main");

                            double temp = weatherData.getDouble("temp");
                            int pressure = weatherData.getInt("pressure");
                            int humidity = weatherData.getInt("humidity");

                            JSONObject wind = response.getJSONObject("wind");
                            double windSpeed = wind.getDouble("speed");

                            String cityName = response.getString("name");

                            JSONObject sys = response.getJSONObject("sys");
                            String countryName = sys.getString("country");

                            city_country.setText(cityName + ", " + countryName);

                            weatherTxt.setText("Temp: " + temp + "\n" + "Humidity: " + humidity + "\n"
                            + "Pressure: " + pressure + "\n" + "Weather: " + mainDescription + "\n"
                            + "Description: " + description +"\n" + "Wind Speed: " + windSpeed + "\n");

                            produceImage(icon);

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

    private void produceImage(String icon) {
        weatherImage.setImageBitmap(null);

        String url = "http://openweathermap.org/img/wn/" + icon + "@2x.png";

        Picasso.get().load(url).into(weatherImage);
    }

}