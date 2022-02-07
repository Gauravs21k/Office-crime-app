package com.gaurav.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView cCrimeRecyclerView;
    private CrimeAdapter cAdapter;
    private int position;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity.newIntent(getActivity(),crime.getcId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        cCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        cCrimeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getcCrimes();
        if (cAdapter==null) {
            cAdapter = new CrimeAdapter(crimes);
            cCrimeRecyclerView.setAdapter(cAdapter);
        } else {
            cAdapter.notifyItemChanged(position);
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView cTitleTextView;
        private TextView cDateTextView;
        private ImageView cSolvedImageView;
        private Crime cCrime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            itemView.setOnClickListener(this);
            cTitleTextView = itemView.findViewById(R.id.crime_title);
            cDateTextView = itemView.findViewById(R.id.crime_date);
            cSolvedImageView = itemView.findViewById(R.id.crime_solved);
        }



        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(), cCrime.getcTitle()+" clicked", Toast.LENGTH_SHORT).show();
            position = getAdapterPosition();
            startActivity(CrimePagerActivity.newIntent(getActivity(),cCrime.getcId()));
        }

        public void bind(Crime crime) {
            this.cCrime = crime;
            cTitleTextView.setText(crime.getcTitle());
            cDateTextView.setText(DateFormat.getDateInstance().format(crime.getcDate()));
            cSolvedImageView.setVisibility(cCrime.iscSolved() ? View.VISIBLE :View.GONE);
        }
    }

    private class SeriousCrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView cTitleTextView;
        private TextView cDateTextView;
        private Crime cCrime;

        public SeriousCrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.serious_list_item_crime, parent, false));
            itemView.setOnClickListener(this);
            cTitleTextView = itemView.findViewById(R.id.serious_crime_title);
            cDateTextView = itemView.findViewById(R.id.serious_crime_date);
        }

        public void bind(Crime crime) {
            this.cCrime = crime;
            cTitleTextView.setText(crime.getcTitle());
            cDateTextView.setText(DateFormat.getDateInstance().format(crime.getcDate()));
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(), "serious" +cCrime.getcTitle()+" clicked", Toast.LENGTH_SHORT).show();
            position = getAdapterPosition();
            startActivity(CrimePagerActivity.newIntent(getActivity(), cCrime.getcId()));
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<Crime> cCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            cCrimes = crimes;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            switch (viewType){
                case 0:
                    return new SeriousCrimeHolder(layoutInflater, parent);
                case 1:
                default:
                    return new CrimeHolder(layoutInflater, parent);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            Crime crime = cCrimes.get(position);
            if (holder.getItemViewType()==0) {
                SeriousCrimeHolder seriousCrimeHolder = (SeriousCrimeHolder) holder;
                seriousCrimeHolder.bind(crime);
            }
            else {
                CrimeHolder crimeHolder = (CrimeHolder) holder;
                crimeHolder.bind(crime);
            }
        }

        @Override
        public int getItemCount() {
            return cCrimes.size();
        }

        @Override
        public int getItemViewType(int position) {
            Crime crime = cCrimes.get(position);
            if (crime.iscRequiresPolice())
                return 0;
            else
                return 1;
        }
    }
}
