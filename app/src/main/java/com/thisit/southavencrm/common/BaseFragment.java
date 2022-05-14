package com.thisit.southavencrm.common;


import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment implements IBaseView {

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

}
