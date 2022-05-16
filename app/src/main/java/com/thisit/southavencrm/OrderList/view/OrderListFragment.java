package com.thisit.southavencrm.OrderList.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.thisit.southavencrm.FAQList.model.FAQListResponseModel;
import com.thisit.southavencrm.FAQList.presenter.FAQListPresenter;
import com.thisit.southavencrm.FAQList.presenter.IFAQListPresenter;
import com.thisit.southavencrm.FAQList.view.IFAQListView;
import com.thisit.southavencrm.OrderList.adapter.OrderlistAdapter;
import com.thisit.southavencrm.OrderList.model.OrderListResponseModel;
import com.thisit.southavencrm.OrderList.presenter.IOrderListPresenter;
import com.thisit.southavencrm.OrderList.presenter.OrderListPresenter;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.common.ConfigApp;
import com.thisit.southavencrm.dashboard.view.ECardActivity;
import com.thisit.southavencrm.locateUs.adapter.LocationAdapter;
import com.thisit.southavencrm.locateUs.model.LocationListResponseModel;

import java.util.ArrayList;
import java.util.Calendar;

public class OrderListFragment extends Fragment implements IOrderListView {
    private View root;
    private Activity activity;
    private IOrderListPresenter iOrderListPresenter;
    private ArrayList<OrderListResponseModel> orderListResponseModelArrayList;
    private RecyclerView orderlistrecyclerView;
    private EditText fromDate, toDate;
    Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_orderlist, container, false);
        activity = getActivity();

        orderlistrecyclerView = (RecyclerView) root.findViewById(R.id.orderlistrecyclerView);
        fromDate = (EditText) root.findViewById(R.id.ed_fromDate);
        toDate = (EditText) root.findViewById(R.id.ed_to_date);
        iOrderListPresenter = new OrderListPresenter(this);
        if (ConfigApp.isNetworkAvailable(activity)) {
            iOrderListPresenter.locationList(ConfigApp.getCompanyCode());
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        fromDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    datePicker(true);
                }
                return true;
            }
        });
        toDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    datePicker(false);
                }
                return true;
            }
        });
        return root;
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void getLocationList(ArrayList<OrderListResponseModel> holdListResponseModelArrayList) {
        orderListResponseModelArrayList = new ArrayList<OrderListResponseModel>();

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
            }
        }


    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void holdListClick(int position) {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ECardActivity) getActivity()).title_tv.setText(R.string.tran_history);
    }

    private void datePicker(final boolean isfrom) {
        final DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        if (isfrom) {
                            fromDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        } else {
                            toDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
//                        date = (dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                        isDateFirstClick = false;
                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();

    }
}

