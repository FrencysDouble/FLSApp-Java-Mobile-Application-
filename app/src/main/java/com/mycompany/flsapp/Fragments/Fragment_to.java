package com.mycompany.flsapp.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;
import com.mycompany.flsapp.R;

public class Fragment_to extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.fragment_to, null);
        SwipeDismissDialog swipeDismissDialog = new SwipeDismissDialog.Builder(requireContext())
                .setView(rootView)
                .setHorizontalOscillation(0f)
                .build();


        Dialog dialog = new Dialog(requireContext()) {
            @Override
            public void show() {
                swipeDismissDialog.show();
            }

            @Override
            public void dismiss() {
                swipeDismissDialog.dismiss();
            }
        };
        return dialog;
    }

}
