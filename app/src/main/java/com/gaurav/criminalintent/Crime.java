package com.gaurav.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID cId;
    private String cTitle;
    private Date cDate;
    private boolean cSolved;
    private boolean cRequiresPolice;
    private String cSuspect;

    public Crime() {
        this(UUID.randomUUID());

    }

    public Crime(UUID id) {
        cId = id;
        cDate = new Date();
    }



    public UUID getId() {
        return cId;
    }

    public String getTitle() {
        return cTitle;
    }

    public void setTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public Date getDate() {
        return cDate;
    }

    public void setDate(Date cDate) {
        this.cDate = cDate;
    }

    public boolean isSolved() {
        return cSolved;
    }

    public void setSolved(boolean cSolved) {
        this.cSolved = cSolved;
    }

    public boolean isRequiresPolice() {
        return cRequiresPolice;
    }

    public void setRequiresPolice(boolean cRequiresPolice) {
        this.cRequiresPolice = cRequiresPolice;
    }

    public String getSuspect() {
        return cSuspect;
    }

    public void setSuspect(String cSuspect) {
        this.cSuspect = cSuspect;
    }

    public String getPhotoFileName() {
        return "IMG" + getId().toString() + ".jpg";
    }
}
