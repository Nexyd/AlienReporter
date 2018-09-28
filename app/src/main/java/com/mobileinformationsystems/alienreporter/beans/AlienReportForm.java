package com.mobileinformationsystems.alienreporter.beans;

import java.io.Serializable;

public class AlienReportForm implements Serializable {
    private String id;
    private String caption;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
