package com.example.linemessagereply.reppository;

import com.example.linemessagereply.entity.Member;
import com.example.linemessagereply.entity.Sensor;
import com.example.linemessagereply.ideachain.GetApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WaterAnalyzeRepository {

    @Autowired MemberRepository memberRepo;

    /*1662955200000*//*1663686000000*/
    public List<Sensor> getTimeZoneData(String startTime , String  endTime, String deviceId){
        GetApi getdSensors = new GetApi(deviceId, startTime,endTime);
        List<Sensor> listSensors = getdSensors.getSensors();
        return  listSensors;
    }

    public long analyWaterLevel(String deviceId, String time){
        GetApi test2 = new GetApi(deviceId, String.valueOf(Long.parseLong(time)-86400000),String.valueOf(Long.parseLong(time)-3600000));
        GetApi test = new GetApi(deviceId);
        Sensor sensorNew = test.getSensor();
        List<Sensor> sensorList = test2.getSensors();
        long recordTime = 0;
        for(Sensor sensor : sensorList){
            if(sensorNew.getLevel() == "1"){
                if(sensor.getLevel() == "0"){
                    recordTime = Long.parseLong(sensorNew.getTime()) - Long.parseLong(sensor.getTime());
                }
            }
            else if(sensorNew.getLevel() == "0"){
                if (sensor.getLevel() == "1"){
                    recordTime = Long.parseLong(sensorNew.getTime()) - Long.parseLong(sensor.getTime());
                }
            }
        }
        return recordTime;
    }
}
