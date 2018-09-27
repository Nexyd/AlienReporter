package com.mobileinformationsystems.alienreporter.beans;

import java.util.List;

public class AlienReport {
    private String userId;
    private String formId;
    private List<AlienReportForm> form;
    private String lastChangedDate;
    private String lastChangedBy;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public List<AlienReportForm> getForm() {
        return form;
    }

    public void setForm(List<AlienReportForm> form) {
        this.form = form;
    }

    public String getLastChangedDate() {
        return lastChangedDate;
    }

    public void setLastChangedDate(String lastChangedDate) {
        this.lastChangedDate = lastChangedDate;
    }

    public String getLastChangedBy() {
        return lastChangedBy;
    }

    public void setLastChangedBy(String lastChangedBy) {
        this.lastChangedBy = lastChangedBy;
    }
}
