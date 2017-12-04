package com.example.rosalina.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MapsActivity extends FragmentActivity {

    private Intent googleMapIntent;
    public Button click;
    public Button mapa;
    public static TextView data;
    public static String waypoints = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        click = (Button) findViewById(R.id.button);
        data = (TextView) findViewById(R.id.text);
        mapa = (Button) findViewById(R.id.button2);

        click.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Connection connection = new Connection();
                connection.execute();

            }
        });
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialize();
            }
        });




    }

    private void initialize() {


        googleMapIntent = new Intent(Intent.ACTION_VIEW);
        googleMapIntent.setData(Uri.parse("https://www.google.com/maps/dir/?api=1&origin=&destination=FATEC+sao+jose+campos&waypoints="+waypoints+"&travelmode=driving&dir_action=navigate"));
        this.startActivity(googleMapIntent);
    }

}