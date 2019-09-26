package com.example.intentserviceexample;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressFragment extends DialogFragment {
    ProgressDialog progressDialog;


    public ProgressFragment() {
        // Required empty public constructor
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
         super.onCreateDialog(savedInstanceState);

         progressDialog = new ProgressDialog(getActivity());
         progressDialog.setTitle("Image is Downloading");
         progressDialog.setMessage("please wait....");
         progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         return progressDialog;

    }
}
