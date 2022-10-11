package com.example.linemessagereply.entity;

import java.math.BigInteger;

public class Sensor {
    private String level;
    private String tds;
    private String temp;
    private String UVlevel;
    private String humd;
    private String time;
    private String deviceId;

    public Sensor(String level, String tds, String temp, String UVlevel, String humd, String time, String deviceId){
        this.level = level;
        this.tds = tds;
        this.temp = temp;
        this.UVlevel = UVlevel;
        this.humd = humd;
        this.time = time;
        this.deviceId = deviceId;
    }

    public String getLevel() {
        return level;
    }
    public String getTds() {
        return tds;
    }
    public String getTemp() {
        return temp;
    }
    public String getUVlevel() {
        return UVlevel;
    }
    public String getHumd() {
        return humd;
    }
    public String getTime() { return time; }
    public String getDate() {
        String date = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date(Long.parseLong(getTime())));
        return date;
    }

    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "level='" + level + '\'' +
                ", tds='" + tds + '\'' +
                ", temp='" + temp + '\'' +
                ", UVlevel='" + UVlevel + '\'' +
                ", humd='" + humd + '\'' +
                ", time='" + time + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
