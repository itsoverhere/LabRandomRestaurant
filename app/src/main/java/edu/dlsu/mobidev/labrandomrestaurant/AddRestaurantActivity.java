package edu.dlsu.mobidev.labrandomrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddRestaurantActivity extends AppCompatActivity {

    EditText etName, etWeight;
    Button buttonDone;
    Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        etName = (EditText) findViewById(R.id.et_name);
        etWeight = (EditText) findViewById(R.id.et_weight);
        buttonDone = (Button) findViewById(R.id.button_done);

        restaurant = new Restaurant();

        if(getIntent().hasExtra(Restaurant.RESTAURANT) ){
            setTitle("Edit a restaurant");
            restaurant = getIntent().getParcelableExtra(Restaurant.RESTAURANT);
            etName.setText(restaurant.getName());
            etWeight.setText(String.valueOf(restaurant.getWeight()));
        }

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();

                String name = etName.getText().toString();
                int weight = Integer.parseInt(etWeight.getText().toString());

                if(!name.trim().isEmpty() && weight > 0){
                    restaurant.setName(name);
                    restaurant.setWeight(weight);
                    resultIntent.putExtra(Restaurant.RESTAURANT, restaurant);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }else{
                    Snackbar.make(buttonDone, "Please enter a restaurant name and a valid positive weight.", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

}
