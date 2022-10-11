package com.example.linemessagereply.handler;


import com.example.linemessagereply.entity.Member;
import com.example.linemessagereply.service.AnnounceService;
import com.example.linemessagereply.service.MemberService;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;


@Component
public class ScheduledTasks {

//    @Scheduled(initialDelay = 2000, fixedRate = 3000)
//    public void printUnixEpochTime() {
//        System.out.println(System.currentTimeMillis());
//    }
    @Autowired
    private AnnounceService announceService;
    private MemberService memberService;
//    @Scheduled(cron = "0 0 7 * * ?") // cron接受cron表示式，根據cron表示式確定定時規則
//    public void Crondaily() {
//        Date dNow = new Date( );
//        SimpleDateFormat ft = new SimpleDateFormat (" yyyy.MM.dd E hh:mm:ss");
//        for(Member i : memberService.getAll()) {
//            final LineMessagingClient client = LineMessagingClient
//                    .builder("aGLSAm6glSjuEZejBncJkwh1C2YAQCivBnHkwtq+JDh1pgEeYP/fnuk/L44zWMZAs7XpceyBlpzopMEIywUJA3Q1tEnkXOOzk4gr/Ncxffs4NP/K91qvi1vSEHga+Lt2L4P9kmx3ICRE0FNt6Tm94gdB04t89/1O/w1cDnyilFU=")
//                    .build();
//            final TextMessage textMessage = new TextMessage(ft.format(dNow));
//            final PushMessage pushMessage = new PushMessage(
//                    i.getuserId(),
//                    textMessage);
//            final BotApiResponse botApiResponse;
//            try {
//                botApiResponse = client.pushMessage(pushMessage).get();
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//                return;
//            }
//            System.out.println(botApiResponse);
//            announceService.checkTemp(i.getuserId(), i.getdeviceId());
//            announceService.checkUV(i.getuserId(), i.getdeviceId());
//        }
//    }
//
//    @Scheduled(cron = "0 0 * * * *") // cron接受cron表示式，根據cron表示式確定定時規則
//    public void Cron() {
//        for(Member i : memberService.getAll()){
//            announceService.checkWaterquality(i.getuserId(), i.getdeviceId());
//            announceService.checkWaterlevel(i.getuserId(), i.getdeviceId());
//
//        }
//
//    }
}
