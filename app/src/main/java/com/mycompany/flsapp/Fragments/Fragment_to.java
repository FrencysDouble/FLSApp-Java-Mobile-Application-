package com.mycompany.flsapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import com.mycompany.flsapp.R;

public class Fragment_to extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_to, container, false);
        new SwipeDismissDialog.Builder(this).setView(rootView).build.show();
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialog);
        return rootView;
    }
}
