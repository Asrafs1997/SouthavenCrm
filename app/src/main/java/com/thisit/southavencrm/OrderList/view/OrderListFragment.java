package com.thisit.southavencrm.OrderList.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.thisit.southavencrm.FAQList.model.FAQListResponseModel;
import com.thisit.southavencrm.FAQList.presenter.FAQListPresenter;
import com.thisit.southavencrm.FAQList.presenter.IFAQListPresenter;
import com.thisit.southavencrm.FAQList.view.IFAQListView;
import com.thisit.southavencrm.OrderDetail.view.OrderDetailActivity;
import com.thisit.southavencrm.OrderList.adapter.OrderlistAdapter;
import com.thisit.southavencrm.OrderList.model.OrderListResponseModel;
import com.thisit.southavencrm.OrderList.presenter.IOrderListPresenter;
import com.thisit.southavencrm.OrderList.presenter.OrderListPresenter;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.common.BaseFragment;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.dashboard.view.ECardActivity;
import com.thisit.southavencrm.locateUs.adapter.LocationAdapter;
import com.thisit.southavencrm.locateUs.model.LocationListResponseModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class OrderListFragment extends BaseFragment implements IOrderListView {
    private View root;
    private Activity activity;
    private static IOrderListPresenter iOrderListPresenter;
    private ArrayList<OrderListResponseModel> orderListResponseModelArrayList;
    private RecyclerView orderlistrecyclerView;
    private static TextView fromDate, toDate;
    private TextView record_text;
    static boolean  isfrom;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_orderlist, container, false);
        activity = getActivity();

        orderlistrecyclerView =  root.findViewById(R.id.orderlistrecyclerView);
        fromDate =  root.findViewById(R.id.ed_fromDate);
        toDate =  root.findViewById(R.id.ed_to_date);
        record_text =  root.findViewById(R.id.no_record_text);
        iOrderListPresenter = new OrderListPresenter(this);


        fromDate.setText(ConfigApp.calenderOneMonthBeforDate());
        toDate.setText(ConfigApp.calenderCurrentDate());


        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isfrom = true;
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getActivity().getFragmentManager(), "DatePicker");

            }
        });


        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isfrom = false;
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getActivity().getFragmentManager(), "DatePicker");
            }
        });

        if (ConfigApp.isNetworkAvailable(activity)) {
            iOrderListPresenter.locationList(ConfigApp.getCompanyCode(),fromDate.getText().toString(),toDate.getText().toString());
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        return root;
    }




    @Override
    public void getLocationList(ArrayList<OrderListResponseModel> holdListResponseModelArrayList) {
        orderListResponseModelArrayList = new ArrayList<OrderListResponseModel>();
        if (holdListResponseModelArrayList.size() > 0) {
            record_text.setVisibility(View.GONE);
            orderlistrecyclerView.setVisibility(View.VISIBLE);
            for (int i = 0; i < holdListResponseModelArrayList.size(); i++) {
                OrderListResponseModel orderListResponseModel = new OrderListResponseModel();
                orderListResponseModel.set$id(holdListResponseModelArrayList.get(i).get$id());
                orderListResponseModel.setCompanyCode(holdListResponseModelArrayList.get(i).getCompanyCode());
                orderListResponseModel.setTranNo(holdListResponseModelArrayList.get(i).getTranNo());
                orderListResponseModel.setTranDate(holdListResponseModelArrayList.get(i).getTranDate());
                orderListResponseModel.setDeliveryDate(holdListResponseModelArrayList.get(i).getDeliveryDate());
                orderListResponseModel.setDeliveryName(holdListResponseModelArrayList.get(i).getDeliveryName());
                orderListResponseModel.setContactID(holdListResponseModelArrayList.get(i).getContactID());
                orderListResponseModel.setContactCode(holdListResponseModelArrayList.get(i).getContactCode());
                orderListResponseModel.setContactName(holdListResponseModelArrayList.get(i).getContactName());
                orderListResponseModel.setSubTotal(holdListResponseModelArrayList.get(i).getSubTotal());
                orderListResponseModel.setTax(holdListResponseModelArrayList.get(i).getTax());
                orderListResponseModel.setNetTotal(holdListResponseModelArrayList.get(i).getNetTotal());
                orderListResponseModel.setFinalTotal(holdListResponseModelArrayList.get(i).getFinalTotal());
                orderListResponseModel.setBalanceAmount(holdListResponseModelArrayList.get(i).getBalanceAmount());
                orderListResponseModel.setLocationName(holdListResponseModelArrayList.get(i).getLocationName());
                orderListResponseModel.setLocationCode(holdListResponseModelArrayList.get(i).getLocationCode());
                orderListResponseModelArrayList.add(orderListResponseModel);
                if (orderListResponseModelArrayList.size() > 0) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                    OrderlistAdapter adapter = new OrderlistAdapter(activity, orderListResponseModelArrayList, this);
                    orderlistrecyclerView.setLayoutManager(linearLayoutManager);
                    orderlistrecyclerView.setAdapter(adapter);
                    record_text.setVisibility(View.GONE);
                    orderlistrecyclerView.setVisibility(View.VISIBLE);
                } else {
                    record_text.setVisibility(View.VISIBLE);
                    orderlistrecyclerView.setVisibility(View.GONE);
                }
            }
        }else {
            record_text.setVisibility(View.VISIBLE);
            orderlistrecyclerView.setVisibility(View.GONE);
        }

    }


    @Override
    public void onSuccess() {

    }


    @Override
    public void holdListClick(int position) {
        Intent intent = new Intent(activity, OrderDetailActivity.class);
        intent.putExtra("CompanyCode", orderListResponseModelArrayList.get(position).getCompanyCode());
        intent.putExtra("TranNo", orderListResponseModelArrayList.get(position).getTranNo());
        startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ECardActivity) getActivity()).title_tv.setText(R.string.tran_history);
    }


    /*public void onDateSet(DatePicker view, int year,
                          int monthOfYear, int dayOfMonth) {
        if (isfrom) {
            fromDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        } else {
            toDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        }
//                        date = (dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                        isDateFirstClick = false;
    }*/

                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        if (isfrom) {
                            fromDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        } else {
                            toDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
//                        date = (dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                        isDateFirstClick = false;
                    }


    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);

            //return new DatePickerDialog(getActivity(), android.R.style.Theme_Material_Light_Dialog_NoActionBar, this, yy, mm, dd);
            return new DatePickerDialog(getActivity(), android.R.style.Theme_Material_Light_Dialog_Alert, this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm + 1, dd);
        }

        public void populateSetDate(int year, int month, int day) {
            DecimalFormat mFormat = new DecimalFormat("00");
            String Dates = mFormat.format(Double.valueOf(day)) + "/" + mFormat.format(Double.valueOf(month)) + "/" + mFormat.format(Double.valueOf(year));
            if (isfrom) {
                fromDate.setText(Dates);
            } else {
                toDate.setText(Dates);
            }

            if (ConfigApp.isNetworkAvailable(getActivity())) {
                iOrderListPresenter.locationList(ConfigApp.getCompanyCode(),fromDate.getText().toString(),toDate.getText().toString());
            } else {
                Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        }

    }


}

