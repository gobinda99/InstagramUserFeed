package com.company.instagramusersfeed.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.instagramusersfeed.App;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by gobinda on 11/12/16.
 */

public abstract class AbstractBaseFragment extends Fragment{

    private Unbinder mUnbinder;

    @Inject
    SharedPreferences mPreferences;


    public View inflateLayout(int resource, ViewGroup container) {
        return inflateLayout(resource, container, false);
    }

    public View inflateLayout(int resource, ViewGroup container, boolean attachToRoot) {
        View view = LayoutInflater.from(getActivity()).inflate(resource, container, attachToRoot);
        // inject butter knife
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((App)getActivity().getApplication()).getComponent().inject(this);

    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    @SuppressWarnings("deprecation")
    @Deprecated
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    protected void onAttachToContext(Context context) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}
