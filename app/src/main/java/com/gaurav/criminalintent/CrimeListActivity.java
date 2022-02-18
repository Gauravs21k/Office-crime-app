package com.gaurav.criminalintent;

import android.content.Intent;

import androidx.fragment.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity implements CrimeListFragment.Callbacks{

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    public void onCrimeSelected(Crime crime) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = CrimePagerActivity.newIntent(this, crime.getId());
            startActivity(intent);
        } else {
            Fragment newDetailFragment = CrimeFragment.newInstance(crime.getId());
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.detail_fragment_container, newDetailFragment).commit();
        }
    }
}
