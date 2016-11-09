package edu.dlsu.mobidev.labrandomrestaurant;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditRestaurant extends AppCompatActivity {

    EditText etName, etWeight;
    Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_restaurant);

        etName = (EditText) findViewById(R.id.et_name);
        etWeight = (EditText) findViewById(R.id.et_weight);
        buttonDone = (Button) findViewById(R.id.button_done);

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();

                String name = etName.getText().toString();
                int weight = Integer.parseInt(etWeight.getText().toString());

                if(!name.trim().isEmpty() && weight > 0){
                    resultIntent.putExtra(Restaurant.NAME, etName.getText().toString());
                    resultIntent.putExtra(Restaurant.WEIGHT, etWeight.getText().toString());
                    setResult(RESULT_OK, resultIntent);
                }else{
                    Snackbar.make(buttonDone, "Please enter a restaurant name and a valid positive weight.", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}
