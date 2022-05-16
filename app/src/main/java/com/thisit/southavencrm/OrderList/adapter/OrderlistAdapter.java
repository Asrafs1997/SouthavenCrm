package com.thisit.southavencrm.OrderList.adapter;
;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.thisit.southavencrm.OrderList.model.OrderListResponseModel;
import com.thisit.southavencrm.OrderList.view.IOrderListView;
import com.thisit.southavencrm.R;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderlist, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderlistAdapter.Viewholder holder, int position) {
        OrderListResponseModel orderListResponseModel = locationListResponseModelArrayList.get(position);

        holder.contact_name.setText(orderListResponseModel.getContactName());
        holder.tran_no.setText(orderListResponseModel.getTranNo());
        holder.net_total.setText(orderListResponseModel.getNetTotal());
        holder.tran_date.setText(orderListResponseModel.getTranDate());

    }

    @Override
    public int getItemCount() {
        return locationListResponseModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView contact_name,tran_no,net_total,tran_date;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
             contact_name = itemView.findViewById(R.id.contact_name);
             tran_no = itemView.findViewById(R.id.tran_no);
             net_total = itemView.findViewById(R.id.net_total);
             tran_date = itemView.findViewById(R.id.tran_date);

            itemView.findViewById(R.id.hold_parent_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iOrderListView.holdListClick(getAdapterPosition());
                }
            });
        }
    }
}
