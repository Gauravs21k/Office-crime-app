package com.gaurav.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {
    private static final String EXTRA_CRIME_ID = "criminalintent.crime_id";

    private ViewPager2 cViewPager2;
    private List<Crime> cCrimes;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        return new Intent(packageContext, CrimePagerActivity.class).putExtra(EXTRA_CRIME_ID, crimeId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        cViewPager2 = findViewById(R.id.crime_view_pager2);
        cCrimes = CrimeLab.get(this).getcCrimes();

        cViewPager2.setAdapter(new FragmentStateAdapter(getSupportFragmentManager(),getLifecycle()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return CrimeFragment.newInstance(cCrimes.get(position).getcId());
            }

            @Override
            public int getItemCount() {
                return cCrimes.size();
            }
        });

       /* for (int i = 0; i < cCrimes.size(); i++) {
            if (cCrimes.get(i).getcId().equals(crimeId)) {
                cViewPager2.setCurrentItem(i);
                break;
            }
        }*/

        cViewPager2.setCurrentItem(cCrimes.indexOf(CrimeLab.get(this).getCrime(crimeId)));
    }
}
