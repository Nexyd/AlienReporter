package com.mobileinformationsystems.alienreporter.beans;

public class AlienReport {
    private String userId;
    private String formId;
    private String[] form;
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

    public String[] getForm() {
        return form;
    }

    public void setForm(String[] form) {
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
