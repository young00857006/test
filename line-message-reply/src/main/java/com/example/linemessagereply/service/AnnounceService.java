package com.example.linemessagereply.service;

import com.example.linemessagereply.ideachain.GetApi;
import com.example.linemessagereply.entity.Member;
import com.example.linemessagereply.entity.Sensor;
import com.example.linemessagereply.reppository.WaterAnalyzeRepository;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AnnounceService {
    @Autowired
    private MemberService memberService;
    private WaterAnalyzeRepository waterAnalyzeRepo;

    public void checkWaterquality(String userId, String deviceId){ //announce uv, temp, hum

            GetApi test = new GetApi(deviceId);
            Sensor sensor = test.getSensor();
            List<Sensor> sensorList = waterAnalyzeRepo.getTimeZoneData(String.valueOf(System.currentTimeMillis()-3600000),String.valueOf(System.currentTimeMillis()),deviceId);
            String message = null ;
            if(Float.parseFloat(sensor.getTds())>=500){
                message += "水質 混濁！！！";
            }
            else if((Float.parseFloat(sensor.getTds()) - Float.parseFloat(sensorList.get(0).getTds())) > 50){
                message += "水質 變糟！！！";
            }

            /*push Message*/
            if(message != null){
                final LineMessagingClient client = LineMessagingClient
                        .builder("aGLSAm6glSjuEZejBncJkwh1C2YAQCivBnHkwtq+JDh1pgEeYP/fnuk/L44zWMZAs7XpceyBlpzopMEIywUJA3Q1tEnkXOOzk4gr/Ncxffs4NP/K91qvi1vSEHga+Lt2L4P9kmx3ICRE0FNt6Tm94gdB04t89/1O/w1cDnyilFU=")
                        .build();
                final TextMessage textMessage = new TextMessage(message);
                final PushMessage pushMessage = new PushMessage(
                        userId,
                        textMessage);
                final BotApiResponse botApiResponse;
                try {
                    botApiResponse = client.pushMessage(pushMessage).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return;
                }
                System.out.println(botApiResponse);
            }
    }

    public void checkWaterlevel(String userId, String deviceId){
        for(Member i :memberService.getAll()){

            GetApi test = new GetApi(deviceId);
            Sensor sensor = test.getSensor();
            String message = null;
            if(waterAnalyzeRepo.analyWaterLevel(deviceId,String.valueOf(System.currentTimeMillis())) > 18000000){
                if(sensor.getLevel() == "1"){
                    message = "水塔持續進水，超過5小時！！！";
                }
                else{
                    message = "水塔持續沒水，超過5小時！！！";
                }
                message += "（如果沒有用水或持續用水，請乎略此通知）";
            }
            /*push Message*/
            if(message != null){
                final LineMessagingClient client = LineMessagingClient
                        .builder("aGLSAm6glSjuEZejBncJkwh1C2YAQCivBnHkwtq+JDh1pgEeYP/fnuk/L44zWMZAs7XpceyBlpzopMEIywUJA3Q1tEnkXOOzk4gr/Ncxffs4NP/K91qvi1vSEHga+Lt2L4P9kmx3ICRE0FNt6Tm94gdB04t89/1O/w1cDnyilFU=")
                        .build();
                final TextMessage textMessage = new TextMessage(message);
                final PushMessage pushMessage = new PushMessage(
                        userId,
                        textMessage);
                final BotApiResponse botApiResponse;
                try {
                    botApiResponse = client.pushMessage(pushMessage).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return;
                }
                System.out.println(botApiResponse);
            }

        }
    }

    public void checkTemp(String userId, String deviceId){
        GetApi test = new GetApi(deviceId);
        Sensor sensor = test.getSensor();
        String message = "溫度："+sensor.getTemp() + "ºc";
        final LineMessagingClient client = LineMessagingClient
                .builder("aGLSAm6glSjuEZejBncJkwh1C2YAQCivBnHkwtq+JDh1pgEeYP/fnuk/L44zWMZAs7XpceyBlpzopMEIywUJA3Q1tEnkXOOzk4gr/Ncxffs4NP/K91qvi1vSEHga+Lt2L4P9kmx3ICRE0FNt6Tm94gdB04t89/1O/w1cDnyilFU=")
                .build();
        final TextMessage textMessage = new TextMessage(message);
        final PushMessage pushMessage = new PushMessage(
                userId,
                textMessage);
        final BotApiResponse botApiResponse;
        try {
            botApiResponse = client.pushMessage(pushMessage).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(botApiResponse);
    }

    public void checkUV(String userId, String deviceId){
        GetApi test = new GetApi(deviceId);
        Sensor sensor = test.getSensor();
        String message = "";
        if(Float.parseFloat(sensor.getUVlevel())>795){
            message += "紫外光 危險！！！";
        }
        else if(Float.parseFloat(sensor.getUVlevel())<318){
            message += "紫外線 適中";
        }
        else{
            message += "紫外線 安全";
        }
        final LineMessagingClient client = LineMessagingClient
                .builder("aGLSAm6glSjuEZejBncJkwh1C2YAQCivBnHkwtq+JDh1pgEeYP/fnuk/L44zWMZAs7XpceyBlpzopMEIywUJA3Q1tEnkXOOzk4gr/Ncxffs4NP/K91qvi1vSEHga+Lt2L4P9kmx3ICRE0FNt6Tm94gdB04t89/1O/w1cDnyilFU=")
                .build();
        final TextMessage textMessage = new TextMessage(message);
        final PushMessage pushMessage = new PushMessage(
                userId,
                textMessage);
        final BotApiResponse botApiResponse;
        try {
            botApiResponse = client.pushMessage(pushMessage).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(botApiResponse);
    }
//        else if(text.contains("紫外線")){
//            if(Float.parseFloat(sensor.getUVlevel())>795){
//                message += "紫外光 危險！！！";
//            }
//            else if(Float.parseFloat(sensor.getUVlevel())<318){
//                message += "紫外線 適中";
//            }
//            else{
//                message += "紫外線 安全";
//            }
//        }
//    }


}
