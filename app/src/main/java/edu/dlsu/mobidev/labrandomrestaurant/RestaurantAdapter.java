package edu.dlsu.mobidev.labrandomrestaurant;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by courtneyngo on 10/15/16.
 */
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>{

    ArrayList<Restaurant> restaurantList;
    private OnItemClickLister onItemClickLister;

    public RestaurantAdapter(ArrayList<Restaurant> restaurantList){
        this.restaurantList = restaurantList;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_restaurant, parent, false);
        return new RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        restaurant.setListPosition(position);
        holder.tvName.setText(restaurant.getName());
        holder.tvWeight.setText(String.valueOf(restaurant.getWeight()));
        holder.container.setTag(R.id.list_object_restaurant, restaurant);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Restaurant restaurant = (Restaurant) v.getTag(R.id.list_object_restaurant);
                onItemClickLister.onItemClick(restaurant);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public void addRestaurant(Restaurant restaurant){
        restaurantList.add(restaurant);
        notifyItemInserted(restaurantList.size()-1);
    }

    public void editRestaurant(Restaurant restaurant){
        restaurantList.set(restaurant.getListPosition(), restaurant);
        notifyItemChanged(restaurant.getListPosition());
    }

    public ArrayList<Restaurant> getRestaurantList(){
        return restaurantList;
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvWeight;
        View container;

        public RestaurantViewHolder(View itemView){
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvWeight = (TextView) itemView.findViewById(R.id.tv_weight);
            container = itemView.findViewById(R.id.container);
        }
    }

    public interface OnItemClickLister{
        public void onItemClick(Restaurant restaurant);
    }

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }
}
