package com.gaurav.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID cId;
    private String cTitle;
    private Date cDate;
    private boolean cSolved;
    private boolean cRequiresPolice;

    public Crime() {
        cId = UUID.randomUUID();
        cDate = new Date();
    }

    public boolean iscRequiresPolice() {
        return cRequiresPolice;
    }

    public void setcRequiresPolice(boolean cRequiresPolice) {
        this.cRequiresPolice = cRequiresPolice;
    }

    public UUID getcId() {
        return cId;
    }

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    public boolean iscSolved() {
        return cSolved;
    }

    public void setcSolved(boolean cSolved) {
        this.cSolved = cSolved;
    }
}
