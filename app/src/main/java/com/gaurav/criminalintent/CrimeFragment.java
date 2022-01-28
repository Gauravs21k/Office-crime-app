package com.gaurav.criminalintent;

import static android.widget.CompoundButton.*;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

public class CrimeFragment extends Fragment {
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private Crime cCrime;
    private EditText cTitleField;
    private Button cDateButton;
    private CheckBox cSolvedCheckBox;

    public static Fragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //cCrime = new Crime();
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        cCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        cTitleField = v.findViewById(R.id.crime_title);
        cTitleField.setText(cCrime.getcTitle());
        cTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cCrime.setcTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //this one too blank
            }
        });

        cDateButton = v.findViewById(R.id.crime_date);
        cDateButton.setText(cCrime.getcDate().toString());
        cDateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                DatePickerFragment datePickerDialog = DatePickerFragment.newInstance(cCrime.getcDate());
                datePickerDialog.show(fragmentManager,DIALOG_DATE);
            }
        });

        cSolvedCheckBox = v.findViewById(R.id.is_crime_solved);
        cSolvedCheckBox.setChecked(cCrime.iscSolved());
        cSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cCrime.setcSolved(cSolvedCheckBox.isChecked());
            }
        });

        return v;
    }

}
