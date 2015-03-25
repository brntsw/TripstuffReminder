package com.mit.bruno.tripstuffreminder.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mit.bruno.tripstuffreminder.R;
import com.mit.bruno.tripstuffreminder.interfaces.IComponents;

/**
 * Created by techresult on 24/03/2015.
 */
public class FragmentHelp extends Fragment {
    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.help, container, false);

        return rootView;
    }
}
