package com.gaurav.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.gaurav.criminalintent.Crime;
import com.gaurav.criminalintent.database.CrimeDBSchema.CrimeTable;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeTable.Cols.DATE));
        int isRequirePolice = getInt(getColumnIndex(CrimeTable.Cols.REQUIRE_POLICE));
        int isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOLVED));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setcTitle(title);
        crime.setcDate(new Date(date));
        crime.setcRequiresPolice(isRequirePolice != 0);
        crime.setcSolved(isSolved != 0);

        return crime;
    }
}
