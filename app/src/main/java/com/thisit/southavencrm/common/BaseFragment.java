package com.thisit.southavencrm.common;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.thisit.southavencrm.dashboard.presenter.GetprofilePresenter;
import com.thisit.southavencrm.dashboard.presenter.IGetprofilePresenter;
import com.thisit.southavencrm.dashboard.view.IGetprofileView;

public class BaseFragment extends Fragment implements IBaseView , IGetprofileView {
    private IGetprofilePresenter iGetprofilePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iGetprofilePresenter =  new GetprofilePresenter(this);
        iGetprofilePresenter.apiCall(ConfigApp.getCompanyCode(), ConfigApp.getContactID());
    }
    @Override
    public void showProgress() {
        if (getActivity() != null) {
            ((BaseActivity) (getActivity())).showProgress();
        }
    }

    @Override
    public void hideProgress() {
        if (getActivity() != null) {
            ((BaseActivity) (getActivity())).hideProgress();
        }
    }

    @Override
    public void CompanyCode() {

    }

    @Override
    public void ContactID() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure() {

    }
}
