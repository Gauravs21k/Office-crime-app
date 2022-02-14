package com.gaurav.criminalintent.database;

public class CrimeDBSchema {
    public static final class CrimeTable {
        public static final String NAME = "crimes";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String REQUIRE_POLICE = "require_police";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
        }
    }
}
