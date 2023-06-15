package com.mycompany.flsapp.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mycompany.flsapp.R;

public class BottomSheetFragment extends BottomSheetDialogFragment {
SearchView searchView;

    public BottomSheetFragment() {
        // Пустой конструктор обязателен
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        dialog.getBehavior().setPeekHeight(1800);
        return dialog;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.bottom_sheet_fragment, container, false);

        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setFocusable(true);
        searchView.setIconified(false);

        return view;
    }

}
