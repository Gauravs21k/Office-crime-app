package com.gaurav.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gaurav.criminalintent.database.CrimeBaseHelper;
import com.gaurav.criminalintent.database.CrimeCursorWrapper;
import com.gaurav.criminalintent.database.CrimeDBSchema;
import com.gaurav.criminalintent.database.CrimeDBSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> cCrimes;
    private Context cContext;
    private SQLiteDatabase cDatabase;

    private CrimeLab(Context context) {
        cContext = context.getApplicationContext();
        cDatabase = new CrimeBaseHelper(cContext).getWritableDatabase();
    }

    public void addCrime(Crime crime) {
        //cCrimes.add(crime);
        ContentValues contentValues = getContentValues(crime);
        cDatabase.insert(CrimeTable.NAME, null, contentValues);
    }

    public List<Crime> getcCrimes() {
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }


        return crimes;
    }

    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeTable.Cols.UUID + "=?", new String[] {id.toString()});

        try {
            if (cursor.getCount() ==0 ) {
                return null;
            }

            cursor.moveToFirst();
            return  cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getcId().toString();
        ContentValues contentValues = getContentValues(crime);
        cDatabase.update(CrimeTable.NAME, contentValues,
                CrimeTable.Cols.UUID + "=?",
                new String[] {uuidString});
    }

    public CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = cDatabase.query(CrimeTable.NAME,
                null, // columns - null selects all column
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
                );
        return new CrimeCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CrimeTable.Cols.UUID, crime.getcId().toString());
        contentValues.put(CrimeTable.Cols.TITLE, crime.getcTitle());
        contentValues.put(CrimeTable.Cols.DATE, crime.getcDate().toString());
        contentValues.put(CrimeTable.Cols.REQUIRE_POLICE, crime.getcRequirePolice());
        contentValues.put(CrimeTable.Cols.SOLVED, crime.iscSolved());

        return contentValues;
    }

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null)
            sCrimeLab = new CrimeLab(context);
        return sCrimeLab;
    }

    public void deleteCrime(Crime crime) {
        String uuidString = crime.getcId().toString();
        ContentValues contentValues = getContentValues(crime);
        cDatabase.delete(CrimeTable.NAME,
                CrimeTable.Cols.UUID + "=?",
                new String[] {uuidString});

    }
}
