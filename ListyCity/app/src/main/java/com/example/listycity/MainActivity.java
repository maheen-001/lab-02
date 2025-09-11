package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Declare the variables so that we will be able to reference it later
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    String selectedCity = null; // to track which city the user tapped!
    EditText cityInput; // user inout for adding a city

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        // new buttons to add/delete cities and also confirm an addition
        Button addButton = findViewById(R.id.add_button);
        Button deleteButton = findViewById(R.id.delete_button);
        Button confirmButton = findViewById(R.id.confirm_button);

        cityInput = findViewById(R.id.city_input);
        cityInput.setVisibility(View.GONE);

        // set up the click listeners
        addButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        confirmButton.setOnClickListener(this);

        dataList = new ArrayList<>();
        // dataList.addAll(Arrays.asList(cities)); I don't want all cities added initially

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // change selectedCity when the user taps a city
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = dataList.get(position);
            }
        });

    }

    // Handle when a button gets clicked
    @Override
    public void onClick(View view) {
        // I moved the cities array here because it's more relevant
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi, Montreal"};

        // if the click was on the delete button
        if (view.getId() == R.id.delete_button) {
            if (selectedCity != null) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged(); // refresh the list after tha change
            }
        }
        // if the click was on the add button
        else if (view.getId() == R.id.add_button) {
            // toggle the pop-up thing for the confirm button + enter city
            cityInput.setVisibility(View.VISIBLE);
            findViewById(R.id.confirm_button).setVisibility(View.VISIBLE);
        } else if (view.getId() == R.id.confirm_button) {
            // get the user input for the city to be added
            String newCity = cityInput.getText().toString().trim();
            if (!newCity.isEmpty()) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged(); // refresh the list after the change
            }
            // reset and toggle off
            cityInput.setText("");
            cityInput.setVisibility(View.GONE);
            findViewById(R.id.confirm_button).setVisibility(View.GONE);
        }
    }
}