package edu.dlsu.mobidev.labrandomrestaurant;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by courtneyngo on 10/15/16.
 */
public class RestaurantAdapterSkeleton extends RecyclerView.Adapter<RestaurantAdapterSkeleton.RestaurantViewHolder>{

    ArrayList<Restaurant> restaurantList;
    private OnItemClickLister onItemClickLister;

    public RestaurantAdapterSkeleton(ArrayList<Restaurant> restaurantList){
        this.restaurantList = restaurantList;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO
        return null;
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        restaurant.setListPosition(position);
        // TODO update the display

        /*
        // This code will "tag" a value to the container
        // The value, restaurant at this position, can be used later when the item is clicked
        holder.container.setTag(restaurant);

        // if an item is clicked, we update the listener in MainActivity of the event
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here, we retrieve the "tag" we set earlier, which will return the Restaurant object
                Restaurant restaurant = (Restaurant) v.getTag();
                // and we flag the listener (MainActivity's implementation) that an item has been clicked
                onItemClickLister.onItemClick(restaurant);
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public void addRestaurant(Restaurant restaurant){
        restaurantList.add(restaurant);
        // notifyItemChanged will add a new item on the list, avoiding to update the whole list
        notifyItemInserted(restaurantList.size()-1);
    }

    // DO NOT CHANGE
    // Call this method if you want to edit a restaurant
    public void editRestaurant(Restaurant restaurant){
        restaurantList.set(restaurant.getListPosition(), restaurant);
        // notifyItemChanged will update the item on that position, not the whole list
        notifyItemChanged(restaurant.getListPosition());
    }

    public ArrayList<Restaurant> getRestaurantList(){
        return restaurantList;
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{
        View container; // assign this to ViewGroup (Relative or Linear) of the list item
        // TODO

        public RestaurantViewHolder(View itemView){
            super(itemView);
            // TODO
        }
    }

    public interface OnItemClickLister{
        public void onItemClick(Restaurant restaurant);
    }

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }
}
