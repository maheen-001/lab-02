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

        // new buttons to add/delete cities
        Button addButton = findViewById(R.id.add_button);
        Button deleteButton = findViewById(R.id.delete_button);

        cityInput = findViewById(R.id.city_input);

        // set up the click listeners
        addButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);


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
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        // if the click was on the delete button
        if (view.getId() == R.id.delete_button) {
            if (selectedCity != null) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged(); // refresh the list after tha change
            }
        }
        // if the click was on the add button
        else if (view.getId() == R.id.add_button) {
            String newCity = cityInput.getText().toString().trim();
            if (!newCity.isEmpty()) {
                // I wanted to validate the user input and make sure it was something in the cities array
                boolean valid = Arrays.asList(cities).contains(newCity);
                if (valid) {
                    dataList.add(newCity); // append the user input into our data list
                    cityAdapter.notifyDataSetChanged(); // refresh the list after the change
                    cityInput.setText(""); // clear input after adding so that we can use it again if needed
                }
            }
        }
    }
}