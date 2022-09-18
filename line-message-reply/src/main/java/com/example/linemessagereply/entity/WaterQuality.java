package com.example.linemessagereply.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "waterquality")
public class WaterQuality {
    private int NO;
    private String TDSvalue;
    private String DATETIME;

    public WaterQuality(int NO, String TDSvalue, String DATETIME){
        this.NO = NO;
        this.DATETIME = DATETIME;
        this.TDSvalue = TDSvalue;
    }


    public void setNO(int NO) {
        this.NO = NO;
    }

    public void setDATETIME(String DATETIME) {
        this.DATETIME = DATETIME;
    }

    public void setTDSvalue(String TDSvalue) {
        this.TDSvalue = TDSvalue;
    }

    public int getNO() {
        return NO;
    }

    public String getDATETIME() {
        return DATETIME;
    }

    public String getTDSvalue() {
        return TDSvalue;
    }
}
