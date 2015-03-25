package com.mit.bruno.tripstuffreminder.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mit.bruno.tripstuffreminder.R;
import com.mit.bruno.tripstuffreminder.interfaces.IComponents;
import com.mit.bruno.tripstuffreminder.sqlite.SQLiteHelper;

/**
 * Created by techresult on 24/03/2015.
 */
public class fragment_configuration extends Fragment implements IComponents {
    View rootView;

    private SQLiteHelper sqLiteHelper;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.configuration, container, false);

        initializeComponents();

        return rootView;
    }

    @Override
    public void initializeComponents() {
        sqLiteHelper = new SQLiteHelper(getActivity());
    }
}
