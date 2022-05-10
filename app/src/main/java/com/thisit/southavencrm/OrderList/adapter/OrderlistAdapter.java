package com.thisit.southavencrm.OrderList.adapter;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thisit.southavencrm.OrderList.model.OrderListResponseModel;
import com.thisit.southavencrm.OrderList.view.IOrderListView;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.locateUs.model.LocationListResponseModel;
import com.thisit.southavencrm.locateUs.view.ILocationListView;

import java.util.ArrayList;

public class OrderlistAdapter extends RecyclerView.Adapter<OrderlistAdapter.Viewholder> {

    private Context context;
    private ArrayList<OrderListResponseModel> locationListResponseModelArrayList;
    private IOrderListView iOrderListView;
    public OrderlistAdapter(Context context, ArrayList<OrderListResponseModel> courseModelArrayList, IOrderListView iOrderListView) {
        this.context = context;
        this.locationListResponseModelArrayList = courseModelArrayList;
        this.iOrderListView = iOrderListView;
    }



    @NonNull
    @Override
    public OrderlistAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loctionview, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderlistAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        OrderListResponseModel orderListResponseModel = locationListResponseModelArrayList.get(position);

        holder.location_name.setText(orderListResponseModel.getLocationName());







    }

    @Override
    public int getItemCount() {
        return locationListResponseModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView location_name, address_tv, telnumber_tv, fexnumber_tv,Country_tv;
        private ImageView contact_img,directions_img;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            location_name = itemView.findViewById(R.id.location_name);
            Country_tv = itemView.findViewById(R.id.Country_tv);
            address_tv = itemView.findViewById(R.id.address_tv);
            telnumber_tv = itemView.findViewById(R.id.telnumber_tv);
            fexnumber_tv = itemView.findViewById(R.id.fexnumber_tv);
            contact_img = itemView.findViewById(R.id.contact_img);
            directions_img = itemView.findViewById(R.id.directions_img);

            itemView.findViewById(R.id.hold_parent_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iOrderListView.holdListClick(getAdapterPosition());
                }
            });
        }
    }
}
