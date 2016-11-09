package edu.dlsu.mobidev.labrandomrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public final static int REQUEST_CODE_ADD_RESTAURANT = 0;
    public final static int REQUEST_CODE_EDIT_RESTAURANT = 1;

    Button buttonAdd, buttonSurprise;
    RecyclerView rvRestaurants;
    ArrayList<Restaurant> restaurantList;
    RestaurantAdapter restaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = (Button) findViewById(R.id.button_add);
        buttonSurprise = (Button) findViewById(R.id.button_surprise);
        rvRestaurants = (RecyclerView) findViewById(R.id.rv_restaurants);

        restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant("Shakey's", 1));
        restaurantList.add(new Restaurant("McDonald's", 1));
        restaurantList.add(new Restaurant("Healthy Corner", 4));
        restaurantList.add(new Restaurant("Good Munch", 3));
        restaurantList.add(new Restaurant("Jus n Jerry's", 4));
        restaurantList.add(new Restaurant("Potato Corner", 3));
        restaurantList.add(new Restaurant("Tori Box", 2));

        restaurantAdapter = new RestaurantAdapter(restaurantList);
        rvRestaurants.setAdapter(restaurantAdapter);
        rvRestaurants.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));

        restaurantAdapter.setOnItemClickLister(new RestaurantAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(Restaurant restaurant) {
                Intent intent = new Intent(getBaseContext(), AddRestaurantActivity.class);
                intent.putExtra(Restaurant.RESTAURANT, restaurant);
                startActivityForResult(intent, REQUEST_CODE_EDIT_RESTAURANT);
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getBaseContext(), AddRestaurantActivity.class), REQUEST_CODE_ADD_RESTAURANT);
            }
        });

        buttonSurprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(buttonSurprise, "Eat at " + getRandomRestaurant(restaurantAdapter).getName(), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public Restaurant getRandomRestaurant(RestaurantAdapter restaurantAdapter){
        ArrayList<Integer> weightedRestaurantList = new ArrayList<>();
        ArrayList<Restaurant> restaurantList = restaurantAdapter.getRestaurantList();
        for(int i = 0; i < restaurantList.size(); i++){
            int weight = restaurantList.get(i).getWeight();
            for(int j = 0; j < weight; j++){
                weightedRestaurantList.add(i);
            }
        }
        return restaurantList.get(weightedRestaurantList.get(new Random().nextInt(weightedRestaurantList.size())));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_ADD_RESTAURANT && resultCode == RESULT_OK){
//            Restaurant restaurant = new Restaurant(data.getStringExtra(Restaurant.NAME), data.getIntExtra(Restaurant.WEIGHT, 1));
            Restaurant restaurant = data.getParcelableExtra(Restaurant.RESTAURANT);
//            restaurantList.add(restaurant);
            restaurantAdapter.addRestaurant(restaurant);
        }else if(requestCode == REQUEST_CODE_EDIT_RESTAURANT && resultCode == RESULT_OK){
            Restaurant restaurant = data.getParcelableExtra(Restaurant.RESTAURANT);
            restaurantAdapter.editRestaurant(restaurant);
        }
    }
}
