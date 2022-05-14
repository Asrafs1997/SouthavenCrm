package com.thisit.southavencrm.FAQList.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.thisit.southavencrm.FAQList.adapter.FAQAdapter;
import com.thisit.southavencrm.FAQList.model.FAQListResponseModel;
import com.thisit.southavencrm.FAQList.presenter.FAQListPresenter;
import com.thisit.southavencrm.FAQList.presenter.IFAQListPresenter;
import com.thisit.southavencrm.R;
import com.thisit.southavencrm.common.BaseFragment;
import com.thisit.southavencrm.common.ConfigApp;


import java.util.ArrayList;

public class FQAFragment extends BaseFragment implements IFAQListView {
    private View root;
    private Activity activity;
    private IFAQListPresenter ifaqListPresenter;
    private ArrayList<FAQListResponseModel> faqListResponseModelArrayList;
private RecyclerView faqlistrecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_faqlist, container, false);
        activity = getActivity();

        ifaqListPresenter = new FAQListPresenter(this);
        if (ConfigApp.isNetworkAvailable(activity)) {
            ifaqListPresenter.locationList(ConfigApp.getCompanyCode());
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        faqlistrecyclerView=(RecyclerView)root.findViewById(R.id.faqlistrecyclerView);
        return root;
    }



    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void getLocationList(ArrayList<FAQListResponseModel> holdListResponseModelArrayList) {
        faqListResponseModelArrayList = new ArrayList<FAQListResponseModel>();
        for (int i = 0; i < holdListResponseModelArrayList.size(); i++) {
            FAQListResponseModel faqListResponseModel = new FAQListResponseModel();
            faqListResponseModel.set$id(holdListResponseModelArrayList.get(i).get$id());
            faqListResponseModel.setCode(holdListResponseModelArrayList.get(i).getCode());
            faqListResponseModel.setDate(holdListResponseModelArrayList.get(i).getDate());
            faqListResponseModel.setTitle(holdListResponseModelArrayList.get(i).getTitle());
            faqListResponseModel.setShortDescription(holdListResponseModelArrayList.get(i).getShortDescription());
            faqListResponseModelArrayList.add(faqListResponseModel);


            System.out.println("faqListResponseModelArrayList\t\t\t"+faqListResponseModelArrayList.size());
        }


        if (faqListResponseModelArrayList.size() > 0) {
            faqlistrecyclerView.setLayoutManager(new LinearLayoutManager(activity));
            FAQAdapter adapter = new FAQAdapter(activity, faqListResponseModelArrayList, this);
            faqlistrecyclerView.setAdapter(adapter);
            //recyclerView.setItemAnimator(new DefaultItemAnimator());


        }

    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void holdListClick(int position) {

    }


}

