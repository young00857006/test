package com.example.linemessagereply.analyzedata;

import com.example.linemessagereply.entity.Member;
import com.example.linemessagereply.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;


public class analyzemain {
    @Autowired MemberService memberService;
    public analyzemain(){
//        Timer timer = new Timer();
        // 設定填入schedule中的 Date firstTime為現在的15秒後
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
//        calendar.set(2022,8,27,17,31,15);
//        Date firstTime = calendar.getTime();
//
//        System.out.println("In testScheduleDateAndPeriod："+firstTime);
//
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("Task 執行時間：" + new Date());
//                final LineMessagingClient client = LineMessagingClient
//                        .builder("aGLSAm6glSjuEZejBncJkwh1C2YAQCivBnHkwtq+JDh1pgEeYP/fnuk/L44zWMZAs7XpceyBlpzopMEIywUJA3Q1tEnkXOOzk4gr/Ncxffs4NP/K91qvi1vSEHga+Lt2L4P9kmx3ICRE0FNt6Tm94gdB04t89/1O/w1cDnyilFU=")
//                        .build();
//
//                final TextMessage textMessage = new TextMessage("hello");
//                final PushMessage pushMessage = new PushMessage(
//                        "U3b03057f85dec9078836bf82eafade5f",
//                        textMessage);
//
//                final BotApiResponse botApiResponse;
//                try {
//                    botApiResponse = client.pushMessage(pushMessage).get();
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                    return;
//                }
//
//                System.out.println(botApiResponse);
//
//
//            }
//        }, firstTime, 20000);

//        for(Member i :memberService.getAll()){
//            System.out.println(i.toString() + "\n");
//        }
//
//            GetApi test = new GetApi(member.getDeviceId());
//            Sensor sensor = test.getSensor();
//
////        List<Sensor> list = new ArrayList();
////        GetApi test2 = new GetApi("e762d3d0-328b-11ed-92f4-e76ba21be8a9", "1662955200000","1663686000000");
////        list = test2.getSensors();
////        System.out.println(list);
//            final LineMessagingClient client = LineMessagingClient
//                    .builder("aGLSAm6glSjuEZejBncJkwh1C2YAQCivBnHkwtq+JDh1pgEeYP/fnuk/L44zWMZAs7XpceyBlpzopMEIywUJA3Q1tEnkXOOzk4gr/Ncxffs4NP/K91qvi1vSEHga+Lt2L4P9kmx3ICRE0FNt6Tm94gdB04t89/1O/w1cDnyilFU=")
//                    .build();
//
//            final TextMessage textMessage = new TextMessage(sensor.toString());
//            final PushMessage pushMessage = new PushMessage(
//                    "Udace952f75974d78654108058b606009",
//                    textMessage);
//
//            final BotApiResponse botApiResponse;
//            try {
//                botApiResponse = client.pushMessage(pushMessage).get();
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//                return;
//            }
//
//            System.out.println(botApiResponse);
//        }
    }
}
