package com.gaurav.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> cCrimes;

    private CrimeLab(Context context) {
        cCrimes = new ArrayList<>();
    }

    public void addCrime(Crime crime) {
        cCrimes.add(crime);
    }

    public List<Crime> getcCrimes(){
        return cCrimes;
    }

    public Crime getCrime(UUID id){
        for (Crime crime: cCrimes){
            if (crime.getcId().equals(id))
                return crime;
        }
        return null;
    }

    public static CrimeLab get(Context context){
        if (sCrimeLab == null)
            sCrimeLab = new CrimeLab(context);
        return sCrimeLab;
    }

    public void deleteCrime(Crime cCrime) {
        for (Crime crime: cCrimes) {
            if (crime.equals(cCrime)) {
                cCrimes.remove(cCrime);
            }
        }
    }
}
