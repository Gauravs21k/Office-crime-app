package com.gaurav.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

    return new AlertDialog.Builder(getActivity()).setView(R.layout.dialog_date)
            .setTitle(R.string.date_picker_title)
            .setPositiveButton(android.R.string.ok, null).create();
    }
}
